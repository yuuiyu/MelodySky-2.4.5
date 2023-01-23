//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.transformers;

import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.*;

public abstract class TreeTransformer implements IClassTransformer
{
    private ClassReader classReader;
    private ClassNode classNode;
    
    protected final ClassNode readClass(final byte[] basicClass) {
        return this.readClass(basicClass, true);
    }
    
    protected final ClassNode readClass(final byte[] basicClass, final boolean cacheReader) {
        final ClassReader classReader = new ClassReader(basicClass);
        if (cacheReader) {
            this.classReader = classReader;
        }
        final ClassNode classNode = new ClassNode();
        classReader.accept((ClassVisitor)classNode, 8);
        return classNode;
    }
    
    protected final byte[] writeClass(final ClassNode classNode) {
        if (this.classReader != null && this.classNode == classNode) {
            this.classNode = null;
            final ClassWriter writer = (ClassWriter)new MixinClassWriter(this.classReader, 3);
            this.classReader = null;
            classNode.accept((ClassVisitor)writer);
            return writer.toByteArray();
        }
        this.classNode = null;
        final ClassWriter writer = (ClassWriter)new MixinClassWriter(3);
        classNode.accept((ClassVisitor)writer);
        return writer.toByteArray();
    }
}
