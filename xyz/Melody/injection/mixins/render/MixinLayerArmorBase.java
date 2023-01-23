//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.layers.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.module.modules.QOL.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ LayerArmorBase.class })
public abstract class MixinLayerArmorBase<T extends ModelBase> implements LayerRenderer<EntityLivingBase>
{
    @Shadow
    public abstract void renderLayer(final EntityLivingBase p0, final float p1, final float p2, final float p3, final float p4, final float p5, final float p6, final float p7, final int p8);
    
    @Inject(method = "doRenderLayer", at = { @At("HEAD") }, cancellable = true)
    public void doRenderLayer(final EntityLivingBase entity, final float p_177141_2_, final float p_177141_3_, final float partialTicks, final float p_177141_5_, final float p_177141_6_, final float p_177141_7_, final float scale, final CallbackInfo ci) {
        final NoArmorRender nar = (NoArmorRender)Client.instance.getModuleManager().getModuleByClass(NoArmorRender.class);
        final MobTracker mt = (MobTracker)Client.instance.getModuleManager().getModuleByClass(MobTracker.class);
        if (mt.isEnabled() && mt.checked != null && !mt.checked.isEmpty() && mt.checked.containsKey(entity)) {
            ci.cancel();
        }
        if (nar.isEnabled() && entity instanceof EntityPlayer && (boolean)nar.armor.getValue()) {
            if (nar.selfOnly.getValue()) {
                if (entity == Minecraft.getMinecraft().thePlayer) {
                    ci.cancel();
                }
            }
            else {
                ci.cancel();
            }
        }
    }
    
    @Overwrite
    public ItemStack getCurrentArmor(final EntityLivingBase entitylivingbaseIn, final int armorSlot) {
        final NoArmorRender nar = (NoArmorRender)Client.instance.getModuleManager().getModuleByClass(NoArmorRender.class);
        final MobTracker mt = (MobTracker)Client.instance.getModuleManager().getModuleByClass(MobTracker.class);
        if (mt.isEnabled() && mt.checked != null && !mt.checked.isEmpty() && mt.checked.containsKey(entitylivingbaseIn)) {
            return null;
        }
        if (!nar.isEnabled() || !(boolean)nar.armor.getValue()) {
            return entitylivingbaseIn.getCurrentArmor(armorSlot - 1);
        }
        if (!(boolean)nar.selfOnly.getValue()) {
            return null;
        }
        if (entitylivingbaseIn == Minecraft.getMinecraft().thePlayer) {
            return null;
        }
        return entitylivingbaseIn.getCurrentArmor(armorSlot - 1);
    }
}
