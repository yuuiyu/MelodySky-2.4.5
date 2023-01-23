//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProcess extends nsISupports
{
    public static final String NS_IPROCESS_IID = "{9da0b650-d07e-4617-a18a-250035572ac8}";
    
    void init(final nsIFile p0);
    
    void initWithPid(final long p0);
    
    void kill();
    
    long run(final boolean p0, final String[] p1, final long p2);
    
    nsIFile getLocation();
    
    long getPid();
    
    String getProcessName();
    
    long getProcessSignature();
    
    int getExitValue();
}
