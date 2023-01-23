//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPFault extends nsISupports
{
    public static final String NS_ISOAPFAULT_IID = "{99ec6694-535f-11d4-9a58-000064657374}";
    
    nsIDOMElement getElement();
    
    void setElement(final nsIDOMElement p0);
    
    String getFaultNamespaceURI();
    
    String getFaultCode();
    
    String getFaultString();
    
    String getFaultActor();
    
    nsIDOMElement getDetail();
}
