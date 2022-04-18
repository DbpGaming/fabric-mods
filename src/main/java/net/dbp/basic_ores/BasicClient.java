package net.dbp.basic_ores;

import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.dbp.basic_ores.machine_api.TestFurnaceScreen;
import net.fabricmc.api.*;

@Environment(EnvType.CLIENT)
public class BasicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(Basic.TEST_FURNACE_SCREEN_HANDLER, TestFurnaceScreen::new);
    }
}