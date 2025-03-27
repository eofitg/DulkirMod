package dulkirmod.utils

import dulkirmod.DulkirMod
import net.minecraft.util.ChatComponentText

object TextUtils {
	fun info(text: String, prefix: Boolean = true) {
		if (DulkirMod.mc.thePlayer == null) return

		val textPrefix = if (prefix) "${DulkirMod.CHAT_PREFIX} " else ""
		DulkirMod.mc.thePlayer.addChatMessage(ChatComponentText("$textPrefix$text§r"))
	}

}