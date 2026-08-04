package eagleseye.in_combat.internals.effect;

import eagleseye.in_combat.internals.GraceModifiers;
import eagleseye.in_combat.internals.networking.CombatDataPayload;
import eagleseye.in_combat.internals.networking.GraceDataPayload;
import eagleseye.in_combat.internals.networking.NetworkHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.registry.Registries;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class GraceEffect extends StatusEffect {
    public GraceEffect() {
        super(StatusEffectCategory.NEUTRAL, 0xe9b8b3);
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return true;
    }

    @Override
    public boolean applyUpdateEffect(LivingEntity entity, int amplifier) {
        if(entity instanceof PlayerEntity player) {
            GraceModifiers.heal(player);
        }
        return true;
    }

    @Override
    public void onApplied(LivingEntity entity, int amplifier) {
        if (entity instanceof ServerPlayerEntity player){
            NetworkHandler.sendDataToClient(player, new GraceDataPayload(true));
        }
    }
}
