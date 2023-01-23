//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdateItem extends nsISupports
{
    public static final String NS_IUPDATEITEM_IID = "{7f952767-427f-402b-8114-f80c95d1980d}";
    public static final long TYPE_APP = 1L;
    public static final long TYPE_EXTENSION = 2L;
    public static final long TYPE_THEME = 4L;
    public static final long TYPE_LOCALE = 8L;
    public static final long TYPE_PLUGIN = 16L;
    public static final long TYPE_MULTI_XPI = 32L;
    public static final long TYPE_ADDON = 30L;
    public static final long TYPE_ANY = 31L;
    
    String getId();
    
    String getVersion();
    
    String getMinAppVersion();
    
    String getMaxAppVersion();
    
    String getInstallLocationKey();
    
    String getName();
    
    String getXpiURL();
    
    String getXpiHash();
    
    String getIconURL();
    
    String getUpdateRDF();
    
    int getType();
    
    void init(final String p0, final String p1, final String p2, final String p3, final String p4, final String p5, final String p6, final String p7, final String p8, final String p9, final int p10);
    
    String getObjectSource();
}
