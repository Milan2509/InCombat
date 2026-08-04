package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.config.ServerConfig;
import eagleseye.in_combat.internals.networking.CombatDataPayload;
import eagleseye.in_combat.internals.networking.NetworkHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.network.ServerPlayerEntity;

public class InCombatManager {
    private static final RegistryEntry<StatusEffect> COMBAT_EFFECT = Registries.STATUS_EFFECT.getEntry(InCombat.combatEffect);
    private static final RegistryEntry<StatusEffect> GRACE_EFFECT = Registries.STATUS_EFFECT.getEntry(InCombat.graceEffect);

    public static void applyCombatEffect(ServerPlayerEntity player) {
        applyCombatEffect(player, InCombat.serverConfig.combat_settings.duration * 20);
    }

    public static void applyCombatEffect(ServerPlayerEntity player, int duration) {
        player.addStatusEffect(new StatusEffectInstance(COMBAT_EFFECT, duration, 0, true, false));
    }

    public static void applyCombatEffect(ServerPlayerEntity player, DamageSource source) {
        boolean canApply = false;
        ServerConfig.InCombatSources sourcesConfig = InCombat.serverConfig.combat_settings.sources;

        if(sourcesConfig.all) canApply = true;
        else if(source.getAttacker() instanceof PlayerEntity && sourcesConfig.player) canApply = true;
        else if(source.getAttacker() instanceof Entity && sourcesConfig.entity) canApply = true;
        else if(source.isOf(DamageTypes.ON_FIRE) && sourcesConfig.fire) canApply = true;
        else if(source.isOf(DamageTypes.FALL) && sourcesConfig.fall_damage) canApply = true;

        if (canApply) applyCombatEffect(player);
    }

    public static void applyGraceEffect(ServerPlayerEntity player, int duration) {
        player.addStatusEffect(new StatusEffectInstance(GRACE_EFFECT, duration, 0, true, false));
    }

    public static boolean hasCombatEffect(PlayerEntity player) {
        return player.hasStatusEffect(COMBAT_EFFECT);
    }

    public static boolean hasGraceEffect(PlayerEntity player) {
        return player.hasStatusEffect(GRACE_EFFECT);
    }



}
