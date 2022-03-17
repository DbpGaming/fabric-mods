package net.dbp.basic_ores;

import net.devtech.arrp.api.*;
import net.devtech.arrp.json.blockstate.*;
import net.devtech.arrp.json.models.*;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.json.tags.*;
import net.devtech.arrp.json.loot.*;
import net.devtech.arrp.json.loot.JLootTable.*;
import java.util.*;
import java.util.function.Predicate;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.*;
import net.minecraft.recipe.CookingRecipeSerializer;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.structure.rule.RuleTest;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.placementmodifier.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.*;
import org.apache.commons.lang3.ArrayUtils;
import org.slf4j.*;
import it.unimi.dsi.fastutil.Hash;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;


public class Main implements ModInitializer {
	public static final String modid = "teknologi";
	public static final RuntimeResourcePack RESOURCE_PACK = RuntimeResourcePack.create(modid+":resource-pack");
	public static final Logger LOGGER = LoggerFactory.getLogger(modid);
	public static final boolean babylonCompat = FabricLoader.getInstance().isModLoaded("gateofbabylon");
	public static final boolean bclibCompat = FabricLoader.getInstance().isModLoaded("bclib");
	public static final boolean moreTagsCompat = FabricLoader.getInstance().isModLoaded("moretags");
	public static final boolean fabricShieldLibCompat = FabricLoader.getInstance().isModLoaded("fabricshieldlib");

	public static Block TEST_FURNACE_BLOCK;
	public static RecipeType<TestRecipe> TEST_RECIPE_TYPE;
	public static BlockEntityType TEST_FURNACE_BLOCK_ENTITY;
	public static RecipeSerializer<TestRecipe> TEST_RECIPE_SERIALIZER;
	public static ScreenHandlerType<TestFurnaceScreenHandler> TEST_FURNACE_SCREEN_HANDLER;

	
	public static final String[] vanilla = {"plate", "gear", "dust"};
	public static final String[] metal = {"ingot", "nugget", "raw_ore", "plate", "gear", "dust", "wire", "can", "large_plate", "tube"};
	public static final String[] gem = {"gem", "dust"};
	public static final String[] ore = {"ore", "ore_gravel", "block"};
	public static final String[] block = {"block"};
	public static String[] tools = {
		"axe", "hoe", "pickaxe", "shovel", "sword", "shear", "shield", "bow", "fishingrod", "hammer", "excavator", "helmet", "chestplate", "legging", "boot",
		"spanner", "paxel", "dagger", "spear", "broadsword", "rapier", "haladie", "waraxe", "katana", "boomerang"
	};
	String[] hammerShape = {" M ", " HM", "H  "};
	String[] excavatorShape = {" M  ", "MHM", " H "};
	String[] pickaxeShape = {"PMM", " H ", " H "};
	public static final HashSet<String> shears = new HashSet<>();
	public static final HashSet<String> mattags = new HashSet<>();
	public static final HashSet<String> pickblocks = new HashSet<>();

	@Override
	public void onInitialize() {
		TEST_FURNACE_BLOCK = Registry.register(Registry.BLOCK, new Identifier(modid, "test_furnace"), new TestFurnace(FabricBlockSettings.of(Material.METAL)));
		Registry.register(Registry.ITEM, new Identifier(modid, "test_furnace"), new BlockItem(TEST_FURNACE_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
		TEST_FURNACE_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY_TYPE, new Identifier(modid, "test_furnace"), FabricBlockEntityTypeBuilder.create(TestFurnaceBlockEntity::new, TEST_FURNACE_BLOCK).build(null));
		TEST_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(modid, "test_furnace"), new CookingRecipeSerializer<>(TestRecipe::new, 200));
		TEST_FURNACE_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(modid, "test_furnace"), TestFurnaceScreenHandler::new);
        TEST_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(modid, "test_furnace"), new RecipeType<TestRecipe>() {
            @Override
            public String toString() {return "test_furnace";}
        });

		AutoConfig.register(BasicConfig.class, Toml4jConfigSerializer::new);
		Mat nickel = new Mat("nickel", 0x464D19).setMagicNumber(2, 2).addItemParts(metal, tools).addBlockPart(ore);
		Mat tin = new Mat("tin", 0xE0E0FF).addItemParts(metal).addBlockPart(ore);
		Mat tungsten = new Mat("tungsten", 0x181b07).addItemParts(metal).addBlockPart(ore);
		Mat titanium = new Mat("titanium", 0xc4b4ed).addItemParts(metal).addBlockPart(ore);
		Mat ruby = new Mat("ruby", 0xbb4a1d).setMagicNumber(2, 3).addItemParts(gem, tools).addBlockPart(ore);
		Mat sapphire = new Mat("sapphire", 0x1b55b8).setMagicNumber(2, 3).addItemParts(gem, tools).addBlockPart(ore);
		Mat peridot = new Mat("peridot", 0x08dd7b).setMagicNumber(3, 3).addItemParts(gem).addBlockPart(ore);
		Mat emerald = new Mat("emerald", 0x08dd7b).setMagicNumber(3, 3).addItemPart("dust").addItemParts(tools).addBlockPart(ore);
		Mat galena = new Mat("galena", 0xFFFFFF).addBlockPart("ore");
		Mat nikolite = new Mat("nikolite", 0x1273de).addItemPart("dust").addBlockPart("ore");
		Mat lead = new Mat("lead", 0x544773).addItemParts(metal).addBlockParts(block);
		Mat silver = new Mat("silver", 0x9cbddc).addItemParts(metal).addBlockParts(block);
		Mat platinum = new Mat("platinum", 0x70b6f7).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block);
		Mat gold = new Mat("gold", 0xe7ca53).addItemParts(vanilla);
		Mat copper = new Mat("copper", 0xc78621).addItemParts(vanilla).setMagicNumber(2, 1);
		Mat iron = new Mat("iron", 0xE0E0E0).addItemParts(vanilla).addItemPart("shield", "bow", "fishingrod", "hammer", "excavator");
		Mat bronze = new Mat("bronze", 0xc69114).setMagicNumber(2, 2).addItemParts(metal).addBlockParts(block);
		Mat brass = new Mat("brass", 0xdba31e).addItemParts(metal).addBlockParts(block);
		Mat wroughtiron = new Mat("wroughtiron", 0xceaa9f).addItemParts(metal).addBlockParts(block);
		Mat cobalt = new Mat("cobalt", 0x505080).setMagicNumber(3, 5).addItemParts(metal, tools).addBlockParts(block);
		Mat chromium = new Mat("chromium", 0xf4c4b5).addItemParts(metal).addBlockParts(block);
		Mat invar = new Mat("invar", 0xcebe7c).setMagicNumber(2, 3).addItemParts(metal, tools).addBlockParts(block);
		Mat electrum = new Mat("electrum", 0xf3d248).addItemParts(metal).addBlockParts(block);
		Mat aluminium = new Mat("aluminium", 0xbad4ec).addItemParts(metal).addBlockParts(block);
		Mat steel = new Mat("steel", 0x424c55).setMagicNumber(2, 3).addItemParts(metal).addBlockParts(block);
		Mat tungstensteel = new Mat("tungstensteel", 0x274562).setMagicNumber(4, 4).addItemParts(metal, tools).addBlockParts(block);
		Mat zinc = new Mat("zinc", 0xbba69f).addItemParts(metal).addBlockParts(block);
		Mat osmium = new Mat("osmium", 0x93bbe8).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block);
		Mat iridium = new Mat("iridium", 0xFFFFFF).addItemParts(metal, tools).addBlockParts(block);
		Mat magnesium = new Mat("magnesium", 0x000000).addItemParts(metal).addBlockParts(ore);
		
		registerMat(nickel);
		registerMat(tin);
		registerMat(tungsten);
		registerMat(titanium);
		registerMat(ruby);
		registerMat(sapphire);
		registerMat(peridot);
		registerMat(emerald);
		registerMat(galena);
		registerMat(lead);
		registerMat(silver);
		registerMat(platinum);
		registerMat(gold);
		registerMat(copper);
		registerMat(iron);
		registerMat(bronze);
		registerMat(brass);
		registerMat(wroughtiron);
		registerMat(cobalt);
		registerMat(chromium);
		registerMat(invar);
		registerMat(electrum);
		registerMat(aluminium);
		registerMat(steel);
		registerMat(tungstensteel);
		registerMat(zinc);
		registerMat(osmium);
		registerMat(iridium);
		registerMat(nikolite);
		registerMat(magnesium);

		registerOre("nickel_ore_overworld", BiomeSelectors.foundInOverworld(), OreConfiguredFeatures.STONE_ORE_REPLACEABLES, nickel.blockPartsBlocks.get("ore"), 9, 20, -12, 64);
		registerOre("tin_ore_nether", BiomeSelectors.foundInTheNether(), OreConfiguredFeatures.NETHERRACK, tin.blockPartsBlocks.get("ore"), 9, 40, 10, 112);
		if (bclibCompat) addTags(shears, "fabric:items/shears");
		if (moreTagsCompat) addTags(shears, "c:items/shears");
		addTags(pickblocks, "minecraft:blocks/mineable/pickaxe");
		addTags(pickblocks, "minecraft:blocks/needs_stone_tool");

		RESOURCE_PACK.addRecipe(new Identifier(modid, "pickaxes/iridium"), JRecipe.smithing(JIngredient.ingredient().item(Items.NETHERITE_PICKAXE), JIngredient.ingredient().item(iridium.itemParts.get("plate")), JResult.item(iridium.itemParts.get("pickaxe"))));
	}

	public void registerMat(Mat mat){
		for (Map.Entry<String, Item> set : mat.itemParts.entrySet()) {
			registerItem(set.getValue(), set.getKey()+"_"+mat.name);
			switch (set.getKey()) {
				case "bow": registerItemModel(set.getKey()+"_"+mat.name, "item/bow", set.getKey()); break;
				case "shield": registerItemModel(set.getKey()+"_"+mat.name, "fabricshieldlib:item/fabric_shield", set.getKey()); break;
				default: registerItemModel(set.getKey()+"_"+mat.name, "item/generated", set.getKey()); break;
			}
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.color, set.getValue());
			//RESOURCE_PACK.addTag(new Identifier("c:items/"+mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
			RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+mat.name), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
		}

		for (Map.Entry<String, Item> set : mat.blockPartsItems.entrySet()) {
			registerBlock(set.getValue(), mat.blockPartsBlocks.get(set.getKey()), set.getKey()+"_"+mat.name);

			if (set.getKey() == "ore" || set.getKey() == "ore_gravel")
				registerBlockModel(set.getKey()+"_"+mat.name, "block/cube_all", set.getKey()+"_"+mat.name);
			else
				registerBlockModel(set.getKey()+"_"+mat.name, "block/leaves", set.getKey());
			ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> mat.color, mat.blockPartsBlocks.get(set.getKey()));
			ColorProviderRegistry.ITEM.register((stack, tintIndex) -> mat.color, set.getValue());
			//RESOURCE_PACK.addTag(new Identifier("c:items/"+mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
			RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+mat.name), new JTag().add(new Identifier(modid+":"+set.getKey()+"_"+mat.name)));
			RESOURCE_PACK.addLootTable(new Identifier(modid+":"+"blocks/"+set.getKey()+"_"+mat.name), JLootTable.loot("minecraft:block").pool(new JPool().rolls(1).entry(new JEntry().type("minecraft:item").name(modid+":"+set.getKey()+"_"+mat.name).condition("minecraft:survives_explosion"))));
			pickblocks.add(set.getKey()+"_"+mat.name);
		}

		if(mat.itemParts.containsKey("ingot")){
			if (mat.itemParts.containsKey("nugget")){
				RESOURCE_PACK.addRecipe(new Identifier(modid, "ingot_to_nugget"+"/"+mat.name), JRecipe.shapeless(JIngredients.ingredients().add(JIngredient.ingredient().item(mat.itemParts.get("ingot"))), JResult.itemStack(mat.itemParts.get("nugget"), 9)));
				RESOURCE_PACK.addRecipe(new Identifier(modid, "nugget_to_ingot"+"/"+mat.name), JRecipe.shaped(JPattern.pattern("NNN", "NNN", "NNN"), JKeys.keys().key("N", JIngredient.ingredient().item(mat.itemParts.get("nugget"))), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
			}

			if (mat.blockPartsItems.containsKey("ore")){
				RESOURCE_PACK.addRecipe(new Identifier(modid, "ore_to_ingot"+"/"+mat.name), JRecipe.smelting(JIngredient.ingredient().item(mat.blockPartsItems.get("ore")), JResult.itemStack(mat.itemParts.get("ingot"), 1)));
			}

			if (mat.itemParts.containsKey("hammer")) createToolRecipe(mat.name, "hammer", hammerShape, "large_plates", mat.itemParts.get("hammer"), Items.STICK);
			if (mat.itemParts.containsKey("excavator")) createToolRecipe(mat.name, "excavator", excavatorShape, "large_plates", mat.itemParts.get("excavator"), Items.STICK);
			if (mat.itemParts.containsKey("pickaxe")) createToolRecipe(mat.name, "pickaxe", pickaxeShape, "ingots", "plates", mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if(mat.itemParts.containsKey("gem")){
			if (mat.itemParts.containsKey("hammer")) createToolRecipe(mat.name, "hammer", hammerShape, "blocks", mat.itemParts.get("hammer"), Items.STICK);
			if (mat.itemParts.containsKey("excavator")) createToolRecipe(mat.name, "excavator", excavatorShape, "blocks", mat.itemParts.get("excavator"), Items.STICK);
			if (mat.itemParts.containsKey("pickaxe")) createToolRecipe(mat.name, "pickaxe", pickaxeShape, "gems", "gems", mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if (mat.itemParts.containsKey("shear")){
			shears.add("shears_"+mat.name);
		}
	}

	public void createToolRecipe(String name, String recipeName, String[] shape, String mat, String mat2, Item item, Item handle){
		RESOURCE_PACK.addRecipe(new Identifier(modid, recipeName+name), 
		JRecipe.shaped(JPattern.pattern(shape[0], shape[1], shape[2]), JKeys.keys()
			.key("M", JIngredient.ingredient().tag("c:"+mat+"/"+name))
			.key("P", JIngredient.ingredient().tag("c:"+mat2+"/"+name))
			.key("H", JIngredient.ingredient().item(Items.STICK)),
			JResult.itemStack(item, 1)));
	}

	public void createToolRecipe(String name, String recipeName, String[] shape, String mat, Item item, Item handle){
		RESOURCE_PACK.addRecipe(new Identifier(modid, recipeName+name), 
		JRecipe.shaped(JPattern.pattern(shape[0], shape[1], shape[2]), JKeys.keys()
			.key("M", JIngredient.ingredient().tag("c:"+mat+"/"+name))
			.key("H", JIngredient.ingredient().item(Items.STICK)),
			JResult.itemStack(item, 1)));
	}

	public void addTags(HashSet<String> hashset, String namespace){
		JTag jtag = new JTag();
		for (String string : hashset){
			jtag.add(new Identifier(modid+":"+string));
		}

		RESOURCE_PACK.addTag(new Identifier(namespace), jtag);
	}

	public void registerOre(String name, Predicate<BiomeSelectionContext> predicate, RuleTest replace, Block block, Integer vein_size, Integer veins_per_chunk, Integer min_height, Integer max_height){
//		ConfiguredFeature<?, ?> ORE_FEATURE = Feature.ORE.configure(new OreFeatureConfig(replace,block.getDefaultState(), vein_size));
//		ConfiguredFeature<OreFeatureConfig, ?> ORE_FEATURE = new ConfiguredFeature<>(Feature.ORE, new OreFeatureConfig(replace,block.getDefaultState(), vein_size));
//		PlacedFeature ORE_FEATURE_PLACED = ORE_FEATURE.withPlacement(CountPlacementModifier.of(veins_per_chunk), SquarePlacementModifier.of(), HeightRangePlacementModifier.uniform(YOffset.fixed(min_height), YOffset.fixed(max_height)));	
//		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE,	new Identifier(modid, name), ORE_FEATURE);
//		Registry.register(BuiltinRegistries.PLACED_FEATURE, new Identifier(modid, name), ORE_FEATURE_PLACED);
//		BiomeModifications.addFeature(predicate, GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.PLACED_FEATURE_KEY, new Identifier(modid, name)));
	}

	public void registerItem(Item item, String name){
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}

	public void registerItemModel(String modelName, String modelBase, String textureName){
		RESOURCE_PACK.addModel(JModel.model(modelBase).textures(new JTextures().layer0(modid+":item/"+textureName)), new Identifier(modid, "item/"+modelName));
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(RESOURCE_PACK));
	}

	public void registerBlock(Item item, Block block, String name){
		Registry.register(Registry.BLOCK, new Identifier(modid, name), block);
        Registry.register(Registry.ITEM, new Identifier(modid, name), item);
	}

	public void registerBlockModel(String modelName, String modelBase, String textureName){
		RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(new Identifier(modid+":block/"+modelName)))), new Identifier(modid, modelName));
		RESOURCE_PACK.addModel(JModel.model().parent(modelBase).textures(new JTextures().var("all",modid+":block/"+textureName)), new Identifier(modid, "block/"+modelName));
		RESOURCE_PACK.addModel(JModel.model().parent(modid+":block/"+modelName), new Identifier(modid, "item/"+modelName));
	}
}
