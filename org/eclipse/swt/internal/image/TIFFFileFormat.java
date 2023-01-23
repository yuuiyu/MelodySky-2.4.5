//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public final class TIFFFileFormat extends FileFormat
{
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] header = new byte[4];
            stream.read(header);
            stream.unread(header);
            return header[0] == header[1] && ((header[0] == 73 && header[2] == 42 && header[3] == 0) || (header[0] == 77 && header[2] == 0 && header[3] == 42));
        }
        catch (Exception e) {
            return false;
        }
    }
    
    ImageData[] loadFromByteStream() {
        final byte[] header = new byte[8];
        ImageData[] images = new ImageData[0];
        final TIFFRandomFileAccess file = new TIFFRandomFileAccess(this.inputStream);
        try {
            file.read(header);
            if (header[0] != header[1]) {
                SWT.error(40);
            }
            if ((header[0] != 73 || header[2] != 42 || header[3] != 0) && (header[0] != 77 || header[2] != 0 || header[3] != 42)) {
                SWT.error(40);
            }
            final boolean isLittleEndian = header[0] == 73;
            int offset = isLittleEndian ? ((header[4] & 0xFF) | (header[5] & 0xFF) << 8 | (header[6] & 0xFF) << 16 | (header[7] & 0xFF) << 24) : ((header[7] & 0xFF) | (header[6] & 0xFF) << 8 | (header[5] & 0xFF) << 16 | (header[4] & 0xFF) << 24);
            while (offset != 0) {
                file.seek(offset);
                final TIFFDirectory directory = new TIFFDirectory(file, isLittleEndian, this.loader);
                final int[] nextIFDOffset = { 0 };
                final ImageData image = directory.read(nextIFDOffset);
                offset = nextIFDOffset[0];
                final ImageData[] oldImages = images;
                images = new ImageData[oldImages.length + 1];
                System.arraycopy(oldImages, 0, images, 0, oldImages.length);
                images[images.length - 1] = image;
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        return images;
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final ImageData image = loader.data[0];
        final TIFFDirectory directory = new TIFFDirectory(image);
        try {
            directory.writeToStream(this.outputStream);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
}
