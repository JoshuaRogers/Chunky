package net.joshuarogers.chunkymod.generators;

import net.joshuarogers.chunkymod.ChunkLocation;

public interface IShapeGenerator {
    void reset(int radius);

    ChunkLocation getCurrentPoint();

    boolean next();
}
