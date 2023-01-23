//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISupportsPriority extends nsISupports
{
    public static final String NS_ISUPPORTSPRIORITY_IID = "{aa578b44-abd5-4c19-8b14-36d4de6fdc36}";
    public static final int PRIORITY_HIGHEST = -20;
    public static final int PRIORITY_HIGH = -10;
    public static final int PRIORITY_NORMAL = 0;
    public static final int PRIORITY_LOW = 10;
    public static final int PRIORITY_LOWEST = 20;
    
    int getPriority();
    
    void setPriority(final int p0);
    
    void adjustPriority(final int p0);
}
