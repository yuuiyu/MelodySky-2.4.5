//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.rendering.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.monster.*;
import xyz.Melody.Event.*;
import xyz.Melody.Event.events.world.*;
import xyz.Melody.*;
import xyz.Melody.ClientLib.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Utils.math.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.Utils.render.gl.*;

public class CHMobESP extends Module
{
    private ArrayList<Entity> entities;
    private int ticks;
    
    public CHMobESP() {
        super("CHMobESP", new String[] { "chme" }, ModuleType.Render);
        this.entities = new ArrayList<Entity>();
        this.ticks = 0;
        this.setModInfo("RenderMob ESP & Tags in Crystall Hollows.");
    }
    
    @EventHandler
    private void on3D(final EventRender3D event) {
        if (this.entities.isEmpty()) {
            return;
        }
        for (int i = 0; i < this.entities.size(); ++i) {
            final Entity e = this.entities.get(i);
            if (e instanceof EntityGolem) {
                this.renderTag(e, "Automation", event.getPartialTicks());
            }
            if (e instanceof EntitySlime) {
                this.renderTag(e, "Sludge", event.getPartialTicks());
            }
            if (e instanceof EntityMagmaCube) {
                this.renderTag(e, "Yog", event.getPartialTicks());
            }
            if (e instanceof EntityPlayer) {
                final String name = e.getName().toLowerCase();
                if (name.contains("team treasurite")) {
                    this.renderTag(e, "Team Treasurite", event.getPartialTicks());
                }
                if (name.contains("goblin") || name.contains("weaklin")) {
                    this.renderTag(e, "Goblin", event.getPartialTicks());
                }
            }
            if (e instanceof EntityZombie) {
                this.renderTag(e, "Zombie", event.getPartialTicks());
            }
            if (e instanceof EntityEndermite) {
                this.renderTag(e, "Endermite", event.getPartialTicks());
            }
        }
    }
    
    @EventHandler
    private void onTick(final EventTick event) {
        if (!Client.inSkyblock || Client.instance.sbArea.getCurrentArea() != SkyblockArea.Areas.Crystal_Hollows) {
            this.entities.clear();
            return;
        }
        if (this.ticks < 20) {
            ++this.ticks;
            return;
        }
        this.ticks = 0;
        final ArrayList<Entity> es = new ArrayList<Entity>();
        for (final Entity e : this.mc.theWorld.loadedEntityList) {
            if (e != null && e.isEntityAlive() && e instanceof EntityLivingBase && e.getName() != null && !e.isInvisible()) {
                es.add(e);
            }
        }
        this.entities.clear();
        this.entities.addAll(es);
    }
    
    public void onDisable() {
        this.entities.clear();
    }
    
    @SubscribeEvent
    public void clear(final WorldEvent.Load event) {
        this.entities.clear();
    }
    
    private void renderTag(final Entity e, String name, final float f) {
        float size = MathUtil.distanceToEntity((Entity)this.mc.thePlayer, e) / 8.0f;
        if (size < 1.1f) {
            size = 1.1f;
        }
        float scale = size * 1.8f;
        scale /= 100.0f;
        final double pX = e.lastTickPosX + (e.posX - e.lastTickPosX) * f - this.mc.getRenderManager().viewerPosX;
        final double pY = e.lastTickPosY + (e.posY - e.lastTickPosY) * f - this.mc.getRenderManager().viewerPosY;
        final double pZ = e.lastTickPosZ + (e.posZ - e.lastTickPosZ) * f - this.mc.getRenderManager().viewerPosZ;
        GL11.glPushMatrix();
        GlStateManager.resetColor();
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        GL11.glTranslated(pX, pY, pZ);
        GL11.glRotatef(-this.mc.getRenderManager().playerViewY, 0.0f, 2.0f, 0.0f);
        GL11.glRotatef(this.mc.getRenderManager().playerViewX, 2.0f, 0.0f, 0.0f);
        GL11.glScalef(-scale, -scale, scale);
        GLUtil.setGLCap(2929, false);
        name = name + "[" + (int)MathUtil.distanceToEntity((Entity)this.mc.thePlayer, e) + "m]";
        final float nw = -this.mc.fontRendererObj.getStringWidth(name) / 2 - 4.6f;
        GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
        this.mc.fontRendererObj.drawString(name, (int)nw + 4, -20, -1);
        GLUtil.revertAllCaps();
        GL11.glPopMatrix();
        GlStateManager.resetColor();
    }
}
