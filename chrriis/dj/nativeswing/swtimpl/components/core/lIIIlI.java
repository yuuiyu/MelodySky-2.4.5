//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;

final class lIIIlI implements StatusTextListener
{
    final /* synthetic */ Browser val$browser;
    
    lIIIlI(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void changed(final StatusTextEvent e) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIlI.val$browser:Lorg/eclipse/swt/browser/Browser;
        //     4: ldc             "CMJ_updateStatus.status"
        //     6: invokevirtual   org/eclipse/swt/browser/Browser.getData:(Ljava/lang/String;)Ljava/lang/Object;
        //     9: checkcast       Ljava/lang/String;
        //    12: astore_2        /* oldStatus */
        //    13: aload_1         /* e */
        //    14: getfield        org/eclipse/swt/browser/StatusTextEvent.text:Ljava/lang/String;
        //    17: astore_3        /* newStatus */
        //    18: aload_3         /* newStatus */
        //    19: ldc             "scommand://"
        //    21: invokevirtual   java/lang/String.startsWith:(Ljava/lang/String;)Z
        //    24: ifeq            261
        //    27: aload_1         /* e */
        //    28: getfield        org/eclipse/swt/browser/StatusTextEvent.display:Lorg/eclipse/swt/widgets/Display;
        //    31: new             Lchrriis/dj/nativeswing/swtimpl/components/core/lIIlll;
        //    34: dup            
        //    35: aload_0         /* this */
        //    36: aload_3         /* newStatus */
        //    37: aload_2         /* oldStatus */
        //    38: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/lIIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIlI;Ljava/lang/String;Ljava/lang/String;)V
        //    41: invokevirtual   org/eclipse/swt/widgets/Display.asyncExec:(Ljava/lang/Runnable;)V
        //    44: aload_3         /* newStatus */
        //    45: ldc             "scommand://"
        //    47: invokevirtual   java/lang/String.length:()I
        //    50: invokevirtual   java/lang/String.substring:(I)Ljava/lang/String;
        //    53: astore          query
        //    55: aload           query
        //    57: ldc             "/"
        //    59: invokevirtual   java/lang/String.endsWith:(Ljava/lang/String;)Z
        //    62: ifeq            80
        //    65: aload           query
        //    67: iconst_0       
        //    68: aload           query
        //    70: invokevirtual   java/lang/String.length:()I
        //    73: iconst_1       
        //    74: isub           
        //    75: invokevirtual   java/lang/String.substring:(II)Ljava/lang/String;
        //    78: astore          query
        //    80: new             Ljava/util/ArrayList;
        //    83: dup            
        //    84: invokespecial   java/util/ArrayList.<init>:()V
        //    87: astore          queryElementList
        //    89: new             Ljava/util/StringTokenizer;
        //    92: dup            
        //    93: aload           query
        //    95: ldc             "&"
        //    97: iconst_1       
        //    98: invokespecial   java/util/StringTokenizer.<init>:(Ljava/lang/String;Ljava/lang/String;Z)V
        //   101: astore          st
        //   103: aconst_null    
        //   104: astore          lastToken
        //   106: aload           st
        //   108: invokevirtual   java/util/StringTokenizer.hasMoreTokens:()Z
        //   111: ifeq            172
        //   114: aload           st
        //   116: invokevirtual   java/util/StringTokenizer.nextToken:()Ljava/lang/String;
        //   119: astore          token
        //   121: ldc             "&"
        //   123: aload           token
        //   125: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //   128: ifeq            152
        //   131: aload           lastToken
        //   133: ifnonnull       146
        //   136: aload           queryElementList
        //   138: ldc             ""
        //   140: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   145: pop            
        //   146: aconst_null    
        //   147: astore          lastToken
        //   149: goto            169
        //   152: aload           token
        //   154: astore          lastToken
        //   156: aload           queryElementList
        //   158: aload           token
        //   160: invokestatic    chrriis/dj/nativeswing/common/Utils.decodeURL:(Ljava/lang/String;)Ljava/lang/String;
        //   163: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   168: pop            
        //   169: goto            106
        //   172: aload           lastToken
        //   174: ifnonnull       187
        //   177: aload           queryElementList
        //   179: ldc             ""
        //   181: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   186: pop            
        //   187: aload           queryElementList
        //   189: invokeinterface java/util/List.isEmpty:()Z
        //   194: ifeq            202
        //   197: ldc             ""
        //   199: goto            213
        //   202: aload           queryElementList
        //   204: iconst_0       
        //   205: invokeinterface java/util/List.remove:(I)Ljava/lang/Object;
        //   210: checkcast       Ljava/lang/String;
        //   213: astore          command
        //   215: aload           queryElementList
        //   217: iconst_0       
        //   218: anewarray       Ljava/lang/String;
        //   221: invokeinterface java/util/List.toArray:([Ljava/lang/Object;)[Ljava/lang/Object;
        //   226: checkcast       [Ljava/lang/String;
        //   229: astore          args
        //   231: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_commandReceived;
        //   234: dup            
        //   235: aconst_null    
        //   236: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_commandReceived.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIIl;)V
        //   239: aload_0         /* this */
        //   240: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIlI.val$browser:Lorg/eclipse/swt/browser/Browser;
        //   243: iconst_2       
        //   244: anewarray       Ljava/lang/Object;
        //   247: dup            
        //   248: iconst_0       
        //   249: aload           command
        //   251: aastore        
        //   252: dup            
        //   253: iconst_1       
        //   254: aload           args
        //   256: aastore        
        //   257: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_commandReceived.asyncExec:(Lorg/eclipse/swt/widgets/Control;[Ljava/lang/Object;)V
        //   260: return         
        //   261: aload_2         /* oldStatus */
        //   262: aload_3         /* newStatus */
        //   263: invokestatic    chrriis/dj/nativeswing/common/Utils.equals:(Ljava/lang/Object;Ljava/lang/Object;)Z
        //   266: ifne            302
        //   269: aload_0         /* this */
        //   270: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIlI.val$browser:Lorg/eclipse/swt/browser/Browser;
        //   273: ldc             "CMJ_updateStatus.status"
        //   275: aload_3         /* newStatus */
        //   276: invokevirtual   org/eclipse/swt/browser/Browser.setData:(Ljava/lang/String;Ljava/lang/Object;)V
        //   279: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_updateStatus;
        //   282: dup            
        //   283: aconst_null    
        //   284: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_updateStatus.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIIl;)V
        //   287: aload_0         /* this */
        //   288: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIlI.val$browser:Lorg/eclipse/swt/browser/Browser;
        //   291: iconst_1       
        //   292: anewarray       Ljava/lang/Object;
        //   295: dup            
        //   296: iconst_0       
        //   297: aload_3         /* newStatus */
        //   298: aastore        
        //   299: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeWebBrowser$CMJ_updateStatus.asyncExec:(Lorg/eclipse/swt/widgets/Control;[Ljava/lang/Object;)V
        //   302: return         
        //    StackMapTable: 00 0B FE 00 50 07 00 2D 07 00 2D 07 00 2D FE 00 19 07 00 58 07 00 5B 07 00 2D FC 00 27 07 00 2D 05 10 FA 00 02 0E 0E 4A 07 00 2D FF 00 2F 00 04 07 00 02 07 00 2F 07 00 2D 07 00 2D 00 00 28
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
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:440)
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
