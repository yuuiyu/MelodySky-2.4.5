//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import java.awt.*;
import xyz.Melody.GUI.*;
import net.minecraft.util.*;
import xyz.Melody.*;
import org.spongepowered.asm.mixin.injection.*;
import net.minecraft.client.gui.*;
import xyz.Melody.GUI.Menu.*;

@Mixin({ GuiMainMenu.class })
public abstract class MixinGuiMainMenu extends GuiScreen
{
    @Inject(method = "initGui", at = { @At("RETURN") })
    private void initGui(final CallbackInfo callbackInfo) {
        this.buttonList.add(new ClientButton(114, this.width - 100, 10, 60, 24, "MelodySky", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(19198, this.width - 165, 10, 60, 24, "Hide Mods", (ResourceLocation)null, new Color(20, 20, 20, 80)));
        this.buttonList.add(new ClientButton(514, this.width - 10 - 24, 10, 25, 24, "", new ResourceLocation("Melody/icon/exit.png"), new Color(20, 20, 20, 60)));
        if (!Client.vanillaMenu) {
            this.mc.displayGuiScreen((GuiScreen)new MainMenu());
        }
    }
    
    @Inject(method = "actionPerformed", at = { @At("HEAD") })
    private void actionPerformed(final GuiButton button, final CallbackInfo callbackInfo) {
        switch (button.id) {
            case 114: {
                Client.vanillaMenu = false;
                this.mc.displayGuiScreen((GuiScreen)new MainMenu());
                break;
            }
            case 514: {
                this.mc.shutdown();
                break;
            }
            case 19198: {
                this.mc.displayGuiScreen((GuiScreen)new GuiHideMods((GuiScreen)this));
                break;
            }
        }
    }
}
