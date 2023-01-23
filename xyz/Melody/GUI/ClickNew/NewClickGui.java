//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.ClickNew;

import xyz.Melody.module.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.Utils.shader.*;
import xyz.Melody.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import net.minecraft.client.gui.*;
import xyz.Melody.ClientLib.*;
import xyz.Melody.GUI.CustomUI.*;
import java.io.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Font.*;
import xyz.Melody.Utils.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import xyz.Melody.System.Managers.Client.*;
import java.text.*;
import xyz.Melody.Event.value.*;
import java.util.*;

public class NewClickGui extends GuiScreen implements GuiYesNoCallback
{
    public ModuleType currentModuleType;
    public Module currentModule;
    public float startX;
    public float startY;
    public int moduleStart;
    public int valueStart;
    boolean previousmouse;
    boolean mouse;
    public Opacity opacity;
    public Opacity blurOpacity;
    public int opacityx;
    public float moveX;
    public float moveY;
    public boolean binding;
    public float lastPercent;
    public float percent;
    public float percent2;
    public float lastPercent2;
    public float outro;
    public float lastOutro;
    public int mouseWheel;
    public int mouseX;
    public int mouseY;
    private TimerUtil lcTimer;
    private TimerUtil rcTimer;
    private TimerUtil idkTimer;
    private boolean inSearch;
    private String SearchText;
    private Translate translate;
    private GaussianBlur gb;
    
    public NewClickGui() {
        this.currentModuleType = ModuleType.QOL;
        this.currentModule = ((Client.instance.getModuleManager().getModulesInType(this.currentModuleType).size() != 0) ? Client.instance.getModuleManager().getModulesInType(this.currentModuleType).get(0) : null);
        this.startX = 100.0f;
        this.startY = 100.0f;
        this.moduleStart = 0;
        this.valueStart = 0;
        this.previousmouse = true;
        this.opacity = new Opacity(1);
        this.blurOpacity = new Opacity(1);
        this.opacityx = 255;
        this.moveX = 0.0f;
        this.moveY = 0.0f;
        this.binding = false;
        this.lcTimer = new TimerUtil();
        this.rcTimer = new TimerUtil();
        this.idkTimer = new TimerUtil();
        this.SearchText = "Search...";
        this.translate = new Translate(0.0f, 0.0f);
        this.gb = new GaussianBlur();
    }
    
    public void initGui() {
        this.opacity = new Opacity(1);
        this.blurOpacity = new Opacity(1);
        this.translate = new Translate(0.0f, 0.0f);
        this.startX = this.width / 3.7f;
        this.startY = (float)(this.height / 5);
        this.SearchText = "Search...";
        this.buttonList.add(new ClientButton(0, 5, this.height - 106, 90, 20, "ChatTextShadow", new Color(138, 43, 226, 80)));
        this.buttonList.add(new ClientButton(1, 5, this.height - 84, 90, 20, "ChatBackground", new Color(138, 43, 226, 80)));
        this.buttonList.add(new ClientButton(2, 5, this.height - 62, 120, 20, "ScoreboardBackground", new Color(138, 43, 226, 80)));
        this.buttonList.add(new ClientButton(3, 5, this.height - 40, 80, 20, "Edit Locations", new Color(221, 160, 221, 80)));
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
                this.mc.displayGuiScreen((GuiScreen)new HUDScreen());
                break;
            }
        }
        super.actionPerformed(gay);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        if (this.currentModule != null && this.binding) {
            this.currentModule.setKey(keyCode);
            this.binding = false;
        }
        if (this.inSearch) {
            if (keyCode == 14) {
                try {
                    (this.SearchText = this.SearchText.substring(0, this.SearchText.length() - 1)).substring(0, this.SearchText.length() - 1);
                }
                catch (StringIndexOutOfBoundsException e) {
                    this.SearchText = "";
                }
            }
            if (ChatAllowedCharacters.isAllowedCharacter(typedChar)) {
                this.SearchText += Character.toString(typedChar);
            }
        }
        super.keyTyped(typedChar, keyCode);
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass(HUD.class);
        if (hud.cgblur.getValue()) {
            this.gb.renderBlur(this.blurOpacity.getOpacity());
            this.blurOpacity.interp(50.0f, 5);
        }
        this.opacity.interpolate(160.0f);
        RenderUtil.drawImage(new ResourceLocation("Melody/Melody.png"), (float)(this.width - 160), (float)(this.height - 40), 32.0f, 32.0f);
        FontLoaders.CNMD34.drawString("MelodySky", (float)(this.width - 125), (float)(this.height - 34), -1);
        FontLoaders.CNMD24.drawString(UISettings.chatTextShadow + "", 100.0f, (float)(this.height - 101), -1);
        FontLoaders.CNMD24.drawString(UISettings.chatBackground + "", 100.0f, (float)(this.height - 79), -1);
        FontLoaders.CNMD24.drawString(UISettings.scoreboardBackground + "", 130.0f, (float)(this.height - 57), -1);
        if (!Client.instance.authManager.verified) {
            FontLoaders.CNMD28.drawCenteredString("MelodySky Will Not Work Cause of Failed to Verify Your UUID.", (float)(this.width / 2), 20.0f, Colors.BLUE.c);
        }
        this.mouseX = mouseX;
        this.mouseY = mouseY;
        if (this.mc.currentScreen != null && !(this.mc.currentScreen instanceof NewClickGui)) {
            this.lastOutro = this.outro;
            if (this.outro < 1.7) {
                this.outro += 0.1f;
                this.outro += (float)((1.7 - this.outro) / 3.0 - 0.001);
            }
            if (this.outro > 1.7) {
                this.outro = 1.7f;
            }
            if (this.outro < 1.0f) {
                this.outro = 1.0f;
            }
        }
        if (this.mc.currentScreen != null && this.mc.currentScreen != null && !(this.mc.currentScreen instanceof NewClickGui)) {
            return;
        }
        this.lastPercent = this.percent;
        this.lastPercent2 = this.percent2;
        if (this.percent > 0.98) {
            this.percent += (float)((0.98 - this.percent) / 1.4500000476837158 - 0.001);
        }
        if (this.percent <= 0.98 && this.percent2 < 1.0f) {
            this.percent2 += (float)((1.0f - this.percent2) / 2.8f + 0.002);
        }
        if (this.isHovered(this.startX, this.startY - 25.0f, this.startX + 400.0f, this.startY + 25.0f, mouseX, mouseY) && Mouse.isButtonDown(0)) {
            if (this.moveX == 0.0f && this.moveY == 0.0f) {
                this.moveX = mouseX - this.startX;
                this.moveY = mouseY - this.startY;
            }
            else {
                this.startX = mouseX - this.moveX;
                this.startY = mouseY - this.moveY;
            }
            this.previousmouse = true;
        }
        else if (this.moveX != 0.0f || this.moveY != 0.0f) {
            this.moveX = 0.0f;
            this.moveY = 0.0f;
        }
        GL11.glPushMatrix();
        this.translate.interpolate((float)this.width, (float)this.height, 8.0);
        final double xmod = this.width / 2 - this.translate.getX() / 2.0f;
        final double ymod = this.height / 2 - this.translate.getY() / 2.0f;
        GlStateManager.translate(xmod, ymod, 0.0);
        GlStateManager.scale(this.translate.getX() / this.width, this.translate.getY() / this.height, 1.0f);
        RenderUtil.drawFastRoundedRect(this.startX, this.startY, this.startX + 200.0f, this.startY + 320.0f, 1.0f, new Color(30, 30, 30, (int)this.opacity.getOpacity()).getRGB());
        ClickGuiRenderUtil.drawRect(this.startX + 200.0f, this.startY, this.startX + 431.0f, this.startY + 320.0f, new Color(40, 40, 40, (int)this.opacity.getOpacity()).getRGB());
        ClickGuiRenderUtil.drawRainbowRect((double)this.startX, (double)this.startY, (double)(this.startX + 430.0f), (double)(this.startY + 1.0f));
        int m;
        for (m = 0; m < ModuleType.values().length; ++m) {
            final ModuleType[] mY = ModuleType.values();
            if (mY[m] != this.currentModuleType || (this.SearchText != "Search..." && this.SearchText != null && this.SearchText != "")) {
                if (mY[m].toString() == "QOL") {
                    FontLoaders.NMSL25.drawString("QOL", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Dungeons") {
                    FontLoaders.NMSL25.drawString("Dungeon", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Swapping") {
                    FontLoaders.NMSL25.drawString("Swaps", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Fishing") {
                    FontLoaders.NMSL25.drawString("Fishing", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Nether") {
                    FontLoaders.NMSL25.drawString("Nether", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Render") {
                    FontLoaders.NMSL25.drawString("Render", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Macros") {
                    FontLoaders.NMSL25.drawString("Macros", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Others") {
                    FontLoaders.NMSL25.drawString("Others", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
                if (mY[m].toString() == "Balance") {
                    FontLoaders.NMSL25.drawString("Balance", this.startX + 7.0f, this.startY + 20.0f + m * 30 + 3.0f, new Color(255, 255, 255).getRGB());
                }
            }
            else if (this.SearchText == "Search..." || this.SearchText == null || this.SearchText == "") {
                ClickGuiRenderUtil.drawRoundedRect(this.startX + 4.0f, this.startY + 16.0f + m * 30, this.startX + 5.5f, this.startY + 39.0f + m * 30, new Color(101, 81, 255).getRGB(), new Color(101, 81, 255).getRGB());
                ClickGuiRenderUtil.drawRoundedRect(this.startX + 4.0f, this.startY + 16.0f + m * 30, this.startX + 60.0f, this.startY + 39.0f + m * 30, new Color(101, 81, 255).getRGB(), new Color(101, 81, 255, 100).getRGB());
                if (mY[m].toString() == "QOL") {
                    FontLoaders.NMSL25.drawString("QOL", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Dungeons") {
                    FontLoaders.NMSL25.drawString("Dungeon", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Swapping") {
                    FontLoaders.NMSL25.drawString("Swaps", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Fishing") {
                    FontLoaders.NMSL25.drawString("Fishing", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Nether") {
                    FontLoaders.NMSL25.drawString("Nether", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Render") {
                    FontLoaders.NMSL25.drawString("Render", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Macros") {
                    FontLoaders.NMSL25.drawString("Macros", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Others") {
                    FontLoaders.NMSL25.drawString("Others", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
                if (mY[m].toString() == "Balance") {
                    FontLoaders.NMSL25.drawString("Balance", this.startX + 8.0f, this.startY + 20.0f + m * 30 + 2.0f, new Color(255, 255, 200).getRGB());
                }
            }
            try {
                if (this.isCategoryHovered(this.startX + 7.0f, this.startY + 15.0f + m * 30, this.startX + 60.0f, this.startY + 45.0f + m * 30, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                    this.SearchText = "Search...";
                    this.inSearch = false;
                    this.currentModuleType = mY[m];
                    this.currentModule = ((Client.instance.getModuleManager().getModulesInType(this.currentModuleType).size() != 0) ? Client.instance.getModuleManager().getModulesInType(this.currentModuleType).get(0) : null);
                    this.moduleStart = 0;
                }
            }
            catch (Exception var23) {
                System.err.println(var23);
            }
        }
        this.mouseWheel = Mouse.getDWheel();
        if (this.isCategoryHovered(this.startX + 60.0f, this.startY, this.startX + 200.0f, this.startY + 320.0f, mouseX, mouseY)) {
            if (this.mouseWheel < 0 && this.moduleStart < Client.instance.getModuleManager().getModulesInType(this.currentModuleType).size() - 1) {
                ++this.moduleStart;
            }
            if (this.mouseWheel > 0 && this.moduleStart > 0) {
                --this.moduleStart;
            }
        }
        if (this.isCategoryHovered(this.startX + 200.0f, this.startY, this.startX + 420.0f, this.startY + 320.0f, mouseX, mouseY)) {
            if (this.mouseWheel < 0 && this.valueStart < this.currentModule.getValues().size() - 1) {
                ++this.valueStart;
            }
            if (this.mouseWheel > 0 && this.valueStart > 0) {
                --this.valueStart;
            }
        }
        GL11.glPopMatrix();
        final boolean searchHover = this.mouseWithinBounds(mouseX, mouseY, this.width / 2 - 100, 40, 200, 20);
        RenderUtil.drawFastRoundedRect((float)(this.width / 2 - 100), 40.0f, (float)(this.width / 2 + 100), 60.0f, 1.0f, new Color(55, 55, 55, 190).getRGB());
        FontLoaders.CNMD20.drawStringWithShadow(this.SearchText, this.width / 2 - 95, 46.0, new Color(255, 255, 255, 190).getRGB());
        RenderUtil.drawBorderedRect((float)(this.width / 2 - 100), 40.0f, (float)(this.width / 2 + 100), 60.0f, 0.5f, this.inSearch ? new Color(255, 255, 255, 190).getRGB() : new Color(100, 100, 100, 100).getRGB(), new Color(180, 180, 180, 0).getRGB());
        if (searchHover && Mouse.isButtonDown(0)) {
            this.inSearch = true;
            this.SearchText = "";
        }
        else if (!searchHover && Mouse.isButtonDown(0)) {
            this.inSearch = false;
        }
        if (this.SearchText == "" && !this.inSearch) {
            this.SearchText = "Search...";
        }
        if (this.inSearch) {
            if (this.idkTimer.hasReached(500.0)) {
                FontLoaders.CNMD20.drawString("|", (float)(this.width / 2 - 95 + FontLoaders.CNMD20.getStringWidth(this.SearchText)), 46.0f, -1);
                if (this.idkTimer.hasReached(1000.0)) {
                    this.idkTimer.reset();
                }
            }
        }
        else {
            this.idkTimer.reset();
        }
        GL11.glPushMatrix();
        GlStateManager.translate(xmod, ymod, 0.0);
        GlStateManager.scale(this.translate.getX() / this.width, this.translate.getY() / this.height, 1.0f);
        if (this.currentModule != null) {
            RenderUtil.drawBorderedRect(this.startX + 210.0f, this.startY + 295.0f, this.startX + 420.0f, this.startY + 310.0f, 1.0f, new Color(90, 90, 90, (int)(1.5 * this.opacity.getOpacity())).getRGB(), new Color(30, 30, 30, (int)(0.5 * this.opacity.getOpacity())).getRGB());
            if (!this.binding) {
                FontLoaders.NMSL18.drawString("Current Key: " + Keyboard.getKeyName(this.currentModule.getKey()), this.startX + 215.0f, this.startY + 300.0f, -1);
                FontLoaders.NMSL18.drawString(EnumChatFormatting.GRAY + "Click to Bind.", this.startX + 360.0f, this.startY + 300.0f, -1);
            }
            else {
                FontLoaders.NMSL18.drawString("Waitting...", this.startX + 215.0f, this.startY + 300.0f, -1);
            }
            if (this.isButtonHovered(this.startX + 210.0f, this.startY + 295.0f, this.startX + 420.0f, this.startY + 310.0f, mouseX, mouseY) && Mouse.isButtonDown(0) && !this.binding) {
                this.binding = true;
            }
            if (this.isButtonHovered(this.startX + 210.0f, this.startY + 295.0f, this.startX + 420.0f, this.startY + 310.0f, mouseX, mouseY) && Mouse.isButtonDown(1) && this.binding) {
                this.binding = false;
            }
            if (this.isButtonHovered(this.startX + 210.0f, this.startY + 295.0f, this.startX + 420.0f, this.startY + 310.0f, mouseX, mouseY) && Mouse.isButtonDown(1) && !this.binding) {
                this.currentModule.setKey(0);
            }
        }
        ClickGuiRenderUtil.drawRoundedRect(this.startX + 199.0f, this.startY - 150.0f + this.moduleStart - 85.0f + m * 30, this.startX + 201.0f, this.startY - 50.0f + this.moduleStart + 10.0f + m * 30, new Color(101, 81, 255).getRGB(), new Color(101, 81, 255, 180).getRGB());
        if (this.SearchText != "Search..." && this.SearchText != null && this.SearchText != "") {
            FontLoaders.NMSL18.drawString("Search -> " + this.currentModule.getType().toString() + " ->", this.startX + 70.0f, this.startY + 12.5f, new Color(248, 248, 248).getRGB());
        }
        else {
            FontLoaders.NMSL18.drawString((this.currentModule == null) ? this.currentModuleType.toString() : this.currentModuleType.toString(), this.startX + 70.0f, this.startY + 12.5f, new Color(248, 248, 248).getRGB());
        }
        if (this.currentModule != null) {
            FontLoaders.NMSL18.drawString(this.currentModule.getName(), this.startX + 210.5f, this.startY + 10.5f, Colors.AQUA.c);
            if (this.currentModule.getValues().isEmpty()) {
                FontLoaders.NMSL18.drawString("No Values Available.", this.startX + 270.0f, this.startY + 150.0f, Colors.AQUA.c);
            }
        }
        if (this.currentModule != null) {
            FontLoaders.NMSL14.drawString(this.currentModule.getModInfo(), this.startX + 215.5f + FontLoaders.NMSL18.getStringWidth(this.currentModule.getName()), this.startY + 12.0f, -1);
        }
        if (this.SearchText != "Search..." && this.SearchText != null && this.SearchText != "") {
            float var24 = this.startY + 30.0f;
            for (int i = 0; i < ModuleManager.getModules().size(); ++i) {
                final Module mod = ModuleManager.getModules().get(i);
                final String curMod = ModuleManager.getModules().get(i).getName().toUpperCase();
                final String curModInfo = ModuleManager.getModules().get(i).getModInfo().toUpperCase();
                String val = "";
                for (final Value<?> v : mod.getValues()) {
                    val += (v.getName() + " ").toUpperCase();
                }
                final boolean valContains = mod.getValues().size() != 0 && val.contains(this.SearchText.toUpperCase());
                if (curMod.contains(this.SearchText.toUpperCase()) || curModInfo.contains(this.SearchText.toUpperCase()) || valContains) {
                    final Module value = ModuleManager.getModules().get(i);
                    if (var24 > this.startY + 300.0f) {
                        break;
                    }
                    RenderUtil.drawFastRoundedRect(this.startX + 195.0f, var24, this.startX + 65.0f, var24 + 20.0f, 1.0f, new Color(40, 40, 40, 20).getRGB());
                    if (i >= this.moduleStart) {
                        FontLoaders.NMSL16.drawString(value.getName(), this.startX + 86.0f, var24 + 8.0f, new Color(248, 248, 248, (int)this.opacity.getOpacity()).getRGB());
                        if (!value.isEnabled()) {
                            ClickGuiRenderUtil.drawFilledCircle((double)(this.startX + 75.0f), (double)(var24 + 10.0f), 3.0, new Color(255, 0, 0).getRGB(), 50);
                        }
                        else {
                            ClickGuiRenderUtil.drawFilledCircle((double)(this.startX + 75.0f), (double)(var24 + 10.0f), 3.0, new Color(0, 255, 0).getRGB(), 50);
                        }
                        RenderUtil.drawFastRoundedRect(this.startX + 65.0f, var24, this.startX + 183.0f, var24 + 16.0f + FontLoaders.NMSL20.getHeight() - 5.0f, 2.0f, mod.isEnabled() ? new Color(153, 51, 250, 75).getRGB() : new Color(100, 100, 100, 45).getRGB());
                        if (this.isSettingsButtonHovered(this.startX + 65.0f, var24, this.startX + 183.0f, var24 + 16.0f + FontLoaders.NMSL20.getHeight() - 5.0f, mouseX, mouseY)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                if (value.isEnabled()) {
                                    value.setEnabled(false);
                                }
                                else {
                                    value.setEnabled(true);
                                }
                                this.previousmouse = true;
                            }
                            if (!this.previousmouse && Mouse.isButtonDown(1)) {
                                this.previousmouse = true;
                            }
                        }
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }
                        if (this.isSettingsButtonHovered(this.startX + 90.0f, var24, this.startX + 100.0f + FontLoaders.NMSL20.getStringWidth(value.getName()), var24 + 8.0f + FontLoaders.NMSL20.getHeight(), mouseX, mouseY) && Mouse.isButtonDown(1)) {
                            this.currentModule = value;
                            this.valueStart = 0;
                        }
                        var24 += 25.0f;
                    }
                }
            }
            var24 = this.startY + 30.0f;
            for (int i = 0; i < this.currentModule.getValues().size() && var24 <= this.startY + 280.0f; ++i) {
                if (i >= this.valueStart) {
                    final Value var25 = this.currentModule.getValues().get(i);
                    if (var25 instanceof Numbers) {
                        final float x = this.startX + 300.0f;
                        double current = 68.0f * (((Number)var25.getValue()).floatValue() - ((Numbers)var25).getMinimum().floatValue()) / (((Numbers)var25).getMaximum().floatValue() - ((Numbers)var25).getMinimum().floatValue());
                        ClickGuiRenderUtil.drawRect(x - 6.0f, var24 + 2.0f, (float)(x + 75.0), var24 + 3.0f, new Color(50, 50, 50, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x - 6.0f, var24 + 2.0f, (float)(x + current + 6.5), var24 + 3.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect((float)(x + current + 2.0), var24, (float)(x + current + 7.0), var24 + 5.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        final boolean mouseAtPlus = this.mouseX > x + 80.0f && mouseX < x + 90.0f && mouseY > var24 - 2.5f && mouseY < var24 + 7.5f;
                        RenderUtil.drawFastRoundedRect(x + 80.0f, var24 - 2.5f, x + 90.0f, var24 + 7.5f, 0.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawCenteredString("+", x + 85.0f, var24 - 0.5f, -1);
                        if (mouseAtPlus && Mouse.isButtonDown(0) && this.lcTimer.hasReached(150.0)) {
                            if (((Number)var25.getValue()).floatValue() >= ((Numbers)var25).getMaximum().doubleValue()) {
                                this.lcTimer.reset();
                            }
                            else {
                                final float append = ((Numbers)var25).inc.floatValue();
                                final DecimalFormat df = new DecimalFormat("0.00");
                                final double finalPlus = Double.parseDouble(df.format(((Number)var25.getValue()).floatValue() + append));
                                var25.setValue((Object)finalPlus);
                                this.lcTimer.reset();
                            }
                        }
                        final boolean mouseAtCut = this.mouseX > x + 93.0f && mouseX < x + 103.0f && mouseY > var24 - 2.5f && mouseY < var24 + 7.5f;
                        RenderUtil.drawFastRoundedRect(x + 93.0f, var24 - 2.5f, x + 103.0f, var24 + 7.5f, 0.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawCenteredString("-", x + 97.5f, var24 - 0.5f, -1);
                        if (mouseAtCut && Mouse.isButtonDown(0) && this.rcTimer.hasReached(150.0)) {
                            if (((Number)var25.getValue()).floatValue() <= ((Numbers)var25).getMinimum().doubleValue()) {
                                this.rcTimer.reset();
                            }
                            else {
                                final float append2 = ((Numbers)var25).inc.floatValue();
                                final DecimalFormat df2 = new DecimalFormat("0.00");
                                final double finalPlus2 = Double.parseDouble(df2.format(((Number)var25.getValue()).floatValue() - append2));
                                var25.setValue((Object)finalPlus2);
                                this.rcTimer.reset();
                            }
                        }
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName() + ": " + var25.getValue(), this.startX + 210.0f, var24, -1);
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }
                        if (this.isButtonHovered(x, var24 - 2.0f, x + 75.0f, var24 + 7.0f, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                current = ((Numbers)var25).getMinimum().doubleValue();
                                final double max = ((Numbers)var25).getMaximum().doubleValue();
                                final double inc = ((Numbers)var25).getIncrement().doubleValue();
                                final double valAbs = mouseX - (x + 1.0);
                                double perc = valAbs / 68.0;
                                perc = Math.min(Math.max(0.0, perc), 1.0);
                                final double valRel = (max - current) * perc;
                                double val2 = current + valRel;
                                val2 = Math.round(val2 * (1.0 / inc)) / (1.0 / inc);
                                ((Numbers)var25).setValue((Object)val2);
                            }
                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }
                        var24 += 20.0f;
                    }
                    if (var25 instanceof Option) {
                        final float x = this.startX + 300.0f;
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24, x + 76.0f, var24 + 1.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24 + 8.0f, x + 76.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24, x + 57.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 77.0f, var24, x + 76.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName(), this.startX + 210.0f, var24, -1);
                        if (var25.getValue()) {
                            ClickGuiRenderUtil.drawRect(x + 67.0f, var24 + 2.0f, x + 75.0f, var24 + 7.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        }
                        else {
                            ClickGuiRenderUtil.drawRect(x + 58.0f, var24 + 2.0f, x + 65.0f, var24 + 7.0f, new Color(150, 150, 150, (int)this.opacity.getOpacity()).getRGB());
                        }
                        if (this.isCheckBoxHovered(x + 56.0f, var24, x + 76.0f, var24 + 9.0f, mouseX, mouseY)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                this.previousmouse = true;
                                this.mouse = true;
                            }
                            if (this.mouse) {
                                var25.setValue((Object)!(boolean)var25.getValue());
                                this.mouse = false;
                            }
                        }
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }
                        var24 += 20.0f;
                    }
                    if (var25 instanceof Mode) {
                        final float x = this.startX + 300.0f;
                        ClickGuiRenderUtil.drawRect(x - 5.0f, var24 - 5.0f, x + 90.0f, var24 + 15.0f, new Color(56, 56, 56, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawBorderRect((double)(x - 5.0f), (double)(var24 - 5.0f), (double)(x + 90.0f), (double)(var24 + 15.0f), new Color(101, 81, 255, (int)this.opacity.getOpacity()).getRGB(), 2.0);
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName(), this.startX + 210.0f, var24 + 2.0f, -1);
                        FontLoaders.NMSL18.drawStringWithShadow(((Mode)var25).getModeAsString(), x + 40.0f - FontLoaders.NMSL18.getStringWidth(((Mode)var25).getModeAsString()) / 2, var24 + 2.0f, -1);
                        if (this.isStringHovered(x, var24 - 5.0f, x + 100.0f, var24 + 15.0f, mouseX, mouseY)) {
                            if (Mouse.isButtonDown(0) && !this.previousmouse) {
                                final Enum var26 = (Enum)((Mode)var25).getValue();
                                final int next = (var26.ordinal() + 1 >= ((Mode)var25).getModes().length) ? 0 : (var26.ordinal() + 1);
                                var25.setValue((Object)((Mode)var25).getModes()[next]);
                                this.previousmouse = true;
                            }
                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }
                        var24 += 25.0f;
                    }
                }
            }
        }
        else if (this.currentModule != null) {
            float var24 = this.startY + 30.0f;
            for (int i = 0; i < Client.instance.getModuleManager().getModulesInType(this.currentModuleType).size(); ++i) {
                final Module mod = Client.instance.getModuleManager().getModulesInType(this.currentModuleType).get(i);
                final Module value2 = Client.instance.getModuleManager().getModulesInType(this.currentModuleType).get(i);
                if (var24 > this.startY + 300.0f) {
                    break;
                }
                RenderUtil.drawFastRoundedRect(this.startX + 195.0f, var24, this.startX + 65.0f, var24 + 20.0f, 1.0f, new Color(40, 40, 40, 20).getRGB());
                if (i >= this.moduleStart) {
                    FontLoaders.NMSL16.drawString(value2.getName(), this.startX + 86.0f, var24 + 8.0f, new Color(248, 248, 248, (int)this.opacity.getOpacity()).getRGB());
                    if (!value2.isEnabled()) {
                        ClickGuiRenderUtil.drawFilledCircle((double)(this.startX + 75.0f), (double)(var24 + 10.0f), 3.0, new Color(255, 0, 0).getRGB(), 50);
                    }
                    else {
                        ClickGuiRenderUtil.drawFilledCircle((double)(this.startX + 75.0f), (double)(var24 + 10.0f), 3.0, new Color(0, 255, 0).getRGB(), 50);
                    }
                    RenderUtil.drawFastRoundedRect(this.startX + 65.0f, var24, this.startX + 183.0f, var24 + 16.0f + FontLoaders.NMSL20.getHeight() - 5.0f, 2.0f, mod.isEnabled() ? new Color(153, 51, 250, 75).getRGB() : new Color(100, 100, 100, 45).getRGB());
                    if (this.isSettingsButtonHovered(this.startX + 65.0f, var24, this.startX + 183.0f, var24 + 16.0f + FontLoaders.NMSL20.getHeight() - 5.0f, mouseX, mouseY)) {
                        if (!this.previousmouse && Mouse.isButtonDown(0)) {
                            if (value2.isEnabled()) {
                                value2.setEnabled(false);
                            }
                            else {
                                value2.setEnabled(true);
                            }
                            this.previousmouse = true;
                        }
                        if (!this.previousmouse && Mouse.isButtonDown(1)) {
                            this.previousmouse = true;
                        }
                    }
                    if (!Mouse.isButtonDown(0)) {
                        this.previousmouse = false;
                    }
                    if (this.isSettingsButtonHovered(this.startX + 90.0f, var24, this.startX + 100.0f + FontLoaders.NMSL20.getStringWidth(value2.getName()), var24 + 8.0f + FontLoaders.NMSL20.getHeight(), mouseX, mouseY) && Mouse.isButtonDown(1)) {
                        this.currentModule = value2;
                        this.valueStart = 0;
                    }
                    var24 += 25.0f;
                }
            }
            var24 = this.startY + 30.0f;
            for (int i = 0; i < this.currentModule.getValues().size() && var24 <= this.startY + 280.0f; ++i) {
                if (i >= this.valueStart) {
                    final Value var25 = this.currentModule.getValues().get(i);
                    if (var25 instanceof Numbers) {
                        final float x = this.startX + 300.0f;
                        double current = 68.0f * (((Number)var25.getValue()).floatValue() - ((Numbers)var25).getMinimum().floatValue()) / (((Numbers)var25).getMaximum().floatValue() - ((Numbers)var25).getMinimum().floatValue());
                        ClickGuiRenderUtil.drawRect(x - 6.0f, var24 + 2.0f, (float)(x + 75.0), var24 + 3.0f, new Color(50, 50, 50, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x - 6.0f, var24 + 2.0f, (float)(x + current + 6.5), var24 + 3.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect((float)(x + current + 2.0), var24, (float)(x + current + 7.0), var24 + 5.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        final boolean mouseAtPlus = this.mouseX > x + 80.0f && mouseX < x + 90.0f && mouseY > var24 - 2.5f && mouseY < var24 + 7.5f;
                        RenderUtil.drawFastRoundedRect(x + 80.0f, var24 - 2.5f, x + 90.0f, var24 + 7.5f, 0.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawCenteredString("+", x + 85.0f, var24 - 0.5f, -1);
                        if (mouseAtPlus && Mouse.isButtonDown(0) && this.lcTimer.hasReached(150.0)) {
                            if (((Number)var25.getValue()).floatValue() >= ((Numbers)var25).getMaximum().doubleValue()) {
                                this.lcTimer.reset();
                            }
                            else {
                                final float append = ((Numbers)var25).inc.floatValue();
                                final DecimalFormat df = new DecimalFormat("0.00");
                                final double finalPlus = Double.parseDouble(df.format(((Number)var25.getValue()).floatValue() + append));
                                var25.setValue((Object)finalPlus);
                                this.lcTimer.reset();
                            }
                        }
                        final boolean mouseAtCut = this.mouseX > x + 93.0f && mouseX < x + 103.0f && mouseY > var24 - 2.5f && mouseY < var24 + 7.5f;
                        RenderUtil.drawFastRoundedRect(x + 93.0f, var24 - 2.5f, x + 103.0f, var24 + 7.5f, 0.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawCenteredString("-", x + 97.5f, var24 - 0.5f, -1);
                        if (mouseAtCut && Mouse.isButtonDown(0) && this.rcTimer.hasReached(150.0)) {
                            if (((Number)var25.getValue()).floatValue() <= ((Numbers)var25).getMinimum().doubleValue()) {
                                this.rcTimer.reset();
                            }
                            else {
                                final float append2 = ((Numbers)var25).inc.floatValue();
                                final DecimalFormat df2 = new DecimalFormat("0.00");
                                final double finalPlus2 = Double.parseDouble(df2.format(((Number)var25.getValue()).floatValue() - append2));
                                var25.setValue((Object)finalPlus2);
                                this.rcTimer.reset();
                            }
                        }
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName() + ": " + var25.getValue(), this.startX + 210.0f, var24, -1);
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }
                        if (this.isButtonHovered(x, var24 - 2.0f, x + 75.0f, var24 + 7.0f, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                current = ((Numbers)var25).getMinimum().doubleValue();
                                final double max = ((Numbers)var25).getMaximum().doubleValue();
                                final double inc = ((Numbers)var25).getIncrement().doubleValue();
                                final double valAbs = mouseX - (x + 1.0);
                                double perc = valAbs / 68.0;
                                perc = Math.min(Math.max(0.0, perc), 1.0);
                                final double valRel = (max - current) * perc;
                                double val2 = current + valRel;
                                val2 = Math.round(val2 * (1.0 / inc)) / (1.0 / inc);
                                ((Numbers)var25).setValue((Object)val2);
                            }
                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }
                        var24 += 20.0f;
                    }
                    if (var25 instanceof Option) {
                        final float x = this.startX + 300.0f;
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24, x + 76.0f, var24 + 1.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24 + 8.0f, x + 76.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 56.0f, var24, x + 57.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawRect(x + 77.0f, var24, x + 76.0f, var24 + 9.0f, new Color(255, 255, 255, (int)this.opacity.getOpacity()).getRGB());
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName(), this.startX + 210.0f, var24, -1);
                        if (var25.getValue()) {
                            ClickGuiRenderUtil.drawRect(x + 67.0f, var24 + 2.0f, x + 75.0f, var24 + 7.0f, new Color(61, 141, 255, (int)this.opacity.getOpacity()).getRGB());
                        }
                        else {
                            ClickGuiRenderUtil.drawRect(x + 58.0f, var24 + 2.0f, x + 65.0f, var24 + 7.0f, new Color(150, 150, 150, (int)this.opacity.getOpacity()).getRGB());
                        }
                        if (this.isCheckBoxHovered(x + 56.0f, var24, x + 76.0f, var24 + 9.0f, mouseX, mouseY)) {
                            if (!this.previousmouse && Mouse.isButtonDown(0)) {
                                this.previousmouse = true;
                                this.mouse = true;
                            }
                            if (this.mouse) {
                                var25.setValue((Object)!(boolean)var25.getValue());
                                this.mouse = false;
                            }
                        }
                        if (!Mouse.isButtonDown(0)) {
                            this.previousmouse = false;
                        }
                        var24 += 20.0f;
                    }
                    if (var25 instanceof Mode) {
                        final float x = this.startX + 300.0f;
                        ClickGuiRenderUtil.drawRect(x - 5.0f, var24 - 5.0f, x + 90.0f, var24 + 15.0f, new Color(56, 56, 56, (int)this.opacity.getOpacity()).getRGB());
                        ClickGuiRenderUtil.drawBorderRect((double)(x - 5.0f), (double)(var24 - 5.0f), (double)(x + 90.0f), (double)(var24 + 15.0f), new Color(101, 81, 255, (int)this.opacity.getOpacity()).getRGB(), 2.0);
                        FontLoaders.NMSL18.drawStringWithShadow(var25.getName(), this.startX + 210.0f, var24 + 2.0f, -1);
                        FontLoaders.NMSL18.drawStringWithShadow(((Mode)var25).getModeAsString(), x + 40.0f - FontLoaders.NMSL18.getStringWidth(((Mode)var25).getModeAsString()) / 2, var24 + 2.0f, -1);
                        if (this.isStringHovered(x, var24 - 5.0f, x + 100.0f, var24 + 15.0f, mouseX, mouseY)) {
                            if (Mouse.isButtonDown(0) && !this.previousmouse) {
                                final Enum var26 = (Enum)((Mode)var25).getValue();
                                final int next = (var26.ordinal() + 1 >= ((Mode)var25).getModes().length) ? 0 : (var26.ordinal() + 1);
                                var25.setValue((Object)((Mode)var25).getModes()[next]);
                                this.previousmouse = true;
                            }
                            if (!Mouse.isButtonDown(0)) {
                                this.previousmouse = false;
                            }
                        }
                        var24 += 25.0f;
                    }
                }
            }
        }
        GL11.glPopMatrix();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    public void onGuiClosed() {
        Client.instance.saveConfig(false);
        Client.instance.saveUISettings(false);
        this.opacity.setOpacity(0.0f);
    }
    
    public boolean isStringHovered(final float f, final float y, final float g, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2;
    }
    
    public boolean isSettingsButtonHovered(final float x, final float y, final float x2, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
    
    public boolean isButtonHovered(final float f, final float y, final float g, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2;
    }
    
    public boolean isCheckBoxHovered(final float f, final float y, final float g, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= f && mouseX <= g && mouseY >= y && mouseY <= y2;
    }
    
    public boolean isCategoryHovered(final float x, final float y, final float x2, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
    
    public boolean isHovered(final float x, final float y, final float x2, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
    
    public boolean mouseWithinBounds2(final int mouseX, final int mouseY, int x, int y, int x1, int y1) {
        if (x > x1) {
            final int i = x;
            x = x1;
            x1 = i;
        }
        if (y > y1) {
            final int j = y;
            y = y1;
            y1 = j;
        }
        return mouseX >= x && mouseX <= x1 && mouseY >= y && mouseY <= y1;
    }
    
    public boolean mouseWithinBounds(final int mouseX, final int mouseY, final int x, final int y, final int x1, final int y1) {
        return this.mouseWithinBounds2(mouseX, mouseY, x, y, x + x1, y + y1);
    }
}
