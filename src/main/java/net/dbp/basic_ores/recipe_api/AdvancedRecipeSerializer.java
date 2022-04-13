package net.dbp.basic_ores.recipe_api;

import com.google.gson.JsonObject;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.Identifier;

public class AdvancedRecipeSerializer extends ShapelessRecipe.Serializer {
    public static final AdvancedRecipeSerializer INSTANCE = new AdvancedRecipeSerializer();

    @Override
    public ShapelessRecipe read(Identifier identifier, JsonObject jsonObject) {
        return new AdvancedRecipe(super.read(identifier, jsonObject));
    }

    @Override
    public ShapelessRecipe read(Identifier identifier, PacketByteBuf packetByteBuf) {
        return new AdvancedRecipe(super.read(identifier, packetByteBuf));
    }
}
