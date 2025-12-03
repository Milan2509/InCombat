package eagleseye.incombat;

import eagleseye.incombat.util.PacketManager;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.Perspective;

public class InCombatClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientPlayNetworking.registerGlobalReceiver(
                PacketManager.SYNC_COMBAT_ID,
                (client, handler, buf, responseSender) -> {

                    boolean inCombat = buf.readBoolean();

                    client.execute(() -> {
                        PacketManager.CombatClientState.inCombat = inCombat;

                        if (inCombat) {
                            MinecraftClient.getInstance().options.setPerspective(Perspective.THIRD_PERSON_BACK);
                        } else {
                            MinecraftClient.getInstance().options.setPerspective(Perspective.FIRST_PERSON);
                        }
                    });
                });
    }
}
