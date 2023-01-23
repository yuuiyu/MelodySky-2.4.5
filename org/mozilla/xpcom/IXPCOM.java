//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import java.io.*;
import org.mozilla.interfaces.*;

public interface IXPCOM
{
    nsIServiceManager initXPCOM(final File p0, final IAppFileLocProvider p1) throws XPCOMException;
    
    void shutdownXPCOM(final nsIServiceManager p0) throws XPCOMException;
    
    nsIServiceManager getServiceManager() throws XPCOMException;
    
    nsIComponentManager getComponentManager() throws XPCOMException;
    
    nsIComponentRegistrar getComponentRegistrar() throws XPCOMException;
    
    nsILocalFile newLocalFile(final String p0, final boolean p1) throws XPCOMException;
}
