//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIServerSocket extends nsISupports
{
    public static final String NS_ISERVERSOCKET_IID = "{a5b64be0-d563-46bb-ae95-132e46fcd42f}";
    
    void init(final int p0, final boolean p1, final int p2);
    
    void close();
    
    void asyncListen(final nsIServerSocketListener p0);
    
    int getPort();
}
