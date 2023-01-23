//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIURIFixup extends nsISupports
{
    public static final String NS_IURIFIXUP_IID = "{2efd4a40-a5e1-11d4-9589-0020183bf181}";
    public static final long FIXUP_FLAG_NONE = 0L;
    public static final long FIXUP_FLAG_ALLOW_KEYWORD_LOOKUP = 1L;
    public static final long FIXUP_FLAGS_MAKE_ALTERNATE_URI = 2L;
    
    nsIURI createExposableURI(final nsIURI p0);
    
    nsIURI createFixupURI(final String p0, final long p1);
}
