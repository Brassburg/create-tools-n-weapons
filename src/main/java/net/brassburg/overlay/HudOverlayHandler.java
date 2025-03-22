package net.brassburg.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import net.brassburg.CreateToolsNWeaponsMod;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class HudOverlayHandler implements HudRenderCallback {
    private static final Identifier ICON_ON = new Identifier(CreateToolsNWeaponsMod.MOD_ID, "textures/gui/break_mode_on.png");
    private static final Identifier ICON_OFF = new Identifier(CreateToolsNWeaponsMod.MOD_ID, "textures/gui/break_mode_off.png");

    private static final TagKey<net.minecraft.item.Item> MOD_ITEMS_TAG = TagKey.of(Registries.ITEM.getKey(),
            new Identifier(CreateToolsNWeaponsMod.MOD_ID, "ctnw_items"));

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client.player == null) return;

        ItemStack heldItem = client.player.getMainHandStack();
        if (!heldItem.isIn(MOD_ITEMS_TAG)) return;

        Identifier icon = CreateToolsNWeaponsMod.isBreakModeActive ? ICON_ON : ICON_OFF;

        int screenWidth = client.getWindow().getScaledWidth();
        int screenHeight = client.getWindow().getScaledHeight();

        int x = screenWidth - 20;
        int y = screenHeight - 20;

        RenderSystem.enableBlend();
        drawContext.drawTexture(icon, x, y, 0, 0, 15, 15, 15, 15);
        RenderSystem.disableBlend();
    }
}
