package dulkirmod;

import dulkirmod.command.*;
import dulkirmod.config.ConfigReader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(
        modid = DulkirMod.MOD_ID,
        name = DulkirMod.MOD_NAME,
        version = DulkirMod.MOD_VERSION,
        clientSideOnly = true
)
public class DulkirMod {

    public static final String MOD_ID = "swingspeed";
    public static final String MOD_NAME = "Swing Speed";
    public static final String MOD_VERSION = "2.0";

    public static Minecraft getMc() {
        return Minecraft.getMinecraft();
    }

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        ClientCommandHandler cch = ClientCommandHandler.instance;
        cch.registerCommand(new SettingsCommand());
    }

    @Mod.EventHandler
    public void onInit(FMLInitializationEvent event) {
        ConfigReader.load();
    }

}