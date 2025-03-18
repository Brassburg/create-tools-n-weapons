
/*
 *	MCreator note: This file will be REGENERATED on each build.
 */
package net.brassburg.init;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.resources.ResourceKey;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.Registry;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

import net.brassburg.CreateToolsNWeaponsMod;

public class CreateToolsNWeaponsModTabs {
	public static ResourceKey<CreativeModeTab> TAB_CREATE_TOOLS_N_WEAPONS = ResourceKey.create(Registries.CREATIVE_MODE_TAB, new ResourceLocation(CreateToolsNWeaponsMod.MODID, "create_tools_n_weapons"));

	public static void load() {
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, TAB_CREATE_TOOLS_N_WEAPONS,
				FabricItemGroup.builder().title(Component.translatable("item_group." + CreateToolsNWeaponsMod.MODID + ".create_tools_n_weapons")).icon(() -> new ItemStack(CreateToolsNWeaponsModItems.GEAR_PICKAXE)).build());
	}
}
