//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import org.newdawn.slick.opengl.renderer.*;

public class MaskUtil
{
    protected static SGL GL;
    
    public static void defineMask() {
        MaskUtil.GL.glDepthMask(true);
        MaskUtil.GL.glClearDepth(1.0f);
        MaskUtil.GL.glClear(256);
        MaskUtil.GL.glDepthFunc(519);
        MaskUtil.GL.glEnable(2929);
        MaskUtil.GL.glDepthMask(true);
        MaskUtil.GL.glColorMask(false, false, false, false);
    }
    
    public static void finishDefineMask() {
        MaskUtil.GL.glDepthMask(false);
        MaskUtil.GL.glColorMask(true, true, true, true);
    }
    
    public static void drawOnMask() {
        MaskUtil.GL.glDepthFunc(514);
    }
    
    public static void drawOffMask() {
        MaskUtil.GL.glDepthFunc(517);
    }
    
    public static void resetMask() {
        MaskUtil.GL.glDepthMask(true);
        MaskUtil.GL.glClearDepth(0.0f);
        MaskUtil.GL.glClear(256);
        MaskUtil.GL.glDepthMask(false);
        MaskUtil.GL.glDisable(2929);
    }
    
    static {
        MaskUtil.GL = Renderer.get();
    }
}
