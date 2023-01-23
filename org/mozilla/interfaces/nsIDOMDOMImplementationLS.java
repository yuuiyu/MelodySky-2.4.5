//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMDOMImplementationLS extends nsISupports
{
    public static final String NS_IDOMDOMIMPLEMENTATIONLS_IID = "{e2c8b03c-a49a-4923-81b0-ba9a86da0e21}";
    public static final int MODE_SYNCHRONOUS = 1;
    public static final int MODE_ASYNCHRONOUS = 2;
    
    nsIDOMLSParser createLSParser(final int p0, final String p1);
    
    nsIDOMLSSerializer createLSSerializer();
    
    nsIDOMLSInput createLSInput();
    
    nsIDOMLSOutput createLSOutput();
}
