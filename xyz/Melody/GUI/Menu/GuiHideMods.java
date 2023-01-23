//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import net.minecraftforge.fml.client.config.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.gui.*;
import xyz.Melody.*;
import net.minecraftforge.common.config.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import xyz.Melody.GUI.Particles.*;
import net.minecraft.client.renderer.*;

public class GuiHideMods extends GuiConfig
{
    private int shabiAlpha;
    private int alpha;
    private int contentAlpha;
    private float titleY;
    private TimerUtil timer;
    private int continueAlpha;
    
    public void initGui() {
        this.alpha = 0;
        this.titleY = 0.0f;
        this.contentAlpha = 0;
        this.continueAlpha = 0;
        this.timer.reset();
        super.initGui();
    }
    
    public GuiHideMods(final GuiScreen parentScreen) {
        super(parentScreen, new ConfigElement(Client.modsConfig.getCategory("general")).getChildElements(), "MelodySky", false, false, "Melody Sky Mods Hider");
        this.shabiAlpha = 0;
        this.alpha = 0;
        this.contentAlpha = 0;
        this.titleY = 0.0f;
        this.timer = new TimerUtil();
        this.continueAlpha = 0;
        this.titleLine2 = "Select Which Mods Will Be Hidden.";
    }
    
    public void onGuiClosed() {
        super.onGuiClosed();
        Client.modsConfig.save();
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
        ParticleUtils.drawParticles();
    }
}
