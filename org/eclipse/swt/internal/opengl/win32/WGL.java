//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.opengl.win32;

import org.eclipse.swt.internal.*;

public class WGL extends Platform
{
    public static final int PFD_TYPE_RGBA = 0;
    public static final int PFD_MAIN_PLANE = 0;
    public static final int PFD_DOUBLEBUFFER = 1;
    public static final int PFD_STEREO = 2;
    public static final int PFD_DRAW_TO_WINDOW = 4;
    public static final int PFD_SUPPORT_OPENGL = 32;
    
    public static final native int ChoosePixelFormat(final long p0, final PIXELFORMATDESCRIPTOR p1);
    
    public static final native int DescribePixelFormat(final long p0, final int p1, final int p2, final PIXELFORMATDESCRIPTOR p3);
    
    public static final native boolean SetPixelFormat(final long p0, final int p1, final PIXELFORMATDESCRIPTOR p2);
    
    public static final native boolean SwapBuffers(final long p0);
    
    public static final native long wglCreateContext(final long p0);
    
    public static final native boolean wglDeleteContext(final long p0);
    
    public static final native long wglGetCurrentContext();
    
    public static final native boolean wglMakeCurrent(final long p0, final long p1);
    
    public static final native boolean wglShareLists(final long p0, final long p1);
    
    static {
        Library.loadLibrary("swt-wgl");
    }
}
