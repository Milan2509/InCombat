package eagleseye.in_combat.internals;

import eagleseye.in_combat.InCombat;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.entry.RegistryEntry;

public class InCombatManager {
    private static final RegistryEntry<StatusEffect> IN_COMBAT_EFFECT = Registries.STATUS_EFFECT.getEntry(new InCombatEffect());

    public static void applyInCombatEffect(PlayerEntity player) {
        player.addStatusEffect(new StatusEffectInstance(IN_COMBAT_EFFECT, InCombat.serverConfig.in_combat_duration, 0, true, false));
    }

    public static void removeInCombatEffect(PlayerEntity player) {
        player.removeStatusEffect(IN_COMBAT_EFFECT);
    }

    public static boolean hasInCombatEffect(PlayerEntity player) {
        return player.hasStatusEffect(IN_COMBAT_EFFECT);
    }
}
