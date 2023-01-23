//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.io.*;

final class lIIIIII extends Thread
{
    private SWTNativeInterface.OutProcess.IOStreamFormatter byteProcessor;
    final /* synthetic */ int val$pid;
    final /* synthetic */ BufferedInputStream val$bin;
    final /* synthetic */ PrintStream val$out;
    
    lIIIIII(final String x0, final int val$pid, final BufferedInputStream val$bin, final PrintStream val$out) {
        this.val$pid = val$pid;
        this.val$bin = val$bin;
        this.val$out = val$out;
        super(x0);
        this.byteProcessor = new SWTNativeInterface.OutProcess.IOStreamFormatter(this.val$pid);
    }
    
    @Override
    public void run() {
        try {
            final byte[] bytes = new byte[1024];
            int i;
            while ((i = this.val$bin.read(bytes)) != -1) {
                final byte[] result = this.byteProcessor.process(bytes, 0, i);
                try {
                    this.val$out.write(result);
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        catch (Exception ex) {}
    }
}
