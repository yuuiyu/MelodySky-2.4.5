//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXULBuilderListener extends nsISupports
{
    public static final String NS_IXULBUILDERLISTENER_IID = "{ac46be8f-c863-4c23-84a2-d0fcc8dfa9f4}";
    
    void willRebuild(final nsIXULTemplateBuilder p0);
    
    void didRebuild(final nsIXULTemplateBuilder p0);
}
