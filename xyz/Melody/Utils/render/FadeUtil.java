//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.render;

import java.util.function.*;
import java.awt.*;

public enum FadeUtil
{
    GREEN(() -> new Color(0, 255, 138)), 
    WHITE(() -> Color.WHITE), 
    PURPLE(() -> new Color(198, 139, 255)), 
    DARK_PURPLE(() -> new Color(133, 46, 215)), 
    BLUE(() -> new Color(116, 202, 255)), 
    RED(() -> new Color(255, 50, 50));
    
    private final Supplier<Color> colorSupplier;
    
    private FadeUtil(final Supplier<Color> colorSupplier) {
        this.colorSupplier = colorSupplier;
    }
    
    public static Color fade(final Color color) {
        return fade(color, 2, 100);
    }
    
    public static Color fade(final Color color, final int index, final int count) {
        final float[] hsb = new float[3];
        Color.RGBtoHSB(color.getRed(), color.getGreen(), color.getBlue(), hsb);
        float brightness = Math.abs((System.currentTimeMillis() % 2000L / 1000.0f + index / (float)count * 2.0f) % 2.0f - 1.0f);
        brightness = 0.4f + 0.5f * brightness;
        hsb[2] = brightness % 2.0f;
        return new Color(Color.HSBtoRGB(hsb[0], hsb[1], hsb[2]));
    }
    
    public Color getColor() {
        return this.colorSupplier.get();
    }
}
