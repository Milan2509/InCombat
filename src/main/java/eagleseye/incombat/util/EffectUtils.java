package eagleseye.incombat.util;

import eagleseye.incombat.InCombat;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;

public class EffectUtils {
    public static void applyCombatEffect(PlayerEntity player){
        //Should not apply when player is in creative + config
        if(!COMBAT_CONFIG.creativeMode() && player.isCreative()) return;

        player.addStatusEffect(new StatusEffectInstance(InCombat.IN_COMBAT, COMBAT_CONFIG.inCombatDuration(), 0, true, false));

        // Additional effects that the player gains
        if(!COMBAT_CONFIG.additionalEffects().isEmpty()){
            for (String effect : COMBAT_CONFIG.additionalEffects()) {
                Identifier effectId = new Identifier(effect);
                // Only if the effect exists
                if (Registries.STATUS_EFFECT.contains(RegistryKey.of(RegistryKeys.STATUS_EFFECT, effectId))){
                    player.addStatusEffect(new StatusEffectInstance(Registries.STATUS_EFFECT.get(effectId), COMBAT_CONFIG.inCombatDuration(),
                            0, true, false));
                }

            }

        }
    }

//    public static boolean hasEffectWithKeyword(LivingEntity entity, String keyword) {
//        for (StatusEffectInstance instance : entity.getStatusEffects()) {
//            String translationString = instance.getTranslationKey();
//            if (translationString != null && translationString.contains(keyword)) {
//                return true;
//            }
//        }
//        return false;
//    }
}
