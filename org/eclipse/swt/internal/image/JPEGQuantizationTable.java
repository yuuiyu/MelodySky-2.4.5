//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.image;

final class JPEGQuantizationTable extends JPEGVariableSizeSegment
{
    public static byte[] DefaultLuminanceQTable;
    public static byte[] DefaultChrominanceQTable;
    
    public JPEGQuantizationTable(final byte[] reference) {
        super(reference);
    }
    
    public JPEGQuantizationTable(final LEDataInputStream byteStream) {
        super(byteStream);
    }
    
    public static JPEGQuantizationTable defaultChrominanceTable() {
        final byte[] data = new byte[JPEGQuantizationTable.DefaultChrominanceQTable.length];
        System.arraycopy(JPEGQuantizationTable.DefaultChrominanceQTable, 0, data, 0, data.length);
        return new JPEGQuantizationTable(data);
    }
    
    public static JPEGQuantizationTable defaultLuminanceTable() {
        final byte[] data = new byte[JPEGQuantizationTable.DefaultLuminanceQTable.length];
        System.arraycopy(JPEGQuantizationTable.DefaultLuminanceQTable, 0, data, 0, data.length);
        return new JPEGQuantizationTable(data);
    }
    
    public int[] getQuantizationTablesKeys() {
        int[] keys = new int[4];
        int keysIndex = 0;
        int totalLength = this.getSegmentLength() - 2;
        int ofs = 4;
        while (totalLength > 64) {
            final int tq = this.reference[ofs] & 0xF;
            final int pq = (this.reference[ofs] & 0xFF) >> 4;
            if (pq == 0) {
                ofs += 65;
                totalLength -= 65;
            }
            else {
                ofs += 129;
                totalLength -= 129;
            }
            if (keysIndex >= keys.length) {
                final int[] newKeys = new int[keys.length + 4];
                System.arraycopy(keys, 0, newKeys, 0, keys.length);
                keys = newKeys;
            }
            keys[keysIndex] = tq;
            ++keysIndex;
        }
        final int[] newKeys2 = new int[keysIndex];
        System.arraycopy(keys, 0, newKeys2, 0, keysIndex);
        return newKeys2;
    }
    
    public int[][] getQuantizationTablesValues() {
        int[][] values = new int[4][];
        int valuesIndex = 0;
        int totalLength = this.getSegmentLength() - 2;
        int ofs = 4;
        while (totalLength > 64) {
            final int[] qk = new int[64];
            final int pq = (this.reference[ofs] & 0xFF) >> 4;
            if (pq == 0) {
                for (int i = 0; i < qk.length; ++i) {
                    qk[i] = (this.reference[ofs + i + 1] & 0xFF);
                }
                ofs += 65;
                totalLength -= 65;
            }
            else {
                for (int i = 0; i < qk.length; ++i) {
                    final int idx = (i - 1) * 2;
                    qk[i] = (this.reference[ofs + idx + 1] & 0xFF) * 256 + (this.reference[ofs + idx + 2] & 0xFF);
                }
                ofs += 129;
                totalLength -= 129;
            }
            if (valuesIndex >= values.length) {
                final int[][] newValues = new int[values.length + 4][];
                System.arraycopy(values, 0, newValues, 0, values.length);
                values = newValues;
            }
            values[valuesIndex] = qk;
            ++valuesIndex;
        }
        final int[][] newValues2 = new int[valuesIndex][];
        System.arraycopy(values, 0, newValues2, 0, valuesIndex);
        return newValues2;
    }
    
    public void scaleBy(final int qualityFactor) {
        int qFactor = qualityFactor;
        if (qFactor <= 0) {
            qFactor = 1;
        }
        if (qFactor > 100) {
            qFactor = 100;
        }
        if (qFactor < 50) {
            qFactor = 5000 / qFactor;
        }
        else {
            qFactor = 200 - qFactor * 2;
        }
        int totalLength = this.getSegmentLength() - 2;
        int ofs = 4;
        while (totalLength > 64) {
            final int pq = (this.reference[ofs] & 0xFF) >> 4;
            if (pq == 0) {
                for (int i = ofs + 1; i <= ofs + 64; ++i) {
                    int temp = ((this.reference[i] & 0xFF) * qFactor + 50) / 100;
                    if (temp <= 0) {
                        temp = 1;
                    }
                    if (temp > 255) {
                        temp = 255;
                    }
                    this.reference[i] = (byte)temp;
                }
                ofs += 65;
                totalLength -= 65;
            }
            else {
                for (int i = ofs + 1; i <= ofs + 128; i += 2) {
                    int temp = (((this.reference[i] & 0xFF) * 256 + (this.reference[i + 1] & 0xFF)) * qFactor + 50) / 100;
                    if (temp <= 0) {
                        temp = 1;
                    }
                    if (temp > 32767) {
                        temp = 32767;
                    }
                    this.reference[i] = (byte)(temp >> 8);
                    this.reference[i + 1] = (byte)(temp & 0xFF);
                }
                ofs += 129;
                totalLength -= 129;
            }
        }
    }
    
    @Override
    public int signature() {
        return 65499;
    }
    
    static {
        JPEGQuantizationTable.DefaultLuminanceQTable = new byte[] { -1, -37, 0, 67, 0, 16, 11, 10, 16, 24, 40, 51, 61, 12, 12, 14, 19, 26, 58, 60, 55, 14, 13, 16, 24, 40, 57, 69, 56, 14, 17, 22, 29, 51, 87, 80, 62, 18, 22, 37, 56, 68, 109, 103, 77, 24, 35, 55, 64, 81, 104, 113, 92, 49, 64, 78, 87, 103, 121, 120, 101, 72, 92, 95, 98, 112, 100, 103, 99 };
        JPEGQuantizationTable.DefaultChrominanceQTable = new byte[] { -1, -37, 0, 67, 1, 17, 18, 24, 47, 99, 99, 99, 99, 18, 21, 26, 66, 99, 99, 99, 99, 24, 26, 56, 99, 99, 99, 99, 99, 47, 66, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99, 99 };
    }
}
