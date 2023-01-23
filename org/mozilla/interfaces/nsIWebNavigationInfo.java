//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWebNavigationInfo extends nsISupports
{
    public static final String NS_IWEBNAVIGATIONINFO_IID = "{62a93afb-93a1-465c-84c8-0432264229de}";
    public static final long UNSUPPORTED = 0L;
    public static final long IMAGE = 1L;
    public static final long PLUGIN = 2L;
    public static final long OTHER = 32768L;
    
    long isTypeSupported(final String p0, final nsIWebNavigation p1);
}
