//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWSDLMessage extends nsISupports
{
    public static final String NS_IWSDLMESSAGE_IID = "{0458dac3-65de-11d5-9b42-00104bdf5339}";
    
    String getName();
    
    nsIDOMElement getDocumentation();
    
    nsIWSDLBinding getBinding();
    
    long getPartCount();
    
    nsIWSDLPart getPart(final long p0);
    
    nsIWSDLPart getPartByName(final String p0);
}
