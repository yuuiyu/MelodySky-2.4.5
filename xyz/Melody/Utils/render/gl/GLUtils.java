//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render.gl;

import net.minecraft.client.*;
import java.nio.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.shader.*;
import xyz.Melody.Utils.math.*;
import org.lwjgl.util.glu.*;
import org.lwjgl.opengl.*;
import org.lwjgl.input.*;
import org.lwjgl.*;

public final class GLUtils
{
    private static Minecraft mc;
    public static final FloatBuffer MODELVIEW;
    public static final FloatBuffer PROJECTION;
    public static final IntBuffer VIEWPORT;
    public static final FloatBuffer TO_SCREEN_BUFFER;
    public static final FloatBuffer TO_WORLD_BUFFER;
    
    private GLUtils() {
    }
    
    public static void init() {
    }
    
    public static float[] getColor(final int hex) {
        return new float[] { (hex >> 16 & 0xFF) / 255.0f, (hex >> 8 & 0xFF) / 255.0f, (hex & 0xFF) / 255.0f, (hex >> 24 & 0xFF) / 255.0f };
    }
    
    public static void glColor(final int hex) {
        final float[] color = getColor(hex);
        GlStateManager.color(color[0], color[1], color[2], color[3]);
    }
    
    public static Framebuffer createFrameBuffer(final Framebuffer framebuffer) {
        if (framebuffer == null || framebuffer.framebufferWidth != GLUtils.mc.displayWidth || framebuffer.framebufferHeight != GLUtils.mc.displayHeight) {
            if (framebuffer != null) {
                framebuffer.deleteFramebuffer();
            }
            return new Framebuffer(GLUtils.mc.displayWidth, GLUtils.mc.displayHeight, true);
        }
        return framebuffer;
    }
    
    public static void bindTexture(final int texture) {
        GL11.glBindTexture(3553, texture);
    }
    
    public static void rotateX(final float angle, final double x, final double y, final double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-x, -y, -z);
    }
    
    public static void rotateY(final float angle, final double x, final double y, final double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(-x, -y, -z);
    }
    
    public static void rotateZ(final float angle, final double x, final double y, final double z) {
        GlStateManager.translate(x, y, z);
        GlStateManager.rotate(angle, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-x, -y, -z);
    }
    
    public static Vec3f toScreen(final Vec3f pos) {
        return toScreen(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public static Vec3f toScreen(final double x, final double y, final double z) {
        final boolean result = GLU.gluProject((float)x, (float)y, (float)z, GLUtils.MODELVIEW, GLUtils.PROJECTION, GLUtils.VIEWPORT, (FloatBuffer)GLUtils.TO_SCREEN_BUFFER.clear());
        if (result) {
            return new Vec3f((double)GLUtils.TO_SCREEN_BUFFER.get(0), (double)(Display.getHeight() - GLUtils.TO_SCREEN_BUFFER.get(1)), (double)GLUtils.TO_SCREEN_BUFFER.get(2));
        }
        return null;
    }
    
    public static Vec3f toWorld(final Vec3f pos) {
        return toWorld(pos.getX(), pos.getY(), pos.getZ());
    }
    
    public static Vec3f toWorld(final double x, final double y, final double z) {
        final boolean result = GLU.gluUnProject((float)x, (float)y, (float)z, GLUtils.MODELVIEW, GLUtils.PROJECTION, GLUtils.VIEWPORT, (FloatBuffer)GLUtils.TO_WORLD_BUFFER.clear());
        if (result) {
            return new Vec3f((double)GLUtils.TO_WORLD_BUFFER.get(0), (double)GLUtils.TO_WORLD_BUFFER.get(1), (double)GLUtils.TO_WORLD_BUFFER.get(2));
        }
        return null;
    }
    
    public static FloatBuffer getModelview() {
        return GLUtils.MODELVIEW;
    }
    
    public static FloatBuffer getProjection() {
        return GLUtils.PROJECTION;
    }
    
    public static IntBuffer getViewport() {
        return GLUtils.VIEWPORT;
    }
    
    public static int getMouseX() {
        return Mouse.getX() * getScreenWidth() / Minecraft.getMinecraft().displayWidth;
    }
    
    public static int getMouseY() {
        return getScreenHeight() - Mouse.getY() * getScreenHeight() / Minecraft.getMinecraft().displayWidth - 1;
    }
    
    public static int getScreenWidth() {
        return Minecraft.getMinecraft().displayWidth / getScaleFactor();
    }
    
    public static int getScreenHeight() {
        return Minecraft.getMinecraft().displayHeight / getScaleFactor();
    }
    
    public static int getScaleFactor() {
        int scaleFactor = 1;
        final boolean isUnicode = Minecraft.getMinecraft().isUnicode();
        int guiScale = Minecraft.getMinecraft().gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (scaleFactor < guiScale && Minecraft.getMinecraft().displayWidth / (scaleFactor + 1) >= 320 && Minecraft.getMinecraft().displayHeight / (scaleFactor + 1) >= 240) {
            ++scaleFactor;
        }
        if (isUnicode && scaleFactor % 2 != 0 && scaleFactor != 1) {
            --scaleFactor;
        }
        return scaleFactor;
    }
    
    static {
        GLUtils.mc = Minecraft.getMinecraft();
        MODELVIEW = BufferUtils.createFloatBuffer(16);
        PROJECTION = BufferUtils.createFloatBuffer(16);
        VIEWPORT = BufferUtils.createIntBuffer(16);
        TO_SCREEN_BUFFER = BufferUtils.createFloatBuffer(3);
        TO_WORLD_BUFFER = BufferUtils.createFloatBuffer(3);
    }
}
