//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

final class TIFFDirectory
{
    TIFFRandomFileAccess file;
    boolean isLittleEndian;
    ImageLoader loader;
    int depth;
    int subfileType;
    int imageWidth;
    int imageLength;
    int[] bitsPerSample;
    int compression;
    int photometricInterpretation;
    int[] stripOffsets;
    int samplesPerPixel;
    int rowsPerStrip;
    int[] stripByteCounts;
    int t4Options;
    int colorMapOffset;
    ImageData image;
    LEDataOutputStream out;
    static final int NO_VALUE = -1;
    static final short TAG_NewSubfileType = 254;
    static final short TAG_SubfileType = 255;
    static final short TAG_ImageWidth = 256;
    static final short TAG_ImageLength = 257;
    static final short TAG_BitsPerSample = 258;
    static final short TAG_Compression = 259;
    static final short TAG_PhotometricInterpretation = 262;
    static final short TAG_FillOrder = 266;
    static final short TAG_ImageDescription = 270;
    static final short TAG_StripOffsets = 273;
    static final short TAG_Orientation = 274;
    static final short TAG_SamplesPerPixel = 277;
    static final short TAG_RowsPerStrip = 278;
    static final short TAG_StripByteCounts = 279;
    static final short TAG_XResolution = 282;
    static final short TAG_YResolution = 283;
    static final short TAG_PlanarConfiguration = 284;
    static final short TAG_T4Options = 292;
    static final short TAG_ResolutionUnit = 296;
    static final short TAG_Software = 305;
    static final short TAG_DateTime = 306;
    static final short TAG_ColorMap = 320;
    static final int TYPE_BYTE = 1;
    static final int TYPE_ASCII = 2;
    static final int TYPE_SHORT = 3;
    static final int TYPE_LONG = 4;
    static final int TYPE_RATIONAL = 5;
    static final int FILETYPE_REDUCEDIMAGE = 1;
    static final int FILETYPE_PAGE = 2;
    static final int FILETYPE_MASK = 4;
    static final int OFILETYPE_IMAGE = 1;
    static final int OFILETYPE_REDUCEDIMAGE = 2;
    static final int OFILETYPE_PAGE = 3;
    static final int COMPRESSION_NONE = 1;
    static final int COMPRESSION_CCITT_3_1 = 2;
    static final int COMPRESSION_PACKBITS = 32773;
    static final int IFD_ENTRY_SIZE = 12;
    
    public TIFFDirectory(final TIFFRandomFileAccess file, final boolean isLittleEndian, final ImageLoader loader) {
        this.file = file;
        this.isLittleEndian = isLittleEndian;
        this.loader = loader;
    }
    
    public TIFFDirectory(final ImageData image) {
        this.image = image;
    }
    
    int decodePackBits(final byte[] src, final byte[] dest, final int offsetDest) {
        int destIndex = offsetDest;
        int srcIndex = 0;
        while (srcIndex < src.length) {
            final byte n = src[srcIndex];
            if (n >= 0) {
                System.arraycopy(src, ++srcIndex, dest, destIndex, n + 1);
                srcIndex += n + 1;
                destIndex += n + 1;
            }
            else if (n >= -127) {
                final byte value = src[++srcIndex];
                for (int j = 0; j < -n + 1; ++j) {
                    dest[destIndex++] = value;
                }
                ++srcIndex;
            }
            else {
                ++srcIndex;
            }
        }
        return destIndex - offsetDest;
    }
    
    int getEntryValue(final int type, final byte[] buffer, final int index) {
        return this.toInt(buffer, index + 8, type);
    }
    
    void getEntryValue(final int type, byte[] buffer, final int index, final int[] values) throws IOException {
        int start = index + 8;
        final int offset = this.toInt(buffer, start, 4);
        int size = 0;
        switch (type) {
            case 3: {
                size = 2;
                break;
            }
            case 4: {
                size = 4;
                break;
            }
            case 5: {
                size = 8;
                break;
            }
            case 1:
            case 2: {
                size = 1;
                break;
            }
            default: {
                SWT.error(42);
                return;
            }
        }
        if (values.length * size > 4) {
            buffer = new byte[values.length * size];
            this.file.seek(offset);
            this.file.read(buffer);
            start = 0;
        }
        for (int i = 0; i < values.length; ++i) {
            values[i] = this.toInt(buffer, start + i * size, type);
        }
    }
    
    void decodePixels(final ImageData image) throws IOException {
        final byte[] imageData = new byte[(this.imageWidth * this.depth + 7) / 8 * this.imageLength];
        image.data = imageData;
        int destIndex = 0;
        for (int length = this.stripOffsets.length, i = 0; i < length; ++i) {
            final byte[] data = new byte[this.stripByteCounts[i]];
            this.file.seek(this.stripOffsets[i]);
            this.file.read(data);
            if (this.compression == 1) {
                System.arraycopy(data, 0, imageData, destIndex, data.length);
                destIndex += data.length;
            }
            else if (this.compression == 32773) {
                destIndex += this.decodePackBits(data, imageData, destIndex);
            }
            else if (this.compression == 2 || this.compression == 3) {
                final TIFFModifiedHuffmanCodec codec = new TIFFModifiedHuffmanCodec();
                int nRows = this.rowsPerStrip;
                if (i == length - 1) {
                    final int n = this.imageLength % this.rowsPerStrip;
                    if (n != 0) {
                        nRows = n;
                    }
                }
                destIndex += codec.decode(data, imageData, destIndex, this.imageWidth, nRows);
            }
            if (this.loader.hasListeners()) {
                this.loader.notifyListeners(new ImageLoaderEvent(this.loader, image, i, i == length - 1));
            }
        }
    }
    
    PaletteData getColorMap() throws IOException {
        final int numColors = 1 << this.bitsPerSample[0];
        final int numBytes = 6 * numColors;
        final byte[] buffer = new byte[numBytes];
        this.file.seek(this.colorMapOffset);
        this.file.read(buffer);
        final RGB[] colors = new RGB[numColors];
        int offset = this.isLittleEndian ? 1 : 0;
        final int startG = 2 * numColors;
        final int startB = startG + 2 * numColors;
        for (int i = 0; i < numColors; ++i) {
            final int r = buffer[offset] & 0xFF;
            final int g = buffer[startG + offset] & 0xFF;
            final int b = buffer[startB + offset] & 0xFF;
            colors[i] = new RGB(r, g, b);
            offset += 2;
        }
        return new PaletteData(colors);
    }
    
    PaletteData getGrayPalette() {
        final int numColors = 1 << this.bitsPerSample[0];
        final RGB[] rgbs = new RGB[numColors];
        for (int i = 0; i < numColors; ++i) {
            int value = i * 255 / (numColors - 1);
            if (this.photometricInterpretation == 0) {
                value = 255 - value;
            }
            rgbs[i] = new RGB(value, value, value);
        }
        return new PaletteData(rgbs);
    }
    
    PaletteData getRGBPalette(final int bitsR, final int bitsG, final int bitsB) {
        int blueMask = 0;
        for (int i = 0; i < bitsB; ++i) {
            blueMask |= 1 << i;
        }
        int greenMask = 0;
        for (int j = bitsB; j < bitsB + bitsG; ++j) {
            greenMask |= 1 << j;
        }
        int redMask = 0;
        for (int k = bitsB + bitsG; k < bitsB + bitsG + bitsR; ++k) {
            redMask |= 1 << k;
        }
        return new PaletteData(redMask, greenMask, blueMask);
    }
    
    int formatStrips(final int rowByteSize, final int nbrRows, final byte[] data, final int maxStripByteSize, final int offsetPostIFD, final int extraBytes, final int[][] strips) {
        int n;
        int nbrRowsPerStrip;
        if (rowByteSize > maxStripByteSize) {
            n = data.length / rowByteSize;
            nbrRowsPerStrip = 1;
        }
        else {
            final int nbr = (data.length + maxStripByteSize - 1) / maxStripByteSize;
            nbrRowsPerStrip = nbrRows / nbr;
            n = (nbrRows + nbrRowsPerStrip - 1) / nbrRowsPerStrip;
        }
        final int stripByteSize = rowByteSize * nbrRowsPerStrip;
        final int[] offsets = new int[n];
        final int[] counts = new int[n];
        final int postIFDData = (n == 1) ? 0 : (n * 2 * 4);
        final int startOffset;
        int offset = startOffset = offsetPostIFD + extraBytes + postIFDData;
        for (int i = 0; i < n; ++i) {
            offsets[i] = offset;
            counts[i] = stripByteSize;
            offset += stripByteSize;
        }
        final int mod = data.length % stripByteSize;
        if (mod != 0) {
            counts[counts.length - 1] = mod;
        }
        strips[0] = offsets;
        strips[1] = counts;
        return nbrRowsPerStrip;
    }
    
    int[] formatColorMap(final RGB[] rgbs) {
        final int[] colorMap = new int[rgbs.length * 3];
        final int offsetGreen = rgbs.length;
        final int offsetBlue = rgbs.length * 2;
        for (int i = 0; i < rgbs.length; ++i) {
            colorMap[i] = (rgbs[i].red << 8 | rgbs[i].red);
            colorMap[i + offsetGreen] = (rgbs[i].green << 8 | rgbs[i].green);
            colorMap[i + offsetBlue] = (rgbs[i].blue << 8 | rgbs[i].blue);
        }
        return colorMap;
    }
    
    void parseEntries(final byte[] buffer) throws IOException {
        for (int offset = 0; offset < buffer.length; offset += 12) {
            final int tag = this.toInt(buffer, offset, 3);
            final int type = this.toInt(buffer, offset + 2, 3);
            final int count = this.toInt(buffer, offset + 4, 4);
            switch (tag) {
                case 254: {
                    this.subfileType = this.getEntryValue(type, buffer, offset);
                    break;
                }
                case 255: {
                    final int oldSubfileType = this.getEntryValue(type, buffer, offset);
                    this.subfileType = ((oldSubfileType == 2) ? 1 : ((oldSubfileType == 3) ? 2 : 0));
                    break;
                }
                case 256: {
                    this.imageWidth = this.getEntryValue(type, buffer, offset);
                    break;
                }
                case 257: {
                    this.imageLength = this.getEntryValue(type, buffer, offset);
                    break;
                }
                case 258: {
                    if (type != 3) {
                        SWT.error(40);
                    }
                    this.getEntryValue(type, buffer, offset, this.bitsPerSample = new int[count]);
                    break;
                }
                case 259: {
                    this.compression = this.getEntryValue(type, buffer, offset);
                }
                case 262:
                case 266: {
                    this.photometricInterpretation = this.getEntryValue(type, buffer, offset);
                    break;
                }
                case 273: {
                    if (type != 4 && type != 3) {
                        SWT.error(40);
                    }
                    this.getEntryValue(type, buffer, offset, this.stripOffsets = new int[count]);
                }
                case 277: {
                    if (type != 3) {
                        SWT.error(40);
                    }
                    this.samplesPerPixel = this.getEntryValue(type, buffer, offset);
                    if (this.samplesPerPixel != 1 && this.samplesPerPixel != 3) {
                        SWT.error(38);
                        break;
                    }
                    break;
                }
                case 278: {
                    this.rowsPerStrip = this.getEntryValue(type, buffer, offset);
                    break;
                }
                case 279: {
                    this.getEntryValue(type, buffer, offset, this.stripByteCounts = new int[count]);
                }
                case 282:
                case 283:
                case 292: {
                    if (type != 4) {
                        SWT.error(40);
                    }
                    this.t4Options = this.getEntryValue(type, buffer, offset);
                    if ((this.t4Options & 0x1) == 0x1) {
                        SWT.error(42);
                        break;
                    }
                    break;
                }
                case 296:
                case 305:
                case 320: {
                    if (type != 3) {
                        SWT.error(40);
                    }
                    this.colorMapOffset = this.getEntryValue(4, buffer, offset);
                    break;
                }
            }
        }
    }
    
    public ImageData read(final int[] nextIFDOffset) throws IOException {
        this.bitsPerSample = new int[] { 1 };
        this.colorMapOffset = -1;
        this.compression = 1;
        this.imageLength = -1;
        this.imageWidth = -1;
        this.photometricInterpretation = -1;
        this.rowsPerStrip = Integer.MAX_VALUE;
        this.samplesPerPixel = 1;
        this.stripByteCounts = null;
        this.stripOffsets = null;
        byte[] buffer = new byte[2];
        this.file.read(buffer);
        final int numberEntries = this.toInt(buffer, 0, 3);
        buffer = new byte[12 * numberEntries];
        this.file.read(buffer);
        final byte[] buffer2 = new byte[4];
        this.file.read(buffer2);
        nextIFDOffset[0] = this.toInt(buffer2, 0, 4);
        this.parseEntries(buffer);
        PaletteData palette = null;
        this.depth = 0;
        switch (this.photometricInterpretation) {
            case 0:
            case 1: {
                palette = this.getGrayPalette();
                this.depth = this.bitsPerSample[0];
                break;
            }
            case 2: {
                if (this.colorMapOffset != -1) {
                    SWT.error(40);
                }
                palette = this.getRGBPalette(this.bitsPerSample[0], this.bitsPerSample[1], this.bitsPerSample[2]);
                this.depth = this.bitsPerSample[0] + this.bitsPerSample[1] + this.bitsPerSample[2];
                break;
            }
            case 3: {
                if (this.colorMapOffset == -1) {
                    SWT.error(40);
                }
                palette = this.getColorMap();
                this.depth = this.bitsPerSample[0];
                break;
            }
            default: {
                SWT.error(40);
                break;
            }
        }
        final ImageData image = ImageData.internal_new(this.imageWidth, this.imageLength, this.depth, palette, 1, (byte[])null, 0, (byte[])null, (byte[])null, -1, -1, 6, 0, 0, 0, 0);
        this.decodePixels(image);
        return image;
    }
    
    int toInt(final byte[] buffer, final int i, final int type) {
        if (type == 4) {
            return this.isLittleEndian ? ((buffer[i] & 0xFF) | (buffer[i + 1] & 0xFF) << 8 | (buffer[i + 2] & 0xFF) << 16 | (buffer[i + 3] & 0xFF) << 24) : ((buffer[i + 3] & 0xFF) | (buffer[i + 2] & 0xFF) << 8 | (buffer[i + 1] & 0xFF) << 16 | (buffer[i] & 0xFF) << 24);
        }
        if (type == 3) {
            return this.isLittleEndian ? ((buffer[i] & 0xFF) | (buffer[i + 1] & 0xFF) << 8) : ((buffer[i + 1] & 0xFF) | (buffer[i] & 0xFF) << 8);
        }
        SWT.error(40);
        return -1;
    }
    
    void write(final int photometricInterpretation) throws IOException {
        final boolean isRGB = photometricInterpretation == 2;
        final boolean isColorMap = photometricInterpretation == 3;
        final boolean isBiLevel = photometricInterpretation == 0 || photometricInterpretation == 1;
        final int imageWidth = this.image.width;
        final int imageLength = this.image.height;
        final int rowByteSize = this.image.bytesPerLine;
        final int numberEntries = isBiLevel ? 9 : 11;
        final int lengthDirectory = 2 + 12 * numberEntries + 4;
        int nextOffset = 8 + lengthDirectory;
        int extraBytes = 16;
        int[] colorMap = null;
        if (isColorMap) {
            final PaletteData palette = this.image.palette;
            final RGB[] rgbs = palette.getRGBs();
            colorMap = this.formatColorMap(rgbs);
            if (colorMap.length != 3 << this.image.depth) {
                SWT.error(42);
            }
            extraBytes += colorMap.length * 2;
        }
        if (isRGB) {
            extraBytes += 6;
        }
        final byte[] data = this.image.data;
        final int[][] strips = new int[2][];
        final int nbrRowsPerStrip = this.formatStrips(rowByteSize, imageLength, data, 8192, nextOffset, extraBytes, strips);
        final int[] stripOffsets = strips[0];
        final int[] stripByteCounts = strips[1];
        int bitsPerSampleOffset = -1;
        if (isRGB) {
            bitsPerSampleOffset = nextOffset;
            nextOffset += 6;
        }
        int stripOffsetsOffset = -1;
        int stripByteCountsOffset = -1;
        int colorMapOffset = -1;
        final int cnt = stripOffsets.length;
        if (cnt > 1) {
            stripOffsetsOffset = nextOffset;
            stripByteCountsOffset = (nextOffset += 4 * cnt);
            nextOffset += 4 * cnt;
        }
        final int xResolutionOffset = nextOffset;
        nextOffset += 8;
        final int yResolutionOffset = nextOffset;
        nextOffset += 8;
        if (isColorMap) {
            colorMapOffset = nextOffset;
            nextOffset += colorMap.length * 2;
        }
        this.writeHeader();
        this.out.writeShort(numberEntries);
        this.writeEntry((short)256, 4, 1, imageWidth);
        this.writeEntry((short)257, 4, 1, imageLength);
        if (isColorMap) {
            this.writeEntry((short)258, 3, 1, this.image.depth);
        }
        if (isRGB) {
            this.writeEntry((short)258, 3, 3, bitsPerSampleOffset);
        }
        this.writeEntry((short)259, 3, 1, 1);
        this.writeEntry((short)262, 3, 1, photometricInterpretation);
        this.writeEntry((short)273, 4, cnt, (cnt > 1) ? stripOffsetsOffset : stripOffsets[0]);
        if (isRGB) {
            this.writeEntry((short)277, 3, 1, 3);
        }
        this.writeEntry((short)278, 4, 1, nbrRowsPerStrip);
        this.writeEntry((short)279, 4, cnt, (cnt > 1) ? stripByteCountsOffset : stripByteCounts[0]);
        this.writeEntry((short)282, 5, 1, xResolutionOffset);
        this.writeEntry((short)283, 5, 1, yResolutionOffset);
        if (isColorMap) {
            this.writeEntry((short)320, 3, colorMap.length, colorMapOffset);
        }
        this.out.writeInt(0);
        if (isRGB) {
            for (int i = 0; i < 3; ++i) {
                this.out.writeShort(8);
            }
        }
        if (cnt > 1) {
            for (int i = 0; i < cnt; ++i) {
                this.out.writeInt(stripOffsets[i]);
            }
            for (int i = 0; i < cnt; ++i) {
                this.out.writeInt(stripByteCounts[i]);
            }
        }
        for (int i = 0; i < 2; ++i) {
            this.out.writeInt(300);
            this.out.writeInt(1);
        }
        if (isColorMap) {
            for (final int element : colorMap) {
                this.out.writeShort(element);
            }
        }
        this.out.write(data);
    }
    
    void writeEntry(final short tag, final int type, final int count, final int value) throws IOException {
        this.out.writeShort((int)tag);
        this.out.writeShort(type);
        this.out.writeInt(count);
        this.out.writeInt(value);
    }
    
    void writeHeader() throws IOException {
        this.out.write(73);
        this.out.write(73);
        this.out.writeShort(42);
        this.out.writeInt(8);
    }
    
    void writeToStream(final LEDataOutputStream byteStream) throws IOException {
        this.out = byteStream;
        int photometricInterpretation = -1;
        if (this.image.scanlinePad != 1) {
            SWT.error(42);
        }
        switch (this.image.depth) {
            case 1: {
                final PaletteData palette = this.image.palette;
                final RGB[] rgbs = palette.colors;
                if (palette.isDirect || rgbs == null || rgbs.length != 2) {
                    SWT.error(42);
                }
                final RGB rgb0 = rgbs[0];
                final RGB rgb2 = rgbs[1];
                if (rgb0.red != rgb0.green || rgb0.green != rgb0.blue || rgb2.red != rgb2.green || rgb2.green != rgb2.blue || ((rgb0.red != 0 || rgb2.red != 255) && (rgb0.red != 255 || rgb2.red != 0))) {
                    SWT.error(42);
                }
                photometricInterpretation = ((this.image.palette.colors[0].red != 255) ? 1 : 0);
                break;
            }
            case 4:
            case 8: {
                photometricInterpretation = 3;
                break;
            }
            case 24: {
                photometricInterpretation = 2;
                break;
            }
            default: {
                SWT.error(42);
                break;
            }
        }
        this.write(photometricInterpretation);
    }
}
