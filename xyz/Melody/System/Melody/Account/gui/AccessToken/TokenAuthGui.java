//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.gui.AccessToken;

import xyz.Melody.Utils.shader.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import java.net.*;
import org.apache.commons.io.*;
import xyz.Melody.GUI.Notification.*;
import xyz.Melody.injection.mixins.client.*;
import net.minecraft.util.*;
import com.google.gson.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class TokenAuthGui extends GuiScreen
{
    private GuiScreen previousScreen;
    private String status;
    private GuiTextField sessionField;
    private ScaledResolution sr;
    private GaussianBlur gb;
    
    public TokenAuthGui(final GuiScreen previousScreen) {
        this.status = "Session:";
        this.gb = new GaussianBlur();
        this.previousScreen = previousScreen;
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.sr = new ScaledResolution(this.mc);
        (this.sessionField = new GuiTextField(1, this.mc.fontRendererObj, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2 - 30, 200, 20)).setMaxStringLength(32767);
        this.sessionField.setFocused(true);
        this.buttonList.add(new GuiButton(997, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2, 200, 20, "Login"));
        this.buttonList.add(new GuiButton(999, this.sr.getScaledWidth() / 2 - 100, this.sr.getScaledHeight() / 2 + 30, 200, 20, "Back"));
        super.initGui();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        super.onGuiClosed();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        this.drawDefaultBackground();
        this.mc.fontRendererObj.drawString("Input Format: 1.name:uuid:token 2.token", 20, 13, Color.WHITE.getRGB());
        this.mc.fontRendererObj.drawString("1.name:uuid:token ", 35, 24, Color.WHITE.getRGB());
        this.mc.fontRendererObj.drawString("2.token", 35, 35, Color.WHITE.getRGB());
        this.mc.fontRendererObj.drawString(this.status, this.sr.getScaledWidth() / 2 - this.mc.fontRendererObj.getStringWidth(this.status) / 2, this.sr.getScaledHeight() / 2 - 60, Color.WHITE.getRGB());
        this.sessionField.drawTextBox();
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        if (button.id == 997) {
            try {
                final String session = this.sessionField.getText();
                String username;
                String uuid;
                String token;
                if (session.contains(":")) {
                    username = session.split(":")[0];
                    uuid = session.split(":")[1];
                    token = session.split(":")[2];
                }
                else {
                    final HttpURLConnection c = (HttpURLConnection)new URL("https://api.minecraftservices.com/minecraft/profile/").openConnection();
                    c.setRequestProperty("Content-type", "application/json");
                    c.setRequestProperty("Authorization", "Bearer " + this.sessionField.getText());
                    c.setDoOutput(true);
                    final JsonObject json = new JsonParser().parse(IOUtils.toString(c.getInputStream())).getAsJsonObject();
                    username = json.get("name").getAsString();
                    uuid = json.get("id").getAsString();
                    token = session;
                    NotificationPublisher.queue("Account Loggedin!", "Logged in as: " + this.mc.getSession().getUsername(), NotificationType.SUCCESS, 3000);
                }
                ((MCA)this.mc).setSession(new Session(username, uuid, token, "mojang"));
                this.mc.displayGuiScreen(this.previousScreen);
            }
            catch (Exception e) {
                this.status = "§cError: Couldn't set session (check mc logs)";
                e.printStackTrace();
            }
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
        this.sessionField.textboxKeyTyped(typedChar, keyCode);
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
