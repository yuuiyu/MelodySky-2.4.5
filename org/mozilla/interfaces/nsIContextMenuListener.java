//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContextMenuListener extends nsISupports
{
    public static final String NS_ICONTEXTMENULISTENER_IID = "{3478b6b0-3875-11d4-94ef-0020183bf181}";
    public static final long CONTEXT_NONE = 0L;
    public static final long CONTEXT_LINK = 1L;
    public static final long CONTEXT_IMAGE = 2L;
    public static final long CONTEXT_DOCUMENT = 4L;
    public static final long CONTEXT_TEXT = 8L;
    public static final long CONTEXT_INPUT = 16L;
    
    void onShowContextMenu(final long p0, final nsIDOMEvent p1, final nsIDOMNode p2);
}
