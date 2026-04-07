package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ServerConfig;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class InCombatManager {
    private static final RegistryEntry<StatusEffect> IN_COMBAT_EFFECT = Registries.STATUS_EFFECT.getEntry(InCombat.inCombatEffect);

    public static void applyInCombatEffect(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(IN_COMBAT_EFFECT, InCombat.serverConfig.in_combat_duration * 20, 0, true, false));
    }

    public static void applyInCombatEffect(PlayerEntity player, DamageSource source) {
        boolean canApply = false;
        ServerConfig.InCombatSources sourcesConfig = InCombat.serverConfig.in_combat_sources;

        if(sourcesConfig.all) canApply = true;
        else if(source.getAttacker() instanceof PlayerEntity && sourcesConfig.player) canApply = true;
        else if(source.getAttacker() instanceof Entity && sourcesConfig.entity) canApply = true;
        else if(source.isOf(DamageTypes.ON_FIRE) && sourcesConfig.fire) canApply = true;
        else if(source.isOf(DamageTypes.FALL) && sourcesConfig.fall_damage) canApply = true;

        if (canApply) applyInCombatEffect(player);
    }

    public static void removeInCombatEffect(PlayerEntity player) {
        player.removeStatusEffect(IN_COMBAT_EFFECT);
    }

    public static boolean hasInCombatEffect(PlayerEntity player) {
        return player.hasStatusEffect(IN_COMBAT_EFFECT);
    }

    /*
     *
     *
     * Logic for the nbt system
     *
     *
     */

//    public static void tickInCombatState(PlayerEntity player) {}
//
//    public static void setPlayerInCombat(PlayerEntity player) {}
//
//    public static void getPlayerInCombat(PlayerEntity player) {}
//
//    private static void writeInCombatData(PlayerEntity player) {}
//
//    private static void readInCombatData(PlayerEntity player) {}
}
