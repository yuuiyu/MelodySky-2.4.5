//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHelperAppLauncher extends nsICancelable
{
    public static final String NS_IHELPERAPPLAUNCHER_IID = "{99a0882d-2ff9-4659-9952-9ac531ba5592}";
    
    nsIMIMEInfo getMIMEInfo();
    
    nsIURI getSource();
    
    String getSuggestedFileName();
    
    void saveToDisk(final nsIFile p0, final boolean p1);
    
    void launchWithApplication(final nsIFile p0, final boolean p1);
    
    void setWebProgressListener(final nsIWebProgressListener2 p0);
    
    void closeProgressWindow();
    
    nsIFile getTargetFile();
    
    double getTimeDownloadStarted();
}
