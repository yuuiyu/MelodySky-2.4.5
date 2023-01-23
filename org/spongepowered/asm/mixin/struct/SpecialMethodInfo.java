//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.struct;

import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.transformer.*;

public abstract class SpecialMethodInfo
{
    protected final AnnotationNode annotation;
    protected final ClassNode classNode;
    protected final MethodNode method;
    protected final MixinTargetContext mixin;
    
    public SpecialMethodInfo(final MixinTargetContext mixin, final MethodNode method, final AnnotationNode annotation) {
        this.mixin = mixin;
        this.method = method;
        this.annotation = annotation;
        this.classNode = mixin.getTargetClassNode();
    }
    
    public final MixinTargetContext getContext() {
        return this.mixin;
    }
    
    public final AnnotationNode getAnnotation() {
        return this.annotation;
    }
    
    public final ClassNode getClassNode() {
        return this.classNode;
    }
    
    public final MethodNode getMethod() {
        return this.method;
    }
}
