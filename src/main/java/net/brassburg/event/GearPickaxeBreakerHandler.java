package net.brassburg.event;

import net.brassburg.CreateToolsNWeaponsMod;
import net.brassburg.config.ConfigManager;
import net.brassburg.init.ModItems;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;

import java.util.*;

public class GearPickaxeBreakerHandler {
    static final TagKey<Block> ORES_TAG =
            TagKey.of(RegistryKeys.BLOCK, new Identifier(CreateToolsNWeaponsMod.MOD_ID, "ores"));

    public static void register() {

        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state,
                                                blockEntity) -> {
            if (world.isClient) return true; // Server logic only

            ItemStack heldItem = player.getMainHandStack();
            if (!isValidTool(heldItem)) return true;

            if (!CreateToolsNWeaponsMod.isBreakModeActive) return true;

            Set<BlockPos> visited = new HashSet<>();
            breakOres((ServerWorld) world, pos, state.getBlock(), visited,
                    ConfigManager.getConfig().maxMinedBlocks);

            // Consider tool wear
            applyDurabilityLoss(heldItem, player, visited.size());

            return true;
        });
    }

    private static boolean isValidTool(ItemStack item) {
        return item.isOf(ModItems.GEAR_PICKAXE);
    }

    private static void breakOres(ServerWorld world, BlockPos startPos, Block block, Set<BlockPos> visited, int limit) {
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(startPos);
        visited.add(startPos);

        while (!queue.isEmpty() && visited.size() < limit) {
            BlockPos pos = queue.poll();
            world.breakBlock(pos, true);

            for (Direction direction : new Direction[]{Direction.UP, Direction.NORTH, Direction.EAST,
                    Direction.SOUTH, Direction.WEST, Direction.DOWN}) {
                BlockPos newPos = pos.offset(direction);
                if (!visited.contains(newPos)) {
                    BlockState newState = world.getBlockState(newPos);
                    if (newState.isIn(ORES_TAG)) {
                        queue.add(newPos);
                        visited.add(newPos);
                    }
                }
            }
        }
    }

    private static void applyDurabilityLoss(ItemStack tool, net.minecraft.entity.player.PlayerEntity player,
                                            int blocksBroken) {
        if (tool.isDamageable()) {
            int unbreakingLevel = EnchantmentHelper.getLevel(Enchantments.UNBREAKING, tool);
            Random random = new Random();

            int damage = 0;
            for (int i = 0; i < blocksBroken; i++) {
                if (unbreakingLevel > 0) {
                    if (random.nextInt(unbreakingLevel + 1) == 0) { // Chance of reducing wear
                        damage++;
                    }
                } else {
                    damage++;
                }
            }

            if (tool.getDamage() + damage >= tool.getMaxDamage() - 1) {
                tool.setDamage(tool.getMaxDamage() - 1); // Keep 1 strength
            } else {
                tool.damage(damage, player, (p) -> p.sendToolBreakStatus(p.getActiveHand()));
            }
        }
    }
}
