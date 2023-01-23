//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMLSException extends nsISupports
{
    public static final String NS_IDOMLSEXCEPTION_IID = "{1cc8e4b3-1dbb-4adc-a913-1527bf67748c}";
    public static final int PARSE_ERR = 81;
    public static final int SERIALIZE_ERR = 82;
    
    int getCode();
}
