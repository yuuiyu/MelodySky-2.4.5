//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import chrriis.dj.nativeswing.swtimpl.internal.*;
import org.eclipse.swt.widgets.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.datatransfer.*;
import org.eclipse.swt.*;
import java.awt.event.*;
import javax.swing.*;
import chrriis.dj.nativeswing.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.*;
import org.eclipse.swt.graphics.*;
import chrriis.dj.nativeswing.common.*;
import chrriis.dj.nativeswing.swtimpl.common.*;
import java.util.*;
import chrriis.dj.nativeswing.swtimpl.*;
import java.net.*;
import java.io.*;

public class SWTNativeInterface extends NativeInterface implements ISWTNativeInterface
{
    private static final boolean IS_SYNCING_MESSAGES;
    private static boolean isOpen;
    private static volatile NativeInterfaceConfiguration nativeInterfaceConfiguration;
    private volatile boolean isInitialized;
    private boolean isInProcess;
    private static final Object OPEN_CLOSE_SYNC_LOCK;
    private static final Object OPEN_STATE_LOCK;
    private static MessagingInterface messagingInterface;
    private static volatile Display display;
    private static volatile boolean isEventPumpRunning;
    private EventListenerList listenerList;
    private ApplicationMessageHandler applicationMessageHandler;
    private static volatile long lastProcessTime;
    
    public SWTNativeInterface() {
        this.listenerList = new EventListenerList();
    }
    
    public boolean isAlive() {
        synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
            return NativeInterface.isOpen() && SWTNativeInterface.messagingInterface.isAlive();
        }
    }
    
    @Override
    public boolean isOpen_() {
        synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
            return SWTNativeInterface.isOpen;
        }
    }
    
    private void checkOpen() {
        if (!NativeInterface.isOpen()) {
            throw new IllegalStateException("The native interface is not open! Please refer to the instructions to set it up properly.");
        }
    }
    
    @Override
    public void close_() {
        synchronized (SWTNativeInterface.OPEN_CLOSE_SYNC_LOCK) {
            if (!NativeInterface.isOpen()) {
                return;
            }
            synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
                SWTNativeInterface.isOpen = false;
                SWTNativeInterface.messagingInterface.destroy();
                SWTNativeInterface.messagingInterface = null;
            }
            for (final NativeInterfaceListener listener : NativeInterface.getNativeInterfaceListeners()) {
                listener.nativeInterfaceClosed();
            }
        }
    }
    
    @Override
    public NativeInterfaceConfiguration getConfiguration_() {
        if (SWTNativeInterface.nativeInterfaceConfiguration == null) {
            SWTNativeInterface.nativeInterfaceConfiguration = NativeInterface.createConfiguration();
        }
        return SWTNativeInterface.nativeInterfaceConfiguration;
    }
    
    private void loadClipboardDebuggingProperties() {
        try {
            final Clipboard systemClipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            if (!systemClipboard.isDataFlavorAvailable(DataFlavor.stringFlavor)) {
                return;
            }
            final BufferedReader reader = new BufferedReader(new StringReader((String)systemClipboard.getData(DataFlavor.stringFlavor)));
            if ("[nativeswing debug]".equals(reader.readLine().trim().toLowerCase(Locale.ENGLISH))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    if (line.length() != 0) {
                        final int index = line.indexOf(61);
                        if (index <= 0) {
                            break;
                        }
                        final String propertyName = line.substring(0, index).trim();
                        final String propertyValue = line.substring(index + 1).trim();
                        if (!propertyName.startsWith("nativeswing.")) {
                            continue;
                        }
                        System.setProperty(propertyName, propertyValue);
                    }
                }
            }
            reader.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    public boolean isInitialized_() {
        return this.isInitialized;
    }
    
    @Override
    public boolean isInProcess_() {
        synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
            return this.isInProcess;
        }
    }
    
    @Override
    public void initialize_() {
        synchronized (SWTNativeInterface.OPEN_CLOSE_SYNC_LOCK) {
            if (isInitialized()) {
                return;
            }
            if (Boolean.parseBoolean(NSSystemPropertySWT.DEPENDENCIES_CHECKVERSIONS.get("true")) && SWT.getVersion() < 4332) {
                throw new IllegalStateException("The version of SWT that is required is 4.3 or later!");
            }
            if (SWTNativeInterface.nativeInterfaceConfiguration == null) {
                SWTNativeInterface.nativeInterfaceConfiguration = NativeInterface.createConfiguration();
            }
            Label_0206: {
                if (Utils.IS_MAC && !"applet".equals(NSSystemProperty.DEPLOYMENT_TYPE.get())) {
                    for (final StackTraceElement ste : Thread.currentThread().getStackTrace()) {
                        try {
                            Class<?> clazz;
                            for (Class<?> steClass = clazz = Class.forName(ste.getClassName()); clazz != null; clazz = clazz.getSuperclass()) {
                                if (clazz.getName().equals("java.awt.Component")) {
                                    System.err.println("On Mac, \"NativeInterface.initialize()\"/\"NativeInterface.open()\" should not be called after AWT static initializers have run, otherwise there can be all sorts of side effects (non-functional modal dialogs, etc.). Generally, the problem is when the \"main(String[])\" method is located inside an AWT component subclass and the fix is to move that main method to a standalone class. The problematic class here is \"" + steClass.getName() + "\"");
                                    break Label_0206;
                                }
                            }
                        }
                        catch (ClassNotFoundException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
            final String inProcessProperty = NSSystemPropertySWT.INTERFACE_INPROCESS.get();
            if (inProcessProperty != null) {
                this.isInProcess = Boolean.parseBoolean(inProcessProperty);
            }
            else {
                this.isInProcess = Utils.IS_MAC;
            }
            try {
                for (final NativeInterfaceListener listener : NativeInterface.getNativeInterfaceListeners()) {
                    listener.nativeInterfaceInitialized();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (this.isInProcess_()) {
                initialize();
            }
            else {
                initialize();
            }
            Toolkit.getDefaultToolkit().addAWTEventListener((AWTEventListener)new lIllllI(this), 8L);
            this.isInitialized = true;
        }
    }
    
    @Override
    public void printStackTraces_() {
        Utils.printStackTraces();
        this.printPeerStackTrace(System.err);
    }
    
    @Override
    public void printStackTraces_(final PrintStream printStream) {
        Utils.printStackTraces(printStream);
        this.printPeerStackTrace(printStream);
    }
    
    @Override
    public void printStackTraces_(final PrintWriter printWriter) {
        Utils.printStackTraces(printWriter);
        this.printPeerStackTrace(printWriter);
    }
    
    private void printPeerStackTrace(final Object o) {
        class llllIII extends Thread
        {
            final /* synthetic */ Object val$o;
            final /* synthetic */ SWTNativeInterface this$0;
            
            llllIII(final SWTNativeInterface this$0, final String x0, final Object val$o) {
                this.this$0 = this$0;
                this.val$o = val$o;
                super(x0);
            }
            
            @Override
            public void run() {
                this.this$0.printPeerStackTrace(this.val$o);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.isInProcess_:()Z
        //     4: ifne            199
        //     7: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.isOpen:()Z
        //    10: ifeq            199
        //    13: iconst_0       
        //    14: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.isUIThread:(Z)Z
        //    17: ifeq            48
        //    20: new             Lchrriis/dj/nativeswing/swtimpl/core/llllIII;
        //    23: dup            
        //    24: aload_0         /* this */
        //    25: ldc_w           "NativeSwing stack traces dump"
        //    28: aload_1         /* o */
        //    29: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llllIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface;Ljava/lang/String;Ljava/lang/Object;)V
        //    32: astore_2        /* t */
        //    33: aload_2         /* t */
        //    34: invokevirtual   java/lang/Thread.start:()V
        //    37: aload_2         /* t */
        //    38: invokevirtual   java/lang/Thread.join:()V
        //    41: goto            45
        //    44: astore_3       
        //    45: goto            199
        //    48: aload_1         /* o */
        //    49: ifnonnull       56
        //    52: iconst_1       
        //    53: goto            57
        //    56: iconst_0       
        //    57: istore_2        /* isToConsole */
        //    58: new             Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$CMN_printStackTraces;
        //    61: dup            
        //    62: aconst_null    
        //    63: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$CMN_printStackTraces.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIllllI;)V
        //    66: astore_3        /* message */
        //    67: aload_3         /* message */
        //    68: iconst_1       
        //    69: anewarray       Ljava/lang/Object;
        //    72: dup            
        //    73: iconst_0       
        //    74: iload_2         /* isToConsole */
        //    75: invokestatic    java/lang/Boolean.valueOf:(Z)Ljava/lang/Boolean;
        //    78: aastore        
        //    79: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.setMessageArgs:(Lchrriis/dj/nativeswing/swtimpl/CommandMessage;[Ljava/lang/Object;)V
        //    82: aload_0         /* this */
        //    83: iconst_1       
        //    84: aload_3         /* message */
        //    85: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.syncSend_:(ZLchrriis/dj/nativeswing/swtimpl/Message;)Ljava/lang/Object;
        //    88: checkcast       Ljava/lang/String;
        //    91: astore          s
        //    93: iload_2         /* isToConsole */
        //    94: ifne            199
        //    97: new             Ljava/lang/StringBuilder;
        //   100: dup            
        //   101: invokespecial   java/lang/StringBuilder.<init>:()V
        //   104: ldc_w           "---- NativeSwing["
        //   107: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   110: aload_0         /* this */
        //   111: iconst_0       
        //   112: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getMessagingInterface:(Z)Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
        //   115: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/MessagingInterface.getPID:()I
        //   118: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   121: ldc_w           "] Peer VM Stack Traces ----"
        //   124: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   127: getstatic       chrriis/dj/nativeswing/common/Utils.LINE_SEPARATOR:Ljava/lang/String;
        //   130: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //   133: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   136: astore          descriptor
        //   138: aload_1         /* o */
        //   139: instanceof      Ljava/io/PrintStream;
        //   142: ifeq            170
        //   145: aload_1         /* o */
        //   146: checkcast       Ljava/io/PrintStream;
        //   149: astore          ps
        //   151: aload           ps
        //   153: aload           descriptor
        //   155: invokevirtual   java/io/PrintStream.append:(Ljava/lang/CharSequence;)Ljava/io/PrintStream;
        //   158: pop            
        //   159: aload           ps
        //   161: aload           s
        //   163: invokevirtual   java/io/PrintStream.append:(Ljava/lang/CharSequence;)Ljava/io/PrintStream;
        //   166: pop            
        //   167: goto            199
        //   170: aload_1         /* o */
        //   171: instanceof      Ljava/io/PrintWriter;
        //   174: ifeq            199
        //   177: aload_1         /* o */
        //   178: checkcast       Ljava/io/PrintWriter;
        //   181: astore          pw
        //   183: aload           pw
        //   185: aload           descriptor
        //   187: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   190: pop            
        //   191: aload           pw
        //   193: aload           s
        //   195: invokevirtual   java/io/PrintWriter.append:(Ljava/lang/CharSequence;)Ljava/io/PrintWriter;
        //   198: pop            
        //   199: return         
        //    StackMapTable: 00 07 FF 00 2C 00 03 07 00 02 07 00 60 07 00 18 00 01 07 01 90 00 FA 00 02 07 40 01 FF 00 70 00 06 07 00 02 07 00 60 01 07 00 0F 07 00 AA 07 00 AA 00 00 FF 00 1C 00 02 07 00 02 07 00 60 00 00
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                            
        //  -----  -----  -----  -----  --------------------------------
        //  37     41     44     45     Ljava/lang/InterruptedException;
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
    
    @Override
    public void open_() {
        synchronized (SWTNativeInterface.OPEN_CLOSE_SYNC_LOCK) {
            if (isOpen()) {
                return;
            }
            initialize();
            this.loadClipboardDebuggingProperties();
            if (this.isInProcess_()) {
                InProcess.createInProcessCommunicationChannel();
            }
            else {
                OutProcess.createOutProcessCommunicationChannel();
            }
            try {
                for (final NativeInterfaceListener listener : NativeInterface.getNativeInterfaceListeners()) {
                    listener.nativeInterfaceOpened();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    public boolean notifyKilled() {
        synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
            SWTNativeInterface.isOpen = false;
            SWTNativeInterface.messagingInterface = null;
        }
        try {
            for (final NativeInterfaceListener listener : NativeInterface.getNativeInterfaceListeners()) {
                listener.nativeInterfaceClosed();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        if (!OutProcess.isNativeSide() && SWTNativeInterface.nativeInterfaceConfiguration.isNativeSideRespawnedOnError()) {
            OutProcess.createOutProcessCommunicationChannel();
            return true;
        }
        return false;
    }
    
    public void notifyRespawned() {
        try {
            for (final NativeInterfaceListener listener : NativeInterface.getNativeInterfaceListeners()) {
                listener.nativeInterfaceOpened();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public Object syncSend_(final boolean isTargetNativeSide, final Message message) {
        this.checkOpen();
        if (message instanceof LocalMessage) {
            final LocalMessage localMessage = (LocalMessage)message;
            return runMessageCommand(localMessage);
        }
        return this.getMessagingInterface(!isTargetNativeSide).syncSend(message);
    }
    
    @Override
    public void asyncSend_(final boolean isTargetNativeSide, final Message message) {
        if (SWTNativeInterface.IS_SYNCING_MESSAGES) {
            this.syncSend_(isTargetNativeSide, message);
        }
        else {
            this.checkOpen();
            if (message instanceof LocalMessage) {
                final LocalMessage localMessage = (LocalMessage)message;
                runMessageCommand(localMessage);
                return;
            }
            this.getMessagingInterface(!isTargetNativeSide).asyncSend(message);
        }
    }
    
    MessagingInterface getMessagingInterface(final boolean isNativeSide) {
        synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
            if (this.isInProcess_()) {
                if (isNativeSide) {
                    final InProcessMessagingInterface.SWTInProcessMessagingInterface swtInProcessMessagingInterface = (InProcessMessagingInterface.SWTInProcessMessagingInterface)((InProcessMessagingInterface.SwingInProcessMessagingInterface)SWTNativeInterface.messagingInterface).getMirrorMessagingInterface();
                    return (MessagingInterface)swtInProcessMessagingInterface;
                }
                final InProcessMessagingInterface.SwingInProcessMessagingInterface swingInProcessMessagingInterface = (InProcessMessagingInterface.SwingInProcessMessagingInterface)SWTNativeInterface.messagingInterface;
                return (MessagingInterface)swingInProcessMessagingInterface;
            }
            else if (isNativeSide) {
                if (SWTNativeInterface.messagingInterface instanceof OutProcessSocketsMessagingInterface.SWTOutProcessSocketsMessagingInterface) {
                    final OutProcessSocketsMessagingInterface.SWTOutProcessSocketsMessagingInterface swtOutProcessSocketsMessagingInterface = (OutProcessSocketsMessagingInterface.SWTOutProcessSocketsMessagingInterface)SWTNativeInterface.messagingInterface;
                    return (MessagingInterface)swtOutProcessSocketsMessagingInterface;
                }
                final OutProcessIOMessagingInterface.SWTOutProcessIOMessagingInterface swtOutProcessIOMessagingInterface = (OutProcessIOMessagingInterface.SWTOutProcessIOMessagingInterface)SWTNativeInterface.messagingInterface;
                return (MessagingInterface)swtOutProcessIOMessagingInterface;
            }
            else {
                if (SWTNativeInterface.messagingInterface instanceof OutProcessSocketsMessagingInterface.SwingOutProcessSocketsMessagingInterface) {
                    final OutProcessSocketsMessagingInterface.SwingOutProcessSocketsMessagingInterface swingOutProcessSocketsMessagingInterface = (OutProcessSocketsMessagingInterface.SwingOutProcessSocketsMessagingInterface)SWTNativeInterface.messagingInterface;
                    return (MessagingInterface)swingOutProcessSocketsMessagingInterface;
                }
                final OutProcessIOMessagingInterface.SwingOutProcessIOMessagingInterface swingOutProcessIOMessagingInterface = (OutProcessIOMessagingInterface.SwingOutProcessIOMessagingInterface)SWTNativeInterface.messagingInterface;
                return (MessagingInterface)swingOutProcessIOMessagingInterface;
            }
        }
    }
    
    public Display getDisplay() {
        return SWTNativeInterface.display;
    }
    
    @Override
    public boolean isOutProcessNativeSide_() {
        return OutProcess.isNativeSide();
    }
    
    @Override
    public boolean isUIThread_(final boolean isNativeSide) {
        if (!this.isAlive()) {
            throw new IllegalStateException("The native interface is not alive!");
        }
        return this.getMessagingInterface(isNativeSide).isUIThread();
    }
    
    public int getInterfaceID(final boolean isNativeSide) {
        if (!this.isAlive()) {
            throw new IllegalStateException("The native interface is not alive!");
        }
        return this.getMessagingInterface(isNativeSide).getPID();
    }
    
    public void checkUIThread(final boolean isNativeSide) {
        if (!this.isAlive()) {
            throw new IllegalStateException("The native interface is not alive!");
        }
        this.getMessagingInterface(isNativeSide).checkUIThread();
    }
    
    @Override
    public boolean isEventPumpRunning_() {
        return SWTNativeInterface.isEventPumpRunning;
    }
    
    @Override
    public void runEventPump_() {
        if (!NativeInterface.isInitialized()) {
            throw new IllegalStateException("Cannot run the event pump when the interface is not initialized!");
        }
        if (SWTNativeInterface.isEventPumpRunning) {
            throw new IllegalStateException("runEventPump was already called and can only be called once (the first call should be at the end of the main method)!");
        }
        SWTNativeInterface.isEventPumpRunning = true;
        this.startAutoShutdownThread();
        if (this.isInProcess_()) {
            InProcess.runEventPump();
        }
        else {
            OutProcess.runEventPump();
        }
    }
    
    private void startAutoShutdownThread() {
        final Thread displayThread = (SWTNativeInterface.display == null) ? null : SWTNativeInterface.display.getThread();
        final Thread currentThread = Thread.currentThread();
        final Thread autoShutdownThread = (Thread)new lIlIlII(this, "NativeSwing Auto-Shutdown", displayThread, currentThread);
        autoShutdownThread.setDaemon(true);
        autoShutdownThread.start();
    }
    
    @Override
    public void addNativeInterfaceListener_(final NativeInterfaceListener listener) {
        this.listenerList.add(NativeInterfaceListener.class, listener);
    }
    
    @Override
    public void removeNativeInterfaceListener_(final NativeInterfaceListener listener) {
        this.listenerList.remove(NativeInterfaceListener.class, listener);
    }
    
    @Override
    public NativeInterfaceListener[] getNativeInterfaceListeners_() {
        return this.listenerList.getListeners(NativeInterfaceListener.class);
    }
    
    @Override
    public void setApplicationMessageHandler_(final ApplicationMessageHandler applicationMessageHandler) {
        this.applicationMessageHandler = applicationMessageHandler;
    }
    
    public ApplicationMessageHandler getApplicationMessageHandler() {
        return this.applicationMessageHandler;
    }
    
    private static void handleQuit() {
        final class lllllII implements Runnable
        {
            @Override
            public void run() {
                handleQuit();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifne            17
        //     6: new             Lchrriis/dj/nativeswing/swtimpl/core/lllllII;
        //     9: dup            
        //    10: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllllII.<init>:()V
        //    13: invokestatic    javax/swing/SwingUtilities.invokeLater:(Ljava/lang/Runnable;)V
        //    16: return         
        //    17: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getInstance:()Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface;
        //    20: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.getApplicationMessageHandler:()Lchrriis/dj/nativeswing/swtimpl/ApplicationMessageHandler;
        //    23: astore_0        /* applicationMessageHandler */
        //    24: aload_0         /* applicationMessageHandler */
        //    25: ifnonnull       37
        //    28: sipush          -1324
        //    31: invokestatic    java/lang/System.exit:(I)V
        //    34: goto            43
        //    37: aload_0         /* applicationMessageHandler */
        //    38: invokeinterface chrriis/dj/nativeswing/swtimpl/ApplicationMessageHandler.handleQuit:()V
        //    43: return         
        //    StackMapTable: 00 03 11 FC 00 13 07 02 6F 05
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
    
    private static void destroyControls() {
        final class lIlllII implements Runnable
        {
            @Override
            public void run() {
                destroyControls();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: ifnull          115
        //     6: getstatic       chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.display:Lorg/eclipse/swt/widgets/Display;
        //     9: invokevirtual   org/eclipse/swt/widgets/Display.isDisposed:()Z
        //    12: ifne            115
        //    15: getstatic       chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.display:Lorg/eclipse/swt/widgets/Display;
        //    18: invokevirtual   org/eclipse/swt/widgets/Display.getThread:()Ljava/lang/Thread;
        //    21: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
        //    24: if_acmpeq       41
        //    27: getstatic       chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.display:Lorg/eclipse/swt/widgets/Display;
        //    30: new             Lchrriis/dj/nativeswing/swtimpl/core/lIlllII;
        //    33: dup            
        //    34: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIlllII.<init>:()V
        //    37: invokevirtual   org/eclipse/swt/widgets/Display.syncExec:(Ljava/lang/Runnable;)V
        //    40: return         
        //    41: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeComponent.getControls:()[Lorg/eclipse/swt/widgets/Control;
        //    44: astore_0       
        //    45: aload_0        
        //    46: arraylength    
        //    47: istore_1       
        //    48: iconst_0       
        //    49: istore_2       
        //    50: iload_2        
        //    51: iload_1        
        //    52: if_icmpge       101
        //    55: aload_0        
        //    56: iload_2        
        //    57: aaload         
        //    58: astore_3       
        //    59: aload_3        
        //    60: invokevirtual   org/eclipse/swt/widgets/Control.isDisposed:()Z
        //    63: ifeq            70
        //    66: aconst_null    
        //    67: goto            74
        //    70: aload_3        
        //    71: invokevirtual   org/eclipse/swt/widgets/Control.getShell:()Lorg/eclipse/swt/widgets/Shell;
        //    74: astore          shell
        //    76: aload           shell
        //    78: ifnull          86
        //    81: aload           shell
        //    83: invokevirtual   org/eclipse/swt/widgets/Shell.dispose:()V
        //    86: goto            91
        //    89: astore          5
        //    91: aload_3        
        //    92: invokevirtual   org/eclipse/swt/widgets/Control.dispose:()V
        //    95: iinc            2, 1
        //    98: goto            50
        //   101: getstatic       chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.display:Lorg/eclipse/swt/widgets/Display;
        //   104: invokevirtual   org/eclipse/swt/widgets/Display.dispose:()V
        //   107: goto            115
        //   110: astore_0        /* t */
        //   111: aload_0         /* t */
        //   112: invokevirtual   java/lang/Throwable.printStackTrace:()V
        //   115: return         
        //    StackMapTable: 00 0A 29 FE 00 08 07 02 81 01 01 FC 00 13 07 02 83 43 07 02 8A FC 00 0B 07 02 8A 42 07 00 8A 01 F9 00 09 48 07 00 62 F8 00 04
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  76     86     89     91     Ljava/lang/Exception;
        //  101    107    110    115    Ljava/lang/Throwable;
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
    
    public static SWTNativeInterface getInstance() {
        return (SWTNativeInterface)NativeInterface.getInstance();
    }
    
    protected static int getMessageID(final Message message) {
        return NativeInterface.getMessageID(message);
    }
    
    protected static boolean isMessageValid(final Message message) {
        return NativeInterface.isMessageValid(message);
    }
    
    protected static Object runMessageCommand(final LocalMessage commandMessage) {
        return NativeInterface.runMessageCommand(commandMessage);
    }
    
    protected static Object runMessageCommand(final CommandMessage commandMessage) throws Exception {
        return NativeInterface.runMessageCommand(commandMessage);
    }
    
    protected static boolean isMessageSyncExec(final Message message) {
        return NativeInterface.isMessageSyncExec(message);
    }
    
    protected static void setMessageSyncExec(final Message message, final boolean isSyncExec) {
        NativeInterface.setMessageSyncExec(message, isSyncExec);
    }
    
    protected static void setMessageArgs(final CommandMessage message, final Object... args) {
        NativeInterface.setMessageArgs(message, args);
    }
    
    protected static void computeMessageID(final Message message, final boolean isTargetNativeSide) {
        NativeInterface.computeMessageID(message, isTargetNativeSide);
    }
    
    protected static void setMessageUI(final Message message, final boolean isUI) {
        NativeInterface.setMessageUI(message, isUI);
    }
    
    protected static boolean isMessageUI(final Message message) {
        return NativeInterface.isMessageUI(message);
    }
    
    @Override
    public void main_(final String[] args) throws Exception {
        OutProcess.runNativeSide(args);
    }
    
    static {
        IS_SYNCING_MESSAGES = Boolean.parseBoolean(NSSystemPropertySWT.INTERFACE_SYNCMESSAGES.get());
        OPEN_CLOSE_SYNC_LOCK = new Object();
        OPEN_STATE_LOCK = new Object();
        SWTNativeInterface.lastProcessTime = Long.MAX_VALUE;
    }
    
    private static class CMN_printStackTraces extends CommandMessage
    {
        public Object run(final Object[] args) {
            final boolean isToConsole = (boolean)args[0];
            if (isToConsole) {
                Utils.printStackTraces();
                return null;
            }
            final StringWriter sw = new StringWriter();
            Utils.printStackTraces(new PrintWriter(sw));
            return sw.toString();
        }
    }
    
    static class InProcess
    {
        private static volatile int pid;
        
        static void createInProcessCommunicationChannel() {
            synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
                SWTNativeInterface.messagingInterface = createInProcessMessagingInterface();
                SWTNativeInterface.isOpen = true;
            }
        }
        
        private static void initialize() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //     6: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //     9: putstatic       org/eclipse/swt/graphics/Device.DEBUG:Z
            //    12: getstatic       chrriis/dj/nativeswing/common/Utils.IS_MAC:Z
            //    15: ifeq            48
            //    18: ldc             "applet"
            //    20: getstatic       chrriis/dj/nativeswing/NSSystemProperty.DEPLOYMENT_TYPE:Lchrriis/dj/nativeswing/NSSystemProperty;
            //    23: invokevirtual   chrriis/dj/nativeswing/NSSystemProperty.get:()Ljava/lang/String;
            //    26: invokevirtual   java/lang/String.equals:(Ljava/lang/Object;)Z
            //    29: ifeq            48
            //    32: invokestatic    chrriis/dj/nativeswing/NativeSwing.initialize:()V
            //    35: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIlIlI;
            //    38: dup            
            //    39: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIlIlI.<init>:()V
            //    42: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$InProcess.runWithMacExecutor:(Ljava/lang/Runnable;)V
            //    45: goto            79
            //    48: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$InProcess.findSWTDisplay:()V
            //    51: goto            76
            //    54: astore_0        /* e */
            //    55: getstatic       chrriis/dj/nativeswing/common/Utils.IS_MAC:Z
            //    58: ifeq            74
            //    61: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIlIIl;
            //    64: dup            
            //    65: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIlIIl.<init>:()V
            //    68: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$InProcess.runWithMacExecutor:(Ljava/lang/Runnable;)V
            //    71: goto            76
            //    74: aload_0         /* e */
            //    75: athrow         
            //    76: invokestatic    chrriis/dj/nativeswing/NativeSwing.initialize:()V
            //    79: getstatic       chrriis/dj/nativeswing/common/Utils.IS_MAC:Z
            //    82: ifeq            97
            //    85: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.INTERFACE_INPROCESS_FORCESHUTDOWNHOOK:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    88: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //    91: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //    94: ifeq            112
            //    97: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
            //   100: new             Lchrriis/dj/nativeswing/swtimpl/core/llIIlII;
            //   103: dup            
            //   104: ldc             "NativeSwing Shutdown Hook"
            //   106: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIIlII.<init>:(Ljava/lang/String;)V
            //   109: invokevirtual   java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
            //   112: return         
            //    StackMapTable: 00 07 30 45 07 00 37 FC 00 13 07 00 37 FA 00 01 02 11 0E
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                          
            //  -----  -----  -----  -----  ------------------------------
            //  48     51     54     76     Lorg/eclipse/swt/SWTException;
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
        
        private static void runWithMacExecutor(final Runnable runnable) {
            Executor mainQueueExecutor;
            try {
                final Object dispatch = Class.forName("com.apple.concurrent.Dispatch").getMethod("getInstance", (Class<?>[])new Class[0]).invoke(null, new Object[0]);
                mainQueueExecutor = (Executor)dispatch.getClass().getMethod("getNonBlockingMainQueueExecutor", (Class<?>[])new Class[0]).invoke(dispatch, new Object[0]);
            }
            catch (Exception e) {
                throw new IllegalStateException("Failed to use the Mac Dispatch executor. This may happen if the version of Java that is used is too old.", e);
            }
            final AtomicBoolean isExecutorCallComplete = new AtomicBoolean(false);
            final AtomicReference<Throwable> exceptionReference = new AtomicReference<Throwable>();
            synchronized (isExecutorCallComplete) {
                mainQueueExecutor.execute((Runnable)new llIIllI(runnable, (AtomicReference)exceptionReference, isExecutorCallComplete));
                while (!isExecutorCallComplete.get()) {
                    try {
                        isExecutorCallComplete.wait();
                    }
                    catch (InterruptedException ex) {}
                }
            }
            final Throwable throwable = exceptionReference.get();
            if (throwable == null) {
                return;
            }
            if (throwable instanceof RuntimeException) {
                throw (RuntimeException)throwable;
            }
            throw new RuntimeException(throwable);
        }
        
        private static void findSWTDisplay() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$402:(Lorg/eclipse/swt/widgets/Display;)Lorg/eclipse/swt/widgets/Display;
            //     6: pop            
            //     7: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    10: ifnonnull       64
            //    13: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.INTERFACE_INPROCESS_USEEXTERNALSWTDISPLAY:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    16: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //    19: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //    22: ifeq            64
            //    25: invokestatic    org/eclipse/swt/widgets/Display.getDefault:()Lorg/eclipse/swt/widgets/Display;
            //    28: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$402:(Lorg/eclipse/swt/widgets/Display;)Lorg/eclipse/swt/widgets/Display;
            //    31: pop            
            //    32: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    35: invokevirtual   org/eclipse/swt/widgets/Display.getThread:()Ljava/lang/Thread;
            //    38: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
            //    41: if_acmpne       64
            //    44: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    47: invokevirtual   org/eclipse/swt/widgets/Display.dispose:()V
            //    50: aconst_null    
            //    51: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$402:(Lorg/eclipse/swt/widgets/Display;)Lorg/eclipse/swt/widgets/Display;
            //    54: pop            
            //    55: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.INTERFACE_INPROCESS_USEEXTERNALSWTDISPLAY:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    58: ldc             "false"
            //    60: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.set:(Ljava/lang/String;)Ljava/lang/String;
            //    63: pop            
            //    64: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    67: ifnonnull       116
            //    70: new             Lorg/eclipse/swt/graphics/DeviceData;
            //    73: dup            
            //    74: invokespecial   org/eclipse/swt/graphics/DeviceData.<init>:()V
            //    77: astore_0        /* data */
            //    78: aload_0         /* data */
            //    79: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.SWT_DEVICEDATA_DEBUG:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    82: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //    85: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //    88: putfield        org/eclipse/swt/graphics/DeviceData.debug:Z
            //    91: aload_0         /* data */
            //    92: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.SWT_DEVICEDATA_TRACKING:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    95: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //    98: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   101: putfield        org/eclipse/swt/graphics/DeviceData.tracking:Z
            //   104: new             Lorg/eclipse/swt/widgets/Display;
            //   107: dup            
            //   108: aload_0         /* data */
            //   109: invokespecial   org/eclipse/swt/widgets/Display.<init>:(Lorg/eclipse/swt/graphics/DeviceData;)V
            //   112: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$402:(Lorg/eclipse/swt/widgets/Display;)Lorg/eclipse/swt/widgets/Display;
            //   115: pop            
            //   116: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   119: bipush          21
            //   121: new             Lchrriis/dj/nativeswing/swtimpl/core/llIlIlI;
            //   124: dup            
            //   125: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIlIlI.<init>:()V
            //   128: invokevirtual   org/eclipse/swt/widgets/Display.addListener:(ILorg/eclipse/swt/widgets/Listener;)V
            //   131: return         
            //    StackMapTable: 00 02 FB 00 40 33
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
        
        private static MessagingInterface createInProcessMessagingInterface() {
            final int pid_ = ++InProcess.pid;
            return (MessagingInterface)new InProcessMessagingInterface.SWTInProcessMessagingInterface(SWTNativeInterface.display, pid_).getMirrorMessagingInterface();
        }
        
        static void runEventPump() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //     6: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //     9: ifeq            25
            //    12: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    15: invokevirtual   org/eclipse/swt/widgets/Display.getThread:()Ljava/lang/Thread;
            //    18: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
            //    21: if_acmpeq       25
            //    24: return         
            //    25: getstatic       chrriis/dj/nativeswing/common/Utils.IS_MAC:Z
            //    28: ifeq            54
            //    31: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //    34: invokevirtual   org/eclipse/swt/widgets/Display.getThread:()Ljava/lang/Thread;
            //    37: invokestatic    java/lang/Thread.currentThread:()Ljava/lang/Thread;
            //    40: if_acmpeq       54
            //    43: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIIIIl;
            //    46: dup            
            //    47: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIIIIl.<init>:()V
            //    50: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$InProcess.runWithMacExecutor:(Ljava/lang/Runnable;)V
            //    53: return         
            //    54: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$InProcess.runSWTEventPump:()V
            //    57: return         
            //    StackMapTable: 00 02 19 1C
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
        
        private static void runSWTEventPump() {
            while (SWTNativeInterface.isEventPumpRunning) {
                try {
                    if (SWTNativeInterface.display.isDisposed()) {
                        SWTNativeInterface.isEventPumpRunning = false;
                    }
                    else {
                        if (SWTNativeInterface.display.readAndDispatch() || !SWTNativeInterface.isEventPumpRunning) {
                            continue;
                        }
                        SWTNativeInterface.display.sleep();
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            SWTNativeInterface.display.dispose();
        }
    }
    
    static class OutProcess
    {
        private static final boolean IS_PROCESS_IO_CHANNEL_MODE;
        private static volatile int pid;
        
        private static void initialize() {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
            //     6: new             Lchrriis/dj/nativeswing/swtimpl/core/llIlllI;
            //     9: dup            
            //    10: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIlllI.<init>:()V
            //    13: invokevirtual   java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
            //    16: return         
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
        
        static boolean isNativeSide() {
            return SWTNativeInterface.display != null;
        }
        
        static void createOutProcessCommunicationChannel() {
            synchronized (SWTNativeInterface.OPEN_STATE_LOCK) {
                int i = 2;
                while (i >= 0) {
                    try {
                        SWTNativeInterface.messagingInterface = createOutProcessMessagingInterface();
                    }
                    catch (RuntimeException e) {
                        if (i == 0) {
                            throw e;
                        }
                        --i;
                        continue;
                    }
                    break;
                }
                SWTNativeInterface.isOpen = true;
            }
            final Properties nativeProperties = new Properties();
            final Properties properties = System.getProperties();
            for (final Object key : properties.keySet()) {
                if (key instanceof String) {
                    final Object value = properties.get(key);
                    if (!(value instanceof String)) {
                        continue;
                    }
                    nativeProperties.setProperty((String)key, (String)value);
                }
            }
            new CMN_setProperties(null).syncExec(true, new Object[] { nativeProperties });
        }
        
        private static Process createProcess(final String localHostAddress, final int port, final int pid) {
            final List<String> classPathList = new ArrayList<String>();
            final List<Object> referenceList = new ArrayList<Object>();
            final Class<?>[] nativeClassPathReferenceClasses = NativeInterface.getNativeClassPathReferenceClasses(SWTNativeInterface.nativeInterfaceConfiguration);
            if (nativeClassPathReferenceClasses != null) {
                referenceList.addAll(Arrays.asList(nativeClassPathReferenceClasses));
            }
            final String[] nativeClassPathReferenceResources = NativeInterface.getNativeClassPathReferenceResources(SWTNativeInterface.nativeInterfaceConfiguration);
            if (nativeClassPathReferenceResources != null) {
                referenceList.addAll(Arrays.asList(nativeClassPathReferenceResources));
            }
            final List<String> optionalReferenceList = new ArrayList<String>();
            referenceList.add(NativeSwing.class);
            referenceList.add(NativeInterface.class);
            referenceList.add(SWTNativeInterface.class);
            if (SWTNativeInterface.class.getClassLoader() != NativeInterface.class.getClassLoader()) {
                WebServer.getDefaultWebServer().addReferenceClassLoader(SWTNativeInterface.class.getClassLoader());
            }
            referenceList.add("org/eclipse/swt/widgets/Display.class");
            optionalReferenceList.add("org/mozilla/xpcom/Mozilla.class");
            optionalReferenceList.add("org/mozilla/interfaces/nsIWebBrowser.class");
            for (String optionalReference : optionalReferenceList) {
                if (optionalReference.startsWith("/")) {
                    optionalReference = optionalReference.substring(1);
                }
                if (Utils.getResourceWithinJavaModules((Class)SWTNativeInterface.class, '/' + optionalReference) != null) {
                    referenceList.add(optionalReference);
                }
            }
            boolean isProxyClassLoaderUsed = Boolean.parseBoolean(NSSystemPropertySWT.PEERVM_FORCEPROXYCLASSLOADER.get());
            if (!isProxyClassLoaderUsed) {
                for (final Object o : referenceList) {
                    File clazzClassPath;
                    if (o instanceof Class) {
                        clazzClassPath = Utils.getClassPathFile((Class)o);
                    }
                    else {
                        final String resource = (String)o;
                        clazzClassPath = Utils.getClassPathFile(resource);
                        if (Utils.getResourceWithinJavaModules((Class)SWTNativeInterface.class, '/' + resource) == null) {
                            throw new IllegalStateException("A resource that is needed in the classpath is missing: " + o);
                        }
                    }
                    if (clazzClassPath != null) {
                        final String path = clazzClassPath.getAbsolutePath();
                        if (classPathList.contains(path)) {
                            continue;
                        }
                        classPathList.add(path);
                    }
                    else {
                        isProxyClassLoaderUsed = true;
                    }
                }
            }
            if (isProxyClassLoaderUsed) {
                classPathList.clear();
                final File classPathFile = new File(SystemProperty.JAVA_IO_TMPDIR.get(), ".djnativeswing/classpath");
                Utils.deleteAll(classPathFile);
                final String classPath = NetworkURLClassLoader.class.getName().replace('.', '/') + ".class";
                final File mainClassFile = new File(classPathFile, classPath);
                mainClassFile.getParentFile().mkdirs();
                if (!mainClassFile.exists()) {
                    try {
                        final BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(mainClassFile));
                        final BufferedInputStream in = new BufferedInputStream(Utils.getResourceAsStreamWithinJavaModules((Class)SWTNativeInterface.class, "/" + classPath));
                        final byte[] bytes = new byte[1024];
                        int n;
                        while ((n = in.read(bytes)) != -1) {
                            out.write(bytes, 0, n);
                        }
                        in.close();
                        out.close();
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    mainClassFile.deleteOnExit();
                }
                classPathList.add(classPathFile.getAbsolutePath());
            }
            final List<String> vmParamList = new ArrayList<String>();
            final Map<String, String> systemPropertiesMap = new HashMap<String, String>();
            final String[] peerVMParams = NativeInterface.getPeerVMParams(SWTNativeInterface.nativeInterfaceConfiguration);
            boolean isJavaLibraryPathProperySpecified = false;
            boolean isSWTLibraryPathProperySpecified = false;
            if (peerVMParams != null) {
                for (final String peerVMParam : peerVMParams) {
                    if (peerVMParam.startsWith("-D")) {
                        final String property = peerVMParam.substring(2);
                        final int index = property.indexOf(61);
                        final String propertyKey = property.substring(0, index);
                        final String propertyValue = property.substring(index + 1);
                        systemPropertiesMap.put(propertyKey, propertyValue);
                        if (SystemProperty.JAVA_LIBRARY_PATH.getName().equals(propertyKey)) {
                            isJavaLibraryPathProperySpecified = true;
                        }
                        else if ("swt.library.path".equals(propertyKey)) {
                            isSWTLibraryPathProperySpecified = true;
                        }
                    }
                    else {
                        vmParamList.add(peerVMParam);
                    }
                }
            }
            if (!isJavaLibraryPathProperySpecified) {
                final String javaLibraryPath = SystemProperty.JAVA_LIBRARY_PATH.get();
                if (javaLibraryPath != null) {
                    systemPropertiesMap.put(SystemProperty.JAVA_LIBRARY_PATH.getName(), javaLibraryPath);
                }
            }
            if (!isSWTLibraryPathProperySpecified) {
                final String swtLibraryPath = NSSystemPropertySWT.SWT_LIBRARY_PATH.get();
                if (swtLibraryPath != null) {
                    systemPropertiesMap.put(NSSystemPropertySWT.SWT_LIBRARY_PATH.getName(), swtLibraryPath);
                }
            }
            final String[] array2;
            final String[] flags = array2 = new String[] { NSSystemPropertySWT.INTERFACE_SYNCMESSAGES.getName(), NSSystemPropertySWT.INTERFACE_DEBUG_PRINTMESSAGES.getName(), NSSystemPropertySWT.PEERVM_DEBUG_PRINTSTARTMESSAGE.getName(), NSSystemPropertySWT.PEERVM_DEBUG_PRINTSTOPMESSAGE.getName(), NSSystemPropertySWT.SWT_DEVICE_DEBUG.getName(), NSSystemPropertySWT.SWT_DEVICEDATA_DEBUG.getName(), NSSystemPropertySWT.SWT_DEVICEDATA_TRACKING.getName() };
            for (final String flag : array2) {
                if (Boolean.parseBoolean(System.getProperty(flag))) {
                    systemPropertiesMap.put(flag, "true");
                }
            }
            systemPropertiesMap.put(NSSystemProperty.LOCALHOSTADDRESS.getName(), localHostAddress);
            final List<String> mainClassParameterList = new ArrayList<String>();
            String mainClass;
            if (isProxyClassLoaderUsed) {
                mainClass = NetworkURLClassLoader.class.getName();
                mainClassParameterList.add(WebServer.getDefaultWebServer().getClassPathResourceURL("", ""));
                mainClassParameterList.add(NativeInterface.class.getName());
            }
            else {
                mainClass = NativeInterface.class.getName();
            }
            mainClassParameterList.add(String.valueOf(pid));
            mainClassParameterList.add(String.valueOf(port));
            PeerVMProcessFactory peerVMProcessFactory = SWTNativeInterface.nativeInterfaceConfiguration.getPeerVMProcessFactory();
            if (peerVMProcessFactory == null) {
                peerVMProcessFactory = (PeerVMProcessFactory)new DefaultPeerVMProcessFactory();
            }
            Process p = null;
            try {
                p = peerVMProcessFactory.createProcess(classPathList.toArray(new String[0]), systemPropertiesMap, vmParamList.toArray(new String[0]), mainClass, mainClassParameterList.toArray(new String[0]));
            }
            catch (Exception e2) {
                throw new IllegalStateException("Failed to spawn the peer VM!", e2);
            }
            if (p == null) {
                throw new IllegalStateException("Failed to spawn the peer VM!");
            }
            return p;
        }
        
        private static MessagingInterface createOutProcessMessagingInterface() {
            final String localHostAddress = Utils.getLocalHostAddress();
            if (localHostAddress == null) {
                throw new IllegalStateException("Failed to find a suitable local host address to communicate with a spawned VM!");
            }
            final boolean isCreatingProcess = Boolean.parseBoolean(NSSystemPropertySWT.PEERVM_CREATE.get("true"));
            final boolean isProcessIOChannelMode = OutProcess.IS_PROCESS_IO_CHANNEL_MODE && isCreatingProcess;
            int port;
            if (isProcessIOChannelMode) {
                port = 0;
            }
            else {
                port = Integer.parseInt(NSSystemPropertySWT.INTERFACE_PORT.get("-1"));
                if (port <= 0) {
                    ServerSocket serverSocket;
                    try {
                        serverSocket = new ServerSocket();
                        serverSocket.setReuseAddress(false);
                        serverSocket.bind(new InetSocketAddress(InetAddress.getByName(localHostAddress), 0));
                    }
                    catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    port = serverSocket.getLocalPort();
                    try {
                        serverSocket.close();
                    }
                    catch (IOException ex) {}
                }
            }
            final int pid_ = ++OutProcess.pid;
            Process p;
            if (isCreatingProcess) {
                p = createProcess(localHostAddress, port, pid_);
                if (!isProcessIOChannelMode) {
                    connectStream(System.out, p.getInputStream(), "out", pid_);
                }
                connectStream(System.err, p.getErrorStream(), "err", pid_);
            }
            else {
                p = null;
            }
            if (isProcessIOChannelMode) {
                return (MessagingInterface)new OutProcessIOMessagingInterface.SwingOutProcessIOMessagingInterface(p.getInputStream(), p.getOutputStream(), false, p, pid_);
            }
            Exception exception = null;
            Socket socket = null;
            final long peerVMConnectionTimeout = Integer.parseInt(NSSystemPropertySWT.INTERFACE_OUTPROCESS_CONNECTIONTIMEOUT.get("10000"));
            final long startTime = System.currentTimeMillis();
            while (true) {
                if (p != null) {
                    try {
                        p.exitValue();
                        break;
                    }
                    catch (IllegalThreadStateException ex2) {}
                }
                try {
                    socket = new Socket(localHostAddress, port);
                    exception = null;
                }
                catch (Exception e2) {
                    exception = e2;
                    try {
                        Thread.sleep(200L);
                    }
                    catch (Exception ex3) {}
                    if (System.currentTimeMillis() - startTime < peerVMConnectionTimeout) {
                        continue;
                    }
                }
                break;
            }
            if (socket != null) {
                return (MessagingInterface)new OutProcessSocketsMessagingInterface.SwingOutProcessSocketsMessagingInterface(socket, false, p, pid_);
            }
            if (p != null) {
                p.destroy();
            }
            if (exception == null) {
                throw new IllegalStateException("Failed to connect to spawned VM! The native side process was already terminated.");
            }
            throw new IllegalStateException("Failed to connect to spawned VM!", exception);
        }
        
        private static void connectStream(final PrintStream out, final InputStream in, final String name, final int pid) {
            final BufferedInputStream bin = new BufferedInputStream(in);
            final Thread streamThread = (Thread)new lIIIIII("NativeSwing[" + pid + "] " + name + " Stream Connector", pid, bin, out);
            streamThread.setDaemon(true);
            streamThread.start();
        }
        
        static void runNativeSide(final String[] args) throws IOException {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     1: iconst_0       
            //     2: aaload         
            //     3: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
            //     6: istore_1        /* pid */
            //     7: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.PEERVM_DEBUG_PRINTSTARTMESSAGE:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //    10: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //    13: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //    16: ifeq            45
            //    19: getstatic       java/lang/System.err:Ljava/io/PrintStream;
            //    22: new             Ljava/lang/StringBuilder;
            //    25: dup            
            //    26: invokespecial   java/lang/StringBuilder.<init>:()V
            //    29: ldc_w           "Starting peer VM #"
            //    32: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //    35: iload_1         /* pid */
            //    36: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //    39: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //    42: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //    45: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$700:()Ljava/lang/Object;
            //    48: dup            
            //    49: astore_2       
            //    50: monitorenter   
            //    51: iconst_1       
            //    52: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$902:(Z)Z
            //    55: pop            
            //    56: aload_2        
            //    57: monitorexit    
            //    58: goto            66
            //    61: astore_3       
            //    62: aload_2        
            //    63: monitorexit    
            //    64: aload_3        
            //    65: athrow         
            //    66: aload_0         /* args */
            //    67: iconst_1       
            //    68: aaload         
            //    69: invokestatic    java/lang/Integer.parseInt:(Ljava/lang/String;)I
            //    72: istore_2        /* port */
            //    73: iload_2         /* port */
            //    74: ifgt            81
            //    77: iconst_1       
            //    78: goto            82
            //    81: iconst_0       
            //    82: istore_3        /* isProcessIOChannelMode */
            //    83: aconst_null    
            //    84: astore          socket
            //    86: iload_3         /* isProcessIOChannelMode */
            //    87: ifne            286
            //    90: aconst_null    
            //    91: astore          serverSocket
            //    93: invokestatic    java/lang/System.currentTimeMillis:()J
            //    96: lstore          startTime
            //    98: new             Ljava/net/ServerSocket;
            //   101: dup            
            //   102: invokespecial   java/net/ServerSocket.<init>:()V
            //   105: astore          serverSocket
            //   107: aload           serverSocket
            //   109: iconst_1       
            //   110: invokevirtual   java/net/ServerSocket.setReuseAddress:(Z)V
            //   113: aload           serverSocket
            //   115: new             Ljava/net/InetSocketAddress;
            //   118: dup            
            //   119: invokestatic    chrriis/dj/nativeswing/common/Utils.getLocalHostAddress:()Ljava/lang/String;
            //   122: iload_2         /* port */
            //   123: invokespecial   java/net/InetSocketAddress.<init>:(Ljava/lang/String;I)V
            //   126: invokevirtual   java/net/ServerSocket.bind:(Ljava/net/SocketAddress;)V
            //   129: aconst_null    
            //   130: astore          exception
            //   132: goto            183
            //   135: astore          e
            //   137: aload           e
            //   139: astore          exception
            //   141: aload           serverSocket
            //   143: ifnull          156
            //   146: aload           serverSocket
            //   148: invokevirtual   java/net/ServerSocket.close:()V
            //   151: goto            156
            //   154: astore          10
            //   156: aconst_null    
            //   157: astore          serverSocket
            //   159: ldc2_w          200
            //   162: invokestatic    java/lang/Thread.sleep:(J)V
            //   165: goto            170
            //   168: astore          9
            //   170: invokestatic    java/lang/System.currentTimeMillis:()J
            //   173: lload           startTime
            //   175: lsub           
            //   176: ldc2_w          5000
            //   179: lcmp           
            //   180: iflt            98
            //   183: aload           serverSocket
            //   185: ifnonnull       207
            //   188: aload           exception
            //   190: ifnonnull       204
            //   193: new             Ljava/lang/IllegalStateException;
            //   196: dup            
            //   197: ldc_w           "Failed to create the server socket for native side communication!"
            //   200: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
            //   203: athrow         
            //   204: aload           exception
            //   206: athrow         
            //   207: aload           serverSocket
            //   209: astore          serverSocket_
            //   211: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.PEERVM_KEEPALIVE:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //   214: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //   217: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   220: ifne            248
            //   223: new             Lchrriis/dj/nativeswing/swtimpl/core/lllIIII;
            //   226: dup            
            //   227: ldc_w           "NativeSwing Shutdown"
            //   230: aload           serverSocket_
            //   232: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lllIIII.<init>:(Ljava/lang/String;Ljava/net/ServerSocket;)V
            //   235: astore          shutdownThread
            //   237: aload           shutdownThread
            //   239: iconst_1       
            //   240: invokevirtual   java/lang/Thread.setDaemon:(Z)V
            //   243: aload           shutdownThread
            //   245: invokevirtual   java/lang/Thread.start:()V
            //   248: invokestatic    java/lang/Runtime.getRuntime:()Ljava/lang/Runtime;
            //   251: new             Lchrriis/dj/nativeswing/swtimpl/core/llIIlIl;
            //   254: dup            
            //   255: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIIlIl.<init>:()V
            //   258: invokevirtual   java/lang/Runtime.addShutdownHook:(Ljava/lang/Thread;)V
            //   261: aload           serverSocket
            //   263: invokevirtual   java/net/ServerSocket.accept:()Ljava/net/Socket;
            //   266: astore          socket
            //   268: goto            286
            //   271: astore          e
            //   273: new             Ljava/lang/IllegalStateException;
            //   276: dup            
            //   277: ldc_w           "The native side did not receive an incoming connection!"
            //   280: aload           e
            //   282: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;Ljava/lang/Throwable;)V
            //   285: athrow         
            //   286: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.SWT_DEVICE_DEBUG:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //   289: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //   292: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   295: putstatic       org/eclipse/swt/graphics/Device.DEBUG:Z
            //   298: new             Lorg/eclipse/swt/graphics/DeviceData;
            //   301: dup            
            //   302: invokespecial   org/eclipse/swt/graphics/DeviceData.<init>:()V
            //   305: astore          data
            //   307: aload           data
            //   309: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.SWT_DEVICEDATA_DEBUG:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //   312: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //   315: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   318: putfield        org/eclipse/swt/graphics/DeviceData.debug:Z
            //   321: aload           data
            //   323: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.SWT_DEVICEDATA_TRACKING:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //   326: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //   329: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   332: putfield        org/eclipse/swt/graphics/DeviceData.tracking:Z
            //   335: new             Lorg/eclipse/swt/widgets/Display;
            //   338: dup            
            //   339: aload           data
            //   341: invokespecial   org/eclipse/swt/widgets/Display.<init>:(Lorg/eclipse/swt/graphics/DeviceData;)V
            //   344: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$402:(Lorg/eclipse/swt/widgets/Display;)Lorg/eclipse/swt/widgets/Display;
            //   347: pop            
            //   348: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   351: bipush          21
            //   353: new             Lchrriis/dj/nativeswing/swtimpl/core/llIIIlI;
            //   356: dup            
            //   357: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIIIlI.<init>:()V
            //   360: invokevirtual   org/eclipse/swt/widgets/Display.addListener:(ILorg/eclipse/swt/widgets/Listener;)V
            //   363: ldc_w           "DJ Native Swing"
            //   366: invokestatic    org/eclipse/swt/widgets/Display.setAppName:(Ljava/lang/String;)V
            //   369: iload_3         /* isProcessIOChannelMode */
            //   370: ifeq            506
            //   373: getstatic       java/lang/System.out:Ljava/io/PrintStream;
            //   376: astore          sysout
            //   378: getstatic       java/lang/System.in:Ljava/io/InputStream;
            //   381: astore          sysin
            //   383: new             Lchrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface$SWTOutProcessIOMessagingInterface;
            //   386: dup            
            //   387: aload           sysin
            //   389: aload           sysout
            //   391: iconst_1       
            //   392: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   395: iload_1         /* pid */
            //   396: invokespecial   chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface$SWTOutProcessIOMessagingInterface.<init>:(Ljava/io/InputStream;Ljava/io/OutputStream;ZLorg/eclipse/swt/widgets/Display;I)V
            //   399: astore          outInterface
            //   401: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$700:()Ljava/lang/Object;
            //   404: dup            
            //   405: astore          9
            //   407: monitorenter   
            //   408: aload           outInterface
            //   410: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$802:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
            //   413: pop            
            //   414: aload           9
            //   416: monitorexit    
            //   417: goto            428
            //   420: astore          11
            //   422: aload           9
            //   424: monitorexit    
            //   425: aload           11
            //   427: athrow         
            //   428: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIllII;
            //   431: dup            
            //   432: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIllII.<init>:()V
            //   435: invokestatic    java/lang/System.setIn:(Ljava/io/InputStream;)V
            //   438: new             Ljava/io/PrintStream;
            //   441: dup            
            //   442: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIIlIl;
            //   445: dup            
            //   446: iload_1         /* pid */
            //   447: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIIlIl.<init>:(I)V
            //   450: invokespecial   java/io/PrintStream.<init>:(Ljava/io/OutputStream;)V
            //   453: invokestatic    java/lang/System.setOut:(Ljava/io/PrintStream;)V
            //   456: getstatic       chrriis/dj/nativeswing/common/Utils.IS_WINDOWS:Z
            //   459: ifeq            503
            //   462: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$700:()Ljava/lang/Object;
            //   465: dup            
            //   466: astore          10
            //   468: monitorenter   
            //   469: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$800:()Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
            //   472: astore          messagingInterface_
            //   474: aload           10
            //   476: monitorexit    
            //   477: goto            488
            //   480: astore          12
            //   482: aload           10
            //   484: monitorexit    
            //   485: aload           12
            //   487: athrow         
            //   488: new             Lchrriis/dj/nativeswing/swtimpl/core/lIlIIll;
            //   491: dup            
            //   492: ldc_w           "System.in unlocker"
            //   495: aload           messagingInterface_
            //   497: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIlIIll.<init>:(Ljava/lang/String;Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)V
            //   500: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/lIlIIll.start:()V
            //   503: goto            549
            //   506: new             Lchrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface$SWTOutProcessSocketsMessagingInterface;
            //   509: dup            
            //   510: aload           socket
            //   512: iconst_1       
            //   513: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   516: iload_1         /* pid */
            //   517: invokespecial   chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface$SWTOutProcessSocketsMessagingInterface.<init>:(Ljava/net/Socket;ZLorg/eclipse/swt/widgets/Display;I)V
            //   520: astore          outInterface
            //   522: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$700:()Ljava/lang/Object;
            //   525: dup            
            //   526: astore          7
            //   528: monitorenter   
            //   529: aload           outInterface
            //   531: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$802:(Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;)Lchrriis/dj/nativeswing/swtimpl/core/MessagingInterface;
            //   534: pop            
            //   535: aload           7
            //   537: monitorexit    
            //   538: goto            549
            //   541: astore          13
            //   543: aload           7
            //   545: monitorexit    
            //   546: aload           13
            //   548: athrow         
            //   549: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   552: ifnull          614
            //   555: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   558: invokevirtual   org/eclipse/swt/widgets/Display.isDisposed:()Z
            //   561: ifne            614
            //   564: invokestatic    java/lang/System.currentTimeMillis:()J
            //   567: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$2102:(J)J
            //   570: pop2           
            //   571: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   574: invokevirtual   org/eclipse/swt/widgets/Display.readAndDispatch:()Z
            //   577: ifne            594
            //   580: ldc2_w          9223372036854775807
            //   583: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$2102:(J)J
            //   586: pop2           
            //   587: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
            //   590: invokevirtual   org/eclipse/swt/widgets/Display.sleep:()Z
            //   593: pop            
            //   594: ldc2_w          9223372036854775807
            //   597: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$2102:(J)J
            //   600: pop2           
            //   601: goto            549
            //   604: astore          t
            //   606: aload           t
            //   608: invokevirtual   java/lang/Throwable.printStackTrace:()V
            //   611: goto            549
            //   614: getstatic       chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.PEERVM_DEBUG_PRINTSTOPMESSAGE:Lchrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT;
            //   617: invokevirtual   chrriis/dj/nativeswing/swtimpl/NSSystemPropertySWT.get:()Ljava/lang/String;
            //   620: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
            //   623: ifeq            652
            //   626: getstatic       java/lang/System.err:Ljava/io/PrintStream;
            //   629: new             Ljava/lang/StringBuilder;
            //   632: dup            
            //   633: invokespecial   java/lang/StringBuilder.<init>:()V
            //   636: ldc_w           "Stopping peer VM #"
            //   639: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
            //   642: iload_1         /* pid */
            //   643: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
            //   646: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
            //   649: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
            //   652: return         
            //    Exceptions:
            //  throws java.io.IOException
            //    StackMapTable: 00 1D FC 00 2D 01 FF 00 0F 00 03 07 00 C9 01 07 00 04 00 01 07 00 71 FA 00 04 FC 00 0E 01 40 01 FF 00 0F 00 07 07 00 C9 01 01 01 07 02 76 07 02 31 04 00 00 64 07 02 16 FF 00 12 00 09 07 00 C9 01 01 01 07 02 76 07 02 31 04 07 02 16 07 02 16 00 01 07 00 AA 01 FF 00 0B 00 08 07 00 C9 01 01 01 07 02 76 07 02 31 04 07 02 16 00 01 07 00 AA 01 0C 14 02 FC 00 28 07 02 31 56 07 00 AA FF 00 0E 00 05 07 00 C9 01 01 01 07 02 76 00 00 FF 00 85 00 0A 07 00 C9 01 01 01 07 02 76 07 02 D5 07 02 B7 07 02 F6 07 00 2C 07 00 04 00 01 07 00 71 FA 00 07 FF 00 33 00 0B 07 00 C9 01 01 01 07 02 76 07 02 D5 07 02 B7 07 02 F6 07 00 2C 00 07 00 04 00 01 07 00 71 FF 00 07 00 0A 07 00 C9 01 01 01 07 02 76 07 02 D5 07 02 B7 07 02 F6 07 00 2C 07 03 0A 00 00 FF 00 0E 00 06 07 00 C9 01 01 01 07 02 76 07 02 D5 00 00 02 FF 00 22 00 08 07 00 C9 01 01 01 07 02 76 07 02 D5 07 00 31 07 00 04 00 01 07 00 71 F9 00 07 2C 49 07 00 71 09 25
            //    Exceptions:
            //  Try           Handler
            //  Start  End    Start  End    Type                 
            //  -----  -----  -----  -----  ---------------------
            //  51     58     61     66     Any
            //  61     64     61     66     Any
            //  98     132    135    183    Ljava/io/IOException;
            //  146    151    154    156    Ljava/lang/Exception;
            //  159    165    168    170    Ljava/lang/Exception;
            //  261    268    271    286    Ljava/lang/Exception;
            //  408    417    420    428    Any
            //  420    425    420    428    Any
            //  469    477    480    488    Any
            //  480    485    480    488    Any
            //  529    538    541    549    Any
            //  541    546    541    549    Any
            //  564    601    604    614    Ljava/lang/Throwable;
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
            //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:441)
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
        
        static void runEventPump() {
            while (SWTNativeInterface.isEventPumpRunning) {
                try {
                    Thread.sleep(1000L);
                }
                catch (Exception ex) {}
            }
        }
        
        static {
            IS_PROCESS_IO_CHANNEL_MODE = "processio".equals(NSSystemPropertySWT.INTERFACE_OUTPROCESS_COMMUNICATION.get());
        }
        
        private static class CMN_destroyControls extends CommandMessage
        {
            public Object run(final Object[] args) throws Exception {
                // 
                // This method could not be decompiled.
                // 
                // Original Bytecode:
                // 
                //     3: ifnull          29
                //     6: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
                //     9: invokevirtual   org/eclipse/swt/widgets/Display.isDisposed:()Z
                //    12: ifne            29
                //    15: invokestatic    chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface.access$400:()Lorg/eclipse/swt/widgets/Display;
                //    18: new             Lchrriis/dj/nativeswing/swtimpl/core/llIlIll;
                //    21: dup            
                //    22: aload_0         /* this */
                //    23: invokespecial   chrriis/dj/nativeswing/swtimpl/core/llIlIll.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMN_destroyControls;)V
                //    26: invokevirtual   org/eclipse/swt/widgets/Display.syncExec:(Ljava/lang/Runnable;)V
                //    29: aconst_null    
                //    30: areturn        
                //    Exceptions:
                //  throws java.lang.Exception
                //    StackMapTable: 00 01 1D
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
        
        private static class CMN_setProperties extends CommandMessage
        {
            public Object run(final Object[] args) {
                final Properties systemProperties = System.getProperties();
                final Properties properties = (Properties)args[0];
                for (final Object o : properties.keySet()) {
                    if (!systemProperties.containsKey(o)) {
                        try {
                            System.setProperty((String)o, properties.getProperty((String)o));
                        }
                        catch (Exception ex) {}
                    }
                }
                return null;
            }
        }
        
        private static class IOStreamFormatter
        {
            private ByteArrayOutputStream baos;
            private byte lastByte;
            private boolean isAddingMessage;
            private final byte[] prefixBytes;
            
            public IOStreamFormatter(final int pid) {
                this.baos = new ByteArrayOutputStream();
                this.lastByte = (byte)Utils.LINE_SEPARATOR.charAt(Utils.LINE_SEPARATOR.length() - 1);
                this.isAddingMessage = true;
                this.prefixBytes = ("NativeSwing[" + pid + "]: ").getBytes();
            }
            
            public byte[] process(final byte[] bytes, final int offset, final int length) throws IOException {
                this.baos.reset();
                for (int i = offset; i < length; ++i) {
                    final byte b = bytes[i];
                    if (this.isAddingMessage) {
                        this.baos.write(this.prefixBytes);
                    }
                    this.isAddingMessage = (b == this.lastByte);
                    this.baos.write(b);
                }
                return this.baos.toByteArray();
            }
        }
        
        private static class CMJ_handleClosedDisplay extends CommandMessage
        {
            public Object run(final Object[] args) {
                handleQuit();
                return null;
            }
        }
        
        private static class CMJ_systemOut extends CommandMessage
        {
            public Object run(final Object[] args) {
                try {
                    System.out.write((byte[])args[0]);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }
        }
        
        private static class CMJ_unlockSystemIn extends CommandMessage
        {
            public Object run(final Object[] args) throws Exception {
                new Message().asyncSend(true);
                return null;
            }
        }
    }
}
