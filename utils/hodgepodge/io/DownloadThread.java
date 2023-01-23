//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

import utils.hodgepodge.function.*;
import java.net.*;
import java.io.*;

public final class DownloadThread extends Thread implements Closeable
{
    private final ByteArrayOutputStream byteArrayOutputStream;
    private final BufferedOutputStream bos;
    private final CatchHandler catchHandler;
    private final byte[] buffer;
    private final InputStream inputStream;
    private VoidFunction endingHandler;
    private int weAreWhere;
    private boolean stop;
    
    public DownloadThread(final URL url, final byte[] buffer, final CatchHandler catchHandler) throws IOException {
        this.byteArrayOutputStream = new ByteArrayOutputStream();
        this.bos = new BufferedOutputStream(this.byteArrayOutputStream);
        this.stop = false;
        this.catchHandler = catchHandler;
        this.buffer = buffer;
        this.inputStream = url.openStream();
    }
    
    @Override
    public void run() {
        try {
            while (true) {
                if (!this.stop) {
                    final int cache;
                    if ((cache = this.inputStream.read(this.buffer)) == -1) {
                        break;
                    }
                    this.bos.write(this.buffer, 0, cache);
                    this.bos.flush();
                    this.weAreWhere += cache;
                }
            }
        }
        catch (IOException e) {
            this.catchHandler.onCatchException((Throwable)e);
        }
        if (this.endingHandler != null) {
            this.endingHandler.handle();
        }
    }
    
    public byte[] getDownloadedByteArray() {
        return this.byteArrayOutputStream.toByteArray();
    }
    
    public int getWeAreWhere() {
        return this.weAreWhere;
    }
    
    public void setStop(final boolean stop) {
        this.stop = stop;
    }
    
    public void setEndingHandler(final VoidFunction endingHandler) {
        this.endingHandler = endingHandler;
    }
    
    public void reset() {
        this.byteArrayOutputStream.reset();
    }
    
    @Override
    public void close() throws IOException {
        this.inputStream.close();
        this.bos.close();
        this.byteArrayOutputStream.close();
    }
}
