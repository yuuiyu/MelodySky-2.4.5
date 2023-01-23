//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import org.objectweb.asm.tree.*;
import java.util.*;
import org.objectweb.asm.*;

public class BlockPosPatch
{
    private static final HashMap<String, String> mutableFieldSwaps;
    private static final HashSet<String> mutableDeletedMethods;
    private static final HashSet<String> mutableOwners;
    
    public static byte[] patchVec3i(final byte[] data) {
        final ClassReader reader = new ClassReader(data);
        final ClassNode node = new ClassNode();
        reader.accept((ClassVisitor)node, 0);
        for (final FieldNode fn : node.fields) {
            if ("I".equals(fn.desc) && fn.access == 18) {
                fn.access = 4;
            }
        }
        final ClassWriter writer = new ClassWriter(0);
        node.accept((ClassVisitor)writer);
        return writer.toByteArray();
    }
    
    public static byte[] patchOtherClass(final byte[] data, final boolean isMutable) {
        final ClassReader reader = new ClassReader(data);
        final BlockPosClassVisitor cv = new BlockPosClassVisitor(327680, null, isMutable);
        reader.accept((ClassVisitor)cv, 6);
        if (cv.hasChanged) {
            final ClassWriter writer = new ClassWriter(0);
            cv.setCV((ClassVisitor)writer);
            reader.accept((ClassVisitor)cv, 0);
            return writer.toByteArray();
        }
        return data;
    }
    
    static {
        mutableFieldSwaps = new HashMap<String, String>();
        mutableDeletedMethods = new HashSet<String>();
        mutableOwners = new HashSet<String>();
        BlockPosPatch.mutableFieldSwaps.put("x", "x");
        BlockPosPatch.mutableFieldSwaps.put("y", "y");
        BlockPosPatch.mutableFieldSwaps.put("z", "z");
        BlockPosPatch.mutableFieldSwaps.put("x", "x");
        BlockPosPatch.mutableFieldSwaps.put("y", "y");
        BlockPosPatch.mutableFieldSwaps.put("z", "z");
        BlockPosPatch.mutableDeletedMethods.add("getX");
        BlockPosPatch.mutableDeletedMethods.add("getY");
        BlockPosPatch.mutableDeletedMethods.add("getZ");
        BlockPosPatch.mutableDeletedMethods.add("getX");
        BlockPosPatch.mutableDeletedMethods.add("getY");
        BlockPosPatch.mutableDeletedMethods.add("getZ");
        BlockPosPatch.mutableOwners.add("net/minecraft/util/math/BlockPos$MutableBlockPos");
    }
    
    private static class BlockPosClassVisitor extends ClassVisitor
    {
        private final boolean isMutable;
        private boolean hasChanged;
        
        public BlockPosClassVisitor(final int api, final ClassVisitor next, final boolean isMutable) {
            super(api, next);
            this.hasChanged = false;
            this.isMutable = isMutable;
        }
        
        public void setCV(final ClassVisitor visitor) {
            this.cv = visitor;
        }
        
        public void visit(final int version, final int access, final String name, final String signature, final String superName, final String[] interfaces) {
            if (BlockPosPatch.mutableOwners.contains(superName)) {
                BlockPosPatch.mutableOwners.add(name);
            }
            if (this.cv != null) {
                this.cv.visit(version, access, name, signature, superName, interfaces);
            }
        }
        
        public FieldVisitor visitField(final int access, final String name, final String desc, final String signature, final Object value) {
            if (this.cv == null) {
                return null;
            }
            if (!this.isMutable || !BlockPosPatch.mutableFieldSwaps.containsKey(name)) {
                return this.cv.visitField(access, name, desc, signature, value);
            }
            return null;
        }
        
        public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            if (!this.isMutable || !BlockPosPatch.mutableDeletedMethods.contains(name)) {
                return new BlockPosMethodVisitor(this.api, this, (this.cv != null) ? this.cv.visitMethod(access, name, desc, signature, exceptions) : null);
            }
            return null;
        }
    }
    
    private static class BlockPosMethodVisitor extends MethodVisitor
    {
        private final BlockPosClassVisitor classVisitor;
        
        public BlockPosMethodVisitor(final int api, final BlockPosClassVisitor cv, final MethodVisitor mv) {
            super(api, mv);
            this.classVisitor = cv;
        }
        
        public void visitFieldInsn(final int opcode, final String owner, final String name, final String desc) {
            if (BlockPosPatch.mutableOwners.contains(owner)) {
                final String dst = BlockPosPatch.mutableFieldSwaps.get(name);
                if (dst != null) {
                    if (this.mv != null) {
                        this.mv.visitFieldInsn(opcode, "net/minecraft/util/math/Vec3i", dst, desc);
                    }
                    this.classVisitor.hasChanged = true;
                }
                else if (this.mv != null) {
                    this.mv.visitFieldInsn(opcode, owner, name, desc);
                }
            }
            else if (this.mv != null) {
                this.mv.visitFieldInsn(opcode, owner, name, desc);
            }
        }
    }
}
