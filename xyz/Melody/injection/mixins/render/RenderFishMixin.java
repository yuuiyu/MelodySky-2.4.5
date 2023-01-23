//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.System.Animations.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderFish.class })
public class RenderFishMixin
{
    @Redirect(method = "doRender", at = @At(value = "NEW", target = "net/minecraft/util/Vec3", ordinal = 1))
    private Vec3 oldMelodyFishingLine(final double x, final double y, final double z) {
        final OldAnimations anim = (OldAnimations)Client.instance.getModuleManager().getModuleByClass(OldAnimations.class);
        if (anim.isEnabled()) {
            return anim.oldRod.getValue() ? AnimationHandler.getInstance().getOffset() : new Vec3(x, y, z);
        }
        return new Vec3(x, y, z);
    }
}
