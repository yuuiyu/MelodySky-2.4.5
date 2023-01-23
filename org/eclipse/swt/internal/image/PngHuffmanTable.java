//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

public class PngHuffmanTable
{
    CodeLengthInfo[] codeLengthInfo;
    int[] codeValues;
    static final int MAX_CODE_LENGTH = 15;
    static final int BAD_CODE = 268435455;
    static final int[] incs;
    
    PngHuffmanTable(final int[] lengths) {
        this.initialize(lengths);
        this.generateTable(lengths);
    }
    
    private void initialize(final int[] lengths) {
        this.codeValues = new int[lengths.length];
        for (int i = 0; i < this.codeValues.length; ++i) {
            this.codeValues[i] = i;
        }
        this.codeLengthInfo = new CodeLengthInfo[15];
        for (int i = 0; i < 15; ++i) {
            this.codeLengthInfo[i] = new CodeLengthInfo();
            this.codeLengthInfo[i].length = i;
            this.codeLengthInfo[i].baseIndex = 0;
            this.codeLengthInfo[i].min = 268435455;
            this.codeLengthInfo[i].max = -1;
        }
    }
    
    private void generateTable(final int[] lengths) {
        for (int k = 0; k < 16; ++k) {
            int h;
            for (int i = h = PngHuffmanTable.incs[k]; i < lengths.length; ++i) {
                int v;
                int codeValuesTemp;
                int j;
                for (v = lengths[i], codeValuesTemp = this.codeValues[i], j = i; j >= h && (lengths[j - h] > v || (lengths[j - h] == v && this.codeValues[j - h] > codeValuesTemp)); j -= h) {
                    lengths[j] = lengths[j - h];
                    this.codeValues[j] = this.codeValues[j - h];
                }
                lengths[j] = v;
                this.codeValues[j] = codeValuesTemp;
            }
        }
        final int[] codes = new int[lengths.length];
        int lastLength = 0;
        int code = 0;
        for (int l = 0; l < lengths.length; ++l) {
            while (lastLength != lengths[l]) {
                ++lastLength;
                code <<= 1;
            }
            if (lastLength != 0) {
                codes[l] = code;
                ++code;
            }
        }
        int last = 0;
        for (int m = 0; m < lengths.length; ++m) {
            if (last != lengths[m]) {
                last = lengths[m];
                this.codeLengthInfo[last - 1].baseIndex = m;
                this.codeLengthInfo[last - 1].min = codes[m];
            }
            if (last != 0) {
                this.codeLengthInfo[last - 1].max = codes[m];
            }
        }
    }
    
    int getNextValue(final PngDecodingDataStream stream) throws IOException {
        int code;
        int codelength;
        for (code = stream.getNextIdatBit(), codelength = 0; codelength < 15 && code > this.codeLengthInfo[codelength].max; code = (code << 1 | stream.getNextIdatBit()), ++codelength) {}
        if (codelength >= 15) {
            stream.error();
        }
        final int offset = code - this.codeLengthInfo[codelength].min;
        final int index = this.codeLengthInfo[codelength].baseIndex + offset;
        return this.codeValues[index];
    }
    
    static {
        incs = new int[] { 1391376, 463792, 198768, 86961, 33936, 13776, 4592, 1968, 861, 336, 112, 48, 21, 7, 3, 1 };
    }
    
    static class CodeLengthInfo
    {
        int length;
        int max;
        int min;
        int baseIndex;
    }
}
