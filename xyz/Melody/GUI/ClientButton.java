//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI;

import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.Utils.render.*;
import org.lwjgl.input.*;
import org.lwjgl.opengl.*;
import xyz.Melody.GUI.Font.*;

public final class ClientButton extends GuiButton
{
    private ResourceLocation image;
    private ResourceLocation hoveredImage;
    private float imgScale;
    private float imgXShift;
    private float imgYShift;
    private int r1;
    private int g1;
    private int b1;
    private int alpha;
    private int alpha1;
    private int whiteAlpha;
    private float buttonZ;
    
    public ClientButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final Color color) {
        super(buttonId, x, y, 10, 12, buttonText);
        this.whiteAlpha = 0;
        this.buttonZ = 0.0f;
        this.width = widthIn;
        this.height = heightIn;
        this.alpha = color.getAlpha();
        this.image = null;
        this.hoveredImage = null;
        this.r1 = color.getRed();
        this.g1 = color.getGreen();
        this.b1 = color.getBlue();
        this.alpha1 = color.getAlpha();
        this.imgScale = this.height / 2.6f;
    }
    
    public ClientButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final ResourceLocation image, final Color color) {
        super(buttonId, x, y, 10, 12, buttonText);
        this.whiteAlpha = 0;
        this.buttonZ = 0.0f;
        this.width = widthIn;
        this.height = heightIn;
        this.alpha = color.getAlpha();
        this.image = image;
        this.hoveredImage = null;
        this.r1 = color.getRed();
        this.g1 = color.getGreen();
        this.b1 = color.getBlue();
        this.alpha1 = color.getAlpha();
        this.imgScale = this.height / 2.6f;
        this.imgXShift = 0.0f;
        this.imgYShift = 0.0f;
    }
    
    public ClientButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final ResourceLocation image, final float imgXShift, final float imgYShift, final float imgScale, final Color color) {
        super(buttonId, x, y, 10, 12, buttonText);
        this.whiteAlpha = 0;
        this.buttonZ = 0.0f;
        this.width = widthIn;
        this.height = heightIn;
        this.alpha = color.getAlpha();
        this.image = image;
        this.hoveredImage = null;
        this.r1 = color.getRed();
        this.g1 = color.getGreen();
        this.b1 = color.getBlue();
        this.alpha1 = color.getAlpha();
        this.imgScale = imgScale;
        this.imgXShift = imgXShift;
        this.imgYShift = imgYShift;
    }
    
    public ClientButton(final int buttonId, final int x, final int y, final int widthIn, final int heightIn, final String buttonText, final ResourceLocation image, final ResourceLocation hoveredImage, final float imgXShift, final float imgYShift, final float imgScale, final Color color) {
        super(buttonId, x, y, 10, 12, buttonText);
        this.whiteAlpha = 0;
        this.buttonZ = 0.0f;
        this.width = widthIn;
        this.height = heightIn;
        this.alpha = color.getAlpha();
        this.image = image;
        this.hoveredImage = hoveredImage;
        this.r1 = color.getRed();
        this.g1 = color.getGreen();
        this.b1 = color.getBlue();
        this.alpha1 = color.getAlpha();
        this.imgScale = imgScale;
        this.imgXShift = imgXShift;
        this.imgYShift = imgYShift;
    }
    
    public void drawButton(final Minecraft mc, final int mouseX, final int mouseY) {
        final CFontRenderer font = FontLoaders.CNMD18;
        GlStateManager.resetColor();
        GlStateManager.enableBlend();
        GlStateManager.enableAlpha();
        if (!this.enabled) {
            this.whiteAlpha = 0;
            font.drawCenteredString(this.displayString, (float)(this.xPosition + this.width / 2), this.yPosition + this.height / 2 - 2.7f, -1);
            RenderUtil.drawFastRoundedRect(this.xPosition - this.buttonZ, this.yPosition - this.buttonZ * 0.8f, this.xPosition + this.width + this.buttonZ, this.yPosition + this.height + this.buttonZ * 0.8f, 2.0f, new Color(20, 20, 20, 130).getRGB());
            return;
        }
        this.hovered = (mouseX >= this.xPosition && mouseY >= this.yPosition && mouseX < this.xPosition + this.width && mouseY < this.yPosition + this.height);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.blendFunc(770, 771);
        if (this.hovered) {
            if (this.alpha1 > 15) {
                this.alpha1 -= 15;
            }
            if (this.whiteAlpha < 130) {
                this.whiteAlpha += 15;
            }
            if (this.buttonZ < 1.0f) {
                this.buttonZ += 0.2f;
            }
        }
        if (!this.hovered) {
            if (this.alpha1 <= this.alpha) {
                this.alpha1 += 15;
            }
            if (this.whiteAlpha > 0) {
                this.whiteAlpha -= 15;
            }
            if (this.buttonZ > 0.0f) {
                this.buttonZ -= 0.2f;
            }
        }
        if (this.hovered && Mouse.isButtonDown(0)) {
            RenderUtil.drawFastRoundedRect(this.xPosition - this.buttonZ, this.yPosition - this.buttonZ * 0.8f, this.xPosition + this.width + this.buttonZ, this.yPosition + this.height + this.buttonZ * 0.8f, 2.0f, new Color(this.r1, this.g1, this.b1, this.alpha1).getRGB());
            RenderUtil.drawFastRoundedRect(this.xPosition - this.buttonZ, this.yPosition - this.buttonZ * 0.8f, this.xPosition + this.width + this.buttonZ, this.yPosition + this.height + this.buttonZ * 0.8f, 2.0f, new Color(140, 140, 140, this.whiteAlpha).getRGB());
        }
        else {
            if (this.alpha != 0) {
                RenderUtil.drawFastRoundedRect(this.xPosition - this.buttonZ, this.yPosition - this.buttonZ * 0.8f, this.xPosition + this.width + this.buttonZ, this.yPosition + this.height + this.buttonZ * 0.8f, 2.0f, new Color(this.r1, this.g1, this.b1, this.alpha1).getRGB());
            }
            RenderUtil.drawFastRoundedRect(this.xPosition - this.buttonZ, this.yPosition - this.buttonZ * 0.8f, this.xPosition + this.width + this.buttonZ, this.yPosition + this.height + this.buttonZ * 0.8f, 2.0f, new Color(222, 222, 222, this.whiteAlpha).getRGB());
        }
        if (this.image != null) {
            RenderUtil.drawImage(this.image, this.xPosition + this.height / 3.0f + this.imgXShift, this.yPosition + this.height / 3.0f + this.imgYShift, this.imgScale, this.imgScale);
        }
        if (this.hoveredImage != null && this.hovered) {
            RenderUtil.drawImage(this.hoveredImage, this.xPosition + this.height / 3.0f + this.imgXShift, this.yPosition + this.height / 3.0f + this.imgYShift, this.imgScale, this.imgScale);
        }
        GL11.glColor3f(2.55f, 2.55f, 2.55f);
        this.mouseDragged(mc, mouseX, mouseY);
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glScaled(1.0, 1.0, 1.0);
        font.drawCenteredString(this.displayString, (float)(this.xPosition + this.width / 2), this.yPosition + this.height / 2 - 2.7f, (this.whiteAlpha > 60) ? Color.DARK_GRAY.getRGB() : -1);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
}
