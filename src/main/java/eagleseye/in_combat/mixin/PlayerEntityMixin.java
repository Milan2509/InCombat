package eagleseye.in_combat.mixin;

import eagleseye.in_combat.internals.CombatRestrictions;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerEntity.class)
public class PlayerEntityMixin {
    @Inject(method = "canFoodHeal", at = @At("HEAD"), cancellable = true)
    private void disableNaturalHealthRegeneration(CallbackInfoReturnable<Boolean> cir) {
        PlayerEntity player = (PlayerEntity)(Object)this;
        CombatRestrictions.preventNaturalHealthRegeneration(player, cir);
    }
}
