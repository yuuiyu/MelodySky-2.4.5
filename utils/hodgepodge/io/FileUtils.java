//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

import utils.hodgepodge.object.*;
import java.nio.charset.*;
import java.util.*;
import java.io.*;

public final class FileUtils
{
    private FileUtils() {
    }
    
    public static void createNew(final String fileName, final String path) throws IOException {
        ObjectUtils.makeSureNotNull(fileName, path);
        new File(path).mkdirs();
        new File(fileName).createNewFile();
    }
    
    public static boolean deleteFile(final File file) {
        if (!file.exists()) {
            return false;
        }
        if (file.isFile()) {
            return file.delete();
        }
        for (final File d : Objects.requireNonNull(file.listFiles())) {
            deleteFile(d);
        }
        return file.delete();
    }
    
    public static void moveFile(final File movedFile, final File moveTo, final byte[] buffer) throws IOException {
        copyFile(movedFile, moveTo, buffer);
        deleteFile(movedFile);
    }
    
    public static void copyFile(final File copiedFile, final File copyTo, final byte[] buffer) throws IOException {
        if (!copiedFile.exists()) {
            throw new FileNotFoundException("Copied File");
        }
        if (!copyTo.exists()) {
            if (copyTo.isDirectory()) {
                copyTo.mkdirs();
            }
            else if (copyTo.isFile()) {
                createNew(copyTo.getName(), copyTo.getPath());
            }
        }
        final FileInputStream stream = IOUtils.getFileInputStream(copiedFile);
        copyInputStream(stream, copyTo, buffer);
        IOUtils.close(stream);
    }
    
    public static void copyInputStream(final InputStream stream, final File copyTo, final byte[] buffer) throws IOException {
        writeInputStreamToFile(copyTo, stream, buffer);
    }
    
    public static String readFileAsString(final File file, final String charset) throws IOException {
        return readFileAsString(file, Charset.forName(charset));
    }
    
    public static String readFileAsString(final File file, final Charset charset) throws IOException {
        ObjectUtils.makeSureNotNull(new Object[] { file, charset });
        return IOUtils.inputStreamToString(IOUtils.getFileInputStream(file), charset);
    }
    
    public static List<String> readFileAsStringList(final File file, final Charset charset) throws IOException {
        ObjectUtils.makeSureNotNull(new Object[] { file, charset });
        return IOUtils.inputStreamToStringLines(IOUtils.getFileInputStream(file), charset);
    }
    
    public static byte[] readFileAsByteArray(final File file, final byte[] buffer) throws IOException {
        ObjectUtils.makeSureNotNull(file);
        return IOUtils.toByteArray(IOUtils.getFileInputStream(file), buffer);
    }
    
    public static void writeStringToFile(final File file, final String str, final Charset code) throws IOException {
        final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), code));
        writer.write(str);
        writer.close();
    }
    
    public static void writeByteArrayToFile(final File file, final byte[] byteArray) throws IOException {
        final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        outputStream.write(byteArray);
        outputStream.close();
    }
    
    public static void writeInputStreamToFile(final File file, final InputStream inputStream, final byte[] buffer) throws IOException {
        final BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file));
        int cache;
        while ((cache = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, cache);
        }
        inputStream.close();
        outputStream.close();
    }
}
