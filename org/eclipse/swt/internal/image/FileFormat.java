//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;
import java.io.*;

public abstract class FileFormat
{
    static final String FORMAT_PACKAGE = "org.eclipse.swt.internal.image";
    static final String FORMAT_SUFFIX = "FileFormat";
    static final String[] FORMATS;
    LEDataInputStream inputStream;
    LEDataOutputStream outputStream;
    ImageLoader loader;
    int compression;
    
    static FileFormat getFileFormat(final LEDataInputStream stream, final String format) throws Exception {
        final Class<?> clazz = Class.forName("org.eclipse.swt.internal.image." + format + "FileFormat");
        final FileFormat fileFormat = (FileFormat)clazz.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        if (fileFormat.isFileFormat(stream)) {
            return fileFormat;
        }
        return null;
    }
    
    abstract boolean isFileFormat(final LEDataInputStream p0);
    
    abstract ImageData[] loadFromByteStream();
    
    public ImageData[] loadFromStream(final LEDataInputStream stream) {
        try {
            this.inputStream = stream;
            return this.loadFromByteStream();
        }
        catch (Exception e) {
            if (e instanceof IOException) {
                SWT.error(39, e);
            }
            else {
                SWT.error(40, e);
            }
            return null;
        }
    }
    
    public static ImageData[] load(final InputStream is, final ImageLoader loader) {
        FileFormat fileFormat = null;
        final LEDataInputStream stream = new LEDataInputStream(is);
        for (int i = 1; i < FileFormat.FORMATS.length; ++i) {
            if (FileFormat.FORMATS[i] != null) {
                try {
                    fileFormat = getFileFormat(stream, FileFormat.FORMATS[i]);
                    if (fileFormat != null) {
                        break;
                    }
                }
                catch (ClassNotFoundException e) {
                    FileFormat.FORMATS[i] = null;
                }
                catch (Exception ex) {}
            }
        }
        if (fileFormat == null) {
            SWT.error(42);
        }
        fileFormat.loader = loader;
        return fileFormat.loadFromStream(stream);
    }
    
    public static void save(final OutputStream os, final int format, final ImageLoader loader) {
        if (format < 0 || format >= FileFormat.FORMATS.length) {
            SWT.error(42);
        }
        if (FileFormat.FORMATS[format] == null) {
            SWT.error(42);
        }
        if (loader.data == null || loader.data.length < 1) {
            SWT.error(5);
        }
        final LEDataOutputStream stream = new LEDataOutputStream(os);
        FileFormat fileFormat = null;
        try {
            final Class<?> clazz = Class.forName("org.eclipse.swt.internal.image." + FileFormat.FORMATS[format] + "FileFormat");
            fileFormat = (FileFormat)clazz.getDeclaredConstructor((Class<?>[])new Class[0]).newInstance(new Object[0]);
        }
        catch (Exception e) {
            SWT.error(42);
        }
        if (format == 1) {
            switch (loader.data[0].depth) {
                case 8: {
                    fileFormat.compression = 1;
                    break;
                }
                case 4: {
                    fileFormat.compression = 2;
                    break;
                }
            }
        }
        fileFormat.unloadIntoStream(loader, stream);
    }
    
    abstract void unloadIntoByteStream(final ImageLoader p0);
    
    public void unloadIntoStream(final ImageLoader loader, final LEDataOutputStream stream) {
        try {
            this.outputStream = stream;
            this.unloadIntoByteStream(loader);
            this.outputStream.flush();
        }
        catch (Exception e) {
            try {
                this.outputStream.flush();
            }
            catch (Exception ex) {}
            SWT.error(39, e);
        }
    }
    
    static {
        FORMATS = new String[] { "WinBMP", "WinBMP", "GIF", "WinICO", "JPEG", "PNG", "TIFF", "OS2BMP" };
    }
}
