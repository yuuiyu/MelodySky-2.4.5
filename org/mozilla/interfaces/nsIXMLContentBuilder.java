//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXMLContentBuilder extends nsISupports
{
    public static final String NS_IXMLCONTENTBUILDER_IID = "{e9c4cd4f-cd41-43d0-bf3b-48abb9cde90f}";
    
    void clear(final nsIDOMElement p0);
    
    void setDocument(final nsIDOMDocument p0);
    
    void setElementNamespace(final String p0);
    
    void beginElement(final String p0);
    
    void endElement();
    
    void attrib(final String p0, final String p1);
    
    void textNode(final String p0);
    
    nsIDOMElement getRoot();
    
    nsIDOMElement getCurrent();
}
