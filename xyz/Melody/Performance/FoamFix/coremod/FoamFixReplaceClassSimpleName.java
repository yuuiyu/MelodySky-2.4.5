//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import java.util.*;
import com.google.common.collect.*;
import org.objectweb.asm.*;

public class FoamFixReplaceClassSimpleName
{
    public final Set<String> methods;
    
    public FoamFixReplaceClassSimpleName(final String... methods) {
        this.methods = (Set<String>)ImmutableSet.copyOf((Object[])methods);
    }
    
    public ClassVisitor getClassVisitor(final int api, final ClassVisitor next) {
        return new FFClassVisitor(api, next);
    }
    
    private class FFClassVisitor extends ClassVisitor
    {
        public FFClassVisitor(final int api, final ClassVisitor next) {
            super(api, next);
        }
        
        public MethodVisitor visitMethod(final int access, final String name, final String desc, final String signature, final String[] exceptions) {
            if (FoamFixReplaceClassSimpleName.this.methods.contains(name)) {
                return new FFMethodVisitor(this.api, this.cv.visitMethod(access, name, desc, signature, exceptions));
            }
            return this.cv.visitMethod(access, name, desc, signature, exceptions);
        }
    }
    
    private class FFMethodVisitor extends MethodVisitor
    {
        public FFMethodVisitor(final int api, final MethodVisitor next) {
            super(api, next);
        }
        
        public void visitMethodInsn(final int opcode, final String owner, final String name, final String desc, final boolean itf) {
            if (opcode == 182 && "getSimpleName".equals(name) && "java/lang/Class".equals(owner)) {
                System.out.println("Replaced INVOKEVIRTUAL getSimpleName");
                super.visitMethodInsn(opcode, owner, "getName", desc, itf);
            }
            else {
                super.visitMethodInsn(opcode, owner, name, desc, itf);
            }
        }
    }
}
