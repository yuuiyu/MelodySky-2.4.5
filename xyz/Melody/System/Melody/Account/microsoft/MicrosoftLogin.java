//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account.microsoft;

import xyz.Melody.Utils.*;
import net.minecraft.util.*;
import chrriis.dj.nativeswing.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import xyz.Melody.System.Melody.Account.*;
import xyz.Melody.System.Melody.Account.gui.RefreshToken.*;
import java.net.*;
import xyz.Melody.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;
import com.sun.net.httpserver.*;

public final class MicrosoftLogin implements Closeable
{
    private String CLIENT_ID;
    private String REDIRECT_URI;
    private String SCOPE;
    private String URL;
    public volatile String uuid;
    public volatile String userName;
    public volatile String accessToken;
    public volatile String refreshToken;
    public volatile String msToken;
    public volatile String xblToken;
    public volatile String xsts1;
    public volatile String xsts2;
    public volatile boolean initDone;
    public volatile boolean logged;
    public volatile int stage;
    public volatile String status;
    public HttpServer httpServer;
    private final MicrosoftHttpHandler handler;
    private TimerUtil timer;
    public Browser bruhSir;
    public boolean useSystemBrowser;
    
    public MicrosoftLogin(final boolean sysb) throws Exception {
        this.CLIENT_ID = "a1cabc43-47f4-464d-b869-19857d22d739";
        this.REDIRECT_URI = "http://localhost:9810/login";
        this.SCOPE = "XboxLive.signin%20offline_access";
        this.URL = "https://login.live.com/oauth20_authorize.srf?client_id=<client_id>&redirect_uri=<redirect_uri>&response_type=code&display=touch&scope=<scope>".replace("<client_id>", this.CLIENT_ID).replace("<redirect_uri>", this.REDIRECT_URI).replace("<scope>", this.SCOPE);
        this.uuid = null;
        this.userName = null;
        this.accessToken = null;
        this.refreshToken = null;
        this.msToken = null;
        this.xblToken = null;
        this.xsts1 = null;
        this.xsts2 = null;
        this.initDone = false;
        this.logged = false;
        this.stage = 0;
        this.status = "";
        this.timer = new TimerUtil();
        this.stage = 0;
        if (!(this.useSystemBrowser = sysb)) {
            this.status = EnumChatFormatting.YELLOW + "Initializing...";
            this.bruhSir = new Browser("https://account.xbox.com/account/signout", "Initializing...", true, false, true, new NSOption[0]);
            Thread.sleep(2000L);
            this.status = EnumChatFormatting.YELLOW + "Initialized.";
        }
        this.initDone = true;
        Thread.sleep(1500L);
        this.timer.reset();
        this.status = EnumChatFormatting.YELLOW + "Waitting...";
        this.handler = new MicrosoftHttpHandler(this);
        (this.httpServer = HttpServer.create(new InetSocketAddress("localhost", 9810), 0)).createContext("/login", this.handler);
        this.httpServer.start();
        this.show();
    }
    
    private void show() throws Exception {
        if (this.bruhSir != null) {
            this.bruhSir.close();
        }
        if (!this.useSystemBrowser) {
            this.bruhSir = new Browser(this.URL, "Microsoft Login", true, true, true, new NSOption[0]);
        }
        else {
            Desktop.getDesktop().browse(new URI(this.URL));
        }
    }
    
    public MicrosoftLogin(final String refreshToken, final GuiScreen am) throws IOException {
        this.CLIENT_ID = "a1cabc43-47f4-464d-b869-19857d22d739";
        this.REDIRECT_URI = "http://localhost:9810/login";
        this.SCOPE = "XboxLive.signin%20offline_access";
        this.URL = "https://login.live.com/oauth20_authorize.srf?client_id=<client_id>&redirect_uri=<redirect_uri>&response_type=code&display=touch&scope=<scope>".replace("<client_id>", this.CLIENT_ID).replace("<redirect_uri>", this.REDIRECT_URI).replace("<scope>", this.SCOPE);
        this.uuid = null;
        this.userName = null;
        this.accessToken = null;
        this.refreshToken = null;
        this.msToken = null;
        this.xblToken = null;
        this.xsts1 = null;
        this.xsts2 = null;
        this.initDone = false;
        this.logged = false;
        this.stage = 0;
        this.status = "";
        this.timer = new TimerUtil();
        this.refreshToken = refreshToken;
        this.httpServer = null;
        this.handler = null;
        this.stage = 0;
        String microsoftTokenAndRefreshToken;
        String xBoxLiveToken;
        String[] xstsTokenAndHash;
        String accessToken;
        if (am instanceof GuiAltManager && am != null) {
            ((GuiAltManager)am).status = EnumChatFormatting.YELLOW + "Getting Microsoft Token From Refresh Token...";
            microsoftTokenAndRefreshToken = this.getMicrosoftTokenFromRefreshToken(refreshToken);
            ((GuiAltManager)am).status = EnumChatFormatting.YELLOW + "Getting XboxLive Token...";
            xBoxLiveToken = this.getXBoxLiveToken(microsoftTokenAndRefreshToken);
            ((GuiAltManager)am).status = EnumChatFormatting.YELLOW + "Getting XSTS & UHS...";
            xstsTokenAndHash = this.getXSTSTokenAndUserHash(xBoxLiveToken);
            ((GuiAltManager)am).status = EnumChatFormatting.YELLOW + "Getting Access Token...";
            accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
            ((GuiAltManager)am).status = "Logging in from Access Token...";
        }
        else if (am instanceof AddRefreshAuth && am != null) {
            ((AddRefreshAuth)am).status = EnumChatFormatting.YELLOW + "Getting Microsoft Token From Refresh Token...";
            microsoftTokenAndRefreshToken = this.getMicrosoftTokenFromRefreshToken(refreshToken);
            ((AddRefreshAuth)am).status = EnumChatFormatting.YELLOW + "Getting XboxLive Token...";
            xBoxLiveToken = this.getXBoxLiveToken(microsoftTokenAndRefreshToken);
            ((AddRefreshAuth)am).status = EnumChatFormatting.YELLOW + "Getting XSTS & UHS...";
            xstsTokenAndHash = this.getXSTSTokenAndUserHash(xBoxLiveToken);
            ((AddRefreshAuth)am).status = EnumChatFormatting.YELLOW + "Getting Access Token...";
            accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
            ((AddRefreshAuth)am).status = "Logging in from Access Token...";
        }
        else if (am instanceof RefreshTokenAuth && am != null) {
            ((RefreshTokenAuth)am).status = EnumChatFormatting.YELLOW + "Getting Microsoft Token From Refresh Token...";
            microsoftTokenAndRefreshToken = this.getMicrosoftTokenFromRefreshToken(refreshToken);
            ((RefreshTokenAuth)am).status = EnumChatFormatting.YELLOW + "Getting XboxLive Token...";
            xBoxLiveToken = this.getXBoxLiveToken(microsoftTokenAndRefreshToken);
            ((RefreshTokenAuth)am).status = EnumChatFormatting.YELLOW + "Getting XSTS & UHS...";
            xstsTokenAndHash = this.getXSTSTokenAndUserHash(xBoxLiveToken);
            ((RefreshTokenAuth)am).status = EnumChatFormatting.YELLOW + "Getting Access Token...";
            accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
            ((RefreshTokenAuth)am).status = "Logging in from Access Token...";
        }
        else {
            microsoftTokenAndRefreshToken = this.getMicrosoftTokenFromRefreshToken(refreshToken);
            xBoxLiveToken = this.getXBoxLiveToken(microsoftTokenAndRefreshToken);
            xstsTokenAndHash = this.getXSTSTokenAndUserHash(xBoxLiveToken);
            accessToken = this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
        }
        final URL url = new URL("https://api.minecraftservices.com/minecraft/profile");
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setDoInput(true);
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Bearer " + accessToken);
        final String read = this.read(connection.getInputStream());
        final JsonObject jsonObject = (JsonObject)new JsonParser().parse(read);
        final String uuid = jsonObject.get("id").getAsString();
        final String userName = jsonObject.get("name").getAsString();
        this.refreshToken = refreshToken;
        this.msToken = microsoftTokenAndRefreshToken;
        this.xblToken = xBoxLiveToken;
        this.xsts1 = xstsTokenAndHash[0];
        this.xsts2 = xstsTokenAndHash[1];
        this.uuid = uuid;
        this.userName = userName;
        this.accessToken = accessToken;
        this.logged = true;
    }
    
    @Override
    public void close() {
        if (this.httpServer != null) {
            this.httpServer.stop(0);
        }
    }
    
    private String getAccessToken(final String xstsToken, final String uhs) throws IOException {
        this.stage = 4;
        this.status = EnumChatFormatting.YELLOW + "Getting Access Token.";
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
    
    public String getMicrosoftTokenFromRefreshToken(final String refreshToken) throws IOException {
        this.stage = 1;
        this.status = EnumChatFormatting.YELLOW + "Getting Microsoft Token From Refresh Token.";
        Client.instance.logger.info("Getting microsoft token from refresh token");
        final URL url = new URL("https://login.live.com/oauth20_token.srf");
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        final String param = "client_id=" + this.CLIENT_ID + "&refresh_token=" + refreshToken + "&grant_type=refresh_token&redirect_uri=" + this.REDIRECT_URI;
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        this.write(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())), param);
        final JsonObject response_obj = (JsonObject)new JsonParser().parse(this.read(connection.getInputStream()));
        return response_obj.get("access_token").getAsString();
    }
    
    public String[] getMicrosoftTokenAndRefreshToken(final String code) throws IOException {
        this.stage = 1;
        this.status = EnumChatFormatting.YELLOW + "Getting Microsoft Token.";
        Client.instance.logger.info("Getting microsoft token");
        final URL url = new URL("https://login.live.com/oauth20_token.srf");
        final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        final String param = "client_id=" + this.CLIENT_ID + "&code=" + code + "&grant_type=authorization_code&redirect_uri=" + this.REDIRECT_URI + "&scope=" + this.SCOPE;
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        this.write(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())), param);
        final JsonObject response_obj = (JsonObject)new JsonParser().parse(this.read(connection.getInputStream()));
        return new String[] { response_obj.get("access_token").getAsString(), response_obj.get("refresh_token").getAsString() };
    }
    
    public String getXBoxLiveToken(final String microsoftToken) throws IOException {
        this.stage = 2;
        this.status = EnumChatFormatting.YELLOW + "Getting XboxLive Token.";
        Client.instance.logger.info("Getting xbox live token");
        final URL connectUrl = new URL("https://user.auth.xboxlive.com/user/authenticate");
        final JsonObject xbl_param = new JsonObject();
        final JsonObject xbl_properties = new JsonObject();
        xbl_properties.addProperty("AuthMethod", "RPS");
        xbl_properties.addProperty("SiteName", "user.auth.xboxlive.com");
        xbl_properties.addProperty("RpsTicket", "d=" + microsoftToken);
        xbl_param.add("Properties", (JsonElement)xbl_properties);
        xbl_param.addProperty("RelyingParty", "http://auth.xboxlive.com");
        xbl_param.addProperty("TokenType", "JWT");
        final String param = new Gson().toJson((JsonElement)xbl_param);
        final HttpURLConnection connection = (HttpURLConnection)connectUrl.openConnection();
        connection.setDoInput(true);
        connection.setDoOutput(true);
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setRequestProperty("Accept", "application/json");
        this.write(new BufferedWriter(new OutputStreamWriter(connection.getOutputStream())), param);
        final JsonObject response_obj = (JsonObject)new JsonParser().parse(this.read(connection.getInputStream()));
        return response_obj.get("Token").getAsString();
    }
    
    public String[] getXSTSTokenAndUserHash(final String xboxLiveToken) throws IOException {
        this.stage = 3;
        this.status = EnumChatFormatting.YELLOW + "Getting XSTS Token and User Info.";
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
    
    private class MicrosoftHttpHandler implements HttpHandler
    {
        private boolean got;
        private MicrosoftLogin msLog;
        
        public MicrosoftHttpHandler(final MicrosoftLogin msLog) {
            this.got = false;
            this.msLog = msLog;
        }
        
        @Override
        public void handle(final HttpExchange httpExchange) throws IOException {
            if (!this.got) {
                final String query = httpExchange.getRequestURI().getQuery();
                if (query.contains("code")) {
                    this.got = true;
                    final String code = query.split("code=")[1];
                    final String[] microsoftTokenAndRefreshToken = MicrosoftLogin.this.getMicrosoftTokenAndRefreshToken(code);
                    final String xBoxLiveToken = MicrosoftLogin.this.getXBoxLiveToken(microsoftTokenAndRefreshToken[0]);
                    final String[] xstsTokenAndHash = MicrosoftLogin.this.getXSTSTokenAndUserHash(xBoxLiveToken);
                    final String accessToken = MicrosoftLogin.this.getAccessToken(xstsTokenAndHash[0], xstsTokenAndHash[1]);
                    MicrosoftLogin.this.stage = 5;
                    final URL url = new URL("https://api.minecraftservices.com/minecraft/profile");
                    final HttpURLConnection connection = (HttpURLConnection)url.openConnection();
                    connection.setDoInput(true);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Authorization", "Bearer " + accessToken);
                    final String read = MicrosoftLogin.this.read(connection.getInputStream());
                    final JsonObject jsonObject = (JsonObject)new JsonParser().parse(read);
                    final String uuid = jsonObject.get("id").getAsString();
                    final String userName = jsonObject.get("name").getAsString();
                    this.msLog.msToken = microsoftTokenAndRefreshToken[0];
                    this.msLog.xblToken = xBoxLiveToken;
                    this.msLog.xsts1 = xstsTokenAndHash[0];
                    this.msLog.xsts2 = xstsTokenAndHash[1];
                    this.msLog.uuid = uuid;
                    this.msLog.userName = userName;
                    this.msLog.accessToken = accessToken;
                    this.msLog.refreshToken = microsoftTokenAndRefreshToken[1];
                    this.msLog.logged = true;
                    MicrosoftLogin.this.bruhSir.close();
                }
            }
        }
    }
}
