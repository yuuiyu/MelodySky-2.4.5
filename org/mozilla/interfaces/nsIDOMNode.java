//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNode extends nsISupports
{
    public static final String NS_IDOMNODE_IID = "{a6cf907c-15b3-11d2-932e-00805f8add32}";
    public static final int ELEMENT_NODE = 1;
    public static final int ATTRIBUTE_NODE = 2;
    public static final int TEXT_NODE = 3;
    public static final int CDATA_SECTION_NODE = 4;
    public static final int ENTITY_REFERENCE_NODE = 5;
    public static final int ENTITY_NODE = 6;
    public static final int PROCESSING_INSTRUCTION_NODE = 7;
    public static final int COMMENT_NODE = 8;
    public static final int DOCUMENT_NODE = 9;
    public static final int DOCUMENT_TYPE_NODE = 10;
    public static final int DOCUMENT_FRAGMENT_NODE = 11;
    public static final int NOTATION_NODE = 12;
    
    String getNodeName();
    
    String getNodeValue();
    
    void setNodeValue(final String p0);
    
    int getNodeType();
    
    nsIDOMNode getParentNode();
    
    nsIDOMNodeList getChildNodes();
    
    nsIDOMNode getFirstChild();
    
    nsIDOMNode getLastChild();
    
    nsIDOMNode getPreviousSibling();
    
    nsIDOMNode getNextSibling();
    
    nsIDOMNamedNodeMap getAttributes();
    
    nsIDOMDocument getOwnerDocument();
    
    nsIDOMNode insertBefore(final nsIDOMNode p0, final nsIDOMNode p1);
    
    nsIDOMNode replaceChild(final nsIDOMNode p0, final nsIDOMNode p1);
    
    nsIDOMNode removeChild(final nsIDOMNode p0);
    
    nsIDOMNode appendChild(final nsIDOMNode p0);
    
    boolean hasChildNodes();
    
    nsIDOMNode cloneNode(final boolean p0);
    
    void normalize();
    
    boolean isSupported(final String p0, final String p1);
    
    String getNamespaceURI();
    
    String getPrefix();
    
    void setPrefix(final String p0);
    
    String getLocalName();
    
    boolean hasAttributes();
}
