//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIErrorHook extends nsISupports
{
    public static final String JSDIERRORHOOK_IID = "{b7dd3c1c-1dd1-11b2-83eb-8a857d199e0f}";
    public static final long REPORT_ERROR = 0L;
    public static final long REPORT_WARNING = 1L;
    public static final long REPORT_EXCEPTION = 2L;
    public static final long REPORT_STRICT = 4L;
    
    boolean onError(final String p0, final String p1, final long p2, final long p3, final long p4, final long p5, final jsdIValue p6);
}
