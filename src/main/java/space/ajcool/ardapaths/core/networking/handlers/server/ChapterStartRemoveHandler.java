package space.ajcool.ardapaths.core.networking.handlers.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import space.ajcool.ardapaths.ArdaPaths;
import space.ajcool.ardapaths.core.networking.packets.server.ChapterStartRemovePacket;

public class ChapterStartRemoveHandler {

    public void send(ChapterStartRemovePacket packet) {
        ClientPlayNetworking.send(packet);
    }

    public void receive(ChapterStartRemovePacket payload, ServerPlayNetworking.Context context) {
        final String pathId = payload.pathId();
        final String chapterId = payload.chapterId();
        ArdaPaths.CONFIG.removeChapterStart(pathId, chapterId);
        ArdaPaths.CONFIG_MANAGER.save();
    }
}
