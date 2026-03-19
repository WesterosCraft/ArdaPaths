package space.ajcool.ardapaths.core.networking.handlers.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.math.BlockPos;
import space.ajcool.ardapaths.ArdaPaths;
import space.ajcool.ardapaths.core.data.config.server.PositionData;
import space.ajcool.ardapaths.core.networking.packets.server.ChapterStartUpdatePacket;

public class ChapterStartUpdateHandler {

    public void send(ChapterStartUpdatePacket packet) {
        ClientPlayNetworking.send(packet);
    }

    public void receive(ChapterStartUpdatePacket payload, ServerPlayNetworking.Context context) {
        final String pathId = payload.pathId();
        final String chapterId = payload.chapterId();
        final BlockPos start = payload.position();
        ArdaPaths.CONFIG.setChapterStart(pathId, chapterId, PositionData.fromBlockPos(start));
        ArdaPaths.CONFIG_MANAGER.save();
    }
}
