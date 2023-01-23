//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPEncoding extends nsISupports
{
    public static final String NS_ISOAPENCODING_IID = "{9ae49600-1dd1-11b2-877f-e62f620c5e92}";
    
    String getStyleURI();
    
    nsISOAPEncoding getAssociatedEncoding(final String p0, final boolean p1);
    
    void setEncoder(final String p0, final nsISOAPEncoder p1);
    
    nsISOAPEncoder getEncoder(final String p0);
    
    void setDecoder(final String p0, final nsISOAPDecoder p1);
    
    nsISOAPDecoder getDecoder(final String p0);
    
    nsISOAPEncoder getDefaultEncoder();
    
    void setDefaultEncoder(final nsISOAPEncoder p0);
    
    nsISOAPDecoder getDefaultDecoder();
    
    void setDefaultDecoder(final nsISOAPDecoder p0);
    
    nsISchemaCollection getSchemaCollection();
    
    void setSchemaCollection(final nsISchemaCollection p0);
    
    nsIDOMElement encode(final nsIVariant p0, final String p1, final String p2, final nsISchemaType p3, final nsISOAPAttachments p4, final nsIDOMElement p5);
    
    nsIVariant decode(final nsIDOMElement p0, final nsISchemaType p1, final nsISOAPAttachments p2);
    
    boolean mapSchemaURI(final String p0, final String p1, final boolean p2);
    
    boolean unmapSchemaURI(final String p0);
    
    String getInternalSchemaURI(final String p0);
    
    String getExternalSchemaURI(final String p0);
}
