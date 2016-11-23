package net.joshuarogers.chunkymod.generators;

import net.joshuarogers.chunkymod.ChunkLocation;

public class OffsetGenerator implements IShapeGenerator {
    private IShapeGenerator generator;
    private ChunkLocation newCenter;

    public OffsetGenerator(IShapeGenerator generator, ChunkLocation newCenter) {
        this.generator = generator;
        this.newCenter = newCenter;
    }

    @Override
    public void reset(int radius) {
        generator.reset(radius);
    }

    @Override
    public ChunkLocation getCurrentPoint() {
        ChunkLocation location = generator.getCurrentPoint();
        return new ChunkLocation(location.getX() + newCenter.getX(),
                                 location.getY() + newCenter.getY());
    }

    @Override
    public boolean next() {
        return generator.next();
    }
}
