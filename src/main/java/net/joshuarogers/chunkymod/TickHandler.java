package net.joshuarogers.chunkymod;

import net.joshuarogers.chunkymod.generators.IShapeGenerator;
import net.joshuarogers.chunkymod.generators.SquareGenerator;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.ChunkProviderServer;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.fml.common.FMLLog;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;

public class TickHandler {
    private IShapeGenerator generator;

    public TickHandler(IShapeGenerator generator) {
        this.generator = generator;
    }

    @SubscribeEvent
    public void onServerTick(TickEvent.ServerTickEvent tickEvent) {
        if (tickEvent.phase == TickEvent.Phase.START) {
            if (generator.next()) {
                ChunkLocation location = generator.getCurrentPoint();
                FMLLog.info(String.format("Building (%s,%s)", location.getX(), location.getY()));

                ChunkProviderServer chunkProviderServer = DimensionManager
                        .getWorld(0)
                        .getChunkProvider();
                Chunk chunk = chunkProviderServer
                        .provideChunk(location.getX(), location.getY());
                
                chunk.needsSaving(true);
                chunkProviderServer.unload(chunk);
            }
        }
    }
}
