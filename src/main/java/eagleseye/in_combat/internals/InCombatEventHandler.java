package eagleseye.in_combat.internals;

import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class InCombatEventHandler {
    public static void init(){
        /*
        *
        * Combat Effect
        *
        */
        // Apply In Combat Effect
        ServerLivingEntityEvents.AFTER_DAMAGE.register((entity, source, baseDamageTaken, damageTaken, blocked) -> {
            if (entity instanceof ServerPlayerEntity player) {
                InCombatManager.applyCombatEffect(player, source);
            }
        });
        // Break Block Prevention
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) ->
                CombatRestrictions.preventBlockBreaking(playerEntity, world, blockPos)));
        // Place Block Prevention
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos) ->
                CombatRestrictions.preventBlockPlacing(playerEntity, hand)));
        // Kill on Disconnect
        ServerPlayConnectionEvents.DISCONNECT.register(((playerEntity, server) ->
                CombatRestrictions.killOnDisconnect(playerEntity.player)));

        /*
         *
         * Grace Effect
         *
         */
        // Break Block Prevention
        AttackBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos, direction) ->
                GraceModifiers.preventBlockBreaking(playerEntity, world, blockPos)));
        // Place Block Prevention
        UseBlockCallback.EVENT.register(((playerEntity, world, hand, blockPos) ->
                GraceModifiers.preventBlockPlacing(playerEntity, hand)));
    }
}
