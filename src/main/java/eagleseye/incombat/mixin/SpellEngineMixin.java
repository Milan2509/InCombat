package eagleseye.incombat.mixin;

import eagleseye.incombat.InCombat;
import eagleseye.incombat.api.CombatCheck;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.spell_engine.api.enchantment.Enchantments_SpellEngine;
import net.spell_engine.api.spell.Spell;
import net.spell_engine.internals.SpellHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SpellHelper.class)
public class SpellEngineMixin {
    @ModifyVariable(method = "ammoForSpell", at = @At(value = "STORE"), name = "ignoreAmmo")
    private static boolean modifyIgnoreAmmoVar(boolean original, PlayerEntity player, Spell spell, ItemStack itemStack) {
        boolean inCombat = (CombatCheck.isPlayerInCombat(player) && InCombat.COMBAT_CONFIG.spellEngine());

        // Send informational message indicating the player can cast spells because their out of combat
        // Does not send the message if the player's hand item has spell infinity enchantment or the spell does not require runes
        if(!inCombat && spell.cost.item_id != null && !spell.cost.item_id.isEmpty() &&
                !(EnchantmentHelper.getLevel(Enchantments_SpellEngine.INFINITY, player.getMainHandStack()) > 0)) {
            player.sendMessage(Text.translatable("message.incombat.free_cast").formatted(Formatting.GREEN), true);
        }
        return original || !inCombat;
    }
}
