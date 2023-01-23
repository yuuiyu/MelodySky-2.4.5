//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

import java.io.*;

public final class NativeLoader
{
    public static void loadAndWriteToCacheDirectory(final InputStream nativeStream, final byte[] buffer) throws IOException {
        final File file = new File(System.getProperty("java.io.tmpdir"));
        loadAndWrite(file, nativeStream, buffer);
        file.deleteOnExit();
    }
    
    public static void loadAndWrite(final File writeToWhere, final InputStream nativeStream, final byte[] buffer) throws IOException {
        FileUtils.writeInputStreamToFile(writeToWhere, nativeStream, buffer);
        System.load(writeToWhere.getAbsolutePath());
    }
}
