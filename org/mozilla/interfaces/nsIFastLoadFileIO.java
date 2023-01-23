//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFastLoadFileIO extends nsISupports
{
    public static final String NS_IFASTLOADFILEIO_IID = "{715577db-d9c5-464a-a32e-0a40c29b22d4}";
    
    nsIInputStream getInputStream();
    
    nsIOutputStream getOutputStream();
}
