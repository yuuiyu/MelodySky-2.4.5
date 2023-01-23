//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPPortBinding extends nsIWSDLSOAPBinding
{
    public static final String NS_ISOAPPORTBINDING_IID = "{0458dac7-65de-11d5-9b42-00104bdf5339}";
    public static final int SOAP_VERSION_1_1 = 0;
    public static final int SOAP_VERSION_1_2 = 1;
    public static final int SOAP_VERSION_UNKNOWN = 32767;
    
    String getName();
    
    String getAddress();
    
    int getStyle();
    
    String getTransport();
    
    int getSoapVersion();
}
