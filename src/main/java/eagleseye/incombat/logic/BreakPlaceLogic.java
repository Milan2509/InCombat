package eagleseye.incombat.logic;

import eagleseye.incombat.InCombat;
import eagleseye.incombat.api.CombatCheck;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.util.ActionResult;

public class BreakPlaceLogic {
    public static void init(){
        // Block Breaking
        AttackBlockCallback.EVENT.register((player, world, hand, pos, direction) -> {
            if(CombatCheck.isPlayerInCombat(player) && InCombat.COMBAT_CONFIG.disableBlockBreaking()) return ActionResult.FAIL;

            return ActionResult.PASS;
        });

        // Block Placing
        UseBlockCallback.EVENT.register(((player, world, hand, blockHitResult) -> {
            if(CombatCheck.isPlayerInCombat(player) && InCombat.COMBAT_CONFIG.disableBlockPlacing()) return ActionResult.FAIL;

            return ActionResult.PASS;
        }));
    }
}
