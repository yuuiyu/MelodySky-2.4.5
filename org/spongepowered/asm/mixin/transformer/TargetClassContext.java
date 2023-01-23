//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.mixin.transformer.meta.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.util.*;
import java.lang.annotation.*;
import java.io.*;
import org.spongepowered.asm.lib.tree.*;

class TargetClassContext
{
    private final String sessionId;
    private final String className;
    private final ClassNode classNode;
    private final ClassInfo classInfo;
    private final SourceMap sourceMap;
    private final SortedSet<MixinInfo> mixins;
    private final Map<String, Target> targetMethods;
    private int nextUniqueMethodIndex;
    private int nextUniqueFieldIndex;
    private boolean applied;
    private boolean forceExport;
    
    TargetClassContext(final String sessionId, final String name, final ClassNode classNode, final SortedSet<MixinInfo> mixins) {
        this.targetMethods = new HashMap<String, Target>();
        this.sessionId = sessionId;
        this.className = name;
        this.classNode = classNode;
        this.classInfo = ClassInfo.fromClassNode(classNode);
        this.mixins = mixins;
        (this.sourceMap = new SourceMap(classNode.sourceFile)).addFile(this.classNode);
    }
    
    @Override
    public String toString() {
        return this.className;
    }
    
    public boolean isApplied() {
        return this.applied;
    }
    
    public boolean isExportForced() {
        return this.forceExport;
    }
    
    public String getSessionId() {
        return this.sessionId;
    }
    
    public String getName() {
        return this.classNode.name;
    }
    
    public String getClassName() {
        return this.className;
    }
    
    public ClassNode getClassNode() {
        return this.classNode;
    }
    
    public List<MethodNode> getMethods() {
        return (List<MethodNode>)this.classNode.methods;
    }
    
    public List<FieldNode> getFields() {
        return (List<FieldNode>)this.classNode.fields;
    }
    
    public ClassInfo getClassInfo() {
        return this.classInfo;
    }
    
    public SortedSet<MixinInfo> getMixins() {
        return this.mixins;
    }
    
    public SourceMap getSourceMap() {
        return this.sourceMap;
    }
    
    MethodNode findAliasedMethod(final Deque<String> aliases, final String desc) {
        final String alias = aliases.poll();
        if (alias == null) {
            return null;
        }
        for (final MethodNode target : this.classNode.methods) {
            if (target.name.equals(alias) && target.desc.equals(desc)) {
                return target;
            }
        }
        return this.findAliasedMethod(aliases, desc);
    }
    
    FieldNode findAliasedField(final Deque<String> aliases, final String desc) {
        final String alias = aliases.poll();
        if (alias == null) {
            return null;
        }
        for (final FieldNode target : this.classNode.fields) {
            if (target.name.equals(alias) && target.desc.equals(desc)) {
                return target;
            }
        }
        return this.findAliasedField(aliases, desc);
    }
    
    public Target getTargetMethod(final MethodNode method) {
        if (!this.classNode.methods.contains(method)) {
            throw new IllegalArgumentException("Invalid target method supplied to getTargetMethod()");
        }
        final String targetName = method.name + method.desc;
        Target target = this.targetMethods.get(targetName);
        if (target == null) {
            target = new Target(this.classNode, method);
            this.targetMethods.put(targetName, target);
        }
        return target;
    }
    
    public String getUniqueName(final MethodNode method) {
        final String uniqueIndex = Integer.toHexString(this.nextUniqueMethodIndex++);
        return String.format("md%s$%s$%s", this.sessionId.substring(30), method.name, uniqueIndex);
    }
    
    public String getUniqueName(final FieldNode field) {
        final String uniqueIndex = Integer.toHexString(this.nextUniqueFieldIndex++);
        return String.format("fd%s$%s$%s", this.sessionId.substring(30), field.name, uniqueIndex);
    }
    
    public void applyMixins() {
        if (this.applied) {
            throw new IllegalStateException("Mixins already applied to target class " + this.className);
        }
        this.applied = true;
        final MixinApplicatorStandard applicator = this.createApplicator();
        applicator.apply((SortedSet)this.mixins);
    }
    
    private MixinApplicatorStandard createApplicator() {
        if (this.classInfo.isInterface()) {
            return (MixinApplicatorStandard)new MixinApplicatorInterface(this);
        }
        return new MixinApplicatorStandard(this);
    }
    
    public void processDebugTasks() {
        if (!MixinEnvironment.getCurrentEnvironment().getOption(MixinEnvironment.Option.DEBUG_VERBOSE)) {
            return;
        }
        final AnnotationNode classDebugAnnotation = ASMHelper.getVisibleAnnotation(this.classNode, (Class<? extends Annotation>)Debug.class);
        if (classDebugAnnotation != null) {
            this.forceExport = Boolean.TRUE.equals(ASMHelper.getAnnotationValue(classDebugAnnotation, "export"));
            if (Boolean.TRUE.equals(ASMHelper.getAnnotationValue(classDebugAnnotation, "print"))) {
                ASMHelper.textify(this.classNode, System.err);
            }
        }
        for (final MethodNode method : this.classNode.methods) {
            final AnnotationNode methodDebugAnnotation = ASMHelper.getVisibleAnnotation(method, (Class<? extends Annotation>)Debug.class);
            if (methodDebugAnnotation != null && Boolean.TRUE.equals(ASMHelper.getAnnotationValue(methodDebugAnnotation, "print"))) {
                ASMHelper.textify(method, System.err);
            }
        }
    }
}
