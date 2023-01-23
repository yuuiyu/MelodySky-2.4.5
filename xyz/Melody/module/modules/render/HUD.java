//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.Utils.shader.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Event.*;
import net.minecraft.potion.*;
import net.minecraft.client.resources.*;
import java.util.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.module.modules.QOL.Dungeons.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.module.modules.Fishing.*;
import net.minecraft.client.renderer.*;

public class HUD extends Module
{
    public Option<Boolean> blur;
    public Option<Boolean> cgblur;
    public Numbers<Double> bMax;
    public Numbers<Double> bSpeed;
    public Option<Boolean> container;
    public Option<Boolean> scoreBoard;
    private Option<Boolean> coords;
    private Option<Boolean> pots;
    private GaussianBlur gblur;
    
    public HUD() {
        super("HUD", new String[] { "gui" }, ModuleType.Others);
        this.blur = (Option<Boolean>)new Option("Gui Blur", (Object)true);
        this.cgblur = (Option<Boolean>)new Option("ClickGui Blur", (Object)true);
        this.bMax = (Numbers<Double>)new Numbers("Blur Value", (Number)25.0, (Number)10.0, (Number)100.0, (Number)5.0);
        this.bSpeed = (Numbers<Double>)new Numbers("Blur Speed", (Number)5.0, (Number)1.0, (Number)10.0, (Number)1.0);
        this.container = (Option<Boolean>)new Option("Gui Animations", (Object)true);
        this.scoreBoard = (Option<Boolean>)new Option("ScoreBoard", (Object)true);
        this.coords = (Option<Boolean>)new Option("Coords", (Object)true);
        this.pots = (Option<Boolean>)new Option("Effects", (Object)true);
        this.gblur = new GaussianBlur();
        this.addValues(new Value[] { (Value)this.blur, (Value)this.cgblur, (Value)this.bMax, (Value)this.bSpeed, (Value)this.container, (Value)this.scoreBoard, (Value)this.coords, (Value)this.pots });
        this.setEnabled(true);
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.setEnabled(true);
        super.onDisable();
    }
    
    @EventHandler
    private void onRenderInfo(final EventRender2D event) {
        final ScaledResolution mainWindow = new ScaledResolution(this.mc);
        final float infoY = (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiChat) ? -15.0f : -2.0f;
        RenderUtil.drawFastRoundedRect(0.0f, 0.0f, 1.0f, 1.0f, 1.0f, new Color(10, 10, 10, 10).getRGB());
        if (this.coords.getValue()) {
            this.mc.fontRendererObj.drawString("X: " + (int)this.mc.thePlayer.posX + "  Y: " + (int)this.mc.thePlayer.posY + "  Z: " + (int)this.mc.thePlayer.posZ, 3, (int)(mainWindow.getScaledHeight() - 10 + infoY), -1);
        }
        if (this.pots.getValue()) {
            this.drawPotionStatus(mainWindow);
        }
    }
    
    private void drawPotionStatus(final ScaledResolution sr) {
        final List<PotionEffect> potions = new ArrayList<PotionEffect>();
        for (final Object o : this.mc.thePlayer.getActivePotionEffects()) {
            potions.add((PotionEffect)o);
        }
        potions.sort(Comparator.comparingDouble(effect -> -this.mc.fontRendererObj.getStringWidth(I18n.format(Potion.potionTypes[effect.getPotionID()].getName(), new Object[0]))));
        float pY = (this.mc.currentScreen != null && this.mc.currentScreen instanceof GuiChat) ? -15.0f : -2.0f;
        for (final PotionEffect effect2 : potions) {
            final Potion potion = Potion.potionTypes[effect2.getPotionID()];
            String name = I18n.format(potion.getName(), new Object[0]);
            String PType = "";
            if (effect2.getAmplifier() == 1) {
                name += " II";
            }
            else if (effect2.getAmplifier() == 2) {
                name += " III";
            }
            else if (effect2.getAmplifier() == 3) {
                name += " IV";
            }
            if (effect2.getDuration() < 600 && effect2.getDuration() > 300) {
                PType = PType + "§6 " + Potion.getDurationString(effect2);
            }
            else if (effect2.getDuration() < 300) {
                PType = PType + "§c " + Potion.getDurationString(effect2);
            }
            else if (effect2.getDuration() > 600) {
                PType = PType + "§7 " + Potion.getDurationString(effect2);
            }
            this.mc.fontRendererObj.drawStringWithShadow(name, (float)(sr.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(name + PType)), sr.getScaledHeight() - 9 + pY, potion.getLiquidColor());
            this.mc.fontRendererObj.drawStringWithShadow(PType, (float)(sr.getScaledWidth() - this.mc.fontRendererObj.getStringWidth(PType)), sr.getScaledHeight() - 9 + pY, -1);
            pY -= 9.0f;
        }
    }
    
    public void handleContainer(final Translate translate, final Opacity opacity, final float width, final float height) {
        if (this.blur.getValue()) {
            this.gblur.renderBlur(opacity.getOpacity());
            opacity.interp(((Double)this.bMax.getValue()).floatValue(), ((Double)this.bSpeed.getValue()).intValue());
        }
        if (this.isEnabled() && (boolean)this.container.getValue()) {
            final AutoTerminals at = (AutoTerminals)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoTerminals.class);
            final AutoEnchantTable aet = (AutoEnchantTable)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AutoEnchantTable.class);
            final SlugFishing slug = (SlugFishing)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)SlugFishing.class);
            if (!slug.isEnabled() && (at.currentTerminal == null || at.currentTerminal == AutoTerminals.TerminalType.NONE) && (aet.currentType == null || aet.currentType == AutoEnchantTable.expType.NONE)) {
                translate.interpolate(width, height, 12.0);
                final double xmod = width / 2.0f - translate.getX() / 2.0f;
                final double ymod = height / 2.0f - translate.getY() / 2.0f;
                GlStateManager.translate(xmod, ymod, 0.0);
                GlStateManager.scale(translate.getX() / width, translate.getY() / height, 1.0f);
            }
        }
    }
}
