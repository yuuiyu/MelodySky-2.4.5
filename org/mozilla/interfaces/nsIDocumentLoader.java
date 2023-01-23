//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocumentLoader extends nsISupports
{
    public static final String NS_IDOCUMENTLOADER_IID = "{bbe961ee-59e9-42bb-be50-0331979bb79f}";
    
    void stop();
    
    nsISupports getContainer();
    
    nsILoadGroup getLoadGroup();
    
    nsIChannel getDocumentChannel();
}
