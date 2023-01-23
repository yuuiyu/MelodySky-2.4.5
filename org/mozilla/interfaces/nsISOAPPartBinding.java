//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPPartBinding extends nsIWSDLSOAPBinding
{
    public static final String NS_ISOAPPARTBINDING_IID = "{0458dac9-65de-11d5-9b42-00104bdf5339}";
    public static final int LOCATION_BODY = 1;
    public static final int LOCATION_HEADER = 2;
    public static final int LOCATION_FAULT = 3;
    public static final int USE_LITERAL = 1;
    public static final int USE_ENCODED = 2;
    
    int getLocation();
    
    int getUse();
    
    String getEncodingStyle();
    
    String getNamespace();
}
