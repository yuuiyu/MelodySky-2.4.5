//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.entity;

import net.minecraft.entity.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.potion.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ EntityLivingBase.class })
public abstract class MixinEntityLivingBase extends MixinEntity
{
    @Shadow
    protected boolean isJumping;
    @Shadow
    public float moveStrafing;
    @Shadow
    public float moveForward;
    
    @Inject(method = "isPotionActive(Lnet/minecraft/potion/Potion;)Z", at = { @At("HEAD") }, cancellable = true)
    private void isPotionActive(final Potion p_isPotionActive_1_, final CallbackInfoReturnable<Boolean> ciReturnbale) {
        final Cam cam = (Cam)Client.instance.getModuleManager().getModuleByClass(Cam.class);
        if ((p_isPotionActive_1_ == Potion.confusion || p_isPotionActive_1_ == Potion.blindness) && cam.isEnabled() && (boolean)cam.noBlindness.getValue()) {
            ciReturnbale.setReturnValue((Object)false);
        }
    }
}
