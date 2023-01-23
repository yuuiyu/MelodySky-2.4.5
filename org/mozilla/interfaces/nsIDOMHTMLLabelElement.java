//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLLabelElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLLABELELEMENT_IID = "{a6cf9096-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMHTMLFormElement getForm();
    
    String getAccessKey();
    
    void setAccessKey(final String p0);
    
    String getHtmlFor();
    
    void setHtmlFor(final String p0);
}
