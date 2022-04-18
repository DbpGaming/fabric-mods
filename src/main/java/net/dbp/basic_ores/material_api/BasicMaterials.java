package net.dbp.basic_ores.material_api;

import java.util.*;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.*;
import net.devtech.arrp.json.loot.*;
import net.devtech.arrp.json.recipe.*;
import net.minecraft.util.Identifier;
import net.devtech.arrp.json.tags.JTag;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.loader.api.FabricLoader;
import net.dbp.basic_ores.*;
import net.dbp.basic_ores.ore_api.*;

public class BasicMaterials {
    public static final String[] vanilla = {"plate", "gear", "dust"};
	public static final String[] metal = {"ingot", "nugget", "raw_ore", "plate", "gear", "dust", "wire", "can", "hull", "tube"};
	public static final String[] gem = {"gem", "dust"};
	public static final String[] block = {"block"};
	public static String[] tools = {
		"axe", "hoe", "pickaxe", "shovel", "sword", "shear", "shield", "bow", "fishingrod", "hammer", "excavator", "helmet", "chestplate", "legging", "boot",
		"spanner", "paxel", "dagger", "spear", "broadsword", "rapier", "haladie", "waraxe", "katana", "boomerang"
	};
	public static final HashSet<String> shears = new HashSet<>();
	public static final HashSet<String> tier3hull = new HashSet<>();
	public static final HashSet<String> tier4hull = new HashSet<>();
	public static final HashSet<String> Mattags = new HashSet<>();
	public static final HashSet<String> pickblocks = new HashSet<>();
	
    //vanilla
	public static BasicMaterial gold = new BasicMaterial("gold", 0xe7ca53).addItemParts(vanilla).addTag("tier2");
	public static BasicMaterial copper = new BasicMaterial("copper", 0xc78621).addItemParts(vanilla, tools).setMagicNumber(2, 1).addTag("tier1");
	public static BasicMaterial tetrahedrite = new BasicMaterial("tetrahedrite", 0xc78621).addTag("ore");
	public static BasicMaterial iron = new BasicMaterial("iron", 0xE0E0E0).addItemParts(vanilla).addItemPart("shield", "bow", "fishingrod", "hammer", "excavator").addTag("tier2");
	public static BasicMaterial pyrite = new BasicMaterial("pyrite", 0xE0E0E0).addTag("ore");
	public static BasicMaterial emerald = new BasicMaterial("emerald", 0x08dd7b).setMagicNumber(3, 3).addItemPart("dust").addItemParts(tools);
	//basic
	public static BasicMaterial nickel = new BasicMaterial("nickel", 0x464D19).setMagicNumber(2, 2).addItemParts(metal, tools).addTag("tier2");
	public static BasicMaterial garnierite = new BasicMaterial("garnierite", 0x464D19).addTag("ore");
	public static BasicMaterial galena = new BasicMaterial("galena", 0xFFFFFF).addTag("ore");
	public static BasicMaterial lead = new BasicMaterial("lead", 0x544773).addItemParts(metal).addBlockParts(block).addTag("tier2");
	public static BasicMaterial silver = new BasicMaterial("silver", 0x9cbddc).addItemParts(metal).addBlockParts(block).addTag("tier2");
	public static BasicMaterial platinum = new BasicMaterial("platinum", 0x70b6f7).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block).addTag("tier4");
	public static BasicMaterial sheldonite = new BasicMaterial("sheldonite", 0x181b07).addTag("ore");
	public static BasicMaterial invar = new BasicMaterial("invar", 0xcebe7c).setMagicNumber(2, 3).addItemParts(metal, tools).addBlockParts(block).addTag("tier3");
	public static BasicMaterial electrum = new BasicMaterial("electrum", 0xf3d248).addItemParts(metal).addBlockParts(block).addTag("tier3");
	//basic2
	public static BasicMaterial tin = new BasicMaterial("tin", 0xE0E0FF).addItemParts(metal).addTag("tier2").addTag("tier1");
	public static BasicMaterial cassiterite = new BasicMaterial("cassiterite", 0xE0E0FF).addTag("ore");
	public static BasicMaterial bronze = new BasicMaterial("bronze", 0xc69114).setMagicNumber(2, 2).addItemParts(metal).addBlockParts(block).addTag("tier2");
	public static BasicMaterial steel = new BasicMaterial("steel", 0x424c55).setMagicNumber(2, 3).addItemParts(metal).addBlockParts(block).addTag("tier3");
	public static BasicMaterial aluminium = new BasicMaterial("aluminium", 0xbad4ec).addItemParts(metal).addBlockParts(block).addTag("tier2");
	public static BasicMaterial bauxite = new BasicMaterial("bauxite", 0xbad4ec).addTag("ore");
	//power
	public static BasicMaterial nikolite = new BasicMaterial("nikolite", 0x1273de).addItemPart("dust").addTag("ore");
	public static BasicMaterial ruby = new BasicMaterial("ruby", 0xbb4a1d).setMagicNumber(2, 3).addItemParts(gem, tools).addTag("ore");
	public static BasicMaterial sapphire = new BasicMaterial("sapphire", 0x1b55b8).setMagicNumber(2, 3).addItemParts(gem, tools).addTag("ore");
	public static BasicMaterial olivine = new BasicMaterial("olivine", 0x08dd7b).setMagicNumber(3, 3).addItemParts(gem).addTag("ore");
	public static BasicMaterial redalloy = new BasicMaterial("redalloy", 0x000000).addItemParts(metal).addTag("ore");
	//advanced
	public static BasicMaterial tungsten = new BasicMaterial("tungsten", 0x181b07).addItemParts(metal);
	public static BasicMaterial tungstate = new BasicMaterial("tungstate", 0x181b07).addTag("ore");
	public static BasicMaterial titanium = new BasicMaterial("titanium", 0xc4b4ed).addItemParts(metal);
	public static BasicMaterial rutile = new BasicMaterial("rutile", 0xc4b4ed).addTag("ore");
	public static BasicMaterial sphalerite = new BasicMaterial("sphalerite", 0x181b07).addTag("ore");
	public static BasicMaterial zinc = new BasicMaterial("zinc", 0xbba69f).addItemParts(metal).addBlockParts(block);
	public static BasicMaterial brass = new BasicMaterial("brass", 0xdba31e).addItemParts(metal).addBlockParts(block);
	public static BasicMaterial aluminiumbrass = new BasicMaterial("aluminiumbrass", 0xdba31e).addItemParts(metal).addBlockParts(block);
	public static BasicMaterial cobalt = new BasicMaterial("cobalt", 0x505080).setMagicNumber(3, 5).addItemParts(metal, tools).addBlockParts(block);
	public static BasicMaterial wroughtiron = new BasicMaterial("wroughtiron", 0xceaa9f).addItemParts(metal).addBlockParts(block);
	public static BasicMaterial chromium = new BasicMaterial("chromium", 0xf4c4b5).addItemParts(metal).addBlockParts(block);
	public static BasicMaterial chromite= new BasicMaterial("chromite", 0xf4c4b5).addTag("ore");
	public static BasicMaterial tungstensteel = new BasicMaterial("tungstensteel", 0x274562).setMagicNumber(4, 4).addItemParts(metal, tools).addBlockParts(block);
	public static BasicMaterial osmium = new BasicMaterial("osmium", 0x93bbe8).setMagicNumber(3, 2).addItemParts(metal, tools).addBlockParts(block);
	public static BasicMaterial iridium = new BasicMaterial("iridium", 0xFFFFFF).addItemParts(metal, tools).addBlockParts(block);
	public static BasicMaterial magnesium = new BasicMaterial("magnesium", 0x000000).addItemParts(metal);
	public static BasicMaterial magnesite = new BasicMaterial("magnesite", 0x000000).addTag("ore");
	public static BasicMaterial cupronickel = new BasicMaterial("cupronickel", 0x000000).addItemParts(metal).addTag("ore");
	//nuclear
	public static BasicMaterial uranium = new BasicMaterial("uranium", 0x000000).addItemParts(metal).addTag("ore");
	public static BasicMaterial thorium = new BasicMaterial("thorium", 0x000000).addItemParts(metal);
	public static BasicMaterial plutonium = new BasicMaterial("plutonium", 0x000000).addItemParts(metal);
	//classic foundation
	public static BasicMaterial signalium = new BasicMaterial("signalium", 0x000000).addItemParts(metal);
	public static BasicMaterial lumium = new BasicMaterial("lumium", 0x000000).addItemParts(metal);
	public static BasicMaterial enderium = new BasicMaterial("enderium", 0x000000).addItemParts(metal);
	public static BasicMaterial pyrotheum = new BasicMaterial("pyrotheum", 0x000000).addItemParts(metal);
	public static BasicMaterial cryotheum = new BasicMaterial("cryotheum", 0x000000).addItemParts(metal);
	public static BasicMaterial aerotheum = new BasicMaterial("aerotheum", 0x000000).addItemParts(metal);
	public static BasicMaterial petrotheum = new BasicMaterial("petrotheum", 0x000000).addItemParts(metal);
	public static BasicMaterial fluxedelectrum = new BasicMaterial("fluxedelectrum", 0x000000).addItemParts(metal);
	public static BasicMaterial flux = new BasicMaterial("flux", 0x000000).addItemParts(metal);
	//classic io
	public static BasicMaterial electricalsteel = new BasicMaterial("electricalsteel", 0x000000).addItemParts(metal);
	public static BasicMaterial energetic = new BasicMaterial("energetic", 0x000000).addItemParts(metal);
	public static BasicMaterial vibrant = new BasicMaterial("vibrant", 0x000000).addItemParts(metal);
	public static BasicMaterial conductiveiron = new BasicMaterial("conductiveiron", 0x000000).addItemParts(metal);
	public static BasicMaterial pulsatingiron = new BasicMaterial("pulsatingiron", 0x000000).addItemParts(metal);
	public static BasicMaterial darksteel = new BasicMaterial("darksteel", 0x000000).addItemParts(metal);
	public static BasicMaterial soularium = new BasicMaterial("soularium", 0x000000).addItemParts(metal);
	public static BasicMaterial darksoularium = new BasicMaterial("darksoularium", 0x000000).addItemParts(metal);
    
    public static void registerMats(){
        registerMat(nickel);
		registerMat(garnierite);
		registerMat(tin);
		registerMat(cassiterite);
		registerMat(tungsten);
		registerMat(tungstate);
		registerMat(titanium);
		registerMat(rutile);
		registerMat(sphalerite);
		registerMat(ruby);
		registerMat(sapphire);
		registerMat(olivine);
		registerMat(redalloy);
		registerMat(emerald);
		registerMat(galena);
		registerMat(lead);
		registerMat(silver);
		registerMat(platinum);
		registerMat(sheldonite);
		registerMat(gold);
		registerMat(copper);
		registerMat(tetrahedrite);
		registerMat(iron);
		registerMat(pyrite);
		registerMat(bronze);
		registerMat(brass);
		registerMat(aluminiumbrass);
		registerMat(wroughtiron);
		registerMat(cobalt);
		registerMat(chromium);
		registerMat(chromite);
		registerMat(invar);
		registerMat(electrum);
		registerMat(aluminium);
		registerMat(bauxite);
		registerMat(steel);
		registerMat(tungstensteel);
		registerMat(zinc);
		registerMat(osmium);
		registerMat(iridium);
		registerMat(nikolite);
		registerMat(magnesium);
		registerMat(magnesite);
		registerMat(cupronickel);
		registerMat(uranium);
		registerMat(thorium);
		registerMat(plutonium);
		registerMat(signalium);
		registerMat(lumium);
		registerMat(enderium );
		registerMat(pyrotheum);
		registerMat(cryotheum);
		registerMat(aerotheum);
		registerMat(petrotheum);
		registerMat(fluxedelectrum);
		registerMat(flux);
		registerMat(electricalsteel);
		registerMat(energetic);
		registerMat(vibrant);
		registerMat(conductiveiron);
		registerMat(pulsatingiron);
		registerMat(darksteel);
		registerMat(soularium);
		registerMat(darksoularium);

		BasicJson.addTags(pickblocks, "minecraft:blocks/mineable/pickaxe");
		BasicJson.addTags(pickblocks, "minecraft:blocks/needs_stone_tool");
		if (Basic.bclibCompat) BasicJson.addTags(shears, "fabric:items/shears");
		if (Basic.moreTagsCompat) BasicJson.addTags(shears, "c:items/shears");
		BasicJson.addTags(tier3hull, "c:items/machine_frames/basic");
		BasicJson.addTags(tier4hull, "c:items/machine_frames/good");
    }

	public static void oregen(){
		//OreApi.registerOre(new Identifier(Basic.modid, "garnierite_overworld"), BiomeSelection.PLAINS, StoneSelection.STONE, garnierite.blockPartsBlocks.get("ore_stone"), 8, 2, 0, 50);
		//OreApi.registerOre(new Identifier(Basic.modid, "garnierite_extreme"), BiomeSelection.EXTREME, StoneSelection.STONE, garnierite.blockPartsBlocks.get("ore_stone"), 8, 2, 0, 50);
		OreApi.registerGeneration("garnierite", BiomeSelection.NETHER, -12, 75, 20, 8, 0.0f, OreApi.oreTypes.get("garnierite"));
		//OreApi.registerOre(new Identifier(Basic.modid, "garnierite_low"), BiomeSelection.EXTREME, StoneSelection.DEEPSLATE, garnierite.blockPartsBlocks.get("ore_deepslate"), 8, 1, -25, 0);
		//OreApi.registerOre(new Identifier(Basic.modid, "garnierite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, garnierite.blockPartsBlocks.get("ore_netherrack"), 8, 3, 27, 91);
		//OreApi.registerOre(new Identifier(Basic.modid, "cassiterite_icy"), BiomeSelection.ICY, StoneSelection.STONE, cassiterite.blockPartsBlocks.get("ore_stone"), 30, 2, 30, 80);
		//OreApi.registerOre(new Identifier(Basic.modid, "cassiterite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, cassiterite.blockPartsBlocks.get("ore_netherrack"), 40, 1, 70, 120);
		OreApi.registerGeneration("cassiterite", BiomeSelection.NETHER.or(BiomeSelection.ICY), 30, 120, 60, 60, 0.0f, OreApi.oreTypes.get("cassiterite"));
		//OreApi.registerOre(new Identifier(Basic.modid, "rutile_ocean"), BiomeSelection.OCEAN, StoneSelection.STONE, rutile.blockPartsBlocks.get("ore_stone"), 5, 2, 0, 32);
		//OreApi.registerOre(new Identifier(Basic.modid, "rutile_end"), BiomeSelection.END, StoneSelection.END_STONE, rutile.blockPartsBlocks.get("ore_endstone"), 6, 3, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "uranium_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, uranium.blockPartsBlocks.get("ore_netherrack"), 7, 3, 0, 128);
		//OreApi.registerOre(new Identifier(Basic.modid, "bauxite_overworld"), BiomeSelection.PLAINS, StoneSelection.STONE, bauxite.blockPartsBlocks.get("ore_stone"), 20, 3, 64, 128);
		//OreApi.registerOre(new Identifier(Basic.modid, "bauxite_end"), BiomeSelection.END, StoneSelection.END_STONE, bauxite.blockPartsBlocks.get("ore_endstone"), 10, 6, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "sheldonite_mushroom"), BiomeSelection.MUSHROOM, StoneSelection.STONE, sheldonite.blockPartsBlocks.get("ore_stone"), 7, 2, 0, 20);
		//OreApi.registerOre(new Identifier(Basic.modid, "sheldonite_mushroom_low"), BiomeSelection.MUSHROOM, StoneSelection.DEEPSLATE, sheldonite.blockPartsBlocks.get("ore_deepslate"), 7, 1, -10, 0);
		//OreApi.registerOre(new Identifier(Basic.modid, "sheldonite_rare"), BiomeSelection.OVERWORLD, StoneSelection.STONE, sheldonite.blockPartsBlocks.get("ore_stone"), 9, 6, 0, 40);
		//OreApi.registerOre(new Identifier(Basic.modid, "sheldonite_rare_low"), BiomeSelection.OVERWORLD, StoneSelection.DEEPSLATE, sheldonite.blockPartsBlocks.get("ore_deepslate"), 9, 3, -20, 0);
		//OreApi.registerOre(new Identifier(Basic.modid, "sheldonite_end"), BiomeSelection.END, StoneSelection.END_STONE, sheldonite.blockPartsBlocks.get("ore_endstone"), 9, 7, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "chromite_desert"), BiomeSelection.DESERT, StoneSelection.STONE, chromite.blockPartsBlocks.get("ore_stone"), 20, 3, 64, 128);
		//OreApi.registerOre(new Identifier(Basic.modid, "chromite_ocean"), BiomeSelection.OCEAN, StoneSelection.STONE, chromite.blockPartsBlocks.get("ore_stone"), 9, 6, -12, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "chromite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, chromite.blockPartsBlocks.get("ore_netherrack"), 9, 10, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "pyrite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, pyrite.blockPartsBlocks.get("ore_netherrack"), 9, 10, 10, 112);
		////OreApi.registerOre(new Identifier(Basic.modid, "sodalite_end"), BiomeSelection.END, StoneSelection.END_STONE, sodalite.blockPartsBlocks.get("ore_endstone"), 9, 20, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "olivine_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, olivine.blockPartsBlocks.get("ore_netherrack"), 9, 5, 10, 50);
		//OreApi.registerOre(new Identifier(Basic.modid, "olivine_end"), BiomeSelection.END, StoneSelection.END_STONE, olivine.blockPartsBlocks.get("ore_endstone"), 9, 5, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "ruby_desert"), BiomeSelection.DESERT, StoneSelection.STONE, ruby.blockPartsBlocks.get("ore_stone"), 6, 5, 0, 40);
		//OreApi.registerOre(new Identifier(Basic.modid, "ruby_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, ruby.blockPartsBlocks.get("ore_netherrack"), 6, 8, 32, 96);
		//OreApi.registerOre(new Identifier(Basic.modid, "sapphire_overworld"), BiomeSelection.OCEAN, StoneSelection.STONE, sapphire.blockPartsBlocks.get("ore_stone"), 6, 5, 20, 60);
		//OreApi.registerOre(new Identifier(Basic.modid, "sapphire_end"), BiomeSelection.END, StoneSelection.END_STONE, sapphire.blockPartsBlocks.get("ore_endstone"), 6, 8, 50, 10);
		//OreApi.registerOre(new Identifier(Basic.modid, "nikolite_plains"), BiomeSelection.PLAINS, StoneSelection.STONE, nikolite.blockPartsBlocks.get("ore_stone"), 15, 7, 0, 100);
		//OreApi.registerOre(new Identifier(Basic.modid, "nikolite_ocean"), BiomeSelection.OCEAN, StoneSelection.STONE, nikolite.blockPartsBlocks.get("ore_stone"), 15, 7, 0, 100);
		//OreApi.registerOre(new Identifier(Basic.modid, "nikolite_plains_low"), BiomeSelection.PLAINS, StoneSelection.STONE, nikolite.blockPartsBlocks.get("ore_stone"), 18, 2, -64, 0);
		//OreApi.registerOre(new Identifier(Basic.modid, "nikolite_ocean_low"), BiomeSelection.OCEAN, StoneSelection.STONE, nikolite.blockPartsBlocks.get("ore_stone"), 18, 2, -64, 0);
		//OreApi.registerOre(new Identifier(Basic.modid, "nikolite_end"), BiomeSelection.END, StoneSelection.END_STONE, nikolite.blockPartsBlocks.get("ore_endstone"), 18, 2, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "magnesite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, magnesite.blockPartsBlocks.get("ore_netherrack"), 9, 10, 10, 90);
		//OreApi.registerOre(new Identifier(Basic.modid, "galena_overworld"), BiomeSelection.OVERWORLD, StoneSelection.STONE, galena.blockPartsBlocks.get("ore_stone"), 6, 7, 0, 70);
		//OreApi.registerOre(new Identifier(Basic.modid, "galena_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, galena.blockPartsBlocks.get("ore_netherrack"), 6, 5, 0, 70);
		//OreApi.registerOre(new Identifier(Basic.modid, "tungstate_end"), BiomeSelection.END, StoneSelection.END_STONE, tungstate.blockPartsBlocks.get("ore_endstone"), 2, 2, 0, 64);
		//OreApi.registerOre(new Identifier(Basic.modid, "tetrahedrite_jungle"), BiomeSelection.JUNGLE, StoneSelection.STONE, tetrahedrite.blockPartsBlocks.get("ore_stone"), 20, 6, 32, 96);
		//OreApi.registerOre(new Identifier(Basic.modid, "tetrahedrite_mushroom"), BiomeSelection.MUSHROOM, StoneSelection.STONE, tetrahedrite.blockPartsBlocks.get("ore_stone"), 20, 6, 32, 96);
		//OreApi.registerOre(new Identifier(Basic.modid, "tetrahedrite_extreme"), BiomeSelection.EXTREME, StoneSelection.STONE, tetrahedrite.blockPartsBlocks.get("ore_stone"), 20, 6, 32, 96);
		//OreApi.registerOre(new Identifier(Basic.modid, "tetrahedrite_nether"), BiomeSelection.NETHER, StoneSelection.NETHERRACK, tetrahedrite.blockPartsBlocks.get("ore_netherrack"), 20, 4, 32, 96);
		//OreApi.registerOre(new Identifier(Basic.modid, "tetrahedrite_end"), BiomeSelection.END, StoneSelection.END_STONE, tetrahedrite.blockPartsBlocks.get("ore_endstone"), 20, 6, 32, 96);
		//OreApi.registerOre();
		OreApi.registerOreApi();
	}

    public static void registerMat(BasicMaterial Mat){
		for (Map.Entry<String, Item> set : Mat.itemParts.entrySet()) {
			Basic.registerItem(set.getValue(), set.getKey()+"_"+Mat.name);
			switch (set.getKey()) {
				case "bow": BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/bow", set.getKey()); break;
				case "shield": BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "fabricshieldlib:item/fabric_shield", set.getKey()); break;
				case "sword": 
				BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/generated", "teknologi:item/stick", set.getKey());
				if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> tintIndex == 1 ? Mat.color : -1, set.getValue());
				break;
				case "pickaxe": BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/generated", "teknologi:item/stick", set.getKey()); break;
				default: BasicJson.registerItemModel(set.getKey()+"_"+Mat.name, "item/generated", set.getKey()); 
				if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT) ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Mat.color, set.getValue());
				break;
			}
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+Mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+Mat.name), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
		}

		for (Map.Entry<String, Item> set : Mat.blockPartsItems.entrySet()) {
			Basic.registerBlock(set.getValue(), Mat.blockPartsBlocks.get(set.getKey()), set.getKey()+"_"+Mat.name);

			if (set.getKey() == "ore_stone") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/stone", "ore");
			else if (set.getKey() == "ore_gravel") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/gravel", "ore");
			else if (set.getKey() == "ore_sandstone") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/sandstone", "ore");
			else if (set.getKey() == "ore_netherrack") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/netherrack", "ore");
			else if (set.getKey() == "ore_endstone") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/end_stone", "ore");
			else if (set.getKey() == "ore_deepslate") BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, Basic.modid+":block/ore", "minecraft:block/deepslate", "ore");
			else
				BasicJson.registerBlockModel(set.getKey()+"_"+Mat.name, "block/leaves", set.getKey());
			if (FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT){
				ColorProviderRegistry.BLOCK.register((state, view, pos, tintIndex) -> Mat.color, Mat.blockPartsBlocks.get(set.getKey()));
				BlockRenderLayerMap.INSTANCE.putBlock(Mat.blockPartsBlocks.get(set.getKey()), RenderLayer.getCutout());
				ColorProviderRegistry.ITEM.register((stack, tintIndex) -> Mat.color, set.getValue());
			}
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+Mat.name+"_"+set.getKey()+"s"), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addTag(new Identifier("c:items/"+set.getKey()+"s/"+Mat.name), new JTag().add(new Identifier(Basic.modid+":"+set.getKey()+"_"+Mat.name)));
			Basic.RESOURCE_PACK.addLootTable(new Identifier(Basic.modid+":"+"blocks/"+set.getKey()+"_"+Mat.name), JLootTable.loot("minecraft:block").pool(new JPool().rolls(1).entry(new JEntry().type("minecraft:item").name(Basic.modid+":"+set.getKey()+"_"+Mat.name).condition("minecraft:survives_explosion"))));
			pickblocks.add(set.getKey()+"_"+Mat.name);
		}

		if(Mat.tags.contains("ore")){
			OreApi.registerOre(Mat.name, Mat.itemParts.get("raw_ore"), Mat.color);
		}

		if(Mat.itemParts.containsKey("ingot")){
			if (Mat.itemParts.containsKey("nugget")){
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "ingot_to_nugget"+"/"+Mat.name), JRecipe.shapeless(JIngredients.ingredients().add(JIngredient.ingredient().item(Mat.itemParts.get("ingot"))), JResult.itemStack(Mat.itemParts.get("nugget"), 9)));
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "nugget_to_ingot"+"/"+Mat.name), JRecipe.shaped(JPattern.pattern("NNN", "NNN", "NNN"), JKeys.keys().key("N", JIngredient.ingredient().item(Mat.itemParts.get("nugget"))), JResult.itemStack(Mat.itemParts.get("ingot"), 1)));
			}

			if (Mat.blockPartsItems.containsKey("ore")){
				Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, "ore_to_ingot"+"/"+Mat.name), JRecipe.smelting(JIngredient.ingredient().item(Mat.blockPartsItems.get("ore")), JResult.itemStack(Mat.itemParts.get("ingot"), 1)));
			}

			if (Mat.itemParts.containsKey("hammer")) BasicJson.createToolRecipe(Mat.name, "hammer", BasicJson.hammerShape, "large_plates", Mat.itemParts.get("hammer"), Items.STICK);
			if (Mat.itemParts.containsKey("excavator")) BasicJson.createToolRecipe(Mat.name, "excavator", BasicJson.excavatorShape, "large_plates", Mat.itemParts.get("excavator"), Items.STICK);
			if (Mat.itemParts.containsKey("pickaxe")) BasicJson.createToolRecipe(Mat.name, "pickaxe", BasicJson.pickaxeShape, "ingots", "plates", Mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if(Mat.itemParts.containsKey("gem")){
			if (Mat.itemParts.containsKey("hammer")) BasicJson.createToolRecipe(Mat.name, "hammer", BasicJson.hammerShape, "blocks", Mat.itemParts.get("hammer"), Items.STICK);
			if (Mat.itemParts.containsKey("excavator")) BasicJson.createToolRecipe(Mat.name, "excavator", BasicJson.excavatorShape, "blocks", Mat.itemParts.get("excavator"), Items.STICK);
			if (Mat.itemParts.containsKey("pickaxe")) BasicJson.createToolRecipe(Mat.name, "pickaxe", BasicJson.pickaxeShape, "gems", "gems", Mat.itemParts.get("pickaxe"), Items.STICK);
		}

		if (Mat.itemParts.containsKey("shear")){
			shears.add("shear_"+Mat.name);
		}

		if (Mat.itemParts.containsKey("hull")){
			if (Mat.tags.contains("tier3")) tier3hull.add("hull_"+Mat.name);
			if (Mat.tags.contains("tier4")) tier4hull.add("hull_"+Mat.name);
		}
	}
}