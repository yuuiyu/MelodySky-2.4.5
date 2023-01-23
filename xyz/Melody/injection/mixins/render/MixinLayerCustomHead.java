//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.layers.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.module.modules.QOL.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ LayerCustomHead.class })
public abstract class MixinLayerCustomHead implements LayerRenderer<EntityLivingBase>
{
    @Inject(method = "doRenderLayer", at = { @At("HEAD") }, cancellable = true)
    public void doRenderLayer(final EntityLivingBase entity, final float arg1, final float arg2, final float arg3, final float arg4, final float arg5, final float arg6, final float arg7, final CallbackInfo ci) {
        final NoArmorRender nar = (NoArmorRender)Client.instance.getModuleManager().getModuleByClass(NoArmorRender.class);
        final MobTracker mt = (MobTracker)Client.instance.getModuleManager().getModuleByClass(MobTracker.class);
        if (mt.isEnabled() && mt.checked != null && !mt.checked.isEmpty() && mt.checked.containsKey(entity)) {
            ci.cancel();
        }
        if (nar.isEnabled() && entity instanceof EntityPlayer && (boolean)nar.chead.getValue()) {
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
}
