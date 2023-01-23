//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import net.minecraft.entity.player.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ LayerArrow.class })
public abstract class MixinLayerArrow<T extends ModelBase> implements LayerRenderer<EntityLivingBase>
{
    @Inject(method = "doRenderLayer", at = { @At("HEAD") }, cancellable = true)
    public void doRenderLayer(final EntityLivingBase entity, final float p_177141_2_, final float p_177141_3_, final float partialTicks, final float p_177141_5_, final float p_177141_6_, final float p_177141_7_, final float scale, final CallbackInfo ci) {
        final NoArmorRender nar = (NoArmorRender)Client.instance.getModuleManager().getModuleByClass(NoArmorRender.class);
        if (nar.isEnabled() && entity instanceof EntityPlayer && (boolean)nar.arrows.getValue()) {
            ci.cancel();
        }
    }
}
