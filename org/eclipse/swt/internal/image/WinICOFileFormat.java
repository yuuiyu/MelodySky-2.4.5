//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public final class WinICOFileFormat extends FileFormat
{
    byte[] bitInvertData(final byte[] data, final int startIndex, final int endIndex) {
        for (int i = startIndex; i < endIndex; ++i) {
            data[i] = (byte)(255 - data[i - startIndex]);
        }
        return data;
    }
    
    static byte[] convertPad(final byte[] data, final int width, final int height, final int depth, final int pad, final int newPad) {
        if (pad == newPad) {
            return data;
        }
        final int stride = (width * depth + 7) / 8;
        final int bpl = (stride + (pad - 1)) / pad * pad;
        final int newBpl = (stride + (newPad - 1)) / newPad * newPad;
        final byte[] newData = new byte[height * newBpl];
        int srcIndex = 0;
        int destIndex = 0;
        for (int y = 0; y < height; ++y) {
            System.arraycopy(data, srcIndex, newData, destIndex, newBpl);
            srcIndex += bpl;
            destIndex += newBpl;
        }
        return newData;
    }
    
    int iconSize(final ImageData i) {
        final int shapeDataStride = (i.width * i.depth + 31) / 32 * 4;
        final int maskDataStride = (i.width + 31) / 32 * 4;
        final int dataSize = (shapeDataStride + maskDataStride) * i.height;
        final int paletteSize = (i.palette.colors != null) ? (i.palette.colors.length * 4) : 0;
        return 40 + paletteSize + dataSize;
    }
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] header = new byte[4];
            stream.read(header);
            stream.unread(header);
            return header[0] == 0 && header[1] == 0 && header[2] == 1 && header[3] == 0;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    boolean isValidIcon(final ImageData i) {
        switch (i.depth) {
            case 1:
            case 4:
            case 8: {
                if (i.palette.isDirect) {
                    return false;
                }
                final int size = i.palette.colors.length;
                return size == 2 || size == 16 || size == 32 || size == 256;
            }
            case 24:
            case 32: {
                return i.palette.isDirect;
            }
            default: {
                return false;
            }
        }
    }
    
    int loadFileHeader(final LEDataInputStream byteStream) {
        final int[] fileHeader = new int[3];
        try {
            fileHeader[0] = byteStream.readShort();
            fileHeader[1] = byteStream.readShort();
            fileHeader[2] = byteStream.readShort();
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        if (fileHeader[0] != 0 || fileHeader[1] != 1) {
            SWT.error(40);
        }
        final int numIcons = fileHeader[2];
        if (numIcons <= 0) {
            SWT.error(40);
        }
        return numIcons;
    }
    
    int loadFileHeader(final LEDataInputStream byteStream, final boolean hasHeader) {
        final int[] fileHeader = new int[3];
        try {
            if (hasHeader) {
                fileHeader[0] = byteStream.readShort();
                fileHeader[1] = byteStream.readShort();
            }
            else {
                fileHeader[0] = 0;
                fileHeader[1] = 1;
            }
            fileHeader[2] = byteStream.readShort();
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        if (fileHeader[0] != 0 || fileHeader[1] != 1) {
            SWT.error(40);
        }
        final int numIcons = fileHeader[2];
        if (numIcons <= 0) {
            SWT.error(40);
        }
        return numIcons;
    }
    
    ImageData[] loadFromByteStream() {
        final int numIcons = this.loadFileHeader(this.inputStream);
        final int[][] headers = this.loadIconHeaders(numIcons);
        final ImageData[] icons = new ImageData[headers.length];
        for (int i = 0; i < icons.length; ++i) {
            icons[i] = this.loadIcon(headers[i]);
        }
        return icons;
    }
    
    ImageData loadIcon(final int[] iconHeader) {
        try {
            final FileFormat png = FileFormat.getFileFormat(this.inputStream, "PNG");
            if (png != null) {
                png.loader = this.loader;
                return png.loadFromStream(this.inputStream)[0];
            }
        }
        catch (Exception ex) {}
        final byte[] infoHeader = this.loadInfoHeader(iconHeader);
        final WinBMPFileFormat bmpFormat = new WinBMPFileFormat();
        bmpFormat.inputStream = this.inputStream;
        final PaletteData palette = bmpFormat.loadPalette(infoHeader);
        final byte[] shapeData = bmpFormat.loadData(infoHeader);
        final int width = (infoHeader[4] & 0xFF) | (infoHeader[5] & 0xFF) << 8 | (infoHeader[6] & 0xFF) << 16 | (infoHeader[7] & 0xFF) << 24;
        int height = (infoHeader[8] & 0xFF) | (infoHeader[9] & 0xFF) << 8 | (infoHeader[10] & 0xFF) << 16 | (infoHeader[11] & 0xFF) << 24;
        if (height < 0) {
            height = -height;
        }
        final int depth = (infoHeader[14] & 0xFF) | (infoHeader[15] & 0xFF) << 8;
        infoHeader[14] = 1;
        infoHeader[15] = 0;
        byte[] maskData = bmpFormat.loadData(infoHeader);
        maskData = convertPad(maskData, width, height, 1, 4, 2);
        this.bitInvertData(maskData, 0, maskData.length);
        return ImageData.internal_new(width, height, depth, palette, 4, shapeData, 2, maskData, (byte[])null, -1, -1, 3, 0, 0, 0, 0);
    }
    
    int[][] loadIconHeaders(final int numIcons) {
        final int[][] headers = new int[numIcons][7];
        try {
            for (int i = 0; i < numIcons; ++i) {
                headers[i][0] = this.inputStream.read();
                headers[i][1] = this.inputStream.read();
                headers[i][2] = this.inputStream.readShort();
                headers[i][3] = this.inputStream.readShort();
                headers[i][4] = this.inputStream.readShort();
                headers[i][5] = this.inputStream.readInt();
                headers[i][6] = this.inputStream.readInt();
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        return headers;
    }
    
    byte[] loadInfoHeader(final int[] iconHeader) {
        int width = iconHeader[0];
        int height = iconHeader[1];
        int numColors = iconHeader[2];
        if (numColors == 0) {
            numColors = 256;
        }
        if (numColors != 2 && numColors != 8 && numColors != 16 && numColors != 32 && numColors != 256) {
            SWT.error(40);
        }
        if (this.inputStream.getPosition() < iconHeader[6]) {
            try {
                this.inputStream.skip((long)(iconHeader[6] - this.inputStream.getPosition()));
            }
            catch (IOException e) {
                SWT.error(39, e);
                return null;
            }
        }
        final byte[] infoHeader = new byte[40];
        try {
            this.inputStream.read(infoHeader);
        }
        catch (IOException e2) {
            SWT.error(39, e2);
        }
        if (((infoHeader[12] & 0xFF) | (infoHeader[13] & 0xFF) << 8) != 0x1) {
            SWT.error(40);
        }
        final int infoWidth = (infoHeader[4] & 0xFF) | (infoHeader[5] & 0xFF) << 8 | (infoHeader[6] & 0xFF) << 16 | (infoHeader[7] & 0xFF) << 24;
        final int infoHeight = (infoHeader[8] & 0xFF) | (infoHeader[9] & 0xFF) << 8 | (infoHeader[10] & 0xFF) << 16 | (infoHeader[11] & 0xFF) << 24;
        final int bitCount = (infoHeader[14] & 0xFF) | (infoHeader[15] & 0xFF) << 8;
        if (width == 0) {
            width = infoWidth;
        }
        if (height == 0) {
            height = infoHeight / 2;
        }
        if (height == infoHeight && bitCount == 1) {
            height /= 2;
        }
        if (width != infoWidth || height * 2 != infoHeight || (bitCount != 1 && bitCount != 4 && bitCount != 8 && bitCount != 24 && bitCount != 32)) {
            SWT.error(40);
        }
        infoHeader[8] = (byte)(height & 0xFF);
        infoHeader[9] = (byte)(height >> 8 & 0xFF);
        infoHeader[10] = (byte)(height >> 16 & 0xFF);
        infoHeader[11] = (byte)(height >> 24 & 0xFF);
        return infoHeader;
    }
    
    void unloadIcon(final ImageData icon) {
        final int sizeImage = ((icon.width * icon.depth + 31) / 32 * 4 + (icon.width + 31) / 32 * 4) * icon.height;
        try {
            this.outputStream.writeInt(40);
            this.outputStream.writeInt(icon.width);
            this.outputStream.writeInt(icon.height * 2);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((int)(short)icon.depth);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt(sizeImage);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt(0);
            this.outputStream.writeInt((icon.palette.colors != null) ? icon.palette.colors.length : 0);
            this.outputStream.writeInt(0);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        final byte[] rgbs = WinBMPFileFormat.paletteToBytes(icon.palette);
        try {
            this.outputStream.write(rgbs);
        }
        catch (IOException e2) {
            SWT.error(39, e2);
        }
        this.unloadShapeData(icon);
        this.unloadMaskData(icon);
    }
    
    void unloadIconHeader(final ImageData i) {
        final int headerSize = 16;
        final int offset = 22;
        final int iconSize = this.iconSize(i);
        try {
            this.outputStream.write(i.width);
            this.outputStream.write(i.height);
            this.outputStream.writeShort((i.palette.colors != null) ? i.palette.colors.length : 0);
            this.outputStream.writeShort(0);
            this.outputStream.writeShort(0);
            this.outputStream.writeInt(iconSize);
            this.outputStream.writeInt(22);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final ImageData image = loader.data[0];
        if (!this.isValidIcon(image)) {
            SWT.error(40);
        }
        try {
            this.outputStream.writeShort(0);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort(1);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        this.unloadIconHeader(image);
        this.unloadIcon(image);
    }
    
    void unloadMaskData(final ImageData icon) {
        final ImageData mask = icon.getTransparencyMask();
        final int bpl = (icon.width + 7) / 8;
        final int pad = mask.scanlinePad;
        final int srcBpl = (bpl + pad - 1) / pad * pad;
        final int destBpl = (bpl + 3) / 4 * 4;
        final byte[] buf = new byte[destBpl];
        int offset = (icon.height - 1) * srcBpl;
        final byte[] data = mask.data;
        try {
            for (int i = 0; i < icon.height; ++i) {
                System.arraycopy(data, offset, buf, 0, bpl);
                this.bitInvertData(buf, 0, bpl);
                this.outputStream.write(buf, 0, destBpl);
                offset -= srcBpl;
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    void unloadShapeData(final ImageData icon) {
        final int bpl = (icon.width * icon.depth + 7) / 8;
        final int pad = icon.scanlinePad;
        final int srcBpl = (bpl + pad - 1) / pad * pad;
        final int destBpl = (bpl + 3) / 4 * 4;
        final byte[] buf = new byte[destBpl];
        int offset = (icon.height - 1) * srcBpl;
        final byte[] data = icon.data;
        try {
            for (int i = 0; i < icon.height; ++i) {
                System.arraycopy(data, offset, buf, 0, bpl);
                this.outputStream.write(buf, 0, destBpl);
                offset -= srcBpl;
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
}
