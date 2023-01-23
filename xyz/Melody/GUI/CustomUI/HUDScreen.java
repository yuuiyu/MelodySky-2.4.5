//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI;

import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Particles.*;
import org.lwjgl.input.*;
import xyz.Melody.GUI.Font.*;
import java.awt.*;
import java.io.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;
import com.google.common.collect.*;

public class HUDScreen extends GuiScreen
{
    public static ArrayList<HUDWindow> HUDWindows;
    public int scrollVelocity;
    
    public HUDScreen() {
        if (HUDScreen.HUDWindows.isEmpty()) {
            for (int n22 = 0; n22 < HUDManager.getApis().size(); ++n22) {
                final HUDApi c2 = HUDManager.getApis().get(n22);
                HUDScreen.HUDWindows.add(new HUDWindow(c2, c2.x, c2.y));
            }
        }
    }
    
    public void initGui() {
        if (this.mc.theWorld != null) {
            this.mc.entityRenderer.loadShader(new ResourceLocation("shaders/post/blur.json"));
        }
        super.initGui();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        if (this.mc.theWorld == null) {
            RenderUtil.drawImage(new ResourceLocation("Melody/background.png"), 0.0f, 0.0f, (float)this.width, (float)this.height);
            ParticleUtils.drawParticles(mouseX, mouseY);
        }
        if (Mouse.hasWheel()) {
            final int wheel = Mouse.getDWheel();
            this.scrollVelocity = ((wheel < 0) ? -120 : ((wheel > 0) ? 130 : 0));
        }
        FontLoaders.NMSL20.drawStringWithShadow("RightClick To Toggle.", this.width / 2 - FontLoaders.NMSL20.getStringWidth("RightClick To Toggle.") / 2, this.height - this.height + 9, new Color(255, 20, 0).getRGB());
        HUDScreen.HUDWindows.forEach(w2 -> w2.render(mouseX, mouseY));
        HUDManager.getApis().forEach(w2 -> w2.InScreenRender());
        HUDScreen.HUDWindows.forEach(w2 -> w2.mouseScroll(mouseX, mouseY, this.scrollVelocity));
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        HUDScreen.HUDWindows.forEach(w2 -> w2.click(mouseX, mouseY, mouseButton));
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    public void onGuiClosed() {
        this.mc.entityRenderer.stopUseShader();
        String x = "";
        for (final HUDApi m : HUDManager.getApis()) {
            x = String.valueOf(x) + String.format("%s:%s:%s:%s%s", m.getName(), m.x, m.y, m.isEnabled(), System.lineSeparator());
        }
        FileManager.save("HUD.txt", x, false);
    }
    
    static {
        HUDScreen.HUDWindows = (ArrayList<HUDWindow>)Lists.newArrayList();
    }
}
