package eagleseye.incombat.logic;

import eagleseye.incombat.InCombat;
import eagleseye.incombat.api.CombatCheck;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.server.network.ServerPlayerEntity;

public class PlayerDisconnectLogic {
    private static void killPlayerOnDisconnect(ServerPlayerEntity player){
        if(CombatCheck.isPlayerInCombat(player)){
            player.kill();
        }
    }

    public static void init(){
        ServerPlayConnectionEvents.DISCONNECT.register((handler, server) -> {
            killPlayerOnDisconnect(handler.player);
        });
    }
}
