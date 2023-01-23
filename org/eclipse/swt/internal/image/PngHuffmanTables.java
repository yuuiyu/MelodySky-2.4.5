//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

import java.io.*;

public class PngHuffmanTables
{
    PngHuffmanTable literalTable;
    PngHuffmanTable distanceTable;
    static PngHuffmanTable FixedLiteralTable;
    static PngHuffmanTable FixedDistanceTable;
    static final int LiteralTableSize = 288;
    static final int[] FixedLiteralLengths;
    static final int DistanceTableSize = 32;
    static final int[] FixedDistanceLengths;
    static final int LengthCodeTableSize = 19;
    static final int[] LengthCodeOrder;
    
    static PngHuffmanTables getDynamicTables(final PngDecodingDataStream stream) throws IOException {
        return new PngHuffmanTables(stream);
    }
    
    static PngHuffmanTables getFixedTables() {
        return new PngHuffmanTables();
    }
    
    private PngHuffmanTable getFixedLiteralTable() {
        if (PngHuffmanTables.FixedLiteralTable == null) {
            PngHuffmanTables.FixedLiteralTable = new PngHuffmanTable(PngHuffmanTables.FixedLiteralLengths);
        }
        return PngHuffmanTables.FixedLiteralTable;
    }
    
    private PngHuffmanTable getFixedDistanceTable() {
        if (PngHuffmanTables.FixedDistanceTable == null) {
            PngHuffmanTables.FixedDistanceTable = new PngHuffmanTable(PngHuffmanTables.FixedDistanceLengths);
        }
        return PngHuffmanTables.FixedDistanceTable;
    }
    
    private PngHuffmanTables() {
        this.literalTable = this.getFixedLiteralTable();
        this.distanceTable = this.getFixedDistanceTable();
    }
    
    private PngHuffmanTables(final PngDecodingDataStream stream) throws IOException {
        final int literals = 257 + stream.getNextIdatBits(5);
        final int distances = 1 + stream.getNextIdatBits(5);
        final int codeLengthCodes = 4 + stream.getNextIdatBits(4);
        if (codeLengthCodes > 19) {
            stream.error();
        }
        final int[] lengthCodes = new int[19];
        for (int i = 0; i < codeLengthCodes; ++i) {
            lengthCodes[PngHuffmanTables.LengthCodeOrder[i]] = stream.getNextIdatBits(3);
        }
        final PngHuffmanTable codeLengthsTable = new PngHuffmanTable(lengthCodes);
        final int[] literalLengths = this.readLengths(stream, literals, codeLengthsTable, 288);
        final int[] distanceLengths = this.readLengths(stream, distances, codeLengthsTable, 32);
        this.literalTable = new PngHuffmanTable(literalLengths);
        this.distanceTable = new PngHuffmanTable(distanceLengths);
    }
    
    private int[] readLengths(final PngDecodingDataStream stream, final int numLengths, final PngHuffmanTable lengthsTable, final int tableSize) throws IOException {
        final int[] lengths = new int[tableSize];
        int index = 0;
        while (index < numLengths) {
            final int value = lengthsTable.getNextValue(stream);
            if (value < 16) {
                lengths[index] = value;
                ++index;
            }
            else if (value == 16) {
                for (int count = stream.getNextIdatBits(2) + 3, i = 0; i < count; ++i) {
                    lengths[index] = lengths[index - 1];
                    ++index;
                }
            }
            else if (value == 17) {
                for (int count = stream.getNextIdatBits(3) + 3, i = 0; i < count; ++i) {
                    lengths[index] = 0;
                    ++index;
                }
            }
            else if (value == 18) {
                for (int count = stream.getNextIdatBits(7) + 11, i = 0; i < count; ++i) {
                    lengths[index] = 0;
                    ++index;
                }
            }
            else {
                stream.error();
            }
        }
        return lengths;
    }
    
    int getNextLiteralValue(final PngDecodingDataStream stream) throws IOException {
        return this.literalTable.getNextValue(stream);
    }
    
    int getNextDistanceValue(final PngDecodingDataStream stream) throws IOException {
        return this.distanceTable.getNextValue(stream);
    }
    
    static {
        FixedLiteralLengths = new int[] { 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 8, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 9, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 7, 8, 8, 8, 8, 8, 8, 8, 8 };
        FixedDistanceLengths = new int[] { 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5 };
        LengthCodeOrder = new int[] { 16, 17, 18, 0, 8, 7, 9, 6, 10, 5, 11, 4, 12, 3, 13, 2, 14, 1, 15 };
    }
}
