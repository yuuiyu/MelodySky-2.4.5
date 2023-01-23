//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.render;

import xyz.Melody.GUI.ClickNew.*;
import xyz.Melody.module.*;
import java.awt.*;
import net.minecraft.client.gui.*;

public class ClickGui extends Module
{
    private NewClickGui clickGui;
    
    public ClickGui() {
        super("ClickGui", new String[] { "click" }, ModuleType.Render);
        this.clickGui = new NewClickGui();
        this.setColor(new Color(244, 255, 149).getRGB());
    }
    
    public void onEnable() {
        this.mc.displayGuiScreen((GuiScreen)this.clickGui);
        this.setEnabled(false);
    }
}
