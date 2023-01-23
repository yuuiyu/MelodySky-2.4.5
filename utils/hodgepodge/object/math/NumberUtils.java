//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object.math;

import java.math.*;

public final class NumberUtils
{
    public static int toInteger(final double n) {
        return (int)n;
    }
    
    public static int toInteger(final float n) {
        return (int)n;
    }
    
    public static int toInteger(final long n) {
        return (int)n;
    }
    
    public static int toInteger(final boolean b) {
        return b ? 1 : 0;
    }
    
    public static long toLong(final double n) {
        return (long)n;
    }
    
    public static long toLong(final float n) {
        return (long)n;
    }
    
    public static short toShort(final double n) {
        return (short)n;
    }
    
    public static short toShort(final float n) {
        return (short)n;
    }
    
    public static short toShort(final int n) {
        return (short)n;
    }
    
    public static short toShort(final long n) {
        return (short)n;
    }
    
    public static boolean isNaN(final double n) {
        return n != n;
    }
    
    public static boolean isNaN(final float n) {
        return n != n;
    }
    
    public static String originalNumber(final double n) {
        return new BigDecimal(n).toString();
    }
    
    public static String originalNumber(final float n) {
        return new BigDecimal(n).toString();
    }
    
    public static String originalNumber(final long n) {
        return new BigDecimal(n).toString();
    }
    
    public static String originalNumber(final int n) {
        return new BigDecimal(n).toString();
    }
}
