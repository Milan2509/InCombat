package eagleseye.in_combat.internals.networking;

import eagleseye.in_combat.InCombat;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record CombatDataPayload(boolean inCombat) implements CustomPayload {
    public static final Id<CombatDataPayload> ID = new Id<>(Identifier.of(InCombat.MOD_ID, "combat_data"));
    public static final PacketCodec<RegistryByteBuf, CombatDataPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, CombatDataPayload::inCombat,
            CombatDataPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
