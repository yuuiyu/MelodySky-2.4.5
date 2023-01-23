//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Font;

import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import java.util.*;
import java.awt.*;

public class CFontRenderer extends CFont
{
    protected CFont.CharData[] boldChars;
    protected CFont.CharData[] italicChars;
    protected CFont.CharData[] boldItalicChars;
    private final int[] colorCode;
    private final String colorcodeIdentifiers = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ123456789";
    protected DynamicTexture texBold;
    protected DynamicTexture texItalic;
    protected DynamicTexture texItalicBold;
    
    public CFontRenderer(final Font font, final boolean antiAlias) {
        super(font, antiAlias);
        this.boldChars = new CFont.CharData[256];
        this.italicChars = new CFont.CharData[256];
        this.boldItalicChars = new CFont.CharData[256];
        this.colorCode = new int[32];
        this.setupMinecraftColorcodes();
        this.setupBoldItalicIDs();
    }
    
    public float drawStringWithShadow(final String text, final double x, final double y, final int color) {
        final float shadowWidth = this.drawString(text, x + 0.3, y + 0.3, color, true);
        return Math.max(shadowWidth, this.drawString(text, x, y, color, false));
    }
    
    public float drawString(final String text, final float x, final float y, final int color) {
        return this.drawString(text, x, y, color, false);
    }
    
    public float drawCenteredString(final String text, final float x, final float y, final int color) {
        return this.drawString(text, x - this.getStringWidth(text) / 2, y, color);
    }
    
    public float drawCenteredStringWithShadow(final String text, final float x, final float y, final int color) {
        return this.drawStringWithShadow(text, x - this.getStringWidth(text) / 2, y, color);
    }
    
    public float drawCenteredStringWithShadow(final String text, final double x, final double y, final int color) {
        return this.drawStringWithShadow(text, x - this.getStringWidth(text) / 2, y, color);
    }
    
    public float drawString(final String text, double x, double y, int color, final boolean shadow) {
        GlStateManager.resetColor();
        --x;
        if (text == null) {
            return 0.0f;
        }
        if (color == 553648127) {
            color = 16777215;
        }
        if ((color & 0xFC000000) == 0x0) {
            color |= 0xFF000000;
        }
        if (shadow) {
            color = ((color & 0xFCFCFC) >> 2 | (color & 0xFF000000));
        }
        CFont.CharData[] currentData = this.charData;
        final float alpha = (color >> 24 & 0xFF) / 255.0f;
        boolean bold = false;
        boolean italic = false;
        boolean strikethrough = false;
        boolean underline = false;
        final boolean render = true;
        x *= 2.0;
        y = (y - 3.0) * 2.0;
        if (render) {
            GlStateManager.resetColor();
            GL11.glPushMatrix();
            GlStateManager.scale(0.5, 0.5, 0.5);
            GlStateManager.enableBlend();
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
            final int size = text.length();
            GlStateManager.enableTexture2D();
            GlStateManager.bindTexture(this.tex.getGlTextureId());
            GL11.glBindTexture(3553, this.tex.getGlTextureId());
            for (int i = 0; i < size; ++i) {
                final char character = text.charAt(i);
                if (character == '§' && i < size) {
                    int colorIndex = 21;
                    try {
                        colorIndex = "0123456789abcdefklmnor".indexOf(text.charAt(i + 1));
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (colorIndex < 16) {
                        bold = false;
                        italic = false;
                        underline = false;
                        strikethrough = false;
                        GlStateManager.bindTexture(this.tex.getGlTextureId());
                        currentData = this.charData;
                        if (colorIndex < 0 || colorIndex > 15) {
                            colorIndex = 15;
                        }
                        if (shadow) {
                            colorIndex += 16;
                        }
                        final int colorcode = this.colorCode[colorIndex];
                        GlStateManager.color((colorcode >> 16 & 0xFF) / 255.0f, (colorcode >> 8 & 0xFF) / 255.0f, (colorcode & 0xFF) / 255.0f, alpha);
                    }
                    else if (colorIndex != 16) {
                        if (colorIndex == 17) {
                            bold = true;
                            if (italic) {
                                GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                                currentData = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.bindTexture(this.texBold.getGlTextureId());
                                currentData = this.boldChars;
                            }
                        }
                        else if (colorIndex == 18) {
                            strikethrough = true;
                        }
                        else if (colorIndex == 19) {
                            underline = true;
                        }
                        else if (colorIndex == 20) {
                            italic = true;
                            if (bold) {
                                GlStateManager.bindTexture(this.texItalicBold.getGlTextureId());
                                currentData = this.boldItalicChars;
                            }
                            else {
                                GlStateManager.bindTexture(this.texItalic.getGlTextureId());
                                currentData = this.italicChars;
                            }
                        }
                        else if (colorIndex == 21) {
                            bold = false;
                            italic = false;
                            underline = false;
                            strikethrough = false;
                            GlStateManager.color((color >> 16 & 0xFF) / 255.0f, (color >> 8 & 0xFF) / 255.0f, (color & 0xFF) / 255.0f, alpha);
                            GlStateManager.bindTexture(this.tex.getGlTextureId());
                            currentData = this.charData;
                        }
                    }
                    ++i;
                }
                else if (character < currentData.length && character >= '\0') {
                    GL11.glBegin(4);
                    this.drawChar(currentData, character, (float)x, (float)y);
                    GL11.glEnd();
                    if (strikethrough) {
                        this.drawLine(x, y + currentData[character].height / 2, x + currentData[character].width - 8.0, y + currentData[character].height / 2, 1.0f);
                    }
                    if (underline) {
                        this.drawLine(x, y + currentData[character].height - 2.0, x + currentData[character].width - 8.0, y + currentData[character].height - 2.0, 1.0f);
                    }
                    x += currentData[character].width - 8 + this.charOffset;
                }
            }
            GL11.glHint(3155, 4352);
            GL11.glPopMatrix();
        }
        GlStateManager.resetColor();
        return (float)x / 2.0f;
    }
    
    public int getStringWidth(final String text) {
        if (text == null) {
            return 0;
        }
        int width = 0;
        CFont.CharData[] currentData = this.charData;
        boolean bold = false;
        boolean italic = false;
        for (int size = text.length(), i = 0; i < size; ++i) {
            final char character = text.charAt(i);
            if (character == '§' && i < size) {
                final int colorIndex = "0123456789abcdefklmnor".indexOf(character);
                if (colorIndex < 16) {
                    bold = false;
                    italic = false;
                }
                else if (colorIndex == 17) {
                    bold = true;
                    currentData = (italic ? this.boldItalicChars : this.boldChars);
                }
                else if (colorIndex == 20) {
                    italic = true;
                    currentData = (bold ? this.boldItalicChars : this.italicChars);
                }
                else if (colorIndex == 21) {
                    bold = false;
                    italic = false;
                    currentData = this.charData;
                }
                ++i;
            }
            else if (character < currentData.length && character >= '\0') {
                width += currentData[character].width - 8 + this.charOffset;
            }
        }
        return width / 2;
    }
    
    public void setFont(final Font font) {
        super.setFont(font);
        this.setupBoldItalicIDs();
    }
    
    public void setAntiAlias(final boolean antiAlias) {
        super.setAntiAlias(antiAlias);
        this.setupBoldItalicIDs();
    }
    
    public void setFractionalMetrics(final boolean fractionalMetrics) {
        super.setFractionalMetrics(fractionalMetrics);
        this.setupBoldItalicIDs();
    }
    
    private void setupBoldItalicIDs() {
        this.texBold = this.setupTexture(this.font.deriveFont(1), this.antiAlias, this.fractionalMetrics, this.boldChars);
        this.texItalic = this.setupTexture(this.font.deriveFont(2), this.antiAlias, this.fractionalMetrics, this.italicChars);
    }
    
    private void drawLine(final double x, final double y, final double x1, final double y1, final float width) {
        GL11.glDisable(3553);
        GL11.glLineWidth(width);
        GL11.glBegin(1);
        GL11.glVertex2d(x, y);
        GL11.glVertex2d(x1, y1);
        GL11.glEnd();
        GL11.glEnable(3553);
    }
    
    public List<String> wrapWords(final String text, final double width) {
        final ArrayList<String> finalWords = new ArrayList<String>();
        if (this.getStringWidth(text) > width) {
            final String[] words = text.split(" ");
            String currentWord = "";
            int lastColorCode = 65535;
            for (final String word : words) {
                for (int i = 0; i < word.toCharArray().length; ++i) {
                    final char c = word.toCharArray()[i];
                    if (c == '§' && i < word.toCharArray().length - 1) {
                        lastColorCode = word.toCharArray()[i + 1];
                    }
                }
                if (this.getStringWidth(String.valueOf(currentWord) + word + " ") < width) {
                    currentWord = String.valueOf(currentWord) + word + " ";
                }
                else {
                    finalWords.add(currentWord);
                    currentWord = String.valueOf(167 + lastColorCode) + word + " ";
                }
            }
            if (currentWord.length() > 0) {
                if (this.getStringWidth(currentWord) < width) {
                    finalWords.add(String.valueOf(167 + lastColorCode) + currentWord + " ");
                    currentWord = "";
                }
                else {
                    for (final String s : this.formatString(currentWord, width)) {
                        finalWords.add(s);
                    }
                }
            }
        }
        else {
            finalWords.add(text);
        }
        return finalWords;
    }
    
    public List<String> formatString(final String string, final double width) {
        final ArrayList<String> finalWords = new ArrayList<String>();
        String currentWord = "";
        int lastColorCode = 65535;
        final char[] chars = string.toCharArray();
        for (int i = 0; i < chars.length; ++i) {
            final char c = chars[i];
            if (c == '§' && i < chars.length - 1) {
                lastColorCode = chars[i + 1];
            }
            if (this.getStringWidth(String.valueOf(currentWord) + c) < width) {
                currentWord = String.valueOf(currentWord) + c;
            }
            else {
                finalWords.add(currentWord);
                currentWord = String.valueOf(167 + lastColorCode) + String.valueOf(c);
            }
        }
        if (currentWord.length() > 0) {
            finalWords.add(currentWord);
        }
        return finalWords;
    }
    
    private void setupMinecraftColorcodes() {
        for (int index = 0; index < 32; ++index) {
            final int noClue = (index >> 3 & 0x1) * 85;
            int red = (index >> 2 & 0x1) * 170 + noClue;
            int green = (index >> 1 & 0x1) * 170 + noClue;
            int blue = (index >> 0 & 0x1) * 170 + noClue;
            if (index == 6) {
                red += 85;
            }
            if (index >= 16) {
                red /= 4;
                green /= 4;
                blue /= 4;
            }
            this.colorCode[index] = ((red & 0xFF) << 16 | (green & 0xFF) << 8 | (blue & 0xFF));
        }
    }
    
    public void drawOutlinedString(final String text, final float x, final float y, final int borderColor, final int color) {
        this.drawString(text, x - 0.5f, y, borderColor);
        this.drawString(text, x + 0.5f, y, borderColor);
        this.drawString(text, x, y - 0.5f, borderColor);
        this.drawString(text, x, y + 0.5f, borderColor);
        this.drawString(text, x, y, color);
    }
    
    public void drawCenterOutlinedString(final String text, final float x, final float y, final int borderColor, final int color) {
        this.drawString(text, x - this.getStringWidth(text) / 2 - 0.5f, y, borderColor);
        this.drawString(text, x - this.getStringWidth(text) / 2 + 0.5f, y, borderColor);
        this.drawString(text, x - this.getStringWidth(text) / 2, y - 0.5f, borderColor);
        this.drawString(text, x - this.getStringWidth(text) / 2, y + 0.5f, borderColor);
        this.drawString(text, x - this.getStringWidth(text) / 2, y, color);
    }
    
    public int drawStringWithShadow(String text, final float x, final float y, int color, final int alpha) {
        text = "§r" + text;
        float len = -1.0f;
        final String[] array;
        final String[] array2;
        final String[] split = array2 = (array = text.split("§"));
        for (String str : array2) {
            if (str.length() >= 1) {
                switch (str.charAt(0)) {
                    case '0': {
                        color = new Color(0, 0, 0).getRGB();
                        break;
                    }
                    case '1': {
                        color = new Color(0, 0, 170).getRGB();
                        break;
                    }
                    case '2': {
                        color = new Color(0, 170, 0).getRGB();
                        break;
                    }
                    case '3': {
                        color = new Color(0, 170, 170).getRGB();
                        break;
                    }
                    case '4': {
                        color = new Color(170, 0, 0).getRGB();
                        break;
                    }
                    case '5': {
                        color = new Color(170, 0, 170).getRGB();
                        break;
                    }
                    case '6': {
                        color = new Color(255, 170, 0).getRGB();
                        break;
                    }
                    case '7': {
                        color = new Color(170, 170, 170).getRGB();
                        break;
                    }
                    case '8': {
                        color = new Color(85, 85, 85).getRGB();
                        break;
                    }
                    case '9': {
                        color = new Color(85, 85, 255).getRGB();
                        break;
                    }
                    case 'a': {
                        color = new Color(85, 255, 85).getRGB();
                        break;
                    }
                    case 'b': {
                        color = new Color(85, 255, 255).getRGB();
                        break;
                    }
                    case 'c': {
                        color = new Color(255, 85, 85).getRGB();
                        break;
                    }
                    case 'd': {
                        color = new Color(255, 85, 255).getRGB();
                        break;
                    }
                    case 'e': {
                        color = new Color(255, 255, 85).getRGB();
                        break;
                    }
                    case 'f': {
                        color = new Color(255, 255, 255).getRGB();
                        break;
                    }
                }
                final Color col = new Color(color);
                str = str.substring(1, str.length());
                final int Shadowcolor = (color & 0xFCFCFC) >> 2 | (color & 0xFF000000);
                this.drawString(str, x + len + 0.5f, y + 0.5f, this.getColor(0, 0, 0, 80));
                this.drawString(str, x + len, y, this.getColor(col.getRed(), col.getGreen(), col.getBlue(), alpha));
                len += this.getStringWidth(str) + 1;
            }
        }
        return (int)len;
    }
    
    public int drawStringWithShadow(String text, final float x, final float y, int color, final int colorshadow, final int shift, final int alpha) {
        text = "§r" + text;
        float len = -1.0f;
        final String[] array;
        final String[] array2;
        final String[] split = array2 = (array = text.split("§"));
        for (String str : array2) {
            if (str.length() >= 1) {
                switch (str.charAt(0)) {
                    case '0': {
                        color = new Color(0, 0, 0).getRGB();
                        break;
                    }
                    case '1': {
                        color = new Color(0, 0, 170).getRGB();
                        break;
                    }
                    case '2': {
                        color = new Color(0, 170, 0).getRGB();
                        break;
                    }
                    case '3': {
                        color = new Color(0, 170, 170).getRGB();
                        break;
                    }
                    case '4': {
                        color = new Color(170, 0, 0).getRGB();
                        break;
                    }
                    case '5': {
                        color = new Color(170, 0, 170).getRGB();
                        break;
                    }
                    case '6': {
                        color = new Color(255, 170, 0).getRGB();
                        break;
                    }
                    case '7': {
                        color = new Color(170, 170, 170).getRGB();
                        break;
                    }
                    case '8': {
                        color = new Color(85, 85, 85).getRGB();
                        break;
                    }
                    case '9': {
                        color = new Color(85, 85, 255).getRGB();
                        break;
                    }
                    case 'a': {
                        color = new Color(85, 255, 85).getRGB();
                        break;
                    }
                    case 'b': {
                        color = new Color(85, 255, 255).getRGB();
                        break;
                    }
                    case 'c': {
                        color = new Color(255, 85, 85).getRGB();
                        break;
                    }
                    case 'd': {
                        color = new Color(255, 85, 255).getRGB();
                        break;
                    }
                    case 'e': {
                        color = new Color(255, 255, 85).getRGB();
                        break;
                    }
                    case 'f': {
                        color = new Color(255, 255, 255).getRGB();
                        break;
                    }
                }
                final Color col = new Color(color);
                str = str.substring(1, str.length());
                final int Shadowcolor = (color & 0xFCFCFC) >> 2 | (color & 0xFF000000);
                this.drawString(str, x + len + shift, y + shift, colorshadow);
                this.drawString(str, x + len, y, this.getColor(col.getRed(), col.getGreen(), col.getBlue(), alpha));
                len += this.getStringWidth(str) + 1;
            }
        }
        return (int)len;
    }
    
    public int getColor(final int red, final int green, final int blue, final int alpha) {
        final byte color = 0;
        int color2 = 0x0 | alpha << 24;
        color2 |= red << 16;
        color2 |= green << 8;
        color2 |= blue;
        return color2;
    }
    
    public String trimStringToWidthPassword(String text, final int width, final boolean custom) {
        text = text.replaceAll("\u00c3\u201a", "");
        return "";
    }
    
    public String trimStringToWidth(String text, final int width, final boolean reverse) {
        text = text.replaceAll("\u00c3\u201a", "");
        return "";
    }
    
    public String trimStringToWidth(String text, final int width) {
        text = text.replaceAll("\u00c3\u201a", "");
        return "";
    }
    
    public int drawPassword(final String text, final double x2, final float y2, final int color) {
        return (int)this.drawString(text.replaceAll(".", "."), x2, y2, color, false);
    }
    
    public double getPasswordWidth(String text) {
        text = text.replaceAll("\u00c3\u201a", "");
        return 0.0;
    }
}
