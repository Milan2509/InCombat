package eagleseye.incombat.mixin;

import eagleseye.incombat.integrations.WaystonesIntegrations;
import eagleseye.incombat.util.DependencyUtils;
import eagleseye.incombat.util.EffectUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.damage.DamageTypes;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static eagleseye.incombat.InCombat.COMBAT_CONFIG;
import static eagleseye.incombat.util.EffectUtils.applyCombatEffect;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
    @Inject(method = "damage", at = @At("TAIL"))
    private void applyEffectOnDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir){
        LivingEntity self = (LivingEntity)(Object)this;
        Entity attacker = source.getAttacker();

        boolean canApply = false;

        //Checks to prevent issues
        if (!(self instanceof PlayerEntity player) || player.getWorld().isClient) return;

        //Config checks
        //Always
        if(COMBAT_CONFIG.damageSources.always()) canApply = true;
        //Entity
        if(COMBAT_CONFIG.damageSources.entity() && attacker instanceof LivingEntity
                && !(attacker instanceof PlayerEntity)) canApply = true;
        //Player
        if(COMBAT_CONFIG.damageSources.entity() && attacker instanceof PlayerEntity) canApply = true;
        //Fire
        if(COMBAT_CONFIG.damageSources.fire() && source.isOf(DamageTypes.ON_FIRE)) canApply = true;
        //Fall Damage
        if(COMBAT_CONFIG.damageSources.fallDamage() && source.isOf(DamageTypes.FALL)) canApply = true;
        //Dragon Breath
        if(COMBAT_CONFIG.damageSources.dragonBreath() && source.isOf(DamageTypes.DRAGON_BREATH)) canApply = true;

        //Effects
        if(COMBAT_CONFIG.checkForEffects()) {
            for (String effect : COMBAT_CONFIG.applyEffects()){
                if(EffectUtils.hasEffectWithKeyword(player, effect)){
                    canApply = true;
                }
            }
        }

        //Apply to attack (when enabled)
//        if(InCombat.COMBAT_CONFIG.onAttack()){
//            if(attacker.isPlayer()){
//                LivingEntity attacker1 = (LivingEntity) attacker;
//                PlayerEntity playerAttacker = attacker1.getCommandSource().getPlayer();
//                applyCombatEffect(playerAttacker);
//            }
//        }

        //Apply Effect, if possible
        if(canApply) applyCombatEffect(player);
    }

    @Inject(method = "tick", at = @At("HEAD"))
    private void playerHasEffectOrAlwaysInCombat(CallbackInfo ci){
        if ((LivingEntity)(Object)this instanceof PlayerEntity player){
            //Always active
            if(COMBAT_CONFIG.alwaysActive()) applyCombatEffect(player);

            //Waystones integration
            if(DependencyUtils.isWaystonesLoaded()) {
                WaystonesIntegrations.stopTeleportItems(player);
            }

        }
    }
}
