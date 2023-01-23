//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import xyz.Melody.GUI.Particles.Winter.*;
import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.Utils.shader.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import xyz.Melody.GUI.Click.*;
import chrriis.dj.nativeswing.*;
import chrriis.dj.nativeswing.swtimpl.components.*;
import net.minecraft.client.gui.*;
import xyz.Melody.System.Melody.Account.*;
import java.text.*;
import xyz.Melody.GUI.Particles.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.util.*;
import xyz.Melody.Utils.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.GUI.Font.*;
import java.util.*;
import java.io.*;
import java.lang.reflect.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.realms.*;

public class MainMenu extends GuiScreen implements GuiYesNoCallback
{
    private TimerUtil restTimer;
    private String title;
    private boolean m;
    private boolean e;
    private boolean l;
    private boolean o;
    private boolean d;
    private boolean y;
    private TimerUtil timer;
    private TimerUtil dick;
    private TimerUtil saveTimer;
    private int letterDrawn;
    private boolean titleDone;
    private ArrayList<MenuParticle> particles;
    private Random RANDOM;
    private int particleCount;
    private ParticleEngine winterParticles;
    private Opacity opacity;
    private boolean CO;
    private GaussianBlur gblur;
    Animation anim;
    
    public MainMenu(final int opacity) {
        this.restTimer = new TimerUtil();
        this.title = "";
        this.m = false;
        this.e = false;
        this.l = false;
        this.o = false;
        this.d = false;
        this.y = false;
        this.timer = new TimerUtil();
        this.dick = new TimerUtil();
        this.saveTimer = new TimerUtil();
        this.letterDrawn = 0;
        this.titleDone = false;
        this.particles = new ArrayList<MenuParticle>();
        this.RANDOM = new Random();
        this.particleCount = 7000;
        this.winterParticles = new ParticleEngine();
        this.CO = false;
        this.gblur = new GaussianBlur();
        this.anim = (Animation)new l(this);
        this.opacity = new Opacity(opacity);
        this.CO = true;
    }
    
    public MainMenu() {
        this.restTimer = new TimerUtil();
        this.title = "";
        this.m = false;
        this.e = false;
        this.l = false;
        this.o = false;
        this.d = false;
        this.y = false;
        this.timer = new TimerUtil();
        this.dick = new TimerUtil();
        this.saveTimer = new TimerUtil();
        this.letterDrawn = 0;
        this.titleDone = false;
        this.particles = new ArrayList<MenuParticle>();
        this.RANDOM = new Random();
        this.particleCount = 7000;
        this.winterParticles = new ParticleEngine();
        this.CO = false;
        this.gblur = new GaussianBlur();
        this.anim = (Animation)new l(this);
        this.CO = false;
    }
    
    public void initGui() {
        if (Client.firstMenu && Client.instance.authManager.verified) {
            this.mc.displayGuiScreen((GuiScreen)new GuiWelcome());
        }
        final int opac = (int)(this.CO ? this.opacity.getOpacity() : 140.0f);
        this.opacity = new Opacity(opac);
        this.CO = false;
        this.m = false;
        this.e = false;
        this.l = false;
        this.o = false;
        this.d = false;
        this.y = false;
        this.winterParticles.particles.clear();
        this.particles.clear();
        this.restTimer.reset();
        this.timer.reset();
        this.letterDrawn = 0;
        this.title = "";
        this.titleDone = false;
        this.buttonList.add(new ClientButton(0, this.width / 2 - 80, this.height / 2 - 60, 160, 20, "Single Player", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(1, this.width / 2 - 80, this.height / 2 - 36, 160, 20, "Multi Player", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(2, this.width / 2 - 80, this.height / 2 - 12, 160, 20, "Config Manager", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(3, this.width / 2 - 80, this.height / 2 + 12, 160, 20, "Settings", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(5, this.width / 2 + 2, this.height / 2 + 36, 78, 18, "ChangeLogs", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(15, this.width / 2 - 80, this.height / 2 + 36, 78, 18, "Languages", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(50, this.width - 5, this.height - 5, this.width + 8, this.height + 8, "", (ResourceLocation)null, new Color(0, 0, 0, 0)));
        this.buttonList.add(new ClientButton(16, this.width / 2 - 102, this.height / 2 + 36, 18, 18, "", new ResourceLocation("Melody/icon/realms.png"), new ResourceLocation("Melody/icon/realms_hovered.png"), -3.0f, -3.0f, 12.0f, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(10, this.width - 43, this.height - 40, 32, 32, "", new ResourceLocation("Melody/icon/discord.png"), new Color(20, 20, 20, 0)));
        this.buttonList.add(new ClientButton(11, this.width - 78, this.height - 40, 32, 32, "", new ResourceLocation("Melody/icon/github.png"), new Color(20, 20, 20, 0)));
        this.buttonList.add(new ClientButton(12, this.width - 113, this.height - 40, 32, 32, "", new ResourceLocation("Melody/icon/cnsbtool.png"), -4.0f, -4.0f, 20.0f, new Color(20, 20, 20, 0)));
        this.buttonList.add(new ClientButton(13, this.width - 148, this.height - 40, 32, 32, "", new ResourceLocation("Melody/icon/youtube.png"), new Color(20, 20, 20, 0)));
        this.buttonList.add(new ClientButton(4, this.width - 100, 10, 60, 24, "Vanilla", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(19198, this.width - 165, 10, 60, 24, "Hide Mods", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(14, this.width - 10 - 24, 10, 25, 24, "", new ResourceLocation("Melody/icon/exit.png"), new Color(20, 20, 20, 60)));
        this.anim.on();
        for (int iii = 0; iii < this.particleCount; ++iii) {
            final double randomX = -2.0 + 4.0 * this.RANDOM.nextDouble();
            final double randomY = -2.0 + 4.0 * this.RANDOM.nextDouble();
            final double randomXm = 0.0 + (this.width - 0) * this.RANDOM.nextDouble();
            final double randomYm = 0.0 + (this.height - 0) * this.RANDOM.nextDouble();
            final double randomDepthm = this.RANDOM.nextDouble() + 0.1;
            final int mX = 0;
            final int mY = 0;
            final MenuParticle part = new MenuParticle(randomXm + 0.0, randomYm + 0.0, randomDepthm + 0.0, true).addMotion(randomX + mX / 4, randomY + mY / 4);
            part.alpha = 0.15f;
            this.particles.add(part);
        }
        super.initGui();
    }
    
    protected void actionPerformed(final GuiButton button) {
        switch (button.id) {
            case 0: {
                this.mc.displayGuiScreen((GuiScreen)new GuiSelectWorld((GuiScreen)this));
                break;
            }
            case 1: {
                this.mc.displayGuiScreen((GuiScreen)new GuiMultiplayer((GuiScreen)this));
                break;
            }
            case 2: {
                this.mc.displayGuiScreen((GuiScreen)new ClickUi(true));
                break;
            }
            case 3: {
                this.mc.displayGuiScreen((GuiScreen)new GuiOptions((GuiScreen)this, this.mc.gameSettings));
                break;
            }
            case 4: {
                Client.vanillaMenu = true;
                this.mc.displayGuiScreen((GuiScreen)new GuiMainMenu());
                Client.instance.saveMenuMode();
                break;
            }
            case 5: {
                this.mc.displayGuiScreen((GuiScreen)new GuiChangeLog());
                break;
            }
            case 10: {
                try {
                    this.open("https://discord.gg/VnNCJfEyhU");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 11: {
                try {
                    this.open("https://github.com/NMSLAndy");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 12: {
                try {
                    final Browser browser = new Browser("https://tool.msirp.cn/", "China Skyblock Tool", true, true, false, 800, 550, new NSOption[] { JWebBrowser.useEdgeRuntime() });
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 13: {
                try {
                    this.open("https://www.youtube.com/channel/UCM8A_7JEGLyqlUq7I7BwUVQ");
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            }
            case 14: {
                this.mc.shutdown();
                break;
            }
            case 15: {
                this.mc.displayGuiScreen((GuiScreen)new GuiLanguage((GuiScreen)this, this.mc.gameSettings, this.mc.getLanguageManager()));
                break;
            }
            case 16: {
                this.switchToRealms();
                break;
            }
            case 50: {
                this.mc.displayGuiScreen((GuiScreen)new GuiAltManager(new MainMenu(), true));
                break;
            }
            case 19198: {
                this.mc.displayGuiScreen((GuiScreen)new GuiHideMods((GuiScreen)this));
                break;
            }
        }
    }
    
    private boolean isChristmas() {
        final SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        final Date date = new Date(System.currentTimeMillis());
        final String dStr = formatter.format(date);
        final int dint = Integer.parseInt(dStr);
        return dint > 1225 || dint < 105;
    }
    
    private boolean isWinter() {
        final SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        final Date date = new Date(System.currentTimeMillis());
        final String dStr = formatter.format(date);
        final int dint = Integer.parseInt(dStr);
        return dint >= 1110 || dint <= 131;
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final Tessellator tessellator = Tessellator.getInstance();
        final WorldRenderer worldrenderer = tessellator.getWorldRenderer();
        final CFontRenderer font2 = FontLoaders.CNMD18;
        final CFontRenderer font3 = FontLoaders.CNMD45;
        this.drawDefaultBackground();
        this.gblur.renderBlur(this.opacity.getOpacity());
        this.opacity.interp(20.0f, 4);
        if (this.isWinter()) {
            this.winterParticles.render((float)(this.width / 2), (float)(this.height / 2));
        }
        ParticleUtils.drawParticles(mouseX, mouseY);
        if (this.restTimer.hasReached(20000.0)) {
            this.mc.displayGuiScreen((GuiScreen)new GuiResting());
        }
        if (this.saveTimer.hasReached(1000.0)) {
            Client.instance.saveMenuMode();
            this.saveTimer.reset();
        }
        if (this.timer.hasReached(200.0) && !this.titleDone) {
            if (this.letterDrawn < 1) {
                this.title += "M";
                this.m = true;
                ++this.letterDrawn;
            }
            if (this.timer.hasReached(450.0)) {
                if (this.letterDrawn < 2) {
                    this.title += "e";
                    this.e = true;
                    ++this.letterDrawn;
                }
                if (this.timer.hasReached(700.0)) {
                    if (this.letterDrawn < 3) {
                        this.title += "l";
                        this.l = true;
                        ++this.letterDrawn;
                    }
                    if (this.timer.hasReached(950.0)) {
                        if (this.letterDrawn < 4) {
                            this.title += "o";
                            this.o = true;
                            ++this.letterDrawn;
                        }
                        if (this.timer.hasReached(1200.0)) {
                            if (this.letterDrawn < 5) {
                                this.title += "d";
                                this.d = true;
                                ++this.letterDrawn;
                            }
                            if (this.timer.hasReached(1450.0)) {
                                if (this.letterDrawn < 6) {
                                    this.title += "y";
                                    this.y = true;
                                    ++this.letterDrawn;
                                }
                                if (!this.isChristmas() && this.timer.hasReached(1700.0)) {
                                    if (this.letterDrawn < 7) {
                                        this.title += " ";
                                        ++this.letterDrawn;
                                    }
                                    if (this.timer.hasReached(1950.0)) {
                                        if (this.letterDrawn < 8) {
                                            this.title += "S";
                                            ++this.letterDrawn;
                                        }
                                        if (this.timer.hasReached(2200.0)) {
                                            if (this.letterDrawn < 9) {
                                                this.title += "k";
                                                ++this.letterDrawn;
                                            }
                                            if (this.timer.hasReached(2450.0) && this.letterDrawn < 10) {
                                                this.title += "y";
                                                ++this.letterDrawn;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        final int cx = 2;
        final int cy = -6;
        if (this.isChristmas()) {
            if (this.m) {
                Logo.M((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 21 + cx), (float)(this.height / 2 - 101 + cy), 34.0f, 32.0f);
            }
            if (this.e) {
                Logo.e((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 12 + 30 + cx), (float)(this.height / 2 - 91 + cy), 20.0f, 18.4f);
            }
            if (this.l) {
                Logo.l((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 16 + 28 + 28 + cx), (float)(this.height / 2 - 108 + cy), 20.0f, 40.0f);
            }
            if (this.o) {
                Logo.o((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 25 + 28 + 28 + 28 + cx), (float)(this.height / 2 - 100 + cy), 38.0f, 29.0f);
            }
            if (this.d) {
                Logo.d((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 16 + 28 + 28 + 28 + 28 + cx), (float)(this.height / 2 - 106 + cy), 32.0f, 36.0f);
            }
            if (this.y) {
                Logo.y((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 16 + 28 + 28 + 28 + 28 + 28 + cx), (float)(this.height / 2 - 92 + cy), 21.0f, 32.0f);
            }
        }
        final String t = this.isChristmas() ? "" : this.title;
        font3.drawString(t, (float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 - 3), (float)(this.height / 2 - 107), new Color(138, 43, 226, 160).getRGB());
        if (this.dick.hasReached(600.0)) {
            if (this.isChristmas()) {
                final int am = 72;
                final int ae = 50;
                final int al = 38;
                final int ao = 70;
                final int ad = 55;
                final int ay = 51;
                int w = -170;
                if (this.m && w == -170) {
                    w += am;
                }
                if (this.e && w == -98) {
                    w += ae;
                }
                if (this.l && w == -48) {
                    w += al;
                }
                if (this.o && w == -10) {
                    w += ao;
                }
                if (this.d && w == 60) {
                    w += ad;
                }
                if (this.y && w == 115) {
                    w += ay;
                }
                RenderUtil.drawFastRoundedRect((float)(this.width / 2 - w / 2 + w + 4 - 3), (float)(this.height / 2 - 108), (float)(this.width / 2 - w / 2 + w + 5 - 3), (float)(this.height / 2 - 76), 1.0f, new Color(198, 198, 198).getRGB());
            }
            else {
                RenderUtil.drawFastRoundedRect((float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 + font3.getStringWidth(this.title) + 4 - 3), (float)(this.height / 2 - 108), (float)(this.width / 2 - font3.getStringWidth("Melody Sky") / 2 + font3.getStringWidth(this.title) + 5 - 3), (float)(this.height / 2 - 83), 1.0f, new Color(198, 198, 198).getRGB());
            }
            if (this.dick.hasReached(1200.0)) {
                this.dick.reset();
            }
        }
        this.mc.fontRendererObj.drawString("?2019-2022 MelodyWorkGroup", 4, this.height - 10, new Color(20, 20, 20, 180).getRGB());
        RenderUtil.drawFastRoundedRect((float)(this.width - 153), (float)(this.height - 43), (float)(this.width - 8), (float)(this.height - 5), 1.0f, new Color(100, 180, 255, 20).getRGB());
        RenderUtil.drawFastRoundedRect(10.0f, 10.0f, 186.0f, 50.0f, 1.0f, new Color(20, 20, 20, 100).getRGB());
        font2.drawCenteredString("Logged in as: " + EnumChatFormatting.BLUE + this.mc.getSession().getUsername(), 98.0f, 20.0f, Colors.GRAY.c);
        font2.drawCenteredString(EnumChatFormatting.GRAY + "Released Build " + EnumChatFormatting.GREEN + "2.4.5Build2", 98.0f, 34.0f, Colors.GRAY.c);
        this.anim.render();
        if (!this.particles.isEmpty()) {
            GlStateManager.pushMatrix();
            for (final MenuParticle particle : this.particles) {
                particle.update(mouseX, mouseY, this.particles);
                if (particle.alpha < 0.1f) {
                    particle.remove = true;
                }
            }
            final Iterator<MenuParticle> iter = this.particles.iterator();
            while (iter.hasNext()) {
                final MenuParticle part = iter.next();
                if (part.remove) {
                    iter.remove();
                }
            }
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            for (final MenuParticle particle2 : this.particles) {
                GlStateManager.color(0.5f, 0.6f, 1.0f, particle2.alpha);
                final double x = particle2.x;
                final double y = particle2.y;
                worldrenderer.begin(7, DefaultVertexFormats.POSITION);
                worldrenderer.pos(x, y + 1.0, 0.0).endVertex();
                worldrenderer.pos(x + 0.5, y + 1.0, 0.0).endVertex();
                worldrenderer.pos(x + 0.5, y, 0.0).endVertex();
                worldrenderer.pos(x, y, 0.0).endVertex();
                tessellator.draw();
            }
            GlStateManager.popMatrix();
        }
        super.drawScreen(mouseX, mouseY, partialTicks);
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.restTimer.reset();
        for (final MenuParticle part : this.particles) {
            float angle = (float)Math.toDegrees(Math.atan2(mouseY - part.y, mouseX - part.x));
            if (angle < 0.0f) {
                angle += 360.0f;
            }
            final double xDist = mouseX - part.x;
            final double yDist = mouseY - part.y;
            double dist = Math.sqrt(xDist * xDist + yDist * yDist);
            final double mX = Math.cos(Math.toRadians(angle));
            final double mY = Math.sin(Math.toRadians(angle));
            if (dist < 20.0) {
                dist = 20.0;
            }
            final MenuParticle menuParticle = part;
            menuParticle.motionX -= mX * 200.0 / (dist / 2.0);
            final MenuParticle menuParticle2 = part;
            menuParticle2.motionY -= mY * 200.0 / (dist / 2.0);
        }
        super.mouseClicked(mouseX, mouseY, mouseButton);
    }
    
    private void open(final String url) throws Exception {
        final String osName = System.getProperty("os.name", "");
        if (osName.startsWith("Windows")) {
            Runtime.getRuntime().exec("rundll32 url.dll, FileProtocolHandler " + url);
        }
        else if (osName.startsWith("Mac OS")) {
            final Class fileMgr = Class.forName("com.apple.eio.FileManager");
            final Method openURL = fileMgr.getDeclaredMethod("openURL", String.class);
            openURL.invoke(null, url);
        }
        else {
            final String[] browsers = { "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape" };
            String browser = null;
            for (int count = 0; count < browsers.length && browser == null; ++count) {
                if (Runtime.getRuntime().exec(new String[] { "which", browsers[count] }).waitFor() == 0) {
                    browser = browsers[count];
                }
            }
            if (browser == null) {
                throw new RuntimeException("No Browser Was Found.");
            }
            Runtime.getRuntime().exec(new String[] { browser, url });
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
    }
    
    private void switchToRealms() {
        final RealmsBridge realmsbridge = new RealmsBridge();
        realmsbridge.switchToRealms((GuiScreen)this);
    }
}
