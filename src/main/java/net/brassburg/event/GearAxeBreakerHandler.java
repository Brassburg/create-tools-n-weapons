package net.brassburg.event;

import net.brassburg.CreateToolsNWeaponsMod;
import net.brassburg.config.ConfigManager;
import net.brassburg.init.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;

import java.util.*;

public class GearAxeBreakerHandler {

    public static void register() {
        PlayerBlockBreakEvents.BEFORE.register((world, player, pos, state,
                                                blockEntity) -> {
            if (world.isClient) return true; // Only server logic

            ItemStack heldItem = player.getMainHandStack();
            if (!isValidTool(heldItem)) return true;

            if (!CreateToolsNWeaponsMod.isBreakModeActive) return true;

            Set<BlockPos> visited = new HashSet<>();
            breakConnectedLogs((ServerWorld) world, pos, state.getBlock(), visited,
                    ConfigManager.getConfig().maxMinedBlocks);

            applyDurabilityLoss(heldItem, player, visited.size());

            return true;
        });
    }

    private static boolean isValidTool(ItemStack item) {
        return item.isOf(ModItems.GEAR_AXE);
    }

    private static void breakConnectedLogs(ServerWorld world, BlockPos startPos,
                                           Block block, Set<BlockPos> visited, int limit) {
        Queue<BlockPos> queue = new LinkedList<>();
        queue.add(startPos);
        visited.add(startPos);

        int logsBroken = 0;

        while (!queue.isEmpty() && logsBroken < limit) {
            BlockPos pos = queue.poll();
            BlockState state = world.getBlockState(pos);
            boolean isLog = state.isIn(BlockTags.LOGS);
            boolean isLeaves = state.isIn(BlockTags.LEAVES);

            if (isLog || isLeaves) {
                world.breakBlock(pos, true);

                for (Direction direction : Direction.values()) {
                    BlockPos newPos = pos.offset(direction);
                    BlockState newState = world.getBlockState(newPos);

                    if (!visited.contains(newPos)) {
                        if (newState.isIn(BlockTags.LOGS)) {
                            queue.add(newPos);
                            visited.add(newPos);
                            logsBroken++;
                        } else if (newState.isIn(BlockTags.LEAVES)) {
                            queue.add(newPos);
                        }
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
