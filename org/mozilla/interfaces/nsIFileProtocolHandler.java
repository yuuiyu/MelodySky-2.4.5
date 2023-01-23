//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFileProtocolHandler extends nsIProtocolHandler
{
    public static final String NS_IFILEPROTOCOLHANDLER_IID = "{255602ea-c31f-4d29-8f35-905ead3f76f4}";
    
    nsIURI newFileURI(final nsIFile p0);
    
    String getURLSpecFromFile(final nsIFile p0);
    
    nsIFile getFileFromURLSpec(final String p0);
    
    nsIURI readURLFile(final nsIFile p0);
}
