//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContentViewerContainer extends nsISupports
{
    public static final String NS_ICONTENTVIEWERCONTAINER_IID = "{ea2ce7a0-5c3d-11d4-90c2-0050041caf44}";
    
    void embed(final nsIContentViewer p0, final String p1, final nsISupports p2);
    
    void setIsPrinting(final boolean p0);
}
