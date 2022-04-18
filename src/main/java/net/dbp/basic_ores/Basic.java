package net.dbp.basic_ores;

import net.devtech.arrp.api.*;
import net.devtech.arrp.json.recipe.*;
import java.util.*;
import java.util.function.Predicate;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.structure.rule.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.util.registry.*;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.stateprovider.*;
import net.dbp.basic_ores.GravelOreFeature;
import net.dbp.basic_ores.machine_api.*;
import net.dbp.basic_ores.material_api.BasicMaterials;
import net.dbp.basic_ores.recipe_api.AdvancedRecipeSerializer;

import org.slf4j.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;

public class Basic implements ModInitializer {
	public static final String modid = "teknologi";
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(modid+":resource-pack");
	public static final Logger LOGGER = LoggerFactory.getLogger(modid);
	public static final boolean babylonCompat = FabricLoader.getInstance().isModLoaded("gateofbabylon");
	public static final boolean bclibCompat = FabricLoader.getInstance().isModLoaded("bclib");
	public static final boolean moreTagsCompat = FabricLoader.getInstance().isModLoaded("moretags");
	public static final boolean fabricShieldLibCompat = FabricLoader.getInstance().isModLoaded("fabricshieldlib");
	public static final Block TEST_FURNACE_BLOCK;
	public static final RecipeType<TestRecipe> TEST_RECIPE_TYPE;
	public static final BlockEntityType TEST_FURNACE_BLOCK_ENTITY;
	public static final RecipeSerializer<TestRecipe> TEST_RECIPE_SERIALIZER;
	public static final ScreenHandlerType<TestFurnaceScreenHandler> TEST_FURNACE_SCREEN_HANDLER;
	static {
		TEST_FURNACE_BLOCK = Registry.register(Registry.BLOCK, new Identifier(modid, "test_furnace"), new TestFurnace(FabricBlockSettings.of(Material.METAL)));
		Registry.register(Registry.ITEM, new Identifier(modid, "test_furnace"), new BlockItem(TEST_FURNACE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
		TEST_FURNACE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(modid, "test_furnace"), FabricBlockEntityTypeBuilder.create(TestFurnaceBlockEntity::new, TEST_FURNACE_BLOCK).build(null));
		TEST_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(modid, "test_furnace"), new CookingRecipeSerializer<>(TestRecipe::new, 200));
		TEST_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(modid, "test_furnace"), TestFurnaceScreenHandler::new);
        TEST_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(modid, "test_furnace"), new RecipeType<TestRecipe>() {
            @Override
            public String toString() {return "test_furnace";}
        });
	}

	public record GravelOreFeatureConfig(IntProvider height, BlockStateProvider block) implements FeatureConfig {
        public static final Codec<GravelOreFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group(
          IntProvider.VALUE_CODEC.fieldOf("height").forGetter(GravelOreFeatureConfig::height),
          BlockStateProvider.TYPE_CODEC.fieldOf("block").forGetter(GravelOreFeatureConfig::block)
        ).apply(instance, instance.stable(GravelOreFeatureConfig::new)));
    }

	private static final Feature<GravelOreFeatureConfig> GRAVEL_ORE_FEATURE = new GravelOreFeature(GravelOreFeatureConfig.CODEC);;
	//public static final ConfiguredFeature<?, ?> STONE_SPIRAL = new ConfiguredFeature(GRAVEL_ORE_FEATURE,
	//new GravelOreFeatureConfig(ConstantIntProvider.create(15), BlockStateProvider.of(Blocks.STONE.getDefaultState())))
	//.decorate(Decorator.HEIGHTMAP.configure(new HeightmapDecoratorConfig(Heightmap.Type.OCEAN_FLOOR_WG)))
	//.spreadHorizontally()
	//.applyChance(5);

	@Override
	public void onInitialize() {
		AutoConfig.register(BasicConfig.class, Toml4jConfigSerializer::new);
		BasicMaterials.registerMats();
		BasicMaterials.oregen();
		RESOURCE_PACK.addRecipe(new Identifier(modid, "pickaxes/iridium"), JRecipe.smithing(JIngredient.ingredient().item(Items.NETHERITE_PICKAXE), JIngredient.ingredient().item(BasicMaterials.iridium.itemParts.get("plate")), JResult.item(BasicMaterials.iridium.itemParts.get("pickaxe"))));
		Registry.register(Registry.FEATURE, new Identifier(modid, "gravelore"), GRAVEL_ORE_FEATURE);
		Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(modid, "advanced_recipe"), AdvancedRecipeSerializer.INSTANCE);
	}

	public static void registerItem(Item item, String name){
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}

	public static void registerBlock(Item item, Block block, String name){
		Registry.register(Registry.BLOCK, new Identifier(modid, name), block);
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}
}
