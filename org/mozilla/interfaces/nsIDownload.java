//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDownload extends nsITransfer
{
    public static final String NS_IDOWNLOAD_IID = "{9e1fd9f2-9727-4926-85cd-f16c375bba6d}";
    
    nsILocalFile getTargetFile();
    
    int getPercentComplete();
    
    double getAmountTransferred();
    
    double getSize();
    
    nsIURI getSource();
    
    nsIURI getTarget();
    
    nsICancelable getCancelable();
    
    String getDisplayName();
    
    long getStartTime();
    
    nsIMIMEInfo getMIMEInfo();
}
