//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStandardURL extends nsISupports
{
    public static final String NS_ISTANDARDURL_IID = "{8793370a-311f-11d4-9876-00c04fa0cf4a}";
    public static final long URLTYPE_STANDARD = 1L;
    public static final long URLTYPE_AUTHORITY = 2L;
    public static final long URLTYPE_NO_AUTHORITY = 3L;
    
    void init(final long p0, final int p1, final String p2, final String p3, final nsIURI p4);
    
    boolean getMutable();
    
    void setMutable(final boolean p0);
}
