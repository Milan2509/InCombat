package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ServerConfig;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GraceModifiers {
    private static final ServerConfig.CombatRestrictionsConfig restrictionsConfig = InCombat.serverConfig.grace_settings.restrictions;

    public static ActionResult preventBlockBreaking(PlayerEntity player, World world, BlockPos pos) {
        if(!canBreakBlock(player, world, pos)) {
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

    public static void heal(PlayerEntity player) {
        if(InCombat.serverConfig.grace_settings.heal){
            player.heal(InCombat.serverConfig.grace_settings.heal_amount);
        }
    }

    private static boolean canPlaceBlock(PlayerEntity player, Hand hand) {
        Item handItem = player.getStackInHand(hand).getItem();
        String heldBlockId =  Registries.ITEM.getId(handItem).getNamespace() + ":" + Registries.ITEM.getId(handItem).getPath();

        // Return false if: player is in combat, hand item is a block, hand item is not whitelisted and the features is enabled in the config
        return !(
                InCombatManager.hasGraceEffect(player)
                && handItem instanceof BlockItem
                && !restrictionsConfig.block_placing_whitelist.contains(heldBlockId)
                && restrictionsConfig.disable_block_placing
        );
    }

    private static boolean canBreakBlock(PlayerEntity player, World world, BlockPos pos) {
        String blockId = Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).getNamespace() + ":" +  Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).getPath();

        // Return false if: player is in combat, target block is not in whitelisted and the feature is enabled in the config
        return !(
                InCombatManager.hasGraceEffect(player)
                && !restrictionsConfig.block_breaking_whitelist.contains(blockId)
                && restrictionsConfig.disable_block_breaking
        );
    }

}
