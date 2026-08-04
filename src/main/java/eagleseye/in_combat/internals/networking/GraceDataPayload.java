package eagleseye.in_combat.internals.networking;

import eagleseye.in_combat.InCombat;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;

public record GraceDataPayload(boolean hasGrace) implements CustomPayload {
    public static final Id<GraceDataPayload> ID = new Id<>(Identifier.of(InCombat.MOD_ID, "grace_data"));
    public static final PacketCodec<RegistryByteBuf, GraceDataPayload> CODEC = PacketCodec.tuple(
            PacketCodecs.BOOL, GraceDataPayload::hasGrace,
            GraceDataPayload::new
    );

    @Override
    public Id<? extends CustomPayload> getId() { return ID; }
}
