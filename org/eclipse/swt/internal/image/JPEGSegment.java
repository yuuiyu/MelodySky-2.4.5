//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

class JPEGSegment
{
    public byte[] reference;
    
    JPEGSegment() {
    }
    
    public JPEGSegment(final byte[] reference) {
        this.reference = reference;
    }
    
    public int signature() {
        return 0;
    }
    
    public boolean verify() {
        return this.getSegmentMarker() == this.signature();
    }
    
    public int getSegmentMarker() {
        return (this.reference[0] & 0xFF) << 8 | (this.reference[1] & 0xFF);
    }
    
    public void setSegmentMarker(final int marker) {
        this.reference[0] = (byte)((marker & 0xFF00) >> 8);
        this.reference[1] = (byte)(marker & 0xFF);
    }
    
    public int getSegmentLength() {
        return (this.reference[2] & 0xFF) << 8 | (this.reference[3] & 0xFF);
    }
    
    public void setSegmentLength(final int length) {
        this.reference[2] = (byte)((length & 0xFF00) >> 8);
        this.reference[3] = (byte)(length & 0xFF);
    }
    
    public boolean writeToStream(final LEDataOutputStream byteStream) {
        try {
            byteStream.write(this.reference);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
