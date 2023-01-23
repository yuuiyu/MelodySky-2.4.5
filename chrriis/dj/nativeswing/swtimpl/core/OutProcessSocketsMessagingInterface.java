//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.net.*;
import java.io.*;
import chrriis.dj.nativeswing.swtimpl.*;
import org.eclipse.swt.widgets.*;
import javax.swing.*;

abstract class OutProcessSocketsMessagingInterface extends MessagingInterface
{
    private ObjectOutputStream oos;
    private ObjectInputStream ois;
    private Socket socket;
    private static final int OOS_RESET_THRESHOLD;
    private int oosByteCount;
    
    public OutProcessSocketsMessagingInterface(final boolean isNativeSide, final Socket socket, final boolean exitOnEndOfStream, final int pid) {
        super(isNativeSide, pid);
        this.socket = socket;
        this.initialize(exitOnEndOfStream);
    }
    
    @Override
    public void destroy() {
        this.setAlive(false);
        try {
            this.ois.close();
        }
        catch (Exception ex) {}
    }
    
    @Override
    protected void openChannel() {
        class lIIlIII extends BufferedOutputStream
        {
            final /* synthetic */ OutProcessSocketsMessagingInterface this$0;
            
            lIIlIII(final OutProcessSocketsMessagingInterface this$0, final OutputStream x0) {
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
        //     5: new             Lchrriis/dj/nativeswing/swtimpl/core/lIIlIII;
        //     8: dup            
        //     9: aload_0         /* this */
        //    10: aload_0         /* this */
        //    11: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface.socket:Ljava/net/Socket;
        //    14: invokevirtual   java/net/Socket.getOutputStream:()Ljava/io/OutputStream;
        //    17: invokespecial   chrriis/dj/nativeswing/swtimpl/core/lIIlIII.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface;Ljava/io/OutputStream;)V
        //    20: invokespecial   java/io/ObjectOutputStream.<init>:(Ljava/io/OutputStream;)V
        //    23: putfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface.oos:Ljava/io/ObjectOutputStream;
        //    26: aload_0         /* this */
        //    27: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface.oos:Ljava/io/ObjectOutputStream;
        //    30: invokevirtual   java/io/ObjectOutputStream.flush:()V
        //    33: aload_0         /* this */
        //    34: new             Ljava/io/ObjectInputStream;
        //    37: dup            
        //    38: new             Ljava/io/BufferedInputStream;
        //    41: dup            
        //    42: aload_0         /* this */
        //    43: getfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface.socket:Ljava/net/Socket;
        //    46: invokevirtual   java/net/Socket.getInputStream:()Ljava/io/InputStream;
        //    49: invokespecial   java/io/BufferedInputStream.<init>:(Ljava/io/InputStream;)V
        //    52: invokespecial   java/io/ObjectInputStream.<init>:(Ljava/io/InputStream;)V
        //    55: putfield        chrriis/dj/nativeswing/swtimpl/core/OutProcessSocketsMessagingInterface.ois:Ljava/io/ObjectInputStream;
        //    58: goto            71
        //    61: astore_1        /* e */
        //    62: new             Ljava/lang/RuntimeException;
        //    65: dup            
        //    66: aload_1         /* e */
        //    67: invokespecial   java/lang/RuntimeException.<init>:(Ljava/lang/Throwable;)V
        //    70: athrow         
        //    71: return         
        //    StackMapTable: 00 02 7D 07 00 38 09
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      58     61     71     Ljava/io/IOException;
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
            this.socket.close();
        }
        catch (Exception ex3) {}
        this.socket = null;
    }
    
    @Override
    protected void writeMessageToChannel(final Message message) throws IOException {
        synchronized (this.oos) {
            this.oos.writeUnshared(message);
            this.oos.flush();
            if (this.oosByteCount > OutProcessSocketsMessagingInterface.OOS_RESET_THRESHOLD) {
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
            if (OutProcessSocketsMessagingInterface.IS_DEBUGGING_MESSAGES) {
                System.err.println("RECV: " + SWTNativeInterface.getMessageID(message) + ", " + message);
            }
            return message;
        }
        System.err.println("Unknown message: " + o);
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
    
    static class SWTOutProcessSocketsMessagingInterface extends OutProcessSocketsMessagingInterface
    {
        private Display display;
        
        public SWTOutProcessSocketsMessagingInterface(final Socket socket, final boolean exitOnEndOfStream, final Display display, final int pid) {
            super(true, socket, exitOnEndOfStream, pid);
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
        
        @Override
        protected void terminate() {
            if (this.isNativeSide() && Boolean.parseBoolean(NSSystemPropertySWT.PEERVM_DEBUG_PRINTSTOPMESSAGE.get())) {
                System.err.println("Stopping peer VM #" + this.getPID());
            }
            super.terminate();
        }
    }
    
    static class SwingOutProcessSocketsMessagingInterface extends OutProcessSocketsMessagingInterface
    {
        private final Process process;
        
        public SwingOutProcessSocketsMessagingInterface(final Socket socket, final boolean exitOnEndOfStream, final Process process, final int pid) {
            super(false, socket, exitOnEndOfStream, pid);
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
