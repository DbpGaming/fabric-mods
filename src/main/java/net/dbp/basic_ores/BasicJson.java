package net.dbp.basic_ores;

import net.devtech.arrp.json.tags.*;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import java.util.HashSet;
import net.devtech.arrp.json.recipe.*;
import net.devtech.arrp.api.RRPCallback;
import net.devtech.arrp.json.blockstate.*;
import net.devtech.arrp.json.models.*;

public class BasicJson {
    public static String[] hammerShape = {" M ", " HM", "H  "};
	public static String[] excavatorShape = {" M  ", "MHM", " H "};
	public static String[] pickaxeShape = {"PMM", " H ", " H "};
    
    public static void createToolRecipe(String name, String recipeName, String[] shape, String mat, String mat2, Item item, Item handle){
		Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, recipeName+name), 
		JRecipe.shaped(JPattern.pattern(shape[0], shape[1], shape[2]), JKeys.keys()
			.key("M", JIngredient.ingredient().tag("c:"+mat+"/"+name))
			.key("P", JIngredient.ingredient().tag("c:"+mat2+"/"+name))
			.key("H", JIngredient.ingredient().item(Items.STICK)),
			JResult.itemStack(item, 1)));
	}

	public static void createToolRecipe(String name, String recipeName, String[] shape, String mat, Item item, Item handle){
		Basic.RESOURCE_PACK.addRecipe(new Identifier(Basic.modid, recipeName+name), 
		JRecipe.shaped(JPattern.pattern(shape[0], shape[1], shape[2]), JKeys.keys()
			.key("M", JIngredient.ingredient().tag("c:"+mat+"/"+name))
			.key("H", JIngredient.ingredient().item(Items.STICK)),
			JResult.itemStack(item, 1)));
	}

	public static void addTags(HashSet<String> hashset, String namespace){
		JTag jtag = new JTag();
		for (String string : hashset){
			jtag.add(new Identifier(Basic.modid+":"+string));
		}

		Basic.RESOURCE_PACK.addTag(new Identifier(namespace), jtag);
	}

    public static void registerItemModel(String modelName, String modelBase, String textureName){
		Basic.RESOURCE_PACK.addModel(JModel.model(modelBase).textures(new JTextures().layer0(Basic.modid+":item/"+textureName)), new Identifier(Basic.modid, "item/"+modelName));
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(Basic.RESOURCE_PACK));
	}


    public static void registerItemModel(String modelName, String modelBase, String textureBase, String textureName){
		Basic.RESOURCE_PACK.addModel(JModel.model(modelBase).textures(new JTextures().layer0(textureBase).layer1(Basic.modid+":item/"+textureName)), new Identifier(Basic.modid, "item/"+modelName));
		RRPCallback.BEFORE_VANILLA.register(a -> a.add(Basic.RESOURCE_PACK));
	}

	public static void registerBlockModel(String modelName, String modelBase, String textureName){
		Basic.RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(new Identifier(Basic.modid+":block/"+modelName)))), new Identifier(Basic.modid, modelName));
		Basic.RESOURCE_PACK.addModel(JModel.model().parent(modelBase).textures(new JTextures().var("all",Basic.modid+":block/"+textureName)), new Identifier(Basic.modid, "block/"+modelName));
		Basic.RESOURCE_PACK.addModel(JModel.model().parent(Basic.modid+":block/"+modelName), new Identifier(Basic.modid, "item/"+modelName));
	}

	public static void registerBlockModel(String modelName, String modelBase, String textureBase, String textureName){
		Basic.RESOURCE_PACK.addBlockState(new JState().add(new JVariant().put("", new JBlockModel(new Identifier(Basic.modid+":block/"+modelName)))), new Identifier(Basic.modid, modelName));
		Basic.RESOURCE_PACK.addModel(JModel.model().parent(modelBase).textures(new JTextures().layer0(textureBase).layer1(Basic.modid+":block/"+textureName)), new Identifier(Basic.modid, "block/"+modelName));
		Basic.RESOURCE_PACK.addModel(JModel.model().parent(Basic.modid+":block/"+modelName), new Identifier(Basic.modid, "item/"+modelName));
	}
}