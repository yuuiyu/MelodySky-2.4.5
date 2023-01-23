//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLFormElement extends nsIDOMHTMLElement
{
    public static final String NS_IDOMHTMLFORMELEMENT_IID = "{a6cf908f-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMHTMLCollection getElements();
    
    int getLength();
    
    String getName();
    
    void setName(final String p0);
    
    String getAcceptCharset();
    
    void setAcceptCharset(final String p0);
    
    String getAction();
    
    void setAction(final String p0);
    
    String getEnctype();
    
    void setEnctype(final String p0);
    
    String getMethod();
    
    void setMethod(final String p0);
    
    String getTarget();
    
    void setTarget(final String p0);
    
    void submit();
    
    void reset();
}
