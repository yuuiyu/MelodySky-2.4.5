//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

public interface Filter<T>
{
    Acceptance accept(final T p0);
    
    public enum Acceptance
    {
        YES, 
        NO, 
        TEST_CHILDREN;
    }
}
