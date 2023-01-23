//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

class lIIlIlII extends Thread
{
    final /* synthetic */ VLCPlaylist this$0;
    
    lIIlIlII(final VLCPlaylist this$0, final String x0) {
        this.this$0 = this$0;
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
        //     2: iconst_1       
        //     3: istore_2        /* isFirst */
        //     4: aload_1         /* currentThread */
        //     5: aload_0         /* this */
        //     6: getfield        chrriis/dj/nativeswing/swtimpl/components/lIIlIlII.this$0:Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;
        //     9: invokestatic    chrriis/dj/nativeswing/swtimpl/components/VLCPlaylist.access$000:(Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;)Ljava/lang/Thread;
        //    12: if_acmpne       74
        //    15: aload_0         /* this */
        //    16: getfield        chrriis/dj/nativeswing/swtimpl/components/lIIlIlII.this$0:Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;
        //    19: invokestatic    chrriis/dj/nativeswing/swtimpl/components/VLCPlaylist.access$100:(Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;)Lchrriis/dj/nativeswing/swtimpl/components/JVLCPlayer;
        //    22: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JVLCPlayer.isNativePeerDisposed:()Z
        //    25: ifeq            37
        //    28: aload_0         /* this */
        //    29: getfield        chrriis/dj/nativeswing/swtimpl/components/lIIlIlII.this$0:Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;
        //    32: iconst_0       
        //    33: invokestatic    chrriis/dj/nativeswing/swtimpl/components/VLCPlaylist.access$200:(Lchrriis/dj/nativeswing/swtimpl/components/VLCPlaylist;Z)V
        //    36: return         
        //    37: iload_2         /* isFirst */
        //    38: ifeq            47
        //    41: ldc2_w          3000
        //    44: goto            50
        //    47: ldc2_w          500
        //    50: invokestatic    chrriis/dj/nativeswing/swtimpl/components/lIIlIlII.sleep:(J)V
        //    53: iconst_0       
        //    54: istore_2        /* isFirst */
        //    55: goto            59
        //    58: astore_3       
        //    59: new             Lchrriis/dj/nativeswing/swtimpl/components/lIllIIl;
        //    62: dup            
        //    63: aload_0         /* this */
        //    64: aload_1         /* currentThread */
        //    65: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIllIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/lIIlIlII;Ljava/lang/Thread;)V
        //    68: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    71: goto            4
        //    74: return         
        //    StackMapTable: 00 07 FD 00 04 07 00 02 01 20 09 42 04 47 07 00 1D 00 0E
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  37     55     58     59     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
