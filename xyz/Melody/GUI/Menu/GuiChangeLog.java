//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Menu;

import net.minecraft.client.gui.*;
import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.GUI.Particles.*;
import xyz.Melody.Utils.shader.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import xyz.Melody.GUI.Font.*;
import java.io.*;
import xyz.Melody.GUI.ShaderBG.Shaders.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;

public class GuiChangeLog extends GuiScreen
{
    public boolean firstLaunch;
    private int alpha;
    private Opacity opacity;
    private boolean shouldMainMenu;
    private ParticleUtils particle;
    private GaussianBlur gb;
    private int mouseWheel;
    private int logStart;
    private String[] logs;
    
    public GuiChangeLog() {
        this.firstLaunch = false;
        this.alpha = 0;
        this.opacity = new Opacity(10);
        this.particle = new ParticleUtils();
        this.gb = new GaussianBlur();
        this.mouseWheel = 0;
        this.logStart = 0;
        this.alpha = 0;
        this.logs = new String[] { "[2.4.5] [*] Full ModHider For MelodySky(ClientSide & ServerSide).", "[2.4.5] [/] Recode AutoRuby to AutoGeomstone.", "[2.4.5] [+] Crystall Hollows Mobs ESP(CHMobESP).", "[2.4.5] [+] BlueEggDrill Skill in GemstoneNuker.", "[2.4.5] [/] Tunned Timer Value 35 -> 25 in SlugFishing.", "[2.4.5] [+] .ar command(.ar load [name]/.ar save [name])", "[2.4.5] [/] Recode AutoTerminals(Timing).", "[2.4.5] [/] Recode NoSlow.", "[2.4.5] [+] AutoPlay.", "[2.4.5] [+] Recode AutoBlock.", "[2.4.4] [+] Cookie Login(Works with Firefox).", "[2.4.4] [+] Auto Sell Inventory and Sack(When Full).", "[2.4.4] [+] AutoRuby Fall Check.", "[2.4.4] [+] AutoRuby Auto Kill Yog.", "[2.4.4] [/] Recode AutoRuby Basic Logic.", "[2.4.4] [/] Fixed ResouecePack Issues.", "[2.4.4] [+] ChestStealer.", "[2.4.4] [+] InvManager.", "[2.4.4] [+] AutoArmor.", "[2.4.4] [/] Recode KillAura(Aura).", "[2.4.3] [+] Auto Buy FishBaits(AutoBaits).", "[2.4.3] [/] Recode AccountManager.", "[2.4.3] [+] WebBrowser(Inside).", "[2.4.3] [+] AutoRuby.", "[2.4.3] [+] .ar Command.", "[2.4.3] [+] InternetSurfing.", "[2.4.2] [+] XBoxLive Token Login.", "[2.4.2] [+] Refresh Token Login.", "[2.4.2] [/] Fix the Compability with Feather(WIP).", "[2.4.1] [B2] Fixed Charset Compability with GBK & UTF-8.", "[2.4.1] [+] SandNuker.", "[2.4.1] [+] MyceliumNuker.", "[2.4.1] [+] SulphurESP.", "[2.4.1] [+] Notification System.", "[2.4.1] [+] Mushed Glowy Tonic Duration in CustomUI.", "[2.4.1] [/] Fixed Superpairs Solver.", "[2.4.1] [/] Fixed EnablePlayerList Option in AutoFish.", "[2.4.1] [/] Fixed EnablePlayerList Option in SlugFishing.", "[2.4.1] [/] Fixed Microsoft Login in Account Manager.", "[2.4.1] [/] Recode TargetHUD.", "[2.4.1] [/] Fixed Blur in Inventory.", "[2.4.1] [+] Compability With MacOSX.", "[2.4.1] [+] Recode SapphirePitESP.", "[2.4.0] [/] Recode MotionBlur.", "[2.4.0] [+] GlowingMushroomNuker.", "[2.4.0] [+] New GaoNeng System.", "[2.4.0] [+] Recode Client Menus.", "[2.4.0] [+] New GUI Blur.", "[2.4.0] [+] Accound Manager.", "[2.4.0] [+] Reverse Mode in AntiKB.", "[2.4.0] [+] Blur Speed in HUD.", "[2.4.0] [+] Max Blur Value in HUD.", "[2.4.0] [+] Range Value in GemstoneNuker.", "[2.4.0] [+] Range Value in MithrilNuker.", "[2.4.0] [+] Range Value in CropNuker.", "[2.4.0] [+] Range Value in GoldNuker.", "[2.4.0] [/] SapphirePitESP Optimizations.", "[2.4.0] [/] Fixed KillAura(Aura).", "[2.4.0] [/] Fixed TargetHUD.", "[2.4.0] [-] DungeonMap.", "[2.3.9] [+] Sapphire Mining Pit ESP (SapphirePitESP).", "[2.3.9] [+] Menu Animations.", "[2.3.9] [/] WormFishingESP Optimizations.", "[2.3.9] [/] Fixed GemStoneESP.", "[2.3.8] [+] Add Auto Disable in AntiAFK.", "[2.3.8] [/] Fixed TargetHUD.", "[2.3.8] [/] Set Escape Time in AutoFish 5s -> 3s.", "[2.3.8] [/] Fixed Launching Issues.", "[2.3.8] [/] Compability Optimizations.", "[2.3.7] [+] TargetHUD.", "[2.3.7] [/] Fixed Authentication.", "[2.3.7] [/] Optimizations.", "[2.3.6] [+] Force Hide 'melodysky' Mod.", "[2.3.6] [+] BlockOverlay.", "[2.3.6] [/] Recode Aura Module.", "[2.3.6] [+] NoSlow.", "[2.3.6] [+] AntiAFK.", "[2.3.6] [-] HitBox.", "[2.3.5] [+] Notification With Escape Feature in AutoFish.", "[2.3.5] [/] ForagingMacro Optimizations.", "[2.3.5] [/] Compability With Linux(x64) System.", "[2.3.4] [+] Frozen Treasure Only in IceNuker.", "[2.3.4] [/] Forgot.", "[2.3.3] [/] Fixed AutoPowderChest.", "[2.3.3] [+] AimCrystals Feature.", "[2.3.3] [+] AimAssist Feature.", "[2.3.2] [B2] Optimizations.", "[2.3.2] [+] Ice Nuker.", "[2.3.2] [+] FrozenTreasure ESP.", "[2.3.2] [+] Under Option in GemstoneNuker.", "[2.3.1] [+] Show Player Name Option in Camera Module.", "[2.3.1] [+] HardStone Nuker.", "[2.3.1] [+] PlayerList Module.", "[2.3.1] [+] Auto Enable PlayerList in AutoFish Module.", "[2.3.1] [+] Auto Enable PlayerList in SlugFishing Module.", "[2.3.0] [+] /kc Command for IRC Chatting.", "[2.3.0] [/] Adapted New IRC Server.", "[2.3.0] [+] Rases Mode in Alerts.", "[2.3.0] [+] Motion Blur.", "[2.2.9] [/] Tried to Fixed Superpairs Solver.", "[2.2.9] [/] Adapted New IRC Server.", "[2.2.9] [/] I FORGOT THIS CHANGE.", "[2.2.8] [B2] Custom Name can nolonger change your name in IRC.", "[2.2.8] [+] GemstoneESP.", "[2.2.7] [/] Adapted New IRC Server.", "[2.2.6] [/] CPU Performance Optimiazations.", "[2.2.5] [B2] Fixed ScoreBoard Render.", "[2.2.5] [/] Client Menu Optimizations.", "[2.2.5] [+] Client Menu Animations.", "[2.2.5] [/] Memory Optimizations (FoamFix Inside).", "[2.2.5] [/] Fixed AutoExperiments.", "[2.2.5] [+] Gui Animations Option in Others -> HUD.", "[2.2.5] [+] Gui Blur Option in Others -> HUD.", "[2.2.5] [+] Scoreboard Option in Others -> HUD.", "[2.2.4] [B2] Fixed SlugFishing.", "[2.2.4] [+] Camera -> Custom Rank, Use '.rank [rank]' command.", "[2.2.4] [+] Endangered Animals Tracker in MobTracker.(FUCK dyorange)", "[2.2.4] [/] Fixed Star Mob Tracker.", "[2.2.4] [/] Fixed DamageFormat.", "[2.2.3] [B2] FPS += 60~100", "[2.2.3] [B2] Fixed Multi Thread Lags.", "[2.2.3] [^] Thread Optimization.", "[2.2.3] [^] Frame Per Second Optimizations.", "[2.2.3] [+] Ground Check in FreeCam(Inside).", "[2.2.3] [+] Shelmet/Cancel Mode in AntiKB.", "[2.2.3] [/] Container Gui Optimization.", "[2.2.3] [/] ClickGui Optimization.", "[2.2.2] Last Released Build in Near-Future.", "[2.2.2] [/] Fixed .af command.", "[2.2.2] Other Optimizations.", "[2.2.1] [B2] ScoreBoard Optimizations.", "[2.2.1] [B2] Compatibility Optimizations.", "[2.2.1] [/] Fixed ScoreBoard Background Hider.", "[2.2.1] [+] Custom Slugfishing Ember Armor Swap Delay.", "[2.2.1] [/] Auto Terminals Optimizations.", "[2.2.0] [/] Fixed AutoSimonSays.", "[2.2.0] [+] Slug Fishing.", "[2.2.0] [/] Recode MobTracker.", "[2.1.9] [B2] Fixed Compatibility Issues.", "[2.1.9] [B2] AutoTerminals Optimization.", "[2.1.9] [+] Hand XYZ In OldAnimations.", "[2.1.9] [+] Add Space Support in .name Command.", "[2.1.9] [/] Fixed .name reset Command.", "[2.1.9] [+] Auto Enable Modules After .authme Command.", "[2.1.8] [+] .authme Command.", "[2.1.8] [-] ClickMode in AutoTerminals.", "[2.1.8] [/] Fixed Auto Terminals.", "[2.1.7] [/] Optimized ALL Slot Clicking Features.", "[2.1.7] [/] Fixed Same Color(AutoTerminals).", "[2.1.7] [/] Auto Melody Optimization.", "[2.1.6] [/] Fixed Auto Salvage.", "[2.1.6] [/] Fixed .name reset Command.", "[2.1.6] [+] Add Command Mode in ClientCommand Module.", "[2.1.5] [+] Mods Hider.", "[2.1.5] [+] ClickMode in Auto Terminals.", "[2.1.5] [+] ClickMode in Auto Experiments.", "[2.1.5] [+] ClickMode in Auto Melody.", "[2.1.5] [/] Fixed Ghost Block.", "[2.1.5] [+] Ground Check in FreeCam(Inside).", "[2.1.4] [Build2] [+] Move Mode in Auto Fish.", "[2.1.4] [Build2] [+] Rotation Mode in Auto Fish.", "[2.1.4] [Build2] [+] Stonk less Stonk Optimization.", "[2.1.4] [/] Crop Nuker Optimization.", "[2.1.4] [+] Auto Mode in StonkLessStonk.", "[2.1.4] [/] Fixed Free Cam.", "[2.1.4] [/] Ghost Block Optimization.", "[2.1.3] [+] CropNuker.", "[2.1.3] [/] Ghost Block Optimization.", "[2.1.3] [/] Auto Fish Escape Optimization.", "[2.1.3] [/] MainMenu Optimization.", "[2.1.2] [B2] [/] Old Animations Optimization x2.", "[2.1.2] [B2] [/] Fixed Alerts Module.", "[2.1.2] [+] FreeCam.", "[2.1.2] [/] Old Animations Optimization.", "[2.1.2] [+] Hide Explosions in Sounds Hider.", "[2.1.1] [+] OldAnimations.", "[2.1.0] [Build2] [/] Fixed WormFishingESP.", "[2.1.0] [Build2] [/] Mob Tracker Optimization.", "[2.1.0] [/] Recode Auto Shoot The Target.", "[2.1.0] [/] WormFishingESP Optimizations.", "[2.1.0] [/] Fixed Worm Fishing ESP.", "[2.1.0] [/] Fixed Macro Protect in Mithril Nuker.", "[2.1.0] [-] GemstoneESP.", "[2.0.9] [/] Recode Click Gui.", "[2.0.9] [/] Module Categories Redistribution.", "[2.0.9] [+] Search Bar in Click Gui.", "[2.0.9] [+] Imprived Admin Check in AutoFish.", "[2.0.9] [+] Death Check in AutoFish.", "[2.0.9] [+] Slayer MiniBoss ESP.", "[2.0.9] [+] Ashfang Helper.", "[2.0.9] [+] Boss Entry Check in LeverAura.", "[2.0.9] [/] Fixed Dungeon Boss Check.", "[2.0.9] [/] Add Click Delay in Stonk Less Stonk.", "[2.0.8] [+] JerryChine Vertical Helper in Balance -> AntiKB.", "[2.0.8] [+] Auto Swap Blaze Slayer Dagger.", "[2.0.8] [/] AutoFish Optimization.", "[2.0.7] [+] Implosion Particles Hider.", "[2.0.7] [+] Day Counter in CustomUI.", "[2.0.6] [Build2] [+] Anti Admin Feature in AutoFish.", "[2.0.6] [Build2] [/] Camera -> No Hurt Cam Optimization.", "[2.0.6] [/] Fixed Worm Fishing ESP.", "[2.0.6] [/] Devides BetterHurtCam to NoHurtCam and ColorHurtCam.", "[2.0.6] [/] Auto Shoot The Target Optimization.", "[2.0.6] [/] Stonk less Stonk Optimization.", "[2.0.6] [+] Lock View in AutoFish.", "[2.0.6] [+] Lock Rod Slot in AutoFish.", "[2.0.6] [+] Fixed Anti Movement AFK Key Pressing in AutoFish.", "[2.0.5] [Build3] [/] Fixed Dungeon Chest Profit Doesn't Work in M7.", "[2.0.5] [Build2] [/] AutoTerminals -> Click on Time Optimization.", "[2.0.5] [/] Damage Format Optimizations.", "[2.0.5] [/] MobTracker Optimizations.", "[2.0.5] [+] Auto Combind Enchant Books.", "[2.0.5] [+] Balance -> No Hit Delay.", "[2.0.5] [/] Fixed Auto Arrow Align.", "[2.0.5] [/] Fixed Auto Simon Says.", "[2.0.5] [/] Fixed Auto Shoot The Targets.", "[2.0.4] [+] SoulWhip Feature.", "[2.0.4] [/] Fixed Auto Shoot The Target.", "[2.0.3] [Build2] Set the Minium Value of TickTimer to 20 in AutoFish.", "[2.0.3] [Build2] FPS Optimization.", "[2.0.3] [Build2] WormfishingESP Optimization.", "[2.0.3] [Build2] Fixed Reading Custom Name on Startup.", "[2.0.3] [Build2] Mob Tracker ESP Optimization.", "[2.0.3] [+] No Clip in Render -> Camera.", "[2.0.3] [/] Increased the Speed of Throwing Hook.", "[2.0.3] [/] Increased the Speed of Aiming Sea Creatures.", "[2.0.3] [+] Reach Module.", "[2.0.3] [/] Fixed +/- Button in ClickGui.", "[2.0.2] [Build2] [/] Damage Format Optimization.", "[2.0.2] [Build2] [+] .irc disconnect Command.", "[2.0.2] [Build2] [/] Recoded .irc reconnect Command.", "[2.0.2] [+] Damage Format.", "[2.0.2] [+] Auto Fish Escape Delay Check.", "[2.0.1] [Build2] [/] Fixed AntiKB.", "[2.0.1] [Build2] [/] FPS Optimization.", "[2.0.1] Fixed Escaping to Limbo in Autofish.", "[2.0.1] [+] .name Command. /to set your own name to a custom one/", "[2.0.1] [+] Auto Arrow Align.", "[2.0.1] [+] Auto Shoot The Target.", "[2.0.0] [IMPORTANT] [+] Verity System.", "[^] Other Tuning.", "Fixed Known Bugs." };
        this.opacity = new Opacity(10);
    }
    
    public void initGui() {
        this.alpha = 6;
        this.firstLaunch = true;
        super.initGui();
    }
    
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
        final CFontRenderer font = FontLoaders.NMSL18;
        this.drawDefaultBackground();
        this.gb.renderBlur(this.opacity.getOpacity());
        this.opacity.interp(140.0f, 5);
        RenderUtil.drawFastRoundedRect(0.0f, 59.0f, (float)this.width, 61.0f, 1.0f, new Color(160, 160, 160, 100).getRGB());
        FontLoaders.CNMD45.drawCenteredString("ChangeLogs", (float)(this.width / 2), 20.0f, FadeUtil.fade(FadeUtil.PURPLE.getColor()).getRGB());
        this.mc.fontRendererObj.drawString("?2019-2022 MelodyWorkGroup", 4, this.height - 10, new Color(60, 60, 60, 180).getRGB());
        this.mouseWheel = Mouse.getDWheel();
        if (this.mouseWheel < 0 && this.logStart < this.logs.length / 5 + 1) {
            ++this.logStart;
        }
        if (this.mouseWheel > 0 && this.logStart > 0) {
            --this.logStart;
        }
        final float appendX = (float)(this.logs.length * ((this.logStart == 0) ? 1 : this.logStart) / (this.logs.length / 25) - 25);
        RenderUtil.drawFastRoundedRect(0.0f + appendX, 59.0f, 20.0f + appendX, 61.0f, 0.0f, FadeUtil.BLUE.getColor().getRGB());
        int i = 0;
        int x = 0;
        if (!this.shouldMainMenu && this.alpha < 210) {
            this.alpha += 10;
        }
        if (this.shouldMainMenu && this.alpha > 10) {
            this.alpha -= 12;
        }
        this.alpha = ((this.alpha <= 10) ? 6 : this.alpha);
        for (String log : this.logs) {
            if (i % 25 == 0 && i != 0) {
                ++x;
            }
            if (log.contains("2.4.5")) {
                log = EnumChatFormatting.AQUA + log;
                if (log.contains("[B2]")) {
                    log = EnumChatFormatting.GREEN + StringUtils.stripControlCodes(log);
                }
            }
            if (log.contains("[HEY]")) {
                log = EnumChatFormatting.RED + log;
            }
            font.drawString(log, 6 + x * 340 - this.logStart * 42.5f, (float)(i * 16 + 71 - x * 400), new Color(255, 255, 255, this.alpha).getRGB());
            ++i;
        }
        if (this.shouldMainMenu) {
            this.mc.displayGuiScreen((GuiScreen)new MainMenu((int)this.opacity.getOpacity()));
        }
    }
    
    protected void mouseClicked(final int mouseX, final int mouseY, final int mouseButton) throws IOException {
        this.shouldMainMenu = true;
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
        ParticleUtils.drawParticles();
    }
    
    public void handleKeyboardInput() throws IOException {
        super.handleKeyboardInput();
    }
    
    public void onGuiClosed() {
        this.mc.entityRenderer.switchUseShader();
    }
}
