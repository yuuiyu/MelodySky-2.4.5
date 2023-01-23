//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDialogParamBlock extends nsISupports
{
    public static final String NS_IDIALOGPARAMBLOCK_IID = "{f76c0901-437a-11d3-b7a0-e35db351b4bc}";
    
    int getInt(final int p0);
    
    void setInt(final int p0, final int p1);
    
    void setNumberStrings(final int p0);
    
    String getString(final int p0);
    
    void setString(final int p0, final String p1);
    
    nsIMutableArray getObjects();
    
    void setObjects(final nsIMutableArray p0);
}
