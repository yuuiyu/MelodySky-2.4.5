//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDocument extends nsIDOMNode
{
    public static final String NS_IDOMDOCUMENT_IID = "{a6cf9075-15b3-11d2-932e-00805f8add32}";
    
    nsIDOMDocumentType getDoctype();
    
    nsIDOMDOMImplementation getImplementation();
    
    nsIDOMElement getDocumentElement();
    
    nsIDOMElement createElement(final String p0);
    
    nsIDOMDocumentFragment createDocumentFragment();
    
    nsIDOMText createTextNode(final String p0);
    
    nsIDOMComment createComment(final String p0);
    
    nsIDOMCDATASection createCDATASection(final String p0);
    
    nsIDOMProcessingInstruction createProcessingInstruction(final String p0, final String p1);
    
    nsIDOMAttr createAttribute(final String p0);
    
    nsIDOMEntityReference createEntityReference(final String p0);
    
    nsIDOMNodeList getElementsByTagName(final String p0);
    
    nsIDOMNode importNode(final nsIDOMNode p0, final boolean p1);
    
    nsIDOMElement createElementNS(final String p0, final String p1);
    
    nsIDOMAttr createAttributeNS(final String p0, final String p1);
    
    nsIDOMNodeList getElementsByTagNameNS(final String p0, final String p1);
    
    nsIDOMElement getElementById(final String p0);
}
