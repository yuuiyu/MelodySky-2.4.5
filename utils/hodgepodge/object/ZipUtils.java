//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import java.io.*;
import java.util.*;
import java.util.zip.*;

public final class ZipUtils
{
    public static List<ZipEntry> getZipEntriesFromZipInputStream(final ZipInputStream zipInputStream, final List<ZipEntry> list) throws IOException {
        ZipEntry entry;
        while ((entry = zipInputStream.getNextEntry()) != null) {
            list.add(entry);
        }
        return list;
    }
    
    public static List<ZipEntry> getZipEntriesFromZipFile(final ZipFile zipFile, final List<ZipEntry> list) {
        final Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            final ZipEntry entry = (ZipEntry)entries.nextElement();
            list.add(entry);
        }
        return list;
    }
    
    public static void addZipEntryToZipOutputStream(final ZipEntry zipEntry, final byte[] data, final ZipOutputStream zos) throws IOException {
        zos.putNextEntry(zipEntry);
        zos.write(data);
        zos.closeEntry();
    }
}
