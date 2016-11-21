package net.joshuarogers.chunkymod.generators;

import net.joshuarogers.chunkymod.ChunkLocation;

public class CircleGenerator implements IShapeGenerator {
    private IShapeGenerator squareGenerator;
    private int radius;

    public CircleGenerator() {
        squareGenerator = new SquareGenerator();
    }

    @Override
    public void reset(int radius) {
        squareGenerator.reset(radius);
        this.radius = radius;
    }

    @Override
    public ChunkLocation getCurrentPoint() {
        return squareGenerator.getCurrentPoint();
    }

    @Override
    public boolean next() {
        boolean candidatesRemain = true;

        do {
            squareGenerator.next();
            candidatesRemain = squareGenerator.getCurrentPoint() != null;
        } while(candidatesRemain && !insideRadius(squareGenerator.getCurrentPoint()));

        return candidatesRemain;
    }

    private boolean insideRadius(ChunkLocation chunkLocation) {
        return Math.hypot(chunkLocation.getX(), chunkLocation.getY()) <= radius;
    }
}
