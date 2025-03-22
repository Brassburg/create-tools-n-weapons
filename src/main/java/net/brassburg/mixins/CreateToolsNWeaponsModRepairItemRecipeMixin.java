package net.brassburg.mixins;

import com.google.common.collect.Lists;
import net.minecraft.inventory.RecipeInputInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.RepairItemRecipe;
import net.minecraft.registry.DynamicRegistryManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.ArrayList;

@Mixin(RepairItemRecipe.class)
public abstract class CreateToolsNWeaponsModRepairItemRecipeMixin {
    @Inject(method = "craft", at = @At("HEAD"), cancellable = true)
    public void craft(RecipeInputInventory craftingInventory, DynamicRegistryManager registryManager,
                      CallbackInfoReturnable<ItemStack> cir) {

        ArrayList<ItemStack> list = Lists.newArrayList();
        for (int i = 0; i < craftingInventory.size(); ++i) {
            ItemStack itemStack = craftingInventory.getStack(i);
            if (itemStack.isEmpty())
                continue;
            list.add(itemStack);
        }
    }
}
