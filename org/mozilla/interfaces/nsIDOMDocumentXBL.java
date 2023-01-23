//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDocumentXBL extends nsISupports
{
    public static final String NS_IDOMDOCUMENTXBL_IID = "{c7c0ae9b-a0ba-4f4e-9f2c-c18deb62ee8b}";
    
    nsIDOMNodeList getAnonymousNodes(final nsIDOMElement p0);
    
    nsIDOMElement getAnonymousElementByAttribute(final nsIDOMElement p0, final String p1, final String p2);
    
    void addBinding(final nsIDOMElement p0, final String p1);
    
    void removeBinding(final nsIDOMElement p0, final String p1);
    
    nsIDOMElement getBindingParent(final nsIDOMNode p0);
    
    nsIDOMDocument loadBindingDocument(final String p0);
}
