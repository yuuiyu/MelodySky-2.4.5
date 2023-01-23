//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.*;
import javax.swing.event.*;
import chrriis.dj.nativeswing.jna.platform.*;
import java.awt.peer.*;
import java.awt.image.*;
import org.eclipse.swt.awt.*;
import com.sun.jna.*;
import chrriis.dj.nativeswing.common.*;
import java.awt.geom.*;
import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import org.eclipse.swt.*;
import java.awt.event.*;
import java.lang.reflect.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import chrriis.dj.nativeswing.swtimpl.*;
import java.lang.ref.*;
import java.util.concurrent.atomic.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import java.awt.dnd.*;

public abstract class SWTNativeComponent extends NativeComponent
{
    private static final boolean IS_PRINTING_FAILED_MESSAGES;
    private NativeComponentWrapper nativeComponentWrapper;
    private volatile List<CommandMessage> initializationCommandMessageList;
    private int componentID;
    private volatile Thread resizeThread;
    private volatile Thread repaintThread;
    private int additionCount;
    private boolean isForcingInitialization;
    private Method getAWTHandleMethod;
    private NativeInterfaceListener nativeInterfaceListener;
    private boolean isNativePeerValid;
    private String invalidNativePeerText;
    private boolean isNativePeerInitialized;
    private boolean isNativePeerDisposed;
    private boolean isControlParentEnabled;
    private boolean isStoredInHiddenParent;
    protected EventListenerList listenerList;
    
    protected static ObjectRegistry getControlRegistry() {
        return NativeComponent.getControlRegistry();
    }
    
    protected static ObjectRegistry getNativeComponentRegistry() {
        return NativeComponent.getNativeComponentRegistry();
    }
    
    @Override
    public void runInSequence(final Runnable runnable) {
        this.runSync(new CMLocal_runInSequence((lIIllIl)null), runnable);
    }
    
    @Override
    public Object runSync(final CommandMessage commandMessage, final Object... args) {
        final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
        if (nativeInterface.isAlive()) {
            nativeInterface.checkUIThread(false);
        }
        if (commandMessage instanceof ControlCommandMessage) {
            ((ControlCommandMessage)commandMessage).setNativeComponent((NativeComponent)this);
        }
        if (this.initializationCommandMessageList != null) {
            SWTNativeInterface.setMessageSyncExec((Message)commandMessage, true);
            SWTNativeInterface.setMessageArgs(commandMessage, args);
            this.initializationCommandMessageList.add(commandMessage);
            return null;
        }
        if (!this.isNativePeerValid()) {
            SWTNativeInterface.setMessageArgs(commandMessage, args);
            this.printFailedInvocation((Message)commandMessage);
            return null;
        }
        try {
            return commandMessage.syncExec(true, args);
        }
        catch (RuntimeException e) {
            this.processFailedMessageException(e, commandMessage);
            return null;
        }
    }
    
    @Override
    public void runAsync(final CommandMessage commandMessage, final Object... args) {
        final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
        if (nativeInterface.isAlive()) {
            nativeInterface.checkUIThread(false);
        }
        if (commandMessage instanceof ControlCommandMessage) {
            ((ControlCommandMessage)commandMessage).setNativeComponent((NativeComponent)this);
        }
        if (this.initializationCommandMessageList != null) {
            SWTNativeInterface.setMessageSyncExec((Message)commandMessage, false);
            SWTNativeInterface.setMessageArgs(commandMessage, args);
            this.initializationCommandMessageList.add(commandMessage);
        }
        else if (!this.isNativePeerValid()) {
            SWTNativeInterface.setMessageArgs(commandMessage, args);
            this.printFailedInvocation((Message)commandMessage);
        }
        else {
            commandMessage.asyncExec(true, args);
        }
    }
    
    private void printFailedInvocation(final Message message) {
        if (SWTNativeComponent.IS_PRINTING_FAILED_MESSAGES) {
            System.err.println("Failed message to " + this.getComponentDescription() + ": " + message);
        }
    }
    
    static Control[] getControls() {
        final List<Control> controlList = new ArrayList<Control>();
        final ObjectRegistry controlRegistry = getControlRegistry();
        for (final int instanceID : controlRegistry.getInstanceIDs()) {
            final Control nativeComponent = (Control)controlRegistry.get(instanceID);
            if (nativeComponent != null) {
                controlList.add(nativeComponent);
            }
        }
        return controlList.toArray(new Control[0]);
    }
    
    @Override
    protected int getComponentID() {
        return this.componentID;
    }
    
    public SWTNativeComponent() {
        this.nativeComponentWrapper = (NativeComponentWrapper)new lIIllIl(this, (Component)this);
        this.initializationCommandMessageList = new ArrayList<CommandMessage>();
        this.isControlParentEnabled = true;
        this.listenerList = new EventListenerList();
        activateDragAndDrop();
        this.componentID = getNativeComponentRegistry().add((Object)this);
        this.addFocusListener((FocusListener)new lIIIIll(this));
        this.enableEvents(8L);
        this.setFocusable(true);
    }
    
    @Override
    protected void processKeyEvent(final KeyEvent e) {
        final KeyEvent ke = e;
        if (!(ke instanceof CKeyEvent)) {
            final ComponentPeer peer = WindowUtils.getPeer((Component)this);
            if (peer != null) {
                peer.handleEvent(e);
            }
            e.consume();
            return;
        }
        super.processKeyEvent(ke);
    }
    
    @Override
    public void reshape(final int x, final int y, final int width, final int height) {
        class lIllIlI extends Thread
        {
            final /* synthetic */ SWTNativeComponent this$0;
            
            lIllIlI(final SWTNativeComponent this$0, final String x0) {
                this.this$0 = this$0;
                super(x0);
            }
            
            @Override
            public void run() {
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex) {}
                this.this$0.applyPendingReshape();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //     4: ifnonnull       46
        //     7: iload_3         /* width */
        //     8: aload_0         /* this */
        //     9: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getWidth:()I
        //    12: if_icmpne       24
        //    15: iload           height
        //    17: aload_0         /* this */
        //    18: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getHeight:()I
        //    21: if_icmpeq       46
        //    24: aload_0         /* this */
        //    25: new             Lchrriis/dj/nativeswing/swtimpl/core/lIllIlI;
        //    28: dup            
        //    29: aload_0         /* this */
        //    30: ldc_w           "NativeSwing Resize"
        //    33: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIllIlI.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent;Ljava/lang/String;)V
        //    36: putfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //    39: aload_0         /* this */
        //    40: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //    43: invokevirtual   java/lang/Thread.start:()V
        //    46: aload_0         /* this */
        //    47: iload_1         /* x */
        //    48: iload_2         /* y */
        //    49: iload_3         /* width */
        //    50: iload           height
        //    52: invokespecial   chrriis/dj/nativeswing/swtimpl/NativeComponent.reshape:(IIII)V
        //    55: return         
        //    StackMapTable: 00 02 18 15
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void applyPendingReshape() {
        class lllIlll implements Runnable
        {
            final /* synthetic */ SWTNativeComponent this$0;
            
            lllIlll(final SWTNativeComponent this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                this.this$0.applyPendingReshape();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //     4: ifnonnull       8
        //     7: return         
        //     8: invokestatic    javax/swing/SwingUtilities.isEventDispatchThread:()Z
        //    11: ifne            26
        //    14: new             Lchrriis/dj/nativeswing/swtimpl/core/lllIlll;
        //    17: dup            
        //    18: aload_0         /* this */
        //    19: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent;)V
        //    22: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    25: return         
        //    26: aload_0         /* this */
        //    27: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //    30: ifnonnull       34
        //    33: return         
        //    34: aload_0         /* this */
        //    35: aconst_null    
        //    36: putfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.resizeThread:Ljava/lang/Thread;
        //    39: aload_0         /* this */
        //    40: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.isNativePeerValid:()Z
        //    43: ifeq            127
        //    46: aload_0         /* this */
        //    47: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getWidth:()I
        //    50: istore_1        /* width */
        //    51: aload_0         /* this */
        //    52: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getHeight:()I
        //    55: istore_2        /* height */
        //    56: aload_0         /* this */
        //    57: invokestatic    chrriis/dj/nativeswing/common/UIUtils.getScaledFactor:(Ljava/awt/Component;)Ljava/awt/geom/Point2D$Double;
        //    60: astore_3        /* scaledFactor */
        //    61: aload_3         /* scaledFactor */
        //    62: getfield        java/awt/geom/Point2D$Double.x:D
        //    65: dconst_1       
        //    66: dcmpl          
        //    67: ifne            79
        //    70: aload_3         /* scaledFactor */
        //    71: getfield        java/awt/geom/Point2D$Double.y:D
        //    74: dconst_1       
        //    75: dcmpl          
        //    76: ifeq            97
        //    79: iload_1         /* width */
        //    80: i2d            
        //    81: aload_3         /* scaledFactor */
        //    82: getfield        java/awt/geom/Point2D$Double.x:D
        //    85: dmul           
        //    86: d2i            
        //    87: istore_1        /* width */
        //    88: iload_2         /* height */
        //    89: i2d            
        //    90: aload_3         /* scaledFactor */
        //    91: getfield        java/awt/geom/Point2D$Double.y:D
        //    94: dmul           
        //    95: d2i            
        //    96: istore_2        /* height */
        //    97: new             Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_reshape;
        //   100: dup            
        //   101: aconst_null    
        //   102: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_reshape.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIIllIl;)V
        //   105: aload_0         /* this */
        //   106: iconst_2       
        //   107: anewarray       Ljava/lang/Object;
        //   110: dup            
        //   111: iconst_0       
        //   112: iload_1         /* width */
        //   113: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   116: aastore        
        //   117: dup            
        //   118: iconst_1       
        //   119: iload_2         /* height */
        //   120: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //   123: aastore        
        //   124: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_reshape.asyncExec:(Lchrriis/dj/nativeswing/swtimpl/NativeComponent;[Ljava/lang/Object;)V
        //   127: return         
        //    StackMapTable: 00 06 08 11 07 FE 00 2C 01 01 07 00 5C 11 F8 00 1D
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void repaintNativeComponent() {
        class lIllIII extends Thread
        {
            final /* synthetic */ SWTNativeComponent this$0;
            
            lIllIII(final SWTNativeComponent this$0, final String x0) {
                this.this$0 = this$0;
                super(x0);
            }
            
            @Override
            public void run() {
                try {
                    Thread.sleep(50L);
                }
                catch (Exception ex) {}
                this.this$0.applyPendingRepaint();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //     4: ifnonnull       43
        //     7: aload_0         /* this */
        //     8: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getWidth:()I
        //    11: ifle            43
        //    14: aload_0         /* this */
        //    15: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getHeight:()I
        //    18: ifle            43
        //    21: aload_0         /* this */
        //    22: new             Lchrriis/dj/nativeswing/swtimpl/core/lIllIII;
        //    25: dup            
        //    26: aload_0         /* this */
        //    27: ldc_w           "NativeSwing Repaint"
        //    30: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIllIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent;Ljava/lang/String;)V
        //    33: putfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //    36: aload_0         /* this */
        //    37: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //    40: invokevirtual   java/lang/Thread.start:()V
        //    43: return         
        //    StackMapTable: 00 01 2B
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    private void applyPendingRepaint() {
        class llIllIl implements Runnable
        {
            final /* synthetic */ SWTNativeComponent this$0;
            
            llIllIl(final SWTNativeComponent this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                this.this$0.applyPendingRepaint();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //     4: ifnonnull       8
        //     7: return         
        //     8: invokestatic    javax/swing/SwingUtilities.isEventDispatchThread:()Z
        //    11: ifne            26
        //    14: new             Lchrriis/dj/nativeswing/swtimpl/core/llIllIl;
        //    17: dup            
        //    18: aload_0         /* this */
        //    19: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIllIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent;)V
        //    22: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    25: return         
        //    26: aload_0         /* this */
        //    27: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //    30: ifnonnull       34
        //    33: return         
        //    34: aload_0         /* this */
        //    35: aconst_null    
        //    36: putfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.repaintThread:Ljava/lang/Thread;
        //    39: aload_0         /* this */
        //    40: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.isNativePeerValid:()Z
        //    43: ifeq            62
        //    46: new             Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_redraw;
        //    49: dup            
        //    50: aconst_null    
        //    51: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_redraw.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIIllIl;)V
        //    54: aload_0         /* this */
        //    55: iconst_0       
        //    56: anewarray       Ljava/lang/Object;
        //    59: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_redraw.asyncExec:(Lchrriis/dj/nativeswing/swtimpl/NativeComponent;[Ljava/lang/Object;)V
        //    62: return         
        //    StackMapTable: 00 04 08 11 07 1B
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    public void repaint() {
        super.repaint();
        this.repaintNativeComponent();
    }
    
    private static Object[] getKeyEventArgs(final org.eclipse.swt.events.KeyEvent keyEvent, final int keyEventType) {
        return new Object[] { keyEventType, keyEvent.stateMask, keyEvent.character, keyEvent.keyCode };
    }
    
    private static Object[] getMouseEventArgs(final Control control, final MouseEvent e, final int mouseEventType) {
        MouseEvent lastEvent = (MouseEvent)control.getData("NS_LastMouseEvent");
        if (lastEvent != null) {
            final Integer lastEventType = (Integer)control.getData("NS_LastMouseEventType");
            if (lastEventType == mouseEventType && mouseEventType != 507 && lastEvent.x == e.x && lastEvent.y == e.y && lastEvent.button == e.button && lastEvent.count == e.count && lastEvent.stateMask == e.stateMask) {
                return null;
            }
        }
        control.setData("NS_LastMouseEvent", (Object)e);
        control.setData("NS_LastMouseEventType", (Object)mouseEventType);
        lastEvent = e;
        return new Object[] { mouseEventType, e.x, e.y, e.button, e.count, e.stateMask, e.display.getCursorLocation() };
    }
    
    private static void configureControl(final Control control, final int componentID) {
        final class llllll extends MouseAdapter
        {
            final /* synthetic */ Control val$control;
            
            llllll(final Control val$control) {
                this.val$control = val$control;
            }
            
            public void mouseDown(final MouseEvent e) {
                final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 501);
                if (mouseEventArgs != null) {
                    new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                }
            }
            
            public void mouseUp(final MouseEvent e) {
                final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 502);
                if (mouseEventArgs != null) {
                    new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                }
            }
        }
        final class lllllIl extends MouseTrackAdapter
        {
            final /* synthetic */ Control val$control;
            
            lllllIl(final Control val$control) {
                this.val$control = val$control;
            }
            
            public void mouseEnter(final MouseEvent e) {
                final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 504);
                if (mouseEventArgs != null) {
                    new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                }
            }
            
            public void mouseExit(final MouseEvent e) {
                final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 505);
                if (mouseEventArgs != null) {
                    new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                }
            }
        }
        final class lIlIllI implements MouseMoveListener
        {
            final /* synthetic */ Control val$control;
            
            lIlIllI(final Control val$control) {
                this.val$control = val$control;
            }
            
            public void mouseMove(final MouseEvent e) {
                if (((long)e.widget.getData("NS_EnabledEventsMask") & 0x20L) != 0x0L) {
                    final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 503);
                    if (mouseEventArgs != null) {
                        new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                    }
                }
            }
        }
        final class llllIIl implements MouseWheelListener
        {
            final /* synthetic */ Control val$control;
            
            llllIIl(final Control val$control) {
                this.val$control = val$control;
            }
            
            public void mouseScrolled(final MouseEvent e) {
                final Object[] mouseEventArgs = getMouseEventArgs(this.val$control, e, 507);
                if (mouseEventArgs != null) {
                    new CMJ_dispatchMouseEvent(null).asyncExec(this.val$control, mouseEventArgs);
                }
            }
        }
        final class lIlllll implements KeyListener
        {
            final /* synthetic */ Control val$control;
            
            lIlllll(final Control val$control) {
                this.val$control = val$control;
            }
            
            public void keyPressed(final org.eclipse.swt.events.KeyEvent e) {
                if ((e.stateMask & 0x40000) != 0x0 && e.keyCode == 9) {
                    e.doit = false;
                }
                new CMJ_dispatchKeyEvent(null).asyncExec(this.val$control, getKeyEventArgs(e, 401));
            }
            
            public void keyReleased(final org.eclipse.swt.events.KeyEvent e) {
                new CMJ_dispatchKeyEvent(null).asyncExec(this.val$control, getKeyEventArgs(e, 402));
                new CMJ_dispatchKeyEvent(null).asyncExec(this.val$control, getKeyEventArgs(e, 400));
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: ldc_w           "NS_ID"
        //     4: iload_1         /* componentID */
        //     5: invokestatic    java/lang/Integer.valueOf:(I)Ljava/lang/Integer;
        //     8: invokevirtual   org/eclipse/swt/widgets/Control.setData:(Ljava/lang/String;Ljava/lang/Object;)V
        //    11: aload_0         /* control */
        //    12: ldc_w           "NS_EnabledEventsMask"
        //    15: lconst_0       
        //    16: invokestatic    java/lang/Long.valueOf:(J)Ljava/lang/Long;
        //    19: invokevirtual   org/eclipse/swt/widgets/Control.setData:(Ljava/lang/String;Ljava/lang/Object;)V
        //    22: aload_0         /* control */
        //    23: new             Lchrriis/dj/nativeswing/swtimpl/core/llllll;
        //    26: dup            
        //    27: aload_0         /* control */
        //    28: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llllll.<init>:(Lorg/eclipse/swt/widgets/Control;)V
        //    31: invokevirtual   org/eclipse/swt/widgets/Control.addMouseListener:(Lorg/eclipse/swt/events/MouseListener;)V
        //    34: aload_0         /* control */
        //    35: new             Lchrriis/dj/nativeswing/swtimpl/core/lllllIl;
        //    38: dup            
        //    39: aload_0         /* control */
        //    40: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllllIl.<init>:(Lorg/eclipse/swt/widgets/Control;)V
        //    43: invokevirtual   org/eclipse/swt/widgets/Control.addMouseTrackListener:(Lorg/eclipse/swt/events/MouseTrackListener;)V
        //    46: aload_0         /* control */
        //    47: new             Lchrriis/dj/nativeswing/swtimpl/core/lIlIllI;
        //    50: dup            
        //    51: aload_0         /* control */
        //    52: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIlIllI.<init>:(Lorg/eclipse/swt/widgets/Control;)V
        //    55: invokevirtual   org/eclipse/swt/widgets/Control.addMouseMoveListener:(Lorg/eclipse/swt/events/MouseMoveListener;)V
        //    58: aload_0         /* control */
        //    59: new             Lchrriis/dj/nativeswing/swtimpl/core/llllIIl;
        //    62: dup            
        //    63: aload_0         /* control */
        //    64: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llllIIl.<init>:(Lorg/eclipse/swt/widgets/Control;)V
        //    67: invokevirtual   org/eclipse/swt/widgets/Control.addMouseWheelListener:(Lorg/eclipse/swt/events/MouseWheelListener;)V
        //    70: aload_0         /* control */
        //    71: new             Lchrriis/dj/nativeswing/swtimpl/core/lIlllll;
        //    74: dup            
        //    75: aload_0         /* control */
        //    76: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIlllll.<init>:(Lorg/eclipse/swt/widgets/Control;)V
        //    79: invokevirtual   org/eclipse/swt/widgets/Control.addKeyListener:(Lorg/eclipse/swt/events/KeyListener;)V
        //    82: return         
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
    
    @Override
    public synchronized void addMouseMotionListener(final MouseMotionListener listener) {
        if (this.getMouseMotionListeners().length == 0 && listener != null) {
            this.runAsync((CommandMessage)new CMN_setEventsEnabled(null), 32L, true);
        }
        super.addMouseMotionListener(listener);
    }
    
    @Override
    public synchronized void removeMouseMotionListener(final MouseMotionListener listener) {
        super.removeMouseMotionListener(listener);
        if (this.getMouseMotionListeners().length == 0) {
            this.runAsync((CommandMessage)new CMN_setEventsEnabled(null), 32L, false);
        }
    }
    
    @Override
    public void paint(final Graphics g) {
        super.paint(g);
        if (!this.isNativePeerValid()) {
            String text = this.invalidNativePeerText;
            if (text == null) {
                text = "Invalid " + this.getComponentDescription();
            }
            final FontMetrics fm = g.getFontMetrics();
            final BufferedReader r = new BufferedReader(new StringReader(text));
            final int lineHeight = fm.getHeight();
            final int ascent = fm.getAscent();
            try {
                int i = 0;
                String line;
                while ((line = r.readLine()) != null) {
                    g.drawString(line, 5, ascent + 5 + lineHeight * i);
                    ++i;
                }
            }
            catch (Exception ex) {}
        }
        else {
            this.nativeComponentWrapper.paintBackBuffer(g, false);
        }
    }
    
    @Override
    public void print(final Graphics g) {
        final BufferedImage image = new BufferedImage(this.getWidth(), this.getHeight(), 2);
        this.paintComponent(image);
        g.drawImage(image, 0, 0, null);
        g.dispose();
        image.flush();
    }
    
    private void throwDuplicateCreationException() {
        this.isNativePeerValid = false;
        this.invalidNativePeerText = "Failed to create " + this.getComponentDescription() + "\n\nReason:\nThe native component cannot be removed then re-added to a component hierarchy.";
        this.repaint();
        throw new IllegalStateException("The native component cannot be removed then re-added to a component hierarchy! To allow such reparenting, the component must be created with the \"destroyOnFinalization\" constructor option.");
    }
    
    @Override
    public void addNotify() {
        class llllIll implements Runnable
        {
            final /* synthetic */ SWTNativeComponent this$0;
            
            llllIll(final SWTNativeComponent this$0) {
                this.this$0 = this$0;
            }
            
            @Override
            public void run() {
                if (this.this$0.isNativePeerDisposed && this.this$0.additionCount > 1) {
                    this.this$0.throwDuplicateCreationException();
                }
                if (!this.this$0.isNativePeerInitialized) {
                    this.this$0.createNativePeer();
                }
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   chrriis/dj/nativeswing/swtimpl/NativeComponent.addNotify:()V
        //     4: aload_0         /* this */
        //     5: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.isStoredInHiddenParent:Z
        //     8: ifeq            12
        //    11: return         
        //    12: aload_0         /* this */
        //    13: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.isForcingInitialization:Z
        //    16: ifeq            20
        //    19: return         
        //    20: aload_0         /* this */
        //    21: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.isNativePeerDisposed:Z
        //    24: ifeq            31
        //    27: aload_0         /* this */
        //    28: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.throwDuplicateCreationException:()V
        //    31: aload_0         /* this */
        //    32: dup            
        //    33: getfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.additionCount:I
        //    36: iconst_1       
        //    37: iadd           
        //    38: putfield        chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.additionCount:I
        //    41: new             Lchrriis/dj/nativeswing/swtimpl/core/llllIll;
        //    44: dup            
        //    45: aload_0         /* this */
        //    46: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llllIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent;)V
        //    49: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    52: return         
        //    StackMapTable: 00 03 0C 07 0A
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
    
    @Override
    public void initializeNativePeer() {
        final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
        if (nativeInterface.isAlive()) {
            nativeInterface.checkUIThread(false);
        }
        final Window windowAncestor = SwingUtilities.getWindowAncestor(this);
        if (windowAncestor == null) {
            throw new IllegalStateException("This method can only be called when the component has a Window ancestor!");
        }
        if (this.isNativePeerDisposed) {
            this.throwDuplicateCreationException();
        }
        if (!this.isNativePeerInitialized) {
            this.isForcingInitialization = true;
            try {
                if (Utils.IS_MAC) {
                    class 1MacWindowInitMessage extends CommandMessage implements NoSerializationTestMessage
                    {
                        public Object run(final Object[] args) throws Exception {
                            windowAncestor.addNotify();
                            return null;
                        }
                    }
                    new 1MacWindowInitMessage().syncSend(true);
                }
                else {
                    windowAncestor.addNotify();
                }
                this.createNativePeer();
            }
            finally {
                this.isForcingInitialization = false;
            }
        }
    }
    
    private Object getHandle() {
        if (SWTNativeInterface.getInstance().isInProcess_()) {
            return this;
        }
        try {
            if (this.getAWTHandleMethod == null) {
                final Method loadLibraryMethod = SWT_AWT.class.getDeclaredMethod("loadLibrary", (Class<?>[])new Class[0]);
                loadLibraryMethod.setAccessible(true);
                loadLibraryMethod.invoke(null, new Object[0]);
                (this.getAWTHandleMethod = SWT_AWT.class.getDeclaredMethod("getAWTHandle", Canvas.class)).setAccessible(true);
            }
            return this.getAWTHandleMethod.invoke(null, this);
        }
        catch (Exception e) {
            try {
                if (this.isDisplayable()) {
                    return Native.getComponentID((Component)this);
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            return 0;
        }
    }
    
    @Override
    protected Object[] getNativePeerCreationParameters() {
        return null;
    }
    
    private void createNativePeer() {
        final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
        final boolean isInterfaceAlive = nativeInterface.isAlive();
        if (isInterfaceAlive) {
            nativeInterface.checkUIThread(false);
        }
        if (this.initializationCommandMessageList == null) {
            this.throwDuplicateCreationException();
        }
        final List<CommandMessage> initializationCommandMessageList_ = this.initializationCommandMessageList;
        this.initializationCommandMessageList = null;
        if (this.isNativePeerDisposed) {
            this.invalidNativePeerText = "Failed to create " + this.getComponentDescription() + "\n\nReason:\nThe native peer was disposed!";
        }
        else {
            this.isNativePeerInitialized = true;
            if (isInterfaceAlive) {
                NativeInterface.addNativeInterfaceListener(this.nativeInterfaceListener = new NNativeInterfaceListener(this));
                this.isNativePeerValid = true;
                try {
                    this.runSync(new CMN_createControl(null), this.componentID, this.getHandle(), this.getClass().getName(), this.getNativePeerCreationParameters());
                }
                catch (Exception e) {
                    this.isNativePeerValid = false;
                    final StringBuilder sb = new StringBuilder();
                    for (Throwable t = e; t != null; t = t.getCause()) {
                        sb.append("    " + t.toString() + "\n");
                    }
                    this.invalidNativePeerText = "Failed to create " + this.getComponentDescription() + "\n\nReason:\n" + sb.toString();
                    e.printStackTrace();
                }
                int width = this.getWidth();
                int height = this.getHeight();
                final Point2D.Double scaledFactor = UIUtils.getScaledFactor((Component)this);
                if (scaledFactor.x != 1.0 || scaledFactor.y != 1.0) {
                    width *= (int)scaledFactor.x;
                    height *= (int)scaledFactor.y;
                }
                new CMN_reshape(null).asyncExec((NativeComponent)this, new Object[] { width, height });
            }
            else {
                this.invalidNativePeerText = "Failed to create " + this.getComponentDescription() + "\n\nReason:\nThe native interface is not open!";
            }
        }
        try {
            Thread.sleep(100L);
        }
        catch (InterruptedException ex) {}
        for (final CommandMessage initCommandMessage : initializationCommandMessageList_) {
            if (!this.isNativePeerValid()) {
                this.printFailedInvocation((Message)initCommandMessage);
            }
            else if (SWTNativeInterface.isMessageSyncExec((Message)initCommandMessage)) {
                try {
                    initCommandMessage.syncSend(true);
                }
                catch (RuntimeException e2) {
                    this.processFailedMessageException(e2, initCommandMessage);
                }
            }
            else {
                initCommandMessage.asyncSend(true);
            }
        }
    }
    
    private void processFailedMessageException(final RuntimeException e, final CommandMessage commandMessage) {
        boolean isCatchingException = false;
        for (Throwable ex = e; ex != null; ex = ex.getCause()) {
            if (ex instanceof ControlCommandMessage.DisposedControlException) {
                isCatchingException = true;
                break;
            }
        }
        if (!isCatchingException && Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_SWALLOWRUNTIMEEXCEPTIONS.get())) {
            e.printStackTrace();
            isCatchingException = true;
        }
        if (isCatchingException) {
            this.printFailedInvocation((Message)commandMessage);
            return;
        }
        throw e;
    }
    
    @Override
    public void removeNotify() {
        if (this.isStoredInHiddenParent) {
            super.removeNotify();
            return;
        }
        this.disposeNativePeer();
        super.removeNotify();
    }
    
    @Override
    protected void disposeNativePeer() {
        if (!this.isNativePeerDisposed) {
            this.isNativePeerDisposed = true;
            if (this.isNativePeerInitialized) {
                NativeInterface.removeNativeInterfaceListener(this.nativeInterfaceListener);
                if (this.isNativePeerValid()) {
                    this.runSync((CommandMessage)new CMN_destroyControl(null), new Object[0]);
                }
            }
            this.invalidateNativePeer("The native component was disposed.");
            getNativeComponentRegistry().remove(this.componentID);
            this.nativeComponentWrapper.disposeNativeComponent();
        }
    }
    
    @Override
    public boolean isNativePeerDisposed() {
        return this.isNativePeerDisposed;
    }
    
    @Override
    public boolean isNativePeerInitialized() {
        return this.isNativePeerInitialized;
    }
    
    @Override
    public boolean isNativePeerValid() {
        return this.isNativePeerValid && SWTNativeInterface.getInstance().isAlive();
    }
    
    private void invalidateNativePeer(final String invalidNativePeerText) {
        if (this.isNativePeerValid) {
            this.isNativePeerValid = false;
            this.invalidNativePeerText = "Invalid " + this.getComponentDescription() + "\n\nReason:\n" + invalidNativePeerText;
            this.repaint();
        }
    }
    
    private String getComponentDescription() {
        return this.getClass().getName() + "[" + this.getComponentID() + "," + this.hashCode() + "]";
    }
    
    @Override
    public String toString() {
        return this.getComponentDescription();
    }
    
    @Override
    protected Component createEmbeddableComponent(final Map<Object, Object> optionMap) {
        return this.nativeComponentWrapper.createEmbeddableComponent((Map)optionMap);
    }
    
    private boolean isControlParentEnabled() {
        return this.isControlParentEnabled;
    }
    
    private void setControlParentEnabled(final boolean isEnabled, final boolean isForcingRepaint) {
        if (isEnabled == this.isControlParentEnabled) {
            return;
        }
        this.isControlParentEnabled = isEnabled;
        if (!this.isNativePeerInitialized() || this.isNativePeerValid()) {
            this.runAsync((CommandMessage)new CMN_setControlParentEnabled(null), isEnabled, isForcingRepaint);
        }
    }
    
    @Override
    public void setEnabled(final boolean isEnabled) {
        super.setEnabled(isEnabled);
        this.runAsync((CommandMessage)new CMN_setEnabled(null), isEnabled);
    }
    
    @Override
    public boolean hasFocus() {
        final boolean hasFocus = super.hasFocus();
        if (!hasFocus && this.isNativePeerValid() && !this.isNativePeerDisposed) {
            return Boolean.TRUE.equals(new CMN_hasFocus(null).syncExec((NativeComponent)this, new Object[0]));
        }
        return hasFocus;
    }
    
    @Override
    public Dimension getPreferredSize() {
        Dimension result = null;
        if (this.isNativePeerValid() && !this.isNativePeerDisposed) {
            result = (Dimension)new CMN_getPreferredSize(null).syncExec((NativeComponent)this, new Object[0]);
        }
        if (result == null) {
            result = super.getPreferredSize();
        }
        return result;
    }
    
    @Override
    public void paintComponent(final BufferedImage image) {
        this.paintComponent(image, null);
    }
    
    @Override
    public void paintComponent(final BufferedImage image, Rectangle[] rectangles) {
        if (image == null || !this.isNativePeerValid() || this.isNativePeerDisposed) {
            return;
        }
        this.applyPendingReshape();
        final int width = Math.min(this.getWidth(), image.getWidth());
        final int height = Math.min(this.getHeight(), image.getHeight());
        if (width <= 0 || height <= 0) {
            return;
        }
        if (rectangles == null) {
            rectangles = new Rectangle[] { new Rectangle(width, height) };
        }
        final Rectangle bounds = new Rectangle(width, height);
        final List<Rectangle> rectangleList = new ArrayList<Rectangle>();
        for (final Rectangle rectangle : rectangles) {
            if (rectangle.intersects(bounds)) {
                rectangleList.add(rectangle.intersection(bounds));
            }
        }
        if (rectangleList.isEmpty()) {
            return;
        }
        rectangles = rectangleList.toArray(new Rectangle[0]);
        try {
            final ServerSocket serverSocket = new ServerSocket();
            serverSocket.setReuseAddress(false);
            String localHostAddress = Utils.getLocalHostAddress();
            if (localHostAddress == null) {
                localHostAddress = "127.0.0.1";
            }
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(localHostAddress), 0));
            final NativeInterfaceListener nativeInterfaceListener = (NativeInterfaceListener)new lIlIlIl(this, serverSocket);
            final CMN_getComponentImage getComponentImage = new CMN_getComponentImage(null);
            NativeInterface.addNativeInterfaceListener(nativeInterfaceListener);
            final AtomicReference<Boolean> isServerSocketToBeClosed = new AtomicReference<Boolean>(true);
            if (Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_USECOMPONENTIMAGECLOSINGTHREAD.get())) {
                new lllIllI(this, "NativeSwing[" + SWTNativeInterface.getInstance().getInterfaceID(false) + "] Component Image Socket Closing", (AtomicReference)isServerSocketToBeClosed, serverSocket).start();
            }
            getComponentImage.asyncExec((NativeComponent)this, new Object[] { serverSocket.getLocalPort(), rectangles, localHostAddress });
            final Socket socket = serverSocket.accept();
            isServerSocketToBeClosed.set(false);
            final byte[] bytes = new byte[3072];
            int count = 0;
            int readCount = 0;
            try {
                final BufferedInputStream in = new BufferedInputStream(socket.getInputStream());
                synchronized (image) {
                    for (final Rectangle rectangle2 : rectangles) {
                        final int[] pixels = new int[rectangle2.width];
                        for (int y = 0; y < rectangle2.height && readCount != -1; ++y) {
                            for (int x = 0; x < rectangle2.width && readCount != -1; ++x) {
                                int itCount = 0;
                                while (readCount != -1 && (readCount == 0 || readCount % 3 != 0)) {
                                    if (itCount++ == 1000) {
                                        readCount = -1;
                                    }
                                    final int c = in.read(bytes, readCount, bytes.length - readCount);
                                    if (c == -1) {
                                        readCount = -1;
                                    }
                                    else {
                                        readCount += c;
                                    }
                                }
                                if (readCount == -1) {
                                    break;
                                }
                                pixels[x] = (0xFF000000 | (0xFF & bytes[count]) << 16 | (0xFF & bytes[count + 1]) << 8 | (0xFF & bytes[count + 2]));
                                count += 3;
                                if (count == readCount) {
                                    count = 0;
                                    readCount = 0;
                                }
                            }
                            if (readCount != -1) {
                                image.setRGB(rectangle2.x, rectangle2.y + y, rectangle2.width, 1, pixels, 0, rectangle2.width);
                            }
                        }
                        if (readCount == -1) {
                            break;
                        }
                    }
                }
                NativeInterface.removeNativeInterfaceListener(nativeInterfaceListener);
                in.close();
                socket.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            serverSocket.close();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
    }
    
    private void storeInHiddenParent() {
        this.isStoredInHiddenParent = true;
        this.runSync((CommandMessage)new CMN_reparentToHiddenShell(null), this.componentID, this.getHandle());
    }
    
    private void restoreFromHiddenParent() {
        if (!this.isDisplayable()) {
            this.isStoredInHiddenParent = false;
            return;
        }
        try {
            this.runSync(new CMN_createControl(null), this.componentID, this.getHandle());
            int width = this.getWidth();
            int height = this.getHeight();
            final Point2D.Double scaledFactor = UIUtils.getScaledFactor((Component)this);
            if (scaledFactor.x != 1.0 || scaledFactor.y != 1.0) {
                width *= (int)scaledFactor.x;
                height *= (int)scaledFactor.y;
            }
            new CMN_reshape(null).asyncExec((NativeComponent)this, new Object[] { width, height });
        }
        catch (Exception e) {
            final StringBuilder sb = new StringBuilder();
            for (Throwable t = e; t != null; t = t.getCause()) {
                sb.append("    " + t.toString() + "\n");
            }
            this.invalidateNativePeer("Failed to reparent " + this.getComponentDescription() + "\n\nReason:\n" + sb.toString());
            e.printStackTrace();
        }
        this.isStoredInHiddenParent = false;
    }
    
    @Override
    public Dimension getMinimumSize() {
        return new Dimension(0, 0);
    }
    
    @Override
    public void createBackBuffer() {
        this.nativeComponentWrapper.createBackBuffer();
    }
    
    @Override
    public boolean hasBackBuffer() {
        return this.nativeComponentWrapper.hasBackBuffer();
    }
    
    @Override
    public void updateBackBufferOnVisibleTranslucentAreas() {
        this.nativeComponentWrapper.updateBackBufferOnVisibleTranslucentAreas();
    }
    
    @Override
    public void updateBackBuffer(final Rectangle[] rectangles) {
        this.nativeComponentWrapper.updateBackBuffer(rectangles);
    }
    
    @Override
    public void destroyBackBuffer() {
        this.nativeComponentWrapper.destroyBackBuffer();
    }
    
    @Override
    public <T extends EventListener> T[] getListeners(final Class<T> listenerType) {
        final T[] result = this.listenerList.getListeners(listenerType);
        if (result.length == 0) {
            return super.getListeners(listenerType);
        }
        return result;
    }
    
    @Override
    public Point getLocationOnScreen() {
        final Container parent = this.getParent();
        if (parent != null) {
            final Point locationOnScreen2;
            final Point locationOnScreen = locationOnScreen2 = parent.getLocationOnScreen();
            locationOnScreen2.x += this.getX();
            final Point point = locationOnScreen;
            point.y += this.getY();
            return locationOnScreen;
        }
        return super.getLocationOnScreen();
    }
    
    static {
        IS_PRINTING_FAILED_MESSAGES = Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_DEBUG_PRINTFAILEDMESSAGES.get());
    }
    
    private class CMLocal_runInSequence extends LocalMessage
    {
        @Override
        public Object run(final Object[] args) {
            ((Runnable)args[0]).run();
            return null;
        }
    }
    
    private static class CMN_reshape extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Shell shell = this.getControl().getShell();
            if (!shell.isDisposed()) {
                shell.setSize((int)args[0], (int)args[1]);
            }
            return null;
        }
    }
    
    private static class CMN_transferFocus extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            this.getControl().traverse(16);
            return null;
        }
    }
    
    private static class CMJ_dispatchMouseEvent extends ControlCommandMessage
    {
        private static int buttonPressedCount;
        
        public Object run(final Object[] args) {
            final NativeComponent nativeComponent = this.getNativeComponent();
            if (nativeComponent == null || !nativeComponent.isShowing()) {
                return null;
            }
            int type = (int)args[0];
            int e_x = (int)args[1];
            int e_y = (int)args[2];
            final Point2D.Double scaledFactor = UIUtils.getScaledFactor((Component)nativeComponent);
            if (scaledFactor.x != 1.0 || scaledFactor.y != 1.0) {
                e_x /= (int)scaledFactor.x;
                e_y /= (int)scaledFactor.y;
            }
            final int e_button = (int)args[3];
            final int e_count = (int)args[4];
            final int e_stateMask = (int)args[5];
            org.eclipse.swt.graphics.Point e_cursorLocation = (org.eclipse.swt.graphics.Point)args[6];
            if (scaledFactor.x != 1.0 || scaledFactor.y != 1.0) {
                e_cursorLocation = new org.eclipse.swt.graphics.Point((int)(e_cursorLocation.x / scaledFactor.x), (int)(e_cursorLocation.y / scaledFactor.y));
            }
            switch (type) {
                case 501: {
                    ++CMJ_dispatchMouseEvent.buttonPressedCount;
                    break;
                }
                case 502: {
                    --CMJ_dispatchMouseEvent.buttonPressedCount;
                    if (CMJ_dispatchMouseEvent.buttonPressedCount < 0) {
                        CMJ_dispatchMouseEvent.buttonPressedCount = 0;
                        break;
                    }
                    break;
                }
            }
            final int button = SWTUtils.translateSWTMouseButton(e_button);
            if (button == 0) {
                switch (type) {
                    case 500:
                    case 501:
                    case 502: {
                        return null;
                    }
                }
            }
            if (CMJ_dispatchMouseEvent.buttonPressedCount != 0 && type == 503) {
                type = 506;
            }
            java.awt.event.MouseEvent me;
            if (Utils.IS_JAVA_6_OR_GREATER) {
                if (type == 507) {
                    me = new MouseWheelEvent(nativeComponent, type, System.currentTimeMillis(), SWTUtils.translateSWTModifiers(e_stateMask), e_x, e_y, e_cursorLocation.x, e_cursorLocation.y, 0, false, 0, Math.abs(e_count), (e_count < 0) ? 1 : -1);
                }
                else {
                    final boolean isPopupTrigger = type == 502 && button == 3;
                    me = new java.awt.event.MouseEvent(nativeComponent, type, System.currentTimeMillis(), SWTUtils.translateSWTModifiers(e_stateMask), e_x, e_y, e_cursorLocation.x, e_cursorLocation.y, e_count, isPopupTrigger, button);
                }
            }
            else if (type == 507) {
                me = new MouseWheelEvent(nativeComponent, type, System.currentTimeMillis(), SWTUtils.translateSWTModifiers(e_stateMask), e_x, e_y, 0, false, 0, Math.abs(e_count), (e_count < 0) ? 1 : -1);
            }
            else {
                final boolean isPopupTrigger = type == 502 && button == 3;
                me = new java.awt.event.MouseEvent(nativeComponent, type, System.currentTimeMillis(), SWTUtils.translateSWTModifiers(e_stateMask), e_x, e_y, e_count, isPopupTrigger, button);
            }
            switch (me.getID()) {
                case 501: {
                    MenuSelectionManager.defaultManager().clearSelectedPath();
                    for (final MouseListener mouseListener : nativeComponent.getMouseListeners()) {
                        mouseListener.mousePressed(me);
                    }
                    break;
                }
                case 502: {
                    for (final MouseListener mouseListener : nativeComponent.getMouseListeners()) {
                        mouseListener.mouseReleased(me);
                    }
                    break;
                }
                case 500: {
                    for (final MouseListener mouseListener : nativeComponent.getMouseListeners()) {
                        mouseListener.mouseClicked(me);
                    }
                    break;
                }
                case 504: {
                    for (final MouseListener mouseListener : nativeComponent.getMouseListeners()) {
                        mouseListener.mouseEntered(me);
                    }
                    break;
                }
                case 505: {
                    for (final MouseListener mouseListener : nativeComponent.getMouseListeners()) {
                        mouseListener.mouseExited(me);
                    }
                    break;
                }
                case 503: {
                    for (final MouseMotionListener mouseListener2 : nativeComponent.getMouseMotionListeners()) {
                        mouseListener2.mouseMoved(me);
                    }
                    break;
                }
                case 506: {
                    for (final MouseMotionListener mouseListener2 : nativeComponent.getMouseMotionListeners()) {
                        mouseListener2.mouseDragged(me);
                    }
                    break;
                }
                case 507: {
                    for (final java.awt.event.MouseWheelListener mouseListener3 : nativeComponent.getMouseWheelListeners()) {
                        mouseListener3.mouseWheelMoved((MouseWheelEvent)me);
                    }
                    break;
                }
            }
            return null;
        }
    }
    
    private static class CMJ_dispatchKeyEvent extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final NativeComponent nativeComponent = this.getNativeComponent();
            if (nativeComponent == null || !nativeComponent.isShowing()) {
                return null;
            }
            final int type = (int)args[0];
            final int e_stateMask = (int)args[1];
            final char e_character = (char)args[2];
            final int e_keyCode = (int)args[3];
            if (e_keyCode == 9) {
                if (type == 401 && (e_stateMask & 0x40000) != 0x0) {
                    final boolean isForward = (e_stateMask & 0x20000) == 0x0;
                    ((SWTNativeComponent)nativeComponent).nativeComponentWrapper.transferFocus(!isForward);
                }
                return null;
            }
            final char character = e_character;
            int keyCode;
            if (type == 400) {
                if (character == '\0') {
                    return null;
                }
                keyCode = 0;
            }
            else {
                keyCode = SWTUtils.translateSWTKeyCode(e_keyCode);
            }
            final KeyEvent ke = new CKeyEvent(nativeComponent, type, System.currentTimeMillis(), SWTUtils.translateSWTModifiers(e_stateMask), keyCode, character);
            nativeComponent.dispatchEvent(ke);
            return null;
        }
    }
    
    private static class CKeyEvent extends KeyEvent
    {
        public CKeyEvent(final Component source, final int id, final long when, final int modifiers, final int keyCode, final char keyChar) {
            super(source, id, when, modifiers, keyCode, keyChar);
        }
        
        @Override
        public String toString() {
            String srcName = null;
            if (this.source instanceof Component) {
                srcName = ((Component)this.source).getName();
            }
            else if (this.source instanceof MenuComponent) {
                srcName = ((MenuComponent)this.source).getName();
            }
            return KeyEvent.class.getName() + "[" + this.paramString() + "] on " + ((srcName != null) ? srcName : this.source);
        }
    }
    
    private static class CMN_createControl extends CommandMessage implements NoSerializationTestMessage
    {
        private static Shell createShell(final Object handle) throws Exception {
            final SWTNativeInterface nativeInterface = SWTNativeInterface.getInstance();
            final Display display = nativeInterface.getDisplay();
            if (nativeInterface.isInProcess_()) {
                final Canvas canvas = (Canvas)handle;
                final ComponentListener[] componentListeners = canvas.getComponentListeners();
                final Shell shell = SWT_AWT.new_Shell(display, canvas);
                for (final ComponentListener componentListener : canvas.getComponentListeners()) {
                    canvas.removeComponentListener(componentListener);
                }
                for (final ComponentListener componentListener : componentListeners) {
                    canvas.addComponentListener(componentListener);
                }
                return shell;
            }
            Method shellCreationMethod = null;
            try {
                shellCreationMethod = Shell.class.getMethod(SWT.getPlatform() + "_new", Display.class, Integer.TYPE);
            }
            catch (Exception ex) {}
            if (shellCreationMethod != null) {
                return (Shell)shellCreationMethod.invoke(null, display, ((Number)handle).intValue());
            }
            try {
                shellCreationMethod = Shell.class.getMethod(SWT.getPlatform() + "_new", Display.class, Long.TYPE);
            }
            catch (Exception ex2) {}
            if (shellCreationMethod != null) {
                return (Shell)shellCreationMethod.invoke(null, display, ((Number)handle).longValue());
            }
            Constructor<Shell> shellConstructor = null;
            try {
                shellConstructor = Shell.class.getConstructor(Display.class, Shell.class, Integer.TYPE, Integer.TYPE);
            }
            catch (Exception ex3) {}
            if (shellConstructor != null) {
                shellConstructor.setAccessible(true);
                return shellConstructor.newInstance(display, null, 8, ((Number)handle).intValue());
            }
            try {
                shellConstructor = Shell.class.getConstructor(Display.class, Shell.class, Integer.TYPE, Long.TYPE);
            }
            catch (Exception ex4) {}
            if (shellConstructor != null) {
                shellConstructor.setAccessible(true);
                return shellConstructor.newInstance(display, null, 8, ((Number)handle).longValue());
            }
            throw new IllegalStateException("Failed to create a Shell!");
        }
        
        public Object run(final Object[] args) throws Exception {
            final ObjectRegistry controlRegistry = SWTNativeComponent.getControlRegistry();
            synchronized (controlRegistry) {
                final int componentID = (int)args[0];
                final Object canvasHandle = args[1];
                final Shell shell = createShell(canvasHandle);
                shell.addControlListener((ControlListener)new lllIIll(this));
                shell.setLayout((Layout)new FillLayout());
                final Composite controlComposite = new Composite((Composite)shell, 0);
                controlComposite.setLayout((Layout)new FillLayout());
                Control control = (Control)controlRegistry.get(componentID);
                if (control != null) {
                    final Shell oldShell = control.getShell();
                    control.setParent(controlComposite);
                    oldShell.dispose();
                }
                else {
                    final String nativeComponentClassName = (String)args[2];
                    final Object nativePeerCreationParameters = args[3];
                    final Method createControlMethod = Class.forName(nativeComponentClassName).getDeclaredMethod("createControl", Composite.class, Object[].class);
                    createControlMethod.setAccessible(true);
                    control = (Control)createControlMethod.invoke(null, controlComposite, nativePeerCreationParameters);
                    if (Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_DEBUG_PRINTCREATION.get())) {
                        System.err.println("Created control: " + componentID);
                    }
                    control.addDisposeListener((DisposeListener)new lIllIIl(this, componentID));
                    controlRegistry.add((Object)control, componentID);
                    configureControl(control, componentID);
                }
                shell.setVisible(true);
                shell.getDisplay().asyncExec((Runnable)new llIllII(this, shell));
            }
            return null;
        }
    }
    
    private static class CMN_setEventsEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final long eventMask = (long)args[0];
            final boolean isEnabled = (boolean)args[1];
            long events = (long)this.getControl().getData("NS_EnabledEventsMask");
            if (isEnabled) {
                events |= eventMask;
            }
            else {
                events &= ~eventMask;
            }
            this.getControl().setData("NS_EnabledEventsMask", (Object)events);
            return null;
        }
    }
    
    private static class NNativeInterfaceListener extends NativeInterfaceAdapter
    {
        protected Reference<SWTNativeComponent> nativeComponent;
        
        protected NNativeInterfaceListener(final SWTNativeComponent nativeComponent) {
            this.nativeComponent = new WeakReference<SWTNativeComponent>(nativeComponent);
        }
        
        @Override
        public void nativeInterfaceClosed() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokestatic    chrriis/dj/nativeswing/swtimpl/NativeInterface.removeNativeInterfaceListener:(Lchrriis/dj/nativeswing/swtimpl/NativeInterfaceListener;)V
            //     4: new             Lchrriis/dj/nativeswing/swtimpl/core/llIIlll;
            //     7: dup            
            //     8: aload_0         /* this */
            //     9: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$NNativeInterfaceListener;)V
            //    12: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
            //    15: return         
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    
    private static class CMN_destroyControl extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            SWTNativeComponent.getControlRegistry().remove(this.getComponentID());
            if (control != null) {
                final Shell shell = control.getShell();
                if (shell != null) {
                    shell.dispose();
                }
                control.dispose();
            }
            return null;
        }
    }
    
    private static class CMN_setControlParentEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            if (control == null || control.isDisposed()) {
                return null;
            }
            control.getShell().setEnabled((boolean)args[0]);
            if (args[1]) {
                final org.eclipse.swt.graphics.Point size2;
                final org.eclipse.swt.graphics.Point size = size2 = control.getParent().getSize();
                --size2.y;
                control.setSize(size);
                final Thread t = (Thread)new llIIIll(this, "Native Swing Repaint fix", control);
                t.setDaemon(true);
                t.start();
            }
            return null;
        }
    }
    
    private static class CMN_setEnabled extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            this.getControl().setEnabled((boolean)args[0]);
            return null;
        }
    }
    
    private static class CMN_hasFocus extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            if (control.isDisposed()) {
                return false;
            }
            if (!NativeInterface.isUIThread(true)) {
                final AtomicBoolean result = new AtomicBoolean();
                control.getDisplay().syncExec((Runnable)new lIlIIlI(this, result, control));
                return result.get();
            }
            return control.isFocusControl();
        }
    }
    
    private static class CMN_getPreferredSize extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            if (control.isDisposed()) {
                return null;
            }
            if (!NativeInterface.isUIThread(true)) {
                final AtomicReference<Dimension> result = new AtomicReference<Dimension>();
                control.getDisplay().syncExec((Runnable)new lIlllIl(this, control, (AtomicReference)result));
                return result.get();
            }
            final org.eclipse.swt.graphics.Point cSize = control.computeSize(-1, -1);
            return new Dimension(cSize.x, cSize.y);
        }
    }
    
    private static class CMN_getComponentImage extends ControlCommandMessage
    {
        protected boolean isValid() {
            return true;
        }
        
        private static boolean printRemoveClip(final Control control, final GC gc) {
            final boolean isFocusControl = control.isFocusControl();
            final org.eclipse.swt.graphics.Rectangle bounds = control.getBounds();
            final Display display = control.getDisplay();
            final Composite oldParent = control.getParent();
            final Shell tmpHiddenParentShell = new Shell(786440);
            final Shell tmpParentShell = new Shell(tmpHiddenParentShell, 786440);
            final org.eclipse.swt.graphics.Point location = display.map(control, (Control)null, control.getLocation());
            tmpParentShell.setLocation(location);
            tmpParentShell.setSize(bounds.width, bounds.height);
            final org.eclipse.swt.widgets.Canvas screenshotCanvas = new org.eclipse.swt.widgets.Canvas((Composite)tmpParentShell, 262144);
            screenshotCanvas.setSize(bounds.width, bounds.height);
            final GC displayGC = new GC((Drawable)display);
            final org.eclipse.swt.graphics.Image screenshot = new org.eclipse.swt.graphics.Image((Device)display, bounds.width, bounds.height);
            displayGC.copyArea(screenshot, location.x, location.y);
            displayGC.dispose();
            final PaintListener paintListener = (PaintListener)new lIIlIll(screenshot);
            tmpParentShell.addPaintListener(paintListener);
            screenshotCanvas.addPaintListener(paintListener);
            oldParent.addPaintListener(paintListener);
            final org.eclipse.swt.widgets.Canvas controlReplacementCanvas = new org.eclipse.swt.widgets.Canvas(oldParent, 262144);
            controlReplacementCanvas.setSize(bounds.width, bounds.height);
            controlReplacementCanvas.addPaintListener(paintListener);
            control.setRedraw(false);
            oldParent.setRedraw(false);
            control.setParent((Composite)tmpParentShell);
            control.setLocation(0, 0);
            control.moveBelow((Control)screenshotCanvas);
            tmpParentShell.setVisible(true);
            final boolean result = control.print(gc);
            if (oldParent.isDisposed()) {
                control.dispose();
            }
            else {
                control.setParent(oldParent);
                if (isFocusControl && !control.isFocusControl()) {
                    control.setFocus();
                }
                control.setLocation(bounds.x, bounds.y);
                control.moveAbove((Control)controlReplacementCanvas);
                controlReplacementCanvas.dispose();
                oldParent.removePaintListener(paintListener);
                tmpParentShell.dispose();
                tmpHiddenParentShell.dispose();
                oldParent.setRedraw(true);
                control.setRedraw(true);
                control.redraw();
                control.update();
                screenshot.dispose();
            }
            return result;
        }
        
        private ImageData getImageData(final Control control, final Region region) {
            if (control.isDisposed()) {
                return null;
            }
            final org.eclipse.swt.graphics.Point size = control.getSize();
            if (size.x <= 0 || size.y <= 0) {
                return null;
            }
            final org.eclipse.swt.graphics.Rectangle regionBounds = region.getBounds();
            final Display display = control.getDisplay();
            final org.eclipse.swt.graphics.Image image = new org.eclipse.swt.graphics.Image((Device)display, regionBounds.x + regionBounds.width, regionBounds.y + regionBounds.height);
            final GC gc = new GC((Drawable)image);
            gc.setClipping(region);
            if (Boolean.parseBoolean(NSSystemPropertySWT.COMPONENTS_PRINTINGHACK.get())) {
                printRemoveClip(control, gc);
            }
            else if (Utils.IS_WINDOWS) {
                final org.eclipse.swt.graphics.Rectangle bounds = control.getBounds();
                final boolean isFocusControl = control.isFocusControl();
                final Composite oldParent = control.getParent();
                control.setRedraw(false);
                oldParent.setRedraw(false);
                control.print(gc);
                if (isFocusControl && !control.isFocusControl()) {
                    control.setFocus();
                }
                control.setLocation(bounds.x, bounds.y);
                oldParent.setRedraw(true);
                control.setRedraw(true);
                control.redraw(0, 0, bounds.width, bounds.height, true);
                control.update();
            }
            else {
                control.print(gc);
            }
            gc.dispose();
            final ImageData imageData = image.getImageData();
            image.dispose();
            return imageData;
        }
        
        public Object run(final Object[] args) throws Exception {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: iconst_0       
            //     2: aaload         
            //     3: checkcast       Ljava/lang/Integer;
            //     6: invokevirtual   java/lang/Integer.intValue:()I
            //     9: istore_2        /* port */
            //    10: aload_1         /* args */
            //    11: iconst_1       
            //    12: aaload         
            //    13: checkcast       [Ljava/awt/Rectangle;
            //    16: checkcast       [Ljava/awt/Rectangle;
            //    19: astore_3        /* rectangles */
            //    20: aload_1         /* args */
            //    21: iconst_2       
            //    22: aaload         
            //    23: checkcast       Ljava/lang/String;
            //    26: astore          hostAddress
            //    28: aload_0         /* this */
            //    29: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_getComponentImage.getControl:()Lorg/eclipse/swt/widgets/Control;
            //    32: astore          control
            //    34: new             Lorg/eclipse/swt/graphics/Region;
            //    37: dup            
            //    38: invokespecial   org/eclipse/swt/graphics/Region.<init>:()V
            //    41: astore          region
            //    43: aload_3         /* rectangles */
            //    44: astore          8
            //    46: aload           8
            //    48: arraylength    
            //    49: istore          9
            //    51: iconst_0       
            //    52: istore          10
            //    54: iload           10
            //    56: iload           9
            //    58: if_icmpge       99
            //    61: aload           8
            //    63: iload           10
            //    65: aaload         
            //    66: astore          rectangle
            //    68: aload           region
            //    70: aload           rectangle
            //    72: getfield        java/awt/Rectangle.x:I
            //    75: aload           rectangle
            //    77: getfield        java/awt/Rectangle.y:I
            //    80: aload           rectangle
            //    82: getfield        java/awt/Rectangle.width:I
            //    85: aload           rectangle
            //    87: getfield        java/awt/Rectangle.height:I
            //    90: invokevirtual   org/eclipse/swt/graphics/Region.add:(IIII)V
            //    93: iinc            10, 1
            //    96: goto            54
            //    99: iconst_1       
            //   100: invokestatic    chrriis/dj/nativeswing/swtimpl/NativeInterface.isUIThread:(Z)Z
            //   103: ifne            274
            //   106: new             Ljava/util/concurrent/atomic/AtomicReference;
            //   109: dup            
            //   110: invokespecial   java/util/concurrent/atomic/AtomicReference.<init>:()V
            //   113: astore          exception
            //   115: new             Ljava/util/concurrent/atomic/AtomicReference;
            //   118: dup            
            //   119: invokespecial   java/util/concurrent/atomic/AtomicReference.<init>:()V
            //   122: astore          result
            //   124: aload           control
            //   126: ifnull          137
            //   129: aload           control
            //   131: invokevirtual   org/eclipse/swt/widgets/Control.isDisposed:()Z
            //   134: ifeq            152
            //   137: new             Ljava/net/Socket;
            //   140: dup            
            //   141: aload           hostAddress
            //   143: iload_2         /* port */
            //   144: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
            //   147: invokevirtual   java/net/Socket.close:()V
            //   150: aconst_null    
            //   151: areturn        
            //   152: new             Ljava/util/concurrent/atomic/AtomicReference;
            //   155: dup            
            //   156: iconst_0       
            //   157: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
            //   160: invokespecial   java/util/concurrent/atomic/AtomicReference.<init>:(Ljava/lang/Object;)V
            //   163: astore          isSocketClosed
            //   165: aload           control
            //   167: invokevirtual   org/eclipse/swt/widgets/Control.getDisplay:()Lorg/eclipse/swt/widgets/Display;
            //   170: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIIlII;
            //   173: dup            
            //   174: aload_0         /* this */
            //   175: aload           control
            //   177: aload           hostAddress
            //   179: iload_2         /* port */
            //   180: aload           isSocketClosed
            //   182: aload           result
            //   184: aload           region
            //   186: aload           exception
            //   188: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIIlII.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_getComponentImage;Lorg/eclipse/swt/widgets/Control;Ljava/lang/String;ILjava/util/concurrent/atomic/AtomicReference;Ljava/util/concurrent/atomic/AtomicReference;Lorg/eclipse/swt/graphics/Region;Ljava/util/concurrent/atomic/AtomicReference;)V
            //   191: invokevirtual   org/eclipse/swt/widgets/Display.syncExec:(Ljava/lang/Runnable;)V
            //   194: aload           isSocketClosed
            //   196: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //   199: checkcast       Ljava/lang/Boolean;
            //   202: invokevirtual   java/lang/Boolean.booleanValue:()Z
            //   205: ifne            231
            //   208: aload           control
            //   210: invokevirtual   org/eclipse/swt/widgets/Control.isDisposed:()Z
            //   213: ifeq            231
            //   216: new             Ljava/net/Socket;
            //   219: dup            
            //   220: aload           hostAddress
            //   222: iload_2         /* port */
            //   223: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
            //   226: invokevirtual   java/net/Socket.close:()V
            //   229: aconst_null    
            //   230: areturn        
            //   231: aload           exception
            //   233: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //   236: ifnull          261
            //   239: new             Ljava/net/Socket;
            //   242: dup            
            //   243: aload           hostAddress
            //   245: iload_2         /* port */
            //   246: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
            //   249: invokevirtual   java/net/Socket.close:()V
            //   252: aload           exception
            //   254: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //   257: checkcast       Ljava/lang/Exception;
            //   260: athrow         
            //   261: aload           result
            //   263: invokevirtual   java/util/concurrent/atomic/AtomicReference.get:()Ljava/lang/Object;
            //   266: checkcast       Lorg/eclipse/swt/graphics/ImageData;
            //   269: astore          imageData
            //   271: goto            284
            //   274: aload_0         /* this */
            //   275: aload           control
            //   277: aload           region
            //   279: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_getComponentImage.getImageData:(Lorg/eclipse/swt/widgets/Control;Lorg/eclipse/swt/graphics/Region;)Lorg/eclipse/swt/graphics/ImageData;
            //   282: astore          imageData
            //   284: aload           region
            //   286: invokevirtual   org/eclipse/swt/graphics/Region.dispose:()V
            //   289: aload           imageData
            //   291: ifnonnull       309
            //   294: new             Ljava/net/Socket;
            //   297: dup            
            //   298: aload           hostAddress
            //   300: iload_2         /* port */
            //   301: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
            //   304: invokevirtual   java/net/Socket.close:()V
            //   307: aconst_null    
            //   308: areturn        
            //   309: aload_0         /* this */
            //   310: aload           hostAddress
            //   312: iload_2         /* port */
            //   313: aload           imageData
            //   315: aload_3         /* rectangles */
            //   316: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_getComponentImage.sendImageData:(Ljava/lang/String;ILorg/eclipse/swt/graphics/ImageData;[Ljava/awt/Rectangle;)V
            //   319: aconst_null    
            //   320: areturn        
            //    Exceptions:
            //  throws java.lang.Exception
            //    StackMapTable: 00 09 FF 00 36 00 0B 07 00 02 07 01 07 01 07 00 FE 07 01 00 07 00 1C 00 07 00 C9 07 00 FE 01 01 00 00 2C FF 00 25 00 0B 07 00 02 07 01 07 01 07 00 FE 07 01 00 07 00 1C 00 07 00 C9 07 01 19 07 01 19 01 00 00 0E FF 00 4E 00 0B 07 00 02 07 01 07 01 07 00 FE 07 01 00 07 00 1C 00 07 00 C9 07 01 19 07 01 19 07 01 19 00 00 1D FF 00 0C 00 0B 07 00 02 07 01 07 01 07 00 FE 07 01 00 07 00 1C 00 07 00 C9 07 00 FE 01 01 00 00 FF 00 09 00 09 07 00 02 07 01 07 01 07 00 FE 07 01 00 07 00 1C 07 01 38 07 00 C9 07 01 3C 00 00 18
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        private void sendImageData(final String hostAddress, final int port, final ImageData imageData, final Rectangle[] rectangles) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: invokestatic    chrriis/dj/nativeswing/swtimpl/NativeInterface.isUIThread:(Z)Z
            //     4: ifeq            56
            //     7: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIIlll;
            //    10: dup            
            //    11: aload_0         /* this */
            //    12: new             Ljava/lang/StringBuilder;
            //    15: dup            
            //    16: invokespecial   java/lang/StringBuilder.<init>:()V
            //    19: ldc_w           "NativeSwing["
            //    22: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    25: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getInstance:()Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface;
            //    28: iconst_1       
            //    29: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getInterfaceID:(Z)I
            //    32: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //    35: ldc_w           "] Component Image Data Transfer"
            //    38: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    41: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    44: aload_1         /* hostAddress */
            //    45: iload_2         /* port */
            //    46: aload_3         /* imageData */
            //    47: aload           rectangles
            //    49: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIIlll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$CMN_getComponentImage;Ljava/lang/String;Ljava/lang/String;ILorg/eclipse/swt/graphics/ImageData;[Ljava/awt/Rectangle;)V
            //    52: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/lIIIlll.start:()V
            //    55: return         
            //    56: iconst_0       
            //    57: istore          cursor
            //    59: sipush          3072
            //    62: newarray        B
            //    64: astore          bytes
            //    66: aload_3         /* imageData */
            //    67: getfield        org/eclipse/swt/graphics/ImageData.palette:Lorg/eclipse/swt/graphics/PaletteData;
            //    70: astore          palette
            //    72: aconst_null    
            //    73: astore          socket
            //    75: aconst_null    
            //    76: astore          out
            //    78: aload_3         /* imageData */
            //    79: getfield        org/eclipse/swt/graphics/ImageData.width:I
            //    82: istore          width
            //    84: aload_3         /* imageData */
            //    85: getfield        org/eclipse/swt/graphics/ImageData.height:I
            //    88: istore          height
            //    90: new             Ljava/net/Socket;
            //    93: dup            
            //    94: aload_1         /* hostAddress */
            //    95: iload_2         /* port */
            //    96: invokespecial   java/net/Socket.<init>:(Ljava/lang/String;I)V
            //    99: astore          socket
            //   101: new             Ljava/io/BufferedOutputStream;
            //   104: dup            
            //   105: aload           socket
            //   107: invokevirtual   java/net/Socket.getOutputStream:()Ljava/io/OutputStream;
            //   110: invokespecial   java/io/BufferedOutputStream.<init>:(Ljava/io/OutputStream;)V
            //   113: astore          out
            //   115: aload           rectangles
            //   117: astore          12
            //   119: aload           12
            //   121: arraylength    
            //   122: istore          13
            //   124: iconst_0       
            //   125: istore          14
            //   127: iload           14
            //   129: iload           13
            //   131: if_icmpge       457
            //   134: aload           12
            //   136: iload           14
            //   138: aaload         
            //   139: astore          rectangle
            //   141: iconst_0       
            //   142: istore          j
            //   144: iload           j
            //   146: aload           rectangle
            //   148: getfield        java/awt/Rectangle.height:I
            //   151: if_icmpge       451
            //   154: aload           rectangle
            //   156: getfield        java/awt/Rectangle.y:I
            //   159: iload           j
            //   161: iadd           
            //   162: istore          y
            //   164: iconst_0       
            //   165: istore          i
            //   167: iload           i
            //   169: aload           rectangle
            //   171: getfield        java/awt/Rectangle.width:I
            //   174: if_icmpge       445
            //   177: aload           rectangle
            //   179: getfield        java/awt/Rectangle.x:I
            //   182: iload           i
            //   184: iadd           
            //   185: istore          x
            //   187: iload           x
            //   189: iload           width
            //   191: if_icmpge       418
            //   194: iload           y
            //   196: iload           height
            //   198: if_icmpge       418
            //   201: aload_3         /* imageData */
            //   202: iload           x
            //   204: iload           y
            //   206: invokevirtual   org/eclipse/swt/graphics/ImageData.getPixel:(II)I
            //   209: istore          pixel
            //   211: aload           palette
            //   213: getfield        org/eclipse/swt/graphics/PaletteData.isDirect:Z
            //   216: ifeq            363
            //   219: iload           pixel
            //   221: aload           palette
            //   223: getfield        org/eclipse/swt/graphics/PaletteData.redMask:I
            //   226: iand           
            //   227: istore          red
            //   229: aload           bytes
            //   231: iload           cursor
            //   233: iinc            cursor, 1
            //   236: aload           palette
            //   238: getfield        org/eclipse/swt/graphics/PaletteData.redShift:I
            //   241: ifge            256
            //   244: iload           red
            //   246: aload           palette
            //   248: getfield        org/eclipse/swt/graphics/PaletteData.redShift:I
            //   251: ineg           
            //   252: iushr          
            //   253: goto            264
            //   256: iload           red
            //   258: aload           palette
            //   260: getfield        org/eclipse/swt/graphics/PaletteData.redShift:I
            //   263: ishl           
            //   264: i2b            
            //   265: bastore        
            //   266: iload           pixel
            //   268: aload           palette
            //   270: getfield        org/eclipse/swt/graphics/PaletteData.greenMask:I
            //   273: iand           
            //   274: istore          green
            //   276: aload           bytes
            //   278: iload           cursor
            //   280: iinc            cursor, 1
            //   283: aload           palette
            //   285: getfield        org/eclipse/swt/graphics/PaletteData.greenShift:I
            //   288: ifge            303
            //   291: iload           green
            //   293: aload           palette
            //   295: getfield        org/eclipse/swt/graphics/PaletteData.greenShift:I
            //   298: ineg           
            //   299: iushr          
            //   300: goto            311
            //   303: iload           green
            //   305: aload           palette
            //   307: getfield        org/eclipse/swt/graphics/PaletteData.greenShift:I
            //   310: ishl           
            //   311: i2b            
            //   312: bastore        
            //   313: iload           pixel
            //   315: aload           palette
            //   317: getfield        org/eclipse/swt/graphics/PaletteData.blueMask:I
            //   320: iand           
            //   321: istore          blue
            //   323: aload           bytes
            //   325: iload           cursor
            //   327: iinc            cursor, 1
            //   330: aload           palette
            //   332: getfield        org/eclipse/swt/graphics/PaletteData.blueShift:I
            //   335: ifge            350
            //   338: iload           blue
            //   340: aload           palette
            //   342: getfield        org/eclipse/swt/graphics/PaletteData.blueShift:I
            //   345: ineg           
            //   346: iushr          
            //   347: goto            358
            //   350: iload           blue
            //   352: aload           palette
            //   354: getfield        org/eclipse/swt/graphics/PaletteData.blueShift:I
            //   357: ishl           
            //   358: i2b            
            //   359: bastore        
            //   360: goto            415
            //   363: aload           palette
            //   365: getfield        org/eclipse/swt/graphics/PaletteData.colors:[Lorg/eclipse/swt/graphics/RGB;
            //   368: iload           pixel
            //   370: aaload         
            //   371: astore          rgb
            //   373: aload           bytes
            //   375: iload           cursor
            //   377: iinc            cursor, 1
            //   380: aload           rgb
            //   382: getfield        org/eclipse/swt/graphics/RGB.red:I
            //   385: i2b            
            //   386: bastore        
            //   387: aload           bytes
            //   389: iload           cursor
            //   391: iinc            cursor, 1
            //   394: aload           rgb
            //   396: getfield        org/eclipse/swt/graphics/RGB.green:I
            //   399: i2b            
            //   400: bastore        
            //   401: aload           bytes
            //   403: iload           cursor
            //   405: iinc            cursor, 1
            //   408: aload           rgb
            //   410: getfield        org/eclipse/swt/graphics/RGB.blue:I
            //   413: i2b            
            //   414: bastore        
            //   415: goto            421
            //   418: iinc            cursor, 3
            //   421: iload           cursor
            //   423: aload           bytes
            //   425: arraylength    
            //   426: if_icmpne       439
            //   429: aload           out
            //   431: aload           bytes
            //   433: invokevirtual   java/io/BufferedOutputStream.write:([B)V
            //   436: iconst_0       
            //   437: istore          cursor
            //   439: iinc            i, 1
            //   442: goto            167
            //   445: iinc            j, 1
            //   448: goto            144
            //   451: iinc            14, 1
            //   454: goto            127
            //   457: aload           out
            //   459: aload           bytes
            //   461: iconst_0       
            //   462: iload           cursor
            //   464: invokevirtual   java/io/BufferedOutputStream.write:([BII)V
            //   467: aload           out
            //   469: invokevirtual   java/io/BufferedOutputStream.flush:()V
            //   472: goto            482
            //   475: astore          e
            //   477: aload           e
            //   479: invokevirtual   java/lang/Exception.printStackTrace:()V
            //   482: aload           out
            //   484: ifnull          492
            //   487: aload           out
            //   489: invokevirtual   java/io/BufferedOutputStream.close:()V
            //   492: goto            497
            //   495: astore          12
            //   497: aload           socket
            //   499: ifnull          507
            //   502: aload           socket
            //   504: invokevirtual   java/net/Socket.close:()V
            //   507: goto            512
            //   510: astore          12
            //   512: return         
            //    StackMapTable: 00 1A 38 FF 00 46 00 0F 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 00 00 FD 00 10 07 01 09 01 FD 00 16 01 01 FF 00 58 00 16 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 00 02 07 01 80 01 FF 00 07 00 16 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 00 03 07 01 80 01 01 FF 00 26 00 17 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 01 00 02 07 01 80 01 FF 00 07 00 17 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 01 00 03 07 01 80 01 01 FF 00 26 00 18 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 01 01 00 02 07 01 80 01 FF 00 07 00 18 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 07 00 FE 01 01 07 01 09 01 01 01 01 01 01 01 01 00 03 07 01 80 01 01 F8 00 04 33 FA 00 02 02 11 FA 00 05 F9 00 05 F9 00 05 FF 00 11 00 0C 07 00 02 07 01 00 01 07 01 38 07 00 FE 01 07 01 80 07 01 82 07 01 1C 07 01 77 01 01 00 01 07 00 F6 FC 00 06 07 01 3C 09 42 07 00 F6 01 09 42 07 00 F6 01
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  90     472    475    482    Ljava/lang/Exception;
            //  482    492    495    497    Ljava/lang/Exception;
            //  497    507    510    512    Ljava/lang/Exception;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    
    private static class CMN_reparentToHiddenShell extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            final Shell shell = control.getShell();
            final Shell newShell = new Shell(control.getDisplay(), 0);
            control.getParent().setParent((Composite)newShell);
            shell.dispose();
            return null;
        }
    }
    
    private static class CMN_redraw extends ControlCommandMessage
    {
        public Object run(final Object[] args) {
            final Control control = this.getControl();
            if (control.isDisposed()) {
                return null;
            }
            final org.eclipse.swt.graphics.Point size = control.getSize();
            control.redraw(0, 0, size.x, size.y, true);
            return null;
        }
    }
    
    private static class DnDHandler
    {
        private static boolean isDnDActive;
        private static DnDHandler dndHandler;
        private SWTNativeComponent[] nativeComponents;
        private boolean[] wereEnabled;
        
        private static void activateDragAndDrop() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: ifeq            7
            //     6: return         
            //     7: iconst_1       
            //     8: putstatic       chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent$DnDHandler.isDnDActive:Z
            //    11: invokestatic    java/awt/dnd/DragSource.getDefaultDragSource:()Ljava/awt/dnd/DragSource;
            //    14: astore_0        /* dragSource */
            //    15: new             Lchrriis/dj/nativeswing/swtimpl/core/llIIIIl;
            //    18: dup            
            //    19: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIIIIl.<init>:()V
            //    22: astore_1        /* dragSourceListener */
            //    23: aload_0         /* dragSource */
            //    24: aload_1         /* dragSourceListener */
            //    25: invokevirtual   java/awt/dnd/DragSource.addDragSourceMotionListener:(Ljava/awt/dnd/DragSourceMotionListener;)V
            //    28: aload_0         /* dragSource */
            //    29: aload_1         /* dragSourceListener */
            //    30: invokevirtual   java/awt/dnd/DragSource.addDragSourceListener:(Ljava/awt/dnd/DragSourceListener;)V
            //    33: return         
            //    StackMapTable: 00 01 07
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
}
