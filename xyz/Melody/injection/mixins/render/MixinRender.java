//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.render;

import net.minecraft.entity.*;
import net.minecraft.client.renderer.entity.*;
import org.spongepowered.asm.mixin.*;

@Mixin({ Render.class })
public abstract class MixinRender<T extends Entity>
{
    @Shadow
    protected abstract void renderName(final T p0, final double p1, final double p2, final double p3);
    
    @Overwrite
    public void doRender(final T entity, final double x, final double y, final double z, final float entityYaw, final float partialTicks) {
        this.renderName(entity, x, y, z);
    }
}
