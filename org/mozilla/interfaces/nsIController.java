//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIController extends nsISupports
{
    public static final String NS_ICONTROLLER_IID = "{d5b61b82-1da4-11d3-bf87-00105a1b0627}";
    
    boolean isCommandEnabled(final String p0);
    
    boolean supportsCommand(final String p0);
    
    void doCommand(final String p0);
    
    void onEvent(final String p0);
}
