//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import org.eclipse.swt.*;

final class JPEGFrameHeader extends JPEGVariableSizeSegment
{
    int maxVFactor;
    int maxHFactor;
    public int[] componentIdentifiers;
    public int[][] componentParameters;
    
    public JPEGFrameHeader(final byte[] reference) {
        super(reference);
    }
    
    public JPEGFrameHeader(final LEDataInputStream byteStream) {
        super(byteStream);
        this.initializeComponentParameters();
    }
    
    public int getSamplePrecision() {
        return this.reference[4] & 0xFF;
    }
    
    public int getNumberOfLines() {
        return (this.reference[5] & 0xFF) << 8 | (this.reference[6] & 0xFF);
    }
    
    public int getSamplesPerLine() {
        return (this.reference[7] & 0xFF) << 8 | (this.reference[8] & 0xFF);
    }
    
    public int getNumberOfImageComponents() {
        return this.reference[9] & 0xFF;
    }
    
    public void setSamplePrecision(final int precision) {
        this.reference[4] = (byte)(precision & 0xFF);
    }
    
    public void setNumberOfLines(final int anInteger) {
        this.reference[5] = (byte)((anInteger & 0xFF00) >> 8);
        this.reference[6] = (byte)(anInteger & 0xFF);
    }
    
    public void setSamplesPerLine(final int samples) {
        this.reference[7] = (byte)((samples & 0xFF00) >> 8);
        this.reference[8] = (byte)(samples & 0xFF);
    }
    
    public void setNumberOfImageComponents(final int anInteger) {
        this.reference[9] = (byte)(anInteger & 0xFF);
    }
    
    public int getMaxHFactor() {
        return this.maxHFactor;
    }
    
    public int getMaxVFactor() {
        return this.maxVFactor;
    }
    
    public void setMaxHFactor(final int anInteger) {
        this.maxHFactor = anInteger;
    }
    
    public void setMaxVFactor(final int anInteger) {
        this.maxVFactor = anInteger;
    }
    
    void initializeComponentParameters() {
        final int nf = this.getNumberOfImageComponents();
        this.componentIdentifiers = new int[nf];
        int[][] compSpecParams = new int[0][];
        int hmax = 1;
        int vmax = 1;
        for (int i = 0; i < nf; ++i) {
            final int ofs = i * 3 + 10;
            final int ci = this.reference[ofs] & 0xFF;
            this.componentIdentifiers[i] = ci;
            final int hi = (this.reference[ofs + 1] & 0xFF) >> 4;
            final int vi = this.reference[ofs + 1] & 0xF;
            final int tqi = this.reference[ofs + 2] & 0xFF;
            if (hi > hmax) {
                hmax = hi;
            }
            if (vi > vmax) {
                vmax = vi;
            }
            final int[] compParam = { tqi, hi, vi, 0, 0 };
            if (compSpecParams.length <= ci) {
                final int[][] newParams = new int[ci + 1][];
                System.arraycopy(compSpecParams, 0, newParams, 0, compSpecParams.length);
                compSpecParams = newParams;
            }
            compSpecParams[ci] = compParam;
        }
        final int x = this.getSamplesPerLine();
        final int y = this.getNumberOfLines();
        final int[] multiples = { 8, 16, 24, 32 };
        for (int j = 0; j < nf; ++j) {
            final int[] compParam2 = compSpecParams[this.componentIdentifiers[j]];
            final int hi2 = compParam2[1];
            final int vi2 = compParam2[2];
            final int compWidth = (x * hi2 + hmax - 1) / hmax;
            final int compHeight = (y * vi2 + vmax - 1) / vmax;
            final int dsWidth = this.roundUpToMultiple(compWidth, multiples[hi2 - 1]);
            final int dsHeight = this.roundUpToMultiple(compHeight, multiples[vi2 - 1]);
            compParam2[3] = dsWidth;
            compParam2[4] = dsHeight;
        }
        this.setMaxHFactor(hmax);
        this.setMaxVFactor(vmax);
        this.componentParameters = compSpecParams;
    }
    
    public void initializeContents() {
        final int nf = this.getNumberOfImageComponents();
        if (nf == 0 || nf != this.componentParameters.length) {
            SWT.error(40);
        }
        int hmax = 0;
        int vmax = 0;
        final int[][] compSpecParams = this.componentParameters;
        for (int i = 0; i < nf; ++i) {
            final int ofs = i * 3 + 10;
            final int[] compParam = compSpecParams[this.componentIdentifiers[i]];
            final int hi = compParam[1];
            final int vi = compParam[2];
            if (hi * vi > 4) {
                SWT.error(40);
            }
            this.reference[ofs] = (byte)(i + 1);
            this.reference[ofs + 1] = (byte)(hi * 16 + vi);
            this.reference[ofs + 2] = (byte)compParam[0];
            if (hi > hmax) {
                hmax = hi;
            }
            if (vi > vmax) {
                vmax = vi;
            }
        }
        final int x = this.getSamplesPerLine();
        final int y = this.getNumberOfLines();
        final int[] multiples = { 8, 16, 24, 32 };
        for (int j = 0; j < nf; ++j) {
            final int[] compParam2 = compSpecParams[this.componentIdentifiers[j]];
            final int hi2 = compParam2[1];
            final int vi2 = compParam2[2];
            final int compWidth = (x * hi2 + hmax - 1) / hmax;
            final int compHeight = (y * vi2 + vmax - 1) / vmax;
            final int dsWidth = this.roundUpToMultiple(compWidth, multiples[hi2 - 1]);
            final int dsHeight = this.roundUpToMultiple(compHeight, multiples[vi2 - 1]);
            compParam2[3] = dsWidth;
            compParam2[4] = dsHeight;
        }
        this.setMaxHFactor(hmax);
        this.setMaxVFactor(vmax);
    }
    
    int roundUpToMultiple(final int anInteger, final int mInteger) {
        final int a = anInteger + mInteger - 1;
        return a - a % mInteger;
    }
    
    @Override
    public boolean verify() {
        final int marker = this.getSegmentMarker();
        return (marker >= 65472 && marker <= 65475) || (marker >= 65477 && marker <= 65479) || (marker >= 65481 && marker <= 65483) || (marker >= 65485 && marker <= 65487);
    }
    
    public boolean isProgressive() {
        final int marker = this.getSegmentMarker();
        return marker == 65474 || marker == 65478 || marker == 65482 || marker == 65486;
    }
    
    public boolean isArithmeticCoding() {
        return this.getSegmentMarker() >= 65481;
    }
}
