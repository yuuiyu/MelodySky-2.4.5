//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object.math;

public enum CubeEnum
{
    CUBIC_CENTIMETER("CUBIC_CENTIMETER", 0) {
        lIl(final String x0, final int x2) {
        }
        
        @Override
        public double toCubicMeter(final double n) {
            return this.toCubicDecimeter(n) / 1000.0;
        }
        
        @Override
        public double toCubicDecimeter(final double n) {
            return n / 1000.0;
        }
        
        @Override
        public double toCubicCentimeter(final double n) {
            return n;
        }
    }, 
    CUBIC_DECIMETER("CUBIC_DECIMETER", 1) {
        ll(final String x0, final int x2) {
        }
        
        @Override
        public double toCubicMeter(final double n) {
            return n / 1000.0;
        }
        
        @Override
        public double toCubicDecimeter(final double n) {
            return n;
        }
        
        @Override
        public double toCubicCentimeter(final double n) {
            return n * 1000.0;
        }
    }, 
    CUBIC_METER("CUBIC_METER", 2) {
        lII(final String x0, final int x2) {
        }
        
        @Override
        public double toCubicMeter(final double n) {
            return n;
        }
        
        @Override
        public double toCubicDecimeter(final double n) {
            return n * 1000.0;
        }
        
        @Override
        public double toCubicCentimeter(final double n) {
            return this.toCubicDecimeter(n) * 1000.0;
        }
    };
    
    public abstract double toCubicMeter(final double p0);
    
    public abstract double toCubicDecimeter(final double p0);
    
    public abstract double toCubicCentimeter(final double p0);
}
