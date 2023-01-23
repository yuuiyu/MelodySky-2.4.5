//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;

final class lIllIl implements OpenWindowListener
{
    final /* synthetic */ Browser val$browser;
    
    lIllIl(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void open(final WindowEvent e) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: iconst_1       
        //     2: putfield        org/eclipse/swt/browser/WindowEvent.required:Z
        //     5: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_createWindow;
        //     8: dup            
        //     9: aconst_null    
        //    10: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_createWindow.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIIl;)V
        //    13: aload_0         /* this */
        //    14: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIllIl.val$browser:Lorg/eclipse/swt/browser/Browser;
        //    17: iconst_0       
        //    18: anewarray       Ljava/lang/Object;
        //    21: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_createWindow.syncExec:(Lorg/eclipse/swt/widgets/Control;[Ljava/lang/Object;)Ljava/lang/Object;
        //    24: checkcast       Ljava/lang/Integer;
        //    27: astore_2        /* componentID */
        //    28: aload_2         /* componentID */
        //    29: ifnonnull       68
        //    32: iconst_1       
        //    33: istore          isDisposed
        //    35: new             Lorg/eclipse/swt/widgets/Shell;
        //    38: dup            
        //    39: invokespecial   org/eclipse/swt/widgets/Shell.<init>:()V
        //    42: astore          shell
        //    44: new             Lorg/eclipse/swt/browser/Browser;
        //    47: dup            
        //    48: aload           shell
        //    50: aload_0         /* this */
        //    51: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIllIl.val$browser:Lorg/eclipse/swt/browser/Browser;
        //    54: invokevirtual   org/eclipse/swt/browser/Browser.getStyle:()I
        //    57: invokespecial   org/eclipse/swt/browser/Browser.<init>:(Lorg/eclipse/swt/widgets/Composite;I)V
        //    60: astore_3        /* newWebBrowser */
        //    61: aload_3         /* newWebBrowser */
        //    62: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser.access$1900:(Lorg/eclipse/swt/browser/Browser;)V
        //    65: goto            85
        //    68: iconst_0       
        //    69: istore          isDisposed
        //    71: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser.access$2000:()Lchrriis/dj/nativeswing/common/ObjectRegistry;
        //    74: aload_2         /* componentID */
        //    75: invokevirtual   java/lang/Integer.intValue:()I
        //    78: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.get:(I)Ljava/lang/Object;
        //    81: checkcast       Lorg/eclipse/swt/browser/Browser;
        //    84: astore_3        /* newWebBrowser */
        //    85: aload_1         /* e */
        //    86: aload_3         /* newWebBrowser */
        //    87: putfield        org/eclipse/swt/browser/WindowEvent.browser:Lorg/eclipse/swt/browser/Browser;
        //    90: aload_3         /* newWebBrowser */
        //    91: new             Lchrriis/dj/nativeswing/swtimpl/components/core/lIlIlI;
        //    94: dup            
        //    95: aload_0         /* this */
        //    96: iload           isDisposed
        //    98: aload_3         /* newWebBrowser */
        //    99: aload_2         /* componentID */
        //   100: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/lIlIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIllIl;ZLorg/eclipse/swt/browser/Browser;Ljava/lang/Integer;)V
        //   103: invokevirtual   org/eclipse/swt/browser/Browser.addVisibilityWindowListener:(Lorg/eclipse/swt/browser/VisibilityWindowListener;)V
        //   106: return         
        //    StackMapTable: 00 02 FC 00 44 07 00 2F FD 00 10 07 00 34 01
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
