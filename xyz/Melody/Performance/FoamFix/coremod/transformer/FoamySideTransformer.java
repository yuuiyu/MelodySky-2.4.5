//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod.transformer;

import net.minecraft.launchwrapper.*;
import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import org.objectweb.asm.*;

public class FoamySideTransformer implements IClassTransformer
{
    public static final String SIDEONLY_DESCRIPTOR;
    private static String SIDE;
    private static final boolean DEBUG = false;
    
    public byte[] transform(final String name, final String transformedName, final byte[] bytes) {
        if (bytes == null) {
            return null;
        }
        final ClassReader classReader = new ClassReader(bytes);
        final SideCapturingClassVisitor sideCapturingClassVisitor = new SideCapturingClassVisitor(327680);
        classReader.accept((ClassVisitor)sideCapturingClassVisitor, 7);
        if (sideCapturingClassVisitor.removableClasses.size() > 0) {
            throw new RuntimeException(String.format("Attempted to load class %s for invalid side %s", sideCapturingClassVisitor.className, FoamySideTransformer.SIDE));
        }
        if (sideCapturingClassVisitor.removableFields.size() > 0 || sideCapturingClassVisitor.removableMethods.size() > 0) {
            final ClassWriter writer = new ClassWriter(0);
            classReader.accept((ClassVisitor)new SideRemovingClassVisitor(327680, (ClassVisitor)writer, sideCapturingClassVisitor), 0);
            return writer.toByteArray();
        }
        return bytes;
    }
    
    static {
        SIDEONLY_DESCRIPTOR = Type.getDescriptor((Class)SideOnly.class);
        FoamySideTransformer.SIDE = FMLLaunchHandler.side().name();
    }
    
    public static class SideCapturingAnnotationVisitor extends AnnotationVisitor
    {
        public final Collection<String> targetSet;
        public final String targetName;
        
        public SideCapturingAnnotationVisitor(final int api, final Collection<String> targetSet, final String targetName) {
            super(api);
            this.targetSet = targetSet;
            this.targetName = targetName;
        }
        
        public void visitEnum(final String name, final String desc, final String value) {
            if (name.equals("value") && !value.equals(FoamySideTransformer.SIDE)) {
                this.targetSet.add(this.targetName);
            }
        }
    }
    
    public static class SideCapturingClassVisitor extends ClassVisitor
    {
        public List<String> removableClasses;
        public Set<String> removableFields;
        public Set<String> removableMethods;
        public String className;
        
        public SideCapturingClassVisitor(final int api) {
            super(api);
            this.removableClasses = new ArrayList<String>(1);
            this.removableFields = new HashSet<String>();
            this.removableMethods = new HashSet<String>();
        }
        
        public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
            this.className = name;
        }
        
        public AnnotationVisitor visitAnnotation(final String desc, final boolean visible) {
            if (desc.equals(FoamySideTransformer.SIDEONLY_DESCRIPTOR)) {
                return new SideCapturingAnnotationVisitor(this.api, this.removableClasses, this.className);
            }
            return null;
        }
        
        public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: invokespecial   java/lang/StringBuilder.<init>:()V
            //     7: aload_2         /* name */
            //     8: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    11: aload_3         /* desc */
            //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    15: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    18: astore          name1
            //    20: new             Lxyz/Melody/Performance/FoamFix/coremod/transformer/lI;
            //    23: dup            
            //    24: aload_0         /* this */
            //    25: aload_0         /* this */
            //    26: getfield        xyz/Melody/Performance/FoamFix/coremod/transformer/FoamySideTransformer$SideCapturingClassVisitor.api:I
            //    29: aload_0         /* this */
            //    30: iload_1         /* access */
            //    31: aload_2         /* name */
            //    32: aload_3         /* desc */
            //    33: aload           signature
            //    35: aload           value
            //    37: invokespecial   org/objectweb/asm/ClassVisitor.visitField:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)Lorg/objectweb/asm/FieldVisitor;
            //    40: aload           name1
            //    42: invokespecial   xyz/Melody/Performance/FoamFix/coremod/transformer/lI.<init>:(Lxyz/Melody/Performance/FoamFix/coremod/transformer/FoamySideTransformer$SideCapturingClassVisitor;ILorg/objectweb/asm/FieldVisitor;Ljava/lang/String;)V
            //    45: areturn        
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
        
        public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: invokespecial   java/lang/StringBuilder.<init>:()V
            //     7: aload_2         /* name */
            //     8: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    11: aload_3         /* desc */
            //    12: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    15: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    18: astore          name1
            //    20: new             Lxyz/Melody/Performance/FoamFix/coremod/transformer/ll;
            //    23: dup            
            //    24: aload_0         /* this */
            //    25: aload_0         /* this */
            //    26: getfield        xyz/Melody/Performance/FoamFix/coremod/transformer/FoamySideTransformer$SideCapturingClassVisitor.api:I
            //    29: aload_0         /* this */
            //    30: iload_1         /* access */
            //    31: aload_2         /* name */
            //    32: aload_3         /* desc */
            //    33: aload           signature
            //    35: aload           exceptions
            //    37: invokespecial   org/objectweb/asm/ClassVisitor.visitMethod:(ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;)Lorg/objectweb/asm/MethodVisitor;
            //    40: aload           name1
            //    42: invokespecial   xyz/Melody/Performance/FoamFix/coremod/transformer/ll.<init>:(Lxyz/Melody/Performance/FoamFix/coremod/transformer/FoamySideTransformer$SideCapturingClassVisitor;ILorg/objectweb/asm/MethodVisitor;Ljava/lang/String;)V
            //    45: areturn        
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
    
    public static class SideRemovingClassVisitor extends ClassVisitor
    {
        private final SideCapturingClassVisitor capturer;
        
        public SideRemovingClassVisitor(final int api, final ClassVisitor cv, final SideCapturingClassVisitor capturer) {
            super(api, cv);
            this.capturer = capturer;
        }
        
        public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
            final String name2 = name + desc;
            if (this.capturer.removableFields.contains(name2)) {
                return null;
            }
            return super.visitField(access, name, desc, signature, value);
        }
        
        public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            final String name2 = name + desc;
            if (this.capturer.removableMethods.contains(name2)) {
                return null;
            }
            return super.visitMethod(access, name, desc, signature, exceptions);
        }
    }
}
