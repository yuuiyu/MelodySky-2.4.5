//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIScriptError extends nsIConsoleMessage
{
    public static final String NS_ISCRIPTERROR_IID = "{b0196fc7-1913-441a-882a-453c0d8b89b8}";
    public static final long errorFlag = 0L;
    public static final long warningFlag = 1L;
    public static final long exceptionFlag = 2L;
    public static final long strictFlag = 4L;
    
    String getErrorMessage();
    
    String getSourceName();
    
    String getSourceLine();
    
    long getLineNumber();
    
    long getColumnNumber();
    
    long getFlags();
    
    String getCategory();
    
    void init(final String p0, final String p1, final String p2, final long p3, final long p4, final long p5, final String p6);
    
    String toString();
}
