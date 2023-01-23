//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import xyz.Melody.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import java.io.*;

@Mixin({ GuiChat.class })
public abstract class MixinGuiChat extends MixinGuiScreen
{
    @Inject(method = "initGui", at = { @At("HEAD") }, cancellable = true)
    private void initGui(final CallbackInfo ci) {
        this.buttonList.add((GuiButton)new ClientButton(1145, this.width / 2 - 50, this.height - 50, 100, 20, "Current: " + (Client.clientChat ? "Melody" : "Vanilla"), new Color(20, 20, 20, 120)));
    }
    
    @Override
    protected void actionPerformed(final GuiButton button) throws IOException {
        switch (button.id) {
            case 1145: {
                Client.clientChat = !Client.clientChat;
                this.mc.displayGuiScreen((GuiScreen)new GuiChat());
                break;
            }
        }
        super.actionPerformed(button);
    }
}
