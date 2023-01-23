//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public final class JPEGFileFormat extends FileFormat
{
    int restartInterval;
    JPEGFrameHeader frameHeader;
    int imageWidth;
    int imageHeight;
    int interleavedMcuCols;
    int interleavedMcuRows;
    int maxV;
    int maxH;
    boolean progressive;
    int samplePrecision;
    int nComponents;
    int[][] frameComponents;
    int[] componentIds;
    byte[][] imageComponents;
    int[] dataUnit;
    int[][][] dataUnits;
    int[] precedingDCs;
    JPEGScanHeader scanHeader;
    byte[] dataBuffer;
    int currentBitCount;
    int bufferCurrentPosition;
    int restartsToGo;
    int nextRestartNumber;
    JPEGHuffmanTable[] acHuffmanTables;
    JPEGHuffmanTable[] dcHuffmanTables;
    int[][] quantizationTables;
    int currentByte;
    int encoderQFactor;
    int eobrun;
    public static final int DCTSIZE = 8;
    public static final int DCTSIZESQR = 64;
    public static final int FIX_0_899976223 = 7373;
    public static final int FIX_1_961570560 = 16069;
    public static final int FIX_2_053119869 = 16819;
    public static final int FIX_0_298631336 = 2446;
    public static final int FIX_1_847759065 = 15137;
    public static final int FIX_1_175875602 = 9633;
    public static final int FIX_3_072711026 = 25172;
    public static final int FIX_0_765366865 = 6270;
    public static final int FIX_2_562915447 = 20995;
    public static final int FIX_0_541196100 = 4433;
    public static final int FIX_0_390180644 = 3196;
    public static final int FIX_1_501321110 = 12299;
    public static final int APP0 = 65504;
    public static final int APP15 = 65519;
    public static final int COM = 65534;
    public static final int DAC = 65484;
    public static final int DHP = 65502;
    public static final int DHT = 65476;
    public static final int DNL = 65500;
    public static final int DRI = 65501;
    public static final int DQT = 65499;
    public static final int EOI = 65497;
    public static final int EXP = 65503;
    public static final int JPG = 65480;
    public static final int JPG0 = 65520;
    public static final int JPG13 = 65533;
    public static final int RST0 = 65488;
    public static final int RST1 = 65489;
    public static final int RST2 = 65490;
    public static final int RST3 = 65491;
    public static final int RST4 = 65492;
    public static final int RST5 = 65493;
    public static final int RST6 = 65494;
    public static final int RST7 = 65495;
    public static final int SOF0 = 65472;
    public static final int SOF1 = 65473;
    public static final int SOF2 = 65474;
    public static final int SOF3 = 65475;
    public static final int SOF5 = 65477;
    public static final int SOF6 = 65478;
    public static final int SOF7 = 65479;
    public static final int SOF9 = 65481;
    public static final int SOF10 = 65482;
    public static final int SOF11 = 65483;
    public static final int SOF13 = 65485;
    public static final int SOF14 = 65486;
    public static final int SOF15 = 65487;
    public static final int SOI = 65496;
    public static final int SOS = 65498;
    public static final int TEM = 65281;
    public static final int TQI = 0;
    public static final int HI = 1;
    public static final int VI = 2;
    public static final int CW = 3;
    public static final int CH = 4;
    public static final int DC = 0;
    public static final int AC = 1;
    public static final int ID_Y = 0;
    public static final int ID_CB = 1;
    public static final int ID_CR = 2;
    public static final RGB[] RGB16;
    public static final int[] ExtendTest;
    public static final int[] ExtendOffset;
    public static final int[] ZigZag8x8;
    public static final int[] CrRTable;
    public static final int[] CbBTable;
    public static final int[] CrGTable;
    public static final int[] CbGTable;
    public static final int[] RYTable;
    public static final int[] GYTable;
    public static final int[] BYTable;
    public static final int[] RCbTable;
    public static final int[] GCbTable;
    public static final int[] BCbTable;
    public static final int[] RCrTable;
    public static final int[] GCrTable;
    public static final int[] BCrTable;
    public static final int[] NBitsTable;
    
    public JPEGFileFormat() {
        this.encoderQFactor = 75;
        this.eobrun = 0;
    }
    
    void compress(final ImageData image, final byte[] dataYComp, final byte[] dataCbComp, final byte[] dataCrComp) {
        final int srcWidth = image.width;
        final int srcHeight = image.height;
        final int vhFactor = this.maxV * this.maxH;
        this.imageComponents = new byte[this.nComponents][];
        for (int i = 0; i < this.nComponents; ++i) {
            final int[] frameComponent = this.frameComponents[this.componentIds[i]];
            this.imageComponents[i] = new byte[frameComponent[3] * frameComponent[4]];
        }
        int[] frameComponent2 = this.frameComponents[this.componentIds[0]];
        for (int yPos = 0; yPos < srcHeight; ++yPos) {
            final int srcOfs = yPos * srcWidth;
            final int dstOfs = yPos * frameComponent2[3];
            System.arraycopy(dataYComp, srcOfs, this.imageComponents[0], dstOfs, srcWidth);
        }
        frameComponent2 = this.frameComponents[this.componentIds[1]];
        for (int yPos = 0; yPos < srcHeight / this.maxV; ++yPos) {
            final int destRowIndex = yPos * frameComponent2[3];
            for (int xPos = 0; xPos < srcWidth / this.maxH; ++xPos) {
                int sum = 0;
                for (int iv = 0; iv < this.maxV; ++iv) {
                    final int srcIndex = (yPos * this.maxV + iv) * srcWidth + xPos * this.maxH;
                    for (int ih = 0; ih < this.maxH; ++ih) {
                        sum += (dataCbComp[srcIndex + ih] & 0xFF);
                    }
                }
                this.imageComponents[1][destRowIndex + xPos] = (byte)(sum / vhFactor);
            }
        }
        frameComponent2 = this.frameComponents[this.componentIds[2]];
        for (int yPos = 0; yPos < srcHeight / this.maxV; ++yPos) {
            final int destRowIndex = yPos * frameComponent2[3];
            for (int xPos = 0; xPos < srcWidth / this.maxH; ++xPos) {
                int sum = 0;
                for (int iv = 0; iv < this.maxV; ++iv) {
                    final int srcIndex = (yPos * this.maxV + iv) * srcWidth + xPos * this.maxH;
                    for (int ih = 0; ih < this.maxH; ++ih) {
                        sum += (dataCrComp[srcIndex + ih] & 0xFF);
                    }
                }
                this.imageComponents[2][destRowIndex + xPos] = (byte)(sum / vhFactor);
            }
        }
        for (int iComp = 0; iComp < this.nComponents; ++iComp) {
            final byte[] imageComponent = this.imageComponents[iComp];
            frameComponent2 = this.frameComponents[this.componentIds[iComp]];
            final int hFactor = frameComponent2[1];
            final int vFactor = frameComponent2[2];
            final int componentWidth = frameComponent2[3];
            final int componentHeight = frameComponent2[4];
            final int compressedWidth = srcWidth / (this.maxH / hFactor);
            final int compressedHeight = srcHeight / (this.maxV / vFactor);
            if (compressedWidth < componentWidth) {
                final int delta = componentWidth - compressedWidth;
                for (int yPos2 = 0; yPos2 < compressedHeight; ++yPos2) {
                    final int dstOfs2 = (yPos2 + 1) * componentWidth - delta;
                    final int dataValue = imageComponent[(dstOfs2 > 0) ? (dstOfs2 - 1) : 0] & 0xFF;
                    for (int j = 0; j < delta; ++j) {
                        imageComponent[dstOfs2 + j] = (byte)dataValue;
                    }
                }
            }
            if (compressedHeight < componentHeight) {
                final int srcOfs2 = (compressedHeight > 0) ? ((compressedHeight - 1) * componentWidth) : 1;
                for (int yPos2 = (compressedHeight > 0) ? compressedHeight : 1; yPos2 <= componentHeight; ++yPos2) {
                    final int dstOfs2 = (yPos2 - 1) * componentWidth;
                    System.arraycopy(imageComponent, srcOfs2, imageComponent, dstOfs2, componentWidth);
                }
            }
        }
    }
    
    void convert4BitRGBToYCbCr(final ImageData image) {
        final RGB[] rgbs = image.getRGBs();
        final int paletteSize = rgbs.length;
        final byte[] yComp = new byte[paletteSize];
        final byte[] cbComp = new byte[paletteSize];
        final byte[] crComp = new byte[paletteSize];
        final int srcWidth = image.width;
        final int srcHeight = image.height;
        for (int i = 0; i < paletteSize; ++i) {
            final RGB color = rgbs[i];
            final int r = color.red;
            final int g = color.green;
            final int b = color.blue;
            int n = JPEGFileFormat.RYTable[r] + JPEGFileFormat.GYTable[g] + JPEGFileFormat.BYTable[b];
            yComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array = yComp;
                final int n2 = i;
                final byte[] array4 = array;
                final int n5 = n2;
                --array4[n5];
            }
            n = JPEGFileFormat.RCbTable[r] + JPEGFileFormat.GCbTable[g] + JPEGFileFormat.BCbTable[b];
            cbComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array2 = cbComp;
                final int n3 = i;
                final byte[] array5 = array2;
                final int n6 = n3;
                --array5[n6];
            }
            n = JPEGFileFormat.RCrTable[r] + JPEGFileFormat.GCrTable[g] + JPEGFileFormat.BCrTable[b];
            crComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array3 = crComp;
                final int n4 = i;
                final byte[] array6 = array3;
                final int n7 = n4;
                --array6[n7];
            }
        }
        final int bSize = srcWidth * srcHeight;
        final byte[] dataYComp = new byte[bSize];
        final byte[] dataCbComp = new byte[bSize];
        final byte[] dataCrComp = new byte[bSize];
        final byte[] origData = image.data;
        final int bytesPerLine = image.bytesPerLine;
        final int maxScanlineByte = srcWidth >> 1;
        for (int yPos = 0; yPos < srcHeight; ++yPos) {
            for (int xPos = 0; xPos < maxScanlineByte; ++xPos) {
                final int srcIndex = yPos * bytesPerLine + xPos;
                final int dstIndex = yPos * srcWidth + xPos * 2;
                int value2 = origData[srcIndex] & 0xFF;
                final int value3 = value2 >> 4;
                value2 &= 0xF;
                dataYComp[dstIndex] = yComp[value3];
                dataCbComp[dstIndex] = cbComp[value3];
                dataCrComp[dstIndex] = crComp[value3];
                dataYComp[dstIndex + 1] = yComp[value2];
                dataCbComp[dstIndex + 1] = cbComp[value2];
                dataCrComp[dstIndex + 1] = crComp[value2];
            }
        }
        this.compress(image, dataYComp, dataCbComp, dataCrComp);
    }
    
    void convert8BitRGBToYCbCr(final ImageData image) {
        final RGB[] rgbs = image.getRGBs();
        final int paletteSize = rgbs.length;
        final byte[] yComp = new byte[paletteSize];
        final byte[] cbComp = new byte[paletteSize];
        final byte[] crComp = new byte[paletteSize];
        final int srcWidth = image.width;
        final int srcHeight = image.height;
        for (int i = 0; i < paletteSize; ++i) {
            final RGB color = rgbs[i];
            final int r = color.red;
            final int g = color.green;
            final int b = color.blue;
            int n = JPEGFileFormat.RYTable[r] + JPEGFileFormat.GYTable[g] + JPEGFileFormat.BYTable[b];
            yComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array = yComp;
                final int n2 = i;
                final byte[] array4 = array;
                final int n5 = n2;
                --array4[n5];
            }
            n = JPEGFileFormat.RCbTable[r] + JPEGFileFormat.GCbTable[g] + JPEGFileFormat.BCbTable[b];
            cbComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array2 = cbComp;
                final int n3 = i;
                final byte[] array5 = array2;
                final int n6 = n3;
                --array5[n6];
            }
            n = JPEGFileFormat.RCrTable[r] + JPEGFileFormat.GCrTable[g] + JPEGFileFormat.BCrTable[b];
            crComp[i] = (byte)(n >> 16);
            if (n < 0 && (n & 0xFFFF) != 0x0) {
                final byte[] array3 = crComp;
                final int n4 = i;
                final byte[] array6 = array3;
                final int n7 = n4;
                --array6[n7];
            }
        }
        final int dstWidth = image.width;
        final int dstHeight = srcHeight;
        final int stride = srcWidth + 3 >> 2 << 2;
        final int bSize = dstWidth * dstHeight;
        final byte[] dataYComp = new byte[bSize];
        final byte[] dataCbComp = new byte[bSize];
        final byte[] dataCrComp = new byte[bSize];
        final byte[] origData = image.data;
        for (int yPos = 0; yPos < srcHeight; ++yPos) {
            final int srcRowIndex = yPos * stride;
            final int dstRowIndex = yPos * dstWidth;
            for (int xPos = 0; xPos < srcWidth; ++xPos) {
                final int value = origData[srcRowIndex + xPos] & 0xFF;
                final int dstIndex = dstRowIndex + xPos;
                dataYComp[dstIndex] = yComp[value];
                dataCbComp[dstIndex] = cbComp[value];
                dataCrComp[dstIndex] = crComp[value];
            }
        }
        this.compress(image, dataYComp, dataCbComp, dataCrComp);
    }
    
    byte[] convertCMYKToRGB() {
        return new byte[0];
    }
    
    void convertImageToYCbCr(final ImageData image) {
        switch (image.depth) {
            case 4: {
                this.convert4BitRGBToYCbCr(image);
            }
            case 8: {
                this.convert8BitRGBToYCbCr(image);
            }
            case 16:
            case 24:
            case 32: {
                this.convertMultiRGBToYCbCr(image);
            }
            default: {
                SWT.error(38);
            }
        }
    }
    
    void convertMultiRGBToYCbCr(final ImageData image) {
        final int srcWidth = image.width;
        final int srcHeight = image.height;
        final int bSize = srcWidth * srcHeight;
        final byte[] dataYComp = new byte[bSize];
        final byte[] dataCbComp = new byte[bSize];
        final byte[] dataCrComp = new byte[bSize];
        final PaletteData palette = image.palette;
        final int[] buffer = new int[srcWidth];
        if (palette.isDirect) {
            final int redMask = palette.redMask;
            final int greenMask = palette.greenMask;
            final int blueMask = palette.blueMask;
            final int redShift = palette.redShift;
            final int greenShift = palette.greenShift;
            final int blueShift = palette.blueShift;
            for (int yPos = 0; yPos < srcHeight; ++yPos) {
                image.getPixels(0, yPos, srcWidth, buffer, 0);
                final int dstRowIndex = yPos * srcWidth;
                for (int xPos = 0; xPos < srcWidth; ++xPos) {
                    final int pixel = buffer[xPos];
                    final int dstDataIndex = dstRowIndex + xPos;
                    int r = pixel & redMask;
                    r = ((redShift < 0) ? (r >>> -redShift) : (r << redShift));
                    int g = pixel & greenMask;
                    g = ((greenShift < 0) ? (g >>> -greenShift) : (g << greenShift));
                    int b = pixel & blueMask;
                    b = ((blueShift < 0) ? (b >>> -blueShift) : (b << blueShift));
                    dataYComp[dstDataIndex] = (byte)(JPEGFileFormat.RYTable[r] + JPEGFileFormat.GYTable[g] + JPEGFileFormat.BYTable[b] >> 16);
                    dataCbComp[dstDataIndex] = (byte)(JPEGFileFormat.RCbTable[r] + JPEGFileFormat.GCbTable[g] + JPEGFileFormat.BCbTable[b] >> 16);
                    dataCrComp[dstDataIndex] = (byte)(JPEGFileFormat.RCrTable[r] + JPEGFileFormat.GCrTable[g] + JPEGFileFormat.BCrTable[b] >> 16);
                }
            }
        }
        else {
            for (int yPos2 = 0; yPos2 < srcHeight; ++yPos2) {
                image.getPixels(0, yPos2, srcWidth, buffer, 0);
                final int dstRowIndex2 = yPos2 * srcWidth;
                for (int xPos2 = 0; xPos2 < srcWidth; ++xPos2) {
                    final int pixel2 = buffer[xPos2];
                    final int dstDataIndex2 = dstRowIndex2 + xPos2;
                    final RGB rgb = palette.getRGB(pixel2);
                    final int r2 = rgb.red;
                    final int g2 = rgb.green;
                    final int b2 = rgb.blue;
                    dataYComp[dstDataIndex2] = (byte)(JPEGFileFormat.RYTable[r2] + JPEGFileFormat.GYTable[g2] + JPEGFileFormat.BYTable[b2] >> 16);
                    dataCbComp[dstDataIndex2] = (byte)(JPEGFileFormat.RCbTable[r2] + JPEGFileFormat.GCbTable[g2] + JPEGFileFormat.BCbTable[b2] >> 16);
                    dataCrComp[dstDataIndex2] = (byte)(JPEGFileFormat.RCrTable[r2] + JPEGFileFormat.GCrTable[g2] + JPEGFileFormat.BCrTable[b2] >> 16);
                }
            }
        }
        this.compress(image, dataYComp, dataCbComp, dataCrComp);
    }
    
    byte[] convertYToRGB() {
        final int compWidth = this.frameComponents[this.componentIds[0]][3];
        final int bytesPerLine = ((this.imageWidth * 8 + 7) / 8 + 3) / 4 * 4;
        final byte[] data = new byte[bytesPerLine * this.imageHeight];
        final byte[] yComp = this.imageComponents[0];
        int destIndex = 0;
        for (int i = 0; i < this.imageHeight; ++i) {
            int srcIndex = i * compWidth;
            for (int j = 0; j < bytesPerLine; ++j) {
                int y = yComp[srcIndex] & 0xFF;
                if (y < 0) {
                    y = 0;
                }
                else if (y > 255) {
                    y = 255;
                }
                if (j >= this.imageWidth) {
                    y = 0;
                }
                data[destIndex] = (byte)y;
                ++srcIndex;
                ++destIndex;
            }
        }
        return data;
    }
    
    byte[] convertYCbCrToRGB() {
        final int bSize = this.imageWidth * this.imageHeight * this.nComponents;
        final byte[] rgbData = new byte[bSize];
        int destIndex = 0;
        this.expandImageComponents();
        final byte[] yComp = this.imageComponents[0];
        final byte[] cbComp = this.imageComponents[1];
        final byte[] crComp = this.imageComponents[2];
        final int compWidth = this.frameComponents[this.componentIds[0]][3];
        for (int v = 0; v < this.imageHeight; ++v) {
            int srcIndex = v * compWidth;
            for (int i = 0; i < this.imageWidth; ++i) {
                final int y = yComp[srcIndex] & 0xFF;
                final int cb = cbComp[srcIndex] & 0xFF;
                final int cr = crComp[srcIndex] & 0xFF;
                int r = y + JPEGFileFormat.CrRTable[cr];
                int g = y + (JPEGFileFormat.CbGTable[cb] + JPEGFileFormat.CrGTable[cr] >> 16);
                int b = y + JPEGFileFormat.CbBTable[cb];
                if (r < 0) {
                    r = 0;
                }
                else if (r > 255) {
                    r = 255;
                }
                if (g < 0) {
                    g = 0;
                }
                else if (g > 255) {
                    g = 255;
                }
                if (b < 0) {
                    b = 0;
                }
                else if (b > 255) {
                    b = 255;
                }
                rgbData[destIndex] = (byte)b;
                rgbData[destIndex + 1] = (byte)g;
                rgbData[destIndex + 2] = (byte)r;
                destIndex += 3;
                ++srcIndex;
            }
        }
        return rgbData;
    }
    
    void decodeACCoefficients(final int[] dataUnit, final int iComp) {
        final int[] sParams = this.scanHeader.componentParameters[this.componentIds[iComp]];
        final JPEGHuffmanTable acTable = this.acHuffmanTables[sParams[1]];
        int k = 1;
        while (k < 64) {
            final int rs = this.decodeUsingTable(acTable);
            final int r = rs >> 4;
            final int s = rs & 0xF;
            if (s == 0) {
                if (r != 15) {
                    break;
                }
                k += 16;
            }
            else {
                k += r;
                final int bits = this.receive(s);
                dataUnit[JPEGFileFormat.ZigZag8x8[k]] = this.extendBy(bits, s);
                ++k;
            }
        }
    }
    
    void decodeACFirstCoefficients(final int[] dataUnit, final int iComp, final int start, final int end, final int approxBit) {
        if (this.eobrun > 0) {
            --this.eobrun;
            return;
        }
        final int[] sParams = this.scanHeader.componentParameters[this.componentIds[iComp]];
        final JPEGHuffmanTable acTable = this.acHuffmanTables[sParams[1]];
        int k = start;
        while (k <= end) {
            final int rs = this.decodeUsingTable(acTable);
            final int r = rs >> 4;
            final int s = rs & 0xF;
            if (s == 0) {
                if (r != 15) {
                    this.eobrun = (1 << r) + this.receive(r) - 1;
                    break;
                }
                k += 16;
            }
            else {
                k += r;
                final int bits = this.receive(s);
                dataUnit[JPEGFileFormat.ZigZag8x8[k]] = this.extendBy(bits, s) << approxBit;
                ++k;
            }
        }
    }
    
    void decodeACRefineCoefficients(final int[] dataUnit, final int iComp, final int start, final int end, final int approxBit) {
        final int[] sParams = this.scanHeader.componentParameters[this.componentIds[iComp]];
        final JPEGHuffmanTable acTable = this.acHuffmanTables[sParams[1]];
        int k = start;
        while (k <= end) {
            if (this.eobrun > 0) {
                while (k <= end) {
                    final int zzIndex = JPEGFileFormat.ZigZag8x8[k];
                    if (dataUnit[zzIndex] != 0) {
                        dataUnit[zzIndex] = this.refineAC(dataUnit[zzIndex], approxBit);
                    }
                    ++k;
                }
                --this.eobrun;
            }
            else {
                final int rs = this.decodeUsingTable(acTable);
                final int r = rs >> 4;
                final int s = rs & 0xF;
                if (s == 0) {
                    if (r == 15) {
                        for (int zeros = 0; zeros < 16 && k <= end; ++k) {
                            final int zzIndex2 = JPEGFileFormat.ZigZag8x8[k];
                            if (dataUnit[zzIndex2] != 0) {
                                dataUnit[zzIndex2] = this.refineAC(dataUnit[zzIndex2], approxBit);
                            }
                            else {
                                ++zeros;
                            }
                        }
                    }
                    else {
                        this.eobrun = (1 << r) + this.receive(r);
                    }
                }
                else {
                    final int bit = this.receive(s);
                    int zeros2;
                    int zzIndex3;
                    for (zeros2 = 0, zzIndex3 = JPEGFileFormat.ZigZag8x8[k]; (zeros2 < r || dataUnit[zzIndex3] != 0) && k <= end; ++k, zzIndex3 = JPEGFileFormat.ZigZag8x8[k]) {
                        if (dataUnit[zzIndex3] != 0) {
                            dataUnit[zzIndex3] = this.refineAC(dataUnit[zzIndex3], approxBit);
                        }
                        else {
                            ++zeros2;
                        }
                    }
                    if (bit != 0) {
                        dataUnit[zzIndex3] = 1 << approxBit;
                    }
                    else {
                        dataUnit[zzIndex3] = -1 << approxBit;
                    }
                    ++k;
                }
            }
        }
    }
    
    int refineAC(int ac, final int approxBit) {
        if (ac > 0) {
            final int bit = this.nextBit();
            if (bit != 0) {
                ac += 1 << approxBit;
            }
        }
        else if (ac < 0) {
            final int bit = this.nextBit();
            if (bit != 0) {
                ac += -1 << approxBit;
            }
        }
        return ac;
    }
    
    void decodeDCCoefficient(final int[] dataUnit, final int iComp, final boolean first, final int approxBit) {
        final int[] sParams = this.scanHeader.componentParameters[this.componentIds[iComp]];
        final JPEGHuffmanTable dcTable = this.dcHuffmanTables[sParams[0]];
        int lastDC = 0;
        if (this.progressive && !first) {
            final int bit = this.nextBit();
            lastDC = dataUnit[0] + (bit << approxBit);
        }
        else {
            lastDC = this.precedingDCs[iComp];
            final int nBits = this.decodeUsingTable(dcTable);
            if (nBits != 0) {
                final int bits = this.receive(nBits);
                final int diff = this.extendBy(bits, nBits);
                lastDC += diff;
                this.precedingDCs[iComp] = lastDC;
            }
            if (this.progressive) {
                lastDC <<= approxBit;
            }
        }
        dataUnit[0] = lastDC;
    }
    
    void dequantize(final int[] dataUnit, final int iComp) {
        final int[] qTable = this.quantizationTables[this.frameComponents[this.componentIds[iComp]][0]];
        for (int i = 0; i < dataUnit.length; ++i) {
            final int n;
            final int zzIndex = n = JPEGFileFormat.ZigZag8x8[i];
            dataUnit[n] *= qTable[i];
        }
    }
    
    byte[] decodeImageComponents() {
        if (this.nComponents == 3) {
            return this.convertYCbCrToRGB();
        }
        if (this.nComponents == 4) {
            return this.convertCMYKToRGB();
        }
        return this.convertYToRGB();
    }
    
    void decodeMCUAtXAndY(final int xmcu, final int ymcu, final int nComponentsInScan, final boolean first, final int start, final int end, final int approxBit) {
        for (int iComp = 0; iComp < nComponentsInScan; ++iComp) {
            int scanComponent;
            for (scanComponent = iComp; this.scanHeader.componentParameters[this.componentIds[scanComponent]] == null; ++scanComponent) {}
            final int[] frameComponent = this.frameComponents[this.componentIds[scanComponent]];
            int hi = frameComponent[1];
            int vi = frameComponent[2];
            if (nComponentsInScan == 1) {
                hi = 1;
                vi = 1;
            }
            final int compWidth = frameComponent[3];
            for (int ivi = 0; ivi < vi; ++ivi) {
                for (int ihi = 0; ihi < hi; ++ihi) {
                    if (this.progressive) {
                        final int index = (ymcu * vi + ivi) * compWidth + xmcu * hi + ihi;
                        this.dataUnit = this.dataUnits[scanComponent][index];
                        if (this.dataUnit == null) {
                            this.dataUnit = new int[64];
                            this.dataUnits[scanComponent][index] = this.dataUnit;
                        }
                    }
                    else {
                        for (int i = 0; i < this.dataUnit.length; ++i) {
                            this.dataUnit[i] = 0;
                        }
                    }
                    if (!this.progressive || this.scanHeader.isDCProgressiveScan()) {
                        this.decodeDCCoefficient(this.dataUnit, scanComponent, first, approxBit);
                    }
                    if (!this.progressive) {
                        this.decodeACCoefficients(this.dataUnit, scanComponent);
                    }
                    else {
                        if (this.scanHeader.isACProgressiveScan()) {
                            if (first) {
                                this.decodeACFirstCoefficients(this.dataUnit, scanComponent, start, end, approxBit);
                            }
                            else {
                                this.decodeACRefineCoefficients(this.dataUnit, scanComponent, start, end, approxBit);
                            }
                        }
                        if (this.loader.hasListeners()) {
                            final int[] temp = this.dataUnit;
                            System.arraycopy(temp, 0, this.dataUnit = new int[64], 0, 64);
                        }
                    }
                    if (!this.progressive || (this.progressive && this.loader.hasListeners())) {
                        this.dequantize(this.dataUnit, scanComponent);
                        this.inverseDCT(this.dataUnit);
                        this.storeData(this.dataUnit, scanComponent, xmcu, ymcu, hi, ihi, vi, ivi);
                    }
                }
            }
        }
    }
    
    void decodeScan() {
        if (this.progressive && !this.scanHeader.verifyProgressiveScan()) {
            SWT.error(40);
        }
        final int nComponentsInScan = this.scanHeader.getNumberOfImageComponents();
        int mcuRowsInScan = this.interleavedMcuRows;
        int mcusPerRow = this.interleavedMcuCols;
        if (nComponentsInScan == 1) {
            int scanComponent;
            for (scanComponent = 0; this.scanHeader.componentParameters[this.componentIds[scanComponent]] == null; ++scanComponent) {}
            final int[] frameComponent = this.frameComponents[this.componentIds[scanComponent]];
            final int hi = frameComponent[1];
            final int vi = frameComponent[2];
            final int mcuWidth = 8 * this.maxH / hi;
            final int mcuHeight = 8 * this.maxV / vi;
            mcusPerRow = (this.imageWidth + mcuWidth - 1) / mcuWidth;
            mcuRowsInScan = (this.imageHeight + mcuHeight - 1) / mcuHeight;
        }
        final boolean first = this.scanHeader.isFirstScan();
        final int start = this.scanHeader.getStartOfSpectralSelection();
        final int end = this.scanHeader.getEndOfSpectralSelection();
        final int approxBit = this.scanHeader.getApproxBitPositionLow();
        this.restartsToGo = this.restartInterval;
        this.nextRestartNumber = 0;
        for (int ymcu = 0; ymcu < mcuRowsInScan; ++ymcu) {
            for (int xmcu = 0; xmcu < mcusPerRow; ++xmcu) {
                if (this.restartInterval != 0) {
                    if (this.restartsToGo == 0) {
                        this.processRestartInterval();
                    }
                    --this.restartsToGo;
                }
                this.decodeMCUAtXAndY(xmcu, ymcu, nComponentsInScan, first, start, end, approxBit);
            }
        }
    }
    
    int decodeUsingTable(final JPEGHuffmanTable huffmanTable) {
        int i = 0;
        final int[] maxCodes = huffmanTable.getDhMaxCodes();
        final int[] minCodes = huffmanTable.getDhMinCodes();
        final int[] valPtrs = huffmanTable.getDhValPtrs();
        final int[] huffVals = huffmanTable.getDhValues();
        int code;
        for (code = this.nextBit(); code > maxCodes[i]; code = code * 2 + this.nextBit(), ++i) {}
        final int j = valPtrs[i] + code - minCodes[i];
        return huffVals[j];
    }
    
    void emit(final int huffCode, final int nBits) {
        if (nBits == 0) {
            SWT.error(40);
        }
        final int[] power2m1 = { 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095, 8191, 16383, 32767, 65535, 131125 };
        final int code = (huffCode & power2m1[nBits - 1]) << 24 - nBits - this.currentBitCount;
        final byte[] codeBuffer = { (byte)(code & 0xFF), (byte)(code >> 8 & 0xFF), (byte)(code >> 16 & 0xFF), (byte)(code >> 24 & 0xFF) };
        int abs = nBits - (8 - this.currentBitCount);
        if (abs < 0) {
            abs = -abs;
        }
        if (abs >> 3 > 0) {
            this.currentByte += codeBuffer[2];
            this.emitByte((byte)this.currentByte);
            this.emitByte(codeBuffer[1]);
            this.currentByte = codeBuffer[0];
            this.currentBitCount += nBits - 16;
        }
        else {
            this.currentBitCount += nBits;
            if (this.currentBitCount >= 8) {
                this.currentByte += codeBuffer[2];
                this.emitByte((byte)this.currentByte);
                this.currentByte = codeBuffer[1];
                this.currentBitCount -= 8;
            }
            else {
                this.currentByte += codeBuffer[2];
            }
        }
    }
    
    void emitByte(final byte byteValue) {
        if (this.bufferCurrentPosition >= 512) {
            this.resetOutputBuffer();
        }
        this.dataBuffer[this.bufferCurrentPosition] = byteValue;
        ++this.bufferCurrentPosition;
        if (byteValue == -1) {
            this.emitByte((byte)0);
        }
    }
    
    void encodeACCoefficients(final int[] dataUnit, final int iComp) {
        final int[] sParams = this.scanHeader.componentParameters[iComp];
        final JPEGHuffmanTable acTable = this.acHuffmanTables[sParams[1]];
        final int[] ehCodes = acTable.ehCodes;
        final byte[] ehSizes = acTable.ehCodeLengths;
        int r = 0;
        int k = 1;
        while (k < 64) {
            ++k;
            final int acValue = dataUnit[JPEGFileFormat.ZigZag8x8[k - 1]];
            if (acValue == 0) {
                if (k == 64) {
                    this.emit(ehCodes[0], ehSizes[0] & 0xFF);
                }
                else {
                    ++r;
                }
            }
            else {
                while (r > 15) {
                    this.emit(ehCodes[240], ehSizes[240] & 0xFF);
                    r -= 16;
                }
                if (acValue < 0) {
                    int absACValue = acValue;
                    if (absACValue < 0) {
                        absACValue = -absACValue;
                    }
                    final int nBits = JPEGFileFormat.NBitsTable[absACValue];
                    final int rs = r * 16 + nBits;
                    this.emit(ehCodes[rs], ehSizes[rs] & 0xFF);
                    this.emit(16777215 - absACValue, nBits);
                }
                else {
                    final int nBits2 = JPEGFileFormat.NBitsTable[acValue];
                    final int rs2 = r * 16 + nBits2;
                    this.emit(ehCodes[rs2], ehSizes[rs2] & 0xFF);
                    this.emit(acValue, nBits2);
                }
                r = 0;
            }
        }
    }
    
    void encodeDCCoefficients(final int[] dataUnit, final int iComp) {
        final int[] sParams = this.scanHeader.componentParameters[iComp];
        final JPEGHuffmanTable dcTable = this.dcHuffmanTables[sParams[0]];
        final int lastDC = this.precedingDCs[iComp];
        final int dcValue = dataUnit[0];
        final int diff = dcValue - lastDC;
        this.precedingDCs[iComp] = dcValue;
        if (diff < 0) {
            final int absDiff = 0 - diff;
            final int nBits = JPEGFileFormat.NBitsTable[absDiff];
            this.emit(dcTable.ehCodes[nBits], dcTable.ehCodeLengths[nBits]);
            this.emit(16777215 - absDiff, nBits);
        }
        else {
            final int nBits2 = JPEGFileFormat.NBitsTable[diff];
            this.emit(dcTable.ehCodes[nBits2], dcTable.ehCodeLengths[nBits2]);
            if (nBits2 != 0) {
                this.emit(diff, nBits2);
            }
        }
    }
    
    void encodeMCUAtXAndY(final int xmcu, final int ymcu) {
        final int nComponentsInScan = this.scanHeader.getNumberOfImageComponents();
        this.dataUnit = new int[64];
        for (int iComp = 0; iComp < nComponentsInScan; ++iComp) {
            final int[] frameComponent = this.frameComponents[this.componentIds[iComp]];
            final int hi = frameComponent[1];
            for (int vi = frameComponent[2], ivi = 0; ivi < vi; ++ivi) {
                for (int ihi = 0; ihi < hi; ++ihi) {
                    this.extractData(this.dataUnit, iComp, xmcu, ymcu, ihi, ivi);
                    this.forwardDCT(this.dataUnit);
                    this.quantizeData(this.dataUnit, iComp);
                    this.encodeDCCoefficients(this.dataUnit, iComp);
                    this.encodeACCoefficients(this.dataUnit, iComp);
                }
            }
        }
    }
    
    void encodeScan() {
        for (int ymcu = 0; ymcu < this.interleavedMcuRows; ++ymcu) {
            for (int xmcu = 0; xmcu < this.interleavedMcuCols; ++xmcu) {
                this.encodeMCUAtXAndY(xmcu, ymcu);
            }
        }
        if (this.currentBitCount != 0) {
            this.emitByte((byte)this.currentByte);
        }
        this.resetOutputBuffer();
    }
    
    void expandImageComponents() {
        for (int iComp = 0; iComp < this.nComponents; ++iComp) {
            final int[] frameComponent = this.frameComponents[this.componentIds[iComp]];
            final int hi = frameComponent[1];
            final int vi = frameComponent[2];
            final int upH = this.maxH / hi;
            final int upV = this.maxV / vi;
            if (upH * upV > 1) {
                final byte[] component = this.imageComponents[iComp];
                final int compWidth = frameComponent[3];
                final int compHeight = frameComponent[4];
                final int upCompWidth = compWidth * upH;
                final int upCompHeight = compHeight * upV;
                final ImageData src = new ImageData(compWidth, compHeight, 8, new PaletteData(JPEGFileFormat.RGB16), 4, component);
                final ImageData dest = src.scaledTo(upCompWidth, upCompHeight);
                this.imageComponents[iComp] = dest.data;
            }
        }
    }
    
    int extendBy(final int diff, final int t) {
        if (diff < JPEGFileFormat.ExtendTest[t]) {
            return diff + JPEGFileFormat.ExtendOffset[t];
        }
        return diff;
    }
    
    void extractData(final int[] dataUnit, final int iComp, final int xmcu, final int ymcu, final int ihi, final int ivi) {
        final byte[] compImage = this.imageComponents[iComp];
        final int[] frameComponent = this.frameComponents[this.componentIds[iComp]];
        final int hi = frameComponent[1];
        final int vi = frameComponent[2];
        final int compWidth = frameComponent[3];
        int srcIndex = (ymcu * vi + ivi) * compWidth * 8 + (xmcu * hi + ihi) * 8;
        int destIndex = 0;
        for (int i = 0; i < 8; ++i) {
            for (int col = 0; col < 8; ++col) {
                dataUnit[destIndex] = (compImage[srcIndex + col] & 0xFF) - 128;
                ++destIndex;
            }
            srcIndex += compWidth;
        }
    }
    
    void forwardDCT(final int[] dataUnit) {
        for (int row = 0; row < 8; ++row) {
            final int rIndex = row * 8;
            final int tmp0 = dataUnit[rIndex] + dataUnit[rIndex + 7];
            int tmp2 = dataUnit[rIndex] - dataUnit[rIndex + 7];
            final int tmp3 = dataUnit[rIndex + 1] + dataUnit[rIndex + 6];
            int tmp4 = dataUnit[rIndex + 1] - dataUnit[rIndex + 6];
            final int tmp5 = dataUnit[rIndex + 2] + dataUnit[rIndex + 5];
            int tmp6 = dataUnit[rIndex + 2] - dataUnit[rIndex + 5];
            final int tmp7 = dataUnit[rIndex + 3] + dataUnit[rIndex + 4];
            int tmp8 = dataUnit[rIndex + 3] - dataUnit[rIndex + 4];
            final int tmp9 = tmp0 + tmp7;
            final int tmp10 = tmp0 - tmp7;
            final int tmp11 = tmp3 + tmp5;
            final int tmp12 = tmp3 - tmp5;
            dataUnit[rIndex] = (tmp9 + tmp11) * 4;
            dataUnit[rIndex + 4] = (tmp9 - tmp11) * 4;
            int z1 = (tmp12 + tmp10) * 4433;
            int n = z1 + tmp10 * 6270 + 1024;
            dataUnit[rIndex + 2] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n17;
                final int n2 = n17 = rIndex + 2;
                --dataUnit[n17];
            }
            n = z1 + tmp12 * -15137 + 1024;
            dataUnit[rIndex + 6] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n18;
                final int n3 = n18 = rIndex + 6;
                --dataUnit[n18];
            }
            z1 = tmp8 + tmp2;
            int z2 = tmp6 + tmp4;
            int z3 = tmp8 + tmp4;
            int z4 = tmp6 + tmp2;
            final int z5 = (z3 + z4) * 9633;
            tmp8 *= 2446;
            tmp6 *= 16819;
            tmp4 *= 25172;
            tmp2 *= 12299;
            z1 *= -7373;
            z2 *= -20995;
            z3 *= -16069;
            z4 *= -3196;
            z3 += z5;
            z4 += z5;
            n = tmp8 + z1 + z3 + 1024;
            dataUnit[rIndex + 7] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n19;
                final int n4 = n19 = rIndex + 7;
                --dataUnit[n19];
            }
            n = tmp6 + z2 + z4 + 1024;
            dataUnit[rIndex + 5] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n20;
                final int n5 = n20 = rIndex + 5;
                --dataUnit[n20];
            }
            n = tmp4 + z2 + z3 + 1024;
            dataUnit[rIndex + 3] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n21;
                final int n6 = n21 = rIndex + 3;
                --dataUnit[n21];
            }
            n = tmp2 + z1 + z4 + 1024;
            dataUnit[rIndex + 1] = n >> 11;
            if (n < 0 && (n & 0x7FF) != 0x0) {
                final int n22;
                final int n7 = n22 = rIndex + 1;
                --dataUnit[n22];
            }
        }
        for (int col = 0; col < 8; ++col) {
            final int c0 = col;
            final int c2 = col + 8;
            final int c3 = col + 16;
            final int c4 = col + 24;
            final int c5 = col + 32;
            final int c6 = col + 40;
            final int c7 = col + 48;
            final int c8 = col + 56;
            final int tmp13 = dataUnit[c0] + dataUnit[c8];
            int tmp14 = dataUnit[c0] - dataUnit[c8];
            final int tmp15 = dataUnit[c2] + dataUnit[c7];
            int tmp16 = dataUnit[c2] - dataUnit[c7];
            final int tmp17 = dataUnit[c3] + dataUnit[c6];
            int tmp18 = dataUnit[c3] - dataUnit[c6];
            final int tmp19 = dataUnit[c4] + dataUnit[c5];
            int tmp20 = dataUnit[c4] - dataUnit[c5];
            final int tmp21 = tmp13 + tmp19;
            final int tmp22 = tmp13 - tmp19;
            final int tmp23 = tmp15 + tmp17;
            final int tmp24 = tmp15 - tmp17;
            int n8 = tmp21 + tmp23 + 16;
            dataUnit[c0] = n8 >> 5;
            if (n8 < 0 && (n8 & 0x1F) != 0x0) {
                final int n23;
                final int n9 = n23 = c0;
                --dataUnit[n23];
            }
            n8 = tmp21 - tmp23 + 16;
            dataUnit[c5] = n8 >> 5;
            if (n8 < 0 && (n8 & 0x1F) != 0x0) {
                final int n24;
                final int n10 = n24 = c5;
                --dataUnit[n24];
            }
            int z6 = (tmp24 + tmp22) * 4433;
            n8 = z6 + tmp22 * 6270 + 131072;
            dataUnit[c3] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n25;
                final int n11 = n25 = c3;
                --dataUnit[n25];
            }
            n8 = z6 + tmp24 * -15137 + 131072;
            dataUnit[c7] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n26;
                final int n12 = n26 = c7;
                --dataUnit[n26];
            }
            z6 = tmp20 + tmp14;
            int z7 = tmp18 + tmp16;
            int z8 = tmp20 + tmp16;
            int z9 = tmp18 + tmp14;
            final int z10 = (z8 + z9) * 9633;
            tmp20 *= 2446;
            tmp18 *= 16819;
            tmp16 *= 25172;
            tmp14 *= 12299;
            z6 *= -7373;
            z7 *= -20995;
            z8 *= -16069;
            z9 *= -3196;
            z8 += z10;
            z9 += z10;
            n8 = tmp20 + z6 + z8 + 131072;
            dataUnit[c8] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n27;
                final int n13 = n27 = c8;
                --dataUnit[n27];
            }
            n8 = tmp18 + z7 + z9 + 131072;
            dataUnit[c6] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n28;
                final int n14 = n28 = c6;
                --dataUnit[n28];
            }
            n8 = tmp16 + z7 + z8 + 131072;
            dataUnit[c4] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n29;
                final int n15 = n29 = c4;
                --dataUnit[n29];
            }
            n8 = tmp14 + z6 + z9 + 131072;
            dataUnit[c2] = n8 >> 18;
            if (n8 < 0 && (n8 & 0x3FFFF) != 0x0) {
                final int n30;
                final int n16 = n30 = c2;
                --dataUnit[n30];
            }
        }
    }
    
    void getAPP0() {
        final JPEGAppn appn = new JPEGAppn(this.inputStream);
        if (!appn.verify()) {
            SWT.error(40);
        }
    }
    
    void getCOM() {
        new JPEGComment(this.inputStream);
    }
    
    void getDAC() {
        new JPEGArithmeticConditioningTable(this.inputStream);
    }
    
    void getDHT() {
        final JPEGHuffmanTable dht = new JPEGHuffmanTable(this.inputStream);
        if (!dht.verify()) {
            SWT.error(40);
        }
        if (this.acHuffmanTables == null) {
            this.acHuffmanTables = new JPEGHuffmanTable[4];
        }
        if (this.dcHuffmanTables == null) {
            this.dcHuffmanTables = new JPEGHuffmanTable[4];
        }
        for (final JPEGHuffmanTable dhtTable : dht.getAllTables()) {
            if (dhtTable.getTableClass() == 0) {
                this.dcHuffmanTables[dhtTable.getTableIdentifier()] = dhtTable;
            }
            else {
                this.acHuffmanTables[dhtTable.getTableIdentifier()] = dhtTable;
            }
        }
    }
    
    void getDNL() {
        new JPEGRestartInterval(this.inputStream);
    }
    
    void getDQT() {
        final JPEGQuantizationTable dqt = new JPEGQuantizationTable(this.inputStream);
        int[][] currentTables = this.quantizationTables;
        if (currentTables == null) {
            currentTables = new int[4][];
        }
        final int[] dqtTablesKeys = dqt.getQuantizationTablesKeys();
        final int[][] dqtTablesValues = dqt.getQuantizationTablesValues();
        for (int i = 0; i < dqtTablesKeys.length; ++i) {
            final int index = dqtTablesKeys[i];
            currentTables[index] = dqtTablesValues[i];
        }
        this.quantizationTables = currentTables;
    }
    
    void getDRI() {
        final JPEGRestartInterval dri = new JPEGRestartInterval(this.inputStream);
        if (!dri.verify()) {
            SWT.error(40);
        }
        this.restartInterval = dri.getRestartInterval();
    }
    
    void inverseDCT(final int[] dataUnit) {
        for (int row = 0; row < 8; ++row) {
            final int rIndex = row * 8;
            if (this.isZeroInRow(dataUnit, rIndex)) {
                final int dcVal = dataUnit[rIndex] << 2;
                for (int i = rIndex + 7; i >= rIndex; --i) {
                    dataUnit[i] = dcVal;
                }
            }
            else {
                int z2 = dataUnit[rIndex + 2];
                int z3 = dataUnit[rIndex + 6];
                int z4 = (z2 + z3) * 4433;
                int tmp2 = z4 + z3 * -15137;
                int tmp3 = z4 + z2 * 6270;
                int tmp4 = dataUnit[rIndex] + dataUnit[rIndex + 4] << 13;
                int tmp5 = dataUnit[rIndex] - dataUnit[rIndex + 4] << 13;
                final int tmp6 = tmp4 + tmp3;
                final int tmp7 = tmp4 - tmp3;
                final int tmp8 = tmp5 + tmp2;
                final int tmp9 = tmp5 - tmp2;
                tmp4 = dataUnit[rIndex + 7];
                tmp5 = dataUnit[rIndex + 5];
                tmp2 = dataUnit[rIndex + 3];
                tmp3 = dataUnit[rIndex + 1];
                z4 = tmp4 + tmp3;
                z2 = tmp5 + tmp2;
                z3 = tmp4 + tmp2;
                int z5 = tmp5 + tmp3;
                final int z6 = (z3 + z5) * 9633;
                tmp4 *= 2446;
                tmp5 *= 16819;
                tmp2 *= 25172;
                tmp3 *= 12299;
                z4 *= -7373;
                z2 *= -20995;
                z3 *= -16069;
                z5 *= -3196;
                z3 += z6;
                z5 += z6;
                tmp4 += z4 + z3;
                tmp5 += z2 + z5;
                tmp2 += z2 + z3;
                tmp3 += z4 + z5;
                dataUnit[rIndex] = tmp6 + tmp3 + 1024 >> 11;
                dataUnit[rIndex + 7] = tmp6 - tmp3 + 1024 >> 11;
                dataUnit[rIndex + 1] = tmp8 + tmp2 + 1024 >> 11;
                dataUnit[rIndex + 6] = tmp8 - tmp2 + 1024 >> 11;
                dataUnit[rIndex + 2] = tmp9 + tmp5 + 1024 >> 11;
                dataUnit[rIndex + 5] = tmp9 - tmp5 + 1024 >> 11;
                dataUnit[rIndex + 3] = tmp7 + tmp4 + 1024 >> 11;
                dataUnit[rIndex + 4] = tmp7 - tmp4 + 1024 >> 11;
            }
        }
        for (int col = 0; col < 8; ++col) {
            final int c0 = col;
            final int c2 = col + 8;
            final int c3 = col + 16;
            final int c4 = col + 24;
            final int c5 = col + 32;
            final int c6 = col + 40;
            final int c7 = col + 48;
            final int c8 = col + 56;
            if (this.isZeroInColumn(dataUnit, col)) {
                final int dcVal2 = dataUnit[c0] + 16 >> 5;
                dataUnit[c2] = (dataUnit[c0] = dcVal2);
                dataUnit[c4] = (dataUnit[c3] = dcVal2);
                dataUnit[c6] = (dataUnit[c5] = dcVal2);
                dataUnit[c8] = (dataUnit[c7] = dcVal2);
            }
            else {
                int z7 = dataUnit[c0];
                int z8 = dataUnit[c3];
                int z9 = dataUnit[c7];
                int z10 = dataUnit[c5];
                int z11 = (z8 + z9) * 4433;
                int tmp10 = z11 + z9 * -15137;
                int tmp11 = z11 + z8 * 6270;
                int tmp12 = z7 + z10 << 13;
                int tmp13 = z7 - z10 << 13;
                final int tmp14 = tmp12 + tmp11;
                final int tmp15 = tmp12 - tmp11;
                final int tmp16 = tmp13 + tmp10;
                final int tmp17 = tmp13 - tmp10;
                tmp12 = dataUnit[c8];
                tmp13 = dataUnit[c6];
                tmp10 = dataUnit[c4];
                tmp11 = dataUnit[c2];
                z11 = tmp12 + tmp11;
                z8 = tmp13 + tmp10;
                z9 = tmp12 + tmp10;
                z10 = tmp13 + tmp11;
                z7 = (z9 + z10) * 9633;
                tmp12 *= 2446;
                tmp13 *= 16819;
                tmp10 *= 25172;
                tmp11 *= 12299;
                z11 *= -7373;
                z8 *= -20995;
                z9 *= -16069;
                z10 *= -3196;
                z9 += z7;
                z10 += z7;
                tmp12 += z11 + z9;
                tmp13 += z8 + z10;
                tmp10 += z8 + z9;
                tmp11 += z11 + z10;
                dataUnit[c0] = tmp14 + tmp11 + 131072 >> 18;
                dataUnit[c8] = tmp14 - tmp11 + 131072 >> 18;
                dataUnit[c2] = tmp16 + tmp10 + 131072 >> 18;
                dataUnit[c7] = tmp16 - tmp10 + 131072 >> 18;
                dataUnit[c3] = tmp17 + tmp13 + 131072 >> 18;
                dataUnit[c6] = tmp17 - tmp13 + 131072 >> 18;
                dataUnit[c4] = tmp15 + tmp12 + 131072 >> 18;
                dataUnit[c5] = tmp15 - tmp12 + 131072 >> 18;
            }
        }
    }
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final JPEGStartOfImage soi = new JPEGStartOfImage(stream);
            stream.unread(soi.reference);
            return soi.verify();
        }
        catch (Exception e) {
            return false;
        }
    }
    
    boolean isZeroInColumn(final int[] dataUnit, final int col) {
        return dataUnit[col + 8] == 0 && dataUnit[col + 16] == 0 && dataUnit[col + 24] == 0 && dataUnit[col + 32] == 0 && dataUnit[col + 40] == 0 && dataUnit[col + 48] == 0 && dataUnit[col + 56] == 0;
    }
    
    boolean isZeroInRow(final int[] dataUnit, final int rIndex) {
        return dataUnit[rIndex + 1] == 0 && dataUnit[rIndex + 2] == 0 && dataUnit[rIndex + 3] == 0 && dataUnit[rIndex + 4] == 0 && dataUnit[rIndex + 5] == 0 && dataUnit[rIndex + 6] == 0 && dataUnit[rIndex + 7] == 0;
    }
    
    ImageData[] loadFromByteStream() {
        if (System.getProperty("org.eclipse.swt.internal.image.JPEGFileFormat_3.2") == null) {
            return JPEGDecoder.loadFromByteStream((InputStream)this.inputStream, this.loader);
        }
        final JPEGStartOfImage soi = new JPEGStartOfImage(this.inputStream);
        if (!soi.verify()) {
            SWT.error(40);
        }
        this.restartInterval = 0;
        this.processTables();
        this.frameHeader = new JPEGFrameHeader(this.inputStream);
        if (!this.frameHeader.verify()) {
            SWT.error(40);
        }
        this.imageWidth = this.frameHeader.getSamplesPerLine();
        this.imageHeight = this.frameHeader.getNumberOfLines();
        this.maxH = this.frameHeader.getMaxHFactor();
        this.maxV = this.frameHeader.getMaxVFactor();
        final int mcuWidth = this.maxH * 8;
        final int mcuHeight = this.maxV * 8;
        this.interleavedMcuCols = (this.imageWidth + mcuWidth - 1) / mcuWidth;
        this.interleavedMcuRows = (this.imageHeight + mcuHeight - 1) / mcuHeight;
        this.progressive = this.frameHeader.isProgressive();
        this.samplePrecision = this.frameHeader.getSamplePrecision();
        this.nComponents = this.frameHeader.getNumberOfImageComponents();
        this.frameComponents = this.frameHeader.componentParameters;
        this.componentIds = this.frameHeader.componentIdentifiers;
        this.imageComponents = new byte[this.nComponents][];
        if (this.progressive) {
            this.dataUnits = new int[this.nComponents][][];
        }
        else {
            this.dataUnit = new int[64];
        }
        for (int i = 0; i < this.nComponents; ++i) {
            final int[] frameComponent = this.frameComponents[this.componentIds[i]];
            final int bufferSize = frameComponent[3] * frameComponent[4];
            this.imageComponents[i] = new byte[bufferSize];
            if (this.progressive) {
                this.dataUnits[i] = new int[bufferSize][];
            }
        }
        this.processTables();
        this.scanHeader = new JPEGScanHeader(this.inputStream);
        if (!this.scanHeader.verify()) {
            SWT.error(40);
        }
        int progressiveScanCount = 0;
        boolean done = false;
        while (!done) {
            this.resetInputBuffer();
            this.precedingDCs = new int[4];
            this.decodeScan();
            if (this.progressive && this.loader.hasListeners()) {
                final ImageData imageData = this.createImageData();
                this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageData, progressiveScanCount, false));
                ++progressiveScanCount;
            }
            final int delta = 512 - this.bufferCurrentPosition - 1;
            if (delta > 0) {
                final byte[] unreadBuffer = new byte[delta];
                System.arraycopy(this.dataBuffer, this.bufferCurrentPosition + 1, unreadBuffer, 0, delta);
                try {
                    this.inputStream.unread(unreadBuffer);
                }
                catch (IOException e) {
                    SWT.error(39, e);
                }
            }
            final JPEGSegment jpegSegment = this.processTables();
            if (jpegSegment == null || jpegSegment.getSegmentMarker() == 65497) {
                done = true;
            }
            else {
                this.scanHeader = new JPEGScanHeader(this.inputStream);
                if (this.scanHeader.verify()) {
                    continue;
                }
                SWT.error(40);
            }
        }
        if (this.progressive) {
            for (int ymcu = 0; ymcu < this.interleavedMcuRows; ++ymcu) {
                for (int xmcu = 0; xmcu < this.interleavedMcuCols; ++xmcu) {
                    for (int iComp = 0; iComp < this.nComponents; ++iComp) {
                        final int[] frameComponent2 = this.frameComponents[this.componentIds[iComp]];
                        final int hi = frameComponent2[1];
                        final int vi = frameComponent2[2];
                        final int compWidth = frameComponent2[3];
                        for (int ivi = 0; ivi < vi; ++ivi) {
                            for (int ihi = 0; ihi < hi; ++ihi) {
                                final int index = (ymcu * vi + ivi) * compWidth + xmcu * hi + ihi;
                                this.dequantize(this.dataUnit = this.dataUnits[iComp][index], iComp);
                                this.inverseDCT(this.dataUnit);
                                this.storeData(this.dataUnit, iComp, xmcu, ymcu, hi, ihi, vi, ivi);
                            }
                        }
                    }
                }
            }
            this.dataUnits = null;
        }
        final ImageData imageData = this.createImageData();
        if (this.progressive && this.loader.hasListeners()) {
            this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageData, progressiveScanCount, true));
        }
        return new ImageData[] { imageData };
    }
    
    ImageData createImageData() {
        return ImageData.internal_new(this.imageWidth, this.imageHeight, this.nComponents * this.samplePrecision, this.setUpPalette(), (this.nComponents == 1) ? 4 : 1, this.decodeImageComponents(), 0, (byte[])null, (byte[])null, -1, -1, 4, 0, 0, 0, 0);
    }
    
    int nextBit() {
        if (this.currentBitCount != 0) {
            --this.currentBitCount;
            this.currentByte *= 2;
            if (this.currentByte > 255) {
                this.currentByte -= 256;
                return 1;
            }
            return 0;
        }
        else {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition >= 512) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
            this.currentBitCount = 8;
            byte nextByte;
            if (this.bufferCurrentPosition == 511) {
                this.resetInputBuffer();
                this.currentBitCount = 8;
                nextByte = this.dataBuffer[0];
            }
            else {
                nextByte = this.dataBuffer[this.bufferCurrentPosition + 1];
            }
            if (this.currentByte == 255) {
                if (nextByte == 0) {
                    ++this.bufferCurrentPosition;
                    --this.currentBitCount;
                    this.currentByte *= 2;
                    if (this.currentByte > 255) {
                        this.currentByte -= 256;
                        return 1;
                    }
                    return 0;
                }
                else {
                    if ((nextByte & 0xFF) + 65280 == 65500) {
                        this.getDNL();
                        return 0;
                    }
                    SWT.error(40);
                    return 0;
                }
            }
            else {
                --this.currentBitCount;
                this.currentByte *= 2;
                if (this.currentByte > 255) {
                    this.currentByte -= 256;
                    return 1;
                }
                return 0;
            }
        }
    }
    
    void processRestartInterval() {
        do {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition > 511) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        } while (this.currentByte != 255);
        while (this.currentByte == 255) {
            ++this.bufferCurrentPosition;
            if (this.bufferCurrentPosition > 511) {
                this.resetInputBuffer();
                this.bufferCurrentPosition = 0;
            }
            this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        }
        if (this.currentByte != (65488 + this.nextRestartNumber & 0xFF)) {
            SWT.error(40);
        }
        ++this.bufferCurrentPosition;
        if (this.bufferCurrentPosition > 511) {
            this.resetInputBuffer();
            this.bufferCurrentPosition = 0;
        }
        this.currentByte = (this.dataBuffer[this.bufferCurrentPosition] & 0xFF);
        this.currentBitCount = 8;
        this.restartsToGo = this.restartInterval;
        this.nextRestartNumber = (this.nextRestartNumber + 1 & 0x7);
        this.precedingDCs = new int[4];
        this.eobrun = 0;
    }
    
    JPEGSegment processTables() {
        JPEGSegment jpegSegment = null;
    Label_0129:
        while (true) {
            jpegSegment = seekUnspecifiedMarker(this.inputStream);
            if (jpegSegment == null) {
                return null;
            }
            final JPEGFrameHeader sof = new JPEGFrameHeader(jpegSegment.reference);
            if (sof.verify()) {
                return jpegSegment;
            }
            final int marker = jpegSegment.getSegmentMarker();
            switch (marker) {
                case 65496: {
                    SWT.error(40);
                }
                case 65497:
                case 65498: {
                    break Label_0129;
                }
                case 65499: {
                    this.getDQT();
                    continue;
                }
                case 65476: {
                    this.getDHT();
                    continue;
                }
                case 65484: {
                    this.getDAC();
                    continue;
                }
                case 65501: {
                    this.getDRI();
                    continue;
                }
                case 65504: {
                    this.getAPP0();
                    continue;
                }
                case 65534: {
                    this.getCOM();
                    continue;
                }
                default: {
                    skipSegmentFrom(this.inputStream);
                    continue;
                }
            }
        }
        return jpegSegment;
    }
    
    void quantizeData(final int[] dataUnit, final int iComp) {
        final int[] qTable = this.quantizationTables[this.frameComponents[this.componentIds[iComp]][0]];
        for (int i = 0; i < dataUnit.length; ++i) {
            final int zzIndex = JPEGFileFormat.ZigZag8x8[i];
            final int data = dataUnit[zzIndex];
            int absData = (data < 0) ? (0 - data) : data;
            final int qValue = qTable[i];
            final int q2 = qValue >> 1;
            absData += q2;
            if (absData < qValue) {
                dataUnit[zzIndex] = 0;
            }
            else {
                absData /= qValue;
                if (data >= 0) {
                    dataUnit[zzIndex] = absData;
                }
                else {
                    dataUnit[zzIndex] = 0 - absData;
                }
            }
        }
    }
    
    int receive(final int nBits) {
        int v = 0;
        for (int i = 0; i < nBits; ++i) {
            v = v * 2 + this.nextBit();
        }
        return v;
    }
    
    void resetInputBuffer() {
        if (this.dataBuffer == null) {
            this.dataBuffer = new byte[512];
        }
        try {
            this.inputStream.read(this.dataBuffer);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        this.currentBitCount = 0;
        this.bufferCurrentPosition = -1;
    }
    
    void resetOutputBuffer() {
        if (this.dataBuffer == null) {
            this.dataBuffer = new byte[512];
        }
        else {
            try {
                this.outputStream.write(this.dataBuffer, 0, this.bufferCurrentPosition);
            }
            catch (IOException e) {
                SWT.error(39, e);
            }
        }
        this.bufferCurrentPosition = 0;
    }
    
    static JPEGSegment seekUnspecifiedMarker(final LEDataInputStream byteStream) {
        final byte[] byteArray = new byte[2];
        try {
            while (byteStream.read(byteArray, 0, 1) == 1) {
                if (byteArray[0] == -1) {
                    if (byteStream.read(byteArray, 1, 1) != 1) {
                        return null;
                    }
                    if (byteArray[1] != -1 && byteArray[1] != 0) {
                        byteStream.unread(byteArray);
                        return new JPEGSegment(byteArray);
                    }
                    continue;
                }
            }
            return null;
        }
        catch (IOException e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    PaletteData setUpPalette() {
        if (this.nComponents == 1) {
            final RGB[] entries = new RGB[256];
            for (int i = 0; i < 256; ++i) {
                entries[i] = new RGB(i, i, i);
            }
            return new PaletteData(entries);
        }
        return new PaletteData(255, 65280, 16711680);
    }
    
    static void skipSegmentFrom(final LEDataInputStream byteStream) {
        try {
            final byte[] byteArray = new byte[4];
            final JPEGSegment jpegSegment = new JPEGSegment(byteArray);
            if (byteStream.read(byteArray) != byteArray.length) {
                SWT.error(40);
            }
            if (byteArray[0] != -1 || byteArray[1] == 0 || byteArray[1] == -1) {
                SWT.error(40);
            }
            final int delta = jpegSegment.getSegmentLength() - 2;
            byteStream.skip(delta);
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
    }
    
    void storeData(final int[] dataUnit, final int iComp, final int xmcu, final int ymcu, final int hi, final int ihi, final int vi, final int ivi) {
        final byte[] compImage = this.imageComponents[iComp];
        final int[] frameComponent = this.frameComponents[this.componentIds[iComp]];
        final int compWidth = frameComponent[3];
        int destIndex = (ymcu * vi + ivi) * compWidth * 8 + (xmcu * hi + ihi) * 8;
        int srcIndex = 0;
        for (int i = 0; i < 8; ++i) {
            for (int col = 0; col < 8; ++col) {
                int x = dataUnit[srcIndex] + 128;
                if (x < 0) {
                    x = 0;
                }
                else if (x > 255) {
                    x = 255;
                }
                compImage[destIndex + col] = (byte)x;
                ++srcIndex;
            }
            destIndex += compWidth;
        }
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final ImageData image = loader.data[0];
        if (!new JPEGStartOfImage().writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        final JPEGAppn appn = new JPEGAppn(new byte[] { -1, -32, 0, 16, 74, 70, 73, 70, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0 });
        if (!appn.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.quantizationTables = new int[4][];
        final JPEGQuantizationTable chromDQT = JPEGQuantizationTable.defaultChrominanceTable();
        final int encoderQFactor = (loader.compression >= 1 && loader.compression <= 100) ? loader.compression : 75;
        chromDQT.scaleBy(encoderQFactor);
        int[] jpegDQTKeys = chromDQT.getQuantizationTablesKeys();
        int[][] jpegDQTValues = chromDQT.getQuantizationTablesValues();
        for (int i = 0; i < jpegDQTKeys.length; ++i) {
            this.quantizationTables[jpegDQTKeys[i]] = jpegDQTValues[i];
        }
        final JPEGQuantizationTable lumDQT = JPEGQuantizationTable.defaultLuminanceTable();
        lumDQT.scaleBy(encoderQFactor);
        jpegDQTKeys = lumDQT.getQuantizationTablesKeys();
        jpegDQTValues = lumDQT.getQuantizationTablesValues();
        for (int j = 0; j < jpegDQTKeys.length; ++j) {
            this.quantizationTables[jpegDQTKeys[j]] = jpegDQTValues[j];
        }
        if (!lumDQT.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        if (!chromDQT.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        int frameLength;
        int[][] frameParams;
        int[][] scanParams;
        int scanLength;
        int precision;
        if (image.depth == 1) {
            frameLength = 11;
            frameParams = new int[][] { { 1, 1, 1, 0, 0 } };
            scanParams = new int[][] { { 0, 0 } };
            scanLength = 8;
            this.nComponents = 1;
            precision = 1;
        }
        else {
            frameLength = 17;
            frameParams = new int[][] { { 0, 2, 2, 0, 0 }, { 1, 1, 1, 0, 0 }, { 1, 1, 1, 0, 0 } };
            scanParams = new int[][] { { 0, 0 }, { 1, 1 }, { 1, 1 } };
            scanLength = 12;
            this.nComponents = 3;
            precision = 8;
        }
        this.imageWidth = image.width;
        this.imageHeight = image.height;
        (this.frameHeader = new JPEGFrameHeader(new byte[19])).setSegmentMarker(65472);
        this.frameHeader.setSegmentLength(frameLength);
        this.frameHeader.setSamplePrecision(precision);
        this.frameHeader.setSamplesPerLine(this.imageWidth);
        this.frameHeader.setNumberOfLines(this.imageHeight);
        this.frameHeader.setNumberOfImageComponents(this.nComponents);
        this.frameHeader.componentParameters = frameParams;
        this.frameHeader.componentIdentifiers = new int[] { 0, 1, 2 };
        this.frameHeader.initializeContents();
        if (!this.frameHeader.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.frameComponents = frameParams;
        this.componentIds = this.frameHeader.componentIdentifiers;
        this.maxH = this.frameHeader.getMaxHFactor();
        this.maxV = this.frameHeader.getMaxVFactor();
        final int mcuWidth = this.maxH * 8;
        final int mcuHeight = this.maxV * 8;
        this.interleavedMcuCols = (this.imageWidth + mcuWidth - 1) / mcuWidth;
        this.interleavedMcuRows = (this.imageHeight + mcuHeight - 1) / mcuHeight;
        this.acHuffmanTables = new JPEGHuffmanTable[4];
        this.dcHuffmanTables = new JPEGHuffmanTable[4];
        final JPEGHuffmanTable[] dhtTables;
        final JPEGHuffmanTable[] array2;
        final JPEGHuffmanTable[] array = array2 = (dhtTables = new JPEGHuffmanTable[] { JPEGHuffmanTable.getDefaultDCLuminanceTable(), JPEGHuffmanTable.getDefaultDCChrominanceTable(), JPEGHuffmanTable.getDefaultACLuminanceTable(), JPEGHuffmanTable.getDefaultACChrominanceTable() });
        for (final JPEGHuffmanTable dhtTable : array2) {
            if (!dhtTable.writeToStream(this.outputStream)) {
                SWT.error(39);
            }
            for (final JPEGHuffmanTable huffmanTable : dhtTable.getAllTables()) {
                if (huffmanTable.getTableClass() == 0) {
                    this.dcHuffmanTables[huffmanTable.getTableIdentifier()] = huffmanTable;
                }
                else {
                    this.acHuffmanTables[huffmanTable.getTableIdentifier()] = huffmanTable;
                }
            }
        }
        this.precedingDCs = new int[4];
        (this.scanHeader = new JPEGScanHeader(new byte[14])).setSegmentMarker(65498);
        this.scanHeader.setSegmentLength(scanLength);
        this.scanHeader.setNumberOfImageComponents(this.nComponents);
        this.scanHeader.setStartOfSpectralSelection(0);
        this.scanHeader.setEndOfSpectralSelection(63);
        this.scanHeader.componentParameters = scanParams;
        this.scanHeader.initializeContents();
        if (!this.scanHeader.writeToStream(this.outputStream)) {
            SWT.error(39);
        }
        this.convertImageToYCbCr(image);
        this.resetOutputBuffer();
        this.currentByte = 0;
        this.currentBitCount = 0;
        this.encodeScan();
        if (!new JPEGEndOfImage().writeToStream(this.outputStream)) {
            SWT.error(39);
        }
    }
    
    static {
        RGB16 = new RGB[] { new RGB(0, 0, 0), new RGB(128, 0, 0), new RGB(0, 128, 0), new RGB(128, 128, 0), new RGB(0, 0, 128), new RGB(128, 0, 128), new RGB(0, 128, 128), new RGB(192, 192, 192), new RGB(128, 128, 128), new RGB(255, 0, 0), new RGB(0, 255, 0), new RGB(255, 255, 0), new RGB(0, 0, 255), new RGB(255, 0, 255), new RGB(0, 255, 255), new RGB(255, 255, 255) };
        ExtendTest = new int[] { 0, 1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024, 2048, 4096, 8192, 16384, 32768, 65536, 131072, 262144 };
        ExtendOffset = new int[] { 0, -1, -3, -7, -15, -31, -63, -127, -255, -511, -1023, -2047, -4095, -8191, -16383, -32767, -65535, -131071, -262143 };
        ZigZag8x8 = new int[] { 0, 1, 8, 16, 9, 2, 3, 10, 17, 24, 32, 25, 18, 11, 4, 5, 12, 19, 26, 33, 40, 48, 41, 34, 27, 20, 13, 6, 7, 14, 21, 28, 35, 42, 49, 56, 57, 50, 43, 36, 29, 22, 15, 23, 30, 37, 44, 51, 58, 59, 52, 45, 38, 31, 39, 46, 53, 60, 61, 54, 47, 55, 62, 63 };
        final int[] rYTable = new int[256];
        final int[] gYTable = new int[256];
        final int[] bYTable = new int[256];
        final int[] rCbTable = new int[256];
        final int[] gCbTable = new int[256];
        final int[] bCbTable = new int[256];
        final int[] gCrTable = new int[256];
        final int[] bCrTable = new int[256];
        for (int i = 0; i < 256; ++i) {
            rYTable[i] = i * 19595;
            gYTable[i] = i * 38470;
            bYTable[i] = i * 7471 + 32768;
            rCbTable[i] = i * -11059;
            gCbTable[i] = i * -21709;
            bCbTable[i] = i * 32768 + 8388608;
            gCrTable[i] = i * -27439;
            bCrTable[i] = i * -5329;
        }
        RYTable = rYTable;
        GYTable = gYTable;
        BYTable = bYTable;
        RCbTable = rCbTable;
        GCbTable = gCbTable;
        BCbTable = bCbTable;
        RCrTable = bCbTable;
        GCrTable = gCrTable;
        BCrTable = bCrTable;
        final int[] crRTable = new int[256];
        final int[] cbBTable = new int[256];
        final int[] crGTable = new int[256];
        final int[] cbGTable = new int[256];
        for (int j = 0; j < 256; ++j) {
            final int x2 = 2 * j - 255;
            crRTable[j] = 45941 * x2 + 32768 >> 16;
            cbBTable[j] = 58065 * x2 + 32768 >> 16;
            crGTable[j] = -23401 * x2;
            cbGTable[j] = -11277 * x2 + 32768;
        }
        CrRTable = crRTable;
        CbBTable = cbBTable;
        CrGTable = crGTable;
        CbGTable = cbGTable;
        int nBits = 1;
        int power2 = 2;
        final int[] nBitsTable = new int[2048];
        nBitsTable[0] = 0;
        for (int k = 1; k < nBitsTable.length; ++k) {
            if (k >= power2) {
                ++nBits;
                power2 *= 2;
            }
            nBitsTable[k] = nBits;
        }
        NBitsTable = nBitsTable;
    }
}
