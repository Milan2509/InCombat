package eagleseye.incombat.api;

import eagleseye.incombat.InCombat;
import net.minecraft.entity.player.PlayerEntity;

public class CombatCheck {
    public static boolean isPlayerInCombat(PlayerEntity player){
        return player.hasStatusEffect(InCombat.IN_COMBAT);
    }
}
