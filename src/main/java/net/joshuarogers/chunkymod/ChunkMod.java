package net.joshuarogers.chunkymod;

import net.joshuarogers.chunkymod.commands.GenerateSquareCommand;
import net.minecraft.command.CommandHandler;
import net.minecraft.init.Blocks;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLServerStartingEvent;

@Mod(modid = ChunkMod.MODID, version = ChunkMod.VERSION)
public class ChunkMod
{
    public static final String MODID = "chunkymod";
    public static final String VERSION = "0.1";
    
    @EventHandler
    public void init(FMLInitializationEvent event)
    {
        // some example code
        System.out.println("DIRT BLOCK >> "+Blocks.DIRT.getUnlocalizedName());
    }

    @EventHandler
    public void serverStarting(FMLServerStartingEvent event)
    {
        CommandHandler commandManager = (CommandHandler) event.getServer().getCommandManager();
        commandManager.registerCommand(new GenerateSquareCommand());
    }
}
