//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheEntryDescriptor extends nsICacheEntryInfo
{
    public static final String NS_ICACHEENTRYDESCRIPTOR_IID = "{49c1a11d-f5d2-4f09-8262-551e64908ada}";
    
    void setExpirationTime(final long p0);
    
    void setDataSize(final long p0);
    
    nsIInputStream openInputStream(final long p0);
    
    nsIOutputStream openOutputStream(final long p0);
    
    nsISupports getCacheElement();
    
    void setCacheElement(final nsISupports p0);
    
    int getAccessGranted();
    
    int getStoragePolicy();
    
    void setStoragePolicy(final int p0);
    
    nsIFile getFile();
    
    nsISupports getSecurityInfo();
    
    void setSecurityInfo(final nsISupports p0);
    
    void doom();
    
    void doomAndFailPendingRequests(final long p0);
    
    void markValid();
    
    void close();
    
    String getMetaDataElement(final String p0);
    
    void setMetaDataElement(final String p0, final String p1);
    
    void visitMetaData(final nsICacheMetaDataVisitor p0);
}
