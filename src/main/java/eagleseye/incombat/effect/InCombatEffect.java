package eagleseye.incombat.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class InCombatEffect extends StatusEffect {
    public InCombatEffect() {
        super(StatusEffectCategory.NEUTRAL, 0x98D982);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
         super.applyUpdateEffect(entity, amplifier);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
