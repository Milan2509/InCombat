package eagleseye.in_combat.internals.networking;

import eagleseye.in_combat.internals.InCombatManager;
import eagleseye.in_combat.internals.client.ClientStates;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;

public class NetworkHandler {
    public static void registerPayloads() {
        PayloadTypeRegistry.playS2C().register(CombatDataPayload.ID, CombatDataPayload.CODEC);
        PayloadTypeRegistry.playS2C().register(GraceDataPayload.ID, GraceDataPayload.CODEC);
    }

    public static void registerClient() {
        ClientPlayNetworking.registerGlobalReceiver(CombatDataPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientStates.setInCombat(payload.inCombat());
            });
        });

        ClientPlayNetworking.registerGlobalReceiver(GraceDataPayload.ID, (payload, context) -> {
            context.client().execute(() -> {
                ClientStates.setHasGrace(payload.hasGrace());
            });
        });
    }

    public static void sendDataToClient(ServerPlayerEntity player, CombatDataPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void sendDataToClient(ServerPlayerEntity player, GraceDataPayload payload) {
        ServerPlayNetworking.send(player, payload);
    }

    public static void syncData(ServerPlayerEntity player) {
        if (InCombatManager.hasCombatEffect(player)) {
            sendDataToClient(player, new CombatDataPayload(true));
        } else {
            sendDataToClient(player, new CombatDataPayload(false));
        }

        if (InCombatManager.hasGraceEffect(player)) {
            sendDataToClient(player, new GraceDataPayload(true));
        } else {
            sendDataToClient(player, new GraceDataPayload(false));
        }
    }

    public static void registerSyncDataOnLogin() {
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
                syncData(handler.player);
        });
    }
}
