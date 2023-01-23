//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAppShellService extends nsISupports
{
    public static final String NS_IAPPSHELLSERVICE_IID = "{93a28ba2-7e22-11d9-9b6f-000a95d535fa}";
    public static final int SIZE_TO_CONTENT = -1;
    
    nsIXULWindow createTopLevelWindow(final nsIXULWindow p0, final nsIURI p1, final long p2, final int p3, final int p4, final nsIAppShell p5);
    
    void destroyHiddenWindow();
    
    nsIXULWindow getHiddenWindow();
    
    nsIDOMWindowInternal getHiddenDOMWindow();
    
    void registerTopLevelWindow(final nsIXULWindow p0);
    
    void unregisterTopLevelWindow(final nsIXULWindow p0);
    
    void topLevelWindowIsModal(final nsIXULWindow p0, final boolean p1);
}
