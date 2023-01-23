//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheDeviceInfo extends nsISupports
{
    public static final String NS_ICACHEDEVICEINFO_IID = "{31d1c294-1dd2-11b2-be3a-c79230dca297}";
    
    String getDescription();
    
    String getUsageReport();
    
    long getEntryCount();
    
    long getTotalSize();
    
    long getMaximumSize();
}
