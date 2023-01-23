//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.*;

final class LZWCodec
{
    int bitsPerPixel;
    int blockSize;
    int blockIndex;
    int currentByte;
    int bitsLeft;
    int codeSize;
    int clearCode;
    int endCode;
    int newCodes;
    int topSlot;
    int currentSlot;
    int imageWidth;
    int imageHeight;
    int imageX;
    int imageY;
    int pass;
    int line;
    int codeMask;
    byte[] block;
    byte[] lineArray;
    int[] stack;
    int[] suffix;
    int[] prefix;
    LZWNode[] nodeStack;
    LEDataInputStream inputStream;
    LEDataOutputStream outputStream;
    ImageData image;
    ImageLoader loader;
    boolean interlaced;
    static final int[] MASK_TABLE;
    
    void decode() {
        int oc = 0;
        int fc = 0;
        final byte[] buf = new byte[this.imageWidth];
        int stackIndex = 0;
        int bufIndex = 0;
        int c;
        while ((c = this.nextCode()) != this.endCode) {
            if (c == this.clearCode) {
                this.codeSize = this.bitsPerPixel + 1;
                this.codeMask = LZWCodec.MASK_TABLE[this.bitsPerPixel];
                this.currentSlot = this.newCodes;
                this.topSlot = 1 << this.codeSize;
                while ((c = this.nextCode()) == this.clearCode) {}
                if (c == this.endCode) {
                    continue;
                }
                oc = (fc = c);
                buf[bufIndex] = (byte)c;
                if (++bufIndex != this.imageWidth) {
                    continue;
                }
                this.nextPutPixels(buf);
                bufIndex = 0;
            }
            else {
                int code = c;
                if (code >= this.currentSlot) {
                    code = oc;
                    this.stack[stackIndex] = fc;
                    ++stackIndex;
                }
                while (code >= this.newCodes) {
                    this.stack[stackIndex] = this.suffix[code];
                    ++stackIndex;
                    code = this.prefix[code];
                }
                this.stack[stackIndex] = code;
                ++stackIndex;
                if (this.currentSlot < this.topSlot) {
                    fc = code;
                    this.suffix[this.currentSlot] = fc;
                    this.prefix[this.currentSlot] = oc;
                    ++this.currentSlot;
                    oc = c;
                }
                if (this.currentSlot >= this.topSlot && this.codeSize < 12) {
                    this.codeMask = LZWCodec.MASK_TABLE[this.codeSize];
                    ++this.codeSize;
                    this.topSlot += this.topSlot;
                }
                while (stackIndex > 0) {
                    --stackIndex;
                    buf[bufIndex] = (byte)this.stack[stackIndex];
                    if (++bufIndex == this.imageWidth) {
                        this.nextPutPixels(buf);
                        bufIndex = 0;
                    }
                }
            }
        }
        if (bufIndex != 0 && this.line < this.imageHeight) {
            this.nextPutPixels(buf);
        }
    }
    
    public void decode(final LEDataInputStream inputStream, final ImageLoader loader, final ImageData image, final boolean interlaced, final int depth) {
        this.inputStream = inputStream;
        this.loader = loader;
        this.image = image;
        this.interlaced = interlaced;
        this.bitsPerPixel = depth;
        this.initializeForDecoding();
        this.decode();
    }
    
    void encode() {
        this.nextPutCode(this.clearCode);
        final int lastPrefix = this.encodeLoop();
        this.nextPutCode(lastPrefix);
        this.nextPutCode(this.endCode);
        if (this.bitsLeft == 8) {
            this.block[0] = (byte)(this.blockIndex - 1);
        }
        else {
            this.block[0] = (byte)this.blockIndex;
        }
        this.writeBlock();
        if (this.block[0] != 0) {
            this.block[0] = 0;
            this.writeBlock();
        }
    }
    
    public void encode(final LEDataOutputStream byteStream, final ImageData image) {
        this.outputStream = byteStream;
        this.image = image;
        this.initializeForEncoding();
        this.encode();
    }
    
    int encodeLoop() {
        int pixel = this.nextPixel();
        while (true) {
            int currentPrefix = pixel;
            LZWNode node = this.nodeStack[currentPrefix];
            boolean found = true;
            pixel = this.nextPixel();
            if (pixel < 0) {
                return currentPrefix;
            }
            while (found && node.children != null) {
                node = node.children;
                while (found && node.suffix != pixel) {
                    if (pixel < node.suffix) {
                        if (node.left == null) {
                            node.left = new LZWNode();
                            found = false;
                        }
                        node = node.left;
                    }
                    else {
                        if (node.right == null) {
                            node.right = new LZWNode();
                            found = false;
                        }
                        node = node.right;
                    }
                }
                if (found) {
                    currentPrefix = node.code;
                    pixel = this.nextPixel();
                    if (pixel < 0) {
                        return currentPrefix;
                    }
                    continue;
                }
            }
            if (found) {
                node.children = new LZWNode();
                node = node.children;
            }
            node.children = null;
            node.left = null;
            node.right = null;
            node.code = this.currentSlot;
            node.prefix = currentPrefix;
            node.suffix = pixel;
            this.nextPutCode(currentPrefix);
            ++this.currentSlot;
            if (this.currentSlot < 4096) {
                if (this.currentSlot <= this.topSlot) {
                    continue;
                }
                ++this.codeSize;
                this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
                this.topSlot *= 2;
            }
            else {
                this.nextPutCode(this.clearCode);
                for (final LZWNode lzwnode : this.nodeStack) {
                    lzwnode.children = null;
                }
                this.codeSize = this.bitsPerPixel + 1;
                this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
                this.currentSlot = this.newCodes;
                this.topSlot = 1 << this.codeSize;
            }
        }
    }
    
    void initializeForDecoding() {
        this.pass = 1;
        this.line = 0;
        this.codeSize = this.bitsPerPixel + 1;
        this.topSlot = 1 << this.codeSize;
        this.clearCode = 1 << this.bitsPerPixel;
        this.endCode = this.clearCode + 1;
        final int n = this.endCode + 1;
        this.currentSlot = n;
        this.newCodes = n;
        this.currentByte = -1;
        final int n2 = 0;
        this.bitsLeft = 0;
        this.blockSize = 0;
        this.blockIndex = 0;
        this.codeMask = LZWCodec.MASK_TABLE[this.codeSize - 1];
        this.stack = new int[4096];
        this.suffix = new int[4096];
        this.prefix = new int[4096];
        this.block = new byte[256];
        this.imageWidth = this.image.width;
        this.imageHeight = this.image.height;
    }
    
    void initializeForEncoding() {
        this.interlaced = false;
        this.bitsPerPixel = this.image.depth;
        this.codeSize = this.bitsPerPixel + 1;
        this.topSlot = 1 << this.codeSize;
        this.clearCode = 1 << this.bitsPerPixel;
        this.endCode = this.clearCode + 1;
        final int n = this.endCode + 1;
        this.currentSlot = n;
        this.newCodes = n;
        this.bitsLeft = 8;
        this.currentByte = 0;
        this.blockIndex = 1;
        this.blockSize = 255;
        (this.block = new byte[this.blockSize])[0] = (byte)(this.blockSize - 1);
        this.nodeStack = new LZWNode[1 << this.bitsPerPixel];
        for (int i = 0; i < this.nodeStack.length; ++i) {
            final LZWNode node = new LZWNode();
            node.code = i + 1;
            node.prefix = -1;
            node.suffix = i + 1;
            this.nodeStack[i] = node;
        }
        this.imageWidth = this.image.width;
        this.imageHeight = this.image.height;
        this.imageY = -1;
        this.lineArray = new byte[this.imageWidth];
        this.imageX = this.imageWidth + 1;
    }
    
    int nextCode() {
        int code;
        if (this.bitsLeft == 0) {
            if (this.blockIndex >= this.blockSize) {
                this.blockSize = this.readBlock();
                this.blockIndex = 0;
                if (this.blockSize == 0) {
                    return this.endCode;
                }
            }
            ++this.blockIndex;
            this.currentByte = (this.block[this.blockIndex] & 0xFF);
            this.bitsLeft = 8;
            code = this.currentByte;
        }
        else {
            final int shift = this.bitsLeft - 8;
            if (shift < 0) {
                code = this.currentByte >> 0 - shift;
            }
            else {
                code = this.currentByte << shift;
            }
        }
        while (this.codeSize > this.bitsLeft) {
            if (this.blockIndex >= this.blockSize) {
                this.blockSize = this.readBlock();
                this.blockIndex = 0;
                if (this.blockSize == 0) {
                    return this.endCode;
                }
            }
            ++this.blockIndex;
            this.currentByte = (this.block[this.blockIndex] & 0xFF);
            code += this.currentByte << this.bitsLeft;
            this.bitsLeft += 8;
        }
        this.bitsLeft -= this.codeSize;
        return code & this.codeMask;
    }
    
    int nextPixel() {
        ++this.imageX;
        if (this.imageX > this.imageWidth) {
            ++this.imageY;
            if (this.imageY >= this.imageHeight) {
                return -1;
            }
            this.nextPixels(this.lineArray, this.imageWidth);
            this.imageX = 1;
        }
        return this.lineArray[this.imageX - 1] & 0xFF;
    }
    
    void nextPixels(final byte[] buf, final int lineWidth) {
        if (this.image.depth == 8) {
            System.arraycopy(this.image.data, this.imageY * this.image.bytesPerLine, buf, 0, lineWidth);
        }
        else {
            this.image.getPixels(0, this.imageY, lineWidth, buf, 0);
        }
    }
    
    void nextPutCode(final int aCode) {
        int codeToDo = aCode;
        int codeBitsToDo = this.codeSize;
        final int c = codeToDo & LZWCodec.MASK_TABLE[this.bitsLeft - 1];
        this.currentByte |= c << 8 - this.bitsLeft;
        this.block[this.blockIndex] = (byte)this.currentByte;
        codeBitsToDo -= this.bitsLeft;
        if (codeBitsToDo < 1) {
            this.bitsLeft -= this.codeSize;
            if (this.bitsLeft == 0) {
                this.bitsLeft = 8;
                ++this.blockIndex;
                if (this.blockIndex >= this.blockSize) {
                    this.writeBlock();
                    this.blockIndex = 1;
                }
                this.currentByte = 0;
            }
            return;
        }
        codeToDo >>= this.bitsLeft;
        ++this.blockIndex;
        if (this.blockIndex >= this.blockSize) {
            this.writeBlock();
            this.blockIndex = 1;
        }
        while (codeBitsToDo >= 8) {
            this.currentByte = (codeToDo & 0xFF);
            this.block[this.blockIndex] = (byte)this.currentByte;
            codeToDo >>= 8;
            codeBitsToDo -= 8;
            ++this.blockIndex;
            if (this.blockIndex >= this.blockSize) {
                this.writeBlock();
                this.blockIndex = 1;
            }
        }
        this.bitsLeft = 8 - codeBitsToDo;
        this.currentByte = codeToDo;
        this.block[this.blockIndex] = (byte)this.currentByte;
    }
    
    void nextPutPixels(final byte[] buf) {
        if (this.image.depth == 8) {
            final int start = this.line * this.image.bytesPerLine;
            System.arraycopy(buf, 0, this.image.data, start, this.imageWidth);
        }
        else {
            this.image.setPixels(0, this.line, this.imageWidth, buf, 0);
        }
        if (this.interlaced) {
            if (this.pass == 1) {
                this.copyRow(buf, 7);
                this.line += 8;
            }
            else if (this.pass == 2) {
                this.copyRow(buf, 3);
                this.line += 8;
            }
            else if (this.pass == 3) {
                this.copyRow(buf, 1);
                this.line += 4;
            }
            else if (this.pass == 4) {
                this.line += 2;
            }
            else if (this.pass == 5) {
                this.line += 0;
            }
            if (this.line >= this.imageHeight) {
                ++this.pass;
                if (this.pass == 2) {
                    this.line = 4;
                }
                else if (this.pass == 3) {
                    this.line = 2;
                }
                else if (this.pass == 4) {
                    this.line = 1;
                }
                else if (this.pass == 5) {
                    this.line = 0;
                }
                if (this.pass < 5 && this.loader.hasListeners()) {
                    final ImageData imageCopy = (ImageData)this.image.clone();
                    this.loader.notifyListeners(new ImageLoaderEvent(this.loader, imageCopy, this.pass - 2, false));
                }
            }
            if (this.line >= this.imageHeight) {
                this.line = 0;
            }
        }
        else {
            ++this.line;
        }
    }
    
    void copyRow(final byte[] buf, final int copies) {
        for (int i = 1; i <= copies; ++i) {
            if (this.line + i < this.imageHeight) {
                this.image.setPixels(0, this.line + i, this.imageWidth, buf, 0);
            }
        }
    }
    
    int readBlock() {
        int size = -1;
        try {
            size = this.inputStream.read();
            if (size == -1) {
                SWT.error(40);
            }
            this.block[0] = (byte)size;
            size = this.inputStream.read(this.block, 1, size);
            if (size == -1) {
                SWT.error(40);
            }
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
        return size;
    }
    
    void writeBlock() {
        try {
            this.outputStream.write(this.block, 0, (this.block[0] & 0xFF) + 1);
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
    }
    
    static {
        MASK_TABLE = new int[] { 1, 3, 7, 15, 31, 63, 127, 255, 511, 1023, 2047, 4095 };
    }
}
