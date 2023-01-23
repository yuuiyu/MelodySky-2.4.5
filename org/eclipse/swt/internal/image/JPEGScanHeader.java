//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

final class JPEGScanHeader extends JPEGVariableSizeSegment
{
    public int[][] componentParameters;
    
    public JPEGScanHeader(final byte[] reference) {
        super(reference);
    }
    
    public JPEGScanHeader(final LEDataInputStream byteStream) {
        super(byteStream);
        this.initializeComponentParameters();
    }
    
    public int getApproxBitPositionHigh() {
        return this.reference[2 * this.getNumberOfImageComponents() + 7] >> 4;
    }
    
    public int getApproxBitPositionLow() {
        return this.reference[2 * this.getNumberOfImageComponents() + 7] & 0xF;
    }
    
    public int getEndOfSpectralSelection() {
        return this.reference[2 * this.getNumberOfImageComponents() + 6];
    }
    
    public int getNumberOfImageComponents() {
        return this.reference[4];
    }
    
    public int getStartOfSpectralSelection() {
        return this.reference[2 * this.getNumberOfImageComponents() + 5];
    }
    
    void initializeComponentParameters() {
        final int compCount = this.getNumberOfImageComponents();
        this.componentParameters = new int[0][];
        for (int i = 0; i < compCount; ++i) {
            final int ofs = 5 + i * 2;
            final int cid = this.reference[ofs] & 0xFF;
            final int dc = (this.reference[ofs + 1] & 0xFF) >> 4;
            final int ac = this.reference[ofs + 1] & 0xF;
            if (this.componentParameters.length <= cid) {
                final int[][] newParams = new int[cid + 1][];
                System.arraycopy(this.componentParameters, 0, newParams, 0, this.componentParameters.length);
                this.componentParameters = newParams;
            }
            this.componentParameters[cid] = new int[] { dc, ac };
        }
    }
    
    public void initializeContents() {
        final int compCount = this.getNumberOfImageComponents();
        final int[][] compSpecParams = this.componentParameters;
        if (compCount == 0 || compCount != compSpecParams.length) {
            SWT.error(40);
        }
        for (int i = 0; i < compCount; ++i) {
            final int ofs = i * 2 + 5;
            final int[] compParams = compSpecParams[i];
            this.reference[ofs] = (byte)(i + 1);
            this.reference[ofs + 1] = (byte)(compParams[0] * 16 + compParams[1]);
        }
    }
    
    public void setEndOfSpectralSelection(final int anInteger) {
        this.reference[2 * this.getNumberOfImageComponents() + 6] = (byte)anInteger;
    }
    
    public void setNumberOfImageComponents(final int anInteger) {
        this.reference[4] = (byte)(anInteger & 0xFF);
    }
    
    public void setStartOfSpectralSelection(final int anInteger) {
        this.reference[2 * this.getNumberOfImageComponents() + 5] = (byte)anInteger;
    }
    
    @Override
    public int signature() {
        return 65498;
    }
    
    public boolean verifyProgressiveScan() {
        final int start = this.getStartOfSpectralSelection();
        final int end = this.getEndOfSpectralSelection();
        final int low = this.getApproxBitPositionLow();
        final int high = this.getApproxBitPositionHigh();
        final int count = this.getNumberOfImageComponents();
        return ((start == 0 && end == 0) || (start <= end && end <= 63)) && low <= 13 && high <= 13 && (high == 0 || high == low + 1) && (start == 0 || (start > 0 && count == 1));
    }
    
    public boolean isACProgressiveScan() {
        return this.getStartOfSpectralSelection() != 0 && this.getEndOfSpectralSelection() != 0;
    }
    
    public boolean isDCProgressiveScan() {
        return this.getStartOfSpectralSelection() == 0 && this.getEndOfSpectralSelection() == 0;
    }
    
    public boolean isFirstScan() {
        return this.getApproxBitPositionHigh() == 0;
    }
}
