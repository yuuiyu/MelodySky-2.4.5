//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintStatusFeedback extends nsISupports
{
    public static final String NS_IPRINTSTATUSFEEDBACK_IID = "{19855dff-3248-4902-b196-93ee4c477880}";
    
    void showStatusString(final String p0);
    
    void startMeteors();
    
    void stopMeteors();
    
    void showProgress(final int p0);
    
    void closeWindow();
}
