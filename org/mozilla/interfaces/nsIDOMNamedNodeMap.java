//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNamedNodeMap extends nsISupports
{
    public static final String NS_IDOMNAMEDNODEMAP_IID = "{a6cf907b-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMNode getNamedItem(final String p0);
    
    nsIDOMNode setNamedItem(final nsIDOMNode p0);
    
    nsIDOMNode removeNamedItem(final String p0);
    
    nsIDOMNode item(final long p0);
    
    long getLength();
    
    nsIDOMNode getNamedItemNS(final String p0, final String p1);
    
    nsIDOMNode setNamedItemNS(final nsIDOMNode p0);
    
    nsIDOMNode removeNamedItemNS(final String p0, final String p1);
}
