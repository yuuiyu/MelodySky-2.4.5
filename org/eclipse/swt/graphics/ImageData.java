//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.internal.*;

public final class ImageData implements Cloneable
{
    public int width;
    public int height;
    public int depth;
    public int scanlinePad;
    public int bytesPerLine;
    public byte[] data;
    public PaletteData palette;
    public int transparentPixel;
    public byte[] maskData;
    public int maskPad;
    public byte[] alphaData;
    public int alpha;
    public int type;
    public int x;
    public int y;
    public int disposalMethod;
    public int delayTime;
    static final byte[][] ANY_TO_EIGHT;
    static final byte[] ONE_TO_ONE_MAPPING;
    static final int[][] DITHER_MATRIX;
    static final int BLIT_SRC = 1;
    static final int BLIT_ALPHA = 2;
    static final int BLIT_DITHER = 4;
    static final int ALPHA_OPAQUE = 255;
    static final int ALPHA_TRANSPARENT = 0;
    static final int ALPHA_CHANNEL_SEPARATE = -1;
    static final int ALPHA_CHANNEL_SOURCE = -2;
    static final int ALPHA_MASK_UNPACKED = -3;
    static final int ALPHA_MASK_PACKED = -4;
    static final int ALPHA_MASK_INDEX = -5;
    static final int ALPHA_MASK_RGB = -6;
    static final int LSB_FIRST = 0;
    static final int MSB_FIRST = 1;
    private static final int TYPE_GENERIC_8 = 0;
    private static final int TYPE_GENERIC_16_MSB = 1;
    private static final int TYPE_GENERIC_16_LSB = 2;
    private static final int TYPE_GENERIC_24 = 3;
    private static final int TYPE_GENERIC_32_MSB = 4;
    private static final int TYPE_GENERIC_32_LSB = 5;
    private static final int TYPE_INDEX_8 = 6;
    private static final int TYPE_INDEX_4 = 7;
    private static final int TYPE_INDEX_2 = 8;
    private static final int TYPE_INDEX_1_MSB = 9;
    private static final int TYPE_INDEX_1_LSB = 10;
    
    public ImageData(final int width, final int height, final int depth, final PaletteData palette) {
        this(width, height, depth, palette, 4, null, 0, null, null, -1, -1, -1, 0, 0, 0, 0);
    }
    
    public ImageData(final int width, final int height, final int depth, final PaletteData palette, final int scanlinePad, final byte[] data) {
        this(width, height, depth, palette, scanlinePad, checkData(data), 0, null, null, -1, -1, -1, 0, 0, 0, 0);
    }
    
    public ImageData(final InputStream stream) {
        final ImageData[] data = ImageDataLoader.load(stream);
        if (data.length < 1) {
            SWT.error(40);
        }
        final ImageData i = data[0];
        this.setAllFields(i.width, i.height, i.depth, i.scanlinePad, i.bytesPerLine, i.data, i.palette, i.transparentPixel, i.maskData, i.maskPad, i.alphaData, i.alpha, i.type, i.x, i.y, i.disposalMethod, i.delayTime);
    }
    
    public ImageData(final String filename) {
        final ImageData[] data = ImageDataLoader.load(filename);
        if (data.length < 1) {
            SWT.error(40);
        }
        final ImageData i = data[0];
        this.setAllFields(i.width, i.height, i.depth, i.scanlinePad, i.bytesPerLine, i.data, i.palette, i.transparentPixel, i.maskData, i.maskPad, i.alphaData, i.alpha, i.type, i.x, i.y, i.disposalMethod, i.delayTime);
    }
    
    ImageData() {
    }
    
    ImageData(final int width, final int height, final int depth, final PaletteData palette, final int scanlinePad, final byte[] data, final int maskPad, final byte[] maskData, final byte[] alphaData, final int alpha, final int transparentPixel, final int type, final int x, final int y, final int disposalMethod, final int delayTime) {
        if (palette == null) {
            SWT.error(4);
        }
        if (depth != 1 && depth != 2 && depth != 4 && depth != 8 && depth != 16 && depth != 24 && depth != 32) {
            SWT.error(5);
        }
        if (width <= 0 || height <= 0) {
            SWT.error(5);
        }
        if (scanlinePad == 0) {
            SWT.error(7);
        }
        final int bytesPerLine = ((width * depth + 7) / 8 + (scanlinePad - 1)) / scanlinePad * scanlinePad;
        final int minBytesPerLine = (type == 5) ? (((width + 7) / 8 + 3) / 4 * 4) : bytesPerLine;
        if (data != null && data.length < minBytesPerLine * height) {
            SWT.error(5);
        }
        this.setAllFields(width, height, depth, scanlinePad, bytesPerLine, (data != null) ? data : new byte[bytesPerLine * height], palette, transparentPixel, maskData, maskPad, alphaData, alpha, type, x, y, disposalMethod, delayTime);
    }
    
    void setAllFields(final int width, final int height, final int depth, final int scanlinePad, final int bytesPerLine, final byte[] data, final PaletteData palette, final int transparentPixel, final byte[] maskData, final int maskPad, final byte[] alphaData, final int alpha, final int type, final int x, final int y, final int disposalMethod, final int delayTime) {
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.scanlinePad = scanlinePad;
        this.bytesPerLine = bytesPerLine;
        this.data = data;
        this.palette = palette;
        this.transparentPixel = transparentPixel;
        this.maskData = maskData;
        this.maskPad = maskPad;
        this.alphaData = alphaData;
        this.alpha = alpha;
        this.type = type;
        this.x = x;
        this.y = y;
        this.disposalMethod = disposalMethod;
        this.delayTime = delayTime;
    }
    
    public static ImageData internal_new(final int width, final int height, final int depth, final PaletteData palette, final int scanlinePad, final byte[] data, final int maskPad, final byte[] maskData, final byte[] alphaData, final int alpha, final int transparentPixel, final int type, final int x, final int y, final int disposalMethod, final int delayTime) {
        return new ImageData(width, height, depth, palette, scanlinePad, data, maskPad, maskData, alphaData, alpha, transparentPixel, type, x, y, disposalMethod, delayTime);
    }
    
    ImageData colorMaskImage(final int pixel) {
        final ImageData mask = new ImageData(this.width, this.height, 1, bwPalette(), 2, null, 0, null, null, -1, -1, -1, 0, 0, 0, 0);
        final int[] row = new int[this.width];
        for (int y = 0; y < this.height; ++y) {
            this.getPixels(0, y, this.width, row, 0);
            for (int i = 0; i < this.width; ++i) {
                if (pixel != -1 && row[i] == pixel) {
                    row[i] = 0;
                }
                else {
                    row[i] = 1;
                }
            }
            mask.setPixels(0, y, this.width, row, 0);
        }
        return mask;
    }
    
    static byte[] checkData(final byte[] data) {
        if (data == null) {
            SWT.error(4);
        }
        return data;
    }
    
    public Object clone() {
        final byte[] cloneData = new byte[this.data.length];
        System.arraycopy(this.data, 0, cloneData, 0, this.data.length);
        byte[] cloneMaskData = null;
        if (this.maskData != null) {
            cloneMaskData = new byte[this.maskData.length];
            System.arraycopy(this.maskData, 0, cloneMaskData, 0, this.maskData.length);
        }
        byte[] cloneAlphaData = null;
        if (this.alphaData != null) {
            cloneAlphaData = new byte[this.alphaData.length];
            System.arraycopy(this.alphaData, 0, cloneAlphaData, 0, this.alphaData.length);
        }
        return new ImageData(this.width, this.height, this.depth, this.palette, this.scanlinePad, cloneData, this.maskPad, cloneMaskData, cloneAlphaData, this.alpha, this.transparentPixel, this.type, this.x, this.y, this.disposalMethod, this.delayTime);
    }
    
    public int getAlpha(final int x, final int y) {
        if (x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (this.alphaData == null) {
            return 255;
        }
        return this.alphaData[y * this.width + x] & 0xFF;
    }
    
    public void getAlphas(final int x, final int y, final int getWidth, final byte[] alphas, final int startIndex) {
        if (alphas == null) {
            SWT.error(4);
        }
        if (getWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (getWidth == 0) {
            return;
        }
        if (this.alphaData == null) {
            for (int endIndex = startIndex + getWidth, i = startIndex; i < endIndex; ++i) {
                alphas[i] = -1;
            }
            return;
        }
        System.arraycopy(this.alphaData, y * this.width + x, alphas, startIndex, getWidth);
    }
    
    public int getPixel(final int x, final int y) {
        if (x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        switch (this.depth) {
            case 32: {
                final int index = y * this.bytesPerLine + x * 4;
                return ((this.data[index] & 0xFF) << 24) + ((this.data[index + 1] & 0xFF) << 16) + ((this.data[index + 2] & 0xFF) << 8) + (this.data[index + 3] & 0xFF);
            }
            case 24: {
                final int index = y * this.bytesPerLine + x * 3;
                return ((this.data[index] & 0xFF) << 16) + ((this.data[index + 1] & 0xFF) << 8) + (this.data[index + 2] & 0xFF);
            }
            case 16: {
                final int index = y * this.bytesPerLine + x * 2;
                return ((this.data[index + 1] & 0xFF) << 8) + (this.data[index] & 0xFF);
            }
            case 8: {
                final int index = y * this.bytesPerLine + x;
                return this.data[index] & 0xFF;
            }
            case 4: {
                final int index = y * this.bytesPerLine + (x >> 1);
                final int theByte = this.data[index] & 0xFF;
                if ((x & 0x1) == 0x0) {
                    return theByte >> 4;
                }
                return theByte & 0xF;
            }
            case 2: {
                final int index = y * this.bytesPerLine + (x >> 2);
                final int theByte = this.data[index] & 0xFF;
                final int offset = 3 - x % 4;
                final int mask = 3 << offset * 2;
                return (theByte & mask) >> offset * 2;
            }
            case 1: {
                final int index = y * this.bytesPerLine + (x >> 3);
                final int theByte = this.data[index] & 0xFF;
                final int mask2 = 1 << 7 - (x & 0x7);
                if ((theByte & mask2) == 0x0) {
                    return 0;
                }
                return 1;
            }
            default: {
                SWT.error(38);
                return 0;
            }
        }
    }
    
    public void getPixels(final int x, final int y, final int getWidth, final byte[] pixels, final int startIndex) {
        if (pixels == null) {
            SWT.error(4);
        }
        if (getWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (getWidth == 0) {
            return;
        }
        int mask = 0;
        int n = getWidth;
        int i = startIndex;
        int srcX = x;
        int srcY = y;
        switch (this.depth) {
            case 8: {
                int index = y * this.bytesPerLine + x;
                for (int j = 0; j < getWidth; ++j) {
                    pixels[i] = this.data[index];
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
            }
            case 4: {
                int index = y * this.bytesPerLine + (x >> 1);
                if ((x & 0x1) == 0x1) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = (byte)(theByte & 0xF);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
                while (n > 1) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = (byte)(theByte >> 4);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        pixels[i] = (byte)(theByte & 0xF);
                        ++i;
                        --n;
                        if (++srcX >= this.width) {
                            index = ++srcY * this.bytesPerLine;
                            srcX = 0;
                        }
                        else {
                            ++index;
                        }
                    }
                }
                if (n > 0) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = (byte)(theByte >> 4);
                }
            }
            case 2: {
                int index = y * this.bytesPerLine + (x >> 2);
                int theByte = this.data[index] & 0xFF;
                while (n > 0) {
                    final int offset = 3 - srcX % 4;
                    mask = 3 << offset * 2;
                    pixels[i] = (byte)((theByte & mask) >> offset * 2);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        if (n > 0) {
                            theByte = (this.data[index] & 0xFF);
                        }
                        srcX = 0;
                    }
                    else {
                        if (offset != 0) {
                            continue;
                        }
                        ++index;
                        theByte = (this.data[index] & 0xFF);
                    }
                }
            }
            case 1: {
                int index = y * this.bytesPerLine + (x >> 3);
                int theByte = this.data[index] & 0xFF;
                while (n > 0) {
                    mask = 1 << 7 - (srcX & 0x7);
                    if ((theByte & mask) == 0x0) {
                        pixels[i] = 0;
                    }
                    else {
                        pixels[i] = 1;
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        if (n > 0) {
                            theByte = (this.data[index] & 0xFF);
                        }
                        srcX = 0;
                    }
                    else {
                        if (mask != 1) {
                            continue;
                        }
                        ++index;
                        if (n <= 0) {
                            continue;
                        }
                        theByte = (this.data[index] & 0xFF);
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void getPixels(final int x, final int y, final int getWidth, final int[] pixels, final int startIndex) {
        if (pixels == null) {
            SWT.error(4);
        }
        if (getWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (getWidth == 0) {
            return;
        }
        int n = getWidth;
        int i = startIndex;
        int srcX = x;
        int srcY = y;
        switch (this.depth) {
            case 32: {
                int index = y * this.bytesPerLine + x * 4;
                i = startIndex;
                for (int j = 0; j < getWidth; ++j) {
                    pixels[i] = ((this.data[index] & 0xFF) << 24 | (this.data[index + 1] & 0xFF) << 16 | (this.data[index + 2] & 0xFF) << 8 | (this.data[index + 3] & 0xFF));
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 4;
                    }
                }
            }
            case 24: {
                int index = y * this.bytesPerLine + x * 3;
                for (int j = 0; j < getWidth; ++j) {
                    pixels[i] = ((this.data[index] & 0xFF) << 16 | (this.data[index + 1] & 0xFF) << 8 | (this.data[index + 2] & 0xFF));
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 3;
                    }
                }
            }
            case 16: {
                int index = y * this.bytesPerLine + x * 2;
                for (int j = 0; j < getWidth; ++j) {
                    pixels[i] = ((this.data[index + 1] & 0xFF) << 8) + (this.data[index] & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 2;
                    }
                }
            }
            case 8: {
                int index = y * this.bytesPerLine + x;
                for (int j = 0; j < getWidth; ++j) {
                    pixels[i] = (this.data[index] & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
            }
            case 4: {
                int index = y * this.bytesPerLine + (x >> 1);
                if ((x & 0x1) == 0x1) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = (theByte & 0xF);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
                while (n > 1) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = theByte >> 4;
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        pixels[i] = (theByte & 0xF);
                        ++i;
                        --n;
                        if (++srcX >= this.width) {
                            index = ++srcY * this.bytesPerLine;
                            srcX = 0;
                        }
                        else {
                            ++index;
                        }
                    }
                }
                if (n > 0) {
                    final int theByte = this.data[index] & 0xFF;
                    pixels[i] = theByte >> 4;
                }
            }
            case 2: {
                int index = y * this.bytesPerLine + (x >> 2);
                int theByte = this.data[index] & 0xFF;
                while (n > 0) {
                    final int offset = 3 - srcX % 4;
                    final int mask = 3 << offset * 2;
                    pixels[i] = (byte)((theByte & mask) >> offset * 2);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        if (n > 0) {
                            theByte = (this.data[index] & 0xFF);
                        }
                        srcX = 0;
                    }
                    else {
                        if (offset != 0) {
                            continue;
                        }
                        ++index;
                        theByte = (this.data[index] & 0xFF);
                    }
                }
            }
            case 1: {
                int index = y * this.bytesPerLine + (x >> 3);
                int theByte = this.data[index] & 0xFF;
                while (n > 0) {
                    final int mask2 = 1 << 7 - (srcX & 0x7);
                    if ((theByte & mask2) == 0x0) {
                        pixels[i] = 0;
                    }
                    else {
                        pixels[i] = 1;
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        if (n > 0) {
                            theByte = (this.data[index] & 0xFF);
                        }
                        srcX = 0;
                    }
                    else {
                        if (mask2 != 1) {
                            continue;
                        }
                        ++index;
                        if (n <= 0) {
                            continue;
                        }
                        theByte = (this.data[index] & 0xFF);
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public RGB[] getRGBs() {
        return this.palette.getRGBs();
    }
    
    public ImageData getTransparencyMask() {
        final int transparencyType = this.getTransparencyType();
        switch (transparencyType) {
            case 1: {
                return this.getTransparencyMaskFromAlphaData();
            }
            case 2: {
                return new ImageData(this.width, this.height, 1, bwPalette(), this.maskPad, this.maskData);
            }
            case 4: {
                return this.colorMaskImage(this.transparentPixel);
            }
            default: {
                return this.colorMaskImage(this.transparentPixel);
            }
        }
    }
    
    ImageData getTransparencyMaskFromAlphaData() {
        final ImageData mask = new ImageData(this.width, this.height, 1, bwPalette(), 2, null, 0, null, null, -1, -1, -1, 0, 0, 0, 0);
        int offset = 0;
        for (int y = 0; y < this.height; ++y) {
            for (int x = 0; x < this.width; ++x) {
                final byte a = this.alphaData[offset++];
                if (a == 0) {
                    mask.setPixel(x, y, 0);
                }
                else {
                    mask.setPixel(x, y, 1);
                }
            }
        }
        return mask;
    }
    
    public int getTransparencyType() {
        if (this.maskData != null) {
            return 2;
        }
        if (this.transparentPixel != -1) {
            return 4;
        }
        if (this.alphaData != null) {
            return 1;
        }
        return 0;
    }
    
    int getByteOrder() {
        return (this.depth != 16) ? 1 : 0;
    }
    
    public ImageData scaledTo(int width, int height) {
        final boolean flipX = width < 0;
        if (flipX) {
            width = -width;
        }
        final boolean flipY = height < 0;
        if (flipY) {
            height = -height;
        }
        final ImageData dest = new ImageData(width, height, this.depth, this.palette, this.scanlinePad, null, 0, null, null, -1, this.transparentPixel, this.type, this.x, this.y, this.disposalMethod, this.delayTime);
        if (this.palette.isDirect) {
            blit(1, this.data, this.depth, this.bytesPerLine, this.getByteOrder(), 0, 0, this.width, this.height, 0, 0, 0, 255, null, 0, 0, 0, dest.data, dest.depth, dest.bytesPerLine, dest.getByteOrder(), 0, 0, dest.width, dest.height, 0, 0, 0, flipX, flipY);
        }
        else {
            blit(1, this.data, this.depth, this.bytesPerLine, this.getByteOrder(), 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, dest.data, dest.depth, dest.bytesPerLine, dest.getByteOrder(), 0, 0, dest.width, dest.height, null, null, null, flipX, flipY);
        }
        if (this.maskData != null) {
            dest.maskPad = this.maskPad;
            int destBpl = (dest.width + 7) / 8;
            destBpl = (destBpl + (dest.maskPad - 1)) / dest.maskPad * dest.maskPad;
            dest.maskData = new byte[destBpl * dest.height];
            int srcBpl = (this.width + 7) / 8;
            srcBpl = (srcBpl + (this.maskPad - 1)) / this.maskPad * this.maskPad;
            blit(1, this.maskData, 1, srcBpl, 1, 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, dest.maskData, 1, destBpl, 1, 0, 0, dest.width, dest.height, null, null, null, flipX, flipY);
        }
        else if (this.alpha != -1) {
            dest.alpha = this.alpha;
        }
        else if (this.alphaData != null) {
            dest.alphaData = new byte[dest.width * dest.height];
            blit(1, this.alphaData, 8, this.width, 1, 0, 0, this.width, this.height, null, null, null, 255, null, 0, 0, 0, dest.alphaData, 8, dest.width, 1, 0, 0, dest.width, dest.height, null, null, null, flipX, flipY);
        }
        return dest;
    }
    
    public void setAlpha(final int x, final int y, final int alpha) {
        if (x >= this.width || y >= this.height || x < 0 || y < 0 || alpha < 0 || alpha > 255) {
            SWT.error(5);
        }
        if (this.alphaData == null) {
            this.alphaData = new byte[this.width * this.height];
        }
        this.alphaData[y * this.width + x] = (byte)alpha;
    }
    
    public void setAlphas(final int x, final int y, final int putWidth, final byte[] alphas, final int startIndex) {
        if (alphas == null) {
            SWT.error(4);
        }
        if (putWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (putWidth == 0) {
            return;
        }
        if (this.alphaData == null) {
            this.alphaData = new byte[this.width * this.height];
        }
        System.arraycopy(alphas, startIndex, this.alphaData, y * this.width + x, putWidth);
    }
    
    public void setPixel(final int x, final int y, final int pixelValue) {
        if (x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        switch (this.depth) {
            case 32: {
                final int index = y * this.bytesPerLine + x * 4;
                this.data[index] = (byte)(pixelValue >> 24 & 0xFF);
                this.data[index + 1] = (byte)(pixelValue >> 16 & 0xFF);
                this.data[index + 2] = (byte)(pixelValue >> 8 & 0xFF);
                this.data[index + 3] = (byte)(pixelValue & 0xFF);
            }
            case 24: {
                final int index = y * this.bytesPerLine + x * 3;
                this.data[index] = (byte)(pixelValue >> 16 & 0xFF);
                this.data[index + 1] = (byte)(pixelValue >> 8 & 0xFF);
                this.data[index + 2] = (byte)(pixelValue & 0xFF);
            }
            case 16: {
                final int index = y * this.bytesPerLine + x * 2;
                this.data[index + 1] = (byte)(pixelValue >> 8 & 0xFF);
                this.data[index] = (byte)(pixelValue & 0xFF);
            }
            case 8: {
                final int index = y * this.bytesPerLine + x;
                this.data[index] = (byte)(pixelValue & 0xFF);
            }
            case 4: {
                final int index = y * this.bytesPerLine + (x >> 1);
                if ((x & 0x1) == 0x0) {
                    this.data[index] = (byte)((this.data[index] & 0xF) | (pixelValue & 0xF) << 4);
                }
                else {
                    this.data[index] = (byte)((this.data[index] & 0xF0) | (pixelValue & 0xF));
                }
            }
            case 2: {
                final int index = y * this.bytesPerLine + (x >> 2);
                final byte theByte = this.data[index];
                final int offset = 3 - x % 4;
                final int mask = 0xFF ^ 3 << offset * 2;
                this.data[index] = (byte)((this.data[index] & mask) | pixelValue << offset * 2);
            }
            case 1: {
                final int index = y * this.bytesPerLine + (x >> 3);
                final byte theByte = this.data[index];
                final int mask2 = 1 << 7 - (x & 0x7);
                if ((pixelValue & 0x1) == 0x1) {
                    this.data[index] = (byte)(theByte | mask2);
                }
                else {
                    this.data[index] = (byte)(theByte & ~mask2);
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void setPixels(final int x, final int y, final int putWidth, final byte[] pixels, final int startIndex) {
        if (pixels == null) {
            SWT.error(4);
        }
        if (putWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (putWidth == 0) {
            return;
        }
        int n = putWidth;
        int i = startIndex;
        int srcX = x;
        int srcY = y;
        switch (this.depth) {
            case 8: {
                int index = y * this.bytesPerLine + x;
                for (int j = 0; j < putWidth; ++j) {
                    this.data[index] = (byte)(pixels[i] & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
            }
            case 4: {
                int index = y * this.bytesPerLine + (x >> 1);
                boolean high = (x & 0x1) == 0x0;
                while (n > 0) {
                    final int theByte = pixels[i] & 0xF;
                    if (high) {
                        this.data[index] = (byte)((this.data[index] & 0xF) | theByte << 4);
                    }
                    else {
                        this.data[index] = (byte)((this.data[index] & 0xF0) | theByte);
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        high = true;
                        srcX = 0;
                    }
                    else {
                        if (!high) {
                            ++index;
                        }
                        high = !high;
                    }
                }
            }
            case 2: {
                final byte[] masks = { -4, -13, -49, 63 };
                int index2 = y * this.bytesPerLine + (x >> 2);
                int offset = 3 - x % 4;
                while (n > 0) {
                    final int theByte2 = pixels[i] & 0x3;
                    this.data[index2] = (byte)((this.data[index2] & masks[offset]) | theByte2 << offset * 2);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index2 = ++srcY * this.bytesPerLine;
                        offset = 0;
                        srcX = 0;
                    }
                    else if (offset == 0) {
                        ++index2;
                        offset = 3;
                    }
                    else {
                        --offset;
                    }
                }
            }
            case 1: {
                int index = y * this.bytesPerLine + (x >> 3);
                while (n > 0) {
                    final int mask = 1 << 7 - (srcX & 0x7);
                    if ((pixels[i] & 0x1) == 0x1) {
                        this.data[index] = (byte)((this.data[index] & 0xFF) | mask);
                    }
                    else {
                        this.data[index] = (byte)(this.data[index] & 0xFF & ~mask);
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        if (mask != 1) {
                            continue;
                        }
                        ++index;
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    public void setPixels(final int x, final int y, final int putWidth, final int[] pixels, final int startIndex) {
        if (pixels == null) {
            SWT.error(4);
        }
        if (putWidth < 0 || x >= this.width || y >= this.height || x < 0 || y < 0) {
            SWT.error(5);
        }
        if (putWidth == 0) {
            return;
        }
        int n = putWidth;
        int i = startIndex;
        int srcX = x;
        int srcY = y;
        switch (this.depth) {
            case 32: {
                int index = y * this.bytesPerLine + x * 4;
                for (int j = 0; j < putWidth; ++j) {
                    final int pixel = pixels[i];
                    this.data[index] = (byte)(pixel >> 24 & 0xFF);
                    this.data[index + 1] = (byte)(pixel >> 16 & 0xFF);
                    this.data[index + 2] = (byte)(pixel >> 8 & 0xFF);
                    this.data[index + 3] = (byte)(pixel & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 4;
                    }
                }
            }
            case 24: {
                int index = y * this.bytesPerLine + x * 3;
                for (int j = 0; j < putWidth; ++j) {
                    final int pixel = pixels[i];
                    this.data[index] = (byte)(pixel >> 16 & 0xFF);
                    this.data[index + 1] = (byte)(pixel >> 8 & 0xFF);
                    this.data[index + 2] = (byte)(pixel & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 3;
                    }
                }
            }
            case 16: {
                int index = y * this.bytesPerLine + x * 2;
                for (int j = 0; j < putWidth; ++j) {
                    final int pixel = pixels[i];
                    this.data[index] = (byte)(pixel & 0xFF);
                    this.data[index + 1] = (byte)(pixel >> 8 & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        index += 2;
                    }
                }
            }
            case 8: {
                int index = y * this.bytesPerLine + x;
                for (int j = 0; j < putWidth; ++j) {
                    this.data[index] = (byte)(pixels[i] & 0xFF);
                    ++i;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        ++index;
                    }
                }
            }
            case 4: {
                int index = y * this.bytesPerLine + (x >> 1);
                boolean high = (x & 0x1) == 0x0;
                while (n > 0) {
                    final int theByte = pixels[i] & 0xF;
                    if (high) {
                        this.data[index] = (byte)((this.data[index] & 0xF) | theByte << 4);
                    }
                    else {
                        this.data[index] = (byte)((this.data[index] & 0xF0) | theByte);
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        high = true;
                        srcX = 0;
                    }
                    else {
                        if (!high) {
                            ++index;
                        }
                        high = !high;
                    }
                }
            }
            case 2: {
                final byte[] masks = { -4, -13, -49, 63 };
                int index2 = y * this.bytesPerLine + (x >> 2);
                int offset = 3 - x % 4;
                while (n > 0) {
                    final int theByte2 = pixels[i] & 0x3;
                    this.data[index2] = (byte)((this.data[index2] & masks[offset]) | theByte2 << offset * 2);
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index2 = ++srcY * this.bytesPerLine;
                        offset = 3;
                        srcX = 0;
                    }
                    else if (offset == 0) {
                        ++index2;
                        offset = 3;
                    }
                    else {
                        --offset;
                    }
                }
            }
            case 1: {
                int index = y * this.bytesPerLine + (x >> 3);
                while (n > 0) {
                    final int mask = 1 << 7 - (srcX & 0x7);
                    if ((pixels[i] & 0x1) == 0x1) {
                        this.data[index] = (byte)((this.data[index] & 0xFF) | mask);
                    }
                    else {
                        this.data[index] = (byte)(this.data[index] & 0xFF & ~mask);
                    }
                    ++i;
                    --n;
                    if (++srcX >= this.width) {
                        index = ++srcY * this.bytesPerLine;
                        srcX = 0;
                    }
                    else {
                        if (mask != 1) {
                            continue;
                        }
                        ++index;
                    }
                }
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    static PaletteData bwPalette() {
        return new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) });
    }
    
    static int getMSBOffset(final int mask) {
        for (int i = 31; i >= 0; --i) {
            if ((mask >> i & 0x1) != 0x0) {
                return i + 1;
            }
        }
        return 0;
    }
    
    static int closestMatch(final int depth, final byte red, final byte green, final byte blue, final int redMask, final int greenMask, final int blueMask, final byte[] reds, final byte[] greens, final byte[] blues) {
        if (depth > 8) {
            final int rshift = 32 - getMSBOffset(redMask);
            final int gshift = 32 - getMSBOffset(greenMask);
            final int bshift = 32 - getMSBOffset(blueMask);
            return (red << 24 >>> rshift & redMask) | (green << 24 >>> gshift & greenMask) | (blue << 24 >>> bshift & blueMask);
        }
        int minDistance = Integer.MAX_VALUE;
        int nearestPixel = 0;
        for (int n = reds.length, j = 0; j < n; ++j) {
            final int r = (reds[j] & 0xFF) - (red & 0xFF);
            final int g = (greens[j] & 0xFF) - (green & 0xFF);
            final int b = (blues[j] & 0xFF) - (blue & 0xFF);
            final int distance = r * r + g * g + b * b;
            if (distance < minDistance) {
                nearestPixel = j;
                if (distance == 0) {
                    break;
                }
                minDistance = distance;
            }
        }
        return nearestPixel;
    }
    
    static ImageData convertMask(final ImageData mask) {
        if (mask.depth == 1) {
            return mask;
        }
        final PaletteData palette = new PaletteData(new RGB[] { new RGB(0, 0, 0), new RGB(255, 255, 255) });
        final ImageData newMask = new ImageData(mask.width, mask.height, 1, palette);
        int blackIndex = 0;
        final RGB[] rgbs = mask.getRGBs();
        if (rgbs != null) {
            while (blackIndex < rgbs.length) {
                if (rgbs[blackIndex].equals(palette.colors[0])) {
                    break;
                }
                ++blackIndex;
            }
        }
        final int[] pixels = new int[mask.width];
        for (int y = 0; y < mask.height; ++y) {
            mask.getPixels(0, y, mask.width, pixels, 0);
            for (int i = 0; i < pixels.length; ++i) {
                if (pixels[i] == blackIndex) {
                    pixels[i] = 0;
                }
                else {
                    pixels[i] = 1;
                }
            }
            newMask.setPixels(0, y, mask.width, pixels, 0);
        }
        return newMask;
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
            System.arraycopy(data, srcIndex, newData, destIndex, stride);
            srcIndex += bpl;
            destIndex += newBpl;
        }
        return newData;
    }
    
    static void blit(final int op, final byte[] srcData, final int srcDepth, final int srcStride, final int srcOrder, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int srcRedMask, final int srcGreenMask, final int srcBlueMask, int alphaMode, final byte[] alphaData, int alphaStride, final int alphaX, final int alphaY, final byte[] destData, final int destDepth, final int destStride, final int destOrder, final int destX, final int destY, final int destWidth, final int destHeight, final int destRedMask, final int destGreenMask, final int destBlueMask, final boolean flipX, final boolean flipY) {
        if (destWidth <= 0 || destHeight <= 0 || alphaMode == 0) {
            return;
        }
        final int srcAlphaMask = 0;
        final int destAlphaMask = 0;
        final int dwm1 = destWidth - 1;
        final int sfxi = (dwm1 != 0) ? ((int)((((long)srcWidth << 16) - 1L) / dwm1)) : 0;
        final int dhm1 = destHeight - 1;
        final int sfyi = (dhm1 != 0) ? ((int)((((long)srcHeight << 16) - 1L) / dhm1)) : 0;
        int sbpp = 0;
        int stype = 0;
        switch (srcDepth) {
            case 8: {
                sbpp = 1;
                stype = 0;
                break;
            }
            case 16: {
                sbpp = 2;
                stype = ((srcOrder == 1) ? 1 : 2);
                break;
            }
            case 24: {
                sbpp = 3;
                stype = 3;
                break;
            }
            case 32: {
                sbpp = 4;
                stype = ((srcOrder == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int spr = srcY * srcStride + srcX * sbpp;
        int dbpp = 0;
        int dtype = 0;
        switch (destDepth) {
            case 8: {
                dbpp = 1;
                dtype = 0;
                break;
            }
            case 16: {
                dbpp = 2;
                dtype = ((destOrder == 1) ? 1 : 2);
                break;
            }
            case 24: {
                dbpp = 3;
                dtype = 3;
                break;
            }
            case 32: {
                dbpp = 4;
                dtype = ((destOrder == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int dpr = (flipY ? (destY + dhm1) : destY) * destStride + (flipX ? (destX + dwm1) : destX) * dbpp;
        final int dprxi = flipX ? (-dbpp) : dbpp;
        final int dpryi = flipY ? (-destStride) : destStride;
        int apr = 0;
        if ((op & 0x2) != 0x0) {
            switch (alphaMode) {
                case -3:
                case -1: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -4: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    alphaStride <<= 3;
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -5: {
                    return;
                }
                case -6: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = 0;
                    break;
                }
                default: {
                    alphaMode = (alphaMode << 16) / 255;
                }
                case -2: {
                    apr = 0;
                    break;
                }
            }
        }
        else {
            alphaMode = 65536;
            apr = 0;
        }
        int dp = dpr;
        int sp = spr;
        if (alphaMode == 65536 && stype == dtype && srcRedMask == destRedMask && srcGreenMask == destGreenMask && srcBlueMask == destBlueMask) {
            switch (sbpp) {
                case 1: {
                    for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                        for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                            destData[dp] = srcData[sp];
                            sp += sfx >>> 16;
                        }
                    }
                    break;
                }
                case 2: {
                    for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                        for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                            destData[dp] = srcData[sp];
                            destData[dp + 1] = srcData[sp + 1];
                            sp += (sfx >>> 16) * 2;
                        }
                    }
                    break;
                }
                case 3: {
                    for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                        for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                            destData[dp] = srcData[sp];
                            destData[dp + 1] = srcData[sp + 1];
                            destData[dp + 2] = srcData[sp + 2];
                            sp += (sfx >>> 16) * 3;
                        }
                    }
                    break;
                }
                case 4: {
                    for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                        for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                            destData[dp] = srcData[sp];
                            destData[dp + 1] = srcData[sp + 1];
                            destData[dp + 2] = srcData[sp + 2];
                            destData[dp + 3] = srcData[sp + 3];
                            sp += (sfx >>> 16) * 4;
                        }
                    }
                    break;
                }
            }
            return;
        }
        if (alphaMode == 65536 && stype == 4 && dtype == 4 && srcRedMask == 65280 && srcGreenMask == 16711680 && srcBlueMask == -16777216 && destRedMask == 16711680 && destGreenMask == 65280 && destBlueMask == 255) {
            for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                    destData[dp] = srcData[sp + 3];
                    destData[dp + 1] = srcData[sp + 2];
                    destData[dp + 2] = srcData[sp + 1];
                    destData[dp + 3] = srcData[sp];
                    sp += (sfx >>> 16) * 4;
                }
            }
            return;
        }
        if (alphaMode == 65536 && stype == 3 && dtype == 4 && srcRedMask == 255 && srcGreenMask == 65280 && srcBlueMask == 16711680 && destRedMask == 16711680 && destGreenMask == 65280 && destBlueMask == 255) {
            for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                    destData[dp] = 0;
                    destData[dp + 1] = srcData[sp + 2];
                    destData[dp + 2] = srcData[sp + 1];
                    destData[dp + 3] = srcData[sp];
                    sp += (sfx >>> 16) * 3;
                }
            }
            return;
        }
        final int srcRedShift = getChannelShift(srcRedMask);
        final byte[] srcReds = ImageData.ANY_TO_EIGHT[getChannelWidth(srcRedMask, srcRedShift)];
        final int srcGreenShift = getChannelShift(srcGreenMask);
        final byte[] srcGreens = ImageData.ANY_TO_EIGHT[getChannelWidth(srcGreenMask, srcGreenShift)];
        final int srcBlueShift = getChannelShift(srcBlueMask);
        final byte[] srcBlues = ImageData.ANY_TO_EIGHT[getChannelWidth(srcBlueMask, srcBlueShift)];
        final int srcAlphaShift = getChannelShift(0);
        final byte[] srcAlphas = ImageData.ANY_TO_EIGHT[getChannelWidth(0, srcAlphaShift)];
        final int destRedShift = getChannelShift(destRedMask);
        final int destRedWidth = getChannelWidth(destRedMask, destRedShift);
        final byte[] destReds = ImageData.ANY_TO_EIGHT[destRedWidth];
        final int destRedPreShift = 8 - destRedWidth;
        final int destGreenShift = getChannelShift(destGreenMask);
        final int destGreenWidth = getChannelWidth(destGreenMask, destGreenShift);
        final byte[] destGreens = ImageData.ANY_TO_EIGHT[destGreenWidth];
        final int destGreenPreShift = 8 - destGreenWidth;
        final int destBlueShift = getChannelShift(destBlueMask);
        final int destBlueWidth = getChannelWidth(destBlueMask, destBlueShift);
        final byte[] destBlues = ImageData.ANY_TO_EIGHT[destBlueWidth];
        final int destBluePreShift = 8 - destBlueWidth;
        final int destAlphaShift = getChannelShift(0);
        final int destAlphaWidth = getChannelWidth(0, destAlphaShift);
        final byte[] destAlphas = ImageData.ANY_TO_EIGHT[destAlphaWidth];
        final int destAlphaPreShift = 8 - destAlphaWidth;
        int ap = apr;
        int alpha = alphaMode;
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int rq = 0;
        int gq = 0;
        int bq = 0;
        int aq = 0;
        for (int dy2 = destHeight, sfy2 = sfyi; dy2 > 0; --dy2, sp = (spr += (sfy2 >>> 16) * srcStride), ap = (apr += (sfy2 >>> 16) * alphaStride), sfy2 = (sfy2 & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
            for (int dx2 = destWidth, sfx2 = sfxi; dx2 > 0; --dx2, dp += dprxi, sfx2 = (sfx2 & 0xFFFF) + sfxi) {
                switch (stype) {
                    case 0: {
                        final int data = srcData[sp] & 0xFF;
                        sp += sfx2 >>> 16;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 1: {
                        final int data = (srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF);
                        sp += (sfx2 >>> 16) * 2;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 2: {
                        final int data = (srcData[sp + 1] & 0xFF) << 8 | (srcData[sp] & 0xFF);
                        sp += (sfx2 >>> 16) * 2;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 3: {
                        final int data = ((srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp + 2] & 0xFF);
                        sp += (sfx2 >>> 16) * 3;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 4: {
                        final int data = (((srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp + 2] & 0xFF)) << 8 | (srcData[sp + 3] & 0xFF);
                        sp += (sfx2 >>> 16) * 4;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 5: {
                        final int data = (((srcData[sp + 3] & 0xFF) << 8 | (srcData[sp + 2] & 0xFF)) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp] & 0xFF);
                        sp += (sfx2 >>> 16) * 4;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                }
                switch (alphaMode) {
                    case -1: {
                        alpha = ((alphaData[ap] & 0xFF) << 16) / 255;
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -2: {
                        alpha = (a << 16) / 255;
                        break;
                    }
                    case -3: {
                        alpha = ((alphaData[ap] != 0) ? 65536 : 0);
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -4: {
                        alpha = (alphaData[ap >> 3] << (ap & 0x7) + 9 & 0x10000);
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -6: {
                        alpha = 65536;
                        for (int i = 0; i < alphaData.length; i += 3) {
                            if (r == alphaData[i] && g == alphaData[i + 1] && b == alphaData[i + 2]) {
                                alpha = 0;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (alpha != 65536) {
                    if (alpha == 0) {
                        continue;
                    }
                    switch (dtype) {
                        case 0: {
                            final int data = destData[dp] & 0xFF;
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 1: {
                            final int data = (destData[dp] & 0xFF) << 8 | (destData[dp + 1] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 2: {
                            final int data = (destData[dp + 1] & 0xFF) << 8 | (destData[dp] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 3: {
                            final int data = ((destData[dp] & 0xFF) << 8 | (destData[dp + 1] & 0xFF)) << 8 | (destData[dp + 2] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 4: {
                            final int data = (((destData[dp] & 0xFF) << 8 | (destData[dp + 1] & 0xFF)) << 8 | (destData[dp + 2] & 0xFF)) << 8 | (destData[dp + 3] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 5: {
                            final int data = (((destData[dp + 3] & 0xFF) << 8 | (destData[dp + 2] & 0xFF)) << 8 | (destData[dp + 1] & 0xFF)) << 8 | (destData[dp] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                    }
                    a = aq + ((a - aq) * alpha >> 16);
                    r = rq + ((r - rq) * alpha >> 16);
                    g = gq + ((g - gq) * alpha >> 16);
                    b = bq + ((b - bq) * alpha >> 16);
                }
                final int data = r >>> destRedPreShift << destRedShift | g >>> destGreenPreShift << destGreenShift | b >>> destBluePreShift << destBlueShift | a >>> destAlphaPreShift << destAlphaShift;
                switch (dtype) {
                    case 0: {
                        destData[dp] = (byte)data;
                        break;
                    }
                    case 1: {
                        destData[dp] = (byte)(data >>> 8);
                        destData[dp + 1] = (byte)(data & 0xFF);
                        break;
                    }
                    case 2: {
                        destData[dp] = (byte)(data & 0xFF);
                        destData[dp + 1] = (byte)(data >>> 8);
                        break;
                    }
                    case 3: {
                        destData[dp] = (byte)(data >>> 16);
                        destData[dp + 1] = (byte)(data >>> 8);
                        destData[dp + 2] = (byte)(data & 0xFF);
                        break;
                    }
                    case 4: {
                        destData[dp] = (byte)(data >>> 24);
                        destData[dp + 1] = (byte)(data >>> 16);
                        destData[dp + 2] = (byte)(data >>> 8);
                        destData[dp + 3] = (byte)(data & 0xFF);
                        break;
                    }
                    case 5: {
                        destData[dp] = (byte)(data & 0xFF);
                        destData[dp + 1] = (byte)(data >>> 8);
                        destData[dp + 2] = (byte)(data >>> 16);
                        destData[dp + 3] = (byte)(data >>> 24);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int op, final byte[] srcData, final int srcDepth, int srcStride, final int srcOrder, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final byte[] srcReds, final byte[] srcGreens, final byte[] srcBlues, int alphaMode, final byte[] alphaData, int alphaStride, final int alphaX, final int alphaY, final byte[] destData, final int destDepth, int destStride, final int destOrder, final int destX, final int destY, final int destWidth, final int destHeight, final byte[] destReds, final byte[] destGreens, final byte[] destBlues, final boolean flipX, final boolean flipY) {
        if (destWidth <= 0 || destHeight <= 0 || alphaMode == 0) {
            return;
        }
        final int dwm1 = destWidth - 1;
        final int sfxi = (dwm1 != 0) ? ((int)((((long)srcWidth << 16) - 1L) / dwm1)) : 0;
        final int dhm1 = destHeight - 1;
        final int sfyi = (dhm1 != 0) ? ((int)((((long)srcHeight << 16) - 1L) / dhm1)) : 0;
        int stype = 0;
        switch (srcDepth) {
            case 8: {
                stype = 6;
                break;
            }
            case 4: {
                srcStride <<= 1;
                stype = 7;
                break;
            }
            case 2: {
                srcStride <<= 2;
                stype = 8;
                break;
            }
            case 1: {
                srcStride <<= 3;
                stype = ((srcOrder == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int spr = srcY * srcStride + srcX;
        int dtype = 0;
        switch (destDepth) {
            case 8: {
                dtype = 6;
                break;
            }
            case 4: {
                destStride <<= 1;
                dtype = 7;
                break;
            }
            case 2: {
                destStride <<= 2;
                dtype = 8;
                break;
            }
            case 1: {
                destStride <<= 3;
                dtype = ((destOrder == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int dpr = (flipY ? (destY + dhm1) : destY) * destStride + (flipX ? (destX + dwm1) : destX);
        final int dprxi = flipX ? -1 : 1;
        final int dpryi = flipY ? (-destStride) : destStride;
        int apr = 0;
        if ((op & 0x2) != 0x0) {
            switch (alphaMode) {
                case -3:
                case -1: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -4: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    alphaStride <<= 3;
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -6:
                case -5: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = 0;
                    break;
                }
                default: {
                    alphaMode = (alphaMode << 16) / 255;
                }
                case -2: {
                    apr = 0;
                    break;
                }
            }
        }
        else {
            alphaMode = 65536;
            apr = 0;
        }
        final boolean ditherEnabled = (op & 0x4) != 0x0;
        int dp = dpr;
        int sp = spr;
        int ap = apr;
        int destPaletteSize = 1 << destDepth;
        if (destReds != null && destReds.length < destPaletteSize) {
            destPaletteSize = destReds.length;
        }
        byte[] paletteMapping = null;
        boolean isExactPaletteMapping = true;
        switch (alphaMode) {
            case 65536: {
                if (stype == dtype && srcReds == destReds && srcGreens == destGreens && srcBlues == destBlues) {
                    paletteMapping = ImageData.ONE_TO_ONE_MAPPING;
                    break;
                }
                if (srcReds != null && destReds != null && srcDepth <= destDepth) {
                    paletteMapping = ImageData.ONE_TO_ONE_MAPPING;
                    break;
                }
                paletteMapping = new byte[1 << srcDepth];
                final int mask = 255 << destDepth >>> 8;
                for (int i = 0; i < paletteMapping.length; ++i) {
                    paletteMapping[i] = (byte)(i & mask);
                }
                break;
            }
            case -6:
            case -5:
            case -4:
            case -3: {
                int srcPaletteSize = 1 << srcDepth;
                paletteMapping = new byte[srcPaletteSize];
                if (srcReds != null && srcReds.length < srcPaletteSize) {
                    srcPaletteSize = srcReds.length;
                }
                for (int i = 0; i < srcPaletteSize; ++i) {
                    final int r = srcReds[i] & 0xFF;
                    final int g = srcGreens[i] & 0xFF;
                    final int b = srcBlues[i] & 0xFF;
                    int index = 0;
                    int minDistance = Integer.MAX_VALUE;
                    for (int j = 0; j < destPaletteSize; ++j) {
                        final int dr = (destReds[j] & 0xFF) - r;
                        final int dg = (destGreens[j] & 0xFF) - g;
                        final int db = (destBlues[j] & 0xFF) - b;
                        final int distance = dr * dr + dg * dg + db * db;
                        if (distance < minDistance) {
                            index = j;
                            if (distance == 0) {
                                break;
                            }
                            minDistance = distance;
                        }
                    }
                    paletteMapping[i] = (byte)index;
                    if (minDistance != 0) {
                        isExactPaletteMapping = false;
                    }
                }
                break;
            }
        }
        if (paletteMapping != null && (isExactPaletteMapping || !ditherEnabled)) {
            if (stype == dtype && alphaMode == 65536) {
                switch (stype) {
                    case 6: {
                        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                                destData[dp] = paletteMapping[srcData[sp] & 0xFF];
                                sp += sfx >>> 16;
                            }
                        }
                        break;
                    }
                    case 7: {
                        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                                int v;
                                if ((sp & 0x1) != 0x0) {
                                    v = paletteMapping[srcData[sp >> 1] & 0xF];
                                }
                                else {
                                    v = (srcData[sp >> 1] >>> 4 & 0xF);
                                }
                                sp += sfx >>> 16;
                                if ((dp & 0x1) != 0x0) {
                                    destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF0) | v);
                                }
                                else {
                                    destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF) | v << 4);
                                }
                            }
                        }
                        break;
                    }
                    case 8: {
                        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                                final int index2 = paletteMapping[srcData[sp >> 2] >>> 6 - (sp & 0x3) * 2 & 0x3];
                                sp += sfx >>> 16;
                                final int shift = 6 - (dp & 0x3) * 2;
                                destData[dp >> 2] = (byte)((destData[dp >> 2] & ~(3 << shift)) | index2 << shift);
                            }
                        }
                        break;
                    }
                    case 9: {
                        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                                final int index2 = paletteMapping[srcData[sp >> 3] >>> 7 - (sp & 0x7) & 0x1];
                                sp += sfx >>> 16;
                                final int shift = 7 - (dp & 0x7);
                                destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | index2 << shift);
                            }
                        }
                        break;
                    }
                    case 10: {
                        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                                final int index2 = paletteMapping[srcData[sp >> 3] >>> (sp & 0x7) & 0x1];
                                sp += sfx >>> 16;
                                final int shift = dp & 0x7;
                                destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | index2 << shift);
                            }
                        }
                        break;
                    }
                }
            }
            else {
                for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
                    for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                        int index2 = 0;
                        switch (stype) {
                            case 6: {
                                index2 = (srcData[sp] & 0xFF);
                                sp += sfx >>> 16;
                                break;
                            }
                            case 7: {
                                if ((sp & 0x1) != 0x0) {
                                    index2 = (srcData[sp >> 1] & 0xF);
                                }
                                else {
                                    index2 = (srcData[sp >> 1] >>> 4 & 0xF);
                                }
                                sp += sfx >>> 16;
                                break;
                            }
                            case 8: {
                                index2 = (srcData[sp >> 2] >>> 6 - (sp & 0x3) * 2 & 0x3);
                                sp += sfx >>> 16;
                                break;
                            }
                            case 9: {
                                index2 = (srcData[sp >> 3] >>> 7 - (sp & 0x7) & 0x1);
                                sp += sfx >>> 16;
                                break;
                            }
                            case 10: {
                                index2 = (srcData[sp >> 3] >>> (sp & 0x7) & 0x1);
                                sp += sfx >>> 16;
                                break;
                            }
                            default: {
                                return;
                            }
                        }
                        switch (alphaMode) {
                            case -3: {
                                final byte mask2 = alphaData[ap];
                                ap += sfx >> 16;
                                if (mask2 == 0) {
                                    continue;
                                }
                                break;
                            }
                            case -4: {
                                final int mask3 = alphaData[ap >> 3] & 1 << (ap & 0x7);
                                ap += sfx >> 16;
                                if (mask3 == 0) {
                                    continue;
                                }
                                break;
                            }
                            case -5: {
                                final int k = 0;
                                while (0 < alphaData.length && index2 != (alphaData[0] & 0xFF)) {}
                                if (0 < alphaData.length) {
                                    continue;
                                }
                                break;
                            }
                            case -6: {
                                byte r2;
                                byte g2;
                                byte b2;
                                int l;
                                for (r2 = srcReds[index2], g2 = srcGreens[index2], b2 = srcBlues[index2], l = 0; l < alphaData.length && (r2 != alphaData[l] || g2 != alphaData[l + 1] || b2 != alphaData[l + 2]); l += 3) {}
                                if (l < alphaData.length) {
                                    continue;
                                }
                                break;
                            }
                        }
                        index2 = (paletteMapping[index2] & 0xFF);
                        switch (dtype) {
                            case 6: {
                                destData[dp] = (byte)index2;
                                break;
                            }
                            case 7: {
                                if ((dp & 0x1) != 0x0) {
                                    destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF0) | index2);
                                    break;
                                }
                                destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF) | index2 << 4);
                                break;
                            }
                            case 8: {
                                final int shift = 6 - (dp & 0x3) * 2;
                                destData[dp >> 2] = (byte)((destData[dp >> 2] & ~(3 << shift)) | index2 << shift);
                                break;
                            }
                            case 9: {
                                final int shift = 7 - (dp & 0x7);
                                destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | index2 << shift);
                                break;
                            }
                            case 10: {
                                final int shift = dp & 0x7;
                                destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | index2 << shift);
                                break;
                            }
                        }
                    }
                }
            }
            return;
        }
        int alpha = alphaMode;
        int index3 = 0;
        int indexq = 0;
        int lastindex = 0;
        int lastr = -1;
        int lastg = -1;
        int lastb = -1;
        int[] rerr;
        int[] gerr;
        int[] berr;
        if (ditherEnabled) {
            rerr = new int[destWidth + 2];
            gerr = new int[destWidth + 2];
            berr = new int[destWidth + 2];
        }
        else {
            rerr = null;
            gerr = null;
            berr = null;
        }
        for (int dy2 = destHeight, sfy2 = sfyi; dy2 > 0; --dy2, sp = (spr += (sfy2 >>> 16) * srcStride), ap = (apr += (sfy2 >>> 16) * alphaStride), sfy2 = (sfy2 & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
            int lrerr = 0;
            int lgerr = 0;
            int lberr = 0;
            for (int dx2 = destWidth, sfx2 = sfxi; dx2 > 0; --dx2, dp += dprxi, sfx2 = (sfx2 & 0xFFFF) + sfxi) {
                switch (stype) {
                    case 6: {
                        index3 = (srcData[sp] & 0xFF);
                        sp += sfx2 >>> 16;
                        break;
                    }
                    case 7: {
                        if ((sp & 0x1) != 0x0) {
                            index3 = (srcData[sp >> 1] & 0xF);
                        }
                        else {
                            index3 = (srcData[sp >> 1] >>> 4 & 0xF);
                        }
                        sp += sfx2 >>> 16;
                        break;
                    }
                    case 8: {
                        index3 = (srcData[sp >> 2] >>> 6 - (sp & 0x3) * 2 & 0x3);
                        sp += sfx2 >>> 16;
                        break;
                    }
                    case 9: {
                        index3 = (srcData[sp >> 3] >>> 7 - (sp & 0x7) & 0x1);
                        sp += sfx2 >>> 16;
                        break;
                    }
                    case 10: {
                        index3 = (srcData[sp >> 3] >>> (sp & 0x7) & 0x1);
                        sp += sfx2 >>> 16;
                        break;
                    }
                }
                int r3 = srcReds[index3] & 0xFF;
                int g3 = srcGreens[index3] & 0xFF;
                int b3 = srcBlues[index3] & 0xFF;
                switch (alphaMode) {
                    case -1: {
                        alpha = ((alphaData[ap] & 0xFF) << 16) / 255;
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -3: {
                        alpha = ((alphaData[ap] != 0) ? 65536 : 0);
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -4: {
                        alpha = (alphaData[ap >> 3] << (ap & 0x7) + 9 & 0x10000);
                        ap += sfx2 >> 16;
                        break;
                    }
                    case -5: {
                        final int m = 0;
                        while (0 < alphaData.length && index3 != (alphaData[0] & 0xFF)) {}
                        if (0 < alphaData.length) {
                            continue;
                        }
                        break;
                    }
                    case -6: {
                        int m;
                        for (m = 0; m < alphaData.length && (r3 != (alphaData[m] & 0xFF) || g3 != (alphaData[m + 1] & 0xFF) || b3 != (alphaData[m + 2] & 0xFF)); m += 3) {}
                        if (m < alphaData.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (alpha != 65536) {
                    if (alpha == 0) {
                        continue;
                    }
                    switch (dtype) {
                        case 6: {
                            indexq = (destData[dp] & 0xFF);
                            break;
                        }
                        case 7: {
                            if ((dp & 0x1) != 0x0) {
                                indexq = (destData[dp >> 1] & 0xF);
                                break;
                            }
                            indexq = (destData[dp >> 1] >>> 4 & 0xF);
                            break;
                        }
                        case 8: {
                            indexq = (destData[dp >> 2] >>> 6 - (dp & 0x3) * 2 & 0x3);
                            break;
                        }
                        case 9: {
                            indexq = (destData[dp >> 3] >>> 7 - (dp & 0x7) & 0x1);
                            break;
                        }
                        case 10: {
                            indexq = (destData[dp >> 3] >>> (dp & 0x7) & 0x1);
                            break;
                        }
                    }
                    final int rq = destReds[indexq] & 0xFF;
                    final int gq = destGreens[indexq] & 0xFF;
                    final int bq = destBlues[indexq] & 0xFF;
                    r3 = rq + ((r3 - rq) * alpha >> 16);
                    g3 = gq + ((g3 - gq) * alpha >> 16);
                    b3 = bq + ((b3 - bq) * alpha >> 16);
                }
                if (ditherEnabled) {
                    r3 += rerr[dx2] >> 4;
                    if (r3 < 0) {
                        r3 = 0;
                    }
                    else if (r3 > 255) {
                        r3 = 255;
                    }
                    g3 += gerr[dx2] >> 4;
                    if (g3 < 0) {
                        g3 = 0;
                    }
                    else if (g3 > 255) {
                        g3 = 255;
                    }
                    b3 += berr[dx2] >> 4;
                    if (b3 < 0) {
                        b3 = 0;
                    }
                    else if (b3 > 255) {
                        b3 = 255;
                    }
                    rerr[dx2] = lrerr;
                    gerr[dx2] = lgerr;
                    berr[dx2] = lberr;
                }
                if (r3 != lastr || g3 != lastg || b3 != lastb) {
                    int j2 = 0;
                    int minDistance2 = Integer.MAX_VALUE;
                    while (j2 < destPaletteSize) {
                        final int dr2 = (destReds[j2] & 0xFF) - r3;
                        final int dg2 = (destGreens[j2] & 0xFF) - g3;
                        final int db2 = (destBlues[j2] & 0xFF) - b3;
                        final int distance2 = dr2 * dr2 + dg2 * dg2 + db2 * db2;
                        if (distance2 < minDistance2) {
                            lastindex = j2;
                            if (distance2 == 0) {
                                break;
                            }
                            minDistance2 = distance2;
                        }
                        ++j2;
                    }
                    lastr = r3;
                    lastg = g3;
                    lastb = b3;
                }
                if (ditherEnabled) {
                    final int dxm1 = dx2 - 1;
                    final int dxp1 = dx2 + 1;
                    final int[] array = rerr;
                    final int n = dxp1;
                    final int[] array10 = array;
                    final int n10 = n;
                    int acc;
                    array10[n10] += (acc = (lrerr = r3 - (destReds[lastindex] & 0xFF)) + lrerr + lrerr);
                    final int[] array2 = rerr;
                    final int n2 = dx2;
                    final int[] array11 = array2;
                    final int n11 = n2;
                    array11[n11] += (acc += lrerr + lrerr);
                    final int[] array3 = rerr;
                    final int n3 = dxm1;
                    final int[] array12 = array3;
                    final int n12 = n3;
                    array12[n12] += acc + lrerr + lrerr;
                    final int[] array4 = gerr;
                    final int n4 = dxp1;
                    final int[] array13 = array4;
                    final int n13 = n4;
                    array13[n13] += (acc = (lgerr = g3 - (destGreens[lastindex] & 0xFF)) + lgerr + lgerr);
                    final int[] array5 = gerr;
                    final int n5 = dx2;
                    final int[] array14 = array5;
                    final int n14 = n5;
                    array14[n14] += (acc += lgerr + lgerr);
                    final int[] array6 = gerr;
                    final int n6 = dxm1;
                    final int[] array15 = array6;
                    final int n15 = n6;
                    array15[n15] += acc + lgerr + lgerr;
                    final int[] array7 = berr;
                    final int n7 = dxp1;
                    final int[] array16 = array7;
                    final int n16 = n7;
                    array16[n16] += (acc = (lberr = b3 - (destBlues[lastindex] & 0xFF)) + lberr + lberr);
                    final int[] array8 = berr;
                    final int n8 = dx2;
                    final int[] array17 = array8;
                    final int n17 = n8;
                    array17[n17] += (acc += lberr + lberr);
                    final int[] array9 = berr;
                    final int n9 = dxm1;
                    final int[] array18 = array9;
                    final int n18 = n9;
                    array18[n18] += acc + lberr + lberr;
                }
                switch (dtype) {
                    case 6: {
                        destData[dp] = (byte)lastindex;
                        break;
                    }
                    case 7: {
                        if ((dp & 0x1) != 0x0) {
                            destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF0) | lastindex);
                            break;
                        }
                        destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF) | lastindex << 4);
                        break;
                    }
                    case 8: {
                        final int shift2 = 6 - (dp & 0x3) * 2;
                        destData[dp >> 2] = (byte)((destData[dp >> 2] & ~(3 << shift2)) | lastindex << shift2);
                        break;
                    }
                    case 9: {
                        final int shift2 = 7 - (dp & 0x7);
                        destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift2)) | lastindex << shift2);
                        break;
                    }
                    case 10: {
                        final int shift2 = dp & 0x7;
                        destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift2)) | lastindex << shift2);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int op, final byte[] srcData, final int srcDepth, int srcStride, final int srcOrder, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final byte[] srcReds, final byte[] srcGreens, final byte[] srcBlues, int alphaMode, final byte[] alphaData, int alphaStride, final int alphaX, final int alphaY, final byte[] destData, final int destDepth, final int destStride, final int destOrder, final int destX, final int destY, final int destWidth, final int destHeight, final int destRedMask, final int destGreenMask, final int destBlueMask, final boolean flipX, final boolean flipY) {
        if (destWidth <= 0 || destHeight <= 0 || alphaMode == 0) {
            return;
        }
        if (srcX == 0 && srcY == 0 && destX == 0 && destY == 0 && destWidth == srcWidth && destHeight == srcHeight) {
            if (destDepth == 24 && srcDepth == 8 && (op & 0x2) == 0x0 && destRedMask == 16711680 && destGreenMask == 65280 && destBlueMask == 255) {
                for (int y = 0, sp = 0, dp = 0, spad = srcStride - srcWidth, dpad = destStride - destWidth * 3; y < destHeight; ++y, sp += spad, dp += dpad) {
                    for (int x = 0; x < destWidth; ++x) {
                        final int index = srcData[sp++] & 0xFF;
                        destData[dp++] = srcReds[index];
                        destData[dp++] = srcGreens[index];
                        destData[dp++] = srcBlues[index];
                    }
                }
                return;
            }
            if (destDepth == 32 && destOrder == 1 && srcDepth == 8 && (op & 0x2) == 0x0 && destRedMask == 16711680 && destGreenMask == 65280 && destBlueMask == 255) {
                for (int y = 0, sp = 0, dp = 0, spad = srcStride - srcWidth, dpad = destStride - destWidth * 4; y < destHeight; ++y, sp += spad, dp += dpad) {
                    for (int x = 0; x < destWidth; ++x) {
                        final int index = srcData[sp++] & 0xFF;
                        ++dp;
                        destData[dp++] = srcReds[index];
                        destData[dp++] = srcGreens[index];
                        destData[dp++] = srcBlues[index];
                    }
                }
                return;
            }
        }
        final int destAlphaMask = 0;
        final int dwm1 = destWidth - 1;
        final int sfxi = (dwm1 != 0) ? ((int)((((long)srcWidth << 16) - 1L) / dwm1)) : 0;
        final int dhm1 = destHeight - 1;
        final int sfyi = (dhm1 != 0) ? ((int)((((long)srcHeight << 16) - 1L) / dhm1)) : 0;
        int stype = 0;
        switch (srcDepth) {
            case 8: {
                stype = 6;
                break;
            }
            case 4: {
                srcStride <<= 1;
                stype = 7;
                break;
            }
            case 2: {
                srcStride <<= 2;
                stype = 8;
                break;
            }
            case 1: {
                srcStride <<= 3;
                stype = ((srcOrder == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int spr = srcY * srcStride + srcX;
        int dbpp = 0;
        int dtype = 0;
        switch (destDepth) {
            case 8: {
                dbpp = 1;
                dtype = 0;
                break;
            }
            case 16: {
                dbpp = 2;
                dtype = ((destOrder == 1) ? 1 : 2);
                break;
            }
            case 24: {
                dbpp = 3;
                dtype = 3;
                break;
            }
            case 32: {
                dbpp = 4;
                dtype = ((destOrder == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int dpr = (flipY ? (destY + dhm1) : destY) * destStride + (flipX ? (destX + dwm1) : destX) * dbpp;
        final int dprxi = flipX ? (-dbpp) : dbpp;
        final int dpryi = flipY ? (-destStride) : destStride;
        int apr = 0;
        if ((op & 0x2) != 0x0) {
            switch (alphaMode) {
                case -3:
                case -1: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -4: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    alphaStride <<= 3;
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -6:
                case -5: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = 0;
                    break;
                }
                default: {
                    alphaMode = (alphaMode << 16) / 255;
                }
                case -2: {
                    apr = 0;
                    break;
                }
            }
        }
        else {
            alphaMode = 65536;
            apr = 0;
        }
        final int destRedShift = getChannelShift(destRedMask);
        final int destRedWidth = getChannelWidth(destRedMask, destRedShift);
        final byte[] destReds = ImageData.ANY_TO_EIGHT[destRedWidth];
        final int destRedPreShift = 8 - destRedWidth;
        final int destGreenShift = getChannelShift(destGreenMask);
        final int destGreenWidth = getChannelWidth(destGreenMask, destGreenShift);
        final byte[] destGreens = ImageData.ANY_TO_EIGHT[destGreenWidth];
        final int destGreenPreShift = 8 - destGreenWidth;
        final int destBlueShift = getChannelShift(destBlueMask);
        final int destBlueWidth = getChannelWidth(destBlueMask, destBlueShift);
        final byte[] destBlues = ImageData.ANY_TO_EIGHT[destBlueWidth];
        final int destBluePreShift = 8 - destBlueWidth;
        final int destAlphaShift = getChannelShift(0);
        final int destAlphaWidth = getChannelWidth(0, destAlphaShift);
        final byte[] destAlphas = ImageData.ANY_TO_EIGHT[destAlphaWidth];
        final int destAlphaPreShift = 8 - destAlphaWidth;
        int dp2 = dpr;
        int sp2 = spr;
        int ap = apr;
        int alpha = alphaMode;
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int index2 = 0;
        int rq = 0;
        int gq = 0;
        int bq = 0;
        int aq = 0;
        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp2 = (spr += (sfy >>> 16) * srcStride), ap = (apr += (sfy >>> 16) * alphaStride), sfy = (sfy & 0xFFFF) + sfyi, dp2 = (dpr += dpryi)) {
            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp2 += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                switch (stype) {
                    case 6: {
                        index2 = (srcData[sp2] & 0xFF);
                        sp2 += sfx >>> 16;
                        break;
                    }
                    case 7: {
                        if ((sp2 & 0x1) != 0x0) {
                            index2 = (srcData[sp2 >> 1] & 0xF);
                        }
                        else {
                            index2 = (srcData[sp2 >> 1] >>> 4 & 0xF);
                        }
                        sp2 += sfx >>> 16;
                        break;
                    }
                    case 8: {
                        index2 = (srcData[sp2 >> 2] >>> 6 - (sp2 & 0x3) * 2 & 0x3);
                        sp2 += sfx >>> 16;
                        break;
                    }
                    case 9: {
                        index2 = (srcData[sp2 >> 3] >>> 7 - (sp2 & 0x7) & 0x1);
                        sp2 += sfx >>> 16;
                        break;
                    }
                    case 10: {
                        index2 = (srcData[sp2 >> 3] >>> (sp2 & 0x7) & 0x1);
                        sp2 += sfx >>> 16;
                        break;
                    }
                }
                r = (srcReds[index2] & 0xFF);
                g = (srcGreens[index2] & 0xFF);
                b = (srcBlues[index2] & 0xFF);
                switch (alphaMode) {
                    case -1: {
                        alpha = ((alphaData[ap] & 0xFF) << 16) / 255;
                        ap += sfx >> 16;
                        break;
                    }
                    case -3: {
                        alpha = ((alphaData[ap] != 0) ? 65536 : 0);
                        ap += sfx >> 16;
                        break;
                    }
                    case -4: {
                        alpha = (alphaData[ap >> 3] << (ap & 0x7) + 9 & 0x10000);
                        ap += sfx >> 16;
                        break;
                    }
                    case -5: {
                        final int i = 0;
                        while (0 < alphaData.length && index2 != (alphaData[0] & 0xFF)) {}
                        if (0 < alphaData.length) {
                            continue;
                        }
                        break;
                    }
                    case -6: {
                        int i;
                        for (i = 0; i < alphaData.length && (r != (alphaData[i] & 0xFF) || g != (alphaData[i + 1] & 0xFF) || b != (alphaData[i + 2] & 0xFF)); i += 3) {}
                        if (i < alphaData.length) {
                            continue;
                        }
                        break;
                    }
                }
                if (alpha != 65536) {
                    if (alpha == 0) {
                        continue;
                    }
                    switch (dtype) {
                        case 0: {
                            final int data = destData[dp2] & 0xFF;
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 1: {
                            final int data = (destData[dp2] & 0xFF) << 8 | (destData[dp2 + 1] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 2: {
                            final int data = (destData[dp2 + 1] & 0xFF) << 8 | (destData[dp2] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 3: {
                            final int data = ((destData[dp2] & 0xFF) << 8 | (destData[dp2 + 1] & 0xFF)) << 8 | (destData[dp2 + 2] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 4: {
                            final int data = (((destData[dp2] & 0xFF) << 8 | (destData[dp2 + 1] & 0xFF)) << 8 | (destData[dp2 + 2] & 0xFF)) << 8 | (destData[dp2 + 3] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                        case 5: {
                            final int data = (((destData[dp2 + 3] & 0xFF) << 8 | (destData[dp2 + 2] & 0xFF)) << 8 | (destData[dp2 + 1] & 0xFF)) << 8 | (destData[dp2] & 0xFF);
                            rq = (destReds[(data & destRedMask) >>> destRedShift] & 0xFF);
                            gq = (destGreens[(data & destGreenMask) >>> destGreenShift] & 0xFF);
                            bq = (destBlues[(data & destBlueMask) >>> destBlueShift] & 0xFF);
                            aq = (destAlphas[(data & 0x0) >>> destAlphaShift] & 0xFF);
                            break;
                        }
                    }
                    a = aq + ((a - aq) * alpha >> 16);
                    r = rq + ((r - rq) * alpha >> 16);
                    g = gq + ((g - gq) * alpha >> 16);
                    b = bq + ((b - bq) * alpha >> 16);
                }
                final int data = r >>> destRedPreShift << destRedShift | g >>> destGreenPreShift << destGreenShift | b >>> destBluePreShift << destBlueShift | a >>> destAlphaPreShift << destAlphaShift;
                switch (dtype) {
                    case 0: {
                        destData[dp2] = (byte)data;
                        break;
                    }
                    case 1: {
                        destData[dp2] = (byte)(data >>> 8);
                        destData[dp2 + 1] = (byte)(data & 0xFF);
                        break;
                    }
                    case 2: {
                        destData[dp2] = (byte)(data & 0xFF);
                        destData[dp2 + 1] = (byte)(data >>> 8);
                        break;
                    }
                    case 3: {
                        destData[dp2] = (byte)(data >>> 16);
                        destData[dp2 + 1] = (byte)(data >>> 8);
                        destData[dp2 + 2] = (byte)(data & 0xFF);
                        break;
                    }
                    case 4: {
                        destData[dp2] = (byte)(data >>> 24);
                        destData[dp2 + 1] = (byte)(data >>> 16);
                        destData[dp2 + 2] = (byte)(data >>> 8);
                        destData[dp2 + 3] = (byte)(data & 0xFF);
                        break;
                    }
                    case 5: {
                        destData[dp2] = (byte)(data & 0xFF);
                        destData[dp2 + 1] = (byte)(data >>> 8);
                        destData[dp2 + 2] = (byte)(data >>> 16);
                        destData[dp2 + 3] = (byte)(data >>> 24);
                        break;
                    }
                }
            }
        }
    }
    
    static void blit(final int op, final byte[] srcData, final int srcDepth, final int srcStride, final int srcOrder, final int srcX, final int srcY, final int srcWidth, final int srcHeight, final int srcRedMask, final int srcGreenMask, final int srcBlueMask, int alphaMode, final byte[] alphaData, int alphaStride, final int alphaX, final int alphaY, final byte[] destData, final int destDepth, int destStride, final int destOrder, final int destX, final int destY, final int destWidth, final int destHeight, final byte[] destReds, final byte[] destGreens, final byte[] destBlues, final boolean flipX, final boolean flipY) {
        if (destWidth <= 0 || destHeight <= 0 || alphaMode == 0) {
            return;
        }
        final int srcAlphaMask = 0;
        final int dwm1 = destWidth - 1;
        final int sfxi = (dwm1 != 0) ? ((int)((((long)srcWidth << 16) - 1L) / dwm1)) : 0;
        final int dhm1 = destHeight - 1;
        final int sfyi = (dhm1 != 0) ? ((int)((((long)srcHeight << 16) - 1L) / dhm1)) : 0;
        int sbpp = 0;
        int stype = 0;
        switch (srcDepth) {
            case 8: {
                sbpp = 1;
                stype = 0;
                break;
            }
            case 16: {
                sbpp = 2;
                stype = ((srcOrder == 1) ? 1 : 2);
                break;
            }
            case 24: {
                sbpp = 3;
                stype = 3;
                break;
            }
            case 32: {
                sbpp = 4;
                stype = ((srcOrder == 1) ? 4 : 5);
                break;
            }
            default: {
                return;
            }
        }
        int spr = srcY * srcStride + srcX * sbpp;
        int dtype = 0;
        switch (destDepth) {
            case 8: {
                dtype = 6;
                break;
            }
            case 4: {
                destStride <<= 1;
                dtype = 7;
                break;
            }
            case 2: {
                destStride <<= 2;
                dtype = 8;
                break;
            }
            case 1: {
                destStride <<= 3;
                dtype = ((destOrder == 1) ? 9 : 10);
                break;
            }
            default: {
                return;
            }
        }
        int dpr = (flipY ? (destY + dhm1) : destY) * destStride + (flipX ? (destX + dwm1) : destX);
        final int dprxi = flipX ? -1 : 1;
        final int dpryi = flipY ? (-destStride) : destStride;
        int apr = 0;
        if ((op & 0x2) != 0x0) {
            switch (alphaMode) {
                case -3:
                case -1: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -4: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    alphaStride <<= 3;
                    apr = alphaY * alphaStride + alphaX;
                    break;
                }
                case -5: {
                    return;
                }
                case -6: {
                    if (alphaData == null) {
                        alphaMode = 65536;
                    }
                    apr = 0;
                    break;
                }
                default: {
                    alphaMode = (alphaMode << 16) / 255;
                }
                case -2: {
                    apr = 0;
                    break;
                }
            }
        }
        else {
            alphaMode = 65536;
            apr = 0;
        }
        final boolean ditherEnabled = (op & 0x4) != 0x0;
        final int srcRedShift = getChannelShift(srcRedMask);
        final byte[] srcReds = ImageData.ANY_TO_EIGHT[getChannelWidth(srcRedMask, srcRedShift)];
        final int srcGreenShift = getChannelShift(srcGreenMask);
        final byte[] srcGreens = ImageData.ANY_TO_EIGHT[getChannelWidth(srcGreenMask, srcGreenShift)];
        final int srcBlueShift = getChannelShift(srcBlueMask);
        final byte[] srcBlues = ImageData.ANY_TO_EIGHT[getChannelWidth(srcBlueMask, srcBlueShift)];
        final int srcAlphaShift = getChannelShift(0);
        final byte[] srcAlphas = ImageData.ANY_TO_EIGHT[getChannelWidth(0, srcAlphaShift)];
        int dp = dpr;
        int sp = spr;
        int ap = apr;
        int alpha = alphaMode;
        int r = 0;
        int g = 0;
        int b = 0;
        int a = 0;
        int indexq = 0;
        int lastindex = 0;
        int lastr = -1;
        int lastg = -1;
        int lastb = -1;
        int destPaletteSize = 1 << destDepth;
        if (destReds != null && destReds.length < destPaletteSize) {
            destPaletteSize = destReds.length;
        }
        int[] rerr;
        int[] gerr;
        int[] berr;
        if (ditherEnabled) {
            rerr = new int[destWidth + 2];
            gerr = new int[destWidth + 2];
            berr = new int[destWidth + 2];
        }
        else {
            rerr = null;
            gerr = null;
            berr = null;
        }
        for (int dy = destHeight, sfy = sfyi; dy > 0; --dy, sp = (spr += (sfy >>> 16) * srcStride), ap = (apr += (sfy >>> 16) * alphaStride), sfy = (sfy & 0xFFFF) + sfyi, dp = (dpr += dpryi)) {
            int lrerr = 0;
            int lgerr = 0;
            int lberr = 0;
            for (int dx = destWidth, sfx = sfxi; dx > 0; --dx, dp += dprxi, sfx = (sfx & 0xFFFF) + sfxi) {
                switch (stype) {
                    case 0: {
                        final int data = srcData[sp] & 0xFF;
                        sp += sfx >>> 16;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 1: {
                        final int data = (srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF);
                        sp += (sfx >>> 16) * 2;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 2: {
                        final int data = (srcData[sp + 1] & 0xFF) << 8 | (srcData[sp] & 0xFF);
                        sp += (sfx >>> 16) * 2;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 3: {
                        final int data = ((srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp + 2] & 0xFF);
                        sp += (sfx >>> 16) * 3;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 4: {
                        final int data = (((srcData[sp] & 0xFF) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp + 2] & 0xFF)) << 8 | (srcData[sp + 3] & 0xFF);
                        sp += (sfx >>> 16) * 4;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                    case 5: {
                        final int data = (((srcData[sp + 3] & 0xFF) << 8 | (srcData[sp + 2] & 0xFF)) << 8 | (srcData[sp + 1] & 0xFF)) << 8 | (srcData[sp] & 0xFF);
                        sp += (sfx >>> 16) * 4;
                        r = (srcReds[(data & srcRedMask) >>> srcRedShift] & 0xFF);
                        g = (srcGreens[(data & srcGreenMask) >>> srcGreenShift] & 0xFF);
                        b = (srcBlues[(data & srcBlueMask) >>> srcBlueShift] & 0xFF);
                        a = (srcAlphas[(data & 0x0) >>> srcAlphaShift] & 0xFF);
                        break;
                    }
                }
                switch (alphaMode) {
                    case -1: {
                        alpha = ((alphaData[ap] & 0xFF) << 16) / 255;
                        ap += sfx >> 16;
                        break;
                    }
                    case -2: {
                        alpha = (a << 16) / 255;
                        break;
                    }
                    case -3: {
                        alpha = ((alphaData[ap] != 0) ? 65536 : 0);
                        ap += sfx >> 16;
                        break;
                    }
                    case -4: {
                        alpha = (alphaData[ap >> 3] << (ap & 0x7) + 9 & 0x10000);
                        ap += sfx >> 16;
                        break;
                    }
                    case -6: {
                        alpha = 65536;
                        for (int i = 0; i < alphaData.length; i += 3) {
                            if (r == alphaData[i] && g == alphaData[i + 1] && b == alphaData[i + 2]) {
                                alpha = 0;
                                break;
                            }
                        }
                        break;
                    }
                }
                if (alpha != 65536) {
                    if (alpha == 0) {
                        continue;
                    }
                    switch (dtype) {
                        case 6: {
                            indexq = (destData[dp] & 0xFF);
                            break;
                        }
                        case 7: {
                            if ((dp & 0x1) != 0x0) {
                                indexq = (destData[dp >> 1] & 0xF);
                                break;
                            }
                            indexq = (destData[dp >> 1] >>> 4 & 0xF);
                            break;
                        }
                        case 8: {
                            indexq = (destData[dp >> 2] >>> 6 - (dp & 0x3) * 2 & 0x3);
                            break;
                        }
                        case 9: {
                            indexq = (destData[dp >> 3] >>> 7 - (dp & 0x7) & 0x1);
                            break;
                        }
                        case 10: {
                            indexq = (destData[dp >> 3] >>> (dp & 0x7) & 0x1);
                            break;
                        }
                    }
                    final int rq = destReds[indexq] & 0xFF;
                    final int gq = destGreens[indexq] & 0xFF;
                    final int bq = destBlues[indexq] & 0xFF;
                    r = rq + ((r - rq) * alpha >> 16);
                    g = gq + ((g - gq) * alpha >> 16);
                    b = bq + ((b - bq) * alpha >> 16);
                }
                if (ditherEnabled) {
                    r += rerr[dx] >> 4;
                    if (r < 0) {
                        r = 0;
                    }
                    else if (r > 255) {
                        r = 255;
                    }
                    g += gerr[dx] >> 4;
                    if (g < 0) {
                        g = 0;
                    }
                    else if (g > 255) {
                        g = 255;
                    }
                    b += berr[dx] >> 4;
                    if (b < 0) {
                        b = 0;
                    }
                    else if (b > 255) {
                        b = 255;
                    }
                    rerr[dx] = lrerr;
                    gerr[dx] = lgerr;
                    berr[dx] = lberr;
                }
                if (r != lastr || g != lastg || b != lastb) {
                    int j = 0;
                    int minDistance = Integer.MAX_VALUE;
                    while (j < destPaletteSize) {
                        final int dr = (destReds[j] & 0xFF) - r;
                        final int dg = (destGreens[j] & 0xFF) - g;
                        final int db = (destBlues[j] & 0xFF) - b;
                        final int distance = dr * dr + dg * dg + db * db;
                        if (distance < minDistance) {
                            lastindex = j;
                            if (distance == 0) {
                                break;
                            }
                            minDistance = distance;
                        }
                        ++j;
                    }
                    lastr = r;
                    lastg = g;
                    lastb = b;
                }
                if (ditherEnabled) {
                    final int dxm1 = dx - 1;
                    final int dxp1 = dx + 1;
                    final int[] array = rerr;
                    final int n = dxp1;
                    final int[] array10 = array;
                    final int n10 = n;
                    int acc;
                    array10[n10] += (acc = (lrerr = r - (destReds[lastindex] & 0xFF)) + lrerr + lrerr);
                    final int[] array2 = rerr;
                    final int n2 = dx;
                    final int[] array11 = array2;
                    final int n11 = n2;
                    array11[n11] += (acc += lrerr + lrerr);
                    final int[] array3 = rerr;
                    final int n3 = dxm1;
                    final int[] array12 = array3;
                    final int n12 = n3;
                    array12[n12] += acc + lrerr + lrerr;
                    final int[] array4 = gerr;
                    final int n4 = dxp1;
                    final int[] array13 = array4;
                    final int n13 = n4;
                    array13[n13] += (acc = (lgerr = g - (destGreens[lastindex] & 0xFF)) + lgerr + lgerr);
                    final int[] array5 = gerr;
                    final int n5 = dx;
                    final int[] array14 = array5;
                    final int n14 = n5;
                    array14[n14] += (acc += lgerr + lgerr);
                    final int[] array6 = gerr;
                    final int n6 = dxm1;
                    final int[] array15 = array6;
                    final int n15 = n6;
                    array15[n15] += acc + lgerr + lgerr;
                    final int[] array7 = berr;
                    final int n7 = dxp1;
                    final int[] array16 = array7;
                    final int n16 = n7;
                    array16[n16] += (acc = (lberr = b - (destBlues[lastindex] & 0xFF)) + lberr + lberr);
                    final int[] array8 = berr;
                    final int n8 = dx;
                    final int[] array17 = array8;
                    final int n17 = n8;
                    array17[n17] += (acc += lberr + lberr);
                    final int[] array9 = berr;
                    final int n9 = dxm1;
                    final int[] array18 = array9;
                    final int n18 = n9;
                    array18[n18] += acc + lberr + lberr;
                }
                switch (dtype) {
                    case 6: {
                        destData[dp] = (byte)lastindex;
                        break;
                    }
                    case 7: {
                        if ((dp & 0x1) != 0x0) {
                            destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF0) | lastindex);
                            break;
                        }
                        destData[dp >> 1] = (byte)((destData[dp >> 1] & 0xF) | lastindex << 4);
                        break;
                    }
                    case 8: {
                        final int shift = 6 - (dp & 0x3) * 2;
                        destData[dp >> 2] = (byte)((destData[dp >> 2] & ~(3 << shift)) | lastindex << shift);
                        break;
                    }
                    case 9: {
                        final int shift = 7 - (dp & 0x7);
                        destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | lastindex << shift);
                        break;
                    }
                    case 10: {
                        final int shift = dp & 0x7;
                        destData[dp >> 3] = (byte)((destData[dp >> 3] & ~(1 << shift)) | lastindex << shift);
                        break;
                    }
                }
            }
        }
    }
    
    static int getChannelShift(int mask) {
        if (mask == 0) {
            return 0;
        }
        int i;
        for (i = 0; (mask & 0x1) == 0x0 && i < 32; mask >>>= 1, ++i) {}
        return i;
    }
    
    static int getChannelWidth(int mask, final int shift) {
        if (mask == 0) {
            return 0;
        }
        int i;
        for (mask >>>= shift, i = shift; (mask & 0x1) != 0x0 && i < 32; mask >>>= 1, ++i) {}
        return i - shift;
    }
    
    static byte getChannelField(final int data, final int mask) {
        final int shift = getChannelShift(mask);
        return ImageData.ANY_TO_EIGHT[getChannelWidth(mask, shift)][(data & mask) >>> shift];
    }
    
    static ImageData createGradientBand(final int width, final int height, final boolean vertical, final RGB fromRGB, final RGB toRGB, final int redBits, final int greenBits, final int blueBits) {
        PaletteData paletteData;
        int bitmapDepth;
        int bandWidth;
        int bandHeight;
        byte[] bitmapData;
        if (redBits != 0 && greenBits != 0 && blueBits != 0) {
            paletteData = new PaletteData(65280, 16711680, -16777216);
            bitmapDepth = 32;
            if (redBits >= 8 && greenBits >= 8 && blueBits >= 8) {
                int steps;
                if (vertical) {
                    bandWidth = 1;
                    bandHeight = height;
                    steps = ((bandHeight > 1) ? (bandHeight - 1) : 1);
                }
                else {
                    bandWidth = width;
                    bandHeight = 1;
                    steps = ((bandWidth > 1) ? (bandWidth - 1) : 1);
                }
                final int bytesPerLine = bandWidth * 4;
                bitmapData = new byte[bandHeight * bytesPerLine];
                buildPreciseGradientChannel(fromRGB.blue, toRGB.blue, steps, bandWidth, bandHeight, vertical, bitmapData, 0, bytesPerLine);
                buildPreciseGradientChannel(fromRGB.green, toRGB.green, steps, bandWidth, bandHeight, vertical, bitmapData, 1, bytesPerLine);
                buildPreciseGradientChannel(fromRGB.red, toRGB.red, steps, bandWidth, bandHeight, vertical, bitmapData, 2, bytesPerLine);
            }
            else {
                int steps;
                if (vertical) {
                    bandWidth = ((width < 8) ? width : 8);
                    bandHeight = height;
                    steps = ((bandHeight > 1) ? (bandHeight - 1) : 1);
                }
                else {
                    bandWidth = width;
                    bandHeight = ((height < 8) ? height : 8);
                    steps = ((bandWidth > 1) ? (bandWidth - 1) : 1);
                }
                final int bytesPerLine = bandWidth * 4;
                bitmapData = new byte[bandHeight * bytesPerLine];
                buildDitheredGradientChannel(fromRGB.blue, toRGB.blue, steps, bandWidth, bandHeight, vertical, bitmapData, 0, bytesPerLine, blueBits);
                buildDitheredGradientChannel(fromRGB.green, toRGB.green, steps, bandWidth, bandHeight, vertical, bitmapData, 1, bytesPerLine, greenBits);
                buildDitheredGradientChannel(fromRGB.red, toRGB.red, steps, bandWidth, bandHeight, vertical, bitmapData, 2, bytesPerLine, redBits);
            }
        }
        else {
            paletteData = new PaletteData(new RGB[] { fromRGB, toRGB });
            bitmapDepth = 8;
            int blendi;
            if (vertical) {
                bandWidth = ((width < 8) ? width : 8);
                bandHeight = height;
                blendi = ((bandHeight > 1) ? (17039360 / (bandHeight - 1) + 1) : 1);
            }
            else {
                bandWidth = width;
                bandHeight = ((height < 8) ? height : 8);
                blendi = ((bandWidth > 1) ? (17039360 / (bandWidth - 1) + 1) : 1);
            }
            final int bytesPerLine = bandWidth + 3 & 0xFFFFFFFC;
            bitmapData = new byte[bandHeight * bytesPerLine];
            if (vertical) {
                for (int dy = 0, blend = 0, dp = 0; dy < bandHeight; ++dy, blend += blendi, dp += bytesPerLine) {
                    for (int dx = 0; dx < bandWidth; ++dx) {
                        bitmapData[dp + dx] = (byte)((blend + ImageData.DITHER_MATRIX[dy & 0x7][dx] >= 16777216) ? 1 : 0);
                    }
                }
            }
            else {
                for (int dx2 = 0, blend = 0; dx2 < bandWidth; ++dx2, blend += blendi) {
                    for (int dy2 = 0, dptr = dx2; dy2 < bandHeight; ++dy2, dptr += bytesPerLine) {
                        bitmapData[dptr] = (byte)((blend + ImageData.DITHER_MATRIX[dy2][dx2 & 0x7] >= 16777216) ? 1 : 0);
                    }
                }
            }
        }
        return new ImageData(bandWidth, bandHeight, bitmapDepth, paletteData, 4, bitmapData);
    }
    
    static void buildPreciseGradientChannel(final int from, final int to, final int steps, final int bandWidth, final int bandHeight, final boolean vertical, final byte[] bitmapData, int dp, final int bytesPerLine) {
        int val = from << 16;
        final int inc = ((to << 16) - val) / steps + 1;
        if (vertical) {
            for (int dy = 0; dy < bandHeight; ++dy, dp += bytesPerLine) {
                bitmapData[dp] = (byte)(val >>> 16);
                val += inc;
            }
        }
        else {
            for (int dx = 0; dx < bandWidth; ++dx, dp += 4) {
                bitmapData[dp] = (byte)(val >>> 16);
                val += inc;
            }
        }
    }
    
    static void buildDitheredGradientChannel(final int from, final int to, final int steps, final int bandWidth, final int bandHeight, final boolean vertical, final byte[] bitmapData, int dp, final int bytesPerLine, final int bits) {
        final int mask = 65280 >>> bits;
        int val = from << 16;
        final int inc = ((to << 16) - val) / steps + 1;
        if (vertical) {
            for (int dy = 0; dy < bandHeight; ++dy, dp += bytesPerLine) {
                for (int dx = 0, dptr = dp; dx < bandWidth; ++dx, dptr += 4) {
                    final int thresh = ImageData.DITHER_MATRIX[dy & 0x7][dx] >>> bits;
                    final int temp = val + thresh;
                    if (temp > 16777215) {
                        bitmapData[dptr] = -1;
                    }
                    else {
                        bitmapData[dptr] = (byte)(temp >>> 16 & mask);
                    }
                }
                val += inc;
            }
        }
        else {
            for (int dx2 = 0; dx2 < bandWidth; ++dx2, dp += 4) {
                for (int dy2 = 0, dptr = dp; dy2 < bandHeight; ++dy2, dptr += bytesPerLine) {
                    final int thresh = ImageData.DITHER_MATRIX[dy2][dx2 & 0x7] >>> bits;
                    final int temp = val + thresh;
                    if (temp > 16777215) {
                        bitmapData[dptr] = -1;
                    }
                    else {
                        bitmapData[dptr] = (byte)(temp >>> 16 & mask);
                    }
                }
                val += inc;
            }
        }
    }
    
    static void fillGradientRectangle(final GC gc, final Device device, final int x, final int y, final int width, final int height, final boolean vertical, final RGB fromRGB, final RGB toRGB, final int redBits, final int greenBits, final int blueBits) {
        final ImageData band = createGradientBand(width, height, vertical, fromRGB, toRGB, redBits, greenBits, blueBits);
        final Image image = new Image(device, band);
        if (band.width == 1 || band.height == 1) {
            gc.drawImage(image, 0, 0, DPIUtil.autoScaleDown(band.width), DPIUtil.autoScaleDown(band.height), DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(y), DPIUtil.autoScaleDown(width), DPIUtil.autoScaleDown(height));
        }
        else if (vertical) {
            for (int dx = 0; dx < width; dx += band.width) {
                int blitWidth = width - dx;
                if (blitWidth > band.width) {
                    blitWidth = band.width;
                }
                gc.drawImage(image, 0, 0, DPIUtil.autoScaleDown(blitWidth), DPIUtil.autoScaleDown(band.height), DPIUtil.autoScaleDown(dx + x), DPIUtil.autoScaleDown(y), DPIUtil.autoScaleDown(blitWidth), DPIUtil.autoScaleDown(band.height));
            }
        }
        else {
            for (int dy = 0; dy < height; dy += band.height) {
                int blitHeight = height - dy;
                if (blitHeight > band.height) {
                    blitHeight = band.height;
                }
                gc.drawImage(image, 0, 0, DPIUtil.autoScaleDown(band.width), DPIUtil.autoScaleDown(blitHeight), DPIUtil.autoScaleDown(x), DPIUtil.autoScaleDown(dy + y), DPIUtil.autoScaleDown(band.width), DPIUtil.autoScaleDown(blitHeight));
            }
        }
        image.dispose();
    }
    
    static {
        ANY_TO_EIGHT = new byte[9][];
        for (int b = 0; b < 9; ++b) {
            final byte[][] any_TO_EIGHT = ImageData.ANY_TO_EIGHT;
            final int n = b;
            final byte[] array = new byte[1 << b];
            any_TO_EIGHT[n] = array;
            final byte[] data = array;
            if (b != 0) {
                int inc = 0;
                int bit = 65536;
                while ((bit >>= b) != 0) {
                    inc |= bit;
                }
                int v = 0;
                int p = 0;
                while (v < 65536) {
                    data[p++] = (byte)(v >> 8);
                    v += inc;
                }
            }
        }
        ONE_TO_ONE_MAPPING = ImageData.ANY_TO_EIGHT[8];
        DITHER_MATRIX = new int[][] { { 16515072, 8126464, 14417920, 6029312, 15990784, 7602176, 13893632, 5505024 }, { 3932160, 12320768, 1835008, 10223616, 3407872, 11796480, 1310720, 9699328 }, { 13369344, 4980736, 15466496, 7077888, 12845056, 4456448, 14942208, 6553600 }, { 786432, 9175040, 2883584, 11272192, 262144, 8650752, 2359296, 10747904 }, { 15728640, 7340032, 13631488, 5242880, 16252928, 7864320, 14155776, 5767168 }, { 3145728, 11534336, 1048576, 9437184, 3670016, 12058624, 1572864, 9961472 }, { 12582912, 4194304, 14680064, 6291456, 13107200, 4718592, 15204352, 6815744 }, { 0, 8388608, 2097152, 10485760, 524288, 8912896, 2621440, 11010048 } };
    }
}
