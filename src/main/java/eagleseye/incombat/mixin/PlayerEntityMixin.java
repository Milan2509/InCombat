package eagleseye.incombat.mixin;

import eagleseye.incombat.InCombat;
import eagleseye.incombat.api.CombatCheck;
import eagleseye.incombat.util.EffectUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "canFoodHeal", at = @At("RETURN"), cancellable = true)
    private void disableHealWhileInCombat(CallbackInfoReturnable<Boolean> cir){
        PlayerEntity player = (PlayerEntity) (Object) this;

        if(InCombat.COMBAT_CONFIG.disableNaturalRegen() && CombatCheck.isPlayerInCombat(player)){
            cir.setReturnValue(false);
        } else cir.setReturnValue(true);
    }

    @Inject(method = "attack", at = @At("TAIL"))
    private void enterCombatOnAttack(Entity target, CallbackInfo ci){
        if(InCombat.COMBAT_CONFIG.onAttack() && target.isAttackable()){
            PlayerEntity player = (PlayerEntity) (Object) this;
            EffectUtils.applyCombatEffect(player);
        }
    }
}
