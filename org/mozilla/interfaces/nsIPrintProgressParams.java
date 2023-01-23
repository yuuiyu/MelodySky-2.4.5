//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintProgressParams extends nsISupports
{
    public static final String NS_IPRINTPROGRESSPARAMS_IID = "{ca89b55b-6faf-4051-9645-1c03ef5108f8}";
    
    String getDocTitle();
    
    void setDocTitle(final String p0);
    
    String getDocURL();
    
    void setDocURL(final String p0);
}
