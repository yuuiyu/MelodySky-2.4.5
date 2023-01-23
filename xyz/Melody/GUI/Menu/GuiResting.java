//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.Utils.*;
import xyz.Melody.GUI.Particles.*;
import xyz.Melody.Utils.shader.*;
import java.util.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import xyz.Melody.GUI.Font.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class GuiResting extends GuiScreen
{
    public boolean shouldMainMenu;
    private Opacity opacity;
    private TimerUtil timer;
    private ParticleUtils particle;
    private int textAlpha;
    private double Anitext;
    private GaussianBlur gb;
    
    public GuiResting() {
        this.shouldMainMenu = false;
        this.opacity = new Opacity(10);
        this.timer = new TimerUtil();
        this.gb = new GaussianBlur();
        this.particle = new ParticleUtils();
        this.textAlpha = 0;
        this.Anitext = 0.0;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(11);
        final int min = c.get(12);
        final int sec = c.get(13);
        final String time = hour + " : " + min + " : " + sec;
        final ScaledResolution sr = new ScaledResolution(this.mc);
        final CFontRenderer titleFont = FontLoaders.CNMD35;
        final CFontRenderer timeFont = FontLoaders.NMSL28;
        this.drawDefaultBackground();
        this.gb.renderBlur(this.opacity.getOpacity());
        this.opacity.interp(140.0f, 5);
        if (this.opacity.getOpacity() == 140.0f) {
            if (this.shouldMainMenu) {
                if (this.textAlpha >= 16) {
                    this.textAlpha -= 16;
                    this.timer.reset();
                }
                if (this.textAlpha <= 16) {
                    this.textAlpha = 6;
                    if (this.timer.hasReached(300.0)) {
                        this.mc.displayGuiScreen((GuiScreen)new MainMenu((int)this.opacity.getOpacity()));
                    }
                }
            }
            else {
                if (this.textAlpha < 170) {
                    this.textAlpha += 14;
                }
                if (this.textAlpha >= 170) {
                    this.textAlpha = 170;
                }
            }
        }
        else {
            this.textAlpha = 6;
        }
        ParticleUtils.drawParticles(mouseX, mouseY);
        titleFont.drawCenteredString("Click or Tap the Keyboard to Continue.", sr.getScaledWidth() / 2.0f, sr.getScaledHeight() / 2.0f - 15.0f - (float)this.Anitext, new Color(255, 255, 255, this.textAlpha).getRGB());
        timeFont.drawCenteredString(time, sr.getScaledWidth() / 2.0f, sr.getScaledHeight() / 2.0f + 10.0f - (float)this.Anitext, new Color(180, 180, 180, this.textAlpha).getRGB());
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.shouldMainMenu = true;
    }
    
    public void handleKeyboardInput() throws IOException {
        this.shouldMainMenu = true;
        super.handleKeyboardInput();
    }
    
    public void drawDefaultBackground() {
        BackgroundShader.BACKGROUND_SHADER.startShader();
        final Tessellator instance = Tessellator.getInstance();
        final WorldRenderer worldRenderer = instance.getWorldRenderer();
        worldRenderer.begin(7, DefaultVertexFormats.POSITION);
        worldRenderer.pos(0.0, (double)this.height, 0.0).endVertex();
        worldRenderer.pos((double)this.width, (double)this.height, 0.0).endVertex();
        worldRenderer.pos((double)this.width, 0.0, 0.0).endVertex();
        worldRenderer.pos(0.0, 0.0, 0.0).endVertex();
        instance.draw();
        BackgroundShader.BACKGROUND_SHADER.stopShader();
    }
    
    public void onGuiClosed() {
        this.mc.entityRenderer.switchUseShader();
    }
}
