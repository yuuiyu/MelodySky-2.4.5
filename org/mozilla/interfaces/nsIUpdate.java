//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIUpdate extends nsISupports
{
    public static final String NS_IUPDATE_IID = "{b0fb539e-f4ab-4ea1-bd75-e6d2813e5fc1}";
    
    String getType();
    
    void setType(final String p0);
    
    String getName();
    
    void setName(final String p0);
    
    String getVersion();
    
    void setVersion(final String p0);
    
    String getExtensionVersion();
    
    void setExtensionVersion(final String p0);
    
    String getBuildID();
    
    void setBuildID(final String p0);
    
    String getDetailsURL();
    
    void setDetailsURL(final String p0);
    
    String getLicenseURL();
    
    void setLicenseURL(final String p0);
    
    String getServiceURL();
    
    void setServiceURL(final String p0);
    
    boolean getIsCompleteUpdate();
    
    void setIsCompleteUpdate(final boolean p0);
    
    boolean getIsSecurityUpdate();
    
    void setIsSecurityUpdate(final boolean p0);
    
    long getInstallDate();
    
    void setInstallDate(final long p0);
    
    String getStatusText();
    
    void setStatusText(final String p0);
    
    nsIUpdatePatch getSelectedPatch();
    
    String getState();
    
    void setState(final String p0);
    
    int getErrorCode();
    
    void setErrorCode(final int p0);
    
    long getPatchCount();
    
    nsIUpdatePatch getPatchAt(final long p0);
    
    nsIDOMElement serialize(final nsIDOMDocument p0);
}
