//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.interfaces;

import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public interface IReferenceManager
{
    void write();
    
    ReferenceMapper getMapper();
    
    void addMethodMapping(final String p0, final String p1, final ObfuscationData<MappingMethod> p2);
    
    void addMethodMapping(final String p0, final String p1, final MemberInfo p2, final ObfuscationData<MappingMethod> p3);
    
    void addFieldMapping(final String p0, final String p1, final MemberInfo p2, final ObfuscationData<MappingField> p3);
    
    void addClassMapping(final String p0, final String p1, final ObfuscationData<String> p2);
}
