//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import java.io.*;

public interface IGRE
{
    void initEmbedding(final File p0, final File p1, final IAppFileLocProvider p2) throws XPCOMException;
    
    void termEmbedding();
    
    ProfileLock lockProfileDirectory(final File p0) throws XPCOMException;
    
    void notifyProfile();
}
