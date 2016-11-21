package net.joshuarogers.chunkymod.generators;

import net.joshuarogers.chunkymod.ChunkLocation;

public class SquareGenerator implements IShapeGenerator {
    private int radius;
    private int x;
    private int y;
    private ChunkLocation currentLocation;

    @Override
    public void reset(int radius) {
        this.radius = radius;

        // Sets the current
        this.x = -radius;
        this.y = -radius;
        this.currentLocation = null;
    }

    @Override
    public ChunkLocation getCurrentPoint() {
        return currentLocation;
    }

    @Override
    public boolean next() {
        if (x == radius && y == radius) {
            currentLocation = null;
            return false;
        } else if (x == radius) {
            x = -radius;
            y++;
        } else {
            x++;
        }

        currentLocation = new ChunkLocation(x, y);
        return true;
    }
}
