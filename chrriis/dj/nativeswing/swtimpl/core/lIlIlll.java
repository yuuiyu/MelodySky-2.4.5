//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import javax.swing.*;
import java.awt.event.*;

class lIlIlll extends WindowAdapter
{
    final /* synthetic */ JDialog val$dialog;
    final /* synthetic */ ControlCommandMessage val$message;
    final /* synthetic */ Object[] val$args;
    final /* synthetic */ NativeModalDialogHandler this$0;
    
    lIlIlll(final NativeModalDialogHandler this$0, final JDialog val$dialog, final ControlCommandMessage val$message, final Object[] val$args) {
        this.this$0 = this$0;
        this.val$dialog = val$dialog;
        this.val$message = val$message;
        this.val$args = val$args;
    }
    
    @Override
    public void windowOpened(final WindowEvent e) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aconst_null    
        //     5: invokespecial   chrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$NativeModalComponent.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIlIlll;)V
        //     8: astore_2        /* nativeModalComponent */
        //     9: aload_0         /* this */
        //    10: getfield        chrriis/dj/nativeswing/swtimpl/core/lIlIlll.val$dialog:Ljavax/swing/JDialog;
        //    13: invokevirtual   javax/swing/JDialog.getContentPane:()Ljava/awt/Container;
        //    16: aload_2         /* nativeModalComponent */
        //    17: new             Ljava/util/HashMap;
        //    20: dup            
        //    21: invokespecial   java/util/HashMap.<init>:()V
        //    24: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$NativeModalComponent.createEmbeddableComponent:(Ljava/util/Map;)Ljava/awt/Component;
        //    27: ldc             "Center"
        //    29: invokevirtual   java/awt/Container.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //    32: aload_2         /* nativeModalComponent */
        //    33: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$NativeModalComponent.initializeNativePeer:()V
        //    36: new             Lchrriis/dj/nativeswing/swtimpl/core/lIlIIIl;
        //    39: dup            
        //    40: aload_0         /* this */
        //    41: ldc             "Modal dialog handler"
        //    43: aload_2         /* nativeModalComponent */
        //    44: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIlIIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIlIlll;Ljava/lang/String;Lchrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$NativeModalComponent;)V
        //    47: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/lIlIIIl.start:()V
        //    50: return         
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
