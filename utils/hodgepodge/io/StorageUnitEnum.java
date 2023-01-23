//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

public enum StorageUnitEnum
{
    BYTE("BYTE", 0, "B"), 
    KILOBYTE("KILOBYTE", 1, "KB"), 
    MEGABYTE("MEGABYTE", 2, "MB"), 
    GIGABYTE("GIGABYTE", 3, "GB"), 
    TERABYTE("TERABYTE", 4, "TB"), 
    PETABYTE("PETABYTE", 5, "PB"), 
    EXABYTE("EXABYTE", 6, "EB"), 
    ZETTA_BYTE("ZETTA_BYTE", 7, "ZB"), 
    YOTTA_BYTE("YOTTA_BYTE", 8, "YB"), 
    BRONTO_BYTE("BRONTO_BYTE", 9, "BB");
    
    private final String abbreviation;
    
    private StorageUnitEnum(final String name) {
        this.abbreviation = name;
    }
    
    public abstract double toByte(final double p0);
    
    public abstract double toKilobyte(final double p0);
    
    public abstract double toMegabyte(final double p0);
    
    public abstract double toGigabyte(final double p0);
    
    public abstract double toTerabyte(final double p0);
    
    public abstract double toPetabyte(final double p0);
    
    public abstract double toExabyte(final double p0);
    
    public abstract double toZettaByte(final double p0);
    
    public abstract double toYottaByte(final double p0);
    
    public abstract double toBrontoByte(final double p0);
    
    public String getAbbreviation() {
        return this.abbreviation;
    }
}
