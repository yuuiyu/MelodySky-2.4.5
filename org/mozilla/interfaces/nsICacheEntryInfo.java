//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheEntryInfo extends nsISupports
{
    public static final String NS_ICACHEENTRYINFO_IID = "{fab51c92-95c3-4468-b317-7de4d7588254}";
    
    String getClientID();
    
    String getDeviceID();
    
    String getKey();
    
    int getFetchCount();
    
    long getLastFetched();
    
    long getLastModified();
    
    long getExpirationTime();
    
    long getDataSize();
    
    boolean isStreamBased();
}
