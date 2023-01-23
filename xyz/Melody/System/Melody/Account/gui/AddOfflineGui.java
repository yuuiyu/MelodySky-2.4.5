//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.gui;

import xyz.Melody.Utils.shader.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import xyz.Melody.System.Melody.Account.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class AddOfflineGui extends GuiScreen
{
    private GuiScreen previousScreen;
    private String status;
    private GuiTextField nameField;
    private ScaledResolution sr;
    private GaussianBlur gb;
    
    public AddOfflineGui(final GuiScreen previousScreen) {
        this.status = "Name: ";
        this.gb = new GaussianBlur();
        this.previousScreen = previousScreen;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.sr = new ScaledResolution(this.mc);
        (this.nameField = new GuiTextField(1, this.mc.fontRendererObj, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2 - 30, 200, 20)).setMaxStringLength(32767);
        this.nameField.setFocused(true);
        this.buttonList.add(new GuiButton(997, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2, 200, 20, "Add"));
        this.buttonList.add(new GuiButton(999, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2 + 30, 200, 20, "Back"));
        super.initGui();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.mc.fontRendererObj.drawString(this.status, this.sr.getScaledWidth() / 2 - this.mc.fontRendererObj.getStringWidth(this.status) / 2, this.sr.getScaledHeight() / 2 - 60, Color.WHITE.getRGB());
        this.nameField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 997) {
            try {
                final String username = this.nameField.getText();
                Client.instance.getAccountManager().addAlt((Alt)new OfflineAlt(username));
                this.mc.displayGuiScreen(this.previousScreen);
            }
            catch (Exception e) {
                this.status = "§cError: Couldn't set session (check mc logs)";
                e.printStackTrace();
            }
        }
        if (button.id == 999) {
            this.mc.displayGuiScreen(this.previousScreen);
        }
        super.actionPerformed(button);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        this.nameField.textboxKeyTyped(typedChar, keyCode);
        if (1 == keyCode) {
            this.mc.displayGuiScreen(this.previousScreen);
        }
        else {
            super.keyTyped(typedChar, keyCode);
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
        this.gb.renderBlur(140.0f);
    }
}
