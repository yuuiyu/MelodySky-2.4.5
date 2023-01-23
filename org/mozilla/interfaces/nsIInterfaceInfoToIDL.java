//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIInterfaceInfoToIDL extends nsISupports
{
    public static final String NS_IINTERFACEINFOTOIDL_IID = "{b01eb40c-026b-49c9-af55-25e8c9d034c8}";
    
    String generateIDL(final String p0, final boolean p1, final boolean p2);
    
    String[] getReferencedInterfaceNames(final String p0, final long[] p1);
}
