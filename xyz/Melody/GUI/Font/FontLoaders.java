//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.GUI.Font;

import xyz.Melody.*;
import java.awt.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import java.io.*;

public abstract class FontLoaders
{
    public static CFontRenderer NMSL10;
    public static CFontRenderer NMSL11;
    public static CFontRenderer NMSL12;
    public static CFontRenderer NMSL13;
    public static CFontRenderer NMSL14;
    public static CFontRenderer NMSL15;
    public static CFontRenderer NMSL16;
    public static CFontRenderer NMSL18;
    public static CFontRenderer NMSL19;
    public static CFontRenderer NMSL20;
    public static CFontRenderer NMSL21;
    public static CFontRenderer NMSL22;
    public static CFontRenderer NMSL24;
    public static CFontRenderer NMSL25;
    public static CFontRenderer NMSL26;
    public static CFontRenderer NMSL35;
    public static CFontRenderer NMSL40;
    public static CFontRenderer NMSL45;
    public static CFontRenderer NMSL28;
    public static CFontRenderer CNMD10;
    public static CFontRenderer CNMD11;
    public static CFontRenderer CNMD12;
    public static CFontRenderer CNMD13;
    public static CFontRenderer CNMD14;
    public static CFontRenderer CNMD15;
    public static CFontRenderer CNMD16;
    public static CFontRenderer CNMD18;
    public static CFontRenderer CNMD19;
    public static CFontRenderer CNMD20;
    public static CFontRenderer CNMD21;
    public static CFontRenderer CNMD22;
    public static CFontRenderer CNMD24;
    public static CFontRenderer CNMD25;
    public static CFontRenderer CNMD26;
    public static CFontRenderer CNMD30;
    public static CFontRenderer CNMD31;
    public static CFontRenderer CNMD32;
    public static CFontRenderer CNMD33;
    public static CFontRenderer CNMD34;
    public static CFontRenderer CNMD35;
    public static CFontRenderer CNMD40;
    public static CFontRenderer CNMD45;
    public static CFontRenderer CNMD28;
    
    private static void log(final String font) {
        Client.instance.logger.info("[Melody] [Font] Loading Font: " + font + ".");
    }
    
    private static Font getNMSL(final int size) {
        Font font;
        try {
            font = new Font("Tahoma", 0, size);
            log("Tahoma " + size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    private static Font getCNMD(final int size) {
        Font font;
        try {
            final InputStream is = Minecraft.getMinecraft().getResourceManager().getResource(new ResourceLocation("Melody/Fonts/CNMD.ttf")).getInputStream();
            font = Font.createFont(0, is);
            font = font.deriveFont(0, (float)size);
            log("Montserrat Regular " + size);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            System.out.println("Error loading font");
            font = new Font("default", 0, size);
        }
        return font;
    }
    
    static {
        FontLoaders.NMSL10 = new CFontRenderer(getNMSL(10), true);
        FontLoaders.NMSL11 = new CFontRenderer(getNMSL(11), true);
        FontLoaders.NMSL12 = new CFontRenderer(getNMSL(12), true);
        FontLoaders.NMSL13 = new CFontRenderer(getNMSL(13), true);
        FontLoaders.NMSL14 = new CFontRenderer(getNMSL(14), true);
        FontLoaders.NMSL15 = new CFontRenderer(getNMSL(15), true);
        FontLoaders.NMSL16 = new CFontRenderer(getNMSL(16), true);
        FontLoaders.NMSL18 = new CFontRenderer(getNMSL(18), true);
        FontLoaders.NMSL19 = new CFontRenderer(getNMSL(19), true);
        FontLoaders.NMSL20 = new CFontRenderer(getNMSL(20), true);
        FontLoaders.NMSL21 = new CFontRenderer(getNMSL(21), true);
        FontLoaders.NMSL22 = new CFontRenderer(getNMSL(22), true);
        FontLoaders.NMSL24 = new CFontRenderer(getNMSL(24), true);
        FontLoaders.NMSL25 = new CFontRenderer(getNMSL(25), true);
        FontLoaders.NMSL26 = new CFontRenderer(getNMSL(26), true);
        FontLoaders.NMSL35 = new CFontRenderer(getNMSL(35), true);
        FontLoaders.NMSL40 = new CFontRenderer(getNMSL(40), true);
        FontLoaders.NMSL45 = new CFontRenderer(getNMSL(45), true);
        FontLoaders.NMSL28 = new CFontRenderer(getNMSL(28), true);
        FontLoaders.CNMD10 = new CFontRenderer(getCNMD(10), true);
        FontLoaders.CNMD11 = new CFontRenderer(getCNMD(11), true);
        FontLoaders.CNMD12 = new CFontRenderer(getCNMD(12), true);
        FontLoaders.CNMD13 = new CFontRenderer(getCNMD(13), true);
        FontLoaders.CNMD14 = new CFontRenderer(getCNMD(14), true);
        FontLoaders.CNMD15 = new CFontRenderer(getCNMD(15), true);
        FontLoaders.CNMD16 = new CFontRenderer(getCNMD(16), true);
        FontLoaders.CNMD18 = new CFontRenderer(getCNMD(18), true);
        FontLoaders.CNMD19 = new CFontRenderer(getCNMD(19), true);
        FontLoaders.CNMD20 = new CFontRenderer(getCNMD(20), true);
        FontLoaders.CNMD21 = new CFontRenderer(getCNMD(21), true);
        FontLoaders.CNMD22 = new CFontRenderer(getCNMD(22), true);
        FontLoaders.CNMD24 = new CFontRenderer(getCNMD(24), true);
        FontLoaders.CNMD25 = new CFontRenderer(getCNMD(25), true);
        FontLoaders.CNMD26 = new CFontRenderer(getCNMD(26), true);
        FontLoaders.CNMD30 = new CFontRenderer(getCNMD(30), true);
        FontLoaders.CNMD31 = new CFontRenderer(getCNMD(31), true);
        FontLoaders.CNMD32 = new CFontRenderer(getCNMD(32), true);
        FontLoaders.CNMD33 = new CFontRenderer(getCNMD(33), true);
        FontLoaders.CNMD34 = new CFontRenderer(getCNMD(34), true);
        FontLoaders.CNMD35 = new CFontRenderer(getCNMD(35), true);
        FontLoaders.CNMD40 = new CFontRenderer(getCNMD(40), true);
        FontLoaders.CNMD45 = new CFontRenderer(getCNMD(45), true);
        FontLoaders.CNMD28 = new CFontRenderer(getCNMD(28), true);
    }
}
