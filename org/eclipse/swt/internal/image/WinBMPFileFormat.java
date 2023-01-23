//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import java.io.*;

public final class WinBMPFileFormat extends FileFormat
{
    static final int BMPFileHeaderSize = 14;
    static final int BMPHeaderFixedSize = 40;
    static final int BI_RGB = 0;
    static final int BI_RLE8 = 1;
    static final int BI_RLE4 = 2;
    static final int BI_BITFIELDS = 3;
    int importantColors;
    Point pelsPerMeter;
    
    public WinBMPFileFormat() {
        this.pelsPerMeter = new Point(0, 0);
    }
    
    int compress(final int comp, final byte[] src, final int srcOffset, final int numBytes, final byte[] dest, final boolean last) {
        if (comp == 1) {
            return this.compressRLE8Data(src, srcOffset, numBytes, dest, last);
        }
        if (comp == 2) {
            return this.compressRLE4Data(src, srcOffset, numBytes, dest, last);
        }
        SWT.error(40);
        return 0;
    }
    
    int compressRLE4Data(final byte[] src, final int srcOffset, final int numBytes, final byte[] dest, final boolean last) {
        int sp = srcOffset;
        final int end = srcOffset + numBytes;
        int dp = 0;
        int size;
        int n;
        for (size = 0; sp < end; sp += n, size += 2) {
            int left = end - sp - 1;
            if (left > 127) {
                left = 127;
            }
            for (n = 0; n < left && src[sp + n] != src[sp + n + 1]; ++n) {}
            if (n < 127 && n == left) {
                ++n;
            }
            switch (n) {
                case 0: {
                    break;
                }
                case 1: {
                    dest[dp] = 2;
                    ++dp;
                    dest[dp] = src[sp];
                    ++dp;
                    ++sp;
                    size += 2;
                    break;
                }
                default: {
                    dest[dp] = 0;
                    ++dp;
                    dest[dp] = (byte)(n + n);
                    ++dp;
                    for (int i = n; i > 0; --i) {
                        dest[dp] = src[sp];
                        ++dp;
                        ++sp;
                    }
                    size += 2 + n;
                    if ((n & 0x1) != 0x0) {
                        dest[dp] = 0;
                        ++dp;
                        ++size;
                        break;
                    }
                    break;
                }
            }
            left = end - sp;
            if (left > 0) {
                if (left > 127) {
                    left = 127;
                }
                byte theByte;
                for (theByte = src[sp], n = 1; n < left && src[sp + n] == theByte; ++n) {}
                dest[dp] = (byte)(n + n);
                ++dp;
                dest[dp] = theByte;
                ++dp;
            }
        }
        dest[dp] = 0;
        ++dp;
        if (last) {
            dest[dp] = 1;
            ++dp;
        }
        else {
            dest[dp] = 0;
            ++dp;
        }
        size += 2;
        return size;
    }
    
    int compressRLE8Data(final byte[] src, final int srcOffset, final int numBytes, final byte[] dest, final boolean last) {
        int sp = srcOffset;
        final int end = srcOffset + numBytes;
        int dp = 0;
        int size;
        int n;
        for (size = 0; sp < end; sp += n, size += 2) {
            int left = end - sp - 1;
            if (left > 254) {
                left = 254;
            }
            for (n = 0; n < left && src[sp + n] != src[sp + n + 1]; ++n) {}
            if (n == left) {
                ++n;
            }
            switch (n) {
                case 0: {
                    break;
                }
                case 2: {
                    dest[dp] = 1;
                    ++dp;
                    dest[dp] = src[sp];
                    ++dp;
                    ++sp;
                    size += 2;
                }
                case 1: {
                    dest[dp] = 1;
                    ++dp;
                    dest[dp] = src[sp];
                    ++dp;
                    ++sp;
                    size += 2;
                    break;
                }
                default: {
                    dest[dp] = 0;
                    ++dp;
                    dest[dp] = (byte)n;
                    ++dp;
                    for (int i = n; i > 0; --i) {
                        dest[dp] = src[sp];
                        ++dp;
                        ++sp;
                    }
                    size += 2 + n;
                    if ((n & 0x1) != 0x0) {
                        dest[dp] = 0;
                        ++dp;
                        ++size;
                        break;
                    }
                    break;
                }
            }
            left = end - sp;
            if (left > 0) {
                if (left > 255) {
                    left = 255;
                }
                byte theByte;
                for (theByte = src[sp], n = 1; n < left && src[sp + n] == theByte; ++n) {}
                dest[dp] = (byte)n;
                ++dp;
                dest[dp] = theByte;
                ++dp;
            }
        }
        dest[dp] = 0;
        ++dp;
        if (last) {
            dest[dp] = 1;
            ++dp;
        }
        else {
            dest[dp] = 0;
            ++dp;
        }
        size += 2;
        return size;
    }
    
    void convertPixelsToBGR(final ImageData image, final byte[] dest) {
        final byte[] data = image.data;
        final PaletteData palette = image.palette;
        for (int y = 0; y < image.height; ++y) {
            int srcX = 0;
            int srcY = y;
            final int numOfBytes = image.depth / 8;
            int index = y * image.bytesPerLine;
            for (int i = 0; i < image.width; ++i) {
                int pixel = 0;
                switch (image.depth) {
                    case 32: {
                        pixel = ((data[index] & 0xFF) << 24 | (data[index + 1] & 0xFF) << 16 | (data[index + 2] & 0xFF) << 8 | (data[index + 3] & 0xFF));
                        break;
                    }
                    case 24: {
                        pixel = ((data[index] & 0xFF) << 16 | (data[index + 1] & 0xFF) << 8 | (data[index + 2] & 0xFF));
                        break;
                    }
                    case 16: {
                        pixel = ((data[index + 1] & 0xFF) << 8 | (data[index] & 0xFF));
                        break;
                    }
                    default: {
                        SWT.error(38);
                        break;
                    }
                }
                if (image.depth == 16) {
                    int r = pixel & palette.redMask;
                    r = ((palette.redShift < 0) ? (r >>> -palette.redShift) : (r << palette.redShift));
                    int g = pixel & palette.greenMask;
                    g = ((palette.greenShift < 0) ? (g >>> -palette.greenShift) : (g << palette.greenShift));
                    g &= 0xF8;
                    int b = pixel & palette.blueMask;
                    b = ((palette.blueShift < 0) ? (b >>> -palette.blueShift) : (b << palette.blueShift));
                    final int modPixel = r << 7 | g << 2 | b >> 3;
                    dest[index] = (byte)(modPixel & 0xFF);
                    dest[index + 1] = (byte)(modPixel >> 8 & 0xFF);
                }
                else {
                    final int b2 = pixel & palette.blueMask;
                    dest[index] = (byte)((palette.blueShift < 0) ? (b2 >>> -palette.blueShift) : (b2 << palette.blueShift));
                    final int g = pixel & palette.greenMask;
                    dest[index + 1] = (byte)((palette.greenShift < 0) ? (g >>> -palette.greenShift) : (g << palette.greenShift));
                    final int r2 = pixel & palette.redMask;
                    dest[index + 2] = (byte)((palette.redShift < 0) ? (r2 >>> -palette.redShift) : (r2 << palette.redShift));
                    if (numOfBytes == 4) {
                        dest[index + 3] = 0;
                    }
                }
                if (++srcX >= image.width) {
                    index = ++srcY * image.bytesPerLine;
                    srcX = 0;
                }
                else {
                    index += numOfBytes;
                }
            }
        }
    }
    
    void decompressData(final byte[] src, final byte[] dest, final int stride, final int cmp) {
        if (cmp == 1) {
            if (this.decompressRLE8Data(src, src.length, stride, dest, dest.length) <= 0) {
                SWT.error(40);
            }
            return;
        }
        if (cmp == 2) {
            if (this.decompressRLE4Data(src, src.length, stride, dest, dest.length) <= 0) {
                SWT.error(40);
            }
            return;
        }
        SWT.error(40);
    }
    
    int decompressRLE4Data(final byte[] src, final int numBytes, final int stride, final byte[] dest, final int destSize) {
        int sp = 0;
        final int se = numBytes;
        int dp = 0;
        final int de = destSize;
        int x = 0;
        int y = 0;
        while (sp < se) {
            int len = src[sp] & 0xFF;
            ++sp;
            if (len == 0) {
                len = (src[sp] & 0xFF);
                ++sp;
                switch (len) {
                    case 0: {
                        ++y;
                        x = 0;
                        dp = y * stride;
                        if (dp > de) {
                            return -1;
                        }
                        continue;
                    }
                    case 1: {
                        return 1;
                    }
                    case 2: {
                        x += (src[sp] & 0xFF);
                        ++sp;
                        y += (src[sp] & 0xFF);
                        ++sp;
                        dp = y * stride + x / 2;
                        if (dp > de) {
                            return -1;
                        }
                        continue;
                    }
                    default: {
                        if ((len & 0x1) != 0x0) {
                            return -1;
                        }
                        x += len;
                        len /= 2;
                        if (len > se - sp) {
                            return -1;
                        }
                        if (len > de - dp) {
                            return -1;
                        }
                        for (int i = 0; i < len; ++i) {
                            dest[dp] = src[sp];
                            ++dp;
                            ++sp;
                        }
                        if ((sp & 0x1) != 0x0) {
                            ++sp;
                            continue;
                        }
                        continue;
                    }
                }
            }
            else {
                if ((len & 0x1) != 0x0) {
                    return -1;
                }
                x += len;
                len /= 2;
                final byte theByte = src[sp];
                ++sp;
                if (len > de - dp) {
                    return -1;
                }
                for (int j = 0; j < len; ++j) {
                    dest[dp] = theByte;
                    ++dp;
                }
            }
        }
        return 1;
    }
    
    int decompressRLE8Data(final byte[] src, final int numBytes, final int stride, final byte[] dest, final int destSize) {
        int sp = 0;
        final int se = numBytes;
        int dp = 0;
        final int de = destSize;
        int x = 0;
        int y = 0;
        while (sp < se) {
            int len = src[sp] & 0xFF;
            ++sp;
            if (len == 0) {
                len = (src[sp] & 0xFF);
                ++sp;
                switch (len) {
                    case 0: {
                        ++y;
                        x = 0;
                        dp = y * stride;
                        if (dp > de) {
                            return -1;
                        }
                        continue;
                    }
                    case 1: {
                        return 1;
                    }
                    case 2: {
                        x += (src[sp] & 0xFF);
                        ++sp;
                        y += (src[sp] & 0xFF);
                        ++sp;
                        dp = y * stride + x;
                        if (dp > de) {
                            return -1;
                        }
                        continue;
                    }
                    default: {
                        if (len > se - sp) {
                            return -1;
                        }
                        if (len > de - dp) {
                            return -1;
                        }
                        for (int i = 0; i < len; ++i) {
                            dest[dp] = src[sp];
                            ++dp;
                            ++sp;
                        }
                        if ((sp & 0x1) != 0x0) {
                            ++sp;
                        }
                        x += len;
                        continue;
                    }
                }
            }
            else {
                final byte theByte = src[sp];
                ++sp;
                if (len > de - dp) {
                    return -1;
                }
                for (int j = 0; j < len; ++j) {
                    dest[dp] = theByte;
                    ++dp;
                }
                x += len;
            }
        }
        return 1;
    }
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] header = new byte[18];
            stream.read(header);
            stream.unread(header);
            final int infoHeaderSize = (header[14] & 0xFF) | (header[15] & 0xFF) << 8 | (header[16] & 0xFF) << 16 | (header[17] & 0xFF) << 24;
            return header[0] == 66 && header[1] == 77 && infoHeaderSize >= 40;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    boolean isPaletteBMP(final PaletteData pal, final int depth) {
        switch (depth) {
            case 32: {
                return pal.redMask == 65280 && pal.greenMask == 16711680 && pal.blueMask == -16777216;
            }
            case 24: {
                return pal.redMask == 255 && pal.greenMask == 65280 && pal.blueMask == 16711680;
            }
            case 16: {
                return pal.redMask == 31744 && pal.greenMask == 992 && pal.blueMask == 31;
            }
            default: {
                return true;
            }
        }
    }
    
    byte[] loadData(final byte[] infoHeader) {
        final int width = (infoHeader[4] & 0xFF) | (infoHeader[5] & 0xFF) << 8 | (infoHeader[6] & 0xFF) << 16 | (infoHeader[7] & 0xFF) << 24;
        final int height = (infoHeader[8] & 0xFF) | (infoHeader[9] & 0xFF) << 8 | (infoHeader[10] & 0xFF) << 16 | (infoHeader[11] & 0xFF) << 24;
        final int bitCount = (infoHeader[14] & 0xFF) | (infoHeader[15] & 0xFF) << 8;
        int stride = (width * bitCount + 7) / 8;
        stride = (stride + 3) / 4 * 4;
        final byte[] data = this.loadData(infoHeader, stride);
        this.flipScanLines(data, stride, height);
        return data;
    }
    
    byte[] loadData(final byte[] infoHeader, final int stride) {
        int height = (infoHeader[8] & 0xFF) | (infoHeader[9] & 0xFF) << 8 | (infoHeader[10] & 0xFF) << 16 | (infoHeader[11] & 0xFF) << 24;
        if (height < 0) {
            height = -height;
        }
        final int dataSize = height * stride;
        final byte[] data = new byte[dataSize];
        final int cmp = (infoHeader[16] & 0xFF) | (infoHeader[17] & 0xFF) << 8 | (infoHeader[18] & 0xFF) << 16 | (infoHeader[19] & 0xFF) << 24;
        if (cmp != 0 && cmp != 3) {
            final int compressedSize = (infoHeader[20] & 0xFF) | (infoHeader[21] & 0xFF) << 8 | (infoHeader[22] & 0xFF) << 16 | (infoHeader[23] & 0xFF) << 24;
            final byte[] compressed = new byte[compressedSize];
            try {
                if (this.inputStream.read(compressed) != compressedSize) {
                    SWT.error(40);
                }
            }
            catch (IOException e) {
                SWT.error(39, e);
            }
            this.decompressData(compressed, data, stride, cmp);
            return data;
        }
        try {
            if (this.inputStream.read(data) != dataSize) {
                SWT.error(40);
            }
        }
        catch (IOException e2) {
            SWT.error(39, e2);
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
        final byte[] infoHeader = new byte[40];
        try {
            this.inputStream.read(infoHeader);
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
        final int width = (infoHeader[4] & 0xFF) | (infoHeader[5] & 0xFF) << 8 | (infoHeader[6] & 0xFF) << 16 | (infoHeader[7] & 0xFF) << 24;
        int height = (infoHeader[8] & 0xFF) | (infoHeader[9] & 0xFF) << 8 | (infoHeader[10] & 0xFF) << 16 | (infoHeader[11] & 0xFF) << 24;
        if (height < 0) {
            height = -height;
        }
        final int bitCount = (infoHeader[14] & 0xFF) | (infoHeader[15] & 0xFF) << 8;
        this.compression = ((infoHeader[16] & 0xFF) | (infoHeader[17] & 0xFF) << 8 | (infoHeader[18] & 0xFF) << 16 | (infoHeader[19] & 0xFF) << 24);
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
        this.importantColors = ((infoHeader[36] & 0xFF) | (infoHeader[37] & 0xFF) << 8 | (infoHeader[38] & 0xFF) << 16 | (infoHeader[39] & 0xFF) << 24);
        final int xPelsPerMeter = (infoHeader[24] & 0xFF) | (infoHeader[25] & 0xFF) << 8 | (infoHeader[26] & 0xFF) << 16 | (infoHeader[27] & 0xFF) << 24;
        final int yPelsPerMeter = (infoHeader[28] & 0xFF) | (infoHeader[29] & 0xFF) << 8 | (infoHeader[30] & 0xFF) << 16 | (infoHeader[31] & 0xFF) << 24;
        this.pelsPerMeter = new Point(xPelsPerMeter, yPelsPerMeter);
        final int type = (this.compression == 1 || this.compression == 2) ? 1 : 0;
        return new ImageData[] { ImageData.internal_new(width, height, bitCount, palette, 4, data, 0, (byte[])null, (byte[])null, -1, -1, type, 0, 0, 0, 0) };
    }
    
    PaletteData loadPalette(final byte[] infoHeader) {
        final int depth = (infoHeader[14] & 0xFF) | (infoHeader[15] & 0xFF) << 8;
        if (depth <= 8) {
            int numColors = (infoHeader[32] & 0xFF) | (infoHeader[33] & 0xFF) << 8 | (infoHeader[34] & 0xFF) << 16 | (infoHeader[35] & 0xFF) << 24;
            if (numColors == 0) {
                numColors = 1 << depth;
            }
            else if (numColors > 256) {
                numColors = 256;
            }
            final byte[] buf = new byte[numColors * 4];
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
        if (depth == 16) {
            if (this.compression == 3) {
                try {
                    return new PaletteData(this.inputStream.readInt(), this.inputStream.readInt(), this.inputStream.readInt());
                }
                catch (IOException e2) {
                    SWT.error(39, e2);
                }
            }
            return new PaletteData(31744, 992, 31);
        }
        if (depth == 24) {
            return new PaletteData(255, 65280, 16711680);
        }
        if (this.compression == 3) {
            try {
                final int maskR = Integer.reverseBytes(this.inputStream.readInt());
                final int maskG = Integer.reverseBytes(this.inputStream.readInt());
                final int maskB = Integer.reverseBytes(this.inputStream.readInt());
                return new PaletteData(maskR, maskG, maskB);
            }
            catch (IOException e2) {
                SWT.error(39, e2);
            }
        }
        return new PaletteData(65280, 16711680, -16777216);
    }
    
    PaletteData paletteFromBytes(final byte[] bytes, final int numColors) {
        int bytesOffset = 0;
        final RGB[] colors = new RGB[numColors];
        for (int i = 0; i < numColors; ++i) {
            colors[i] = new RGB(bytes[bytesOffset + 2] & 0xFF, bytes[bytesOffset + 1] & 0xFF, bytes[bytesOffset] & 0xFF);
            bytesOffset += 4;
        }
        return new PaletteData(colors);
    }
    
    static byte[] paletteToBytes(final PaletteData pal) {
        final int n = (pal.colors == null) ? 0 : ((pal.colors.length < 256) ? pal.colors.length : 256);
        final byte[] bytes = new byte[n * 4];
        int offset = 0;
        for (int i = 0; i < n; ++i) {
            final RGB col = pal.colors[i];
            bytes[offset] = (byte)col.blue;
            bytes[offset + 1] = (byte)col.green;
            bytes[offset + 2] = (byte)col.red;
            offset += 4;
        }
        return bytes;
    }
    
    int unloadData(final ImageData image, byte[] data, final OutputStream out, final int comp) {
        int totalSize = 0;
        try {
            if (comp == 0) {
                return this.unloadDataNoCompression(image, data, out);
            }
            final int bpl = (image.width * image.depth + 7) / 8;
            final int bmpBpl = (bpl + 3) / 4 * 4;
            final int imageBpl = image.bytesPerLine;
            final byte[] buf = new byte[bmpBpl * 2];
            int srcOffset = imageBpl * (image.height - 1);
            if (data == null) {
                data = image.data;
            }
            totalSize = 0;
            final byte[] buf2 = new byte[32768];
            int buf2Offset = 0;
            for (int y = image.height - 1; y >= 0; --y) {
                final int lineSize = this.compress(comp, data, srcOffset, bpl, buf, y == 0);
                if (buf2Offset + lineSize > buf2.length) {
                    out.write(buf2, 0, buf2Offset);
                    buf2Offset = 0;
                }
                System.arraycopy(buf, 0, buf2, buf2Offset, lineSize);
                buf2Offset += lineSize;
                totalSize += lineSize;
                srcOffset -= imageBpl;
            }
            if (buf2Offset > 0) {
                out.write(buf2, 0, buf2Offset);
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        return totalSize;
    }
    
    int unloadDataNoCompression(final ImageData image, byte[] data, final OutputStream out) {
        int bmpBpl = 0;
        try {
            final int bpl = (image.width * image.depth + 7) / 8;
            bmpBpl = (bpl + 3) / 4 * 4;
            final int linesPerBuf = 32678 / bmpBpl;
            final byte[] buf = new byte[linesPerBuf * bmpBpl];
            if (data == null) {
                data = image.data;
            }
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
        final int comp = this.compression;
        if (comp != 0 && (comp != 1 || image.depth != 8) && (comp != 2 || image.depth != 4)) {
            SWT.error(40);
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
        final int headersSize = 54;
        final int[] fileHeader = { 19778, 0, 0, 0, 54 };
        if (rgbs != null) {
            final int[] array = fileHeader;
            final int n = 4;
            final int[] array2 = array;
            final int n2 = 4;
            array2[n2] += rgbs.length;
        }
        byte[] iData = null;
        if (pal.isDirect && !this.isPaletteBMP(pal, image.depth)) {
            iData = new byte[image.data.length];
            this.convertPixelsToBGR(image, iData);
        }
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        this.unloadData(image, iData, out, comp);
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
            this.outputStream.writeInt(40);
            this.outputStream.writeInt(image.width);
            this.outputStream.writeInt(image.height);
            this.outputStream.writeShort(1);
            this.outputStream.writeShort((int)(short)image.depth);
            this.outputStream.writeInt(comp);
            this.outputStream.writeInt(data.length);
            this.outputStream.writeInt(this.pelsPerMeter.x);
            this.outputStream.writeInt(this.pelsPerMeter.y);
            this.outputStream.writeInt(numCols);
            this.outputStream.writeInt(this.importantColors);
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
