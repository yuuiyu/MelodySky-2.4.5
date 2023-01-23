//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICommandLine extends nsISupports
{
    public static final String NS_ICOMMANDLINE_IID = "{bc3173bd-aa46-46a0-9d25-d9867a9659b6}";
    public static final long STATE_INITIAL_LAUNCH = 0L;
    public static final long STATE_REMOTE_AUTO = 1L;
    public static final long STATE_REMOTE_EXPLICIT = 2L;
    
    int getLength();
    
    String getArgument(final int p0);
    
    int findFlag(final String p0, final boolean p1);
    
    void removeArguments(final int p0, final int p1);
    
    boolean handleFlag(final String p0, final boolean p1);
    
    String handleFlagWithParam(final String p0, final boolean p1);
    
    long getState();
    
    boolean getPreventDefault();
    
    void setPreventDefault(final boolean p0);
    
    nsIFile getWorkingDirectory();
    
    nsIDOMWindow getWindowContext();
    
    nsIFile resolveFile(final String p0);
    
    nsIURI resolveURI(final String p0);
}
