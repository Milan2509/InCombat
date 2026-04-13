package eagleseye.in_combat.api;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.internals.InCombatManager;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;

public class InCombatAPI {
    /**
     * This method can be used to apply the combat effect to the player
     * @param player the ServerPlayerEntity instance of the player to apply the effect to
     * @param duration the duration in seconds that the player will gain the combat effect
     */
    public static void applyCombatEffect(ServerPlayerEntity player, int duration) {
        InCombatManager.applyCombatEffect(player, duration * 20);
    }

    /**
     * This method can be used to apply the grace effect to the player
     * @param player the ServerPlayerEntity instance of the player to apply the effect to
     * @param duration the duration in seconds that the player will gain the combat effect
     */
    public static void applyGraceEffect(ServerPlayerEntity player, int duration) {
        InCombatManager.applyGraceEffect(player, duration * 20);
    }

    /**
     * This method can be used to remove the combat effect from the player
     * @param player the ServerPlayerEntity instance of the player to remove the effect from
     */
    public static void removeCombatEffect(ServerPlayerEntity player) {
        player.removeStatusEffect(Registries.STATUS_EFFECT.getEntry(InCombat.combatEffect));
    }

    /**
     * This method can be used to remove the grace effect from the player
     * @param player the ServerPlayerEntity instance of the player to remove the effect from
     */
    public static void removeGraceEffect(ServerPlayerEntity player) {
        player.removeStatusEffect(Registries.STATUS_EFFECT.getEntry(InCombat.graceEffect));
    }

    /**
     * This method can be used to check if the player has the combat effect
     * @param player the ServerPlayerEntity instance of the player that you want to check
     * @return true if the player has the combat effect
     */
    public static boolean isInCombat(ServerPlayerEntity player) {
        return InCombatManager.hasCombatEffect(player);
    }

    /**
     * This method can be used to check if the player has the grace effect
     * @param player the ServerPlayerEntity instance of the player that you want to check
     * @return true if the player has the grace effect
     */
    public static boolean hasGrace(ServerPlayerEntity player) {
        return InCombatManager.hasGraceEffect(player);
    }
}
