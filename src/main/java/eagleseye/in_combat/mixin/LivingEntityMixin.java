package eagleseye.in_combat.mixin;

import eagleseye.in_combat.InCombat;
import eagleseye.in_combat.internals.networking.CombatDataPayload;
import eagleseye.in_combat.internals.networking.GraceDataPayload;
import eagleseye.in_combat.internals.networking.NetworkHandler;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onStatusEffectRemoved", at = @At("TAIL"))
    private void onCombatEffectRemoved(StatusEffectInstance statusEffectInstance, CallbackInfo ci) {
        if(!((Object)this instanceof ServerPlayerEntity player)) return;

        // Note: do not use InCombatManager#hasCombatEffect it breaks stuff
        if(statusEffectInstance.getEffectType().value() == InCombat.combatEffect) {
            NetworkHandler.sendDataToClient(player, new CombatDataPayload(false));
        }

        // Note: do not use InCombatManager#hasGraceEffect it breaks stuff
        if(statusEffectInstance.getEffectType().value() == InCombat.graceEffect) {
            NetworkHandler.sendDataToClient(player, new GraceDataPayload(false));
        }
    }
}
