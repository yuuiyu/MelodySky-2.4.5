//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping;

import java.io.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public interface IMappingProvider
{
    void clear();
    
    boolean isEmpty();
    
    void read(final File p0) throws IOException;
    
    MappingMethod getMethodMapping(final MappingMethod p0);
    
    MappingField getFieldMapping(final MappingField p0);
    
    String getClassMapping(final String p0);
    
    String getPackageMapping(final String p0);
}
