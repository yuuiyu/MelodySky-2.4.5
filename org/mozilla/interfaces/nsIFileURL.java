//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFileURL extends nsIURL
{
    public static final String NS_IFILEURL_IID = "{d26b2e2e-1dd1-11b2-88f3-8545a7ba7949}";
    
    nsIFile getFile();
    
    void setFile(final nsIFile p0);
}
