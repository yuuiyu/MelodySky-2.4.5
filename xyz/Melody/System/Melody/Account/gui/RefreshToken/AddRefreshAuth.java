//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.gui.RefreshToken;

import xyz.Melody.Utils.shader.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import net.minecraft.util.*;
import xyz.Melody.System.Melody.Account.microsoft.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import xyz.Melody.System.Melody.Account.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class AddRefreshAuth extends GuiScreen
{
    private GuiScreen previousScreen;
    public String status;
    private GuiTextField xbltField;
    private ScaledResolution sr;
    private GaussianBlur gb;
    
    public AddRefreshAuth(final GuiScreen previousScreen) {
        this.status = "Refresh Token:";
        this.gb = new GaussianBlur();
        this.previousScreen = previousScreen;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.sr = new ScaledResolution(this.mc);
        (this.xbltField = new GuiTextField(1, this.mc.fontRendererObj, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2 - 30, 200, 20)).setMaxStringLength(32767);
        this.xbltField.setFocused(true);
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
        this.xbltField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 997) {
            MicrosoftLogin microsoftLogin;
            new Thread(() -> {
                this.status = EnumChatFormatting.YELLOW + "Loggingin...";
                try {
                    microsoftLogin = new MicrosoftLogin(this.xbltField.getText(), this);
                    if (microsoftLogin.logged) {
                        Client.instance.getAccountManager().addAlt((Alt)new MicrosoftAlt(microsoftLogin.userName, microsoftLogin.refreshToken, microsoftLogin.msToken, microsoftLogin.xblToken, microsoftLogin.xsts1, microsoftLogin.xsts2, microsoftLogin.accessToken, microsoftLogin.uuid));
                        this.status = EnumChatFormatting.GREEN + "Success! " + this.mc.getSession().getUsername();
                    }
                }
                catch (IOException ioException) {
                    ioException.printStackTrace();
                    this.status = EnumChatFormatting.RED + "Failed. " + ioException.getClass().getName() + ": " + ioException.getMessage();
                }
                return;
            }, "RefreshToken Authenticator").start();
        }
        if (button.id == 999) {
            try {
                this.mc.displayGuiScreen(this.previousScreen);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        super.actionPerformed(button);
    }
    
    protected void keyTyped(final char typedChar, final int keyCode) throws IOException {
        this.xbltField.textboxKeyTyped(typedChar, keyCode);
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
