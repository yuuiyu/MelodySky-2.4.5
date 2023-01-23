//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import net.minecraft.potion.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;

public class Cam extends Module
{
    public Option<Boolean> name;
    public Option<Boolean> showRank;
    public Option<Boolean> colorHurtCam;
    public Option<Boolean> bht;
    public Option<Boolean> noFire;
    public Option<Boolean> noBlindness;
    public Option<Boolean> noClip;
    
    public Cam() {
        super("Camera", ModuleType.Render);
        this.name = (Option<Boolean>)new Option("Show Self Name", (Object)true);
        this.showRank = (Option<Boolean>)new Option("Show Custom Rank", (Object)true);
        this.colorHurtCam = (Option<Boolean>)new Option("ColorHurtCam", (Object)true);
        this.bht = (Option<Boolean>)new Option("NoHurtCam", (Object)true);
        this.noFire = (Option<Boolean>)new Option("NoFireRender", (Object)false);
        this.noBlindness = (Option<Boolean>)new Option("NoBlindness", (Object)true);
        this.noClip = (Option<Boolean>)new Option("NoClip", (Object)false);
        this.addValues(new Value[] { (Value)this.name, (Value)this.showRank, (Value)this.bht, (Value)this.colorHurtCam, (Value)this.noFire, (Value)this.noBlindness, (Value)this.noClip });
        this.setModInfo("Better Camera Render.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @EventHandler
    private void onTick() {
        if (!(boolean)this.noBlindness.getValue()) {
            return;
        }
        if (this.mc.thePlayer.isPotionActive(Potion.blindness.getId())) {
            this.mc.thePlayer.removePotionEffect(Potion.blindness.getId());
        }
    }
    
    @EventHandler
    private void onHurt(final EventRender2D event) {
        if (!(boolean)this.bht.getValue()) {
            return;
        }
        if (!(boolean)this.colorHurtCam.getValue()) {
            return;
        }
        final ScaledResolution sc = new ScaledResolution(Minecraft.getMinecraft());
        if (this.mc.thePlayer.hurtTime > 0) {
            RenderUtil.drawBorderedRect(0.0f, 0.0f, (float)sc.getScaledWidth(), (float)sc.getScaledHeight(), 10.0f, new Color(25 * this.mc.thePlayer.hurtTime, 20, 20, 20 * this.mc.thePlayer.hurtTime).getRGB(), new Color(255, 255, 255, 1).getRGB());
        }
    }
}
