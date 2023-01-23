//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import xyz.Melody.Utils.render.*;
import net.minecraft.util.*;

public final class Logo
{
    public static void M(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("M.png"), x, y, scale, scale1);
    }
    
    public static void e(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("e.png"), x, y, scale, scale1);
    }
    
    public static void l(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("l.png"), x, y, scale, scale1);
    }
    
    public static void o(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("o.png"), x, y, scale, scale1);
    }
    
    public static void d(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("d.png"), x, y, scale, scale1);
    }
    
    public static void y(final float x, final float y, final float scale, final float scale1) {
        RenderUtil.drawImage(getImage("y.png"), x, y, scale, scale1);
    }
    
    private static ResourceLocation getImage(final String name) {
        return new ResourceLocation("Melody/Logo/" + name);
    }
}
