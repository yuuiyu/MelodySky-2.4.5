//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFrameLoader extends nsISupports
{
    public static final String NS_IFRAMELOADER_IID = "{88800e93-c6af-4d69-9ee0-29c1100ff431}";
    
    nsIDocShell getDocShell();
    
    void loadFrame();
    
    void destroy();
    
    boolean getDepthTooGreat();
}
