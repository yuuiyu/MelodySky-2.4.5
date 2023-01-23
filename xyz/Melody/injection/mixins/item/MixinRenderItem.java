//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.item;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.block.model.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.resources.model.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.module.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import xyz.Melody.System.Animations.*;

@Mixin({ RenderItem.class })
public class MixinRenderItem
{
    @Unique
    private EntityLivingBase lastEntityToRenderFor;
    
    public MixinRenderItem() {
        this.lastEntityToRenderFor = null;
    }
    
    @Inject(method = "renderItemModelForEntity", at = { @At("HEAD") })
    public void renderItemModelForEntity(final ItemStack stack, final EntityLivingBase entityToRenderFor, final ItemCameraTransforms.TransformType cameraTransformType, final CallbackInfo ci) {
        this.lastEntityToRenderFor = entityToRenderFor;
    }
    
    @Inject(method = "renderItemModelTransform", at = { @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/RenderItem;renderItem(Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/resources/model/IBakedModel;)V") })
    public void renderItemModelForEntity_renderItem(final ItemStack stack, final IBakedModel model, final ItemCameraTransforms.TransformType cameraTransformType, final CallbackInfo ci) {
        final boolean anim = Client.instance.getModuleManager().getModuleByClass(OldAnimations.class).isEnabled();
        if (anim && cameraTransformType == ItemCameraTransforms.TransformType.THIRD_PERSON && this.lastEntityToRenderFor instanceof EntityPlayer) {
            final EntityPlayer p = (EntityPlayer)this.lastEntityToRenderFor;
            final ItemStack heldStack = p.getHeldItem();
            if (heldStack != null && p.getItemInUseCount() > 0 && heldStack.getItemUseAction() == EnumAction.BLOCK) {
                AnimationHandler.getInstance().doSwordBlock3rdPersonTransform();
            }
        }
    }
}
