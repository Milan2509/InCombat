package eagleseye.in_combat.internals.effect;

import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;

public class CombatEffect extends StatusEffect {
    public CombatEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }
}
