package net.dbp.basic_ores.machine_api;

import net.dbp.basic_ores.Basic;
import net.minecraft.item.*;
import net.minecraft.recipe.*;
import net.minecraft.util.Identifier;

public class TestRecipe extends AbstractCookingRecipe {
    //Same for the BlockEntity, we don't need some of the parameters in the constructor since we already now the type
    public TestRecipe(Identifier id, String group, Ingredient input, ItemStack output, float experience, int cookTime) {
        super(Basic.TEST_RECIPE_TYPE, id, group, input, output, experience, cookTime);
    }
 
    @Override
    public ItemStack createIcon() {
        return new ItemStack(Items.BLACKSTONE);
    }
 
    @Override
    public RecipeSerializer<?> getSerializer() {
        return Basic.TEST_RECIPE_SERIALIZER;
    }
}