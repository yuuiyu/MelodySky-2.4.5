//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPTLoader extends nsISupports
{
    public static final String NS_IXPTLOADER_IID = "{368a15d9-17a9-4c2b-ac3d-a35b3a22b876}";
    
    void enumerateEntries(final nsILocalFile p0, final nsIXPTLoaderSink p1);
    
    nsIInputStream loadEntry(final nsILocalFile p0, final String p1);
}
