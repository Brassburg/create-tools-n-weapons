package net.brassburg.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.brassburg.CreateToolsNWeaponsMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;

public class HudOverlayHandler implements HudRenderCallback {
    private static final Identifier ICON = new Identifier(CreateToolsNWeaponsMod.MOD_ID, "textures/gui/break_mode.png");

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        if (CreateToolsNWeaponsMod.isBreakModeActive) {
            MinecraftClient client = MinecraftClient.getInstance();
            if (client.player == null) return;

            int screenWidth = client.getWindow().getScaledWidth();
            int screenHeight = client.getWindow().getScaledHeight();

            int x = screenWidth - 25;
            int y = screenHeight - 25;

            RenderSystem.enableBlend();
            drawContext.drawTexture(ICON, x, y, 0, 0, 20, 20, 20, 20);
            RenderSystem.disableBlend();
        }
    }
}
