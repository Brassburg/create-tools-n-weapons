package net.brassburg.item;

import net.brassburg.init.ModTabs;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import java.util.List;

public class GearShovelItem extends ShovelItem {
    public GearShovelItem() {
        super(new ToolMaterial() {
            @Override
            public int getDurability() {
                return 2500;
            }

            @Override
            public float getMiningSpeedMultiplier() {
                return 14f;
            }

            @Override
            public float getAttackDamage() {
                return 2f;
            }

            @Override
            public int getMiningLevel() {
                return 0;
            }

            @Override
            public int getEnchantability() {
                return 22;
            }

            @Override
            public Ingredient getRepairIngredient() {
                return Ingredient.empty();
            }
        }, 1, -2.8f, new Item.Settings().fireproof());

        ItemGroupEvents.modifyEntriesEvent(ModTabs.TAB_CREATE_TOOLS_N_WEAPONS)
                .register(content -> content.add(this));
    }

    @Override
    public void appendTooltip(ItemStack itemstack, World world, List<Text> list, TooltipContext context) {
        super.appendTooltip(itemstack, world, list, context);
    }
}