//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIDebuggerService extends nsISupports
{
    public static final String JSDIDEBUGGERSERVICE_IID = "{9dd9006a-4e5e-4a80-ac3d-007fb7335ca4}";
    public static final int VERSION_1_0 = 100;
    public static final int VERSION_1_1 = 110;
    public static final int VERSION_1_2 = 120;
    public static final int VERSION_1_3 = 130;
    public static final int VERSION_1_4 = 140;
    public static final int VERSION_1_5 = 150;
    public static final int VERSION_DEFAULT = 0;
    public static final int VERSION_UNKNOWN = -1;
    public static final long ENABLE_NATIVE_FRAMES = 1L;
    public static final long PROFILE_WHEN_SET = 2L;
    public static final long DEBUG_WHEN_SET = 4L;
    public static final long COLLECT_PROFILE_DATA = 8L;
    public static final long HIDE_DISABLED_FRAMES = 16L;
    public static final long MASK_TOP_FRAME_ONLY = 32L;
    public static final long DISABLE_OBJECT_TRACE = 64L;
    
    jsdIErrorHook getErrorHook();
    
    void setErrorHook(final jsdIErrorHook p0);
    
    jsdIScriptHook getScriptHook();
    
    void setScriptHook(final jsdIScriptHook p0);
    
    jsdIExecutionHook getBreakpointHook();
    
    void setBreakpointHook(final jsdIExecutionHook p0);
    
    jsdIExecutionHook getDebuggerHook();
    
    void setDebuggerHook(final jsdIExecutionHook p0);
    
    jsdIExecutionHook getDebugHook();
    
    void setDebugHook(final jsdIExecutionHook p0);
    
    jsdIExecutionHook getInterruptHook();
    
    void setInterruptHook(final jsdIExecutionHook p0);
    
    jsdIExecutionHook getThrowHook();
    
    void setThrowHook(final jsdIExecutionHook p0);
    
    jsdICallHook getTopLevelHook();
    
    void setTopLevelHook(final jsdICallHook p0);
    
    jsdICallHook getFunctionHook();
    
    void setFunctionHook(final jsdICallHook p0);
    
    long getFlags();
    
    void setFlags(final long p0);
    
    long getImplementationMajor();
    
    long getImplementationMinor();
    
    String getImplementationString();
    
    boolean getInitAtStartup();
    
    void setInitAtStartup(final boolean p0);
    
    boolean getIsOn();
    
    void on();
    
    void off();
    
    long getPauseDepth();
    
    long pause();
    
    long unPause();
    
    void gC();
    
    void clearProfileData();
    
    void insertFilter(final jsdIFilter p0, final jsdIFilter p1);
    
    void appendFilter(final jsdIFilter p0);
    
    void removeFilter(final jsdIFilter p0);
    
    void swapFilters(final jsdIFilter p0, final jsdIFilter p1);
    
    void enumerateFilters(final jsdIFilterEnumerator p0);
    
    void refreshFilters();
    
    void clearFilters();
    
    void enumerateContexts(final jsdIContextEnumerator p0);
    
    void enumerateScripts(final jsdIScriptEnumerator p0);
    
    void clearAllBreakpoints();
    
    jsdIValue wrapValue();
    
    long enterNestedEventLoop(final jsdINestCallback p0);
    
    long exitNestedEventLoop();
}
