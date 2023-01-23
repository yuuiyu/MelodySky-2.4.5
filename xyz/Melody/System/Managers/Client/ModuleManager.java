//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Managers.Client;

import xyz.Melody.System.Managers.*;
import xyz.Melody.module.modules.QOL.Nether.*;
import xyz.Melody.module.modules.QOL.Slayer.*;
import xyz.Melody.module.modules.QOL.Dungeons.Devices.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.module.modules.macros.*;
import xyz.Melody.module.modules.Fishing.*;
import xyz.Melody.module.modules.QOL.Dungeons.*;
import xyz.Melody.module.modules.QOL.Swappings.*;
import xyz.Melody.module.modules.QOL.MainWorld.*;
import xyz.Melody.module.modules.QOL.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.events.misc.*;
import xyz.Melody.Event.*;
import xyz.Melody.Utils.render.gl.*;
import net.minecraft.client.renderer.*;
import java.nio.*;
import org.lwjgl.opengl.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.client.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import net.minecraft.util.*;
import xyz.Melody.ClientLib.*;
import java.awt.*;
import net.minecraft.client.gui.*;
import net.minecraft.scoreboard.*;
import java.util.*;

public class ModuleManager implements Manager
{
    public static List<Module> modules;
    private ScoreObjective objective;
    private ScaledResolution scaledRes;
    public boolean enabledNeededMod;
    public boolean nicetry;
    public static boolean loaded;
    
    public ModuleManager() {
        this.enabledNeededMod = true;
        this.nicetry = true;
    }
    
    @Override
    public void init() {
        ModuleManager.modules.add((Module)new CHMobESP());
        ModuleManager.modules.add((Module)new JerryChineHelper());
        ModuleManager.modules.add((Module)new AutoSellInv());
        ModuleManager.modules.add((Module)new InvCleaner());
        ModuleManager.modules.add((Module)new ChestStealer());
        ModuleManager.modules.add((Module)new AutoArmor());
        ModuleManager.modules.add((Module)new AutoRuby());
        ModuleManager.modules.add((Module)new InternetSurfing());
        ModuleManager.modules.add((Module)new AutoBaits());
        ModuleManager.modules.add((Module)new MyceliumNuker());
        ModuleManager.modules.add((Module)new SandNuker());
        ModuleManager.modules.add((Module)new SulphurESP());
        ModuleManager.modules.add((Module)new GlowingMushroomNuker());
        ModuleManager.modules.add((Module)new SapphireMiningPit());
        ModuleManager.modules.add((Module)new AntiAFK());
        ModuleManager.modules.add((Module)new BlockOverlay());
        ModuleManager.modules.add((Module)new NoSlowDown());
        ModuleManager.modules.add((Module)new AimAssist());
        ModuleManager.modules.add((Module)new AimDragonCrystals());
        ModuleManager.modules.add((Module)new IceNuker());
        ModuleManager.modules.add((Module)new FrozenTreasureESP());
        ModuleManager.modules.add((Module)new HardStoneNuker());
        ModuleManager.modules.add((Module)new PlayerList());
        ModuleManager.modules.add((Module)new MotionBlur());
        ModuleManager.modules.add((Module)new GemStoneESP());
        ModuleManager.modules.add((Module)new SlugFishing());
        ModuleManager.modules.add((Module)new CropNuker());
        ModuleManager.modules.add((Module)new FreeCam());
        ModuleManager.modules.add((Module)new OldAnimations());
        ModuleManager.modules.add((Module)new AshfangHelper());
        ModuleManager.modules.add((Module)new BlazeDagger());
        ModuleManager.modules.add((Module)new HideImplosionParticle());
        ModuleManager.modules.add((Module)new CombindBooks());
        ModuleManager.modules.add((Module)new NoHitDelay());
        ModuleManager.modules.add((Module)new SoulWhip());
        ModuleManager.modules.add((Module)new Reach());
        ModuleManager.modules.add((Module)new DamageFormat());
        ModuleManager.modules.add((Module)new AutoArrowAlign());
        ModuleManager.modules.add((Module)new AutoShootTheTarget());
        ModuleManager.modules.add((Module)new AutoSimonSays());
        ModuleManager.modules.add((Module)new DungeonChestProfit());
        ModuleManager.modules.add((Module)new FetchLBinData());
        ModuleManager.modules.add((Module)new SoundsHider());
        ModuleManager.modules.add((Module)new TerminatorClicker());
        ModuleManager.modules.add((Module)new ForagingMacro());
        ModuleManager.modules.add((Module)new SayMimicKilled());
        ModuleManager.modules.add((Module)new StonkLessStonk());
        ModuleManager.modules.add((Module)new AntiLobbyCommand());
        ModuleManager.modules.add((Module)new AutoSalvage());
        ModuleManager.modules.add((Module)new AutoSell());
        ModuleManager.modules.add((Module)new AutoEnchantTable());
        ModuleManager.modules.add((Module)new AutoCloseChest());
        ModuleManager.modules.add((Module)new HideSummonings());
        ModuleManager.modules.add((Module)new WormFishingESP());
        ModuleManager.modules.add((Module)new RenewCHPass());
        ModuleManager.modules.add((Module)new Tracers());
        ModuleManager.modules.add((Module)new LividFinder());
        ModuleManager.modules.add((Module)new LeverAura());
        ModuleManager.modules.add((Module)new AutoGG());
        ModuleManager.modules.add((Module)new GemstoneNuker());
        ModuleManager.modules.add((Module)new ActionMacro());
        ModuleManager.modules.add((Module)new PowderChestMacro());
        ModuleManager.modules.add((Module)new NoArmorRender());
        ModuleManager.modules.add((Module)new NoNBTUpdate());
        ModuleManager.modules.add((Module)new ClientCommands());
        ModuleManager.modules.add((Module)new Alerts());
        ModuleManager.modules.add((Module)new AutoHead());
        ModuleManager.modules.add((Module)new AutoBlock());
        ModuleManager.modules.add((Module)new AntiBot());
        ModuleManager.modules.add((Module)new Aura());
        ModuleManager.modules.add((Module)new GoldNuker());
        ModuleManager.modules.add((Module)new MithrilNuker());
        ModuleManager.modules.add((Module)new AutoTerminals());
        ModuleManager.modules.add((Module)new AOTS());
        ModuleManager.modules.add((Module)new EndStoneSword());
        ModuleManager.modules.add((Module)new AutoFish());
        ModuleManager.modules.add((Module)new AutoZombieSword());
        ModuleManager.modules.add((Module)new WitherImpact());
        ModuleManager.modules.add((Module)new CrystalGetter());
        ModuleManager.modules.add((Module)new Nametags());
        ModuleManager.modules.add((Module)new ItemSwitcher());
        ModuleManager.modules.add((Module)new ClickGui());
        ModuleManager.modules.add((Module)new MelodyPlayer());
        ModuleManager.modules.add((Module)new AutoClicker());
        ModuleManager.modules.add((Module)new MobTracker());
        ModuleManager.modules.add((Module)new GhostBlock());
        ModuleManager.modules.add((Module)new InvisEntity());
        ModuleManager.modules.add((Module)new HUD());
        ModuleManager.modules.add((Module)new Cam());
        ModuleManager.modules.add((Module)new Sprint());
        ModuleManager.modules.add((Module)new AntiVelocity());
        ModuleManager.modules.add((Module)new FullBright());
        Client.instance.readConfig();
        for (final Module m : ModuleManager.modules) {
            m.makeCommand();
        }
        EventBus.getInstance().register(new Object[] { this });
        ModuleManager.loaded = true;
    }
    
    public static List<Module> getModules() {
        return ModuleManager.modules;
    }
    
    public Module getModuleByClass(final Class<? extends Module> cls) {
        for (final Module m : ModuleManager.modules) {
            if (m.getClass() != cls) {
                continue;
            }
            return m;
        }
        return null;
    }
    
    public static Module getModuleByName(final String name) {
        for (final Module m : ModuleManager.modules) {
            if (!m.getName().equalsIgnoreCase(name)) {
                continue;
            }
            return m;
        }
        return null;
    }
    
    public Module getAlias(final String name) {
        for (final Module f : ModuleManager.modules) {
            if (f.getName().equalsIgnoreCase(name)) {
                return f;
            }
            for (final String s : f.getAlias()) {
                if (s.equalsIgnoreCase(name)) {
                    return f;
                }
            }
        }
        return null;
    }
    
    public List<Module> getModulesInType(final ModuleType t) {
        final ArrayList<Module> output = new ArrayList<Module>();
        for (final Module m : ModuleManager.modules) {
            if (m.getType() != t) {
                continue;
            }
            if (m.getName().equals("ClickGui")) {
                continue;
            }
            output.add(m);
        }
        return output;
    }
    
    @EventHandler
    private void onKeyPress(final EventKey e) {
        for (final Module m : ModuleManager.modules) {
            if (m.getKey() != e.getKey()) {
                continue;
            }
            m.setEnabled(!m.isEnabled());
        }
    }
    
    @EventHandler
    private void onGLHack(final EventRender3D e) {
        GlStateManager.getFloat(2982, (FloatBuffer)GLUtils.MODELVIEW.clear());
        GlStateManager.getFloat(2983, (FloatBuffer)GLUtils.PROJECTION.clear());
        this.glGetInteger(2978, (IntBuffer)GLUtils.VIEWPORT.clear());
    }
    
    public void glGetInteger(final int parameterName, final IntBuffer parameters) {
        GL11.glGetInteger(parameterName, parameters);
    }
    
    @EventHandler
    private void on2DRender(final EventRender2D e) {
        if (this.enabledNeededMod) {
            this.enabledNeededMod = false;
            for (final Module m : ModuleManager.modules) {
                if (!m.enabledOnStartup) {
                    continue;
                }
                m.setEnabled(true);
            }
        }
    }
    
    @EventHandler
    public void onRenderScoreboard(final EventRenderScoreboard event) {
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)HUD.class);
        if (!hud.isEnabled() || !(boolean)hud.scoreBoard.getValue()) {
            this.objective = null;
            this.scaledRes = null;
            return;
        }
        this.objective = event.getObjective();
        this.scaledRes = event.getScaledRes();
    }
    
    @EventHandler
    private void scoreBoard(final EventRender2D e) {
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)HUD.class);
        if (!hud.isEnabled() || !(boolean)hud.scoreBoard.getValue()) {
            return;
        }
        if (Minecraft.getMinecraft().theWorld == null) {
            return;
        }
        if (this.objective == null || this.scaledRes == null) {
            return;
        }
        final Scoreboard scoreboard = this.objective.getScoreboard();
        Collection<Score> collection = (Collection<Score>)scoreboard.getSortedScores(this.objective);
        final List<Score> list = (List<Score>)Lists.newArrayList(Iterables.filter((Iterable)collection, (Predicate)new lIl(this)));
        if (list.size() > 15) {
            collection = (Collection<Score>)Lists.newArrayList(Iterables.skip((Iterable)list, collection.size() - 15));
        }
        else {
            collection = list;
        }
        int i = Minecraft.getMinecraft().fontRendererObj.getStringWidth(this.objective.getDisplayName());
        for (final Score score : collection) {
            final ScorePlayerTeam scoreplayerteam = scoreboard.getPlayersTeam(score.getPlayerName());
            final String s = ScorePlayerTeam.formatPlayerName((Team)scoreplayerteam, score.getPlayerName()) + ": " + EnumChatFormatting.RED + score.getScorePoints();
            i = Math.max(i, Minecraft.getMinecraft().fontRendererObj.getStringWidth(s));
        }
        final int i2 = collection.size() * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
        final int j1 = this.scaledRes.getScaledHeight() / 2 + i2 / 3;
        final int k1 = 3;
        final int l1 = this.scaledRes.getScaledWidth() - i - k1;
        int m = 0;
        for (final Score score2 : collection) {
            ++m;
            final ScorePlayerTeam scoreplayerteam2 = scoreboard.getPlayersTeam(score2.getPlayerName());
            final String s2 = ScorePlayerTeam.formatPlayerName((Team)scoreplayerteam2, score2.getPlayerName());
            final String s3 = UISettings.scoreboardBackground ? (EnumChatFormatting.RED + "" + score2.getScorePoints()) : "";
            final int k2 = j1 - m * Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT;
            final int l2 = this.scaledRes.getScaledWidth() - k1 + 2;
            if (!UISettings.scoreboardBackground) {
                Gui.drawRect(l1 - 2, k2, l2, k2 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, new Color(0, 0, 0, 0).getRGB());
            }
            else {
                GuiIngame.drawRect(l1 - 2, k2, l2, k2 + Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT, 1342177280);
            }
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s2, (float)l1, (float)k2, 553648127);
            Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s3, (float)(l2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(s3)), (float)k2, 553648127);
            if (m == collection.size()) {
                final String s4 = this.objective.getDisplayName();
                if (UISettings.scoreboardBackground) {
                    GuiIngame.drawRect(l1 - 2, k2 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT - 1, l2, k2 - 1, 1610612736);
                    GuiIngame.drawRect(l1 - 2, k2 - 1, l2, k2, 1342177280);
                }
                Minecraft.getMinecraft().fontRendererObj.drawStringWithShadow(s4, (float)(l1 + i / 2 - Minecraft.getMinecraft().fontRendererObj.getStringWidth(s4) / 2), (float)(k2 - Minecraft.getMinecraft().fontRendererObj.FONT_HEIGHT), 553648127);
            }
        }
    }
    
    static {
        ModuleManager.modules = new ArrayList<Module>();
    }
}
