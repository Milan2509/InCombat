package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ServerConfig;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

public class InCombatRestrictions {
    private static final ServerConfig.CombatRestrictions restrictionsConfig = InCombat.serverConfig.combat_restrictions;

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

    public static void killOnDisconnect(ServerPlayerEntity player) {
        if(InCombatManager.hasInCombatEffect(player) && restrictionsConfig.kill_on_disconnect) {
            player.kill();
        }
    }

    public static void preventNaturalHealthRegeneration(PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
        player.sendMessage(Text.literal("Player Found!"));
        player.sendMessage(Text.literal("Config: " + restrictionsConfig.prevent_natural_health_regeneration));
        if(InCombatManager.hasInCombatEffect(player) && restrictionsConfig.prevent_natural_health_regeneration) {
            player.sendMessage(Text.literal("If statement passed!"));
            cir.setReturnValue(false);
        }
    }

    private static boolean canPlaceBlock(PlayerEntity player, Hand hand) {
        Item handItem = player.getStackInHand(hand).getItem();
        String heldBlockId =  Registries.ITEM.getId(handItem).getNamespace() + ":" + Registries.ITEM.getId(handItem).getPath();

        // Return false if: player is in combat, hand item is a block, hand item is not whitelisted and the features is enabled in the config
        return !(
                InCombatManager.hasInCombatEffect(player)
                && handItem instanceof BlockItem
                && !restrictionsConfig.block_placing_whitelist.contains(heldBlockId)
                && restrictionsConfig.disable_block_placing
        );
    }

    private static boolean canBreakBlock(PlayerEntity player, World world, BlockPos pos) {
        String blockId = Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).getNamespace() + ":" +  Registries.BLOCK.getId(world.getBlockState(pos).getBlock()).getPath();

        // Return false if: player is in combat, target block is not in whitelisted and the feature is enabled in the config
        return !(
                InCombatManager.hasInCombatEffect(player)
                && !restrictionsConfig.block_breaking_whitelist.contains(blockId)
                && restrictionsConfig.disable_block_breaking
        );
    }

    public static void initializeEvents(){
        // Break Block Prevention
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) ->
                InCombatRestrictions.preventBlockBreaking(playerEntity, world, blockPos)));
        // Place Block Prevention
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos) ->
                InCombatRestrictions.preventBlockPlacing(playerEntity, hand)));
        // Kill on Disconnect
        ServerPlayConnectionEvents.DISCONNECT.register(((playerEntity, server) ->
                killOnDisconnect(playerEntity.player)));
    }

}
