//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPBlock extends nsISupports
{
    public static final String NS_ISOAPBLOCK_IID = "{843afaa8-1dd2-11b2-8b0d-9b5d16fe64ea}";
    
    void init(final nsISOAPAttachments p0, final int p1);
    
    String getNamespaceURI();
    
    void setNamespaceURI(final String p0);
    
    String getName();
    
    void setName(final String p0);
    
    nsISOAPEncoding getEncoding();
    
    void setEncoding(final nsISOAPEncoding p0);
    
    nsISchemaType getSchemaType();
    
    void setSchemaType(final nsISchemaType p0);
    
    nsIDOMElement getElement();
    
    void setElement(final nsIDOMElement p0);
    
    nsIVariant getValue();
    
    void setValue(final nsIVariant p0);
}
