//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class LRESULT
{
    public long value;
    public static final LRESULT ONE;
    public static final LRESULT ZERO;
    
    public LRESULT(final long value) {
        this.value = value;
    }
    
    static {
        ONE = new LRESULT(1L);
        ZERO = new LRESULT(0L);
    }
}
