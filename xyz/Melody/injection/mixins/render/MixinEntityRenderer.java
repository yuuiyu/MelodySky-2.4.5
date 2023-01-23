//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import net.minecraftforge.fml.relauncher.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.shader.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import xyz.Melody.GUI.Notification.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import java.util.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.module.modules.render.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.*;
import net.minecraft.entity.passive.*;
import net.minecraft.client.renderer.*;
import net.minecraft.world.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.common.*;
import net.minecraft.block.state.*;
import net.minecraft.block.*;
import xyz.Melody.module.balance.*;
import com.google.common.base.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;

@SideOnly(Side.CLIENT)
@Mixin({ EntityRenderer.class })
public abstract class MixinEntityRenderer
{
    @Shadow
    private Entity pointedEntity;
    @Shadow
    private ShaderGroup theShaderGroup;
    @Shadow
    private boolean useShader;
    @Shadow
    private Minecraft mc;
    @Shadow
    private float thirdPersonDistanceTemp;
    @Shadow
    private float thirdPersonDistance;
    @Shadow
    private boolean cloudFog;
    
    @Inject(method = "updateCameraAndRender", at = { @At("HEAD") })
    private void preUpdateCameraAndRender(final float partialTicks, final long nanoTime, final CallbackInfo ci) {
        final EventPreRGO e = new EventPreRGO(partialTicks);
        EventBus.getInstance().call((Event)e);
    }
    
    @Inject(method = "updateCameraAndRender", at = { @At("RETURN") })
    private void postUpdateCameraAndRender(final float partialTicks, final long nanoTime, final CallbackInfo ci) {
        NotificationPublisher.publish(new ScaledResolution(this.mc));
        final EventPostRGO e = new EventPostRGO(partialTicks);
        EventBus.getInstance().call((Event)e);
    }
    
    @Inject(method = "updateCameraAndRender", at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/shader/Framebuffer;bindFramebuffer(Z)V", shift = At.Shift.BEFORE) })
    public void updateCameraAndRender(final float partialTicks, final long nanoTime, final CallbackInfo ci) {
        final List<ShaderGroup> shaders = new ArrayList<ShaderGroup>();
        if (this.theShaderGroup != null && this.useShader) {
            shaders.add(this.theShaderGroup);
        }
        final MotionBlur dick = (MotionBlur)Client.instance.getModuleManager().getModuleByClass(MotionBlur.class);
        final ShaderGroup motionBlur = dick.getShader();
        if (dick.isEnabled()) {
            if (motionBlur != null) {
                shaders.add(motionBlur);
            }
            for (final ShaderGroup shader : shaders) {
                GlStateManager.pushMatrix();
                GlStateManager.loadIdentity();
                shader.loadShaderGroup(partialTicks);
                GlStateManager.popMatrix();
            }
        }
    }
    
    @Inject(method = "updateShaderGroupSize", at = { @At("RETURN") })
    public void updateShaderGroupSize(final int width, final int height, final CallbackInfo ci) {
        if (this.mc.theWorld != null) {
            final MotionBlur dick = (MotionBlur)Client.instance.getModuleManager().getModuleByClass(MotionBlur.class);
            if (OpenGlHelper.shadersSupported) {
                final ShaderGroup motionBlur = dick.getShader();
                if (motionBlur != null) {
                    motionBlur.createBindFramebuffers(width, height);
                }
            }
        }
    }
    
    @Inject(method = "renderWorldPass", at = { @At(value = "FIELD", target = "Lnet/minecraft/client/renderer/EntityRenderer;renderHand:Z", shift = At.Shift.BEFORE) })
    private void renderWorldPass(final int pass, final float partialTicks, final long finishTimeNano, final CallbackInfo callbackInfo) {
        final EventRender3D e = new EventRender3D(partialTicks);
        EventBus.getInstance().call((Event)e);
    }
    
    @Inject(method = "hurtCameraEffect", at = { @At("HEAD") }, cancellable = true)
    private void injectHurtCameraEffect(final CallbackInfo callbackInfo) {
        final Cam cam = (Cam)Client.instance.getModuleManager().getModuleByClass(Cam.class);
        if (cam.isEnabled() && (boolean)cam.bht.getValue()) {
            callbackInfo.cancel();
        }
    }
    
    @Inject(method = "orientCamera", at = { @At(value = "INVOKE", target = "Lnet/minecraft/util/Vec3;distanceTo(Lnet/minecraft/util/Vec3;)D") }, cancellable = true)
    private void cameraClip(final float partialTicks, final CallbackInfo callbackInfo) {
        final Cam cam = (Cam)Client.instance.getModuleManager().getModuleByClass(Cam.class);
        if (cam.isEnabled() && (boolean)cam.noClip.getValue()) {
            callbackInfo.cancel();
            final Entity entity = this.mc.getRenderViewEntity();
            float f = entity.getEyeHeight();
            if (entity instanceof EntityLivingBase && ((EntityLivingBase)entity).isPlayerSleeping()) {
                ++f;
                GlStateManager.translate(0.0f, 0.3f, 0.0f);
                if (!this.mc.gameSettings.debugCamEnable) {
                    final BlockPos blockpos = new BlockPos(entity);
                    final IBlockState iblockstate = this.mc.theWorld.getBlockState(blockpos);
                    ForgeHooksClient.orientBedCamera((IBlockAccess)this.mc.theWorld, blockpos, iblockstate, entity);
                    GlStateManager.rotate(entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0f, 0.0f, -1.0f, 0.0f);
                    GlStateManager.rotate(entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks, -1.0f, 0.0f, 0.0f);
                }
            }
            else if (this.mc.gameSettings.thirdPersonView > 0) {
                final double d3 = this.thirdPersonDistanceTemp + (this.thirdPersonDistance - this.thirdPersonDistanceTemp) * partialTicks;
                if (this.mc.gameSettings.debugCamEnable) {
                    GlStateManager.translate(0.0f, 0.0f, (float)(-d3));
                }
                else {
                    final float f2 = entity.rotationYaw;
                    float f3 = entity.rotationPitch;
                    if (this.mc.gameSettings.thirdPersonView == 2) {
                        f3 += 180.0f;
                    }
                    if (this.mc.gameSettings.thirdPersonView == 2) {
                        GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
                    }
                    GlStateManager.rotate(entity.rotationPitch - f3, 1.0f, 0.0f, 0.0f);
                    GlStateManager.rotate(entity.rotationYaw - f2, 0.0f, 1.0f, 0.0f);
                    GlStateManager.translate(0.0f, 0.0f, (float)(-d3));
                    GlStateManager.rotate(f2 - entity.rotationYaw, 0.0f, 1.0f, 0.0f);
                    GlStateManager.rotate(f3 - entity.rotationPitch, 1.0f, 0.0f, 0.0f);
                }
            }
            else {
                GlStateManager.translate(0.0f, 0.0f, -0.1f);
            }
            if (!this.mc.gameSettings.debugCamEnable) {
                float yaw = entity.prevRotationYaw + (entity.rotationYaw - entity.prevRotationYaw) * partialTicks + 180.0f;
                final float pitch = entity.prevRotationPitch + (entity.rotationPitch - entity.prevRotationPitch) * partialTicks;
                final float roll = 0.0f;
                if (entity instanceof EntityAnimal) {
                    final EntityAnimal entityanimal = (EntityAnimal)entity;
                    yaw = entityanimal.prevRotationYawHead + (entityanimal.rotationYawHead - entityanimal.prevRotationYawHead) * partialTicks + 180.0f;
                }
                final Block block = ActiveRenderInfo.getBlockAtEntityViewpoint((World)this.mc.theWorld, entity, partialTicks);
                final EntityViewRenderEvent.CameraSetup event = new EntityViewRenderEvent.CameraSetup((EntityRenderer)this, entity, block, (double)partialTicks, yaw, pitch, roll);
                MinecraftForge.EVENT_BUS.post((net.minecraftforge.fml.common.eventhandler.Event)event);
                GlStateManager.rotate(event.roll, 0.0f, 0.0f, 1.0f);
                GlStateManager.rotate(event.pitch, 1.0f, 0.0f, 0.0f);
                GlStateManager.rotate(event.yaw, 0.0f, 1.0f, 0.0f);
            }
            GlStateManager.translate(0.0f, -f, 0.0f);
            final double d4 = entity.prevPosX + (entity.posX - entity.prevPosX) * partialTicks;
            final double d5 = entity.prevPosY + (entity.posY - entity.prevPosY) * partialTicks + f;
            final double d6 = entity.prevPosZ + (entity.posZ - entity.prevPosZ) * partialTicks;
            this.cloudFog = this.mc.renderGlobal.hasCloudFog(d4, d5, d6, partialTicks);
        }
    }
    
    @Inject(method = "getMouseOver", at = { @At("HEAD") }, cancellable = true)
    public void getMouseOver(final float mouseOver, final CallbackInfo ci) {
        final Entity entity = this.mc.getRenderViewEntity();
        if (entity != null && this.mc.theWorld != null) {
            this.mc.mcProfiler.startSection("pick");
            this.mc.pointedEntity = null;
            final Reach reach = (Reach)Client.instance.getModuleManager().getModuleByClass(Reach.class);
            double d0 = (double)(reach.isEnabled() ? reach.size.getValue() : ((double)this.mc.playerController.getBlockReachDistance()));
            this.mc.objectMouseOver = entity.rayTrace(reach.isEnabled() ? ((double)reach.size.getValue()) : d0, mouseOver);
            double d2 = d0;
            final Vec3 vec3 = entity.getPositionEyes(mouseOver);
            boolean flag = false;
            if (this.mc.playerController.extendedReach()) {
                d0 = 6.0;
                d2 = 6.0;
            }
            else if (d0 > 3.0) {
                flag = true;
            }
            if (this.mc.objectMouseOver != null) {
                d2 = this.mc.objectMouseOver.hitVec.distanceTo(vec3);
            }
            if (reach.isEnabled()) {
                d2 = (double)reach.size.getValue();
                final MovingObjectPosition movingObjectPosition = entity.rayTrace(d2, mouseOver);
                if (movingObjectPosition != null) {
                    d2 = movingObjectPosition.hitVec.distanceTo(vec3);
                }
            }
            final Vec3 vec4 = entity.getLook(mouseOver);
            final Vec3 vec5 = vec3.addVector(vec4.xCoord * d0, vec4.yCoord * d0, vec4.zCoord * d0);
            this.pointedEntity = null;
            Vec3 vec6 = null;
            final float f = 1.0f;
            final List<Entity> list = (List<Entity>)this.mc.theWorld.getEntitiesInAABBexcluding(entity, entity.getEntityBoundingBox().addCoord(vec4.xCoord * d0, vec4.yCoord * d0, vec4.zCoord * d0).expand((double)f, (double)f, (double)f), Predicates.and(EntitySelectors.NOT_SPECTATING, p_apply_1_ -> p_apply_1_.canBeCollidedWith()));
            double d3 = d2;
            for (int j = 0; j < list.size(); ++j) {
                final Entity entity2 = list.get(j);
                final float f2 = entity2.getCollisionBorderSize();
                final AxisAlignedBB axisalignedbb = entity2.getEntityBoundingBox().expand((double)f2, (double)f2, (double)f2);
                final MovingObjectPosition movingobjectposition = axisalignedbb.calculateIntercept(vec3, vec5);
                if (axisalignedbb.isVecInside(vec3)) {
                    if (d3 >= 0.0) {
                        this.pointedEntity = entity2;
                        vec6 = ((movingobjectposition == null) ? vec3 : movingobjectposition.hitVec);
                        d3 = 0.0;
                    }
                }
                else if (movingobjectposition != null) {
                    final double d4 = vec3.distanceTo(movingobjectposition.hitVec);
                    if (d4 < d3 || d3 == 0.0) {
                        if (entity2 == entity.ridingEntity && !entity.canRiderInteract()) {
                            if (d3 == 0.0) {
                                this.pointedEntity = entity2;
                                vec6 = movingobjectposition.hitVec;
                            }
                        }
                        else {
                            this.pointedEntity = entity2;
                            vec6 = movingobjectposition.hitVec;
                            d3 = d4;
                        }
                    }
                }
            }
            if (this.pointedEntity != null && flag && vec3.distanceTo(vec6) > (reach.isEnabled() ? reach.size.getValue() : 3.0)) {
                this.pointedEntity = null;
                this.mc.objectMouseOver = new MovingObjectPosition(MovingObjectPosition.MovingObjectType.MISS, vec6, (EnumFacing)null, new BlockPos(vec6));
            }
            if (this.pointedEntity != null && (d3 < d2 || this.mc.objectMouseOver == null)) {
                this.mc.objectMouseOver = new MovingObjectPosition(this.pointedEntity, vec6);
                if (this.pointedEntity instanceof EntityLivingBase || this.pointedEntity instanceof EntityItemFrame) {
                    this.mc.pointedEntity = this.pointedEntity;
                }
            }
            this.mc.mcProfiler.endSection();
        }
        ci.cancel();
    }
}
