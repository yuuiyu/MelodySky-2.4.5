//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.Utils.*;
import xyz.Melody.module.*;
import java.awt.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Event.events.rendering.*;
import java.util.regex.*;
import net.minecraft.entity.player.*;
import java.util.*;
import xyz.Melody.Event.*;
import xyz.Melody.module.balance.*;
import xyz.Melody.*;
import net.minecraft.entity.*;
import xyz.Melody.System.Managers.Client.*;
import org.lwjgl.opengl.*;
import xyz.Melody.Utils.render.gl.*;
import xyz.Melody.Utils.animate.*;
import xyz.Melody.Utils.render.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.*;

public class Nametags extends Module
{
    private float renderHpWidth;
    private static String lol;
    private final TimerUtil animationStopwatch;
    Option<Boolean> mcplayer;
    
    public Nametags() {
        super("Nametags", new String[] { "tags" }, ModuleType.Render);
        this.animationStopwatch = new TimerUtil();
        this.mcplayer = (Option<Boolean>)new Option("ShowYou", (Object)true);
        this.setColor(new Color(29, 187, 102).getRGB());
        this.addValues(new Value[] { (Value)this.mcplayer });
        this.setModInfo("Name Tag.");
    }
    
    @EventHandler
    public void onRender(final EventRender3D event) {
        final Pattern COLOR_PATTERN = Pattern.compile("(?i)\ufffd\ufffd[0-9A-FK-OR]");
        for (final Object o : this.mc.theWorld.playerEntities) {
            final EntityPlayer entity = (EntityPlayer)o;
            if (this.mcplayer.getValue()) {
                if (!entity.isEntityAlive()) {
                    continue;
                }
            }
            else if (!entity.isEntityAlive() || entity == this.mc.thePlayer) {
                continue;
            }
            final double pX = entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
            final double pY = entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
            final double pZ = entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
            this.renderNameTag(entity, COLOR_PATTERN.matcher(entity.getDisplayName().getUnformattedText()).replaceAll(""), pX, pY, pZ);
        }
    }
    
    private void renderNameTag(final EntityPlayer entity, final String tag, final double pX, double pY, final double pZ) {
        if (!entity.isInvisible()) {
            if (entity.getName().toUpperCase().contains("CRYPT DREADLORD") || entity.getName().toUpperCase().contains("DECOY") || entity.getName().toUpperCase().contains("ZOMBIE COMMANDER") || entity.getName().toUpperCase().contains("SKELETOR PRIME") || entity.getName().toUpperCase().contains("CRYPT SOULEATER") || ((AntiBot)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)AntiBot.class)).isEntityBot((Entity)entity)) {
                return;
            }
            float size = this.mc.thePlayer.getDistanceToEntity((Entity)entity) / 10.0f;
            if (size < 1.1f) {
                size = 1.1f;
            }
            pY += (entity.isSneaking() ? 0.5 : 0.7);
            float scale = size * 1.8f;
            scale /= 100.0f;
            String friend = "";
            if (FriendManager.isFriend(entity.getName())) {
                friend = EnumChatFormatting.DARK_PURPLE + "[Friend]";
            }
            Nametags.lol = friend + EnumChatFormatting.WHITE + tag;
            GL11.glPushMatrix();
            GL11.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GL11.glTranslatef((float)pX, (float)pY + 1.4f, (float)pZ);
            GL11.glRotatef(-this.mc.getRenderManager().playerViewY, 0.0f, 2.0f, 0.0f);
            GL11.glRotatef(this.mc.getRenderManager().playerViewX, 2.0f, 0.0f, 0.0f);
            GL11.glScalef(-scale, -scale, scale);
            GLUtil.setGLCap(2929, false);
            GLUtil.setGLCap(3042, true);
            final float nw = -this.mc.fontRendererObj.getStringWidth(Nametags.lol) / 2 - 4.6f;
            final float width2 = nw - 2.0f * nw;
            final float health = entity.getHealth();
            float hpPercentage = health / entity.getMaxHealth();
            hpPercentage = (float)MathHelper.clamp_double((double)hpPercentage, 0.0, 1.0);
            final float hpWidth = nw - 2.0f * nw * hpPercentage;
            if (this.animationStopwatch.hasReached(5.0)) {
                this.renderHpWidth = (float)AnimationUtil.animate(hpWidth, this.renderHpWidth, 0.05299999937415123);
                this.animationStopwatch.reset();
            }
            final float yy = -17.0f;
            RenderUtil.drawFastRoundedRect(nw, yy, width2, -0.1f, 1.0f, new Color(25, 25, 25, 101).getRGB());
            RenderUtil.drawFastRoundedRect(nw, -2.0f, width2, -0.1f, 0.0f, new Color(152, 171, 195).getRGB());
            RenderUtil.drawFastRoundedRect(nw, -2.0f, this.renderHpWidth, -0.1f, 0.0f, new Color(123, 104, 238).getRGB());
            this.mc.fontRendererObj.drawString(Nametags.lol, (int)(nw + 4.0f), -13, -1);
            this.renderHead(entity, -19.0f, -55.0f);
            GLUtil.revertAllCaps();
            GL11.glPopMatrix();
            GlStateManager.resetColor();
        }
    }
    
    private void renderHead(final EntityPlayer player, final float x, final float y) {
        final Object texture = ((AbstractClientPlayer)player).getLocationSkin();
        this.mc.getTextureManager().bindTexture((ResourceLocation)texture);
        Gui.drawScaledCustomSizeModalRect((int)(x + 3.0f), (int)(y + 3.5), 8.0f, 8.0f, 8, 8, 32, 32, 64.0f, 64.0f);
    }
}
