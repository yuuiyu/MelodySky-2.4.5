//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import java.lang.reflect.*;

final class lIIIll implements InvocationHandler
{
    final /* synthetic */ Integer val$id;
    final /* synthetic */ boolean val$isNativeSide;
    
    lIIIll(final Integer val$id, final boolean val$isNativeSide) {
        this.val$id = val$id;
        this.val$isNativeSide = val$isNativeSide;
    }
    
    @Override
    public Object invoke(final Object proxy, final Method method, final Object[] args) throws Throwable {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   java/lang/reflect/Method.getName:()Ljava/lang/String;
        //     4: astore          methodName
        //     6: aload_2         /* method */
        //     7: invokevirtual   java/lang/reflect/Method.getParameterTypes:()[Ljava/lang/Class;
        //    10: astore          parameterTypes
        //    12: ldc             "finalize"
        //    14: aload           methodName
        //    16: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
        //    19: ifeq            95
        //    22: aload           parameterTypes
        //    24: arraylength    
        //    25: ifne            95
        //    28: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM.access$400:()Ljava/util/Map;
        //    31: aload_0         /* this */
        //    32: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$id:Ljava/lang/Integer;
        //    35: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    40: pop            
        //    41: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM.access$300:()Ljava/util/Map;
        //    44: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$InterfaceInfo;
        //    47: dup            
        //    48: aload_0         /* this */
        //    49: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$InterfaceInfo.<init>:(Ljava/lang/Object;)V
        //    52: invokeinterface java/util/Map.remove:(Ljava/lang/Object;)Ljava/lang/Object;
        //    57: pop            
        //    58: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_disposeResources;
        //    61: dup            
        //    62: aconst_null    
        //    63: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_disposeResources.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIll;)V
        //    66: aload_0         /* this */
        //    67: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$isNativeSide:Z
        //    70: ifne            77
        //    73: iconst_1       
        //    74: goto            78
        //    77: iconst_0       
        //    78: iconst_1       
        //    79: anewarray       Ljava/lang/Object;
        //    82: dup            
        //    83: iconst_0       
        //    84: aload_0         /* this */
        //    85: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$id:Ljava/lang/Integer;
        //    88: aastore        
        //    89: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_disposeResources.syncExec:(Z[Ljava/lang/Object;)Ljava/lang/Object;
        //    92: pop            
        //    93: aconst_null    
        //    94: areturn        
        //    95: new             Lchrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_runMethod;
        //    98: dup            
        //    99: aconst_null    
        //   100: invokespecial   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_runMethod.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/core/lIIIll;)V
        //   103: aload_0         /* this */
        //   104: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$isNativeSide:Z
        //   107: ifne            114
        //   110: iconst_1       
        //   111: goto            115
        //   114: iconst_0       
        //   115: iconst_5       
        //   116: anewarray       Ljava/lang/Object;
        //   119: dup            
        //   120: iconst_0       
        //   121: aload_0         /* this */
        //   122: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$id:Ljava/lang/Integer;
        //   125: aastore        
        //   126: dup            
        //   127: iconst_1       
        //   128: aload           methodName
        //   130: aastore        
        //   131: dup            
        //   132: iconst_2       
        //   133: aload           parameterTypes
        //   135: aastore        
        //   136: dup            
        //   137: iconst_3       
        //   138: aload_3         /* args */
        //   139: aload_0         /* this */
        //   140: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$isNativeSide:Z
        //   143: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM.pack_:(Ljava/lang/Object;Z)Ljava/lang/Object;
        //   146: aastore        
        //   147: dup            
        //   148: iconst_4       
        //   149: aload_0         /* this */
        //   150: getfield        chrriis/dj/nativeswing/swtimpl/components/core/lIIIll.val$isNativeSide:Z
        //   153: ifne            160
        //   156: iconst_1       
        //   157: goto            161
        //   160: iconst_0       
        //   161: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //   164: aastore        
        //   165: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$CM_runMethod.syncExec:(Z[Ljava/lang/Object;)Ljava/lang/Object;
        //   168: checkcast       Lchrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$RunMethodResult;
        //   171: astore          result
        //   173: aload           result
        //   175: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$RunMethodResult.getOutParams:()[Ljava/lang/Object;
        //   178: astore          outParams
        //   180: aload           outParams
        //   182: ifnull          283
        //   185: iconst_0       
        //   186: istore          cur
        //   188: aload_3         /* args */
        //   189: astore          9
        //   191: aload           9
        //   193: arraylength    
        //   194: istore          10
        //   196: iconst_0       
        //   197: istore          11
        //   199: iload           11
        //   201: iload           10
        //   203: if_icmpge       283
        //   206: aload           9
        //   208: iload           11
        //   210: aaload         
        //   211: astore          o
        //   213: aload           o
        //   215: instanceof      [Ljava/lang/Object;
        //   218: ifeq            277
        //   221: aload           o
        //   223: checkcast       [Ljava/lang/Object;
        //   226: checkcast       [Ljava/lang/Object;
        //   229: astore          in
        //   231: aload           outParams
        //   233: iload           cur
        //   235: iinc            cur, 1
        //   238: aaload         
        //   239: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM.unpack_:(Ljava/lang/Object;)Ljava/lang/Object;
        //   242: checkcast       [Ljava/lang/Object;
        //   245: checkcast       [Ljava/lang/Object;
        //   248: astore          out
        //   250: iconst_0       
        //   251: istore          j
        //   253: iload           j
        //   255: aload           in
        //   257: arraylength    
        //   258: if_icmpge       277
        //   261: aload           in
        //   263: iload           j
        //   265: aload           out
        //   267: iload           j
        //   269: aaload         
        //   270: aastore        
        //   271: iinc            j, 1
        //   274: goto            253
        //   277: iinc            11, 1
        //   280: goto            199
        //   283: aload           result
        //   285: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM$RunMethodResult.getResult:()Ljava/lang/Object;
        //   288: invokestatic    chrriis/dj/nativeswing/swtimpl/components/core/NativeMozillaXPCOM.unpack_:(Ljava/lang/Object;)Ljava/lang/Object;
        //   291: areturn        
        //    Exceptions:
        //  throws java.lang.Throwable
        //    StackMapTable: 00 0B FF 00 4D 00 06 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 00 01 07 00 14 FF 00 00 00 06 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 00 02 07 00 14 01 10 52 07 00 17 FF 00 00 00 06 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 00 02 07 00 17 01 FF 00 2C 00 06 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 00 05 07 00 17 01 07 00 51 07 00 51 01 FF 00 00 00 06 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 00 06 07 00 17 01 07 00 51 07 00 51 01 01 FF 00 25 00 0C 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 07 00 0E 07 00 51 01 07 00 51 01 01 00 00 FF 00 35 00 10 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 07 00 0E 07 00 51 01 07 00 51 01 01 07 00 04 07 00 51 07 00 51 01 00 00 F8 00 17 FF 00 05 00 08 07 00 02 07 00 04 07 00 2D 07 00 51 07 00 39 07 00 53 07 00 0E 07 00 51 00 00
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
