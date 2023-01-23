//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

public interface Drawable
{
    long internal_new_GC(final GCData p0);
    
    void internal_dispose_GC(final long p0, final GCData p1);
    
    default boolean isAutoScalable() {
        return true;
    }
}
