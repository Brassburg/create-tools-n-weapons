package net.brassburg.init;

import net.brassburg.CreateToolsNWeaponsMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModTabs {
    public static final RegistryKey<ItemGroup> TAB_CREATE_TOOLS_N_WEAPONS = RegistryKey.of(Registries.ITEM_GROUP.getKey(),
            new Identifier(CreateToolsNWeaponsMod.MOD_ID, "create_tools_n_weapons"));

    public static void register() {
        Registry.register(Registries.ITEM_GROUP, TAB_CREATE_TOOLS_N_WEAPONS,
                FabricItemGroup.builder()
                        .displayName(Text.translatable("item_group." + CreateToolsNWeaponsMod.MOD_ID + ".ctnw"))
                        .icon(() -> new ItemStack(ModItems.GEAR_PICKAXE))
                        .build()
        );
    }
}
