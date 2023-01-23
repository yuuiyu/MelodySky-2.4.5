//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.io.*;

final class lIIIlIl extends OutputStream
{
    private SWTNativeInterface.OutProcess.IOStreamFormatter byteProcessor;
    final /* synthetic */ int val$pid;
    
    lIIIlIl(final int val$pid) {
        this.val$pid = val$pid;
        this.byteProcessor = new SWTNativeInterface.OutProcess.IOStreamFormatter(this.val$pid);
    }
    
    @Override
    public void write(final int b) throws IOException {
        this.sendBytes(new byte[] { (byte)b }, 0, 1);
    }
    
    @Override
    public void write(final byte[] b) throws IOException {
        this.sendBytes(b, 0, b.length);
    }
    
    @Override
    public void write(final byte[] b, final int off, final int len) throws IOException {
        this.sendBytes(b, off, len);
    }
    
    private void sendBytes(final byte[] bytes, final int offset, final int length) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: aconst_null    
        //     5: invokespecial   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMJ_systemOut.<init>:(Lchrriis/dj/nativeswing/swtimpl/core/lIllllI;)V
        //     8: iconst_0       
        //     9: iconst_1       
        //    10: anewarray       Ljava/lang/Object;
        //    13: dup            
        //    14: iconst_0       
        //    15: aload_0         /* this */
        //    16: getfield        chrriis/dj/nativeswing/swtimpl/core/lIIIlIl.byteProcessor:Lchrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$IOStreamFormatter;
        //    19: aload_1         /* bytes */
        //    20: iload_2         /* offset */
        //    21: iload_3         /* length */
        //    22: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$IOStreamFormatter.process:([BII)[B
        //    25: aastore        
        //    26: invokevirtual   chrriis/dj/nativeswing/swtimpl/core/SWTNativeInterface$OutProcess$CMJ_systemOut.asyncExec:(Z[Ljava/lang/Object;)V
        //    29: goto            34
        //    32: astore          4
        //    34: return         
        //    StackMapTable: 00 02 60 07 00 34 01
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                 
        //  -----  -----  -----  -----  ---------------------
        //  0      29     32     34     Ljava/lang/Exception;
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
}
