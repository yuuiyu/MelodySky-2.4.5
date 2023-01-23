//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIContextMenuListener2 extends nsISupports
{
    public static final String NS_ICONTEXTMENULISTENER2_IID = "{7fb719b3-d804-4964-9596-77cf924ee314}";
    public static final long CONTEXT_NONE = 0L;
    public static final long CONTEXT_LINK = 1L;
    public static final long CONTEXT_IMAGE = 2L;
    public static final long CONTEXT_DOCUMENT = 4L;
    public static final long CONTEXT_TEXT = 8L;
    public static final long CONTEXT_INPUT = 16L;
    public static final long CONTEXT_BACKGROUND_IMAGE = 32L;
    
    void onShowContextMenu(final long p0, final nsIContextMenuInfo p1);
}
