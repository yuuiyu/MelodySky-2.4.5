//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXPCComponents extends nsISupports
{
    public static final String NS_IXPCCOMPONENTS_IID = "{155809f1-71f1-47c5-be97-d812ba560405}";
    
    nsIScriptableInterfaces getInterfaces();
    
    nsIScriptableInterfacesByID getInterfacesByID();
    
    nsIXPCComponents_Classes getClasses();
    
    nsIXPCComponents_ClassesByID getClassesByID();
    
    nsIStackFrame getStack();
    
    nsIXPCComponents_Results getResults();
    
    nsIComponentManager getManager();
    
    nsIXPCComponents_Utils getUtils();
    
    nsIXPCComponents_ID getID();
    
    nsIXPCComponents_Exception getException();
    
    nsIXPCComponents_Constructor getConstructor();
    
    boolean isSuccessCode(final long p0);
    
    void lookupMethod();
    
    void reportError();
}
