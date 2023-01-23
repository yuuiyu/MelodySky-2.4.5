//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Font;

import net.minecraft.client.gui.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.awt.*;
import org.newdawn.slick.font.effects.*;
import org.newdawn.slick.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;

public class UnicodeFontRender extends FontRenderer
{
    private final UnicodeFont font;
    public HashMap<String, Integer> widthMap;
    public HashMap<String, Integer> heightMap;
    
    public UnicodeFontRender(final Font awtFont, final boolean b) {
        super(Minecraft.getMinecraft().gameSettings, new ResourceLocation("textures/font/ascii.png"), Minecraft.getMinecraft().getTextureManager(), false);
        final int p_addGlyphs_1_ = -1;
        final int p_addGlyphs_2_ = -1;
        this.widthMap = new HashMap<String, Integer>();
        this.heightMap = new HashMap<String, Integer>();
        (this.font = new UnicodeFont(awtFont)).addAsciiGlyphs();
        this.font.getEffects().add(new ColorEffect(Color.WHITE));
        if (p_addGlyphs_2_ > -1 && p_addGlyphs_1_ > -1) {
            this.font.addGlyphs(p_addGlyphs_1_, p_addGlyphs_2_);
        }
        if (b) {
            this.font.addGlyphs(0, 65535);
        }
        try {
            this.font.loadGlyphs();
        }
        catch (SlickException class1245) {
            throw new RuntimeException((Throwable)class1245);
        }
        this.FONT_HEIGHT = this.font.getHeight("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789") / 2;
    }
    
    public int drawStringWithColor(String text, final float x, final float y, int color, final int alpha) {
        text = "§r" + text;
        float len = -1.0f;
        for (String str : text.split("§")) {
            if (str.length() >= 1) {
                switch (str.charAt(0)) {
                    case '0': {
                        color = new Color(0, 0, 0, alpha).getRGB();
                        break;
                    }
                    case '1': {
                        color = new Color(0, 0, 170, alpha).getRGB();
                        break;
                    }
                    case '2': {
                        color = new Color(0, 170, 0, alpha).getRGB();
                        break;
                    }
                    case '3': {
                        color = new Color(0, 170, 170, alpha).getRGB();
                        break;
                    }
                    case '4': {
                        color = new Color(170, 0, 0, alpha).getRGB();
                        break;
                    }
                    case '5': {
                        color = new Color(170, 0, 170, alpha).getRGB();
                        break;
                    }
                    case '6': {
                        color = new Color(255, 170, 0, alpha).getRGB();
                        break;
                    }
                    case '7': {
                        color = new Color(170, 170, 170, alpha).getRGB();
                        break;
                    }
                    case '8': {
                        color = new Color(85, 85, 85, alpha).getRGB();
                        break;
                    }
                    case '9': {
                        color = new Color(85, 85, 255, alpha).getRGB();
                        break;
                    }
                    case 'a': {
                        color = new Color(85, 255, 85, alpha).getRGB();
                        break;
                    }
                    case 'b': {
                        color = new Color(85, 255, 255, alpha).getRGB();
                        break;
                    }
                    case 'c': {
                        color = new Color(255, 85, 85, alpha).getRGB();
                        break;
                    }
                    case 'd': {
                        color = new Color(255, 85, 255, alpha).getRGB();
                        break;
                    }
                    case 'e': {
                        color = new Color(255, 255, 85, alpha).getRGB();
                        break;
                    }
                    case 'f': {
                        color = new Color(255, 255, 255, alpha).getRGB();
                        break;
                    }
                    case 'r': {
                        color = new Color(255, 255, 255, alpha).getRGB();
                        break;
                    }
                }
                final Color col = new Color(color);
                str = str.substring(1, str.length());
                this.drawString(str, x + len, y, this.getColor(col.getRed(), col.getGreen(), col.getBlue(), alpha));
                len += this.getStringWidth(str) + 1;
            }
        }
        return (int)len;
    }
    
    public int getColor(final int red, final int green, final int blue, final int alpha) {
        final byte color = 0;
        int color2 = color | alpha << 24;
        color2 |= red << 16;
        color2 |= green << 8;
        color2 |= blue;
        return color2;
    }
    
    public int drawString(final String string, float x, float y, final int color) {
        if (string == null) {
            return 0;
        }
        GL11.glPushMatrix();
        GL11.glScaled(0.5, 0.5, 0.5);
        final boolean blend = GL11.glIsEnabled(3042);
        final boolean lighting = GL11.glIsEnabled(2896);
        final boolean texture = GL11.glIsEnabled(3553);
        GL11.glEnable(3042);
        if (lighting) {
            GL11.glDisable(2896);
        }
        if (texture) {
            GL11.glDisable(3553);
        }
        this.font.drawString(x *= 2.0f, y *= 2.0f, string, new org.newdawn.slick.Color(color));
        if (texture) {
            GL11.glEnable(3553);
        }
        if (lighting) {
            GL11.glEnable(2896);
        }
        if (!blend) {
            GL11.glDisable(3042);
        }
        GlStateManager.color(0.0f, 0.0f, 0.0f);
        GL11.glPopMatrix();
        GlStateManager.bindTexture((int)x);
        return (int)x;
    }
    
    public int drawStringWithShadow(final String text, final float x, final float y, final int color) {
        this.drawString(text, x + 1.0f, y + 1.0f, -16777216);
        return this.drawString(text, x, y, color);
    }
    
    public int getCharWidth(final char c) {
        return this.getStringWidth(Character.toString(c));
    }
    
    public int getStringWidth(final String string) {
        return this.font.getWidth(string) / 2;
    }
    
    public int getStringHeight(final String string) {
        return this.font.getHeight(string) / 2;
    }
    
    public void drawCenteredString(final String text, final float x, final float y, final int color) {
        this.drawString(text, x - this.getStringWidth(text) / 2, y, color);
    }
    
    public int getHeight() {
        return this.getStringHeight("WECabc");
    }
}
