package net.joshuarogers.chunkymod.commands;

import net.joshuarogers.chunkymod.ChunkLocation;
import net.joshuarogers.chunkymod.TickHandler;
import net.joshuarogers.chunkymod.generators.CircleGenerator;
import net.joshuarogers.chunkymod.generators.IShapeGenerator;
import net.joshuarogers.chunkymod.generators.OffsetGenerator;
import net.joshuarogers.chunkymod.generators.SquareGenerator;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.common.MinecraftForge;

import javax.vecmath.Vector2d;

public class GenerateSquareCommand extends CommandBase {
    private final int SHAPE_POSITION = 0;
    private final int CENTER_X_POSITION = 1;
    private final int CENTER_Z_POSITION = 2;
    private final int CHUNK_RADIUS_POSITION = 3;

    @Override
    public String getCommandName() {
        return "chunky-gen";
    }

    @Override
    public String getCommandUsage(ICommandSender iCommandSender) {
        return String.format("/%s <square|circle> <center-x> <center-z> <#-chunks-radius>", getCommandName());
    }

    @Override
    public void execute(MinecraftServer minecraftServer, ICommandSender iCommandSender, String[] strings) throws CommandException {
        String shapeName = strings[SHAPE_POSITION];
        ChunkLocation center = new ChunkLocation(Integer.parseInt(strings[CENTER_X_POSITION], 10),
                                                 Integer.parseInt(strings[CENTER_Z_POSITION], 10));
        int chunkRadius = Integer.parseInt(strings[CHUNK_RADIUS_POSITION], 10);

        IShapeGenerator shapeGenerator = buildGenerator(shapeName);
        IShapeGenerator offsetGenerator = new OffsetGenerator(shapeGenerator, center);
        offsetGenerator.reset(chunkRadius);

        TickHandler tickHandler = new TickHandler(offsetGenerator);
        MinecraftForge.EVENT_BUS.register(tickHandler);
    }

    private IShapeGenerator buildGenerator(String shape) throws CommandException {
        String normalizedName = shape.trim();
        if (normalizedName.equalsIgnoreCase("square")) {
            return new SquareGenerator();
        } else if (normalizedName.equalsIgnoreCase("circle")) {
            return new CircleGenerator();
        }

        throw new CommandException("The shape name was not valid");
    }
}
