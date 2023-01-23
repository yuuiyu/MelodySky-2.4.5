//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping.mcp;

import org.spongepowered.tools.obfuscation.mapping.common.*;
import javax.annotation.processing.*;
import java.nio.charset.*;
import com.google.common.collect.*;
import com.google.common.io.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import java.io.*;
import org.spongepowered.asm.obfuscation.mapping.mcp.*;

public class MappingProviderSrg extends MappingProvider
{
    public MappingProviderSrg(final Messager messager, final Filer filer) {
        super(messager, filer);
    }
    
    public void read(final File input) throws IOException {
        final BiMap<String, String> packageMap = (BiMap<String, String>)this.packageMap;
        final BiMap<String, String> classMap = (BiMap<String, String>)this.classMap;
        final BiMap<MappingField, MappingField> fieldMap = (BiMap<MappingField, MappingField>)this.fieldMap;
        final BiMap<MappingMethod, MappingMethod> methodMap = (BiMap<MappingMethod, MappingMethod>)this.methodMap;
        Files.readLines(input, Charset.defaultCharset(), (LineProcessor)new l(this, (BiMap)packageMap, (BiMap)classMap, (BiMap)fieldMap, (BiMap)methodMap, input));
    }
    
    public MappingField getFieldMapping(MappingField field) {
        if (field.getDesc() != null) {
            field = (MappingField)new MappingFieldSrg(field);
        }
        return (MappingField)this.fieldMap.get((Object)field);
    }
}
