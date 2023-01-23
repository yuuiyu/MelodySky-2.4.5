//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Animations;

import net.minecraft.client.*;
import net.minecraft.client.entity.*;
import net.minecraft.potion.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import xyz.Melody.injection.mixins.item.*;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class AnimationHandler
{
    private static final AnimationHandler INSTANCE;
    private final Minecraft mc;
    public float prevSwingProgress;
    public float swingProgress;
    private int swingProgressInt;
    private boolean isSwingInProgress;
    
    public AnimationHandler() {
        this.mc = Minecraft.getMinecraft();
    }
    
    public static AnimationHandler getInstance() {
        return AnimationHandler.INSTANCE;
    }
    
    public float getSwingProgress(final float partialTickTime) {
        float currentProgress = this.swingProgress - this.prevSwingProgress;
        if (!this.isSwingInProgress) {
            return this.mc.thePlayer.getSwingProgress(partialTickTime);
        }
        if (currentProgress < 0.0f) {
            ++currentProgress;
        }
        return this.prevSwingProgress + currentProgress * partialTickTime;
    }
    
    private int getArmSwingAnimationEnd(final EntityPlayerSP player) {
        return player.isPotionActive(Potion.digSpeed) ? (5 - player.getActivePotionEffect(Potion.digSpeed).getAmplifier()) : (player.isPotionActive(Potion.digSlowdown) ? (8 + player.getActivePotionEffect(Potion.digSlowdown).getAmplifier() * 2) : 6);
    }
    
    private void updateSwingProgress() {
        final EntityPlayerSP player = this.mc.thePlayer;
        if (player == null) {
            return;
        }
        this.prevSwingProgress = this.swingProgress;
        final int max = this.getArmSwingAnimationEnd(player);
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        if ((boolean)anim.punching.getValue() && this.mc.gameSettings.keyBindAttack.isKeyDown() && this.mc.objectMouseOver != null && this.mc.objectMouseOver.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK && (!this.isSwingInProgress || this.swingProgressInt >= max >> 1 || this.swingProgressInt < 0)) {
            this.isSwingInProgress = true;
            this.swingProgressInt = -1;
        }
        if (this.isSwingInProgress) {
            ++this.swingProgressInt;
            if (this.swingProgressInt >= max) {
                this.swingProgressInt = 0;
                this.isSwingInProgress = false;
            }
        }
        else {
            this.swingProgressInt = 0;
        }
        this.swingProgress = this.swingProgressInt / (float)max;
    }
    
    @SubscribeEvent
    public void onClientTick(final TickEvent.ClientTickEvent event) {
        if (event.phase == TickEvent.Phase.END) {
            this.updateSwingProgress();
        }
    }
    
    public boolean renderItemInFirstPerson(final ItemRenderer renderer, final ItemStack stack, final float equipProgress, final float partialTicks) {
        if (stack == null) {
            return false;
        }
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        final Item item = stack.getItem();
        if (item == Items.filled_map) {
            return false;
        }
        final EnumAction action = stack.getItemUseAction();
        if ((item == Items.fishing_rod && !(boolean)anim.oldRod.getValue()) || (action == EnumAction.NONE && !(boolean)anim.oldModel.getValue()) || (action == EnumAction.BLOCK && !(boolean)anim.oldBlockhitting.getValue()) || (action == EnumAction.BOW && !(boolean)anim.oldBow.getValue())) {
            return false;
        }
        final EntityPlayerSP player = this.mc.thePlayer;
        final float var4 = player.prevRotationPitch + (player.rotationPitch - player.prevRotationPitch) * partialTicks;
        GlStateManager.pushMatrix();
        GlStateManager.rotate(var4, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(player.prevRotationYaw + (player.rotationYaw - player.prevRotationYaw) * partialTicks, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.popMatrix();
        final float pitch = player.prevRenderArmPitch + (player.renderArmPitch - player.prevRenderArmPitch) * partialTicks;
        final float yaw = player.prevRenderArmYaw + (player.renderArmYaw - player.prevRenderArmYaw) * partialTicks;
        GlStateManager.rotate((player.rotationPitch - pitch) * 0.1f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate((player.rotationYaw - yaw) * 0.1f, 0.0f, 1.0f, 0.0f);
        GlStateManager.enableRescaleNormal();
        if (item instanceof ItemCloth) {
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        }
        final int i = this.mc.theWorld.getCombinedLight(new BlockPos(player.posX, player.posY + player.getEyeHeight(), player.posZ), 0);
        final float brightnessX = (float)(i & 0xFFFF);
        final float brightnessY = (float)(i >> 16);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, brightnessX, brightnessY);
        final int rgb = item.getColorFromItemStack(stack, 0);
        final float red = (rgb >> 16 & 0xFF) / 255.0f;
        final float green = (rgb >> 8 & 0xFF) / 255.0f;
        final float blue = (rgb & 0xFF) / 255.0f;
        GlStateManager.color(red, green, blue, 1.0f);
        GlStateManager.pushMatrix();
        final int useCount = player.getItemInUseCount();
        final float swingProgress = this.getSwingProgress(partialTicks);
        boolean blockHitOverride = false;
        if ((boolean)anim.punching.getValue() && useCount <= 0 && this.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            final boolean block = action == EnumAction.BLOCK;
            boolean consume = false;
            if (item instanceof ItemFood) {
                final boolean alwaysEdible = ((ItemFoodAccessor)item).getAlwaysEdible();
                if (player.canEat(alwaysEdible)) {
                    consume = (action == EnumAction.EAT || action == EnumAction.DRINK);
                }
            }
            if (block || consume) {
                blockHitOverride = true;
            }
        }
        GlStateManager.translate((double)anim.handX.getValue(), (double)anim.handY.getValue(), (double)anim.handZ.getValue());
        if ((useCount > 0 || blockHitOverride) && action != EnumAction.NONE && this.mc.thePlayer.getItemInUseCount() > 0) {
            switch (l.$SwitchMap$net$minecraft$item$EnumAction[action.ordinal()]) {
                case 1:
                case 2: {
                    this.doConsumeAnimation(stack, useCount, partialTicks);
                    this.doEquipAndSwingTransform(equipProgress, ((boolean)anim.oldBlockhitting.getValue()) ? swingProgress : 0.0f);
                    break;
                }
                case 3: {
                    this.doEquipAndSwingTransform(equipProgress, ((boolean)anim.oldBlockhitting.getValue()) ? swingProgress : 0.0f);
                    this.doSwordBlockAnimation();
                    break;
                }
                case 4: {
                    this.doEquipAndSwingTransform(equipProgress, ((boolean)anim.oldBlockhitting.getValue()) ? swingProgress : 0.0f);
                    this.doBowAnimation(stack, useCount, partialTicks);
                    break;
                }
            }
        }
        else {
            this.doSwingTranslation(swingProgress);
            this.doEquipAndSwingTransform(equipProgress, swingProgress);
        }
        if (item.shouldRotateAroundWhenRendering()) {
            GlStateManager.rotate(180.0f, 0.0f, 1.0f, 0.0f);
        }
        if (this.doFirstPersonTransform(stack)) {
            renderer.renderItem((EntityLivingBase)player, stack, ItemCameraTransforms.TransformType.FIRST_PERSON);
        }
        else {
            renderer.renderItem((EntityLivingBase)player, stack, ItemCameraTransforms.TransformType.NONE);
        }
        GlStateManager.popMatrix();
        if (item instanceof ItemCloth) {
            GlStateManager.disableBlend();
        }
        GlStateManager.disableRescaleNormal();
        RenderHelper.disableStandardItemLighting();
        return true;
    }
    
    public void doSwordBlock3rdPersonTransform() {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        if (anim.oldBlockhitting.getValue()) {
            GlStateManager.translate(-0.15f, -0.2f, 0.0f);
            GlStateManager.rotate(70.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.translate(0.119f, 0.2f, -0.024f);
        }
    }
    
    private boolean doFirstPersonTransform(final ItemStack stack) {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        switch (l.$SwitchMap$net$minecraft$item$EnumAction[stack.getItemUseAction().ordinal()]) {
            case 4: {
                if (!(boolean)anim.oldBow.getValue()) {
                    return true;
                }
                break;
            }
            case 1:
            case 2: {
                if (!(boolean)anim.oldEating.getValue()) {
                    return true;
                }
                break;
            }
            case 3: {
                if (!(boolean)anim.oldBlockhitting.getValue()) {
                    return true;
                }
                break;
            }
            case 5: {
                if (!(boolean)anim.oldModel.getValue()) {
                    return true;
                }
                break;
            }
        }
        GlStateManager.translate(0.58800083f, 0.36999986f, -0.77000016f);
        GlStateManager.translate(0.0f, -0.3f, 0.0f);
        GlStateManager.scale(1.5f, 1.5f, 1.5f);
        GlStateManager.rotate(50.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(335.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.translate(-0.9375f, -0.0625f, 0.0f);
        GlStateManager.scale(-2.0f, 2.0f, -2.0f);
        if (this.mc.getRenderItem().shouldRenderItemIn3D(stack)) {
            GlStateManager.scale(0.58823526f, 0.58823526f, 0.58823526f);
            GlStateManager.rotate(-25.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.rotate(0.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, -0.25f, -0.125f);
            GlStateManager.scale(0.5f, 0.5f, 0.5f);
            return true;
        }
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        return false;
    }
    
    private void doConsumeAnimation(final ItemStack stack, final int useCount, final float partialTicks) {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        if (anim.oldEating.getValue()) {
            final float useAmount = useCount - partialTicks + 1.0f;
            final float useAmountNorm = 1.0f - useAmount / stack.getMaxItemUseDuration();
            float useAmountPow = 1.0f - useAmountNorm;
            useAmountPow *= useAmountPow * useAmountPow;
            useAmountPow *= useAmountPow * useAmountPow;
            useAmountPow *= useAmountPow * useAmountPow;
            final float useAmountFinal = 1.0f - useAmountPow;
            GlStateManager.translate(0.0f, MathHelper.abs(MathHelper.cos(useAmount / 4.0f * 3.1415927f) * 0.1f) * (float)((useAmountNorm > 0.2) ? 1 : 0), 0.0f);
            GlStateManager.translate(useAmountFinal * 0.6f, -useAmountFinal * 0.5f, 0.0f);
            GlStateManager.rotate(useAmountFinal * 90.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(useAmountFinal * 10.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(useAmountFinal * 30.0f, 0.0f, 0.0f, 1.0f);
        }
        else {
            final float f = useCount - partialTicks + 1.0f;
            final float f2 = f / stack.getMaxItemUseDuration();
            float f3 = MathHelper.abs(MathHelper.cos(f / 4.0f * 3.1415927f) * 0.1f);
            if (f2 >= 0.8f) {
                f3 = 0.0f;
            }
            GlStateManager.translate(0.0f, f3, 0.0f);
            final float f4 = 1.0f - (float)Math.pow(f2, 27.0);
            GlStateManager.translate(f4 * 0.6f, f4 * -0.5f, f4 * 0.0f);
            GlStateManager.rotate(f4 * 90.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(f4 * 10.0f, 1.0f, 0.0f, 0.0f);
            GlStateManager.rotate(f4 * 30.0f, 0.0f, 0.0f, 1.0f);
        }
    }
    
    private void doSwingTranslation(final float swingProgress) {
        final float swingProgress2 = MathHelper.sin(swingProgress * 3.1415927f);
        final float swingProgress3 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927f);
        GlStateManager.translate(-swingProgress3 * 0.4f, MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927f * 2.0f) * 0.2f, -swingProgress2 * 0.2f);
    }
    
    private void doEquipAndSwingTransform(final float equipProgress, final float swingProgress) {
        GlStateManager.translate(0.56f, -0.52f - (1.0f - equipProgress) * 0.6f, -0.72f);
        GlStateManager.rotate(45.0f, 0.0f, 1.0f, 0.0f);
        final float swingProgress2 = MathHelper.sin(swingProgress * swingProgress * 3.1415927f);
        final float swingProgress3 = MathHelper.sin(MathHelper.sqrt_float(swingProgress) * 3.1415927f);
        GlStateManager.rotate(-swingProgress2 * 20.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-swingProgress3 * 20.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(-swingProgress3 * 80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(0.4f, 0.4f, 0.4f);
    }
    
    private void doSwordBlockAnimation() {
        GlStateManager.translate(-0.5f, 0.2f, 0.0f);
        GlStateManager.rotate(30.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-80.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.rotate(60.0f, 0.0f, 1.0f, 0.0f);
    }
    
    private void doBowAnimation(final ItemStack stack, final int useCount, final float partialTicks) {
        GlStateManager.rotate(-18.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(-12.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-8.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(-0.9f, 0.2f, 0.0f);
        final float totalPullback = stack.getMaxItemUseDuration() - (useCount - partialTicks + 1.0f);
        float pullbackNorm = totalPullback / 20.0f;
        pullbackNorm = (pullbackNorm * pullbackNorm + pullbackNorm * 2.0f) / 3.0f;
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass((Class<? extends Module>)OldAnimations.class);
        if (pullbackNorm > 1.0f) {
            pullbackNorm = 1.0f;
        }
        if (pullbackNorm > 0.1f) {
            GlStateManager.translate(0.0f, MathHelper.sin((totalPullback - 0.1f) * 1.3f) * 0.01f * (pullbackNorm - 0.1f), 0.0f);
        }
        GlStateManager.translate(0.0f, 0.0f, pullbackNorm * 0.1f);
        if (anim.oldBow.getValue()) {
            GlStateManager.rotate(-335.0f, 0.0f, 0.0f, 1.0f);
            GlStateManager.rotate(-50.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.translate(0.0f, 0.5f, 0.0f);
        }
        final float zScale = 1.0f + pullbackNorm * 0.2f;
        GlStateManager.scale(1.0f, 1.0f, zScale);
        if (anim.oldBow.getValue()) {
            GlStateManager.translate(0.0f, -0.5f, 0.0f);
            GlStateManager.rotate(50.0f, 0.0f, 1.0f, 0.0f);
            GlStateManager.rotate(335.0f, 0.0f, 0.0f, 1.0f);
        }
    }
    
    public Vec3 getOffset() {
        final double fov = Minecraft.getMinecraft().gameSettings.fovSetting;
        final double decimalFov = fov / 110.0;
        return new Vec3(-decimalFov + decimalFov / 2.5 - decimalFov / 8.0 + 0.16, 0.0, 0.4);
    }
    
    static {
        INSTANCE = new AnimationHandler();
    }
}
