//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIIDNService extends nsISupports
{
    public static final String NS_IIDNSERVICE_IID = "{7b67747e-a8c4-4832-80c7-39ebb0c11f94}";
    
    String convertUTF8toACE(final String p0);
    
    String convertACEtoUTF8(final String p0);
    
    boolean isACE(final String p0);
    
    String normalize(final String p0);
}
