//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.*;

public class MixinClassWriter extends ClassWriter
{
    private static final String JAVA_LANG_OBJECT = "java/lang/Object";
    
    public MixinClassWriter(final int flags) {
        super(flags);
    }
    
    public MixinClassWriter(final ClassReader classReader, final int flags) {
        super(classReader, flags);
    }
    
    protected String getCommonSuperClass(final String type1, final String type2) {
        ClassInfo t1 = ClassInfo.forName(type1);
        final ClassInfo t2 = ClassInfo.forName(type2);
        if (t1.hasSuperClass(t2)) {
            return type2;
        }
        if (t2.hasSuperClass(t1)) {
            return type1;
        }
        if (t1.isInterface() || t2.isInterface()) {
            return "java/lang/Object";
        }
        do {
            t1 = t1.getSuperClass();
            if (t1 == null) {
                return "java/lang/Object";
            }
        } while (!t2.hasSuperClass(t1));
        return t1.getName();
    }
}
