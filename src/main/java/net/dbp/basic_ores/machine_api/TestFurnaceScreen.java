package net.dbp.basic_ores.machine_api;

import net.fabricmc.api.*;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.FurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)
public class TestFurnaceScreen extends AbstractFurnaceScreen<TestFurnaceScreenHandler> {
    //You can replace the background with whatever you like, just remember there will always be the recipe book button
    private static final Identifier BACKGROUND = new Identifier("textures/gui/container/furnace.png");
 
    public TestFurnaceScreen(TestFurnaceScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, new FurnaceRecipeBookScreen(), inventory, title, BACKGROUND);
    }
}