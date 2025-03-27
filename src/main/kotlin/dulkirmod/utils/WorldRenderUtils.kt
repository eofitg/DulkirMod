package dulkirmod.utils

import dulkirmod.DulkirMod.Companion.mc
import net.minecraft.client.gui.FontRenderer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.client.renderer.Tessellator
import net.minecraft.client.renderer.WorldRenderer
import net.minecraft.client.renderer.entity.RenderManager
import net.minecraft.client.renderer.vertex.DefaultVertexFormats
import net.minecraft.util.Vec3
import org.lwjgl.opengl.GL11


object WorldRenderUtils {
    private val tessellator: Tessellator
        get() = Tessellator.getInstance()
    private val worldRenderer: WorldRenderer
        get() = tessellator.worldRenderer
    private val renderManager: RenderManager
        get() = mc.renderManager
    private val fontRenderer: FontRenderer
        get() = mc.fontRendererObj

    fun renderString(
        location: Vec3,
        text: String,
        depthTest: Boolean = true,
        scale: Float = 1f,
        showDistance: Boolean = false,
        shadow: Boolean = false,
        renderBlackBox: Boolean = true,
    ) {
        if (!depthTest) {
            GL11.glDisable(GL11.GL_DEPTH_TEST)
            GL11.glDepthMask(false)
        }
        GlStateManager.pushMatrix()
        GlStateManager.enableBlend()
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0)
        GlStateManager.translate(
            location.xCoord - renderManager.viewerPosX,
            location.yCoord - renderManager.viewerPosY,
            location.zCoord - renderManager.viewerPosZ
        )
        GlStateManager.color(1f, 1f, 1f, 0.5f)
        GlStateManager.rotate(-renderManager.playerViewY, 0.0f, 1.0f, 0.0f)
        GlStateManager.rotate(renderManager.playerViewX, 1.0f, 0.0f, 0.0f)
        GlStateManager.scale(-scale / 25, -scale / 25, scale / 25)

        if (renderBlackBox) {
            val j = fontRenderer.getStringWidth(text) / 2
            GlStateManager.disableTexture2D()
            worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR)
            worldRenderer.pos(-j - 1.0, -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
            worldRenderer.pos(-j - 1.0, 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
            worldRenderer.pos(j + 1.0, 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
            worldRenderer.pos(j + 1.0, -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
            tessellator.draw()
            GlStateManager.enableTexture2D()
        }

        if (shadow) {
            fontRenderer.drawStringWithShadow(text, -fontRenderer.getStringWidth(text) / 2f, 0f, 0)
        } else {
            fontRenderer.drawString(text, -fontRenderer.getStringWidth(text) / 2, 0, 0)
        }

        // for waypoints
        if (showDistance) {
            val distance = "§e${mc.thePlayer.positionVector.distanceTo(location).toInt()}m"
            GlStateManager.translate(0.0, 8.66, 0.0)
            GlStateManager.scale(0.66, 0.66, 0.66)

            if (renderBlackBox) {
                val j = fontRenderer.getStringWidth(distance) / 2
                GlStateManager.disableTexture2D()
                worldRenderer.begin(7, DefaultVertexFormats.POSITION_COLOR)
                worldRenderer.pos(-j - 1.0, -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldRenderer.pos(-j - 1.0, 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldRenderer.pos(j + 1.0, 8.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                worldRenderer.pos(j + 1.0, -1.0, 0.0).color(0.0f, 0.0f, 0.0f, 0.25f).endVertex()
                tessellator.draw()
                GlStateManager.enableTexture2D()
            }

            if (shadow) {
                fontRenderer.drawStringWithShadow(distance, -fontRenderer.getStringWidth(distance) / 2f, 0f, 0)
            } else {
                fontRenderer.drawString(distance, -fontRenderer.getStringWidth(distance) / 2, 0, 0)
            }
        }

        GlStateManager.color(1f, 1f, 1f)
        GlStateManager.disableBlend()
        GlStateManager.popMatrix()
        if (!depthTest) {
            GL11.glEnable(GL11.GL_DEPTH_TEST)
            GL11.glDepthMask(true)
        }
    }

}