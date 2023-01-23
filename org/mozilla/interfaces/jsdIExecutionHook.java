//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIExecutionHook extends nsISupports
{
    public static final String JSDIEXECUTIONHOOK_IID = "{9a7b6ad0-1dd1-11b2-a789-fcfae96356a2}";
    public static final long TYPE_INTERRUPTED = 0L;
    public static final long TYPE_BREAKPOINT = 1L;
    public static final long TYPE_DEBUG_REQUESTED = 2L;
    public static final long TYPE_DEBUGGER_KEYWORD = 3L;
    public static final long TYPE_THROW = 4L;
    public static final long RETURN_HOOK_ERROR = 0L;
    public static final long RETURN_CONTINUE = 1L;
    public static final long RETURN_ABORT = 2L;
    public static final long RETURN_RET_WITH_VAL = 3L;
    public static final long RETURN_THROW_WITH_VAL = 4L;
    public static final long RETURN_CONTINUE_THROW = 5L;
    
    long onExecute(final jsdIStackFrame p0, final long p1, final jsdIValue[] p2);
}
