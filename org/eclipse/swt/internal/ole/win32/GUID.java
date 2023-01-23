//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal.ole.win32;

public final class GUID
{
    public int Data1;
    public short Data2;
    public short Data3;
    public byte[] Data4;
    public static final int sizeof;
    static final String zeros = "00000000";
    
    public GUID() {
        this.Data4 = new byte[8];
    }
    
    static String toHex(final int v, final int length) {
        String t = Integer.toHexString(v).toUpperCase();
        final int tlen = t.length();
        if (tlen > length) {
            t = t.substring(tlen - length);
        }
        return "00000000".substring(0, Math.max(0, length - tlen)) + t;
    }
    
    @Override
    public String toString() {
        return "{" + toHex(this.Data1, 8) + "-" + toHex(this.Data2, 4) + "-" + toHex(this.Data3, 4) + "-" + toHex(this.Data4[0], 2) + toHex(this.Data4[1], 2) + "-" + toHex(this.Data4[2], 2) + toHex(this.Data4[3], 2) + toHex(this.Data4[4], 2) + toHex(this.Data4[5], 2) + toHex(this.Data4[6], 2) + toHex(this.Data4[7], 2);
    }
    
    static {
        sizeof = COM.GUID_sizeof();
    }
}
