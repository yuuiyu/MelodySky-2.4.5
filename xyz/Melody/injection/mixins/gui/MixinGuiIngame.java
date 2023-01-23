//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.scoreboard.*;
import xyz.Melody.module.modules.render.*;
import xyz.Melody.*;
import xyz.Melody.module.*;
import xyz.Melody.Event.events.rendering.*;

@SideOnly(Side.CLIENT)
@Mixin({ GuiIngame.class })
public abstract class MixinGuiIngame
{
    @Shadow
    @Final
    protected Random rand;
    
    @Inject(method = "renderTooltip", at = { @At("RETURN") })
    private void renderTooltip(final ScaledResolution sr, final float partialTicks, final CallbackInfo ci) {
        final EventRender2D e = new EventRender2D(partialTicks);
        EventBus.getInstance().call((Event)e);
    }
    
    @Inject(method = "renderScoreboard", at = { @At("HEAD") }, cancellable = true)
    private void renderScoreboard(final ScoreObjective objective, final ScaledResolution scaledRes, final CallbackInfo ci) {
        final HUD hud = (HUD)Client.instance.getModuleManager().getModuleByClass(HUD.class);
        EventBus.getInstance().call((Event)new EventRenderScoreboard(objective, scaledRes));
        if (hud.scoreBoard.getValue()) {
            ci.cancel();
        }
    }
}
