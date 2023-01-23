//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMStyleSheet extends nsISupports
{
    public static final String NS_IDOMSTYLESHEET_IID = "{a6cf9080-15b3-11d2-932e-00805f8add32}";
    
    String getType();
    
    boolean getDisabled();
    
    void setDisabled(final boolean p0);
    
    nsIDOMNode getOwnerNode();
    
    nsIDOMStyleSheet getParentStyleSheet();
    
    String getHref();
    
    String getTitle();
    
    nsIDOMMediaList getMedia();
}
