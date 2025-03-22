package net.brassburg.init;

import net.brassburg.CreateToolsNWeaponsMod;
import net.brassburg.item.*;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static Item GEAR_AXE;
    public static Item GEAR_PICKAXE;
    public static Item GEAR_SWORD;
    public static Item GEAR_SHOVEL;
    public static Item GEAR_HOE;

    public static void register() {
        GEAR_AXE = registerItem("gear_axe", new GearAxeItem());
        GEAR_PICKAXE = registerItem("gear_pickaxe", new GearPickaxeItem());
        GEAR_SWORD = registerItem("gear_sword", new GearSwordItem());
        GEAR_SHOVEL = registerItem("gear_shovel", new GearShovelItem());
        GEAR_HOE = registerItem("gear_hoe", new GearHoeItem());
    }

    private static Item registerItem(String registryName, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(CreateToolsNWeaponsMod.MOD_ID, registryName), item);
    }

}
