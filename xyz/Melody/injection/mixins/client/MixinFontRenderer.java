//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.client;

import org.spongepowered.asm.mixin.*;
import net.minecraft.client.gui.*;
import xyz.Melody.Event.events.rendering.*;
import xyz.Melody.Event.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ FontRenderer.class })
public class MixinFontRenderer
{
    @ModifyVariable(method = "renderString", at = @At("HEAD"), ordinal = 0)
    private String renderString(final String string) {
        if (string == null) {
            return string;
        }
        final EventDrawText textEvent = new EventDrawText(string);
        EventBus.getInstance().call((Event)textEvent);
        return textEvent.getText();
    }
}
