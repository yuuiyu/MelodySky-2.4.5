//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import java.io.*;
import org.spongepowered.asm.lib.util.*;
import java.lang.reflect.*;
import java.lang.annotation.*;
import org.spongepowered.asm.lib.*;
import com.google.common.base.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import com.google.common.primitives.*;

public class ASMHelper
{
    public static final int[] CONSTANTS_INT;
    public static final int[] CONSTANTS_FLOAT;
    public static final int[] CONSTANTS_DOUBLE;
    public static final int[] CONSTANTS_LONG;
    public static final int[] CONSTANTS_ALL;
    private static final Object[] CONSTANTS_VALUES;
    private static final String[] CONSTANTS_TYPES;
    
    public static MethodNode findMethod(final ClassNode classNode, final String name, final String desc) {
        for (final MethodNode method : classNode.methods) {
            if (method.name.equals(name) && method.desc.equals(desc)) {
                return method;
            }
        }
        return null;
    }
    
    public static void textify(final ClassNode classNode, final OutputStream out) {
        classNode.accept((ClassVisitor)new TraceClassVisitor(new PrintWriter(out)));
    }
    
    public static void textify(final MethodNode methodNode, final OutputStream out) {
        final TraceClassVisitor trace = new TraceClassVisitor(new PrintWriter(out));
        final MethodVisitor mv = trace.visitMethod(methodNode.access, methodNode.name, methodNode.desc, methodNode.signature, (String[])methodNode.exceptions.toArray(new String[0]));
        methodNode.accept(mv);
        trace.visitEnd();
    }
    
    public static void dumpClass(final ClassNode classNode) {
        final ClassWriter cw = new ClassWriter(3);
        classNode.accept((ClassVisitor)cw);
        dumpClass(cw.toByteArray());
    }
    
    public static void dumpClass(final byte[] bytes) {
        final ClassReader cr = new ClassReader(bytes);
        CheckClassAdapter.verify(cr, true, new PrintWriter(System.out));
    }
    
    public static void printMethodWithOpcodeIndices(final MethodNode method) {
        System.err.printf("%s%s\n", method.name, method.desc);
        int i = 0;
        final Iterator<AbstractInsnNode> iter = (Iterator<AbstractInsnNode>)method.instructions.iterator();
        while (iter.hasNext()) {
            System.err.printf("[%4d] %s\n", i++, getNodeDescriptionForDebug(iter.next()));
        }
    }
    
    public static void printMethod(final MethodNode method) {
        System.err.printf("%s%s\n", method.name, method.desc);
        final Iterator<AbstractInsnNode> iter = (Iterator<AbstractInsnNode>)method.instructions.iterator();
        while (iter.hasNext()) {
            System.err.print("  ");
            printNode(iter.next());
        }
    }
    
    public static void printNode(final AbstractInsnNode node) {
        System.err.printf("%s\n", getNodeDescriptionForDebug(node));
    }
    
    public static String getNodeDescriptionForDebug(final AbstractInsnNode node) {
        if (node instanceof LabelNode) {
            return String.format("[%s]", ((LabelNode)node).getLabel());
        }
        String out = String.format("   %-14s ", node.getClass().getSimpleName().replace("Node", ""));
        if (node instanceof JumpInsnNode) {
            out += String.format("[%s] [%s]", getOpcodeName(node), ((JumpInsnNode)node).label.getLabel());
        }
        else if (node instanceof VarInsnNode) {
            out += String.format("[%s] %d", getOpcodeName(node), ((VarInsnNode)node).var);
        }
        else if (node instanceof MethodInsnNode) {
            final MethodInsnNode mth = (MethodInsnNode)node;
            out += String.format("[%s] %s %s %s", getOpcodeName(node), mth.owner, mth.name, mth.desc);
        }
        else if (node instanceof FieldInsnNode) {
            final FieldInsnNode fld = (FieldInsnNode)node;
            out += String.format("[%s] %s %s %s", getOpcodeName(node), fld.owner, fld.name, fld.desc);
        }
        else if (node instanceof LineNumberNode) {
            final LineNumberNode ln = (LineNumberNode)node;
            out += String.format("LINE=[%d] LABEL=[%s]", ln.line, ln.start.getLabel());
        }
        else if (node instanceof LdcInsnNode) {
            out += ((LdcInsnNode)node).cst;
        }
        else if (node instanceof IntInsnNode) {
            out += ((IntInsnNode)node).operand;
        }
        else if (node instanceof FrameNode) {
            out += String.format("[%s] ", getOpcodeName(((FrameNode)node).type, "H_INVOKEINTERFACE", -1));
        }
        else {
            out += String.format("[%s] ", getOpcodeName(node));
        }
        return out;
    }
    
    public static String getOpcodeName(final AbstractInsnNode node) {
        return getOpcodeName(node.getOpcode());
    }
    
    public static String getOpcodeName(final int opcode) {
        return getOpcodeName(opcode, "UNINITIALIZED_THIS", 1);
    }
    
    private static String getOpcodeName(final int opcode, final String start, final int min) {
        if (opcode >= min) {
            boolean found = false;
            try {
                for (final Field f : Opcodes.class.getDeclaredFields()) {
                    if (found || f.getName().equals(start)) {
                        found = true;
                        if (f.getType() == Integer.TYPE && f.getInt(null) == opcode) {
                            return f.getName();
                        }
                    }
                }
            }
            catch (Exception ex) {}
        }
        return (opcode >= 0) ? String.valueOf(opcode) : "UNKNOWN";
    }
    
    public static void setVisibleAnnotation(final FieldNode field, final Class<? extends Annotation> annotationClass, final Object... value) {
        final AnnotationNode node = makeAnnotationNode(Type.getDescriptor((Class)annotationClass), value);
        field.visibleAnnotations = addAnnotation(field.visibleAnnotations, node);
    }
    
    public static void setInvisibleAnnotation(final FieldNode field, final Class<? extends Annotation> annotationClass, final Object... value) {
        final AnnotationNode node = makeAnnotationNode(Type.getDescriptor((Class)annotationClass), value);
        field.invisibleAnnotations = addAnnotation(field.invisibleAnnotations, node);
    }
    
    public static void setVisibleAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass, final Object... value) {
        final AnnotationNode node = makeAnnotationNode(Type.getDescriptor((Class)annotationClass), value);
        method.visibleAnnotations = addAnnotation(method.visibleAnnotations, node);
    }
    
    public static void setInvisibleAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass, final Object... value) {
        final AnnotationNode node = makeAnnotationNode(Type.getDescriptor((Class)annotationClass), value);
        method.invisibleAnnotations = addAnnotation(method.invisibleAnnotations, node);
    }
    
    private static AnnotationNode makeAnnotationNode(final String annotationType, final Object... value) {
        final AnnotationNode node = new AnnotationNode(annotationType);
        for (int pos = 0; pos < value.length - 1; pos += 2) {
            if (!(value[pos] instanceof String)) {
                throw new IllegalArgumentException("Annotation keys must be strings, found " + value[pos].getClass().getSimpleName() + " with " + value[pos].toString() + " at index " + pos + " creating " + annotationType);
            }
            node.visit((String)value[pos], value[pos + 1]);
        }
        return node;
    }
    
    private static List<AnnotationNode> addAnnotation(List<AnnotationNode> annotations, final AnnotationNode node) {
        if (annotations == null) {
            annotations = new ArrayList<AnnotationNode>(1);
        }
        else {
            annotations.remove(getAnnotation(annotations, node.desc));
        }
        annotations.add(node);
        return annotations;
    }
    
    public static AnnotationNode getVisibleAnnotation(final FieldNode field, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(field.visibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getInvisibleAnnotation(final FieldNode field, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(field.invisibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getVisibleAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(method.visibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getInvisibleAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(method.invisibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getSingleVisibleAnnotation(final MethodNode method, final Class<? extends Annotation>... annotationClasses) {
        return getSingleAnnotation(method.visibleAnnotations, annotationClasses);
    }
    
    public static AnnotationNode getSingleInvisibleAnnotation(final MethodNode method, final Class<? extends Annotation>... annotationClasses) {
        return getSingleAnnotation(method.invisibleAnnotations, annotationClasses);
    }
    
    public static AnnotationNode getVisibleAnnotation(final ClassNode classNode, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(classNode.visibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getInvisibleAnnotation(final ClassNode classNode, final Class<? extends Annotation> annotationClass) {
        return getAnnotation(classNode.invisibleAnnotations, Type.getDescriptor((Class)annotationClass));
    }
    
    public static AnnotationNode getVisibleParameterAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass, final int paramIndex) {
        return getParameterAnnotation(method.visibleParameterAnnotations, Type.getDescriptor((Class)annotationClass), paramIndex);
    }
    
    public static AnnotationNode getInvisibleParameterAnnotation(final MethodNode method, final Class<? extends Annotation> annotationClass, final int paramIndex) {
        return getParameterAnnotation(method.invisibleParameterAnnotations, Type.getDescriptor((Class)annotationClass), paramIndex);
    }
    
    public static AnnotationNode getParameterAnnotation(final List<AnnotationNode>[] parameterAnnotations, final String annotationType, final int paramIndex) {
        if (parameterAnnotations == null || paramIndex < 0 || paramIndex >= parameterAnnotations.length) {
            return null;
        }
        return getAnnotation(parameterAnnotations[paramIndex], annotationType);
    }
    
    public static AnnotationNode getAnnotation(final List<AnnotationNode> annotations, final String annotationType) {
        if (annotations == null) {
            return null;
        }
        for (final AnnotationNode annotation : annotations) {
            if (annotationType.equals(annotation.desc)) {
                return annotation;
            }
        }
        return null;
    }
    
    private static AnnotationNode getSingleAnnotation(final List<AnnotationNode> annotations, final Class<? extends Annotation>[] annotationClasses) {
        final List<AnnotationNode> nodes = new ArrayList<AnnotationNode>();
        for (final Class<? extends Annotation> annotationClass : annotationClasses) {
            final AnnotationNode annotation = getAnnotation(annotations, Type.getDescriptor((Class)annotationClass));
            if (annotation != null) {
                nodes.add(annotation);
            }
        }
        final int foundNodes = nodes.size();
        if (foundNodes > 1) {
            throw new IllegalArgumentException("Conflicting annotations found: " + annotationClasses);
        }
        return (foundNodes == 0) ? null : nodes.get(0);
    }
    
    public static <T> T getAnnotationValue(final AnnotationNode annotation) {
        return getAnnotationValue(annotation, "value");
    }
    
    public static <T> T getAnnotationValue(final AnnotationNode annotation, final String key, final T defaultValue) {
        final T returnValue = getAnnotationValue(annotation, key);
        return (returnValue != null) ? returnValue : defaultValue;
    }
    
    public static <T> T getAnnotationValue(final AnnotationNode annotation, final String key, final Class<?> annotationClass) {
        Preconditions.checkNotNull((Object)annotationClass, (Object)"annotationClass cannot be null");
        T value = getAnnotationValue(annotation, key);
        if (value == null) {
            try {
                value = (T)annotationClass.getDeclaredMethod(key, (Class<?>[])new Class[0]).getDefaultValue();
            }
            catch (NoSuchMethodException ex) {}
        }
        return value;
    }
    
    public static <T> T getAnnotationValue(final AnnotationNode annotation, final String key) {
        boolean getNextValue = false;
        if (annotation == null || annotation.values == null) {
            return null;
        }
        for (final Object value : annotation.values) {
            if (getNextValue) {
                return (T)value;
            }
            if (!value.equals(key)) {
                continue;
            }
            getNextValue = true;
        }
        return null;
    }
    
    public static <T extends Enum<T>> T getAnnotationValue(final AnnotationNode annotationNode, final String key, final Class<T> enumClass, final T defaultValue) {
        final String[] value = getAnnotationValue(annotationNode, key);
        if (value == null) {
            return defaultValue;
        }
        if (!enumClass.getName().equals(Type.getType(value[0]).getClassName())) {
            throw new IllegalArgumentException("The supplied enum class does not match the stored enum value");
        }
        return Enum.valueOf(enumClass, value[1]);
    }
    
    public static boolean methodIsStatic(final MethodNode method) {
        return (method.access & 0x8) == 0x8;
    }
    
    public static boolean fieldIsStatic(final FieldNode field) {
        return (field.access & 0x8) == 0x8;
    }
    
    public static int getFirstNonArgLocalIndex(final MethodNode method) {
        return getFirstNonArgLocalIndex(Type.getArgumentTypes(method.desc), (method.access & 0x8) == 0x0);
    }
    
    public static int getFirstNonArgLocalIndex(final Type[] args, final boolean includeThis) {
        return getArgsSize(args) + (includeThis ? 1 : 0);
    }
    
    public static int getArgsSize(final Type[] args) {
        int size = 0;
        for (final Type type : args) {
            size += type.getSize();
        }
        return size;
    }
    
    public static void loadArgs(final Type[] args, final InsnList insns, final int pos) {
        loadArgs(args, insns, pos, -1);
    }
    
    public static void loadArgs(final Type[] args, final InsnList insns, final int start, final int end) {
        int pos = start;
        for (final Type type : args) {
            insns.add((AbstractInsnNode)new VarInsnNode(type.getOpcode(21), pos));
            pos += type.getSize();
            if (end >= start && pos >= end) {
                return;
            }
        }
    }
    
    public static Map<LabelNode, LabelNode> cloneLabels(final InsnList source) {
        final Map<LabelNode, LabelNode> labels = new HashMap<LabelNode, LabelNode>();
        for (final AbstractInsnNode insn : source) {
            if (insn instanceof LabelNode) {
                labels.put((LabelNode)insn, new LabelNode(((LabelNode)insn).getLabel()));
            }
        }
        return labels;
    }
    
    public static String generateDescriptor(final Object returnType, final Object... args) {
        final StringBuilder sb = new StringBuilder().append('(');
        for (final Object arg : args) {
            sb.append(toDescriptor(arg));
        }
        return sb.append(')').append((returnType != null) ? toDescriptor(returnType) : "V").toString();
    }
    
    private static String toDescriptor(final Object arg) {
        if (arg instanceof String) {
            return (String)arg;
        }
        if (arg instanceof Type) {
            return arg.toString();
        }
        if (arg instanceof Class) {
            return Type.getDescriptor((Class)arg).toString();
        }
        return (arg == null) ? "" : arg.toString();
    }
    
    public static String getSimpleName(final Class<? extends Annotation> annotationType) {
        return annotationType.getSimpleName();
    }
    
    public static String getSimpleName(final AnnotationNode annotation) {
        return getSimpleName(annotation.desc);
    }
    
    public static String getSimpleName(final String desc) {
        return desc.substring(desc.lastIndexOf(47) + 1).replace(";", "");
    }
    
    public static boolean isConstant(final AbstractInsnNode insn) {
        return insn != null && Ints.contains(ASMHelper.CONSTANTS_ALL, insn.getOpcode());
    }
    
    public static Object getConstant(final AbstractInsnNode insn) {
        if (insn == null) {
            return null;
        }
        if (insn instanceof LdcInsnNode) {
            return ((LdcInsnNode)insn).cst;
        }
        if (!(insn instanceof IntInsnNode)) {
            final int index = Ints.indexOf(ASMHelper.CONSTANTS_ALL, insn.getOpcode());
            return (index < 0) ? null : ASMHelper.CONSTANTS_VALUES[index];
        }
        final int value = ((IntInsnNode)insn).operand;
        if (insn.getOpcode() == 16 || insn.getOpcode() == 17) {
            return value;
        }
        throw new IllegalArgumentException("IntInsnNode with invalid opcode " + insn.getOpcode() + " in getConstant");
    }
    
    public static Type getConstantType(final AbstractInsnNode insn) {
        if (insn == null) {
            return null;
        }
        if (!(insn instanceof LdcInsnNode)) {
            final int index = Ints.indexOf(ASMHelper.CONSTANTS_ALL, insn.getOpcode());
            return (index < 0) ? null : Type.getType(ASMHelper.CONSTANTS_TYPES[index]);
        }
        final Object cst = ((LdcInsnNode)insn).cst;
        if (cst instanceof Integer) {
            return Type.getType("I");
        }
        if (cst instanceof Float) {
            return Type.getType("F");
        }
        if (cst instanceof Long) {
            return Type.getType("J");
        }
        if (cst instanceof Double) {
            return Type.getType("D");
        }
        if (cst instanceof String) {
            return Type.getType("Ljava/lang/String;");
        }
        if (cst instanceof Type) {
            return Type.getType("Ljava/lang/Class;");
        }
        throw new IllegalArgumentException("LdcInsnNode with invalid payload type " + cst.getClass() + " in getConstant");
    }
    
    public static boolean hasFlag(final ClassNode classNode, final int flag) {
        return (classNode.access & flag) == flag;
    }
    
    public static boolean hasFlag(final MethodNode method, final int flag) {
        return (method.access & flag) == flag;
    }
    
    public static boolean hasFlag(final FieldNode field, final int flag) {
        return (field.access & flag) == flag;
    }
    
    public static int getMaxLineNumber(final ClassNode classNode, final int min, final int pad) {
        int max = 0;
        for (final MethodNode method : classNode.methods) {
            for (final AbstractInsnNode insn : method.instructions) {
                if (insn instanceof LineNumberNode) {
                    max = Math.max(max, ((LineNumberNode)insn).line);
                }
            }
        }
        return Math.max(min, max + pad);
    }
    
    static {
        CONSTANTS_INT = new int[] { 2, 3, 4, 5, 6, 7, 8 };
        CONSTANTS_FLOAT = new int[] { 11, 12, 13 };
        CONSTANTS_DOUBLE = new int[] { 14, 15 };
        CONSTANTS_LONG = new int[] { 9, 10 };
        CONSTANTS_ALL = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18 };
        CONSTANTS_VALUES = new Object[] { null, -1, 0, 1, 2, 3, 4, 5, 0L, 1L, 0.0f, 1.0f, 2.0f, 0.0, 1.0 };
        CONSTANTS_TYPES = new String[] { null, "I", "I", "I", "I", "I", "I", "I", "J", "J", "F", "F", "F", "D", "D", "I", "I" };
    }
}
