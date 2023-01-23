//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import java.io.*;

public final class OS2BMPFileFormat extends FileFormat
{
    static final int BMPFileHeaderSize = 14;
    static final int BMPHeaderFixedSize = 12;
    int width;
    int height;
    int bitCount;
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] header = new byte[18];
            stream.read(header);
            stream.unread(header);
            final int infoHeaderSize = (header[14] & 0xFF) | (header[15] & 0xFF) << 8 | (header[16] & 0xFF) << 16 | (header[17] & 0xFF) << 24;
            return header[0] == 66 && header[1] == 77 && infoHeaderSize == 12;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    byte[] loadData(final byte[] infoHeader) {
        int stride = (this.width * this.bitCount + 7) / 8;
        stride = (stride + 3) / 4 * 4;
        final byte[] data = this.loadData(infoHeader, stride);
        this.flipScanLines(data, stride, this.height);
        return data;
    }
    
    byte[] loadData(final byte[] infoHeader, final int stride) {
        final int dataSize = this.height * stride;
        final byte[] data = new byte[dataSize];
        try {
            if (this.inputStream.read(data) != dataSize) {
                SWT.error(40);
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        return data;
    }
    
    int[] loadFileHeader() {
        final int[] header = new int[5];
        try {
            header[0] = this.inputStream.readShort();
            header[1] = this.inputStream.readInt();
            header[2] = this.inputStream.readShort();
            header[3] = this.inputStream.readShort();
            header[4] = this.inputStream.readInt();
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        if (header[0] != 19778) {
            SWT.error(40);
        }
        return header;
    }
    
    ImageData[] loadFromByteStream() {
        final int[] fileHeader = this.loadFileHeader();
        final byte[] infoHeader = new byte[12];
        try {
            this.inputStream.read(infoHeader);
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
        this.width = ((infoHeader[4] & 0xFF) | (infoHeader[5] & 0xFF) << 8);
        this.height = ((infoHeader[6] & 0xFF) | (infoHeader[7] & 0xFF) << 8);
        this.bitCount = ((infoHeader[10] & 0xFF) | (infoHeader[11] & 0xFF) << 8);
        final PaletteData palette = this.loadPalette(infoHeader);
        if (this.inputStream.getPosition() < fileHeader[4]) {
            try {
                this.inputStream.skip((long)(fileHeader[4] - this.inputStream.getPosition()));
            }
            catch (IOException e2) {
                SWT.error(39, e2);
            }
        }
        final byte[] data = this.loadData(infoHeader);
        final int type = 7;
        return new ImageData[] { ImageData.internal_new(this.width, this.height, this.bitCount, palette, 4, data, 0, (byte[])null, (byte[])null, -1, -1, 7, 0, 0, 0, 0) };
    }
    
    PaletteData loadPalette(final byte[] infoHeader) {
        if (this.bitCount <= 8) {
            final int numColors = 1 << this.bitCount;
            final byte[] buf = new byte[numColors * 3];
            try {
                if (this.inputStream.read(buf) != buf.length) {
                    SWT.error(40);
                }
            }
            catch (IOException e) {
                SWT.error(39, e);
            }
            return this.paletteFromBytes(buf, numColors);
        }
        if (this.bitCount == 16) {
            return new PaletteData(31744, 992, 31);
        }
        if (this.bitCount == 24) {
            return new PaletteData(255, 65280, 16711680);
        }
        return new PaletteData(65280, 16711680, -16777216);
    }
    
    PaletteData paletteFromBytes(final byte[] bytes, final int numColors) {
        int bytesOffset = 0;
        final RGB[] colors = new RGB[numColors];
        for (int i = 0; i < numColors; ++i) {
            colors[i] = new RGB(bytes[bytesOffset + 2] & 0xFF, bytes[bytesOffset + 1] & 0xFF, bytes[bytesOffset] & 0xFF);
            bytesOffset += 3;
        }
        return new PaletteData(colors);
    }
    
    static byte[] paletteToBytes(final PaletteData pal) {
        final int n = (pal.colors == null) ? 0 : ((pal.colors.length < 256) ? pal.colors.length : 256);
        final byte[] bytes = new byte[n * 3];
        int offset = 0;
        for (int i = 0; i < n; ++i) {
            final RGB col = pal.colors[i];
            bytes[offset] = (byte)col.blue;
            bytes[offset + 1] = (byte)col.green;
            bytes[offset + 2] = (byte)col.red;
            offset += 3;
        }
        return bytes;
    }
    
    int unloadData(final ImageData image, final OutputStream out) {
        int bmpBpl = 0;
        try {
            final int bpl = (image.width * image.depth + 7) / 8;
            bmpBpl = (bpl + 3) / 4 * 4;
            final int linesPerBuf = 32678 / bmpBpl;
            final byte[] buf = new byte[linesPerBuf * bmpBpl];
            final byte[] data = image.data;
            final int imageBpl = image.bytesPerLine;
            int dataIndex = imageBpl * (image.height - 1);
            if (image.depth == 16) {
                for (int y = 0; y < image.height; y += linesPerBuf) {
                    int count = image.height - y;
                    if (linesPerBuf < count) {
                        count = linesPerBuf;
                    }
                    int bufOffset = 0;
                    for (int i = 0; i < count; ++i) {
                        for (int wIndex = 0; wIndex < bpl; wIndex += 2) {
                            buf[bufOffset + wIndex + 1] = data[dataIndex + wIndex + 1];
                            buf[bufOffset + wIndex] = data[dataIndex + wIndex];
                        }
                        bufOffset += bmpBpl;
                        dataIndex -= imageBpl;
                    }
                    out.write(buf, 0, bufOffset);
                }
            }
            else {
                for (int y = 0; y < image.height; y += linesPerBuf) {
                    final int tmp = image.height - y;
                    final int count2 = (tmp < linesPerBuf) ? tmp : linesPerBuf;
                    int bufOffset2 = 0;
                    for (int j = 0; j < count2; ++j) {
                        System.arraycopy(data, dataIndex, buf, bufOffset2, bpl);
                        bufOffset2 += bmpBpl;
                        dataIndex -= imageBpl;
                    }
                    out.write(buf, 0, bufOffset2);
                }
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        return bmpBpl * image.height;
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final ImageData image = loader.data[0];
        if (image.depth != 1 && image.depth != 4 && image.depth != 8 && image.depth != 16 && image.depth != 24 && image.depth != 32) {
            SWT.error(38);
        }
        final PaletteData pal = image.palette;
        int numCols;
        byte[] rgbs;
        if (image.depth == 16 || image.depth == 24 || image.depth == 32) {
            if (!pal.isDirect) {
                SWT.error(40);
            }
            numCols = 0;
            rgbs = null;
        }
        else {
            if (pal.isDirect) {
                SWT.error(40);
            }
            numCols = pal.colors.length;
            rgbs = paletteToBytes(pal);
        }
        final int headersSize = 26;
        final int[] fileHeader = { 19778, 0, 0, 0, 26 };
        if (rgbs != null) {
            final int[] array = fileHeader;
            final int n = 4;
            final int[] array2 = array;
            final int n2 = 4;
            array2[n2] += rgbs.length;
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.unloadData(image, out);
        final byte[] data = out.toByteArray();
        fileHeader[1] = fileHeader[4] + data.length;
        try {
            this.outputStream.writeShort(fileHeader[0]);
            this.outputStream.writeInt(fileHeader[1]);
            this.outputStream.writeShort(fileHeader[2]);
            this.outputStream.writeShort(fileHeader[3]);
            this.outputStream.writeInt(fileHeader[4]);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        try {
            this.outputStream.writeInt(12);
            this.outputStream.writeShort(image.width);
            this.outputStream.writeShort(image.height);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((int)(short)image.depth);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        if (numCols > 0) {
            try {
                this.outputStream.write(rgbs);
            }
            catch (IOException e) {
                SWT.error(39, e);
            }
        }
        try {
            this.outputStream.write(data);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    void flipScanLines(final byte[] data, final int stride, final int height) {
        int i1 = 0;
        int i2 = (height - 1) * stride;
        for (int j = 0; j < height / 2; ++j) {
            for (int index = 0; index < stride; ++index) {
                final byte b = data[index + i1];
                data[index + i1] = data[index + i2];
                data[index + i2] = b;
            }
            i1 += stride;
            i2 -= stride;
        }
    }
}
