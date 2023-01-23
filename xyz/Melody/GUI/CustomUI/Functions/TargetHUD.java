//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.CustomUI.Functions;

import xyz.Melody.Utils.*;
import xyz.Melody.Utils.shader.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.GUI.CustomUI.*;
import net.minecraft.client.*;
import xyz.Melody.Utils.animate.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.gui.*;
import xyz.Melody.GUI.Font.*;

public class TargetHUD extends HUDApi
{
    private TimerUtil timer;
    public EntityLivingBase curTarget;
    private double healthBarWidth;
    public double hue;
    private GaussianBlur gblur;
    private LayerBlur lblur;
    
    public TargetHUD() {
        super("TargetHUD", 100, 150);
        this.timer = new TimerUtil();
        this.gblur = new GaussianBlur();
        this.lblur = new LayerBlur();
        this.setEnabled(true);
    }
    
    @EventHandler
    public void onTick(final EventTick e) {
        final Aura a = (Aura)Client.instance.getModuleManager().getModuleByClass(Aura.class);
        if (!(boolean)a.targetHud.getValue()) {
            return;
        }
        if (a.isEnabled()) {
            this.curTarget = a.curTarget;
        }
        if ((this.curTarget != null && !this.curTarget.isEntityAlive() && this.curTarget.isDead) || !a.isEnabled()) {
            this.curTarget = null;
        }
        if (this.curTarget == null) {
            this.healthBarWidth = 0.0;
        }
    }
    
    @EventHandler
    public void onRender(final EventRender2D e) {
        if (!Client.instance.getModuleManager().getModuleByClass(Aura.class).isEnabled() && !(Minecraft.getMinecraft().currentScreen instanceof HUDScreen)) {
            return;
        }
        if (this.y < 75) {
            this.y = 75;
        }
        if (this.curTarget != null) {
            this.Melody();
        }
    }
    
    private void Melody() {
        final FontRenderer font2 = this.mc.fontRendererObj;
        final float health = this.curTarget.getHealth();
        double hpPercentage = health / this.curTarget.getMaxHealth();
        hpPercentage = MathHelper.clamp_double(hpPercentage, 0.0, 1.0);
        final double hpWidth = 92.0 * hpPercentage;
        final int healthColor = ColorUtils.getHealthColor(this.curTarget.getHealth(), this.curTarget.getMaxHealth()).getRGB();
        final int armorColor = ColorUtils.getArmorColor((float)this.curTarget.getTotalArmorValue(), this.curTarget.getMaxHealth()).getRGB();
        final float right = (float)(this.x + 20);
        final float height = (float)(this.y + 80);
        if (this.curTarget != null) {
            if (this.timer.hasReached(15.0)) {
                this.healthBarWidth = AnimationUtil.animate(hpWidth, this.healthBarWidth, 0.15299999713897705);
                this.timer.reset();
            }
            final ScaledResolution scale = new ScaledResolution(this.mc);
            final int factor = scale.getScaleFactor();
            Scissor.start((right - 12.0f) * factor, (height - 90.0f) * factor, (right + 130.0f) * factor, (height - 50.0f) * factor);
            this.gblur.renderBlur(10.0f, 1, 0, 0, 0);
            Scissor.end();
            this.lblur.blurArea(right - 12.0f, (float)(this.y - 10), right + 130.0f, (float)(this.y + 30));
            RenderUtil.drawFastRoundedRect(right - 12.0f, (float)(this.y - 10), right + 130.0f, (float)(this.y + 30), 0.0f, new Color(10, 10, 10, 20).getRGB());
            font2.drawString(this.curTarget.getName(), (int)right + 30, (int)height - 87, 16777215);
            font2.drawString("HP:" + (int)this.curTarget.getHealth() + "/" + (int)this.curTarget.getMaxHealth() + " Hurt:" + (this.curTarget.hurtTime > 0), (int)right + 30, (int)height - 69, new Color(255, 255, 255).getRGB());
            font2.drawString("Dist: " + this.mc.thePlayer.getDistanceToEntity((Entity)this.curTarget), (int)right + 30, (int)height - 60, new Color(255, 255, 255).getRGB());
            if (this.curTarget instanceof EntityPlayer) {
                final EntityPlayer player = (EntityPlayer)this.curTarget;
                final Object texture = ((AbstractClientPlayer)player).getLocationSkin();
                this.mc.getTextureManager().bindTexture((ResourceLocation)texture);
                Gui.drawScaledCustomSizeModalRect((int)(right - 11.0f), (int)(height - 89.0f), 8.0f, 8.0f, 8, 8, 38, 38, 64.0f, 64.0f);
            }
            else {
                GuiInventory.drawEntityOnScreen((int)(right + 9.0f), (int)(height - 54.0f), 15, 2.0f, 15.0f, this.curTarget);
            }
            RenderUtil.drawFastRoundedRect(right + 30.0f, height - 78.0f, right + 30.0f + (float)this.healthBarWidth, height - 71.0f, 0.0f, healthColor);
            final Color c = new Color(armorColor);
            final Color arm = new Color(c.getRed(), c.getGreen(), c.getBlue(), 130);
            RenderUtil.drawFastRoundedRect(right - 12.0f, height - 48.5f, right + 130.0f, height - 50.5f, 0.0f, arm.getRGB());
            RenderUtil.drawFastRoundedRect(right - 1.0f, height - 49.0f, right + 131.0f, height - 91.0f, 0.0f, new Color(255, 0, 0, 0).getRGB());
            GlStateManager.resetColor();
        }
    }
    
    @Override
    public void InScreenRender() {
        final float right = (float)this.x;
        final float height = (float)(this.y + 70);
        RenderUtil.drawFastRoundedRect((float)((int)right - 12), (float)((int)height - 50), (float)((int)right + 130), (float)((int)height - 90), 0.0f, new Color(10, 10, 10, 130).getRGB());
        FontLoaders.NMSL18.drawString("TargetHUD", (float)(this.x + 2), (float)(this.y + 5), new Color(0, 0, 0).getRGB());
    }
}
