//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import java.io.*;
import org.spongepowered.asm.lib.tree.analysis.*;

class l extends MethodNode
{
    final /* synthetic */ MethodVisitor val$cmv;
    
    l(final int api, final int access, final String name, final String desc, final String signature, final String[] exceptions, final MethodVisitor val$cmv) {
        this.val$cmv = val$cmv;
        super(api, access, name, desc, signature, exceptions);
    }
    
    public void visitEnd() {
        final Analyzer<BasicValue> a = (Analyzer<BasicValue>)new Analyzer((Interpreter)new BasicVerifier());
        try {
            a.analyze("dummy", (MethodNode)this);
        }
        catch (Exception e) {
            if (e instanceof IndexOutOfBoundsException && this.maxLocals == 0 && this.maxStack == 0) {
                throw new RuntimeException("Data flow checking option requires valid, non zero maxLocals and maxStack values.");
            }
            e.printStackTrace();
            final StringWriter sw = new StringWriter();
            final PrintWriter pw = new PrintWriter(sw, true);
            CheckClassAdapter.printAnalyzerResult((MethodNode)this, (Analyzer)a, pw);
            pw.close();
            throw new RuntimeException(e.getMessage() + ' ' + sw.toString());
        }
        this.accept(this.val$cmv);
    }
}
