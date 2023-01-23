//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIControllers extends nsISupports
{
    public static final String NS_ICONTROLLERS_IID = "{a5ed3a01-7cc7-11d3-bf87-00105a1b0627}";
    
    nsIDOMXULCommandDispatcher getCommandDispatcher();
    
    void setCommandDispatcher(final nsIDOMXULCommandDispatcher p0);
    
    nsIController getControllerForCommand(final String p0);
    
    void insertControllerAt(final long p0, final nsIController p1);
    
    nsIController removeControllerAt(final long p0);
    
    nsIController getControllerAt(final long p0);
    
    void appendController(final nsIController p0);
    
    void removeController(final nsIController p0);
    
    long getControllerId(final nsIController p0);
    
    nsIController getControllerById(final long p0);
    
    long getControllerCount();
}
