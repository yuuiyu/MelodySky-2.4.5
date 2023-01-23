//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIlllIl extends Thread
{
    final /* synthetic */ DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1;
    
    lIlllIl(final DefaultVLCPlayerDecorator.VLCPlayerControlBar this$1, final String x0) {
        this.this$1 = this$1;
        super(x0);
    }
    
    @Override
    public void run() {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: astore_1        /* currentThread */
        //     2: aload_1         /* currentThread */
        //     3: aload_0         /* this */
        //     4: getfield        chrriis/dj/nativeswing/swtimpl/components/lIlllIl.this$1:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;
        //     7: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.access$600:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;)Ljava/lang/Thread;
        //    10: if_acmpne       62
        //    13: aload_0         /* this */
        //    14: getfield        chrriis/dj/nativeswing/swtimpl/components/lIlllIl.this$1:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;
        //    17: getfield        chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.this$0:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;
        //    20: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator.access$000:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator;)Lchrriis/dj/nativeswing/swtimpl/components/JVLCPlayer;
        //    23: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JVLCPlayer.isNativePeerDisposed:()Z
        //    26: ifeq            37
        //    29: aload_0         /* this */
        //    30: getfield        chrriis/dj/nativeswing/swtimpl/components/lIlllIl.this$1:Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;
        //    33: invokestatic    chrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar.access$700:(Lchrriis/dj/nativeswing/swtimpl/components/DefaultVLCPlayerDecorator$VLCPlayerControlBar;)V
        //    36: return         
        //    37: ldc2_w          1000
        //    40: invokestatic    chrriis/dj/nativeswing/swtimpl/components/lIlllIl.sleep:(J)V
        //    43: goto            47
        //    46: astore_2       
        //    47: new             Lchrriis/dj/nativeswing/swtimpl/components/llIlIIl;
        //    50: dup            
        //    51: aload_0         /* this */
        //    52: aload_1         /* currentThread */
        //    53: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIlIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/lIlllIl;Ljava/lang/Thread;)V
        //    56: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    59: goto            2
        //    62: return         
        //    StackMapTable: 00 05 FC 00 02 07 00 02 22 48 07 00 1F 00 0E
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  37     43     46     47     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
