//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;

class lIIIII implements AuthenticationListener
{
    final /* synthetic */ Browser val$browser;
    final /* synthetic */ NativeWebBrowser.CMN_setAuthenticationHandler this$0;
    
    lIIIII(final NativeWebBrowser.CMN_setAuthenticationHandler this$0, final Browser val$browser) {
        this.this$0 = this$0;
        this.val$browser = val$browser;
    }
    
    public void authenticate(final AuthenticationEvent e) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aconst_null    
        //     5: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_getCredentials.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIIl;)V
        //     8: aload_0         /* this */
        //     9: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIII.val$browser:Lorg/eclipse/swt/browser/Browser;
        //    12: iconst_1       
        //    13: anewarray       Ljava/lang/Object;
        //    16: dup            
        //    17: iconst_0       
        //    18: aload_1         /* e */
        //    19: getfield        org/eclipse/swt/browser/AuthenticationEvent.location:Ljava/lang/String;
        //    22: aastore        
        //    23: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_getCredentials.syncExec:(Lorg/eclipse/swt/widgets/Control;[Ljava/lang/Object;)Ljava/lang/Object;
        //    26: checkcast       [Ljava/lang/Object;
        //    29: checkcast       [Ljava/lang/Object;
        //    32: astore_2        /* result */
        //    33: aload_2         /* result */
        //    34: iconst_0       
        //    35: aaload         
        //    36: checkcast       Ljava/lang/Boolean;
        //    39: invokevirtual   java/lang/Boolean.booleanValue:()Z
        //    42: istore_3        /* doIt */
        //    43: iload_3         /* doIt */
        //    44: ifeq            70
        //    47: aload_1         /* e */
        //    48: aload_2         /* result */
        //    49: iconst_1       
        //    50: aaload         
        //    51: checkcast       Ljava/lang/String;
        //    54: putfield        org/eclipse/swt/browser/AuthenticationEvent.user:Ljava/lang/String;
        //    57: aload_1         /* e */
        //    58: aload_2         /* result */
        //    59: iconst_2       
        //    60: aaload         
        //    61: checkcast       Ljava/lang/String;
        //    64: putfield        org/eclipse/swt/browser/AuthenticationEvent.password:Ljava/lang/String;
        //    67: goto            75
        //    70: aload_1         /* e */
        //    71: iconst_0       
        //    72: putfield        org/eclipse/swt/browser/AuthenticationEvent.doit:Z
        //    75: return         
        //    StackMapTable: 00 02 FD 00 46 07 00 34 01 04
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCallCore(AstMethodBodyBuilder.java:1321)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.adjustArgumentsForMethodCall(AstMethodBodyBuilder.java:1276)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1178)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
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
