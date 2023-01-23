//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPServiceRegistry
{
    public static final String NS_ISOAPSERVICEREGISTRY_IID = "{9790d6bc-1dd1-11b2-afe0-bcb310c078bf}";
    
    boolean addConfiguration(final nsIDOMElement p0);
    
    void addSource(final String p0, final boolean p1);
    
    void addService(final nsISOAPService p0);
    
    nsISOAPEncodingRegistry getEncodings();
    
    void setEncodings(final nsISOAPEncodingRegistry p0);
}
