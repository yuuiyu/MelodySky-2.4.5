//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICacheVisitor extends nsISupports
{
    public static final String NS_ICACHEVISITOR_IID = "{f8c08c4b-d778-49d1-a59b-866fdc500d95}";
    
    boolean visitDevice(final String p0, final nsICacheDeviceInfo p1);
    
    boolean visitEntry(final String p0, final nsICacheEntryInfo p1);
}
