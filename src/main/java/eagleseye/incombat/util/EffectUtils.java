package eagleseye.incombat.util;

import eagleseye.incombat.InCombat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;

public class EffectUtils {
    public static void applyCombatEffect(PlayerEntity player){
        player.addStatusEffect(new StatusEffectInstance(InCombat.IN_COMBAT,
                COMBAT_CONFIG.inCombatDuration(), 0, true, false));
    }

    public static boolean hasEffectWithKeyword(LivingEntity entity, String keyword) {
        for (StatusEffectInstance instance : entity.getStatusEffects()) {
            String translationString = instance.getTranslationKey();
            if (translationString != null && translationString.contains(keyword)) {
                return true;
            }
        }
        return false;
    }
}
