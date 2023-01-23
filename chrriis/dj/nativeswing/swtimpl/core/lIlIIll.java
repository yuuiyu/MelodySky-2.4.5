//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

final class lIlIIll extends Thread
{
    final /* synthetic */ MessagingInterface val$messagingInterface_;
    
    lIlIIll(final String x0, final MessagingInterface val$messagingInterface_) {
        this.val$messagingInterface_ = val$messagingInterface_;
        super(x0);
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIIll.val$messagingInterface_:Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //     4: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.isAlive:()Z
        //     7: ifeq            60
        //    10: invokestatic    java/lang/System.currentTimeMillis:()J
        //    13: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$2100:()J
        //    16: lsub           
        //    17: ldc2_w          100
        //    20: lcmp           
        //    21: ifle            47
        //    24: new             Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMJ_unlockSystemIn;
        //    27: dup            
        //    28: aconst_null    
        //    29: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMJ_unlockSystemIn.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIllllI;)V
        //    32: iconst_0       
        //    33: iconst_0       
        //    34: anewarray       Ljava/lang/Object;
        //    37: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMJ_unlockSystemIn.asyncExec:(Z[Ljava/lang/Object;)V
        //    40: invokestatic    java/lang/System.currentTimeMillis:()J
        //    43: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$2102:(J)J
        //    46: pop2           
        //    47: ldc2_w          100
        //    50: invokestatic    chrriis/dj/nativeswing/swtimpl/core/lIlIIll.sleep:(J)V
        //    53: goto            0
        //    56: astore_1       
        //    57: goto            0
        //    60: return         
        //    StackMapTable: 00 04 00 2E 48 07 00 23 03
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  47     53     56     60     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
