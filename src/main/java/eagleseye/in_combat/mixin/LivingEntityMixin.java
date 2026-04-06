package eagleseye.in_combat.mixin;

import eagleseye.in_combat.internals.InCombatManager;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "onDamaged", at = @At("TAIL"))
    private void setInCombatWhenDamaged(DamageSource damageSource, CallbackInfo ci){
        LivingEntity self = (LivingEntity)(Object)this;

        if(self instanceof PlayerEntity player){
            InCombatManager.applyInCombatEffect(player, damageSource);
        }
    }
}
