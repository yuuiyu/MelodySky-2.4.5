//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object.math;

enum lII
{
    lII(final String s, final int n) {
    }
    
    public double toCubicMeter(final double n) {
        return n;
    }
    
    public double toCubicDecimeter(final double n) {
        return n * 1000.0;
    }
    
    public double toCubicCentimeter(final double n) {
        return this.toCubicDecimeter(n) * 1000.0;
    }
}
