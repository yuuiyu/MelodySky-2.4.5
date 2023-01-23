//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.util.*;
import java.io.*;
import chrriis.dj.nativeswing.swtimpl.*;
import org.eclipse.swt.widgets.*;
import javax.swing.*;

abstract class InProcessMessagingInterface extends MessagingInterface
{
    private static final boolean IS_PRINTING_NON_SERIALIZABLE_MESSAGES;
    private volatile InProcessMessagingInterface mirrorMessagingInterface;
    private List<Message> sentMessageList;
    
    public InProcessMessagingInterface(final boolean isNativeSide, final int pid) {
        super(isNativeSide, pid);
        this.sentMessageList = new LinkedList<Message>();
    }
    
    @Override
    public void destroy() {
        class lIIlllI implements Runnable
        {
            final /* synthetic */ Control val$control;
            final /* synthetic */ InProcessMessagingInterface this$0;
            
            lIIlllI(final InProcessMessagingInterface this$0, final Control val$control) {
                this.this$0 = this$0;
                this.val$control = val$control;
            }
            
            @Override
            public void run() {
                this.val$control.getShell().dispose();
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: astore_1        /* controlRegistry */
        //     4: aload_1         /* controlRegistry */
        //     5: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.getInstanceIDs:()[I
        //     8: astore_2       
        //     9: aload_2        
        //    10: arraylength    
        //    11: istore_3       
        //    12: iconst_0       
        //    13: istore          4
        //    15: iload           4
        //    17: iload_3        
        //    18: if_icmpge       68
        //    21: aload_2        
        //    22: iload           4
        //    24: iaload         
        //    25: istore          instanceID
        //    27: aload_1         /* controlRegistry */
        //    28: iload           instanceID
        //    30: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.get:(I)Ljava/lang/Object;
        //    33: checkcast       Lorg/eclipse/swt/widgets/Control;
        //    36: astore          control
        //    38: aload_1         /* controlRegistry */
        //    39: iload           instanceID
        //    41: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.remove:(I)V
        //    44: aload           control
        //    46: invokevirtual   org/eclipse/swt/widgets/Control.getDisplay:()Lorg/eclipse/swt/widgets/Display;
        //    49: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIlllI;
        //    52: dup            
        //    53: aload_0         /* this */
        //    54: aload           control
        //    56: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIlllI.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface;Lorg/eclipse/swt/widgets/Control;)V
        //    59: invokevirtual   org/eclipse/swt/widgets/Display.asyncExec:(Ljava/lang/Runnable;)V
        //    62: iinc            4, 1
        //    65: goto            15
        //    68: aload_0         /* this */
        //    69: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.getMirrorMessagingInterface:()Lchrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface;
        //    72: astore_2        /* mirrorMessagingInterface */
        //    73: aload_0         /* this */
        //    74: iconst_0       
        //    75: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.setAlive:(Z)V
        //    78: aload_2         /* mirrorMessagingInterface */
        //    79: iconst_0       
        //    80: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.setAlive:(Z)V
        //    83: aload_0         /* this */
        //    84: getfield        chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.sentMessageList:Ljava/util/List;
        //    87: dup            
        //    88: astore_3       
        //    89: monitorenter   
        //    90: aload_0         /* this */
        //    91: getfield        chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.sentMessageList:Ljava/util/List;
        //    94: invokevirtual   java/lang/Object.notifyAll:()V
        //    97: aload_3        
        //    98: monitorexit    
        //    99: goto            109
        //   102: astore          7
        //   104: aload_3        
        //   105: monitorexit    
        //   106: aload           7
        //   108: athrow         
        //   109: aload_2         /* mirrorMessagingInterface */
        //   110: getfield        chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.sentMessageList:Ljava/util/List;
        //   113: dup            
        //   114: astore_3       
        //   115: monitorenter   
        //   116: aload_2         /* mirrorMessagingInterface */
        //   117: getfield        chrriis/dj/nativeswing/swtimpl/core/InProcessMessagingInterface.sentMessageList:Ljava/util/List;
        //   120: invokevirtual   java/lang/Object.notifyAll:()V
        //   123: aload_3        
        //   124: monitorexit    
        //   125: goto            135
        //   128: astore          8
        //   130: aload_3        
        //   131: monitorexit    
        //   132: aload           8
        //   134: athrow         
        //   135: return         
        //    StackMapTable: 00 06 FF 00 0F 00 05 07 00 02 07 00 2C 07 00 32 01 01 00 00 34 FF 00 21 00 05 07 00 02 07 00 2C 07 00 02 07 00 58 01 00 01 07 00 5A 06 52 07 00 5A 06
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type
        //  -----  -----  -----  -----  ----
        //  90     99     102    109    Any
        //  102    106    102    109    Any
        //  116    125    128    135    Any
        //  128    132    128    135    Any
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected void openChannel() {
    }
    
    @Override
    protected void closeChannel() {
    }
    
    protected void setMirrorMessagingInterface(final InProcessMessagingInterface mirrorMessagingInterface) {
        this.mirrorMessagingInterface = mirrorMessagingInterface;
    }
    
    public InProcessMessagingInterface getMirrorMessagingInterface() {
        return this.mirrorMessagingInterface;
    }
    
    Message getNextMessage() {
        boolean isAlive = this.isAlive();
        synchronized (this.sentMessageList) {
            while (this.sentMessageList.isEmpty()) {
                try {
                    this.sentMessageList.wait();
                }
                catch (InterruptedException ex) {}
                isAlive = this.isAlive();
                if (!isAlive) {
                    break;
                }
            }
            if (!isAlive) {
                this.sentMessageList.clear();
                throw new IllegalStateException("The interface is closed.");
            }
            return this.sentMessageList.remove(0);
        }
    }
    
    @Override
    protected Message readMessageFromChannel() throws IOException, ClassNotFoundException {
        return this.mirrorMessagingInterface.getNextMessage();
    }
    
    @Override
    protected void writeMessageToChannel(final Message message) throws IOException {
        if (InProcessMessagingInterface.IS_PRINTING_NON_SERIALIZABLE_MESSAGES && !(message instanceof NoSerializationTestMessage)) {
            final ObjectOutputStream oos = new ObjectOutputStream(new ByteArrayOutputStream());
            try {
                oos.writeObject(message);
            }
            catch (Exception e) {
                System.err.println("Non-serializable message: " + message);
            }
            oos.close();
        }
        synchronized (this.sentMessageList) {
            this.sentMessageList.add(message);
            this.sentMessageList.notifyAll();
        }
    }
    
    static {
        IS_PRINTING_NON_SERIALIZABLE_MESSAGES = Boolean.parseBoolean(NSSystemPropertySWT.INTERFACE_INPROCESS_PRINTNONSERIALIZABLEMESSAGES.get());
    }
    
    static class SWTInProcessMessagingInterface extends InProcessMessagingInterface
    {
        private Display display;
        
        public SWTInProcessMessagingInterface(final Display display, final int pid) {
            super(true, pid);
            this.display = display;
            this.setMirrorMessagingInterface(new SwingInProcessMessagingInterface(this, pid));
            this.initialize(false);
        }
        
        @Override
        protected void asyncUIExec(final Runnable runnable) {
            this.display.asyncExec(runnable);
        }
        
        @Override
        public boolean isUIThread() {
            return this.display.getThread() == Thread.currentThread();
        }
    }
    
    static class SwingInProcessMessagingInterface extends InProcessMessagingInterface
    {
        public SwingInProcessMessagingInterface(final InProcessMessagingInterface mirrorMessagingInterface, final int pid) {
            super(false, pid);
            this.setMirrorMessagingInterface(mirrorMessagingInterface);
            this.initialize(false);
        }
        
        @Override
        protected void asyncUIExec(final Runnable runnable) {
            SwingUtilities.invokeLater(runnable);
        }
        
        @Override
        public boolean isUIThread() {
            return SwingUtilities.isEventDispatchThread();
        }
    }
}
