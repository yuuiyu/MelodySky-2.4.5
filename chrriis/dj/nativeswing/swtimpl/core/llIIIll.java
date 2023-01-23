//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.widgets.*;

class llIIIll extends Thread
{
    final /* synthetic */ Control val$control;
    final /* synthetic */ SWTNativeComponent.CMN_setControlParentEnabled this$0;
    
    llIIIll(final SWTNativeComponent.CMN_setControlParentEnabled this$0, final String x0, final Control val$control) {
        this.this$0 = this$0;
        this.val$control = val$control;
        super(x0);
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: invokestatic    java/lang/Thread.sleep:(J)V
        //     6: goto            10
        //     9: astore_1       
        //    10: aload_0         /* this */
        //    11: getfield        chrriis/dj/nativeswing/swtimpl/core/llIIIll.val$control:Lorg/eclipse/swt/widgets/Control;
        //    14: invokevirtual   org/eclipse/swt/widgets/Control.isDisposed:()Z
        //    17: ifeq            21
        //    20: return         
        //    21: aload_0         /* this */
        //    22: getfield        chrriis/dj/nativeswing/swtimpl/core/llIIIll.val$control:Lorg/eclipse/swt/widgets/Control;
        //    25: invokevirtual   org/eclipse/swt/widgets/Control.getDisplay:()Lorg/eclipse/swt/widgets/Display;
        //    28: new             Lchrriis/dj/nativeswing/swtimpl/core/lllIIIl;
        //    31: dup            
        //    32: aload_0         /* this */
        //    33: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllIIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/llIIIll;)V
        //    36: invokevirtual   org/eclipse/swt/widgets/Display.asyncExec:(Ljava/lang/Runnable;)V
        //    39: return         
        //    StackMapTable: 00 03 49 07 00 23 00 0A
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  0      6      9      10     Ljava/lang/InterruptedException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
