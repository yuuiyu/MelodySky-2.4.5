//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAddonUpdateCheckListener extends nsISupports
{
    public static final String NS_IADDONUPDATECHECKLISTENER_IID = "{c946119f-9e7c-41aa-a794-803148045350}";
    public static final long STATUS_NONE = 0L;
    public static final long STATUS_UPDATE = 1L;
    public static final long STATUS_VERSIONINFO = 2L;
    public static final long STATUS_DATA_FOUND = 3L;
    public static final long STATUS_FAILURE = 4L;
    public static final long STATUS_NO_UPDATE = 8L;
    public static final long STATUS_DISABLED = 16L;
    public static final long STATUS_APP_MANAGED = 32L;
    public static final long STATUS_READ_ONLY = 64L;
    public static final long STATUS_PENDING_OP = 128L;
    public static final long STATUS_NOT_MANAGED = 256L;
    public static final long STATUS_DISALLOWED = 496L;
    
    void onUpdateStarted();
    
    void onUpdateEnded();
    
    void onAddonUpdateStarted(final nsIUpdateItem p0);
    
    void onAddonUpdateEnded(final nsIUpdateItem p0, final int p1);
}
