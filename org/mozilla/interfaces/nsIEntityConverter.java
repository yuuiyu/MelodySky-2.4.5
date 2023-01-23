//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEntityConverter extends nsISupports
{
    public static final String NS_IENTITYCONVERTER_IID = "{d14c7111-55e0-11d3-91d9-00105aa3f7dc}";
    public static final long entityNone = 0L;
    public static final long html40Latin1 = 1L;
    public static final long html40Symbols = 2L;
    public static final long html40Special = 4L;
    public static final long transliterate = 8L;
    public static final long mathml20 = 16L;
    public static final long html32 = 1L;
    public static final long html40 = 7L;
    public static final long entityW3C = 23L;
    
    String convertUTF32ToEntity(final long p0, final long p1);
    
    String convertToEntity(final char p0, final long p1);
    
    String convertToEntities(final String p0, final long p1);
}
