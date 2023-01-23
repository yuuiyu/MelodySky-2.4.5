//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import net.minecraft.client.entity.*;
import net.minecraft.entity.item.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.System.Managers.Dungeons.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.entity.*;
import xyz.Melody.Utils.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.module.FMLModules.Utils.*;
import java.util.*;
import xyz.Melody.Utils.other.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class LividFinder extends Module
{
    private String realLividName;
    private String prefix;
    private EntityOtherPlayerMP realLivid;
    private EntityArmorStand lividStand;
    private final Set<String> knownLivids;
    private boolean isMasterMode;
    private Option<Boolean> cc;
    private Numbers<Double> r;
    private Numbers<Double> g;
    private Numbers<Double> b;
    private Numbers<Double> a;
    private static final Map<String, String> lividColorPrefix;
    private Option<Boolean> tracer;
    
    public LividFinder() {
        super("BoxLivid", new String[] { "la" }, ModuleType.Dungeons);
        this.knownLivids = new HashSet<String>();
        this.isMasterMode = false;
        this.cc = (Option<Boolean>)new Option("CustomColor", (Object)false);
        this.r = (Numbers<Double>)new Numbers("Red", (Number)0.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.g = (Numbers<Double>)new Numbers("Green", (Number)0.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.b = (Numbers<Double>)new Numbers("Blue", (Number)0.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.a = (Numbers<Double>)new Numbers("Alpha", (Number)0.0, (Number)0.0, (Number)255.0, (Number)1.0);
        this.tracer = (Option<Boolean>)new Option("Tracer", (Object)true);
        this.addValues(new Value[] { (Value)this.tracer, (Value)this.cc, (Value)this.r, (Value)this.g, (Value)this.b, (Value)this.a });
        this.setModInfo("Create Correct Livid ESP(Tracer).");
    }
    
    @EventHandler
    private void tickDungeon(final EventTick event) {
        final DungeonTypes type = Client.instance.dungeonUtils.floor;
        if (!Client.inDungeons || (type != DungeonTypes.F5 && type != DungeonTypes.M5)) {
            this.knownLivids.clear();
            this.isMasterMode = false;
            this.lividStand = null;
            this.realLivid = null;
            this.realLividName = null;
            this.prefix = null;
            return;
        }
        this.isMasterMode = Client.isMMD;
    }
    
    @EventHandler
    private void onDraw(final EventRender3D event) {
        final DungeonTypes type = Client.instance.dungeonUtils.floor;
        if (type == DungeonTypes.F5 || type == DungeonTypes.M5) {
            final EntityOtherPlayerMP playerMP = this.realLivid;
            if (playerMP != null) {
                final double posX = playerMP.lastTickPosX + (playerMP.posX - playerMP.lastTickPosX) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                final double posY = playerMP.lastTickPosY + (playerMP.posY - playerMP.lastTickPosY) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                final double posZ = playerMP.lastTickPosZ + (playerMP.posZ - playerMP.lastTickPosZ) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                if (this.cc.getValue()) {
                    RenderUtil.entityOutlineAXIS((Entity)playerMP, new Color(((Double)this.r.getValue()).intValue(), ((Double)this.g.getValue()).intValue(), ((Double)this.b.getValue()).intValue(), ((Double)this.a.getValue()).intValue()).getRGB(), event);
                }
                else {
                    RenderUtil.entityOutlineAXIS((Entity)playerMP, Colors.GREEN.c, event);
                }
                RenderUtil.startDrawing();
                this.drawLine((Entity)playerMP, new Color(Colors.WHITE.c), posX, posY, posZ);
                RenderUtil.stopDrawing();
            }
        }
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        this.knownLivids.clear();
        this.isMasterMode = false;
        this.lividStand = null;
        this.realLivid = null;
        this.realLividName = null;
        this.prefix = null;
        super.onDisable();
    }
    
    @SubscribeEvent
    public void onEntityUpdate(final LivingEvent.LivingUpdateEvent updateEvent) {
        final DungeonTypes type = Client.instance.dungeonUtils.floor;
        if (!Client.inDungeons || (type != DungeonTypes.F5 && type != DungeonTypes.M5)) {
            this.knownLivids.clear();
            this.isMasterMode = false;
            this.lividStand = null;
            this.realLivid = null;
            this.realLividName = null;
            this.prefix = null;
            return;
        }
        if (updateEvent.entityLiving.getName().endsWith("Livid") && updateEvent.entityLiving instanceof EntityOtherPlayerMP) {
            if (!this.knownLivids.contains(updateEvent.entityLiving.getName())) {
                this.knownLivids.add(updateEvent.entityLiving.getName());
                this.realLividName = updateEvent.entityLiving.getName();
                this.realLivid = (EntityOtherPlayerMP)updateEvent.entityLiving;
                this.prefix = LividFinder.lividColorPrefix.get(this.realLividName.split(" ")[0]);
            }
            else if (this.realLividName != null && updateEvent.entityLiving != null && this.realLividName.equalsIgnoreCase(updateEvent.entityLiving.getName())) {
                this.realLivid = (EntityOtherPlayerMP)updateEvent.entityLiving;
            }
        }
        else if ((updateEvent.entityLiving.getName().startsWith(this.prefix + "\ufd3e ") || updateEvent.entityLiving.getName().startsWith(this.prefix + "\ufd3e ")) && updateEvent.entityLiving instanceof EntityArmorStand) {
            this.lividStand = (EntityArmorStand)updateEvent.entityLiving;
        }
    }
    
    public List<HealthData> getHealths() {
        final List<HealthData> healths = new ArrayList<HealthData>();
        long health = 0L;
        if (this.lividStand != null) {
            try {
                final String name = TextUtils.stripColor(this.lividStand.getName());
                final String healthPart = name.split(" ")[2];
                health = TextUtils.reverseFormat(healthPart.substring(0, healthPart.length() - 1));
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        healths.add(new HealthData((this.realLividName == null) ? "unknown" : this.realLividName, (int)health, this.isMasterMode ? 600000000 : 7000000, true));
        return healths;
    }
    
    public String getBossName() {
        return (this.realLividName == null) ? "Livid" : this.realLividName;
    }
    
    private void drawLine(final Entity entity, final Color color, final double x, final double y, final double z) {
        final float distance = this.mc.thePlayer.getDistanceToEntity(entity);
        float xD = distance / 48.0f;
        if (xD >= 1.0f) {
            xD = 1.0f;
        }
        GlStateManager.resetColor();
        GL11.glEnable(2848);
        GL11.glColor4f((float)color.getRed(), (float)color.getGreen(), (float)color.getBlue(), (float)color.getAlpha());
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)this.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(2848);
        GlStateManager.resetColor();
    }
    
    static {
        lividColorPrefix = new ll();
    }
}
