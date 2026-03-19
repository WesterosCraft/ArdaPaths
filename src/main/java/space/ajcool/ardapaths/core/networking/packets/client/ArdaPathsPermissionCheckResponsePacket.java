package space.ajcool.ardapaths.core.networking.packets.client;

import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.minecraft.util.Uuids;

import java.util.UUID;

public record ArdaPathsPermissionCheckResponsePacket(UUID requestId, boolean hasPermission) implements CustomPayload {

    public static final CustomPayload.Id<ArdaPathsPermissionCheckResponsePacket> ID =
            new CustomPayload.Id<>(Identifier.of("ardapaths", "ardapaths_permission_check_response"));

    public static final PacketCodec<? super RegistryByteBuf, ArdaPathsPermissionCheckResponsePacket> CODEC =
            PacketCodec.tuple(
                    Uuids.PACKET_CODEC, ArdaPathsPermissionCheckResponsePacket::requestId,
                    PacketCodecs.BOOL, ArdaPathsPermissionCheckResponsePacket::hasPermission,
                    ArdaPathsPermissionCheckResponsePacket::new
            );

    @Override
    public CustomPayload.Id<ArdaPathsPermissionCheckResponsePacket> getId() {
        return ID;
    }
}
