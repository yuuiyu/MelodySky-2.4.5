//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPKCS11Slot extends nsISupports
{
    public static final String NS_IPKCS11SLOT_IID = "{c2d4f296-ee60-11d4-998b-00b0d02354a0}";
    public static final long SLOT_DISABLED = 0L;
    public static final long SLOT_NOT_PRESENT = 1L;
    public static final long SLOT_UNINITIALIZED = 2L;
    public static final long SLOT_NOT_LOGGED_IN = 3L;
    public static final long SLOT_LOGGED_IN = 4L;
    public static final long SLOT_READY = 5L;
    
    String getName();
    
    String getDesc();
    
    String getManID();
    
    String getHWVersion();
    
    String getFWVersion();
    
    long getStatus();
    
    nsIPK11Token getToken();
    
    String getTokenName();
}
