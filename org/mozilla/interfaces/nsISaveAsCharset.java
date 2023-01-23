//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISaveAsCharset extends nsISupports
{
    public static final String NS_ISAVEASCHARSET_IID = "{33b87f70-7a9c-11d3-915c-006008a6edf6}";
    public static final long mask_Fallback = 255L;
    public static final long mask_Entity = 768L;
    public static final long mask_CharsetFallback = 1024L;
    public static final long mask_IgnorableFallback = 2048L;
    public static final long attr_FallbackNone = 0L;
    public static final long attr_FallbackQuestionMark = 1L;
    public static final long attr_FallbackEscapeU = 2L;
    public static final long attr_FallbackDecimalNCR = 3L;
    public static final long attr_FallbackHexNCR = 4L;
    public static final long attr_EntityNone = 0L;
    public static final long attr_EntityBeforeCharsetConv = 256L;
    public static final long attr_EntityAfterCharsetConv = 512L;
    public static final long attr_CharsetFallback = 1024L;
    public static final long attr_IgnoreIgnorables = 2048L;
    public static final long attr_plainTextDefault = 0L;
    public static final long attr_htmlTextDefault = 259L;
    
    String getCharset();
    
    void init(final String p0, final long p1, final long p2);
    
    String convert(final String p0);
}
