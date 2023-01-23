//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render.gl;

import org.lwjgl.opengl.*;
import java.util.*;

public class GLUtil
{
    private static final Map<Integer, Boolean> glCapMap;
    
    public static void setGLCap(final int cap, final boolean flag) {
        GLUtil.glCapMap.put(cap, GL11.glGetBoolean(cap));
        if (flag) {
            GL11.glEnable(cap);
        }
        else {
            GL11.glDisable(cap);
        }
    }
    
    private static void revertGLCap(final int cap) {
        final Boolean origCap = GLUtil.glCapMap.get(cap);
        if (origCap != null) {
            if (origCap) {
                GL11.glEnable(cap);
            }
            else {
                GL11.glDisable(cap);
            }
        }
    }
    
    public static void glEnable(final int cap) {
        setGLCap(cap, true);
    }
    
    public static void glDisable(final int cap) {
        setGLCap(cap, false);
    }
    
    public static void revertAllCaps() {
        for (final Integer cap : GLUtil.glCapMap.keySet()) {
            revertGLCap(cap);
        }
    }
    
    static {
        glCapMap = new HashMap<Integer, Boolean>();
    }
}
