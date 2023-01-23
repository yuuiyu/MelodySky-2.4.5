//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorLogging extends nsISupports
{
    public static final String NS_IEDITORLOGGING_IID = "{4805e681-49b9-11d3-9ce4-ed60bd6cb5bc}";
    
    void startLogging(final nsIFile p0);
    
    void stopLogging();
}
