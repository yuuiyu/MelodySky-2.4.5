//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOM3Document extends nsIDOM3Node
{
    public static final String NS_IDOM3DOCUMENT_IID = "{2e0e9ea1-72ab-4d9e-bdeb-ca64e1abeba4}";
    
    String getInputEncoding();
    
    String getXmlEncoding();
    
    boolean getXmlStandalone();
    
    void setXmlStandalone(final boolean p0);
    
    String getXmlVersion();
    
    void setXmlVersion(final String p0);
    
    boolean getStrictErrorChecking();
    
    void setStrictErrorChecking(final boolean p0);
    
    String getDocumentURI();
    
    void setDocumentURI(final String p0);
    
    nsIDOMNode adoptNode(final nsIDOMNode p0);
    
    nsIDOMDOMConfiguration getDomConfig();
    
    void normalizeDocument();
    
    nsIDOMNode renameNode(final nsIDOMNode p0, final String p1, final String p2);
}
