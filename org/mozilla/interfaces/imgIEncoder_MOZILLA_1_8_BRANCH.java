//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface imgIEncoder_MOZILLA_1_8_BRANCH extends nsIInputStream
{
    public static final String IMGIENCODER_MOZILLA_1_8_BRANCH_IID = "{b1b0b493-3369-44e0-878d-f7c56d937680}";
    public static final long INPUT_FORMAT_RGB = 0L;
    public static final long INPUT_FORMAT_RGBA = 1L;
    public static final long INPUT_FORMAT_HOSTARGB = 2L;
    
    void initFromData(final short[] p0, final long p1, final long p2, final long p3, final long p4, final long p5, final String p6);
}
