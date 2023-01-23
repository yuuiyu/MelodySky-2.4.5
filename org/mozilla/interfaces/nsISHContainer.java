//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISHContainer extends nsISupports
{
    public static final String NS_ISHCONTAINER_IID = "{65281ba2-988a-11d3-bdc7-0050040a9b44}";
    
    int getChildCount();
    
    void addChild(final nsISHEntry p0, final int p1);
    
    void removeChild(final nsISHEntry p0);
    
    nsISHEntry getChildAt(final int p0);
}
