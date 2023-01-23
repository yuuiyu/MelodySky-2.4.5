//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISOAPCallCompletion extends nsISupports
{
    public static final String NS_ISOAPCALLCOMPLETION_IID = "{86114dd8-1dd2-11b2-ab2b-91d0c995e03a}";
    
    nsISOAPCall getCall();
    
    nsISOAPResponse getResponse();
    
    nsISOAPResponseListener getListener();
    
    boolean getIsComplete();
    
    boolean abort();
}
