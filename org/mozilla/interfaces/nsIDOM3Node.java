//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOM3Node extends nsISupports
{
    public static final String NS_IDOM3NODE_IID = "{29fb2a18-1dd2-11b2-8dd9-a6fd5d5ad12f}";
    public static final int DOCUMENT_POSITION_DISCONNECTED = 1;
    public static final int DOCUMENT_POSITION_PRECEDING = 2;
    public static final int DOCUMENT_POSITION_FOLLOWING = 4;
    public static final int DOCUMENT_POSITION_CONTAINS = 8;
    public static final int DOCUMENT_POSITION_CONTAINED_BY = 16;
    public static final int DOCUMENT_POSITION_IMPLEMENTATION_SPECIFIC = 32;
    
    String getBaseURI();
    
    int compareDocumentPosition(final nsIDOMNode p0);
    
    String getTextContent();
    
    void setTextContent(final String p0);
    
    boolean isSameNode(final nsIDOMNode p0);
    
    String lookupPrefix(final String p0);
    
    boolean isDefaultNamespace(final String p0);
    
    String lookupNamespaceURI(final String p0);
    
    boolean isEqualNode(final nsIDOMNode p0);
    
    nsISupports getFeature(final String p0, final String p1);
    
    nsIVariant setUserData(final String p0, final nsIVariant p1, final nsIDOMUserDataHandler p2);
    
    nsIVariant getUserData(final String p0);
}
