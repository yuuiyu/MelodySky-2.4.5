//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl;

import java.util.concurrent.atomic.*;

class llIll extends LocalMessage
{
    final /* synthetic */ WebBrowserObject this$0;
    
    llIll(final WebBrowserObject this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Object run(final Object[] args) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_0       
        //     2: aaload         
        //     3: checkcast       Lchrriis/dj/nativeswing/swtimpl/WebBrowserObject$InitializationListener;
        //     6: astore_2        /* initializationListener */
        //     7: aload_1         /* args */
        //     8: iconst_1       
        //     9: aaload         
        //    10: checkcast       Ljava/util/concurrent/atomic/AtomicBoolean;
        //    13: astore_3        /* result */
        //    14: new             Lchrriis/dj/nativeswing/swtimpl/lIllI;
        //    17: dup            
        //    18: aload_0         /* this */
        //    19: aload_3         /* result */
        //    20: invokespecial   chrriis/dj/nativeswing/swtimpl/lIllI.<init>:(Lchrriis/dj/nativeswing/swtimpl/llIll;Ljava/util/concurrent/atomic/AtomicBoolean;)V
        //    23: sipush          4000
        //    26: invokestatic    chrriis/dj/nativeswing/swtimpl/EventDispatchUtils.sleepWithEventDispatch:(Lchrriis/dj/nativeswing/swtimpl/EventDispatchUtils$Condition;I)V
        //    29: aload_0         /* this */
        //    30: getfield        chrriis/dj/nativeswing/swtimpl/llIll.this$0:Lchrriis/dj/nativeswing/swtimpl/WebBrowserObject;
        //    33: aload_2         /* initializationListener */
        //    34: invokestatic    chrriis/dj/nativeswing/swtimpl/WebBrowserObject.access$100:(Lchrriis/dj/nativeswing/swtimpl/WebBrowserObject;Lchrriis/dj/nativeswing/swtimpl/WebBrowserObject$InitializationListener;)V
        //    37: aconst_null    
        //    38: areturn        
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
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
