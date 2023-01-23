//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMHTMLOptionsCollection extends nsISupports
{
    public static final String NS_IDOMHTMLOPTIONSCOLLECTION_IID = "{bce0213c-f70f-488f-b93f-688acca55d63}";
    
    long getLength();
    
    void setLength(final long p0);
    
    nsIDOMNode item(final long p0);
    
    nsIDOMNode namedItem(final String p0);
}
