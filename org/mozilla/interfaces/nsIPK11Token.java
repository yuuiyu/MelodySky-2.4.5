//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPK11Token extends nsISupports
{
    public static final String NS_IPK11TOKEN_IID = "{51191434-1dd2-11b2-a17c-e49c4e99a4e3}";
    public static final int ASK_EVERY_TIME = -1;
    public static final int ASK_FIRST_TIME = 0;
    public static final int ASK_EXPIRE_TIME = 1;
    
    String getTokenName();
    
    String getTokenLabel();
    
    String getTokenManID();
    
    String getTokenHWVersion();
    
    String getTokenFWVersion();
    
    String getTokenSerialNumber();
    
    boolean isLoggedIn();
    
    void login(final boolean p0);
    
    void logoutSimple();
    
    void logoutAndDropAuthenticatedResources();
    
    void reset();
    
    int getMinimumPasswordLength();
    
    boolean getNeedsUserInit();
    
    boolean checkPassword(final String p0);
    
    void initPassword(final String p0);
    
    void changePassword(final String p0, final String p1);
    
    int getAskPasswordTimes();
    
    int getAskPasswordTimeout();
    
    void setAskPasswordDefaults(final int p0, final int p1);
    
    boolean isHardwareToken();
    
    boolean needsLogin();
    
    boolean isFriendly();
}
