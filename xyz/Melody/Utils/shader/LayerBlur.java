//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.shader;

import net.minecraft.client.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.texture.*;
import java.awt.*;
import java.nio.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.injection.mixins.client.*;

public class LayerBlur
{
    private Minecraft mc;
    private TimerUtil timer;
    private int colorTop;
    private int colorTopRight;
    private int colorBottom;
    private int colorBottomRight;
    private int colorNotification;
    private int colorNotificationBottom;
    private int tRed;
    private int tGreen;
    private int tBlue;
    private int bRed;
    private int bGreen;
    private int bBlue;
    public int lasttRed;
    public int lasttGreen;
    public int lasttBlue;
    public int lastbRed;
    public int lastbGreen;
    public int lastbBlue;
    
    public LayerBlur() {
        this.mc = Minecraft.getMinecraft();
        this.timer = new TimerUtil();
        this.colorNotification = 0;
        this.colorNotificationBottom = 0;
    }
    
    public void blurArea(final float x, final float y, final float x1, final float y1) {
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        final ScaledResolution sr = new ScaledResolution(this.mc);
        this.lasttRed = this.tRed;
        this.lasttGreen = this.tGreen;
        this.lasttBlue = this.tBlue;
        this.lastbRed = this.bRed;
        this.lastbGreen = this.bGreen;
        this.lastbBlue = this.bBlue;
        if (this.timer.hasReached(50.0)) {
            int width = 0;
            int height = 0;
            IntBuffer pixelBuffer = null;
            int[] pixelValues = null;
            if (OpenGlHelper.isFramebufferEnabled()) {
                width = (int)x1;
                height = (int)y1;
            }
            final int l = width * height;
            if (pixelBuffer == null || pixelBuffer.capacity() < l) {
                pixelBuffer = BufferUtils.createIntBuffer(l);
                pixelValues = new int[l];
            }
            GL11.glPixelStorei(3333, 1);
            GL11.glPixelStorei(3317, 1);
            pixelBuffer.clear();
            GL11.glReadPixels((int)x, (int)y, width, height, 32993, 33639, pixelBuffer);
            pixelBuffer.get(pixelValues);
            TextureUtil.processPixelValues(pixelValues, width, height);
            final int wiv = (int)((x + (x1 - x)) / (x1 - x) * 1.5);
            this.colorTop = pixelValues[1 * sr.getScaleFactor() * width + height * wiv + height / 2];
            this.colorBottom = pixelValues[(int)((1.0f + (y1 - y)) * sr.getScaleFactor() * width + height * wiv + height / 2)];
            final Color top = ColorUtils.blend(ColorUtils.colorFromInt(this.colorTop), ColorUtils.colorFromInt(this.colorTopRight));
            final Color bottom = ColorUtils.blend(ColorUtils.colorFromInt(this.colorBottom), ColorUtils.colorFromInt(this.colorBottomRight));
            this.tRed += (int)((top.getRed() - this.tRed) / 5 + 0.1);
            this.tGreen += (int)((top.getGreen() - this.tGreen) / 5 + 0.1);
            this.tBlue += (int)((top.getBlue() - this.tBlue) / 5 + 0.1);
            this.bRed += (int)((bottom.getRed() - this.bRed) / 5 + 0.1);
            this.bGreen += (int)((bottom.getGreen() - this.bGreen) / 5 + 0.1);
            this.bBlue += (int)((bottom.getBlue() - this.bBlue) / 5 + 0.1);
            this.tRed = Math.min(this.tRed, 255);
            this.tGreen = Math.min(this.tGreen, 255);
            this.tBlue = Math.min(this.tBlue, 255);
            this.tRed = Math.max(this.tRed, 0);
            this.tGreen = Math.max(this.tGreen, 0);
            this.tBlue = Math.max(this.tBlue, 0);
            this.bRed = Math.min(this.bRed, 255);
            this.bGreen = Math.min(this.bGreen, 255);
            this.bBlue = Math.min(this.bBlue, 255);
            this.bRed = Math.max(this.bRed, 0);
            this.bGreen = Math.max(this.bGreen, 0);
            this.bBlue = Math.max(this.bBlue, 0);
            this.timer.reset();
        }
        final int tR = this.smoothAnimation(this.tRed, this.lasttRed);
        final int tG = this.smoothAnimation(this.tGreen, this.lasttGreen);
        final int tB = this.smoothAnimation(this.tBlue, this.lasttBlue);
        final int bR = this.smoothAnimation(this.bRed, this.lastbRed);
        final int bG = this.smoothAnimation(this.bGreen, this.lastbGreen);
        final int bB = this.smoothAnimation(this.bBlue, this.lastbBlue);
        final Color tC = ColorUtils.lighter(new Color(tR, tG, tB, 50), 1.0);
        final Color bC = ColorUtils.lighter(new Color(bR, bG, bB, 50), 1.0);
        drawGradientRect(x, y, x1, y1, tC.getRGB(), bC.getRGB());
        GlStateManager.popMatrix();
        GlStateManager.resetColor();
    }
    
    public static void drawGradientRect(final float x, final float y, final float x1, final float y1, final int topColor, final int bottomColor) {
        RenderUtil.enableGL2D();
        GL11.glShadeModel(7425);
        GL11.glBegin(7);
        RenderUtil.glColor(bottomColor);
        GL11.glVertex2f(x, y1);
        GL11.glVertex2f(x1, y1);
        RenderUtil.glColor(topColor);
        GL11.glVertex2f(x1, y);
        GL11.glVertex2f(x, y);
        GL11.glEnd();
        GL11.glShadeModel(7424);
        RenderUtil.disableGL2D();
    }
    
    private int smoothAnimation(final double current, final double last) {
        return (int)(current * ((MCA)this.mc).getTimer().renderPartialTicks + last * (1.0f - ((MCA)this.mc).getTimer().renderPartialTicks));
    }
}
