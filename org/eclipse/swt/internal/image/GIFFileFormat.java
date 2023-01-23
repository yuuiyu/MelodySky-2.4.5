//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;
import java.io.*;
import org.eclipse.swt.graphics.*;

public final class GIFFileFormat extends FileFormat
{
    String signature;
    int screenWidth;
    int screenHeight;
    int backgroundPixel;
    int bitsPerPixel;
    int defaultDepth;
    int disposalMethod;
    int delayTime;
    int transparentPixel;
    int repeatCount;
    static final int GIF_APPLICATION_EXTENSION_BLOCK_ID = 255;
    static final int GIF_GRAPHICS_CONTROL_BLOCK_ID = 249;
    static final int GIF_PLAIN_TEXT_BLOCK_ID = 1;
    static final int GIF_COMMENT_BLOCK_ID = 254;
    static final int GIF_EXTENSION_BLOCK_ID = 33;
    static final int GIF_IMAGE_BLOCK_ID = 44;
    static final int GIF_TRAILER_ID = 59;
    static final byte[] GIF89a;
    static final byte[] NETSCAPE2_0;
    
    public GIFFileFormat() {
        this.disposalMethod = 0;
        this.delayTime = 0;
        this.transparentPixel = -1;
        this.repeatCount = 1;
    }
    
    static PaletteData grayRamp(final int numGrays) {
        final int n = numGrays - 1;
        final RGB[] colors = new RGB[numGrays];
        for (int i = 0; i < numGrays; ++i) {
            final int intensity = (byte)(i * 3 * 256 / n);
            colors[i] = new RGB(intensity, intensity, intensity);
        }
        return new PaletteData(colors);
    }
    
    boolean isFileFormat(final LEDataInputStream stream) {
        try {
            final byte[] signature = new byte[3];
            stream.read(signature);
            stream.unread(signature);
            return signature[0] == 71 && signature[1] == 73 && signature[2] == 70;
        }
        catch (Exception e) {
            return false;
        }
    }
    
    ImageData[] loadFromByteStream() {
        final byte[] signature = new byte[3];
        final byte[] versionBytes = new byte[3];
        final byte[] block = new byte[7];
        try {
            this.inputStream.read(signature);
            if (signature[0] != 71 || signature[1] != 73 || signature[2] != 70) {
                SWT.error(40);
            }
            this.inputStream.read(versionBytes);
            this.inputStream.read(block);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        this.screenWidth = ((block[0] & 0xFF) | (block[1] & 0xFF) << 8);
        this.loader.logicalScreenWidth = this.screenWidth;
        this.screenHeight = ((block[2] & 0xFF) | (block[3] & 0xFF) << 8);
        this.loader.logicalScreenHeight = this.screenHeight;
        final byte bitField = block[4];
        this.backgroundPixel = (block[5] & 0xFF);
        this.bitsPerPixel = (bitField >> 4 & 0x7) + 1;
        this.defaultDepth = (bitField & 0x7) + 1;
        PaletteData palette = null;
        if ((bitField & 0x80) != 0x0) {
            palette = this.readPalette(1 << this.defaultDepth);
        }
        else {
            this.backgroundPixel = -1;
            this.defaultDepth = this.bitsPerPixel;
        }
        this.loader.backgroundPixel = this.backgroundPixel;
        ImageData[] images = new ImageData[0];
        for (int id = this.readID(); id != 59 && id != -1; id = this.readID()) {
            if (id == 44) {
                final ImageData image = this.readImageBlock(palette);
                if (this.loader.hasListeners()) {
                    this.loader.notifyListeners(new ImageLoaderEvent(this.loader, image, 3, true));
                }
                final ImageData[] oldImages = images;
                images = new ImageData[oldImages.length + 1];
                System.arraycopy(oldImages, 0, images, 0, oldImages.length);
                images[images.length - 1] = image;
            }
            else if (id == 33) {
                this.readExtension();
            }
            else {
                if (images.length > 0) {
                    break;
                }
                SWT.error(40);
            }
            id = this.readID();
            if (id == 0) {}
        }
        return images;
    }
    
    int readID() {
        try {
            return this.inputStream.read();
        }
        catch (IOException e) {
            SWT.error(39, e);
            return -1;
        }
    }
    
    byte[] readExtension() {
        final int extensionID = this.readID();
        if (extensionID == 254) {
            return this.readCommentExtension();
        }
        if (extensionID == 1) {
            return this.readPlainTextExtension();
        }
        if (extensionID == 249) {
            return this.readGraphicsControlExtension();
        }
        if (extensionID == 255) {
            return this.readApplicationExtension();
        }
        try {
            final int extSize = this.inputStream.read();
            if (extSize < 0) {
                SWT.error(40);
            }
            final byte[] ext = new byte[extSize];
            this.inputStream.read(ext, 0, extSize);
            return ext;
        }
        catch (IOException e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    byte[] readCommentExtension() {
        try {
            byte[] comment = new byte[0];
            final byte[] block = new byte[255];
            for (int size = this.inputStream.read(); size > 0 && this.inputStream.read(block, 0, size) != -1; size = this.inputStream.read()) {
                final byte[] oldComment = comment;
                comment = new byte[oldComment.length + size];
                System.arraycopy(oldComment, 0, comment, 0, oldComment.length);
                System.arraycopy(block, 0, comment, oldComment.length, size);
            }
            return comment;
        }
        catch (Exception e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    byte[] readPlainTextExtension() {
        try {
            this.inputStream.read();
            final byte[] info = new byte[12];
            this.inputStream.read(info);
            byte[] text = new byte[0];
            final byte[] block = new byte[255];
            for (int size = this.inputStream.read(); size > 0 && this.inputStream.read(block, 0, size) != -1; size = this.inputStream.read()) {
                final byte[] oldText = text;
                text = new byte[oldText.length + size];
                System.arraycopy(oldText, 0, text, 0, oldText.length);
                System.arraycopy(block, 0, text, oldText.length, size);
            }
            return text;
        }
        catch (Exception e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    byte[] readGraphicsControlExtension() {
        try {
            this.inputStream.read();
            final byte[] controlBlock = new byte[4];
            this.inputStream.read(controlBlock);
            final byte bitField = controlBlock[0];
            this.disposalMethod = (bitField >> 2 & 0x7);
            this.delayTime = ((controlBlock[1] & 0xFF) | (controlBlock[2] & 0xFF) << 8);
            if ((bitField & 0x1) != 0x0) {
                this.transparentPixel = (controlBlock[3] & 0xFF);
            }
            else {
                this.transparentPixel = -1;
            }
            return controlBlock;
        }
        catch (Exception e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    byte[] readApplicationExtension() {
        try {
            final int blockSize = this.inputStream.read();
            final byte[] blockData = new byte[blockSize];
            this.inputStream.read(blockData);
            byte[] data = new byte[0];
            final byte[] block = new byte[255];
            for (int size = this.inputStream.read(); size > 0 && this.inputStream.read(block, 0, size) != -1; size = this.inputStream.read()) {
                final byte[] oldData = data;
                data = new byte[oldData.length + size];
                System.arraycopy(oldData, 0, data, 0, oldData.length);
                System.arraycopy(block, 0, data, oldData.length, size);
            }
            final boolean netscape = blockSize > 7 && blockData[0] == 78 && blockData[1] == 69 && blockData[2] == 84 && blockData[3] == 83 && blockData[4] == 67 && blockData[5] == 65 && blockData[6] == 80 && blockData[7] == 69;
            final boolean authentic = blockSize > 10 && blockData[8] == 50 && blockData[9] == 46 && blockData[10] == 48;
            if (netscape && authentic && data[0] == 1) {
                this.repeatCount = ((data[1] & 0xFF) | (data[2] & 0xFF) << 8);
                this.loader.repeatCount = this.repeatCount;
            }
            return data;
        }
        catch (Exception e) {
            SWT.error(39, e);
            return null;
        }
    }
    
    ImageData readImageBlock(final PaletteData defaultPalette) {
        final byte[] block = new byte[9];
        try {
            this.inputStream.read(block);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        final int left = (block[0] & 0xFF) | (block[1] & 0xFF) << 8;
        final int top = (block[2] & 0xFF) | (block[3] & 0xFF) << 8;
        final int width = (block[4] & 0xFF) | (block[5] & 0xFF) << 8;
        final int height = (block[6] & 0xFF) | (block[7] & 0xFF) << 8;
        final byte bitField = block[8];
        final boolean interlaced = (bitField & 0x40) != 0x0;
        int depth;
        PaletteData palette;
        if ((bitField & 0x80) != 0x0) {
            depth = (bitField & 0x7) + 1;
            palette = this.readPalette(1 << depth);
        }
        else {
            depth = this.defaultDepth;
            palette = defaultPalette;
        }
        if (this.transparentPixel > 1 << depth) {
            this.transparentPixel = -1;
        }
        if (depth != 1 && depth != 4 && depth != 8) {
            if (depth < 4) {
                depth = 4;
            }
            else {
                depth = 8;
            }
        }
        if (palette == null) {
            palette = grayRamp(1 << depth);
        }
        int initialCodeSize = -1;
        try {
            initialCodeSize = this.inputStream.read();
        }
        catch (IOException e2) {
            SWT.error(39, e2);
        }
        if (initialCodeSize < 0) {
            SWT.error(40);
        }
        final ImageData image = ImageData.internal_new(width, height, depth, palette, 4, (byte[])null, 0, (byte[])null, (byte[])null, -1, this.transparentPixel, 2, left, top, this.disposalMethod, this.delayTime);
        final LZWCodec codec = new LZWCodec();
        codec.decode(this.inputStream, this.loader, image, interlaced, initialCodeSize);
        return image;
    }
    
    PaletteData readPalette(final int numColors) {
        final byte[] bytes = new byte[numColors * 3];
        try {
            if (this.inputStream.read(bytes) != bytes.length) {
                SWT.error(40);
            }
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        final RGB[] colors = new RGB[numColors];
        for (int i = 0; i < numColors; ++i) {
            colors[i] = new RGB(bytes[i * 3] & 0xFF, bytes[i * 3 + 1] & 0xFF, bytes[i * 3 + 2] & 0xFF);
        }
        return new PaletteData(colors);
    }
    
    void unloadIntoByteStream(final ImageLoader loader) {
        final ImageData[] data = loader.data;
        final int frameCount = data.length;
        final boolean multi = frameCount > 1;
        final ImageData firstImage = data[0];
        final int logicalScreenWidth = multi ? loader.logicalScreenWidth : firstImage.width;
        final int logicalScreenHeight = multi ? loader.logicalScreenHeight : firstImage.height;
        final int backgroundPixel = loader.backgroundPixel;
        final int depth = firstImage.depth;
        final PaletteData palette = firstImage.palette;
        final RGB[] colors = palette.getRGBs();
        short globalTable = 1;
        if (depth != 1 && depth != 4 && depth != 8) {
            SWT.error(38);
        }
        for (int i = 0; i < frameCount; ++i) {
            if (data[i].palette.isDirect) {
                SWT.error(40);
            }
            if (multi) {
                if (data[i].height > logicalScreenHeight || data[i].width > logicalScreenWidth || data[i].depth != depth) {
                    SWT.error(40);
                }
                if (globalTable == 1) {
                    final RGB[] rgbs = data[i].palette.getRGBs();
                    if (rgbs.length != colors.length) {
                        globalTable = 0;
                    }
                    else {
                        for (int j = 0; j < colors.length; ++j) {
                            if (rgbs[j].red != colors[j].red || rgbs[j].green != colors[j].green || rgbs[j].blue != colors[j].blue) {
                                globalTable = 0;
                                break;
                            }
                        }
                    }
                }
            }
        }
        try {
            this.outputStream.write(GIFFileFormat.GIF89a);
            final int bitField = globalTable * 128 + (depth - 1) * 16 + depth - 1;
            this.outputStream.writeShort((short)logicalScreenWidth);
            this.outputStream.writeShort((short)logicalScreenHeight);
            this.outputStream.write(bitField);
            this.outputStream.write(backgroundPixel);
            this.outputStream.write(0);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
        if (globalTable == 1) {
            this.writePalette(palette, depth);
        }
        if (multi) {
            final int repeatCount = loader.repeatCount;
            try {
                this.outputStream.write(33);
                this.outputStream.write(255);
                this.outputStream.write(GIFFileFormat.NETSCAPE2_0.length);
                this.outputStream.write(GIFFileFormat.NETSCAPE2_0);
                this.outputStream.write(3);
                this.outputStream.write(1);
                this.outputStream.writeShort((short)repeatCount);
                this.outputStream.write(0);
            }
            catch (IOException e2) {
                SWT.error(39, e2);
            }
        }
        for (int frame = 0; frame < frameCount; ++frame) {
            if (multi || data[frame].transparentPixel != -1) {
                this.writeGraphicsControlBlock(data[frame]);
            }
            final int x = data[frame].x;
            final int y = data[frame].y;
            final int width = data[frame].width;
            final int height = data[frame].height;
            try {
                this.outputStream.write(44);
                final byte[] block = { (byte)(x & 0xFF), (byte)(x >> 8 & 0xFF), (byte)(y & 0xFF), (byte)(y >> 8 & 0xFF), (byte)(width & 0xFF), (byte)(width >> 8 & 0xFF), (byte)(height & 0xFF), (byte)(height >> 8 & 0xFF), (byte)((globalTable == 0) ? (depth - 1 | 0x80) : 0) };
                this.outputStream.write(block);
            }
            catch (IOException e3) {
                SWT.error(39, e3);
            }
            if (globalTable == 0) {
                this.writePalette(data[frame].palette, depth);
            }
            try {
                this.outputStream.write(depth);
            }
            catch (IOException e3) {
                SWT.error(39, e3);
            }
            new LZWCodec().encode(this.outputStream, data[frame]);
        }
        try {
            this.outputStream.write(59);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    void writeGraphicsControlBlock(final ImageData image) {
        try {
            this.outputStream.write(33);
            this.outputStream.write(249);
            final byte[] gcBlock = { 0, 0, 0, 0 };
            if (image.transparentPixel != -1) {
                gcBlock[0] = 1;
                gcBlock[3] = (byte)image.transparentPixel;
            }
            if (image.disposalMethod != 0) {
                final byte[] array = gcBlock;
                final int n = 0;
                final byte[] array2 = array;
                final int n2 = 0;
                array2[n2] |= (byte)((image.disposalMethod & 0x7) << 2);
            }
            if (image.delayTime != 0) {
                gcBlock[1] = (byte)(image.delayTime & 0xFF);
                gcBlock[2] = (byte)(image.delayTime >> 8 & 0xFF);
            }
            this.outputStream.write((byte)gcBlock.length);
            this.outputStream.write(gcBlock);
            this.outputStream.write(0);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    void writePalette(final PaletteData palette, final int depth) {
        final byte[] bytes = new byte[(1 << depth) * 3];
        int offset = 0;
        for (final RGB color : palette.colors) {
            bytes[offset] = (byte)color.red;
            bytes[offset + 1] = (byte)color.green;
            bytes[offset + 2] = (byte)color.blue;
            offset += 3;
        }
        try {
            this.outputStream.write(bytes);
        }
        catch (IOException e) {
            SWT.error(39, e);
        }
    }
    
    static {
        GIF89a = new byte[] { 71, 73, 70, 56, 57, 97 };
        NETSCAPE2_0 = new byte[] { 78, 69, 84, 83, 67, 65, 80, 69, 50, 46, 48 };
    }
}
