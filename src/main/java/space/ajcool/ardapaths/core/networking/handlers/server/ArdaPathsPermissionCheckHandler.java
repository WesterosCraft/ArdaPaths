package space.ajcool.ardapaths.core.networking.handlers.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import space.ajcool.ardapaths.core.PermissionHelper;
import space.ajcool.ardapaths.core.networking.packets.client.ArdaPathsPermissionCheckResponsePacket;
import space.ajcool.ardapaths.core.networking.packets.server.PermissionCheckRequestPayload;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

public class ArdaPathsPermissionCheckHandler {

    private final Map<UUID, Consumer<ArdaPathsPermissionCheckResponsePacket>> responseConsumers = new HashMap<>();

    public void send(Consumer<ArdaPathsPermissionCheckResponsePacket> consumer) {
        UUID requestId = UUID.randomUUID();
        responseConsumers.put(requestId, consumer);
        ClientPlayNetworking.send(new PermissionCheckRequestPayload(requestId));
    }

    public void receiveOnServer(PermissionCheckRequestPayload payload, ServerPlayNetworking.Context context) {
        boolean hasPerm = PermissionHelper.hasEditPermission(context.player());
        context.responseSender().sendPacket(
                new ArdaPathsPermissionCheckResponsePacket(payload.requestId(), hasPerm)
        );
    }

    public void receiveOnClient(ArdaPathsPermissionCheckResponsePacket payload, ClientPlayNetworking.Context context) {
        Consumer<ArdaPathsPermissionCheckResponsePacket> consumer = responseConsumers.remove(payload.requestId());
        if (consumer != null) consumer.accept(payload);
    }
}
