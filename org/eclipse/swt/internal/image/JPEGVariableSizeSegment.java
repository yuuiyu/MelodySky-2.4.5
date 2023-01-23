//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

abstract class JPEGVariableSizeSegment extends JPEGSegment
{
    public JPEGVariableSizeSegment(final byte[] reference) {
        super(reference);
    }
    
    public JPEGVariableSizeSegment(final LEDataInputStream byteStream) {
        try {
            final byte[] header = new byte[4];
            byteStream.read(header);
            this.reference = header;
            final byte[] contents = new byte[this.getSegmentLength() + 2];
            contents[0] = header[0];
            contents[1] = header[1];
            contents[2] = header[2];
            contents[3] = header[3];
            byteStream.read(contents, 4, contents.length - 4);
            this.reference = contents;
        }
        catch (Exception e) {
            SWT.error(39, e);
        }
    }
}
