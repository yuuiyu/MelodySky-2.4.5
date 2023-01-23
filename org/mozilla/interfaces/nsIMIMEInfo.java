//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMIMEInfo extends nsISupports
{
    public static final String NS_IMIMEINFO_IID = "{1448b42f-cf0d-466e-9a15-64e876ebe857}";
    public static final int saveToDisk = 0;
    public static final int alwaysAsk = 1;
    public static final int useHelperApp = 2;
    public static final int handleInternally = 3;
    public static final int useSystemDefault = 4;
    
    nsIUTF8StringEnumerator getFileExtensions();
    
    void setFileExtensions(final String p0);
    
    boolean extensionExists(final String p0);
    
    void appendExtension(final String p0);
    
    String getPrimaryExtension();
    
    void setPrimaryExtension(final String p0);
    
    String getMIMEType();
    
    String getDescription();
    
    void setDescription(final String p0);
    
    long getMacType();
    
    void setMacType(final long p0);
    
    long getMacCreator();
    
    void setMacCreator(final long p0);
    
    boolean _equals(final nsIMIMEInfo p0);
    
    nsIFile getPreferredApplicationHandler();
    
    void setPreferredApplicationHandler(final nsIFile p0);
    
    String getApplicationDescription();
    
    void setApplicationDescription(final String p0);
    
    boolean getHasDefaultHandler();
    
    String getDefaultDescription();
    
    void launchWithFile(final nsIFile p0);
    
    int getPreferredAction();
    
    void setPreferredAction(final int p0);
    
    boolean getAlwaysAskBeforeHandling();
    
    void setAlwaysAskBeforeHandling(final boolean p0);
}
