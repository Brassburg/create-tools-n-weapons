/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.brassburg.init;

import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;

import net.brassburg.item.GearSwordItem;
import net.brassburg.item.GearShovelItem;
import net.brassburg.item.GearPickaxeItem;
import net.brassburg.item.GearHoeItem;
import net.brassburg.item.GearAxeItem;
import net.brassburg.CreateToolsNWeaponsMod;

public class CreateToolsNWeaponsModItems {
	public static Item GEAR_AXE;
	public static Item GEAR_PICKAXE;
	public static Item GEAR_SWORD;
	public static Item GEAR_SHOVEL;
	public static Item GEAR_HOE;

	public static void load() {
		GEAR_AXE = register("gear_axe", new GearAxeItem());
		GEAR_PICKAXE = register("gear_pickaxe", new GearPickaxeItem());
		GEAR_SWORD = register("gear_sword", new GearSwordItem());
		GEAR_SHOVEL = register("gear_shovel", new GearShovelItem());
		GEAR_HOE = register("gear_hoe", new GearHoeItem());
	}

	public static void clientLoad() {
	}

	private static Item register(String registryName, Item item) {
		return Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(CreateToolsNWeaponsMod.MODID, registryName), item);
	}

	private static void registerBlockingProperty(Item item) {
		ItemProperties.register(item, new ResourceLocation("blocking"), (ClampedItemPropertyFunction) ItemProperties.getProperty(Items.SHIELD, new ResourceLocation("blocking")));
	}
}
