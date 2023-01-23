//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLTextAreaElement extends nsISupports
{
    public static final String NS_IDOMNSHTMLTEXTAREAELEMENT_IID = "{ca066b44-9ddf-11d3-bccc-0060b0fc76bd}";
    
    nsIControllers getControllers();
    
    int getTextLength();
    
    int getSelectionStart();
    
    void setSelectionStart(final int p0);
    
    int getSelectionEnd();
    
    void setSelectionEnd(final int p0);
    
    void setSelectionRange(final int p0, final int p1);
}
