//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

enum llIl
{
    llIl(final String x0, final int x2, final String name) {
    }
    
    @Override
    public double toByte(final double n) {
        return n * 1024.0;
    }
    
    @Override
    public double toKilobyte(final double n) {
        return n;
    }
    
    @Override
    public double toMegabyte(final double n) {
        return n / 1024.0;
    }
    
    @Override
    public double toGigabyte(final double n) {
        return this.toMegabyte(n) / 1024.0;
    }
    
    @Override
    public double toTerabyte(final double n) {
        return this.toGigabyte(n) / 1024.0;
    }
    
    @Override
    public double toPetabyte(final double n) {
        return this.toTerabyte(n) / 1024.0;
    }
    
    @Override
    public double toExabyte(final double n) {
        return this.toPetabyte(n) / 1024.0;
    }
    
    @Override
    public double toZettaByte(final double n) {
        return this.toExabyte(n) / 1024.0;
    }
    
    @Override
    public double toYottaByte(final double n) {
        return this.toZettaByte(n) / 1024.0;
    }
    
    @Override
    public double toBrontoByte(final double n) {
        return this.toYottaByte(n) / 1024.0;
    }
}
