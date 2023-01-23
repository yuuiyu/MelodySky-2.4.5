//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

import by.radioegor146.nativeobfuscator.*;
import net.minecraft.client.multiplayer.*;
import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.Utils.shader.*;
import xyz.Melody.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import xyz.Melody.GUI.Font.*;
import xyz.Melody.System.Melody.Account.altimpl.*;
import net.minecraft.entity.player.*;
import org.lwjgl.input.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.GUI.Notification.*;
import xyz.Melody.Utils.*;
import xyz.Melody.GUI.Menu.*;
import xyz.Melody.System.Melody.Account.gui.*;
import xyz.Melody.injection.mixins.client.*;
import java.net.*;
import org.apache.commons.io.*;
import xyz.Melody.System.Melody.Account.microsoft.*;
import xyz.Melody.System.Melody.Account.gui.AccessToken.*;
import xyz.Melody.System.Melody.Account.gui.XBoxLive.*;
import xyz.Melody.System.Melody.Account.gui.RefreshToken.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.util.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import com.google.gson.*;
import java.util.*;
import java.io.*;

@Native
public final class GuiAltManager extends GuiScreen
{
    private GuiScreen parentScreen;
    private SlidingCalculation slidingCalculation;
    private GuiButton buttonLogin;
    private GuiButton buttonRemove;
    public volatile String status;
    private WorldClient world;
    private EntityPlayerSP player;
    private Opacity opacity;
    private boolean shabi;
    private GaussianBlur gb;
    private DrawEntity entityDrawer;
    private volatile MicrosoftLogin microsoftLogin;
    private static Alt selectAlt;
    private Map<String, ThreadDownloadImageData> avatars;
    private boolean tStarted;
    
    public GuiAltManager(final GuiScreen parentScreen) {
        this.slidingCalculation = new SlidingCalculation(44.0, 44.0);
        this.status = EnumChatFormatting.YELLOW + "Waitting...";
        this.world = null;
        this.player = null;
        this.opacity = new Opacity(20);
        this.shabi = false;
        this.gb = new GaussianBlur();
        this.entityDrawer = new DrawEntity();
        this.avatars = new HashMap<String, ThreadDownloadImageData>();
        this.tStarted = false;
        this.parentScreen = parentScreen;
        this.shabi = false;
        final Client instance = Client.instance;
        final Client instance2 = Client.instance;
        final float n = 0.0f;
        instance2.rotationPitchHead = n;
        instance.prevRotationPitchHead = n;
    }
    
    public GuiAltManager(final GuiScreen parentScreen, final boolean sb) {
        this.slidingCalculation = new SlidingCalculation(44.0, 44.0);
        this.status = EnumChatFormatting.YELLOW + "Waitting...";
        this.world = null;
        this.player = null;
        this.opacity = new Opacity(20);
        this.shabi = false;
        this.gb = new GaussianBlur();
        this.entityDrawer = new DrawEntity();
        this.avatars = new HashMap<String, ThreadDownloadImageData>();
        this.tStarted = false;
        this.parentScreen = parentScreen;
        this.shabi = sb;
        final Client instance = Client.instance;
        final Client instance2 = Client.instance;
        final float n = 0.0f;
        instance2.rotationPitchHead = n;
        instance.prevRotationPitchHead = n;
    }
    
    public void initGui() {
        this.opacity = new Opacity(20);
        Client.instance.getAccountManager().getAltList().sort(Comparator.comparingDouble(alt -> -alt.getAccountType().toString().length()));
        final int appX = 0;
        this.buttonList.add(new ClientButton(0, (int)(this.width / 1.43f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Back", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(1, (int)(this.width / 1.43f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "Add Offline", new Color(10, 10, 10, 110)));
        this.buttonList.add(this.buttonLogin = (GuiButton)new ClientButton(2, (int)(this.width / 6.66f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "Login", new Color(10, 10, 10, 110)));
        this.buttonList.add(this.buttonRemove = (GuiButton)new ClientButton(3, (int)(this.width / 6.66f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Remove", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(5, (int)(this.width / 2.09f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "Microsoft", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(6, (int)(this.width / 2.09f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Add Microsoft", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(7, (int)(this.width / 1.7f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "TokenAuth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(8, (int)(this.width / 1.7f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Add TokenAuth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(11, (int)(this.width / 3.85f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "XBLToken Auth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(12, (int)(this.width / 3.85f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Add XBLT Auth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(15, (int)(this.width / 2.7f) + appX, this.height - 66, (int)(this.width / 9.6f), 20, "RefreshTkn Auth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(16, (int)(this.width / 2.7f) + appX, this.height - 44, (int)(this.width / 9.6f), 20, "Add RefTkn Auth", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(17, (int)(this.width / 5.2f + this.width / 9.6f + 4.0f) + appX, this.height - 130, (int)(this.width / 9.6f), 20, "Cookie Login", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(18, (int)(this.width / 5.2f + this.width / 9.6f + 4.0f) + appX, this.height - 153, (int)(this.width / 9.6f), 20, "Copy UUID:ID", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(9, (int)((int)(this.width / 13.0f) + this.width / 9.6f + 4.0f), this.height - 130, (int)(this.width / 9.6f), 20, "Reset Session", new Color(10, 10, 10, 110)));
        this.buttonList.add(new ClientButton(10, (int)(this.width / 14.0f), this.height - 130, (int)(this.width / 9.6f), 20, "Melody Auth", new Color(10, 10, 10, 110)));
        super.initGui();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        try {
            this.drawDefaultBackground();
            final float appendWidth = 30.0f;
            if (this.shabi) {
                this.opacity.interp(140.0f, 5);
            }
            if (!this.shabi) {
                this.opacity.setOpacity(140.0f);
            }
            if (this.opacity.getOpacity() == 140.0f) {
                this.shabi = false;
            }
            if (Client.sessionChanged) {
                this.mc.thePlayer = null;
                this.mc.theWorld = null;
                this.mc.getRenderManager().livingPlayer = null;
                this.mc.getRenderManager().worldObj = null;
                this.entityDrawer = new DrawEntity();
                Client.sessionChanged = false;
            }
            this.gb.renderBlur(this.opacity.getOpacity());
            if (Client.instance.authenticatingUser) {
                for (final GuiButton b : this.buttonList) {
                    if (b.id != 2 && b.id != 3) {
                        b.enabled = false;
                    }
                }
            }
            else {
                final GuiButton buttonLogin = this.buttonLogin;
                final GuiButton buttonRemove = this.buttonRemove;
                final boolean b2 = GuiAltManager.selectAlt != null;
                buttonRemove.enabled = b2;
                buttonLogin.enabled = b2;
                for (final GuiButton b : this.buttonList) {
                    if (b.id != 2 && b.id != 3) {
                        b.enabled = true;
                    }
                }
            }
            RenderUtil.drawFastRoundedRect(this.width / 2.4f + appendWidth, 50.0f, this.width - 100 + appendWidth, (float)(this.height - 100), 1.0f, new Color(0, 0, 0, 140).getRGB());
            RenderUtil.drawBorderedRect(this.width / 2.4f + appendWidth, 50.0f, this.width - 100 + appendWidth, (float)(this.height - 100), 1.0f, Colors.DARKMAGENTA.c, 0);
            final int shabiX = -30;
            final int shabiY = 5;
            try {
                final int sf = new ScaledResolution(this.mc).getScaleFactor();
                final String jb = "0." + sf;
                final float factor = Float.parseFloat(jb) * 5.0f;
                final int scale = (int)(1.0f / factor * 73.0f);
                if (scale >= 64) {
                    this.entityDrawer.draw((int)(this.width / 5.5f), (int)(this.height / 1.45f), scale, (float)(this.width / 21 - 2 - mouseX / 4), (float)(this.height / 16 - mouseY / 7));
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            this.drawPlayerAvatar(this.mc.getSession().getUsername(), this.mc.getSession().getProfile().getId().toString(), this.width / 20 + shabiX - shabiX * 0.2f, 33 + shabiY - shabiY * 0.3f, 1.45f);
            FontLoaders.CNMD22.drawString(EnumChatFormatting.YELLOW + "Current: " + this.mc.getSession().getUsername(), (float)(this.width / 20 + 102 + shabiX), (float)(74 + shabiY), -1);
            FontLoaders.CNMD20.drawString("UUID: " + this.mc.getSession().getProfile().getId().toString(), (float)(this.width / 20 + 102 + shabiX), (float)(87 + shabiY), -1);
            final String msUser = Client.instance.authenticatingUser ? (EnumChatFormatting.YELLOW + "Authenticating User...") : (Client.instance.authManager.verified ? (EnumChatFormatting.GREEN + "true") : (EnumChatFormatting.GRAY + "false"));
            FontLoaders.CNMD20.drawString("MelodySky Verified: " + msUser, (float)(this.width / 20 + 102 + shabiX), (float)(100 + shabiY), -1);
            FontLoaders.CNMD22.drawString(this.status, (float)(this.width / 20 + 102 + shabiX), (float)(113 + shabiY), -1);
            if (this.tStarted) {
                this.player = null;
                this.tStarted = false;
            }
            float altY = (float)(52.0 - this.slidingCalculation.getCurrent());
            final int jbSize = Client.instance.getAccountManager().getAltList().size();
            final float jbY = (float)this.slidingCalculation.getCurrent() / jbSize * 9.4f;
            RenderUtil.drawFastRoundedRect(this.width - 100 + appendWidth, 52.0f + jbY, this.width - 98 + appendWidth, 62.0f + jbY, 1.0f, Colors.SLOWLY3.c);
            for (final Alt alt : Client.instance.getAccountManager().getAltList()) {
                final String name = alt.getUserName();
                String uuid = "";
                if (alt instanceof OriginalAlt) {
                    if (!((OriginalAlt)alt).getUUID().contains("-")) {
                        final char[] dick = ((OriginalAlt)alt).getUUID().toCharArray();
                        String uid = "";
                        for (int i = 0; i < dick.length; ++i) {
                            if (i == 7 || i == 11 || i == 15 || i == 19) {
                                uid = uid + dick[i] + "-";
                            }
                            else {
                                uid += dick[i];
                            }
                        }
                        uuid = uid;
                    }
                    else {
                        uuid = ((OriginalAlt)alt).getUUID();
                    }
                }
                else if (alt instanceof MicrosoftAlt) {
                    final char[] dick = ((MicrosoftAlt)alt).getUUID().toCharArray();
                    String uid = "";
                    for (int i = 0; i < dick.length; ++i) {
                        if (i == 7 || i == 11 || i == 15 || i == 19) {
                            uid = uid + dick[i] + "-";
                        }
                        else {
                            uid += dick[i];
                        }
                    }
                    uuid = uid;
                }
                else if (alt instanceof XBLTokenAlt) {
                    final char[] dick = ((XBLTokenAlt)alt).getUUID().toCharArray();
                    String uid = "";
                    for (int i = 0; i < dick.length; ++i) {
                        if (i == 7 || i == 11 || i == 15 || i == 19) {
                            uid = uid + dick[i] + "-";
                        }
                        else {
                            uid += dick[i];
                        }
                    }
                    uuid = uid;
                }
                else {
                    uuid = EntityPlayer.getOfflineUUID(name).toString();
                }
                if (altY > 44.0f && altY < 396.0f) {
                    RenderUtil.drawFastRoundedRect(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, 1.0f, new Color(50, 50, 50, 150).getRGB());
                    RenderUtil.drawBorderedRect(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, 1.0f, Colors.GRAY.c, 0);
                    if (this.isHovered(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, mouseX, mouseY)) {
                        RenderUtil.drawBorderedRect(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, 1.0f, Colors.WHITE.c, 0);
                    }
                    if (alt.getUserName().equals(this.mc.getSession().getUsername())) {
                        FontLoaders.NMSL45.drawString("<", this.width - 98 + appendWidth, altY + 8.0f, -1);
                    }
                    if (alt == GuiAltManager.selectAlt) {
                        RenderUtil.drawBorderedRect(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, 1.0f, Colors.BLUE.c, 0);
                    }
                    else if (this.isHovered(this.width / 2.4f + 2.0f + appendWidth, altY, this.width - 102 + appendWidth, altY + 41.0f, mouseX, mouseY) && Mouse.isButtonDown(0)) {
                        GuiAltManager.selectAlt = alt;
                    }
                    this.drawPlayerAvatar(name, uuid, this.width / 2.4f - 13.0f + appendWidth, altY - 15.0f, 1.0f);
                    FontLoaders.CNMD20.drawString("Name: " + name, this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 4), FadeUtil.PURPLE.getColor().getRGB());
                    FontLoaders.CNMD20.drawString("UUID: " + uuid, this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 29), new Color(160, 160, 160, 190).getRGB());
                    switch (llI.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[alt.getAccountType().ordinal()]) {
                        case 1: {
                            FontLoaders.CNMD20.drawString("Offline", this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 16), Colors.GREEN.c);
                            break;
                        }
                        case 2: {
                            FontLoaders.CNMD20.drawString("Microsoft Account", this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 16), Colors.AQUA.c);
                            break;
                        }
                        case 3: {
                            FontLoaders.CNMD20.drawString("Token Auth", this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 16), Colors.YELLOW.c);
                            break;
                        }
                        case 4: {
                            FontLoaders.CNMD20.drawString("XBoxLive Token", this.width / 2.4f + 10.0f + appendWidth + 35.0f, (float)((int)altY + 16), Colors.ORANGE.c);
                            break;
                        }
                    }
                }
                this.slidingCalculation.calculation();
                if (this.slidingCalculation.getCurrent() < 0.0) {
                    this.slidingCalculation.setCurrent(0.0);
                }
                if (this.slidingCalculation.getCurrent() > 44 * (jbSize - 1)) {
                    this.slidingCalculation.setCurrent((jbSize - 1) * 44);
                }
                altY += 44.0f;
            }
            super.drawScreen(mouseX, mouseY, partialTicks);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    public void onGuiClosed() {
        Client.instance.getAccountManager().saveAlt();
        super.onGuiClosed();
    }
    
    protected void actionPerformed(final GuiButton button) throws IOException {
        class lll extends Thread
        {
            final /* synthetic */ GuiAltManager this$0;
            
            lll(final GuiAltManager this$0, final String x0) {
                this.this$0 = this$0;
                super(x0);
            }
            
            @Override
            public void run() {
                try {
                    this.this$0.microsoftLogin = new MicrosoftLogin(true);
                    while (this.this$0.mc.currentScreen instanceof GuiAltManager) {
                        if (this.this$0.microsoftLogin.logged) {
                            this.this$0.microsoftLogin.close();
                            this.this$0.microsoftLogin.status = EnumChatFormatting.GREEN + "Success! " + this.this$0.microsoftLogin.userName;
                            Client.instance.getAccountManager().addAlt((Alt)new MicrosoftAlt(this.this$0.microsoftLogin.userName, this.this$0.microsoftLogin.refreshToken, this.this$0.microsoftLogin.msToken, this.this$0.microsoftLogin.xblToken, this.this$0.microsoftLogin.xsts1, this.this$0.microsoftLogin.xsts2, this.this$0.microsoftLogin.accessToken, this.this$0.microsoftLogin.uuid));
                            NotificationPublisher.queue("Success!", "Added Alt as " + this.this$0.microsoftLogin.userName + ".", NotificationType.SUCCESS, 10000);
                            WindowsNotification.show("MelodySky", "Success! Now you can return to the game.");
                            break;
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    this.this$0.status = EnumChatFormatting.RED + e.getClass().getName() + ":" + e.getMessage();
                    if (this.this$0.microsoftLogin != null) {
                        this.this$0.microsoftLogin.status = EnumChatFormatting.RED + "Failed " + e.getClass().getName() + ":" + e.getMessage();
                        this.this$0.microsoftLogin.close();
                        NotificationPublisher.queue("Failed.", e.getClass().getName() + ":" + e.getMessage(), NotificationType.ERROR, 5000);
                    }
                    this.this$0.microsoftLogin = null;
                }
                this.interrupt();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        net/minecraft/client/gui/GuiButton.id:I
        //     4: ifne            41
        //     7: getstatic       xyz/Melody/Client.instance:Lxyz/Melody/Client;
        //    10: invokevirtual   xyz/Melody/Client.getAccountManager:()Lxyz/Melody/System/Melody/Account/AltManager;
        //    13: invokevirtual   xyz/Melody/System/Melody/Account/AltManager.saveAlt:()V
        //    16: aload_0         /* this */
        //    17: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //    20: new             Lxyz/Melody/GUI/Menu/MainMenu;
        //    23: dup            
        //    24: aload_0         /* this */
        //    25: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.opacity:Lxyz/Melody/GUI/ClickNew/Opacity;
        //    28: invokevirtual   xyz/Melody/GUI/ClickNew/Opacity.getOpacity:()F
        //    31: f2i            
        //    32: invokespecial   xyz/Melody/GUI/Menu/MainMenu.<init>:(I)V
        //    35: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //    38: goto            571
        //    41: aload_1         /* button */
        //    42: getfield        net/minecraft/client/gui/GuiButton.id:I
        //    45: iconst_1       
        //    46: if_icmpne       67
        //    49: aload_0         /* this */
        //    50: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //    53: new             Lxyz/Melody/System/Melody/Account/gui/AddOfflineGui;
        //    56: dup            
        //    57: aload_0         /* this */
        //    58: invokespecial   xyz/Melody/System/Melody/Account/gui/AddOfflineGui.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //    61: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //    64: goto            571
        //    67: aload_1         /* button */
        //    68: getfield        net/minecraft/client/gui/GuiButton.id:I
        //    71: iconst_2       
        //    72: if_icmpne       105
        //    75: getstatic       xyz/Melody/System/Melody/Account/GuiAltManager.selectAlt:Lxyz/Melody/System/Melody/Account/Alt;
        //    78: ifnull          571
        //    81: new             Ljava/lang/Thread;
        //    84: dup            
        //    85: aload_0         /* this */
        //    86: invokedynamic   BootstrapMethod #1, run:(Lxyz/Melody/System/Melody/Account/GuiAltManager;)Ljava/lang/Runnable;
        //    91: ldc_w           "Authentication Thread"
        //    94: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //    97: astore_2        /* shabi */
        //    98: aload_2         /* shabi */
        //    99: invokevirtual   java/lang/Thread.start:()V
        //   102: goto            571
        //   105: aload_1         /* button */
        //   106: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   109: iconst_3       
        //   110: if_icmpne       142
        //   113: getstatic       xyz/Melody/System/Melody/Account/GuiAltManager.selectAlt:Lxyz/Melody/System/Melody/Account/Alt;
        //   116: ifnull          571
        //   119: getstatic       xyz/Melody/Client.instance:Lxyz/Melody/Client;
        //   122: invokevirtual   xyz/Melody/Client.getAccountManager:()Lxyz/Melody/System/Melody/Account/AltManager;
        //   125: invokevirtual   xyz/Melody/System/Melody/Account/AltManager.getAltList:()Ljava/util/ArrayList;
        //   128: getstatic       xyz/Melody/System/Melody/Account/GuiAltManager.selectAlt:Lxyz/Melody/System/Melody/Account/Alt;
        //   131: invokevirtual   java/util/ArrayList.remove:(Ljava/lang/Object;)Z
        //   134: pop            
        //   135: aconst_null    
        //   136: putstatic       xyz/Melody/System/Melody/Account/GuiAltManager.selectAlt:Lxyz/Melody/System/Melody/Account/Alt;
        //   139: goto            571
        //   142: aload_1         /* button */
        //   143: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   146: iconst_5       
        //   147: if_icmpne       168
        //   150: aload_0         /* this */
        //   151: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   154: new             Lxyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin;
        //   157: dup            
        //   158: aload_0         /* this */
        //   159: invokespecial   xyz/Melody/System/Melody/Account/microsoft/GuiMicrosoftLogin.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   162: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   165: goto            571
        //   168: aload_1         /* button */
        //   169: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   172: bipush          6
        //   174: if_icmpne       195
        //   177: aload_0         /* this */
        //   178: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   181: new             Lxyz/Melody/System/Melody/Account/microsoft/GuiAddMicrosoftAlt;
        //   184: dup            
        //   185: aload_0         /* this */
        //   186: invokespecial   xyz/Melody/System/Melody/Account/microsoft/GuiAddMicrosoftAlt.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   189: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   192: goto            571
        //   195: aload_1         /* button */
        //   196: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   199: bipush          7
        //   201: if_icmpne       222
        //   204: aload_0         /* this */
        //   205: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   208: new             Lxyz/Melody/System/Melody/Account/gui/AccessToken/TokenAuthGui;
        //   211: dup            
        //   212: aload_0         /* this */
        //   213: invokespecial   xyz/Melody/System/Melody/Account/gui/AccessToken/TokenAuthGui.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   216: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   219: goto            571
        //   222: aload_1         /* button */
        //   223: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   226: bipush          8
        //   228: if_icmpne       249
        //   231: aload_0         /* this */
        //   232: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   235: new             Lxyz/Melody/System/Melody/Account/gui/AccessToken/AddTokenAuthGui;
        //   238: dup            
        //   239: aload_0         /* this */
        //   240: invokespecial   xyz/Melody/System/Melody/Account/gui/AccessToken/AddTokenAuthGui.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   243: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   246: goto            571
        //   249: aload_1         /* button */
        //   250: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   253: bipush          9
        //   255: if_icmpne       296
        //   258: aload_0         /* this */
        //   259: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   262: checkcast       Lxyz/Melody/injection/mixins/client/MCA;
        //   265: getstatic       xyz/Melody/Client.originalSession:Lnet/minecraft/util/Session;
        //   268: invokeinterface xyz/Melody/injection/mixins/client/MCA.setSession:(Lnet/minecraft/util/Session;)V
        //   273: aload_0         /* this */
        //   274: aconst_null    
        //   275: putfield        xyz/Melody/System/Melody/Account/GuiAltManager.player:Lnet/minecraft/client/entity/EntityPlayerSP;
        //   278: goto            571
        //   281: astore_2        /* e */
        //   282: aload_0         /* this */
        //   283: ldc_w           "§cError: Couldn't Restore Session (check mc logs)."
        //   286: putfield        xyz/Melody/System/Melody/Account/GuiAltManager.status:Ljava/lang/String;
        //   289: aload_2         /* e */
        //   290: invokevirtual   java/lang/Exception.printStackTrace:()V
        //   293: goto            571
        //   296: aload_1         /* button */
        //   297: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   300: bipush          10
        //   302: if_icmpne       326
        //   305: new             Ljava/lang/Thread;
        //   308: dup            
        //   309: invokedynamic   BootstrapMethod #2, run:()Ljava/lang/Runnable;
        //   314: ldc_w           "Account Authentication Thread"
        //   317: invokespecial   java/lang/Thread.<init>:(Ljava/lang/Runnable;Ljava/lang/String;)V
        //   320: invokevirtual   java/lang/Thread.start:()V
        //   323: goto            571
        //   326: aload_1         /* button */
        //   327: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   330: bipush          11
        //   332: if_icmpne       353
        //   335: aload_0         /* this */
        //   336: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   339: new             Lxyz/Melody/System/Melody/Account/gui/XBoxLive/XBLTokenAuth;
        //   342: dup            
        //   343: aload_0         /* this */
        //   344: invokespecial   xyz/Melody/System/Melody/Account/gui/XBoxLive/XBLTokenAuth.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   347: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   350: goto            571
        //   353: aload_1         /* button */
        //   354: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   357: bipush          12
        //   359: if_icmpne       380
        //   362: aload_0         /* this */
        //   363: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   366: new             Lxyz/Melody/System/Melody/Account/gui/XBoxLive/AddXBLTokenAuth;
        //   369: dup            
        //   370: aload_0         /* this */
        //   371: invokespecial   xyz/Melody/System/Melody/Account/gui/XBoxLive/AddXBLTokenAuth.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   374: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   377: goto            571
        //   380: aload_1         /* button */
        //   381: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   384: bipush          15
        //   386: if_icmpne       407
        //   389: aload_0         /* this */
        //   390: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   393: new             Lxyz/Melody/System/Melody/Account/gui/RefreshToken/RefreshTokenAuth;
        //   396: dup            
        //   397: aload_0         /* this */
        //   398: invokespecial   xyz/Melody/System/Melody/Account/gui/RefreshToken/RefreshTokenAuth.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   401: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   404: goto            571
        //   407: aload_1         /* button */
        //   408: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   411: bipush          16
        //   413: if_icmpne       434
        //   416: aload_0         /* this */
        //   417: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   420: new             Lxyz/Melody/System/Melody/Account/gui/RefreshToken/AddRefreshAuth;
        //   423: dup            
        //   424: aload_0         /* this */
        //   425: invokespecial   xyz/Melody/System/Melody/Account/gui/RefreshToken/AddRefreshAuth.<init>:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   428: invokevirtual   net/minecraft/client/Minecraft.displayGuiScreen:(Lnet/minecraft/client/gui/GuiScreen;)V
        //   431: goto            571
        //   434: aload_1         /* button */
        //   435: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   438: bipush          17
        //   440: if_icmpne       467
        //   443: aload_0         /* this */
        //   444: ldc_w           "Waitting For Actions From Browser..."
        //   447: putfield        xyz/Melody/System/Melody/Account/GuiAltManager.status:Ljava/lang/String;
        //   450: new             Lxyz/Melody/System/Melody/Account/lll;
        //   453: dup            
        //   454: aload_0         /* this */
        //   455: ldc_w           "Cookie Login Thread"
        //   458: invokespecial   xyz/Melody/System/Melody/Account/lll.<init>:(Lxyz/Melody/System/Melody/Account/GuiAltManager;Ljava/lang/String;)V
        //   461: invokevirtual   xyz/Melody/System/Melody/Account/lll.start:()V
        //   464: goto            571
        //   467: aload_1         /* button */
        //   468: getfield        net/minecraft/client/gui/GuiButton.id:I
        //   471: bipush          18
        //   473: if_icmpne       571
        //   476: invokestatic    java/awt/Toolkit.getDefaultToolkit:()Ljava/awt/Toolkit;
        //   479: invokevirtual   java/awt/Toolkit.getSystemClipboard:()Ljava/awt/datatransfer/Clipboard;
        //   482: astore_2        /* clip */
        //   483: new             Ljava/awt/datatransfer/StringSelection;
        //   486: dup            
        //   487: new             Ljava/lang/StringBuilder;
        //   490: dup            
        //   491: invokespecial   java/lang/StringBuilder.<init>:()V
        //   494: aload_0         /* this */
        //   495: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   498: invokevirtual   net/minecraft/client/Minecraft.getSession:()Lnet/minecraft/util/Session;
        //   501: invokevirtual   net/minecraft/util/Session.getProfile:()Lcom/mojang/authlib/GameProfile;
        //   504: invokevirtual   com/mojang/authlib/GameProfile.getId:()Ljava/util/UUID;
        //   507: invokevirtual   java/util/UUID.toString:()Ljava/lang/String;
        //   510: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   513: ldc_w           ":"
        //   516: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   519: aload_0         /* this */
        //   520: getfield        xyz/Melody/System/Melody/Account/GuiAltManager.mc:Lnet/minecraft/client/Minecraft;
        //   523: invokevirtual   net/minecraft/client/Minecraft.getSession:()Lnet/minecraft/util/Session;
        //   526: invokevirtual   net/minecraft/util/Session.getUsername:()Ljava/lang/String;
        //   529: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   532: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   535: invokespecial   java/awt/datatransfer/StringSelection.<init>:(Ljava/lang/String;)V
        //   538: astore_3        /* tText */
        //   539: aload_2         /* clip */
        //   540: aload_3         /* tText */
        //   541: aconst_null    
        //   542: invokevirtual   java/awt/datatransfer/Clipboard.setContents:(Ljava/awt/datatransfer/Transferable;Ljava/awt/datatransfer/ClipboardOwner;)V
        //   545: aload_0         /* this */
        //   546: new             Ljava/lang/StringBuilder;
        //   549: dup            
        //   550: invokespecial   java/lang/StringBuilder.<init>:()V
        //   553: getstatic       net/minecraft/util/EnumChatFormatting.GREEN:Lnet/minecraft/util/EnumChatFormatting;
        //   556: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/Object;)Ljava/lang/StringBuilder;
        //   559: ldc_w           "Copied UUID:ID To Your Clipboard."
        //   562: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   565: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   568: putfield        xyz/Melody/System/Melody/Account/GuiAltManager.status:Ljava/lang/String;
        //   571: aload_0         /* this */
        //   572: aload_1         /* button */
        //   573: invokespecial   net/minecraft/client/gui/GuiScreen.actionPerformed:(Lnet/minecraft/client/gui/GuiButton;)V
        //   576: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    StackMapTable: 00 11 29 19 25 24 19 1A 1A 1A 5F 07 00 FE 0E 1D 1A 1A 1A 1A 20 FB 00 67
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  258    278    281    296    Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
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
    }
    
    private void drawPlayerAvatar(final String name, final String uuid, final float x, final float y, final float scale) {
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        GlStateManager.scale(scale, scale, 0.0f);
        GlStateManager.translate(-scale, -scale, 0.0f);
        try {
            ThreadDownloadImageData ab;
            if (this.avatars.containsKey(uuid)) {
                ab = this.avatars.get(uuid);
            }
            else {
                this.avatars.put(uuid, ab = this.getDownloadImageHead(AbstractClientPlayer.getLocationSkin(uuid), uuid));
            }
            ab.loadTexture(Minecraft.getMinecraft().getResourceManager());
            this.mc.getTextureManager().bindTexture(AbstractClientPlayer.getLocationSkin(uuid));
            Gui.drawModalRectWithCustomSizedTexture((int)(x + 18.0f), (int)(y + 18.0f), 37.0f, 37.0f, 37, 37, 297.0f, 297.0f);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        GlStateManager.popMatrix();
    }
    
    public ThreadDownloadImageData getDownloadImageHead(final ResourceLocation resourceLocationIn, final String uuid) {
        final TextureManager tm = Minecraft.getMinecraft().getTextureManager();
        final Object tex = new ThreadDownloadImageData((File)null, "https://crafatar.com/skins/" + uuid, DefaultPlayerSkin.getDefaultSkin(AbstractClientPlayer.getOfflineUUID(uuid)), (IImageBuffer)new ImageBufferDownload());
        tm.loadTexture(resourceLocationIn, (ITextureObject)tex);
        return (ThreadDownloadImageData)tex;
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
    
    public String getXBoxLiveToken(final String microsoftToken) throws IOException {
        this.status = EnumChatFormatting.YELLOW + "Getting XboxLive Token...";
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
    
    public boolean isHovered(final float x, final float y, final float x2, final float y2, final int mouseX, final int mouseY) {
        return mouseX >= x && mouseX <= x2 && mouseY >= y && mouseY <= y2;
    }
}
