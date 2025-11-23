package eagleseye.incombat.logic;

import eagleseye.incombat.InCombat;
import eagleseye.incombat.api.CombatCheck;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

import java.util.Arrays;

public class BreakPlaceLogic {
    public static void init() {
        // Block Breaking
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if (CombatCheck.isPlayerInCombat(player) && InCombat.COMBAT_CONFIG.disableBlockBreaking()) {
                // Check if block is whitelisted
                String blockId = Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).toString();

                if (InCombat.COMBAT_CONFIG.blockBreakingWhitelist().contains(blockId)) {
                    return ActionResult.PASS;
                }
                else return ActionResult.FAIL;
            }

            return ActionResult.PASS;
        });

        // Block Placing
        UseBlockCallback.EVENT.register(((player, world, hand, blockHitResult) -> {
            if (CombatCheck.isPlayerInCombat(player) && InCombat.COMBAT_CONFIG.disableBlockPlacing()) {
                if (player.getStackInHand(hand).getItem() instanceof BlockItem) {
                    // Check if held block is whitelisted
                    String heldBlockId = Registries.ITEM.getId(player.getStackInHand(hand).getItem()).toString();

                    if (InCombat.COMBAT_CONFIG.blockPlacingWhitelist().contains(heldBlockId)) {
                        return ActionResult.PASS;
                    }
                    else return ActionResult.FAIL;
                }

                else return ActionResult.PASS;
            }

            return ActionResult.PASS;
        }));
    }
}
