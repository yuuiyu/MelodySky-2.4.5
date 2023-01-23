//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.gui.XBoxLive;

import xyz.Melody.Utils.shader.*;
import org.lwjgl.input.*;
import net.minecraft.client.gui.*;
import java.awt.*;
import java.net.*;
import org.apache.commons.io.*;
import xyz.Melody.injection.mixins.client.*;
import xyz.Melody.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import xyz.Melody.System.Melody.Account.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;

public class AddXBLTokenAuth extends GuiScreen
{
    private GuiScreen previousScreen;
    private String status;
    private GuiTextField xbltField;
    private ScaledResolution sr;
    private GaussianBlur gb;
    
    public AddXBLTokenAuth(final GuiScreen previousScreen) {
        this.status = "XBox Live Token:";
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
            String XBLToken;
            String[] xstsTokenAndHash;
            String accessToken;
            HttpURLConnection c;
            JsonObject json;
            String username;
            String xstsToken_f;
            String xstsToken_s;
            String uuid;
            String token;
            new Thread(() -> {
                try {
                    XBLToken = this.xbltField.getText();
                    xstsTokenAndHash = this.getXSTSTokenAndUserHash(XBLToken);
                    accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
                    this.status = "Logging in from AccessToken...";
                    c = (HttpURLConnection)new URL("https://api.minecraftservices.com/minecraft/profile/").openConnection();
                    c.setRequestProperty("Content-type", "application/json");
                    c.setRequestProperty("Authorization", "Bearer " + accessToken);
                    c.setDoOutput(true);
                    json = new JsonParser().parse(IOUtils.toString(c.getInputStream())).getAsJsonObject();
                    username = json.get("name").getAsString();
                    xstsToken_f = xstsTokenAndHash[0];
                    xstsToken_s = xstsTokenAndHash[1];
                    uuid = json.get("id").getAsString();
                    token = accessToken;
                    ((MCA)this.mc).setSession(new Session(username, uuid, token, "mojang"));
                    Client.instance.getAccountManager().addAlt((Alt)new XBLTokenAlt(username, XBLToken, xstsToken_f, xstsToken_s, accessToken, uuid, "mojang"));
                    this.status = EnumChatFormatting.GREEN + "Success! " + username;
                }
                catch (Exception e) {
                    this.status = EnumChatFormatting.RED + "Failed: " + e.getMessage();
                    e.printStackTrace();
                }
                return;
            }, "XBLT Authenticator").start();
        }
        if (button.id == 999) {
            try {
                this.mc.displayGuiScreen(this.previousScreen);
            }
            catch (Exception e2) {
                e2.printStackTrace();
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
    
    private String getAccessToken(final String xstsToken, final String uhs) throws IOException {
        this.status = EnumChatFormatting.YELLOW + "Getting Access Token...";
        Client.instance.logger.info("Getting access token");
        final URL url = new URL("https://api.minecraftservices.com/authentication/login_with_xbox");
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        connection.setDoInput(true);
        connection.setDoOutput(true);
        final JsonObject input = new JsonObject();
        input.addProperty("identityToken", "XBL3.0 x=" + uhs + ";" + xstsToken);
        this.write(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())), new Gson().toJson((JsonElement)input));
        final JsonObject jsonObject = (JsonObject)new JsonParser().parse(this.read(connection.getInputStream()));
        return jsonObject.get("access_token").getAsString();
    }
    
    public String[] getXSTSTokenAndUserHash(final String xboxLiveToken) throws IOException {
        this.status = EnumChatFormatting.YELLOW + "Getting XSTS Token and User Info...";
        Client.instance.logger.info("Getting xsts token and user hash");
        final URL ConnectUrl = new URL("https://xsts.auth.xboxlive.com/xsts/authorize");
        final ArrayList<String> tokens = new ArrayList<String>();
        tokens.add(xboxLiveToken);
        final JsonObject xbl_param = new JsonObject();
        final JsonObject xbl_properties = new JsonObject();
        xbl_properties.addProperty("SandboxId", "RETAIL");
        xbl_properties.add("UserTokens", new JsonParser().parse(new Gson().toJson((Object)tokens)));
        xbl_param.add("Properties", (JsonElement)xbl_properties);
        xbl_param.addProperty("RelyingParty", "rp://api.minecraftservices.com/");
        xbl_param.addProperty("TokenType", "JWT");
        final String param = new Gson().toJson((JsonElement)xbl_param);
        final HttpURLConnection connection = (HttpURLConnection)ConnectUrl.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        this.write(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())), param);
        final JsonObject response_obj = (JsonObject)new JsonParser().parse(this.read(connection.getInputStream()));
        final String token = response_obj.get("Token").getAsString();
        final String uhs = response_obj.getAsJsonObject("DisplayClaims").getAsJsonArray("xui").get(0).getAsJsonObject().get("uhs").getAsString();
        return new String[] { token, uhs };
    }
    
    private void write(final BufferedWriter writer, final String s) throws IOException {
        writer.write(s);
        writer.close();
    }
    
    private String read(final InputStream stream) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        final StringBuilder stringBuilder = new StringBuilder();
        String s;
        while ((s = reader.readLine()) != null) {
            stringBuilder.append(s);
        }
        stream.close();
        reader.close();
        return stringBuilder.toString();
    }
}
