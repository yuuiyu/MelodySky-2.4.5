//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMAttr extends nsIDOMNode
{
    public static final String NS_IDOMATTR_IID = "{a6cf9070-15b3-11d2-932e-00805f8add32}";
    
    String getName();
    
    boolean getSpecified();
    
    String getValue();
    
    void setValue(final String p0);
    
    nsIDOMElement getOwnerElement();
}
