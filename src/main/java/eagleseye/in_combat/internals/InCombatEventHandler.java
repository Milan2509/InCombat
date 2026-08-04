package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ServerConfig;
import net.fabricmc.fabric.api.entity.event.v1.ServerLivingEntityEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;

public class InCombatEventHandler {
    public static void register() {
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

        ServerLivingEntityEvents.ALLOW_DAMAGE.register((entity, source, amount) -> {
            if (source.getAttacker() instanceof ServerPlayerEntity player) {
                InCombatManager.applyCombatEffectByAttacking(player, entity);
            }
            return true;
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
