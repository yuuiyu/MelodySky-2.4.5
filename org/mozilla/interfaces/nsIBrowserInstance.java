//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIBrowserInstance extends nsISupports
{
    public static final String NS_IBROWSERINSTANCE_IID = "{8af0fa40-598d-11d3-806a-00600811a9c3}";
    
    boolean startPageCycler();
    
    boolean getCmdLineURLUsed();
    
    void setCmdLineURLUsed(final boolean p0);
    
    void setWebShellWindow(final nsIDOMWindowInternal p0);
    
    void close();
}
