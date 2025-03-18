
package net.brassburg.item;

import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.network.chat.Component;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;

import net.brassburg.init.CreateToolsNWeaponsModTabs;

import java.util.List;

public class GearPickaxeItem extends PickaxeItem {
	public GearPickaxeItem() {
		super(new Tier() {
			public int getUses() {
				return 2500;
			}

			public float getSpeed() {
				return 12f;
			}

			public float getAttackDamageBonus() {
				return 5f;
			}

			public int getLevel() {
				return 5;
			}

			public int getEnchantmentValue() {
				return 22;
			}

			public Ingredient getRepairIngredient() {
				return Ingredient.of();
			}
		}, 1, -2.8f, new Item.Properties().fireResistant());
		ItemGroupEvents.modifyEntriesEvent(CreateToolsNWeaponsModTabs.TAB_CREATE_TOOLS_N_WEAPONS).register(content -> content.accept(this));
	}

	@Override
	public void appendHoverText(ItemStack itemstack, Level world, List<Component> list, TooltipFlag flag) {
		super.appendHoverText(itemstack, world, list, flag);
		list.add(Component.literal("This pick is special"));
	}
}
