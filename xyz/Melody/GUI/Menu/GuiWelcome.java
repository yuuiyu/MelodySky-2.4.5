//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import net.minecraft.client.gui.*;
import xyz.Melody.Utils.*;
import xyz.Melody.Utils.shader.*;
import xyz.Melody.GUI.Particles.*;
import xyz.Melody.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class GuiWelcome extends GuiScreen
{
    private int shabiAlpha;
    private int alpha;
    private int contentAlpha;
    private float titleY;
    private int enjoyAlpha;
    private boolean shouldMainMenu;
    private TimerUtil timer;
    private TimerUtil timer2;
    private TimerUtil timer3;
    private int continueAlpha;
    private GaussianBlur gblur;
    
    public GuiWelcome() {
        this.shabiAlpha = 0;
        this.alpha = 0;
        this.contentAlpha = 0;
        this.titleY = 0.0f;
        this.enjoyAlpha = 0;
        this.timer = new TimerUtil();
        this.timer2 = new TimerUtil();
        this.timer3 = new TimerUtil();
        this.continueAlpha = 0;
        this.gblur = new GaussianBlur();
    }
    
    public void initGui() {
        this.shouldMainMenu = false;
        this.alpha = 0;
        this.titleY = 0.0f;
        this.contentAlpha = 0;
        this.continueAlpha = 0;
        this.enjoyAlpha = 0;
        this.shabiAlpha = 0;
        this.timer.reset();
        this.timer2.reset();
        super.initGui();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final CFontRenderer font = FontLoaders.CNMD35;
        final CFontRenderer titleFont = FontLoaders.CNMD45;
        final CFontRenderer contentFont = FontLoaders.NMSL22;
        final CFontRenderer continueFont = FontLoaders.CNMD30;
        this.drawDefaultBackground();
        this.gblur.renderBlur(140.0f);
        ParticleUtils.drawParticles(mouseX, mouseY);
        if (Client.firstLaunch) {
            if (this.alpha < 210) {
                this.alpha += 3;
            }
            if (this.alpha >= 210 && this.titleY < this.height / 3) {
                this.titleY += this.height / 100;
            }
            if (this.titleY >= this.height / 3 && this.contentAlpha < 210) {
                this.contentAlpha += 7;
            }
            titleFont.drawCenteredString("Melody Skyblock", this.width / 2.0f, this.height / 2.0f - 3.0f - this.titleY, new Color(255, 255, 255, this.alpha).getRGB());
            final float tipsAppendY = 25.0f;
            if (this.contentAlpha > 0) {
                contentFont.drawCenteredString("What is MelodySky?   This is a Mod that improves The Quality of Life of Hypixel Skyblock (QOL Mod).", this.width / 2.0f, this.height / 3.0f, new Color(255, 255, 255, this.contentAlpha).getRGB());
                contentFont.drawCenteredString("What Would This Offer?   Auto Fishing, Auto Experiment Table. Auto Terminals, Livid Finder. Client-Side Name Changing, Custom Rank.", this.width / 2.0f, this.height / 3.0f + 25.0f, new Color(255, 255, 255, this.contentAlpha).getRGB());
                contentFont.drawCenteredString("Mithril Nuker, Hardstone Nuker, Powder Chest Macro, Show Lowes Bin Data, Show Dungeon Chest Profit. And Client IRC Chatting.", this.width / 2.0f, this.height / 3.0f + 50.0f, new Color(255, 255, 255, this.contentAlpha).getRGB());
                contentFont.drawString("Tip 1 - Type '.bind clickgui rshift' to Set the Binding of Click Gui to Right Shift.", 280.0f, this.height / 3.0f + 50.0f + tipsAppendY, new Color(249, 205, 173, this.contentAlpha).getRGB());
                contentFont.drawString("Tip 2 - In Click Gui, Left Click on a Module to Toggle, Right Click to Show Settings.", 280.0f, this.height / 3.0f + 75.0f + tipsAppendY, new Color(249, 205, 173, this.contentAlpha).getRGB());
                contentFont.drawString("Tip 3 - Try 'Edit Locations' Button in the Left Bottom Position in Click Gui.", 280.0f, this.height / 3.0f + 100.0f + tipsAppendY, new Color(249, 205, 173, this.contentAlpha).getRGB());
                contentFont.drawString("Tip 4 - Type '.help' to Show All Client Commands and Useage.", 280.0f, this.height / 3.0f + 125.0f + tipsAppendY, new Color(249, 205, 173, this.contentAlpha).getRGB());
                if (this.timer2.hasReached(6000.0)) {
                    if (this.enjoyAlpha > 0 && this.enjoyAlpha < 210) {
                        contentFont.drawCenteredString("---==== Enjoy :) ====---", this.width / 2.0f, this.height / 3.0f + 175.0f, new Color(78, 128, 190, this.enjoyAlpha).getRGB());
                    }
                    if (this.enjoyAlpha >= 210) {
                        contentFont.drawCenteredString("---==== Enjoy :) ====---", this.width / 2.0f, this.height / 3.0f + 175.0f, new Color(78, 128, 190, this.enjoyAlpha).getRGB());
                    }
                    if (this.enjoyAlpha < 210) {
                        this.enjoyAlpha += 7;
                    }
                }
                if (this.timer.hasReached(11000.0)) {
                    if (this.continueAlpha > 0 && this.continueAlpha < 210) {
                        continueFont.drawCenteredString("Click To Continue.", this.width / 2.0f, (float)(this.height - 100), new Color(255, 255, 255, this.continueAlpha).getRGB());
                    }
                    if (this.continueAlpha >= 210) {
                        continueFont.drawCenteredString("Click To Continue.", this.width / 2.0f, (float)(this.height - 100), FadeUtil.fade(new Color(255, 255, 255, this.continueAlpha)).getRGB());
                    }
                    if (this.continueAlpha < 210) {
                        this.continueAlpha += 7;
                    }
                }
            }
        }
        if (!Client.firstLaunch) {
            if (!this.shouldMainMenu && this.shabiAlpha < 210) {
                this.shabiAlpha += 10;
            }
            if (this.shouldMainMenu && this.shabiAlpha > 10) {
                this.shabiAlpha -= 12;
                this.timer3.reset();
            }
            font.drawCenteredString("Welcome back to MelodySky", this.width / 2.0f, this.height / 2.0f - 3.0f, new Color(255, 255, 255, this.shabiAlpha).getRGB());
            if (this.shabiAlpha <= 10 && this.shouldMainMenu) {
                this.shabiAlpha = 6;
                if (this.timer3.hasReached(100.0)) {
                    Client.firstMenu = false;
                    this.mc.displayGuiScreen((GuiScreen)new MainMenu(140));
                    this.timer3.reset();
                }
            }
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        if (Client.firstLaunch) {
            if (this.continueAlpha >= 210) {
                this.mc.displayGuiScreen((GuiScreen)this);
                Client.firstLaunch = false;
            }
        }
        else {
            this.shouldMainMenu = true;
        }
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
}
