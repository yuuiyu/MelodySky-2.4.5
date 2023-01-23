//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPInstallManagerUI extends nsISupports
{
    public static final String NS_IXPINSTALLMANAGERUI_IID = "{087f52a4-8fd8-40ab-ae52-c3e161810141}";
    public static final short INSTALL_DOWNLOADING = 5;
    public static final short INSTALL_INSTALLING = 6;
    public static final short INSTALL_FINISHED = 7;
    public static final short DOWNLOAD_TYPE_INSTALL = 1;
    
    nsIXPIProgressDialog getXpiProgress();
    
    boolean getHasActiveXPIOperations();
}
