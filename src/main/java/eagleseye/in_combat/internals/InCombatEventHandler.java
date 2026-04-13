package eagleseye.in_combat.internals;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class InCombatEventHandler {
    public static void init(){
        // Apply In Combat Effect
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (entity instanceof ServerPlayerEntity player) {
                InCombatManager.applyInCombatEffect(player, source);
            }
        });
        // Break Block Prevention
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) ->
                InCombatRestrictions.preventBlockBreaking(playerEntity, world, blockPos)));
        // Place Block Prevention
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos) ->
                InCombatRestrictions.preventBlockPlacing(playerEntity, hand)));
        // Kill on Disconnect
        ServerPlayConnectionEvents.DISCONNECT.register(((playerEntity, server) ->
                InCombatRestrictions.killOnDisconnect(playerEntity.player)));
    }
}
