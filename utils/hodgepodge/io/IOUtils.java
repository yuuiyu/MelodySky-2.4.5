//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

import java.net.*;
import java.nio.charset.*;
import java.io.*;
import java.util.*;

public final class IOUtils
{
    private static final byte[] NORMAL_BUFFER;
    private static final String lineSeparator;
    
    private IOUtils() {
    }
    
    public static byte[] toByteArray(final InputStream inputStream, final byte[] buffer) throws IOException {
        final ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int cache;
        while ((cache = inputStream.read(buffer)) != -1) {
            byteArrayOutputStream.write(buffer, 0, cache);
        }
        return byteArrayOutputStream.toByteArray();
    }
    
    public static void close(final Closeable closeable) throws IOException {
        closeable.close();
    }
    
    public static void close(final Closeable... closeables) throws IOException {
        for (final Closeable closeable : closeables) {
            close(closeable);
        }
    }
    
    public static void closeQuietly(final Closeable closeable) {
        try {
            close(closeable);
        }
        catch (IOException ex) {}
    }
    
    public static void closeQuietly(final Closeable... closeable) {
        try {
            close(closeable);
        }
        catch (IOException ex) {}
    }
    
    public static void flush(final Flushable flushable) throws IOException {
        flushable.flush();
    }
    
    public static void flush(final Flushable... flushables) throws IOException {
        for (final Flushable flushable : flushables) {
            flush(flushable);
        }
    }
    
    public static void flushQuietly(final Flushable flushable) {
        try {
            flush(flushable);
        }
        catch (IOException ex) {}
    }
    
    public static void flushQuietly(final Flushable... flushable) {
        try {
            flush(flushable);
        }
        catch (IOException ex) {}
    }
    
    public static InputStream getFileInputStreamByURL(final File file) throws IOException {
        return file.toURI().toURL().openStream();
    }
    
    public static FileInputStream getFileInputStream(final File file) throws IOException {
        return new FileInputStream(file);
    }
    
    public static URL getResource(final String name) {
        return IOUtils.class.getResource("/" + name);
    }
    
    public static InputStream getResourceAsStream(final String name) {
        return IOUtils.class.getResourceAsStream("/" + name);
    }
    
    public static String inputStreamToString(final InputStream inputStream, final String code) throws IOException {
        return inputStreamToString(inputStream, Charset.forName(code));
    }
    
    public static String inputStreamToString(final InputStream inputStream, final Charset code) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, code));
        final StringBuilder builder = new StringBuilder();
        String cache;
        while ((cache = reader.readLine()) != null) {
            builder.append(cache).append(IOUtils.lineSeparator);
        }
        inputStream.close();
        reader.close();
        return builder.toString();
    }
    
    public static List<String> inputStreamStringLines(final InputStream inputStream, final String code) throws IOException {
        return inputStreamToStringLines(inputStream, Charset.forName(code));
    }
    
    public static List<String> inputStreamToStringLines(final InputStream inputStream, final Charset code) throws IOException {
        final BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, code));
        final ArrayList<String> stringList = new ArrayList<String>();
        String cache;
        while ((cache = reader.readLine()) != null) {
            stringList.add(cache);
        }
        inputStream.close();
        reader.close();
        return stringList;
    }
    
    public static int getInputStreamSize(final InputStream inputStream, final byte[] buffer) throws IOException {
        int size = 0;
        int cache;
        while ((cache = inputStream.read(buffer)) != -1) {
            size += cache;
        }
        return size;
    }
    
    public static boolean inputStreamEquals(final InputStream input1, final InputStream input2, final byte[] buffer) throws IOException {
        if (input1 == input2) {
            return true;
        }
        if (input1 == null || input2 == null) {
            return false;
        }
        final byte[] byteArray1 = toByteArray(input1, buffer);
        final byte[] byteArray2 = toByteArray(input2, buffer);
        if (byteArray1.length != byteArray2.length) {
            return false;
        }
        for (int i = 0; i < byteArray1.length; ++i) {
            if (byteArray1[i] != byteArray2[i]) {
                return false;
            }
        }
        return true;
    }
    
    public static byte[] normalBuffer() {
        return IOUtils.NORMAL_BUFFER;
    }
    
    public static byte[] newBuffer() {
        return newBuffer(4096);
    }
    
    public static byte[] newBuffer(final int size) {
        return new byte[size];
    }
    
    static {
        NORMAL_BUFFER = new byte[4096];
        lineSeparator = System.lineSeparator();
    }
}
