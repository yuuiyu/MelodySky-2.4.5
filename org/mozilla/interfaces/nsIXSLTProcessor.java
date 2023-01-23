//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXSLTProcessor extends nsISupports
{
    public static final String NS_IXSLTPROCESSOR_IID = "{4a91aeb3-4100-43ee-a21e-9866268757c5}";
    
    void importStylesheet(final nsIDOMNode p0);
    
    nsIDOMDocumentFragment transformToFragment(final nsIDOMNode p0, final nsIDOMDocument p1);
    
    nsIDOMDocument transformToDocument(final nsIDOMNode p0);
    
    void setParameter(final String p0, final String p1, final nsIVariant p2);
    
    nsIVariant getParameter(final String p0, final String p1);
    
    void removeParameter(final String p0, final String p1);
    
    void clearParameters();
    
    void reset();
}
