//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICookie extends nsISupports
{
    public static final String NS_ICOOKIE_IID = "{e9fcb9a4-d376-458f-b720-e65e7df593bc}";
    public static final int STATUS_UNKNOWN = 0;
    public static final int STATUS_ACCEPTED = 1;
    public static final int STATUS_DOWNGRADED = 2;
    public static final int STATUS_FLAGGED = 3;
    public static final int STATUS_REJECTED = 4;
    public static final int POLICY_UNKNOWN = 0;
    public static final int POLICY_NONE = 1;
    public static final int POLICY_NO_CONSENT = 2;
    public static final int POLICY_IMPLICIT_CONSENT = 3;
    public static final int POLICY_EXPLICIT_CONSENT = 4;
    public static final int POLICY_NO_II = 5;
    
    String getName();
    
    String getValue();
    
    boolean getIsDomain();
    
    String getHost();
    
    String getPath();
    
    boolean getIsSecure();
    
    double getExpires();
    
    int getStatus();
    
    int getPolicy();
}
