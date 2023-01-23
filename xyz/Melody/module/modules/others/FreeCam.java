//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.others;

import xyz.Melody.module.*;
import xyz.Melody.Event.value.*;
import xyz.Melody.Utils.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import net.minecraftforge.event.entity.living.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;
import xyz.Melody.Event.events.rendering.*;
import java.awt.*;
import xyz.Melody.Utils.render.*;
import xyz.Melody.Event.*;
import net.minecraftforge.event.world.*;
import net.minecraft.network.play.server.*;
import xyz.Melody.Event.events.world.*;
import net.minecraft.network.play.client.*;
import xyz.Melody.injection.mixins.packets.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;

public class FreeCam extends Module
{
    private EntityOtherPlayerMP playerEntity;
    public Numbers<Double> speed;
    public Option<Boolean> tracer;
    private double x;
    private double y;
    private double z;
    
    public FreeCam() {
        super("FreeCam", new String[] { "fc", "fcm", "freecam" }, ModuleType.Others);
        this.speed = (Numbers<Double>)new Numbers("Speed", (Number)3.0, (Number)0.1, (Number)5.0, (Number)0.1);
        this.tracer = (Option<Boolean>)new Option("Tracer", (Object)false);
        this.z = 0.0;
        this.setModInfo("Specter Mode in 1.8.");
        this.addValues(new Value[] { (Value)this.speed, (Value)this.tracer });
    }
    
    public void onEnable() {
        if (!this.mc.thePlayer.onGround) {
            Helper.sendMessage("[WARNING] FreeCam can only be used on Ground.");
            this.setEnabled(false);
            return;
        }
        if (this.mc.thePlayer.onGround) {
            (this.playerEntity = new EntityOtherPlayerMP((World)this.mc.theWorld, this.mc.thePlayer.getGameProfile())).copyLocationAndAnglesFrom((Entity)this.mc.thePlayer);
            this.playerEntity.onGround = this.mc.thePlayer.onGround;
            this.x = this.mc.thePlayer.posX;
            this.y = this.mc.thePlayer.posY;
            this.z = this.mc.thePlayer.posZ;
            this.mc.theWorld.addEntityToWorld(-114810, (Entity)this.playerEntity);
        }
    }
    
    public void onDisable() {
        if (this.mc.thePlayer == null || this.mc.theWorld == null || this.playerEntity == null) {
            return;
        }
        this.mc.thePlayer.noClip = false;
        this.mc.thePlayer.setPosition(this.playerEntity.posX, this.playerEntity.posY, this.playerEntity.posZ);
        this.mc.theWorld.removeEntityFromWorld(-114810);
        this.playerEntity = null;
        this.mc.thePlayer.setVelocity(0.0, 0.0, 0.0);
    }
    
    @SubscribeEvent
    public void onLivingUpdate(final LivingEvent.LivingUpdateEvent event) {
        this.mc.thePlayer.noClip = true;
        this.mc.thePlayer.fallDistance = 0.0f;
        this.mc.thePlayer.onGround = false;
        this.mc.thePlayer.capabilities.isFlying = false;
        this.mc.thePlayer.motionY = 0.0;
        if (!this.isMoving()) {
            this.mc.thePlayer.motionZ = 0.0;
            this.mc.thePlayer.motionX = 0.0;
        }
        final double speed = (double)this.speed.getValue() * 0.25;
        this.mc.thePlayer.jumpMovementFactor = (float)speed;
        if (this.mc.gameSettings.keyBindJump.isKeyDown()) {
            final EntityPlayerSP thePlayer = this.mc.thePlayer;
            thePlayer.motionY += speed * 3.0;
        }
        if (this.mc.gameSettings.keyBindSneak.isKeyDown()) {
            final EntityPlayerSP thePlayer2 = this.mc.thePlayer;
            thePlayer2.motionY -= speed * 3.0;
        }
    }
    
    @EventHandler
    public void onRenderWorld(final EventRender3D event) {
        if (!this.mc.thePlayer.onGround) {
            return;
        }
        if (this.playerEntity != null && (boolean)this.tracer.getValue()) {
            final EntityOtherPlayerMP playerMP = this.playerEntity;
            final double x = playerMP.lastTickPosX + (playerMP.posX - playerMP.lastTickPosX) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosX;
            final double y = playerMP.lastTickPosY + (playerMP.posY - playerMP.lastTickPosY) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosY;
            final double z = playerMP.lastTickPosZ + (playerMP.posZ - playerMP.lastTickPosZ) * event.getPartialTicks() - this.mc.getRenderManager().viewerPosZ;
            this.drawLine((Entity)this.playerEntity, new Color(255, 255, 255), x, y, z);
            RenderUtil.drawFilledESP((Entity)this.playerEntity, new Color(255, 255, 255), event);
        }
    }
    
    @SubscribeEvent
    public void onWorldChange(final WorldEvent.Load event) {
        this.setEnabled(false);
    }
    
    @EventHandler
    public void onPacketRCV(final EventPacketRecieve event) {
        if (event.getPacket() instanceof S08PacketPlayerPosLook) {
            final S08PacketPlayerPosLook s08 = (S08PacketPlayerPosLook)event.getPacket();
            this.x = s08.getX();
            this.y = s08.getY();
            this.z = s08.getZ();
            this.playerEntity.setPosition(this.x, this.y, this.z);
        }
    }
    
    @EventHandler
    public void onPacketSend(final EventPacketSend event) {
        if (event.getPacket() instanceof C03PacketPlayer) {
            ((C03Accessor)event.getPacket()).setOnGround(true);
            ((C03Accessor)event.getPacket()).setX(this.x);
            ((C03Accessor)event.getPacket()).setY(this.y);
            ((C03Accessor)event.getPacket()).setZ(this.z);
        }
    }
    
    public boolean isMoving() {
        return this.mc.thePlayer != null && (this.mc.thePlayer.moveForward != 0.0f || this.mc.thePlayer.moveStrafing != 0.0f);
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
}
