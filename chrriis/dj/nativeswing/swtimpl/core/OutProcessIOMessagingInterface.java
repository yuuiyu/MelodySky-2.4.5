//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.io.*;
import chrriis.dj.nativeswing.swtimpl.*;
import org.eclipse.swt.widgets.*;
import javax.swing.*;

abstract class OutProcessIOMessagingInterface extends MessagingInterface
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private InputStream is;
    private OutputStream os;
    private static final int OOS_RESET_THRESHOLD;
    private int oosByteCount;
    
    public OutProcessIOMessagingInterface(final boolean isNativeSide, final InputStream is, final OutputStream os, final boolean exitOnEndOfStream, final int pid) {
        super(isNativeSide, pid);
        this.is = is;
        this.os = os;
        this.initialize(exitOnEndOfStream);
    }
    
    @Override
    public void destroy() {
        this.setAlive(false);
        try {
            this.oos.close();
        }
        catch (Exception ex) {}
        try {
            this.ois.close();
        }
        catch (Exception ex2) {}
    }
    
    @Override
    protected void openChannel() {
        class lIIIllI extends BufferedOutputStream
        {
            final /* synthetic */ OutProcessIOMessagingInterface this$0;
            
            lIIIllI(final OutProcessIOMessagingInterface this$0, final OutputStream x0) {
                this.this$0 = this$0;
                super(x0);
            }
            
            @Override
            public synchronized void write(final int b) throws IOException {
                super.write(b);
                this.this$0.oosByteCount++;
            }
            
            @Override
            public synchronized void write(final byte[] b, final int off, final int len) throws IOException {
                super.write(b, off, len);
                this.this$0.oosByteCount += len;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: new             Ljava/io/ObjectOutputStream;
        //     4: dup            
        //     5: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIIllI;
        //     8: dup            
        //     9: aload_0         /* this */
        //    10: aload_0         /* this */
        //    11: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface.os:Ljava/io/OutputStream;
        //    14: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIIllI.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface;Ljava/io/OutputStream;)V
        //    17: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    20: putfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface.oos:Ljava/io/ObjectOutputStream;
        //    23: aload_0         /* this */
        //    24: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface.oos:Ljava/io/ObjectOutputStream;
        //    27: invokevirtual   java/io/ObjectOutputStream.flush:()V
        //    30: aload_0         /* this */
        //    31: new             Ljava/io/ObjectInputStream;
        //    34: dup            
        //    35: new             Ljava/io/BufferedInputStream;
        //    38: dup            
        //    39: aload_0         /* this */
        //    40: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface.is:Ljava/io/InputStream;
        //    43: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    46: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    49: putfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessIOMessagingInterface.ois:Ljava/io/ObjectInputStream;
        //    52: goto            65
        //    55: astore_1        /* e */
        //    56: new             Ljava/lang/RuntimeException;
        //    59: dup            
        //    60: aload_1         /* e */
        //    61: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    64: athrow         
        //    65: return         
        //    StackMapTable: 00 02 77 07 00 41 09
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      52     55     65     Ljava/io/IOException;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    @Override
    protected void closeChannel() {
        try {
            this.oos.close();
        }
        catch (Exception ex) {}
        try {
            this.ois.close();
        }
        catch (Exception ex2) {}
        try {
            this.is.close();
        }
        catch (Exception ex3) {}
        this.is = null;
        try {
            this.os.close();
        }
        catch (Exception ex4) {}
        this.os = null;
    }
    
    @Override
    protected void writeMessageToChannel(final Message message) throws IOException {
        synchronized (this.oos) {
            this.oos.writeUnshared(message);
            this.oos.flush();
            if (this.oosByteCount > OutProcessIOMessagingInterface.OOS_RESET_THRESHOLD) {
                this.oos.reset();
                this.oosByteCount = 0;
            }
        }
    }
    
    @Override
    protected Message readMessageFromChannel() throws IOException, ClassNotFoundException {
        final Object o = this.ois.readUnshared();
        if (o instanceof Message) {
            final Message message = (Message)o;
            if (OutProcessIOMessagingInterface.IS_DEBUGGING_MESSAGES) {
                System.err.println("RECV: " + SWTNativeInterface.getMessageID(message) + ", " + message);
            }
            return message;
        }
        return null;
    }
    
    static {
        final String maxByteCountProperty = NSSystemPropertySWT.INTERFACE_STREAMRESETTHRESHOLD.get();
        if (maxByteCountProperty != null) {
            OOS_RESET_THRESHOLD = Integer.parseInt(maxByteCountProperty);
        }
        else {
            OOS_RESET_THRESHOLD = 500000;
        }
    }
    
    static class SWTOutProcessIOMessagingInterface extends OutProcessIOMessagingInterface
    {
        private Display display;
        
        public SWTOutProcessIOMessagingInterface(final InputStream is, final OutputStream os, final boolean exitOnEndOfStream, final Display display, final int pid) {
            super(true, is, os, exitOnEndOfStream, pid);
            this.display = display;
        }
        
        @Override
        protected void asyncUIExec(final Runnable runnable) {
            this.display.asyncExec(runnable);
        }
        
        @Override
        public boolean isUIThread() {
            return Thread.currentThread() == this.display.getThread();
        }
    }
    
    static class SwingOutProcessIOMessagingInterface extends OutProcessIOMessagingInterface
    {
        private final Process process;
        
        public SwingOutProcessIOMessagingInterface(final InputStream is, final OutputStream os, final boolean exitOnEndOfStream, final Process process, final int pid) {
            super(false, is, os, exitOnEndOfStream, pid);
            this.process = process;
        }
        
        @Override
        protected void asyncUIExec(final Runnable runnable) {
            SwingUtilities.invokeLater(runnable);
        }
        
        @Override
        public boolean isUIThread() {
            return SwingUtilities.isEventDispatchThread();
        }
        
        @Override
        public void destroy() {
            super.destroy();
            if (this.process != null && Boolean.parseBoolean(NSSystemPropertySWT.INTERFACE_OUTPROCESS_SYNCCLOSING.get())) {
                while (true) {
                    try {
                        this.process.waitFor();
                    }
                    catch (InterruptedException ex) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
}
