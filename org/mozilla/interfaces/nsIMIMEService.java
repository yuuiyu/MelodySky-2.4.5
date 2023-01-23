//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMIMEService extends nsISupports
{
    public static final String NS_IMIMESERVICE_IID = "{5b3675a1-02db-4f8f-a560-b34736635f47}";
    
    nsIMIMEInfo getFromTypeAndExtension(final String p0, final String p1);
    
    String getTypeFromExtension(final String p0);
    
    String getTypeFromURI(final nsIURI p0);
    
    String getTypeFromFile(final nsIFile p0);
    
    String getPrimaryExtension(final String p0, final String p1);
}
