//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIStackFrame extends jsdIEphemeral
{
    public static final String JSDISTACKFRAME_IID = "{b6d50784-1dd1-11b2-a932-882246c6fe45}";
    
    boolean getIsNative();
    
    boolean getIsDebugger();
    
    boolean getIsConstructing();
    
    jsdIStackFrame getCallingFrame();
    
    jsdIContext getExecutionContext();
    
    String getFunctionName();
    
    jsdIScript getScript();
    
    long getPc();
    
    long getLine();
    
    jsdIValue getCallee();
    
    jsdIValue getScope();
    
    jsdIValue getThisValue();
    
    boolean eval(final String p0, final String p1, final long p2, final jsdIValue[] p3);
}
