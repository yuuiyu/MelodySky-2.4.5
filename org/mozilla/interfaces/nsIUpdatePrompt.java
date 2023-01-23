//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdatePrompt extends nsISupports
{
    public static final String NS_IUPDATEPROMPT_IID = "{22b00276-ec23-4034-a764-395da539b4be}";
    
    void checkForUpdates();
    
    void showUpdateAvailable(final nsIUpdate p0);
    
    void showUpdateDownloaded(final nsIUpdate p0);
    
    void showUpdateInstalled(final nsIUpdate p0);
    
    void showUpdateError(final nsIUpdate p0);
    
    void showUpdateHistory(final nsIDOMWindow p0);
}
