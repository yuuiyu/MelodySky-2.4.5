//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.common.*;
import javax.swing.*;
import java.util.*;
import chrriis.dj.nativeswing.swtimpl.*;
import java.awt.event.*;
import java.awt.*;
import org.eclipse.swt.widgets.*;

public abstract class NativeModalDialogHandler
{
    public void showModalDialog(final Component component, final ControlCommandMessage message, final Object... args) {
        final Window window = (Window)((component instanceof Window) ? component : SwingUtilities.getWindowAncestor(component));
        JDialog dialog;
        if (Utils.IS_JAVA_6_OR_GREATER) {
            dialog = new JDialog(window, Dialog.ModalityType.APPLICATION_MODAL);
        }
        else if (window instanceof Dialog) {
            dialog = new JDialog((Dialog)window, true);
        }
        else {
            dialog = new JDialog((Frame)window, true);
        }
        if (Utils.IS_MAC) {
            final NativeModalComponent nativeModalComponent = new NativeModalComponent((lIlIlll)null);
            dialog.getContentPane().add(nativeModalComponent.createEmbeddableComponent(new HashMap<Object, Object>()), "Center");
            nativeModalComponent.initializeNativePeer();
            this.processResult(message.syncExec((NativeComponent)nativeModalComponent, args));
            dialog.dispose();
            return;
        }
        dialog.setUndecorated(true);
        dialog.setSize(0, 0);
        if (Utils.IS_WINDOWS) {
            final Point locationOnScreen;
            final Point location = locationOnScreen = component.getLocationOnScreen();
            locationOnScreen.x += component.getWidth() / 2 - 280;
            final Point point = location;
            point.y += component.getHeight() / 2 - 200;
            dialog.setLocation(location);
        }
        else {
            dialog.setLocationRelativeTo(window);
        }
        MouseEvent mouseEvent;
        if (Utils.IS_JAVA_6_OR_GREATER) {
            mouseEvent = new MouseEvent(window, 503, System.currentTimeMillis(), 0, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, false, 0);
        }
        else {
            mouseEvent = new MouseEvent(window, 503, System.currentTimeMillis(), 0, Integer.MIN_VALUE, Integer.MIN_VALUE, 0, false, 0);
        }
        window.dispatchEvent(mouseEvent);
        dialog.addWindowListener((WindowListener)new lIlIlll(this, dialog, message, args));
        dialog.setVisible(true);
    }
    
    protected abstract void processResult(final Object p0);
    
    private static class NativeModalComponent extends SWTNativeComponent
    {
        protected static Control createControl(final Composite parent, final Object[] parameters) {
            return (Control)new Composite(parent, 0);
        }
        
        @Override
        protected Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
            return super.createEmbeddableComponent(optionMap);
        }
    }
    
    private static class CMN_openDialog extends ControlCommandMessage
    {
        private transient volatile Object result;
        
        public Object run(final Object[] args) throws Exception {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$CMN_openDialog.getControl:()Lorg/eclipse/swt/widgets/Control;
            //     4: astore_2        /* control */
            //     5: aload_2         /* control */
            //     6: invokevirtual   org/eclipse/swt/widgets/Control.isDisposed:()Z
            //     9: ifeq            14
            //    12: aconst_null    
            //    13: areturn        
            //    14: aload_2         /* control */
            //    15: invokevirtual   org/eclipse/swt/widgets/Control.getDisplay:()Lorg/eclipse/swt/widgets/Display;
            //    18: astore_3        /* display */
            //    19: aload_3         /* display */
            //    20: invokevirtual   org/eclipse/swt/widgets/Display.getThread:()Ljava/lang/Thread;
            //    23: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
            //    26: if_acmpeq       61
            //    29: aload_3         /* display */
            //    30: new             Lchrriis/dj/nativeswing/swtimpl/core/llllIlI;
            //    33: dup            
            //    34: aload_0         /* this */
            //    35: aload_1         /* args */
            //    36: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llllIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$CMN_openDialog;[Ljava/lang/Object;)V
            //    39: invokevirtual   org/eclipse/swt/widgets/Display.syncExec:(Ljava/lang/Runnable;)V
            //    42: goto            56
            //    45: astore          e
            //    47: aload           e
            //    49: invokevirtual   java/lang/RuntimeException.getCause:()Ljava/lang/Throwable;
            //    52: checkcast       Ljava/lang/Exception;
            //    55: athrow         
            //    56: aload_0         /* this */
            //    57: getfield        chrriis/dj/nativeswing/swtimpl/core/NativeModalDialogHandler$CMN_openDialog.result:Ljava/lang/Object;
            //    60: areturn        
            //    61: aload_1         /* args */
            //    62: iconst_0       
            //    63: aaload         
            //    64: checkcast       Lchrriis/dj/nativeswing/swtimpl/core/ControlCommandMessage;
            //    67: astore          commandMessage
            //    69: aload           commandMessage
            //    71: aload_2         /* control */
            //    72: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/ControlCommandMessage.setControl:(Lorg/eclipse/swt/widgets/Control;)V
            //    75: aload           commandMessage
            //    77: aload_1         /* args */
            //    78: iconst_1       
            //    79: aaload         
            //    80: checkcast       [Ljava/lang/Object;
            //    83: checkcast       [Ljava/lang/Object;
            //    86: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/ControlCommandMessage.run:([Ljava/lang/Object;)Ljava/lang/Object;
            //    89: areturn        
            //    Exceptions:
            //  throws java.lang.Exception
            //    StackMapTable: 00 04 FC 00 0E 07 00 20 FF 00 1E 00 04 07 00 02 07 00 3C 07 00 20 07 00 2A 00 01 07 00 1A 0A 04
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                        
            //  -----  -----  -----  -----  ----------------------------
            //  29     42     45     56     Ljava/lang/RuntimeException;
            // 
            // The error that occurred was:
            // 
            // java.lang.NullPointerException
            // 
            throw new IllegalStateException("An error occurred while decompiling this method.");
        }
    }
}
