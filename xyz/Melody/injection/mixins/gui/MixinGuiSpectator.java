//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import net.minecraftforge.fml.relauncher.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;

@SideOnly(Side.CLIENT)
@Mixin({ GuiSpectator.class })
public class MixinGuiSpectator
{
    @Inject(method = "renderTooltip", at = { @At("RETURN") })
    private void renderTooltip(final ScaledResolution sr, final float partialTicks, final CallbackInfo ci) {
        EventBus.getInstance().call((Event)new EventRender2D(partialTicks));
    }
}
