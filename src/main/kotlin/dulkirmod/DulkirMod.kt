package dulkirmod

import dulkirmod.command.*
import dulkirmod.config.DulkirConfig
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiScreen
import net.minecraft.client.settings.KeyBinding
import net.minecraftforge.client.ClientCommandHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.client.registry.ClientRegistry
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.gameevent.InputEvent.KeyInputEvent
import net.minecraftforge.fml.common.gameevent.TickEvent
import net.minecraftforge.fml.common.gameevent.TickEvent.ClientTickEvent
import org.lwjgl.input.Keyboard

@Mod(
    modid = DulkirMod.MOD_ID,
    name = DulkirMod.MOD_NAME,
    version = DulkirMod.MOD_VERSION,
    clientSideOnly = true
)
class DulkirMod {

    @Mod.EventHandler
    fun preInit(event: FMLPreInitializationEvent) {
        val cch = ClientCommandHandler.instance
        cch.registerCommand(SettingsCommand())
    }

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        config.init()
        val mcBus = MinecraftForge.EVENT_BUS
        mcBus.register(this)

        keyBinds.forEach(ClientRegistry::registerKeyBinding)
    }

    @SubscribeEvent
    fun onTick(event: ClientTickEvent) {
        if (event.phase == TickEvent.Phase.START && display != null) {
            mc.displayGuiScreen(display)
            display = null
        }
    }

    @SubscribeEvent
    fun onKey(event: KeyInputEvent) {
        if (keyBinds[0].isPressed) config.openGui()
    }

    companion object {
        const val MOD_ID = "swingspeed"
        const val MOD_NAME = "Swing Speed"
        const val MOD_VERSION = "1.2.9"

        val mc: Minecraft = Minecraft.getMinecraft()
        var config = DulkirConfig
        var display: GuiScreen? = null

        val keyBinds = arrayOf(
            KeyBinding("Open Settings", Keyboard.KEY_RCONTROL, "Swing Speed")
        )
    }

}
