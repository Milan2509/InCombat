package eagleseye.incombat.effect;

import eagleseye.incombat.util.PacketManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

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

    @Override
    public void onApplied(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
//        serverPlayer.sendMessage(Text.literal("[APPLIED] Found server player"));
//        PacketManager.sendCombatState((ServerPlayerEntity) entity, true);

//        MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_BACK);

    }

    @Override
    public void onRemoved(LivingEntity entity, AttributeContainer attributes, int amplifier) {
        ServerPlayerEntity serverPlayer = (ServerPlayerEntity) entity;
//        serverPlayer.sendMessage(Text.literal("[REMOVED] Found server player"));
//        PacketManager.sendCombatState((ServerPlayerEntity) entity, false);

//        MinecraftClient.getInstance().options.setPerspective(Perspective.FIRST_PERSON);
    }
}
