//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.extensibility;

public interface IRemapper
{
    String mapMethodName(final String p0, final String p1, final String p2);
    
    String mapFieldName(final String p0, final String p1, final String p2);
    
    String map(final String p0);
    
    String unmap(final String p0);
    
    String mapDesc(final String p0);
    
    String unmapDesc(final String p0);
}
