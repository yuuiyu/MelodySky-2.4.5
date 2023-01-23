//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDirIndexParser extends nsIStreamListener
{
    public static final String NS_IDIRINDEXPARSER_IID = "{38e3066c-1dd2-11b2-9b59-8be515c1ee3f}";
    
    nsIDirIndexListener getListener();
    
    void setListener(final nsIDirIndexListener p0);
    
    String getComment();
    
    String getEncoding();
    
    void setEncoding(final String p0);
}
