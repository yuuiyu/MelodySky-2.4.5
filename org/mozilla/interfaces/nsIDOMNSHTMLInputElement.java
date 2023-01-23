//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSHTMLInputElement extends nsISupports
{
    public static final String NS_IDOMNSHTMLINPUTELEMENT_IID = "{993d2efc-a768-11d3-bccd-0060b0fc76bd}";
    
    nsIControllers getControllers();
    
    int getTextLength();
    
    int getSelectionStart();
    
    void setSelectionStart(final int p0);
    
    int getSelectionEnd();
    
    void setSelectionEnd(final int p0);
    
    void setSelectionRange(final int p0, final int p1);
}
