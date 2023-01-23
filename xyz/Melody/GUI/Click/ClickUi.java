//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Click;

import xyz.Melody.Utils.*;
import xyz.Melody.Utils.shader.*;
import java.util.*;
import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.module.*;
import net.minecraft.client.gui.*;
import xyz.Melody.ClientLib.*;
import javax.swing.*;
import java.awt.*;
import xyz.Melody.GUI.CustomUI.*;
import java.io.*;
import xyz.Melody.Utils.animate.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;
import org.lwjgl.input.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import xyz.Melody.GUI.Particles.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.*;

public class ClickUi extends GuiScreen
{
    private TimerUtil timer;
    private GaussianBlur gb;
    public static ArrayList<Window> windows;
    public int scrollVelocity;
    public static boolean binding;
    private int alpha;
    private float animpos;
    private boolean jb;
    private Opacity opacity;
    
    public ClickUi() {
        this.timer = new TimerUtil();
        this.gb = new GaussianBlur();
        this.alpha = 0;
        this.jb = false;
        this.opacity = new Opacity(10);
        this.animpos = 75.0f;
        if (ClickUi.windows.isEmpty()) {
            int x = 5;
            for (final ModuleType c : ModuleType.values()) {
                ClickUi.windows.add(new Window(c, x, 5));
                x += 105;
            }
        }
    }
    
    public ClickUi(final boolean jb) {
        this.timer = new TimerUtil();
        this.gb = new GaussianBlur();
        this.alpha = 0;
        this.jb = jb;
        this.opacity = new Opacity(10);
        this.animpos = 75.0f;
        if (ClickUi.windows.isEmpty()) {
            int x = 5;
            for (final ModuleType c : ModuleType.values()) {
                ClickUi.windows.add(new Window(c, x, 5));
                x += 105;
            }
        }
    }
    
    public void initGui() {
        super.initGui();
    }
    
    protected void actionPerformed(final GuiButton gay) throws IOException {
        switch (gay.id) {
            case 0: {
                UISettings.chatTextShadow = !UISettings.chatTextShadow;
                break;
            }
            case 1: {
                UISettings.chatBackground = !UISettings.chatBackground;
                break;
            }
            case 2: {
                UISettings.scoreboardBackground = !UISettings.scoreboardBackground;
                break;
            }
            case 3: {
                if (this.mc.theWorld == null) {
                    JOptionPane.showConfirmDialog(null, "This Feature is Not Available in Menus.");
                    break;
                }
                this.mc.displayGuiScreen((GuiScreen)new HUDScreen());
                break;
            }
        }
        super.actionPerformed(gay);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.animpos = AnimationUtil.moveUD(this.animpos, 1.0f, 0.1f, 0.1f);
        if (this.jb) {
            this.drawDefaultBackground();
            this.gb.renderBlur(this.opacity.getOpacity());
            this.opacity.interp(140.0f, 5);
        }
        RenderUtil.drawImage(new ResourceLocation("Melody/Melody.png"), (float)(this.width - 160), (float)(this.height - 40), 32.0f, 32.0f);
        FontLoaders.CNMD34.drawStringWithShadow("MelodySky", this.width - 125, this.height - 34, -1);
        GlStateManager.rotate(this.animpos, 0.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, this.animpos, 0.0f);
        GlStateManager.pushMatrix();
        ClickUi.windows.forEach(w -> w.render(mouseX, mouseY));
        GlStateManager.popMatrix();
        if (Mouse.hasWheel()) {
            final int wheel = Mouse.getDWheel();
            this.scrollVelocity = ((wheel < 0) ? -120 : ((wheel > 0) ? 120 : 0));
        }
        ClickUi.windows.forEach(w -> w.mouseScroll(mouseX, mouseY, this.scrollVelocity));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        ClickUi.windows.forEach(w -> w.click(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (keyCode == 1 && !ClickUi.binding) {
            this.mc.displayGuiScreen((GuiScreen)null);
            return;
        }
        ClickUi.windows.forEach(w -> w.key(typedChar, keyCode));
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
    
    public void onGuiClosed() {
        this.alpha = 0;
        this.timer.reset();
        Client.instance.saveConfig(false);
        Client.instance.saveUISettings(false);
        this.mc.entityRenderer.stopUseShader();
    }
    
    public synchronized void sendToFront(final Window window) {
        int panelIndex = 0;
        for (int i = 0; i < ClickUi.windows.size(); ++i) {
            if (ClickUi.windows.get(i) == window) {
                panelIndex = i;
                break;
            }
        }
        final Window t = ClickUi.windows.get(ClickUi.windows.size() - 1);
        ClickUi.windows.set(ClickUi.windows.size() - 1, ClickUi.windows.get(panelIndex));
        ClickUi.windows.set(panelIndex, t);
    }
    
    static {
        ClickUi.windows = new ArrayList<Window>();
        ClickUi.binding = false;
    }
}
