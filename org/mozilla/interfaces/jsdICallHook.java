//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdICallHook extends nsISupports
{
    public static final String JSDICALLHOOK_IID = "{f102caf6-1dd1-11b2-bd43-c1dbacb95a98}";
    public static final long TYPE_TOPLEVEL_START = 0L;
    public static final long TYPE_TOPLEVEL_END = 1L;
    public static final long TYPE_FUNCTION_CALL = 2L;
    public static final long TYPE_FUNCTION_RETURN = 3L;
    
    void onCall(final jsdIStackFrame p0, final long p1);
}
