//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

class llIllll extends Thread
{
    final /* synthetic */ boolean val$exitOnEndOfStream;
    final /* synthetic */ MessagingInterface this$0;
    
    llIllll(final MessagingInterface this$0, final String x0, final boolean val$exitOnEndOfStream) {
        this.this$0 = this$0;
        this.val$exitOnEndOfStream = val$exitOnEndOfStream;
        super(x0);
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //     4: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.isAlive:()Z
        //     7: ifeq            387
        //    10: aconst_null    
        //    11: astore_1        /* message */
        //    12: aload_0         /* this */
        //    13: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    16: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.readMessageFromChannel:()Lchrriis/dj/nativeswing/swtimpl/Message;
        //    19: astore_1        /* message */
        //    20: goto            224
        //    23: astore_2        /* e */
        //    24: iconst_0       
        //    25: istore_3        /* isRespawned */
        //    26: aload_0         /* this */
        //    27: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    30: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.isAlive:()Z
        //    33: ifeq            80
        //    36: aload_0         /* this */
        //    37: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    40: iconst_0       
        //    41: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.setAlive:(Z)V
        //    44: aload_0         /* this */
        //    45: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.val$exitOnEndOfStream:Z
        //    48: ifeq            59
        //    51: aload_0         /* this */
        //    52: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    55: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.terminate:()V
        //    58: return         
        //    59: aload_2         /* e */
        //    60: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    63: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getInstance:()Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface;
        //    66: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.notifyKilled:()Z
        //    69: istore_3        /* isRespawned */
        //    70: goto            80
        //    73: astore          ex
        //    75: aload           ex
        //    77: invokevirtual   java/lang/Exception.printStackTrace:()V
        //    80: aload_0         /* this */
        //    81: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    84: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$600:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/lang/Object;
        //    87: dup            
        //    88: astore          4
        //    90: monitorenter   
        //    91: aload_0         /* this */
        //    92: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //    95: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$700:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/util/List;
        //    98: invokeinterface java/util/List.clear:()V
        //   103: aload_0         /* this */
        //   104: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   107: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$600:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/lang/Object;
        //   110: invokevirtual   java/lang/Object.notify:()V
        //   113: aload           4
        //   115: monitorexit    
        //   116: goto            127
        //   119: astore          5
        //   121: aload           4
        //   123: monitorexit    
        //   124: aload           5
        //   126: athrow         
        //   127: aload_0         /* this */
        //   128: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   131: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$000:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Lchrriis/dj/nativeswing/common/ObjectRegistry;
        //   134: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.getInstanceIDs:()[I
        //   137: astore          4
        //   139: aload           4
        //   141: arraylength    
        //   142: istore          5
        //   144: iconst_0       
        //   145: istore          6
        //   147: iload           6
        //   149: iload           5
        //   151: if_icmpge       214
        //   154: aload           4
        //   156: iload           6
        //   158: iaload         
        //   159: istore          instanceID
        //   161: aload_0         /* this */
        //   162: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   165: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$000:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Lchrriis/dj/nativeswing/common/ObjectRegistry;
        //   168: iload           instanceID
        //   170: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.get:(I)Ljava/lang/Object;
        //   173: astore          o
        //   175: aload           o
        //   177: instanceof      Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface$ThreadLock;
        //   180: ifeq            208
        //   183: aload           o
        //   185: dup            
        //   186: astore          9
        //   188: monitorenter   
        //   189: aload           o
        //   191: invokevirtual   java/lang/Object.notify:()V
        //   194: aload           9
        //   196: monitorexit    
        //   197: goto            208
        //   200: astore          10
        //   202: aload           9
        //   204: monitorexit    
        //   205: aload           10
        //   207: athrow         
        //   208: iinc            6, 1
        //   211: goto            147
        //   214: iload_3         /* isRespawned */
        //   215: ifeq            224
        //   218: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getInstance:()Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface;
        //   221: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.notifyRespawned:()V
        //   224: aload_1         /* message */
        //   225: ifnull          384
        //   228: aload_1         /* message */
        //   229: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.isMessageUI:(Lchrriis/dj/nativeswing/swtimpl/Message;)Z
        //   232: ifne            294
        //   235: aload_1         /* message */
        //   236: astore_2        /* message_ */
        //   237: new             Lchrriis/dj/nativeswing/swtimpl/core/lllIlIl;
        //   240: dup            
        //   241: aload_0         /* this */
        //   242: new             Ljava/lang/StringBuilder;
        //   245: dup            
        //   246: invokespecial   java/lang/StringBuilder.<init>:()V
        //   249: ldc             "NativeSwing["
        //   251: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   254: aload_0         /* this */
        //   255: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   258: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.getPID:()I
        //   261: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   264: ldc             "] Non-UI Message ["
        //   266: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   269: aload_1         /* message */
        //   270: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getMessageID:(Lchrriis/dj/nativeswing/swtimpl/Message;)I
        //   273: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   276: ldc             "] Executor"
        //   278: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   281: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   284: aload_2         /* message_ */
        //   285: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllIlIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/llIllll;Ljava/lang/String;Lchrriis/dj/nativeswing/swtimpl/Message;)V
        //   288: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/lllIlIl.start:()V
        //   291: goto            384
        //   294: aload_0         /* this */
        //   295: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   298: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$600:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/lang/Object;
        //   301: dup            
        //   302: astore_2       
        //   303: monitorenter   
        //   304: aload_0         /* this */
        //   305: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   308: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$700:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/util/List;
        //   311: aload_1         /* message */
        //   312: invokeinterface java/util/List.add:(Ljava/lang/Object;)Z
        //   317: pop            
        //   318: aload_0         /* this */
        //   319: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   322: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$800:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Z
        //   325: ifeq            341
        //   328: aload_0         /* this */
        //   329: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   332: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$600:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/lang/Object;
        //   335: invokevirtual   java/lang/Object.notify:()V
        //   338: goto            372
        //   341: aload_0         /* this */
        //   342: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   345: invokestatic    chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.access$700:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Ljava/util/List;
        //   348: invokeinterface java/util/List.size:()I
        //   353: iconst_1       
        //   354: if_icmpne       372
        //   357: aload_0         /* this */
        //   358: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   361: new             Lchrriis/dj/nativeswing/swtimpl/core/lIllIll;
        //   364: dup            
        //   365: aload_0         /* this */
        //   366: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIllIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/llIllll;)V
        //   369: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.asyncUIExec:(Ljava/lang/Runnable;)V
        //   372: aload_2        
        //   373: monitorexit    
        //   374: goto            384
        //   377: astore          11
        //   379: aload_2        
        //   380: monitorexit    
        //   381: aload           11
        //   383: athrow         
        //   384: goto            0
        //   387: aload_0         /* this */
        //   388: getfield        chrriis/dj/nativeswing/swtimpl/core/llIllll.this$0:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   391: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.closeChannel:()V
        //   394: return         
        //    StackMapTable: 00 12 00 FF 00 16 00 02 07 00 02 07 00 30 00 01 07 00 26 FD 00 23 07 00 26 01 4D 07 00 26 06 FF 00 26 00 05 07 00 02 07 00 30 07 00 26 01 07 00 51 00 01 07 00 56 07 FF 00 13 00 07 07 00 02 07 00 30 07 00 26 01 07 00 62 01 01 00 00 FF 00 34 00 0A 07 00 02 07 00 30 07 00 26 01 07 00 62 01 01 01 07 00 51 07 00 51 00 01 07 00 56 FA 00 07 F9 00 05 FF 00 09 00 02 07 00 02 07 00 30 00 00 FB 00 45 FC 00 2E 07 00 51 1E 44 07 00 56 FA 00 06 FA 00 02
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  12     20     23     224    Ljava/lang/Exception;
        //  63     70     73     80     Ljava/lang/Exception;
        //  91     116    119    127    Any
        //  119    124    119    127    Any
        //  189    197    200    208    Any
        //  200    205    200    208    Any
        //  304    374    377    384    Any
        //  377    381    377    384    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
