//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIMozIconURI extends nsIURI
{
    public static final String NS_IMOZICONURI_IID = "{1fb33f44-f522-4880-a225-4b75d09b04c6}";
    
    nsIURI getIconFile();
    
    void setIconFile(final nsIURI p0);
    
    long getImageSize();
    
    void setImageSize(final long p0);
    
    String getStockIcon();
    
    String getIconSize();
    
    String getIconState();
    
    String getContentType();
    
    void setContentType(final String p0);
    
    String getFileExtension();
}
