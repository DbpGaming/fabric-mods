package net.dbp.basic_ores.machine_api;

import net.dbp.basic_ores.Basic;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.screen.*;

public class TestFurnaceScreenHandler extends AbstractFurnaceScreenHandler {
    public TestFurnaceScreenHandler(int i, PlayerInventory playerInventory) {
        super(Basic.TEST_FURNACE_SCREEN_HANDLER, Basic.TEST_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory);
    }
 
    public TestFurnaceScreenHandler(int i, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
        super(Basic.TEST_FURNACE_SCREEN_HANDLER, Basic.TEST_RECIPE_TYPE, RecipeBookCategory.FURNACE, i, playerInventory, inventory, propertyDelegate);
    }
}