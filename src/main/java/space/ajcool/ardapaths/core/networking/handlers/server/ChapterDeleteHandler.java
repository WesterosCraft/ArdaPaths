package space.ajcool.ardapaths.core.networking.handlers.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import space.ajcool.ardapaths.ArdaPaths;
import space.ajcool.ardapaths.core.data.config.shared.PathData;
import space.ajcool.ardapaths.core.networking.packets.server.ChapterDeletePacket;

public class ChapterDeleteHandler {

    public void send(ChapterDeletePacket packet) {
        ClientPlayNetworking.send(packet);
    }

    public void receive(ChapterDeletePacket payload, ServerPlayNetworking.Context context) {
        final String pathId = payload.pathId();
        final String chapterId = payload.chapterId();
        final PathData pathData = ArdaPaths.CONFIG.getPath(pathId);
        if (pathData == null) {
            return;
        }

        pathData.removeChapter(chapterId);
        ArdaPaths.CONFIG_MANAGER.save();
    }
}
