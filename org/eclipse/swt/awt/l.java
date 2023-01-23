//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.awt;

import org.eclipse.swt.widgets.*;
import java.awt.event.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.internal.*;
import java.awt.*;

final class l extends ComponentAdapter
{
    final /* synthetic */ Display val$display;
    final /* synthetic */ Shell val$shell;
    final /* synthetic */ Canvas val$parent;
    
    l(final Display val$display, final Shell val$shell, final Canvas val$parent) {
        this.val$display = val$display;
        this.val$shell = val$shell;
        this.val$parent = val$parent;
    }
    
    @Override
    public void componentResized(final ComponentEvent e) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        org/eclipse/swt/awt/l.val$display:Lorg/eclipse/swt/widgets/Display;
        //     4: aload_0         /* this */
        //     5: getfield        org/eclipse/swt/awt/l.val$shell:Lorg/eclipse/swt/widgets/Shell;
        //     8: aload_0         /* this */
        //     9: getfield        org/eclipse/swt/awt/l.val$parent:Ljava/awt/Canvas;
        //    12: invokedynamic   BootstrapMethod #0, run:(Lorg/eclipse/swt/widgets/Shell;Ljava/awt/Canvas;)Ljava/lang/Runnable;
        //    17: invokevirtual   org/eclipse/swt/widgets/Display.syncExec:(Ljava/lang/Runnable;)V
        //    20: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.IllegalStateException: Could not infer any expression.
        //     at com.strobel.decompiler.ast.TypeAnalysis.runInference(TypeAnalysis.java:374)
        //     at com.strobel.decompiler.ast.TypeAnalysis.run(TypeAnalysis.java:96)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:344)
        //     at com.strobel.decompiler.ast.AstOptimizer.optimize(AstOptimizer.java:42)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:214)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
