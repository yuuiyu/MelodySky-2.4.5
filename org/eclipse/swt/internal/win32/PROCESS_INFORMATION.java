//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.win32;

public class PROCESS_INFORMATION
{
    public long hProcess;
    public long hThread;
    public int dwProcessId;
    public int dwThreadId;
    public static int sizeof;
    
    static {
        PROCESS_INFORMATION.sizeof = OS.PROCESS_INFORMATION_sizeof();
    }
}
