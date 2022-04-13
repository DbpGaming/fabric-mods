package net.dbp.basic_ores.recipe_api;

import net.minecraft.inventory.CraftingInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.ShapelessRecipe;
import net.minecraft.util.collection.DefaultedList;

public class AdvancedRecipe extends ShapelessRecipe {

    public AdvancedRecipe(ShapelessRecipe original) {
        super(
                original.getId(),
                original.getGroup(),
                original.getOutput(),
                original.getIngredients()
        );
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return AdvancedRecipeSerializer.INSTANCE;
    }

    @Override
    public DefaultedList<ItemStack> getRemainder(CraftingInventory inventory) {
        DefaultedList<ItemStack> defaultedList = DefaultedList.ofSize(inventory.size(), ItemStack.EMPTY);
        for (int i = 0; i < defaultedList.size(); ++i) {
            ItemStack stack = inventory.getStack(i);
            if (stack.getItem().isDamageable()) {
                if (stack.getDamage() + 1 < stack.getMaxDamage()) {
                    stack = stack.copy();
                    stack.setDamage(stack.getDamage() + 1);
                    defaultedList.set(i, stack);
                }
            } else if (stack.getItem().hasRecipeRemainder()) {
                defaultedList.set(i, new ItemStack(stack.getItem().getRecipeRemainder()));
            }
        }

        return defaultedList;
    }
}
