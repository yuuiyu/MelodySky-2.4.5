//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;
import java.util.zip.*;
import java.io.*;

final class PngEncoder
{
    static final byte[] SIGNATURE;
    static final byte[] TAG_IHDR;
    static final byte[] TAG_PLTE;
    static final byte[] TAG_TRNS;
    static final byte[] TAG_IDAT;
    static final byte[] TAG_IEND;
    static final int NO_COMPRESSION = 0;
    static final int BEST_SPEED = 1;
    static final int BEST_COMPRESSION = 9;
    static final int DEFAULT_COMPRESSION = -1;
    ByteArrayOutputStream bytes;
    PngChunk chunk;
    ImageLoader loader;
    ImageData data;
    int transparencyType;
    int width;
    int height;
    int bitDepth;
    int colorType;
    int compressionMethod;
    int filterMethod;
    int interlaceMethod;
    
    public PngEncoder(final ImageLoader loader) {
        this.bytes = new ByteArrayOutputStream(1024);
        this.compressionMethod = 0;
        this.filterMethod = 0;
        this.interlaceMethod = 0;
        this.loader = loader;
        this.data = loader.data[0];
        this.transparencyType = this.data.getTransparencyType();
        this.width = this.data.width;
        this.height = this.data.height;
        this.bitDepth = 8;
        this.colorType = 2;
        if (this.data.palette.isDirect) {
            if (this.transparencyType == 1) {
                this.colorType = 6;
            }
        }
        else {
            this.colorType = 3;
        }
        if (this.colorType != 2 && this.colorType != 3 && this.colorType != 6) {
            SWT.error(40);
        }
    }
    
    void writeShort(final ByteArrayOutputStream baos, final int theShort) {
        final byte byte1 = (byte)(theShort >> 8 & 0xFF);
        final byte byte2 = (byte)(theShort & 0xFF);
        final byte[] temp = { byte1, byte2 };
        baos.write(temp, 0, 2);
    }
    
    void writeInt(final ByteArrayOutputStream baos, final int theInt) {
        final byte byte1 = (byte)(theInt >> 24 & 0xFF);
        final byte byte2 = (byte)(theInt >> 16 & 0xFF);
        final byte byte3 = (byte)(theInt >> 8 & 0xFF);
        final byte byte4 = (byte)(theInt & 0xFF);
        final byte[] temp = { byte1, byte2, byte3, byte4 };
        baos.write(temp, 0, 4);
    }
    
    void writeChunk(final byte[] tag, final byte[] buffer) {
        final int bufferLength = (buffer != null) ? buffer.length : 0;
        this.chunk = new PngChunk(bufferLength);
        this.writeInt(this.bytes, bufferLength);
        this.bytes.write(tag, 0, 4);
        this.chunk.setType(tag);
        if (bufferLength != 0) {
            this.bytes.write(buffer, 0, bufferLength);
            this.chunk.setData(buffer);
        }
        else {
            this.chunk.setCRC(this.chunk.computeCRC());
        }
        this.writeInt(this.bytes, this.chunk.getCRC());
    }
    
    void writeSignature() {
        this.bytes.write(PngEncoder.SIGNATURE, 0, 8);
    }
    
    void writeHeader() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(13);
        this.writeInt(baos, this.width);
        this.writeInt(baos, this.height);
        baos.write(this.bitDepth);
        baos.write(this.colorType);
        baos.write(this.compressionMethod);
        baos.write(this.filterMethod);
        baos.write(this.interlaceMethod);
        this.writeChunk(PngEncoder.TAG_IHDR, baos.toByteArray());
    }
    
    void writePalette() {
        final RGB[] RGBs = this.data.palette.getRGBs();
        if (RGBs.length > 256) {
            SWT.error(40);
        }
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(RGBs.length);
        for (final RGB rgb : RGBs) {
            baos.write((byte)rgb.red);
            baos.write((byte)rgb.green);
            baos.write((byte)rgb.blue);
        }
        this.writeChunk(PngEncoder.TAG_PLTE, baos.toByteArray());
    }
    
    void writeTransparency() {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream();
        switch (this.transparencyType) {
            case 1: {
                final byte[] alphas = new byte[this.data.palette.getRGBs().length];
                for (int y = 0; y < this.height; ++y) {
                    for (int x = 0; x < this.width; ++x) {
                        final int pixelValue = this.data.getPixel(x, y);
                        final int alphaValue = this.data.getAlpha(x, y);
                        alphas[pixelValue] = (byte)alphaValue;
                    }
                }
                baos.write(alphas, 0, alphas.length);
                break;
            }
            case 4: {
                final int pixel = this.data.transparentPixel;
                if (this.colorType == 2) {
                    final int redMask = this.data.palette.redMask;
                    final int redShift = this.data.palette.redShift;
                    final int greenMask = this.data.palette.greenMask;
                    final int greenShift = this.data.palette.greenShift;
                    final int blueShift = this.data.palette.blueShift;
                    final int blueMask = this.data.palette.blueMask;
                    int r = pixel & redMask;
                    r = ((redShift < 0) ? (r >>> -redShift) : (r << redShift));
                    int g = pixel & greenMask;
                    g = ((greenShift < 0) ? (g >>> -greenShift) : (g << greenShift));
                    int b = pixel & blueMask;
                    b = ((blueShift < 0) ? (b >>> -blueShift) : (b << blueShift));
                    this.writeShort(baos, r);
                    this.writeShort(baos, g);
                    this.writeShort(baos, b);
                }
                if (this.colorType == 3) {
                    final byte[] padding = new byte[pixel + 1];
                    for (int i = 0; i < pixel; ++i) {
                        padding[i] = -1;
                    }
                    padding[pixel] = 0;
                    baos.write(padding, 0, padding.length);
                    break;
                }
                break;
            }
        }
        this.writeChunk(PngEncoder.TAG_TRNS, baos.toByteArray());
    }
    
    void writeImageData() throws IOException {
        final ByteArrayOutputStream baos = new ByteArrayOutputStream(1024);
        OutputStream os = null;
        switch (this.loader.compression) {
            case 0: {
                os = new DeflaterOutputStream(baos, new Deflater(0));
                break;
            }
            case 1: {
                os = new DeflaterOutputStream(baos, new Deflater(1));
                break;
            }
            case 3: {
                os = new DeflaterOutputStream(baos, new Deflater(9));
                break;
            }
            default: {
                os = new DeflaterOutputStream(baos, new Deflater(-1));
                break;
            }
        }
        if (this.colorType == 3) {
            final byte[] lineData = new byte[this.width];
            for (int y = 0; y < this.height; ++y) {
                final int filter = 0;
                os.write(0);
                this.data.getPixels(0, y, this.width, lineData, 0);
                os.write(lineData);
            }
        }
        else {
            final int[] lineData2 = new int[this.width];
            byte[] alphaData = null;
            if (this.colorType == 6) {
                alphaData = new byte[this.width];
            }
            final int redMask = this.data.palette.redMask;
            final int redShift = this.data.palette.redShift;
            final int greenMask = this.data.palette.greenMask;
            final int greenShift = this.data.palette.greenShift;
            final int blueShift = this.data.palette.blueShift;
            final int blueMask = this.data.palette.blueMask;
            final byte[] lineBytes = new byte[this.width * ((this.colorType == 6) ? 4 : 3)];
            for (int y2 = 0; y2 < this.height; ++y2) {
                final int filter2 = 0;
                os.write(0);
                this.data.getPixels(0, y2, this.width, lineData2, 0);
                if (this.colorType == 6) {
                    this.data.getAlphas(0, y2, this.width, alphaData, 0);
                }
                int offset = 0;
                for (int x = 0; x < lineData2.length; ++x) {
                    final int pixel = lineData2[x];
                    final int r = pixel & redMask;
                    lineBytes[offset++] = (byte)((redShift < 0) ? (r >>> -redShift) : (r << redShift));
                    final int g = pixel & greenMask;
                    lineBytes[offset++] = (byte)((greenShift < 0) ? (g >>> -greenShift) : (g << greenShift));
                    final int b = pixel & blueMask;
                    lineBytes[offset++] = (byte)((blueShift < 0) ? (b >>> -blueShift) : (b << blueShift));
                    if (this.colorType == 6) {
                        lineBytes[offset++] = alphaData[x];
                    }
                }
                os.write(lineBytes);
            }
        }
        os.flush();
        os.close();
        this.writeChunk(PngEncoder.TAG_IDAT, baos.toByteArray());
    }
    
    void writeEnd() {
        this.writeChunk(PngEncoder.TAG_IEND, null);
    }
    
    public void encode(final LEDataOutputStream outputStream) {
        try {
            this.writeSignature();
            this.writeHeader();
            if (this.colorType == 3) {
                this.writePalette();
            }
            final boolean transparencyAlpha = this.transparencyType == 1;
            final boolean transparencyPixel = this.transparencyType == 4;
            final boolean type2Transparency = this.colorType == 2 && transparencyPixel;
            final boolean type3Transparency = this.colorType == 3 && (transparencyAlpha || transparencyPixel);
            if (type2Transparency || type3Transparency) {
                this.writeTransparency();
            }
            this.writeImageData();
            this.writeEnd();
            outputStream.write(this.bytes.toByteArray());
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    static {
        SIGNATURE = new byte[] { -119, 80, 78, 71, 13, 10, 26, 10 };
        TAG_IHDR = new byte[] { 73, 72, 68, 82 };
        TAG_PLTE = new byte[] { 80, 76, 84, 69 };
        TAG_TRNS = new byte[] { 116, 82, 78, 83 };
        TAG_IDAT = new byte[] { 73, 68, 65, 84 };
        TAG_IEND = new byte[] { 73, 69, 78, 68 };
    }
}
