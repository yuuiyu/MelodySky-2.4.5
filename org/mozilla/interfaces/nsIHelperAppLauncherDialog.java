//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHelperAppLauncherDialog extends nsISupports
{
    public static final String NS_IHELPERAPPLAUNCHERDIALOG_IID = "{64355793-988d-40a5-ba8e-fcde78cac631}";
    public static final long REASON_CANTHANDLE = 0L;
    public static final long REASON_SERVERREQUEST = 1L;
    public static final long REASON_TYPESNIFFED = 2L;
    
    void show(final nsIHelperAppLauncher p0, final nsISupports p1, final long p2);
    
    nsILocalFile promptForSaveToFile(final nsIHelperAppLauncher p0, final nsISupports p1, final String p2, final String p3);
}
