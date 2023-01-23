//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.util.zip.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public final class PNGFileFormat extends FileFormat
{
    static final int SIGNATURE_LENGTH = 8;
    static final int PRIME = 65521;
    PngIhdrChunk headerChunk;
    PngPlteChunk paletteChunk;
    ImageData imageData;
    byte[] data;
    byte[] alphaPalette;
    byte headerByte1;
    byte headerByte2;
    int adler;
    
    void readSignature() throws IOException {
        final byte[] signature = new byte[8];
        this.inputStream.read(signature);
    }
    
    ImageData[] loadFromByteStream() {
        try {
            this.readSignature();
            final PngChunkReader chunkReader = new PngChunkReader(this.inputStream);
            this.headerChunk = chunkReader.getIhdrChunk();
            final int width = this.headerChunk.getWidth();
            final int height = this.headerChunk.getHeight();
            if (width <= 0 || height <= 0) {
                SWT.error(40);
            }
            final int imageSize = this.getAlignedBytesPerRow() * height;
            this.data = new byte[imageSize];
            this.imageData = ImageData.internal_new(width, height, this.headerChunk.getSwtBitsPerPixel(), new PaletteData(0, 0, 0), 4, this.data, 0, (byte[])null, (byte[])null, -1, -1, 5, 0, 0, 0, 0);
            if (this.headerChunk.usesDirectColor()) {
                this.imageData.palette = this.headerChunk.getPaletteData();
            }
            while (chunkReader.hasMoreChunks()) {
                this.readNextChunk(chunkReader);
            }
            return new ImageData[] { this.imageData };
        }
        catch (IOException e) {
            SWT.error(40);
            return null;
        }
    }
    
    void readNextChunk(final PngChunkReader chunkReader) throws IOException {
        final PngChunk chunk = chunkReader.readNextChunk();
        switch (chunk.getChunkType()) {
            case 3: {
                break;
            }
            case 1: {
                if (!this.headerChunk.usesDirectColor()) {
                    this.paletteChunk = (PngPlteChunk)chunk;
                    this.imageData.palette = this.paletteChunk.getPaletteData();
                    break;
                }
                break;
            }
            case 5: {
                final PngTrnsChunk trnsChunk = (PngTrnsChunk)chunk;
                if (trnsChunk.getTransparencyType(this.headerChunk) == 0) {
                    this.imageData.transparentPixel = trnsChunk.getSwtTransparentPixel(this.headerChunk);
                    break;
                }
                this.alphaPalette = trnsChunk.getAlphaValues(this.headerChunk, this.paletteChunk);
                int transparentCount = 0;
                int transparentPixel = -1;
                for (int i = 0; i < this.alphaPalette.length; ++i) {
                    if ((this.alphaPalette[i] & 0xFF) != 0xFF) {
                        ++transparentCount;
                        transparentPixel = i;
                    }
                }
                if (transparentCount == 0) {
                    this.alphaPalette = null;
                    break;
                }
                if (transparentCount == 1 && this.alphaPalette[transparentPixel] == 0) {
                    this.alphaPalette = null;
                    this.imageData.transparentPixel = transparentPixel;
                    break;
                }
                break;
            }
            case 2: {
                if (chunkReader.readPixelData()) {
                    SWT.error(40);
                    break;
                }
                final PngIdatChunk dataChunk = (PngIdatChunk)chunk;
                this.readPixelData(dataChunk, chunkReader);
                break;
            }
            default: {
                if (chunk.isCritical()) {
                    SWT.error(20);
                    break;
                }
                break;
            }
        }
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final PngEncoder encoder = new PngEncoder(loader);
        encoder.encode(this.outputStream);
    }
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] signature = new byte[8];
            stream.read(signature);
            stream.unread(signature);
            return (signature[0] & 0xFF) == 0x89 && (signature[1] & 0xFF) == 0x50 && (signature[2] & 0xFF) == 0x4E && (signature[3] & 0xFF) == 0x47 && (signature[4] & 0xFF) == 0xD && (signature[5] & 0xFF) == 0xA && (signature[6] & 0xFF) == 0x1A && (signature[7] & 0xFF) == 0xA;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    byte[] validateBitDepth(final byte[] data) {
        if (this.headerChunk.getBitDepth() > 8) {
            final byte[] result = new byte[data.length / 2];
            compress16BitDepthTo8BitDepth(data, 0, result, 0, result.length);
            return result;
        }
        return data;
    }
    
    void setPixelData(final byte[] data, final ImageData imageData) {
        switch (this.headerChunk.getColorType()) {
            case 4: {
                final int width = imageData.width;
                final int height = imageData.height;
                final int destBytesPerLine = imageData.bytesPerLine;
                int srcBytesPerLine = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    srcBytesPerLine /= 2;
                }
                final byte[] rgbData = new byte[destBytesPerLine * height];
                final byte[] alphaData = new byte[width * height];
                for (int y = 0; y < height; ++y) {
                    int srcIndex = srcBytesPerLine * y;
                    int destIndex = destBytesPerLine * y;
                    int destAlphaIndex = width * y;
                    for (int x = 0; x < width; ++x) {
                        final byte grey = data[srcIndex];
                        final byte alpha = data[srcIndex + 1];
                        rgbData[destIndex + 0] = grey;
                        rgbData[destIndex + 2] = (rgbData[destIndex + 1] = grey);
                        alphaData[destAlphaIndex] = alpha;
                        srcIndex += 2;
                        destIndex += 3;
                        ++destAlphaIndex;
                    }
                }
                imageData.data = rgbData;
                imageData.alphaData = alphaData;
                break;
            }
            case 6: {
                final int width = imageData.width;
                final int height = imageData.height;
                final int destBytesPerLine = imageData.bytesPerLine;
                int srcBytesPerLine = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    srcBytesPerLine /= 2;
                }
                final byte[] rgbData = new byte[destBytesPerLine * height];
                final byte[] alphaData = new byte[width * height];
                for (int y = 0; y < height; ++y) {
                    int srcIndex = srcBytesPerLine * y;
                    int destIndex = destBytesPerLine * y;
                    int destAlphaIndex = width * y;
                    for (int x = 0; x < width; ++x) {
                        rgbData[destIndex + 0] = data[srcIndex + 0];
                        rgbData[destIndex + 1] = data[srcIndex + 1];
                        rgbData[destIndex + 2] = data[srcIndex + 2];
                        alphaData[destAlphaIndex] = data[srcIndex + 3];
                        srcIndex += 4;
                        destIndex += 3;
                        ++destAlphaIndex;
                    }
                }
                imageData.data = rgbData;
                imageData.alphaData = alphaData;
                break;
            }
            case 3: {
                imageData.data = data;
                if (this.alphaPalette != null) {
                    final int size = imageData.width * imageData.height;
                    final byte[] alphaData2 = new byte[size];
                    final byte[] pixelData = new byte[size];
                    imageData.getPixels(0, 0, size, pixelData, 0);
                    for (int i = 0; i < pixelData.length; ++i) {
                        alphaData2[i] = this.alphaPalette[pixelData[i] & 0xFF];
                    }
                    imageData.alphaData = alphaData2;
                    break;
                }
                break;
            }
            default: {
                final int height2 = imageData.height;
                final int destBytesPerLine2 = imageData.bytesPerLine;
                int srcBytesPerLine2 = this.getAlignedBytesPerRow();
                if (this.headerChunk.getBitDepth() > 8) {
                    srcBytesPerLine2 /= 2;
                }
                if (destBytesPerLine2 != srcBytesPerLine2) {
                    imageData.data = new byte[destBytesPerLine2 * height2];
                    for (int y2 = 0; y2 < height2; ++y2) {
                        System.arraycopy(data, y2 * srcBytesPerLine2, imageData.data, y2 * destBytesPerLine2, srcBytesPerLine2);
                    }
                    break;
                }
                imageData.data = data;
                break;
            }
        }
    }
    
    void setImageDataValues(final byte[] data, final ImageData imageData) {
        final byte[] result = this.validateBitDepth(data);
        this.setPixelData(result, imageData);
    }
    
    void readPixelData(final PngIdatChunk chunk, final PngChunkReader chunkReader) throws IOException {
        InputStream stream = new PngInputStream(chunk, chunkReader);
        final boolean use3_2 = System.getProperty("org.eclipse.swt.internal.image.PNGFileFormat_3.2") != null;
        final InputStream inflaterStream = use3_2 ? null : new BufferedInputStream(new InflaterInputStream(stream));
        if (inflaterStream != null) {
            stream = inflaterStream;
        }
        else {
            stream = (InputStream)new PngDecodingDataStream(stream);
        }
        final int interlaceMethod = this.headerChunk.getInterlaceMethod();
        if (interlaceMethod == 0) {
            this.readNonInterlacedImage(stream);
        }
        else {
            this.readInterlacedImage(stream);
        }
        while (stream.available() > 0) {
            stream.read();
        }
        stream.close();
    }
    
    int getAlignedBytesPerRow() {
        return (this.getBytesPerRow(this.headerChunk.getWidth()) + 3) / 4 * 4;
    }
    
    int getBytesPerRow() {
        return this.getBytesPerRow(this.headerChunk.getWidth());
    }
    
    int getBytesPerPixel() {
        final int bitsPerPixel = this.headerChunk.getBitsPerPixel();
        return (bitsPerPixel + 7) / 8;
    }
    
    int getBytesPerRow(final int rowWidthInPixels) {
        final int bitsPerPixel = this.headerChunk.getBitsPerPixel();
        final int bitsPerRow = bitsPerPixel * rowWidthInPixels;
        final int bitsPerByte = 8;
        return (bitsPerRow + 7) / 8;
    }
    
    void readInterlaceFrame(final InputStream inputStream, final int rowInterval, final int columnInterval, final int startRow, final int startColumn, final int frameCount) throws IOException {
        final int width = this.headerChunk.getWidth();
        final int alignedBytesPerRow = this.getAlignedBytesPerRow();
        final int height = this.headerChunk.getHeight();
        if (startRow >= height || startColumn >= width) {
            return;
        }
        final int pixelsPerRow = (width - startColumn + columnInterval - 1) / columnInterval;
        final int bytesPerRow = this.getBytesPerRow(pixelsPerRow);
        final byte[] row1 = new byte[bytesPerRow];
        final byte[] row2 = new byte[bytesPerRow];
        byte[] currentRow = row1;
        byte[] lastRow = row2;
        for (int row3 = startRow; row3 < height; row3 += rowInterval) {
            final byte filterType = (byte)inputStream.read();
            for (int read = 0; read != bytesPerRow; read += inputStream.read(currentRow, read, bytesPerRow - read)) {}
            this.filterRow(currentRow, lastRow, filterType);
            if (this.headerChunk.getBitDepth() >= 8) {
                final int bytesPerPixel = this.getBytesPerPixel();
                int dataOffset = row3 * alignedBytesPerRow + startColumn * bytesPerPixel;
                for (int rowOffset = 0; rowOffset < currentRow.length; rowOffset += bytesPerPixel) {
                    for (int byteOffset = 0; byteOffset < bytesPerPixel; ++byteOffset) {
                        this.data[dataOffset + byteOffset] = currentRow[rowOffset + byteOffset];
                    }
                    dataOffset += columnInterval * bytesPerPixel;
                }
            }
            else {
                final int bitsPerPixel = this.headerChunk.getBitDepth();
                final int pixelsPerByte = 8 / bitsPerPixel;
                int column = startColumn;
                final int rowBase = row3 * alignedBytesPerRow;
                int valueMask = 0;
                for (int i = 0; i < bitsPerPixel; ++i) {
                    valueMask <<= 1;
                    valueMask |= 0x1;
                }
                final int maxShift = 8 - bitsPerPixel;
                for (final byte element : currentRow) {
                    for (int bitOffset = maxShift; bitOffset >= 0; bitOffset -= bitsPerPixel) {
                        if (column < width) {
                            final int dataOffset2 = rowBase + column * bitsPerPixel / 8;
                            final int value = element >> bitOffset & valueMask;
                            final int dataShift = maxShift - bitsPerPixel * (column % pixelsPerByte);
                            final byte[] data = this.data;
                            final int n = dataOffset2;
                            final byte[] array2 = data;
                            final int n2 = n;
                            array2[n2] |= (byte)(value << dataShift);
                        }
                        column += columnInterval;
                    }
                }
            }
            currentRow = ((currentRow == row1) ? row2 : row1);
            lastRow = ((lastRow == row1) ? row2 : row1);
        }
        this.setImageDataValues(this.data, this.imageData);
        this.fireInterlacedFrameEvent(frameCount);
    }
    
    void readInterlacedImage(final InputStream inputStream) throws IOException {
        this.readInterlaceFrame(inputStream, 8, 8, 0, 0, 0);
        this.readInterlaceFrame(inputStream, 8, 8, 0, 4, 1);
        this.readInterlaceFrame(inputStream, 8, 4, 4, 0, 2);
        this.readInterlaceFrame(inputStream, 4, 4, 0, 2, 3);
        this.readInterlaceFrame(inputStream, 4, 2, 2, 0, 4);
        this.readInterlaceFrame(inputStream, 2, 2, 0, 1, 5);
        this.readInterlaceFrame(inputStream, 2, 1, 1, 0, 6);
    }
    
    void fireInterlacedFrameEvent(final int frameCount) {
        if (this.loader.hasListeners()) {
            final ImageData image = (ImageData)this.imageData.clone();
            final boolean finalFrame = frameCount == 6;
            this.loader.notifyListeners(new ImageLoaderEvent(this.loader, image, frameCount, finalFrame));
        }
    }
    
    void readNonInterlacedImage(final InputStream inputStream) throws IOException {
        int dataOffset = 0;
        final int alignedBytesPerRow = this.getAlignedBytesPerRow();
        final int bytesPerRow = this.getBytesPerRow();
        final byte[] row1 = new byte[bytesPerRow];
        final byte[] row2 = new byte[bytesPerRow];
        byte[] currentRow = row1;
        byte[] lastRow = row2;
        for (int height = this.headerChunk.getHeight(), row3 = 0; row3 < height; ++row3) {
            final byte filterType = (byte)inputStream.read();
            for (int read = 0; read != bytesPerRow; read += inputStream.read(currentRow, read, bytesPerRow - read)) {}
            this.filterRow(currentRow, lastRow, filterType);
            System.arraycopy(currentRow, 0, this.data, dataOffset, bytesPerRow);
            dataOffset += alignedBytesPerRow;
            currentRow = ((currentRow == row1) ? row2 : row1);
            lastRow = ((lastRow == row1) ? row2 : row1);
        }
        this.setImageDataValues(this.data, this.imageData);
    }
    
    static void compress16BitDepthTo8BitDepth(final byte[] source, final int sourceOffset, final byte[] destination, final int destinationOffset, final int numberOfValues) {
        for (int i = 0; i < numberOfValues; ++i) {
            final int sourceIndex = sourceOffset + 2 * i;
            final int destinationIndex = destinationOffset + i;
            final byte compressedValue = source[sourceIndex];
            destination[destinationIndex] = compressedValue;
        }
    }
    
    static int compress16BitDepthTo8BitDepth(final int value) {
        return value >> 8;
    }
    
    void filterRow(final byte[] row, final byte[] previousRow, final int filterType) {
        final int byteOffset = this.headerChunk.getFilterByteOffset();
        switch (filterType) {
            case 1: {
                for (int i = byteOffset; i < row.length; ++i) {
                    final int current = row[i] & 0xFF;
                    final int left = row[i - byteOffset] & 0xFF;
                    row[i] = (byte)(current + left & 0xFF);
                }
                break;
            }
            case 2: {
                for (int i = 0; i < row.length; ++i) {
                    final int current = row[i] & 0xFF;
                    final int above = previousRow[i] & 0xFF;
                    row[i] = (byte)(current + above & 0xFF);
                }
                break;
            }
            case 3: {
                for (int i = 0; i < row.length; ++i) {
                    final int left2 = (i < byteOffset) ? 0 : (row[i - byteOffset] & 0xFF);
                    final int above = previousRow[i] & 0xFF;
                    final int current2 = row[i] & 0xFF;
                    row[i] = (byte)(current2 + (left2 + above) / 2 & 0xFF);
                }
                break;
            }
            case 4: {
                for (int i = 0; i < row.length; ++i) {
                    final int left2 = (i < byteOffset) ? 0 : (row[i - byteOffset] & 0xFF);
                    final int aboveLeft = (i < byteOffset) ? 0 : (previousRow[i - byteOffset] & 0xFF);
                    final int above2 = previousRow[i] & 0xFF;
                    final int a = Math.abs(above2 - aboveLeft);
                    final int b = Math.abs(left2 - aboveLeft);
                    final int c = Math.abs(left2 - aboveLeft + above2 - aboveLeft);
                    int preductor = 0;
                    if (a <= b && a <= c) {
                        preductor = left2;
                    }
                    else if (b <= c) {
                        preductor = above2;
                    }
                    else {
                        preductor = aboveLeft;
                    }
                    final int currentValue = row[i] & 0xFF;
                    row[i] = (byte)(currentValue + preductor & 0xFF);
                }
                break;
            }
        }
    }
}
