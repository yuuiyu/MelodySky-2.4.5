//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render;

import net.minecraft.client.*;
import org.lwjgl.opengl.*;

public class Scissor
{
    private static Minecraft mc;
    
    public static void start(final float x1, float y1, final float x2, float y2) {
        if (y1 > y2) {
            final float temp = y2;
            y2 = y1;
            y1 = temp;
        }
        GL11.glScissor((int)x1, (int)(Display.getHeight() - y2), (int)(x2 - x1), (int)(y2 - y1));
        GL11.glEnable(3089);
    }
    
    public static void end() {
        GL11.glDisable(3089);
    }
    
    static {
        Scissor.mc = Minecraft.getMinecraft();
    }
}
