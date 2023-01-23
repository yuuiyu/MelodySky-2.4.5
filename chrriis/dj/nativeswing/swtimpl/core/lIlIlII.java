//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

class lIlIlII extends Thread
{
    protected Thread[] activeThreads;
    final /* synthetic */ Thread val$displayThread;
    final /* synthetic */ Thread val$currentThread;
    final /* synthetic */ SWTNativeInterface this$0;
    
    lIlIlII(final SWTNativeInterface this$0, final String x0, final Thread val$displayThread, final Thread val$currentThread) {
        this.this$0 = this$0;
        this.val$displayThread = val$displayThread;
        this.val$currentThread = val$currentThread;
        super(x0);
        this.activeThreads = new Thread[1024];
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: istore_1        /* isAlive */
        //     2: iload_1         /* isAlive */
        //     3: ifeq            121
        //     6: ldc2_w          1000
        //     9: invokestatic    java/lang/Thread.sleep:(J)V
        //    12: goto            16
        //    15: astore_2       
        //    16: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    19: invokevirtual   java/lang/Thread.getThreadGroup:()Ljava/lang/ThreadGroup;
        //    22: astore_2        /* group */
        //    23: aload_2         /* group */
        //    24: astore_3        /* parentGroup */
        //    25: aload_3         /* parentGroup */
        //    26: invokevirtual   java/lang/ThreadGroup.getParent:()Ljava/lang/ThreadGroup;
        //    29: dup            
        //    30: astore_3        /* parentGroup */
        //    31: ifnull          39
        //    34: aload_3         /* parentGroup */
        //    35: astore_2        /* group */
        //    36: goto            25
        //    39: iconst_0       
        //    40: istore_1        /* isAlive */
        //    41: aload_2         /* group */
        //    42: aload_0         /* this */
        //    43: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlII.activeThreads:[Ljava/lang/Thread;
        //    46: iconst_1       
        //    47: invokevirtual   java/lang/ThreadGroup.enumerate:([Ljava/lang/Thread;Z)I
        //    50: iconst_1       
        //    51: isub           
        //    52: istore_3        /* i */
        //    53: iload_3         /* i */
        //    54: iflt            118
        //    57: aload_0         /* this */
        //    58: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlII.activeThreads:[Ljava/lang/Thread;
        //    61: iload_3         /* i */
        //    62: aaload         
        //    63: astore          t
        //    65: iload_1         /* isAlive */
        //    66: ifne            105
        //    69: aload           t
        //    71: aload_0         /* this */
        //    72: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlII.val$displayThread:Ljava/lang/Thread;
        //    75: if_acmpeq       105
        //    78: aload           t
        //    80: aload_0         /* this */
        //    81: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlII.val$currentThread:Ljava/lang/Thread;
        //    84: if_acmpeq       105
        //    87: aload           t
        //    89: invokevirtual   java/lang/Thread.isDaemon:()Z
        //    92: ifne            105
        //    95: aload           t
        //    97: invokevirtual   java/lang/Thread.isAlive:()Z
        //   100: ifeq            105
        //   103: iconst_1       
        //   104: istore_1        /* isAlive */
        //   105: aload_0         /* this */
        //   106: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlII.activeThreads:[Ljava/lang/Thread;
        //   109: iload_3         /* i */
        //   110: aconst_null    
        //   111: aastore        
        //   112: iinc            i, -1
        //   115: goto            53
        //   118: goto            2
        //   121: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
        //   124: ifnonnull       135
        //   127: iconst_0       
        //   128: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$502:(Z)Z
        //   131: pop            
        //   132: goto            158
        //   135: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
        //   138: invokevirtual   org/eclipse/swt/widgets/Display.isDisposed:()Z
        //   141: ifne            158
        //   144: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
        //   147: new             Lchrriis/dj/nativeswing/swtimpl/core/lllIIlI;
        //   150: dup            
        //   151: aload_0         /* this */
        //   152: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllIIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIlIlII;)V
        //   155: invokevirtual   org/eclipse/swt/widgets/Display.asyncExec:(Ljava/lang/Runnable;)V
        //   158: return         
        //    StackMapTable: 00 0B FC 00 02 01 4C 07 00 27 00 FD 00 08 07 00 37 07 00 37 0D FF 00 0D 00 04 07 00 02 01 07 00 37 01 00 00 FC 00 33 07 00 04 FA 00 0C F9 00 02 0D 16
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  6      12     15     16     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
