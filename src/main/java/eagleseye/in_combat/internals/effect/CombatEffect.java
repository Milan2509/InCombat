package eagleseye.in_combat.internals.effect;

import eagleseye.in_combat.internals.networking.CombatDataPayload;
import eagleseye.in_combat.internals.networking.NetworkHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;

public class CombatEffect extends StatusEffect {
    public CombatEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayerEntity player){
            NetworkHandler.sendDataToClient(player, new CombatDataPayload(true));
        }
    }
}
