//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFeedTextConstruct extends nsISupports
{
    public static final String NS_IFEEDTEXTCONSTRUCT_IID = "{fc97a2a9-d649-4494-931e-db81a156c873}";
    
    nsIURI getBase();
    
    void setBase(final nsIURI p0);
    
    String getLang();
    
    void setLang(final String p0);
    
    String getType();
    
    void setType(final String p0);
    
    String getText();
    
    void setText(final String p0);
    
    String plainText();
    
    nsIDOMDocumentFragment createDocumentFragment(final nsIDOMElement p0);
}
