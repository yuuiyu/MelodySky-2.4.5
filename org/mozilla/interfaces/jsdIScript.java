//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIScript extends jsdIEphemeral
{
    public static final String JSDISCRIPT_IID = "{a38f65ca-1dd1-11b2-95d5-ff2947e9c920}";
    public static final long FLAG_PROFILE = 1L;
    public static final long FLAG_DEBUG = 2L;
    public static final long PCMAP_SOURCETEXT = 1L;
    public static final long PCMAP_PRETTYPRINT = 2L;
    
    int getVersion();
    
    long getTag();
    
    long getFlags();
    
    void setFlags(final long p0);
    
    String getFileName();
    
    String getFunctionName();
    
    jsdIValue getFunctionObject();
    
    String getFunctionSource();
    
    long getBaseLineNumber();
    
    long getLineExtent();
    
    long getCallCount();
    
    long getMaxRecurseDepth();
    
    double getMinExecutionTime();
    
    double getMaxExecutionTime();
    
    double getTotalExecutionTime();
    
    double getMinOwnExecutionTime();
    
    double getMaxOwnExecutionTime();
    
    double getTotalOwnExecutionTime();
    
    void clearProfileData();
    
    long pcToLine(final long p0, final long p1);
    
    long lineToPc(final long p0, final long p1);
    
    boolean isLineExecutable(final long p0, final long p1);
    
    void setBreakpoint(final long p0);
    
    void clearBreakpoint(final long p0);
    
    void clearAllBreakpoints();
}
