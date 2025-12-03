package eagleseye.incombat.util;

import eagleseye.incombat.InCombat;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

public class PacketManager {
    public static final Identifier SYNC_COMBAT_ID =
            new Identifier(InCombat.MOD_ID, "sync_combat_state");

    public static void sendCombatState(ServerPlayerEntity player, boolean inCombat) {
        PacketByteBuf buf = PacketByteBufs.create();
        buf.writeBoolean(inCombat);

        ClientPlayNetworking.send(SYNC_COMBAT_ID, buf);
    }

    public static class CombatClientState {
        public static boolean inCombat = false;
    }

}
