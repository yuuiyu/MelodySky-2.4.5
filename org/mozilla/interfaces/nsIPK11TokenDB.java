//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPK11TokenDB extends nsISupports
{
    public static final String NS_IPK11TOKENDB_IID = "{4ee28c82-1dd2-11b2-aabf-bb4017abe395}";
    
    nsIPK11Token getInternalKeyToken();
    
    nsIPK11Token findTokenByName(final String p0);
    
    nsIEnumerator listTokens();
}
