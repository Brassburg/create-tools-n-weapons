package net.brassburg.keybinds;

import net.brassburg.CreateToolsNWeaponsMod;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class KeybindHandler {
    private static final TagKey<Item> MOD_ITEMS_TAG = TagKey.of(Registries.ITEM.getKey(),
            new Identifier(CreateToolsNWeaponsMod.MOD_ID, "ctnw_items"));
    private static KeyBinding modeKey;

    public static void register() {
        modeKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.create_tools.break_mode",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_V,
                "category.create_tools"
        ));

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;
            ItemStack heldItem = client.player.getMainHandStack();

            // Automatically disable BreakMode if the item is not from the mod tag
            if (!heldItem.isIn(MOD_ITEMS_TAG)) {
                if (CreateToolsNWeaponsMod.isBreakModeActive) {
                    CreateToolsNWeaponsMod.isBreakModeActive = false;
//                    client.player.sendMessage(Text.of("BreakMode OFF"), true);
                }
                return;
            }

            if (modeKey.wasPressed()) {
                CreateToolsNWeaponsMod.isBreakModeActive = !CreateToolsNWeaponsMod.isBreakModeActive;
//                client.player.sendMessage(Text.of(
//                        CreateToolsNWeaponsMod.isBreakModeActive ? "BreakMode ON" : "BreakMode OFF"
//                ), true);
            }
        });
    }
}
