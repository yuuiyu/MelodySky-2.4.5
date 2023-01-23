//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMElement extends nsIDOMNode
{
    public static final String NS_IDOMELEMENT_IID = "{a6cf9078-15b3-11d2-932e-00805f8add32}";
    
    String getTagName();
    
    String getAttribute(final String p0);
    
    void setAttribute(final String p0, final String p1);
    
    void removeAttribute(final String p0);
    
    nsIDOMAttr getAttributeNode(final String p0);
    
    nsIDOMAttr setAttributeNode(final nsIDOMAttr p0);
    
    nsIDOMAttr removeAttributeNode(final nsIDOMAttr p0);
    
    nsIDOMNodeList getElementsByTagName(final String p0);
    
    String getAttributeNS(final String p0, final String p1);
    
    void setAttributeNS(final String p0, final String p1, final String p2);
    
    void removeAttributeNS(final String p0, final String p1);
    
    nsIDOMAttr getAttributeNodeNS(final String p0, final String p1);
    
    nsIDOMAttr setAttributeNodeNS(final nsIDOMAttr p0);
    
    nsIDOMNodeList getElementsByTagNameNS(final String p0, final String p1);
    
    boolean hasAttribute(final String p0);
    
    boolean hasAttributeNS(final String p0, final String p1);
}
