//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.item;

import net.minecraft.client.renderer.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.item.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.*;
import xyz.Melody.module.modules.others.*;
import xyz.Melody.module.*;
import xyz.Melody.System.Animations.*;
import org.spongepowered.asm.mixin.injection.*;
import xyz.Melody.module.modules.render.*;

@Mixin({ ItemRenderer.class })
public abstract class MixinItemRenderer
{
    @Shadow
    private float equippedProgress;
    @Shadow
    private float prevEquippedProgress;
    @Shadow
    private ItemStack itemToRender;
    
    @Inject(method = "renderItemInFirstPerson", at = { @At("HEAD") }, cancellable = true)
    public void renderItemInFirstPerson(final float partialTicks, final CallbackInfo ci) {
        final boolean anim = Client.instance.getModuleManager().getModuleByClass(OldAnimations.class).isEnabled();
        if (anim && this.itemToRender != null) {
            final ItemRenderer $this = (ItemRenderer)this;
            final float equipProgress = this.prevEquippedProgress + (this.equippedProgress - this.prevEquippedProgress) * partialTicks;
            if (AnimationHandler.getInstance().renderItemInFirstPerson($this, this.itemToRender, equipProgress, partialTicks)) {
                ci.cancel();
            }
        }
    }
    
    @Inject(method = "renderFireInFirstPerson", at = { @At("HEAD") }, cancellable = true)
    private void renderFireInFirstPerson(final CallbackInfo ci) {
        final Cam cam = (Cam)Client.instance.getModuleManager().getModuleByClass(Cam.class);
        if (cam.isEnabled() && (boolean)cam.noFire.getValue()) {
            ci.cancel();
        }
    }
}
