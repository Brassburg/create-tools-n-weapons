/*
 *	MCreator note:
 *
 *	If you lock base mod element files, you can edit this file and the proxy files
 *	and they won't get overwritten. If you change your mod package or modid, you
 *	need to apply these changes to this file MANUALLY.
 *
 *
 *	If you do not lock base mod element files in Workspace settings, this file
 *	will be REGENERATED on each build.
 *
 */
package net.brassburg;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

import net.fabricmc.api.ModInitializer;

import net.brassburg.init.CreateToolsNWeaponsModTabs;
import net.brassburg.init.CreateToolsNWeaponsModItems;

public class CreateToolsNWeaponsMod implements ModInitializer {
	public static final Logger LOGGER = LogManager.getLogger();
	public static final String MODID = "create_tools_n_weapons";

	@Override
	public void onInitialize() {
		LOGGER.info("Initializing CreateToolsNWeaponsMod");

		CreateToolsNWeaponsModTabs.load();

		CreateToolsNWeaponsModItems.load();

	}
}
