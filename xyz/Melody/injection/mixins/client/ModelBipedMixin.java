//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ ModelBiped.class })
public class ModelBipedMixin extends ModelBase
{
    @ModifyConstant(method = "setRotationAngles", constant = @Constant(floatValue = -0.5235988f))
    private float cancelRotation(final float original) {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass(OldAnimations.class);
        if (anim.isEnabled()) {
            return anim.oldBlockhitting.getValue() ? 0.0f : original;
        }
        return original;
    }
}
