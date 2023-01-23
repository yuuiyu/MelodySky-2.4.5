//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

public class C extends Platform
{
    public static final int PTR_SIZEOF;
    
    public static final native void free(final long p0);
    
    public static final native long getenv(final byte[] p0);
    
    public static final native int setenv(final byte[] p0, final byte[] p1, final int p2);
    
    public static final native long malloc(final long p0);
    
    public static final native void memmove(final long p0, final byte[] p1, final long p2);
    
    public static final native void memmove(final long p0, final char[] p1, final long p2);
    
    public static final native void memmove(final long p0, final double[] p1, final long p2);
    
    public static final native void memmove(final long p0, final float[] p1, final long p2);
    
    public static final native void memmove(final long p0, final int[] p1, final long p2);
    
    public static final native void memmove(final long p0, final long[] p1, final long p2);
    
    public static final native void memmove(final long p0, final short[] p1, final long p2);
    
    public static final native void memmove(final byte[] p0, final char[] p1, final long p2);
    
    public static final native void memmove(final byte[] p0, final long p1, final long p2);
    
    public static final native void memmove(final long p0, final long p1, final long p2);
    
    public static final native void memmove(final char[] p0, final long p1, final long p2);
    
    public static final native void memmove(final double[] p0, final long p1, final long p2);
    
    public static final native void memmove(final float[] p0, final long p1, final long p2);
    
    public static final native void memmove(final int[] p0, final byte[] p1, final long p2);
    
    public static final native void memmove(final short[] p0, final long p1, final long p2);
    
    public static final native void memmove(final int[] p0, final long p1, final long p2);
    
    public static final native void memmove(final long[] p0, final long p1, final long p2);
    
    public static final native long memset(final long p0, final int p1, final long p2);
    
    public static final native int PTR_sizeof();
    
    public static final native int strlen(final long p0);
    
    static {
        Library.loadLibrary("swt");
        PTR_SIZEOF = PTR_sizeof();
    }
}
