package space.ajcool.ardapaths.core.networking.handlers.server;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import space.ajcool.ardapaths.ArdaPaths;
import space.ajcool.ardapaths.core.networking.packets.server.PathMarkerUpdatePacket;
import space.ajcool.ardapaths.mc.blocks.entities.PathMarkerBlockEntity;

public class PathMarkerUpdateHandler {

    public void send(PathMarkerUpdatePacket packet) {
        ClientPlayNetworking.send(packet);
    }

    public void receive(PathMarkerUpdatePacket payload, ServerPlayNetworking.Context context) {
        ServerPlayerEntity player = context.player();
        MinecraftServer server = context.server();

        BlockPos blockPos = payload.position();
        NbtCompound nbt = payload.data();
        ArdaPaths.LOGGER.info("Received NBT : [{}]", nbt.toString());
        server.execute(() -> {
            BlockEntity blockEntity = player.getWorld().getBlockEntity(blockPos);

            if (blockEntity instanceof PathMarkerBlockEntity marker) {
                marker.readNbt(nbt, player.getRegistryManager());
                marker.markUpdated();
            }
        });
    }
}
