//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDebug extends nsISupports
{
    public static final String NS_IDEBUG_IID = "{3bf0c3d7-3bd9-4cf2-a971-33572c503e1e}";
    
    void assertion(final String p0, final String p1, final String p2, final int p3);
    
    void warning(final String p0, final String p1, final int p2);
    
    void _break(final String p0, final int p1);
    
    void abort(final String p0, final int p1);
}
