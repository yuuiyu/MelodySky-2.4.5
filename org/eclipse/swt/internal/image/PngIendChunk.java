//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

class PngIendChunk extends PngChunk
{
    PngIendChunk() {
        super(0);
        this.setType(PngIendChunk.TYPE_IEND);
        this.setCRC(this.computeCRC());
    }
    
    PngIendChunk(final byte[] reference) {
        super(reference);
    }
    
    int getChunkType() {
        return 3;
    }
    
    void validate(final PngFileReadState readState, final PngIhdrChunk headerChunk) {
        if (!readState.readIHDR || (headerChunk.getMustHavePalette() && !readState.readPLTE) || !readState.readIDAT || readState.readIEND) {
            SWT.error(40);
        }
        else {
            readState.readIEND = true;
        }
        super.validate(readState, headerChunk);
        if (this.getLength() > 0) {
            SWT.error(40);
        }
    }
}
