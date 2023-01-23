//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPIProgressDialog extends nsISupports
{
    public static final String NS_IXPIPROGRESSDIALOG_IID = "{ce8f744e-d5a5-41b3-911f-0fee3008b64e}";
    public static final short DOWNLOAD_START = 0;
    public static final short DOWNLOAD_DONE = 1;
    public static final short INSTALL_START = 2;
    public static final short INSTALL_DONE = 3;
    public static final short DIALOG_CLOSE = 4;
    
    void onStateChange(final long p0, final short p1, final int p2);
    
    void onProgress(final long p0, final double p1, final double p2);
}
