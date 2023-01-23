//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.module.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import xyz.Melody.module.balance.*;
import net.minecraft.entity.item.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Utils.math.*;
import xyz.Melody.System.Managers.Client.*;
import java.util.*;
import xyz.Melody.Event.*;
import org.lwjgl.opengl.*;

public class Tracers extends Module
{
    public Tracers() {
        super("Tracers", new String[] { "lines", "tracer" }, ModuleType.Render);
        this.setModInfo("Crosshair ----line---- Players.");
    }
    
    public void onEnable() {
        super.onEnable();
    }
    
    public void onDisable() {
        super.onDisable();
    }
    
    @EventHandler
    private void on3DRender(final EventRender3D e) {
        if (Client.inDungeons) {
            return;
        }
        for (final Object o : this.mc.theWorld.loadedEntityList) {
            final Entity entity = (Entity)o;
            if (entity.isEntityAlive() && entity instanceof EntityPlayer && entity != this.mc.thePlayer && !((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot(entity)) {
                if (entity instanceof EntityArmorStand) {
                    continue;
                }
                final double posX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * e.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
                final double posY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * e.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
                final double posZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * e.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
                RenderUtil.startDrawing();
                final float color = Math.round(255.0 - this.mc.thePlayer.getDistanceSqToEntity(entity) * 255.0 / MathUtil.sq(this.mc.gameSettings.renderDistanceChunks * 2.5)) / 255.0f;
                double[] arrd3;
                if (FriendManager.isFriend(entity.getDisplayName().getUnformattedText())) {
                    final double[] arrd2 = arrd3 = new double[] { 0.0, 1.0, 0.0 };
                    arrd2[2] = 1.0;
                }
                else {
                    final double[] arrd4 = arrd3 = new double[] { color, 1.0f - color, 0.0 };
                    arrd4[2] = 0.0;
                }
                this.drawLine(entity, arrd3, posX, posY, posZ);
                RenderUtil.stopDrawing();
            }
        }
    }
    
    private void drawLine(final Entity entity, final double[] color, final double x, final double y, final double z) {
        final float distance = this.mc.thePlayer.getDistanceToEntity(entity);
        float xD = distance / 48.0f;
        if (xD >= 1.0f) {
            xD = 1.0f;
        }
        GL11.glEnable(2848);
        if (color.length >= 4) {
            if (color[3] <= 0.1) {
                return;
            }
            GL11.glColor4d(color[0], color[1], color[2], color[3]);
        }
        else {
            GL11.glColor3d(color[0], color[1], color[2]);
        }
        GL11.glLineWidth(1.0f);
        GL11.glBegin(1);
        GL11.glVertex3d(0.0, (double)this.mc.thePlayer.getEyeHeight(), 0.0);
        GL11.glVertex3d(x, y, z);
        GL11.glEnd();
        GL11.glDisable(2848);
    }
}
