package net.brassburg;

import net.brassburg.config.ConfigManager;
import net.brassburg.event.ModEvents;
import net.brassburg.init.ModItems;
import net.brassburg.init.ModTabs;
import net.brassburg.keybinds.KeybindHandler;
import net.brassburg.overlay.HudOverlayHandler;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CreateToolsNWeaponsMod implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MOD_ID = "create_tools_n_weapons";

    public static boolean isBreakModeActive = false;

    @Override
    public void onInitialize() {
        LOGGER.info("Initializing CreateToolsNWeaponsMod");

        ModTabs.register();
        ModItems.register();
        ModEvents.register();
        ConfigManager.register();
        KeybindHandler.register();
        HudRenderCallback.EVENT.register(new HudOverlayHandler());
    }
}
