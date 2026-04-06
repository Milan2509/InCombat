package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class InCombatRestrictions {
    public static ActionResult preventBlockBreaking(PlayerEntity player) {
        if(InCombatManager.hasInCombatEffect(player)) {
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    public static ActionResult preventBlockPlacing(PlayerEntity player, Hand hand) {
        if(!canPlaceBlock(player, hand)) {
            return ActionResult.FAIL;
        }
        return ActionResult.PASS;
    }

    private static boolean canPlaceBlock(PlayerEntity player, Hand hand) {
        Item handItem = player.getStackInHand(hand).getItem();
        String heldBlockId =  Registries.ITEM.getId(handItem).getNamespace() + ":" + Registries.ITEM.getId(handItem).getPath();

        // Return false if: player is in combat, hand item is a block, hand item is not whitelisted
        return !(InCombatManager.hasInCombatEffect(player) && handItem instanceof BlockItem
                && !InCombat.serverConfig.combat_restrictions.block_placing_whitelist.contains(heldBlockId));
    }

    public static void initializeEvents(){
        // Break Block Prevention
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) -> InCombatRestrictions.preventBlockBreaking(playerEntity)));
        // Place Block Prevention
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos) -> InCombatRestrictions.preventBlockPlacing(playerEntity, hand)));
    }

}
