//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXULCommandDispatcher extends nsISupports
{
    public static final String NS_IDOMXULCOMMANDDISPATCHER_IID = "{f3c50361-14fe-11d3-bf87-00105a1b0627}";
    
    nsIDOMElement getFocusedElement();
    
    void setFocusedElement(final nsIDOMElement p0);
    
    nsIDOMWindow getFocusedWindow();
    
    void setFocusedWindow(final nsIDOMWindow p0);
    
    void addCommandUpdater(final nsIDOMElement p0, final String p1, final String p2);
    
    void removeCommandUpdater(final nsIDOMElement p0);
    
    void updateCommands(final String p0);
    
    nsIController getControllerForCommand(final String p0);
    
    nsIControllers getControllers();
    
    void advanceFocus();
    
    void rewindFocus();
    
    void advanceFocusIntoSubtree(final nsIDOMElement p0);
    
    boolean getSuppressFocusScroll();
    
    void setSuppressFocusScroll(final boolean p0);
}
