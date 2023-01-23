//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.injection.mixins.gui;

import net.minecraftforge.fml.relauncher.*;
import net.minecraft.client.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;
import net.minecraft.client.gui.*;
import java.io.*;

@SideOnly(Side.CLIENT)
@Mixin({ GuiScreen.class })
public class MixinGuiScreen
{
    @Shadow
    public Minecraft mc;
    @Shadow
    public int width;
    @Shadow
    public int height;
    @Shadow
    public List<GuiButton> buttonList;
    
    @Shadow
    protected void actionPerformed(final GuiButton button) throws IOException {
    }
    
    @Shadow
    protected void drawDefaultBackground() {
    }
    
    @Shadow
    protected void drawWorldBackground(final int tint) {
    }
    
    @Shadow
    public void drawScreen(final int mouseX, final int mouseY, final float partialTicks) {
    }
    
    @Shadow
    public void initGui() {
    }
}
