//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGHuffmanTable extends JPEGVariableSizeSegment
{
    JPEGHuffmanTable[] allTables;
    int tableClass;
    int tableIdentifier;
    int[] dhMaxCodes;
    int[] dhMinCodes;
    int[] dhValPtrs;
    int[] dhValues;
    int[] ehCodes;
    byte[] ehCodeLengths;
    static byte[] DCLuminanceTable;
    static byte[] DCChrominanceTable;
    static byte[] ACLuminanceTable;
    static byte[] ACChrominanceTable;
    
    public JPEGHuffmanTable(final byte[] reference) {
        super(reference);
    }
    
    public JPEGHuffmanTable(final LEDataInputStream byteStream) {
        super(byteStream);
        this.initialize();
    }
    
    public JPEGHuffmanTable[] getAllTables() {
        return this.allTables;
    }
    
    public static JPEGHuffmanTable getDefaultACChrominanceTable() {
        final JPEGHuffmanTable result = new JPEGHuffmanTable(JPEGHuffmanTable.ACChrominanceTable);
        result.initialize();
        return result;
    }
    
    public static JPEGHuffmanTable getDefaultACLuminanceTable() {
        final JPEGHuffmanTable result = new JPEGHuffmanTable(JPEGHuffmanTable.ACLuminanceTable);
        result.initialize();
        return result;
    }
    
    public static JPEGHuffmanTable getDefaultDCChrominanceTable() {
        final JPEGHuffmanTable result = new JPEGHuffmanTable(JPEGHuffmanTable.DCChrominanceTable);
        result.initialize();
        return result;
    }
    
    public static JPEGHuffmanTable getDefaultDCLuminanceTable() {
        final JPEGHuffmanTable result = new JPEGHuffmanTable(JPEGHuffmanTable.DCLuminanceTable);
        result.initialize();
        return result;
    }
    
    public int[] getDhMaxCodes() {
        return this.dhMaxCodes;
    }
    
    public int[] getDhMinCodes() {
        return this.dhMinCodes;
    }
    
    public int[] getDhValPtrs() {
        return this.dhValPtrs;
    }
    
    public int[] getDhValues() {
        return this.dhValues;
    }
    
    public int getTableClass() {
        return this.tableClass;
    }
    
    public int getTableIdentifier() {
        return this.tableIdentifier;
    }
    
    void initialize() {
        int totalLength = this.getSegmentLength() - 2;
        int ofs = 4;
        final int[] bits = new int[16];
        final JPEGHuffmanTable[] huffTables = new JPEGHuffmanTable[8];
        int huffTableCount = 0;
        while (totalLength > 0) {
            final int tc = (this.reference[ofs] & 0xFF) >> 4;
            final int tid = this.reference[ofs] & 0xF;
            ++ofs;
            int count = 0;
            for (int i = 0; i < bits.length; ++i) {
                final int bCount = this.reference[ofs + i] & 0xFF;
                bits[i] = bCount;
                count += bCount;
            }
            ofs += 16;
            totalLength -= 17;
            final int[] huffVals = new int[count];
            for (int j = 0; j < count; ++j) {
                huffVals[j] = (this.reference[ofs + j] & 0xFF);
            }
            ofs += count;
            totalLength -= count;
            int[] huffCodeLengths = new int[50];
            int huffCodeLengthsIndex = 0;
            for (int k = 0; k < 16; ++k) {
                for (int l = 0; l < bits[k]; ++l) {
                    if (huffCodeLengthsIndex >= huffCodeLengths.length) {
                        final int[] newHuffCodeLengths = new int[huffCodeLengths.length + 50];
                        System.arraycopy(huffCodeLengths, 0, newHuffCodeLengths, 0, huffCodeLengths.length);
                        huffCodeLengths = newHuffCodeLengths;
                    }
                    huffCodeLengths[huffCodeLengthsIndex] = k + 1;
                    ++huffCodeLengthsIndex;
                }
            }
            if (huffCodeLengthsIndex < huffCodeLengths.length) {
                final int[] newHuffCodeLengths2 = new int[huffCodeLengthsIndex];
                System.arraycopy(huffCodeLengths, 0, newHuffCodeLengths2, 0, huffCodeLengthsIndex);
                huffCodeLengths = newHuffCodeLengths2;
            }
            int[] huffCodes = new int[50];
            int huffCodesIndex = 0;
            int m = 1;
            int code = 0;
            int si = huffCodeLengths[0];
            int p = 0;
            while (p < huffCodeLengthsIndex) {
                while (p < huffCodeLengthsIndex && huffCodeLengths[p] == si) {
                    if (huffCodesIndex >= huffCodes.length) {
                        final int[] newHuffCodes = new int[huffCodes.length + 50];
                        System.arraycopy(huffCodes, 0, newHuffCodes, 0, huffCodes.length);
                        huffCodes = newHuffCodes;
                    }
                    huffCodes[huffCodesIndex] = code;
                    ++huffCodesIndex;
                    ++code;
                    ++p;
                }
                code *= 2;
                ++si;
            }
            if (huffCodesIndex < huffCodes.length) {
                final int[] newHuffCodes = new int[huffCodesIndex];
                System.arraycopy(huffCodes, 0, newHuffCodes, 0, huffCodesIndex);
                huffCodes = newHuffCodes;
            }
            m = 0;
            final int[] maxCodes = new int[16];
            final int[] minCodes = new int[16];
            final int[] valPtrs = new int[16];
            for (int i2 = 0; i2 < 16; ++i2) {
                final int bSize = bits[i2];
                if (bSize == 0) {
                    maxCodes[i2] = -1;
                }
                else {
                    valPtrs[i2] = m;
                    minCodes[i2] = huffCodes[m];
                    m += bSize;
                    maxCodes[i2] = huffCodes[m - 1];
                }
            }
            final int[] eHuffCodes = new int[256];
            final byte[] eHuffSize = new byte[256];
            for (int i3 = 0; i3 < huffCodesIndex; ++i3) {
                eHuffCodes[huffVals[i3]] = huffCodes[i3];
                eHuffSize[huffVals[i3]] = (byte)huffCodeLengths[i3];
            }
            final JPEGHuffmanTable dhtTable = new JPEGHuffmanTable(this.reference);
            dhtTable.tableClass = tc;
            dhtTable.tableIdentifier = tid;
            dhtTable.dhValues = huffVals;
            dhtTable.dhMinCodes = minCodes;
            dhtTable.dhMaxCodes = maxCodes;
            dhtTable.dhValPtrs = valPtrs;
            dhtTable.ehCodes = eHuffCodes;
            dhtTable.ehCodeLengths = eHuffSize;
            huffTables[huffTableCount] = dhtTable;
            ++huffTableCount;
        }
        System.arraycopy(huffTables, 0, this.allTables = new JPEGHuffmanTable[huffTableCount], 0, huffTableCount);
    }
    
    @Override
    public int signature() {
        return 65476;
    }
    
    static {
        JPEGHuffmanTable.DCLuminanceTable = new byte[] { -1, -60, 0, 31, 0, 0, 1, 5, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        JPEGHuffmanTable.DCChrominanceTable = new byte[] { -1, -60, 0, 31, 1, 0, 3, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        JPEGHuffmanTable.ACLuminanceTable = new byte[] { -1, -60, 0, -75, 16, 0, 2, 1, 3, 3, 2, 4, 3, 5, 5, 4, 4, 0, 0, 1, 125, 1, 2, 3, 0, 4, 17, 5, 18, 33, 49, 65, 6, 19, 81, 97, 7, 34, 113, 20, 50, -127, -111, -95, 8, 35, 66, -79, -63, 21, 82, -47, -16, 36, 51, 98, 114, -126, 9, 10, 22, 23, 24, 25, 26, 37, 38, 39, 40, 41, 42, 52, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -31, -30, -29, -28, -27, -26, -25, -24, -23, -22, -15, -14, -13, -12, -11, -10, -9, -8, -7, -6 };
        JPEGHuffmanTable.ACChrominanceTable = new byte[] { -1, -60, 0, -75, 17, 0, 2, 1, 2, 4, 4, 3, 4, 7, 5, 4, 4, 0, 1, 2, 119, 0, 1, 2, 3, 17, 4, 5, 33, 49, 6, 18, 65, 81, 7, 97, 113, 19, 34, 50, -127, 8, 20, 66, -111, -95, -79, -63, 9, 35, 51, 82, -16, 21, 98, 114, -47, 10, 22, 36, 52, -31, 37, -15, 23, 24, 25, 26, 38, 39, 40, 41, 42, 53, 54, 55, 56, 57, 58, 67, 68, 69, 70, 71, 72, 73, 74, 83, 84, 85, 86, 87, 88, 89, 90, 99, 100, 101, 102, 103, 104, 105, 106, 115, 116, 117, 118, 119, 120, 121, 122, -126, -125, -124, -123, -122, -121, -120, -119, -118, -110, -109, -108, -107, -106, -105, -104, -103, -102, -94, -93, -92, -91, -90, -89, -88, -87, -86, -78, -77, -76, -75, -74, -73, -72, -71, -70, -62, -61, -60, -59, -58, -57, -56, -55, -54, -46, -45, -44, -43, -42, -41, -40, -39, -38, -30, -29, -28, -27, -26, -25, -24, -23, -22, -14, -13, -12, -11, -10, -9, -8, -7, -6 };
    }
}
