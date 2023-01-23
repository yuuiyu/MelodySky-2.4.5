//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.graphics.*;
import java.awt.*;
import java.util.*;
import java.awt.image.*;

public class SWTUtils
{
    private SWTUtils() {
    }
    
    public static int translateSWTKeyCode(final int key) {
        switch (key) {
            case 65536: {
                return 18;
            }
            case 131072: {
                return 16;
            }
            case 262144: {
                return 17;
            }
            case 4194304: {
                return 524;
            }
            case 16777217: {
                return 38;
            }
            case 16777218: {
                return 40;
            }
            case 16777219: {
                return 37;
            }
            case 16777220: {
                return 39;
            }
            case 16777221: {
                return 33;
            }
            case 16777222: {
                return 34;
            }
            case 16777223: {
                return 36;
            }
            case 16777224: {
                return 35;
            }
            case 16777225: {
                return 155;
            }
            case 8: {
                return 8;
            }
            case 13: {
                return 10;
            }
            case 127: {
                return 127;
            }
            case 27: {
                return 27;
            }
            case 10: {
                return 10;
            }
            case 9: {
                return 9;
            }
            case 16777226: {
                return 112;
            }
            case 16777227: {
                return 113;
            }
            case 16777228: {
                return 114;
            }
            case 16777229: {
                return 115;
            }
            case 16777230: {
                return 116;
            }
            case 16777231: {
                return 117;
            }
            case 16777232: {
                return 118;
            }
            case 16777233: {
                return 119;
            }
            case 16777234: {
                return 120;
            }
            case 16777235: {
                return 121;
            }
            case 16777236: {
                return 122;
            }
            case 16777237: {
                return 123;
            }
            case 16777238: {
                return 61440;
            }
            case 16777239: {
                return 61441;
            }
            case 16777240: {
                return 61442;
            }
            case 16777258: {
                return 106;
            }
            case 16777259: {
                return 107;
            }
            case 16777296: {
                return 10;
            }
            case 16777261: {
                return 109;
            }
            case 16777262: {
                return 110;
            }
            case 16777263: {
                return 111;
            }
            case 16777264: {
                return 96;
            }
            case 16777265: {
                return 97;
            }
            case 16777266: {
                return 98;
            }
            case 16777267: {
                return 99;
            }
            case 16777268: {
                return 100;
            }
            case 16777269: {
                return 101;
            }
            case 16777270: {
                return 102;
            }
            case 16777271: {
                return 103;
            }
            case 16777272: {
                return 104;
            }
            case 16777273: {
                return 105;
            }
            case 16777298: {
                return 20;
            }
            case 16777299: {
                return 144;
            }
            case 16777300: {
                return 145;
            }
            case 16777301: {
                return 19;
            }
            case 16777302: {
                return 3;
            }
            case 16777303: {
                return 154;
            }
            case 16777297: {
                return 156;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int translateSWTMouseButton(final int button) {
        switch (button) {
            case 1: {
                return 1;
            }
            case 2: {
                return 2;
            }
            case 3: {
                return 3;
            }
            default: {
                return 0;
            }
        }
    }
    
    public static int translateSWTModifiers(final int stateMask) {
        int modifiers = 0;
        if ((stateMask & 0x20000) != 0x0) {
            modifiers |= 0x1;
        }
        if ((stateMask & 0x40000) != 0x0) {
            modifiers |= 0x2;
        }
        if ((stateMask & 0x10000) != 0x0) {
            modifiers |= 0x8;
        }
        return modifiers;
    }
    
    public static ImageData convertAWTImage(final Image image) {
        final BufferedImage handle = new BufferedImage(image.getWidth(null), image.getHeight(null), 2);
        final Graphics g = handle.getGraphics();
        g.drawImage(image, 0, 0, null);
        g.dispose();
        final ColorModel colorModel = handle.getColorModel();
        final PaletteData paletteData = new PaletteData(16711680, 65280, 255);
        final int width = handle.getWidth();
        final ImageData imageData = new ImageData(width, handle.getHeight(), colorModel.getPixelSize(), paletteData);
        final int height = handle.getHeight();
        final byte[] maskData = new byte[(width + 7) / 8 * height];
        for (int x = width - 1; x >= 0; --x) {
            for (int y = height - 1; y >= 0; --y) {
                final int rgb = handle.getRGB(x, y);
                final int pixel = paletteData.getPixel(new RGB(rgb >> 16 & 0xFF, rgb >> 8 & 0xFF, rgb & 0xFF));
                imageData.setPixel(x, y, pixel);
                final int alpha = rgb >> 24 & 0xFF;
                imageData.setAlpha(x, y, alpha);
                if (alpha != 0) {
                    final int index = x + y * ((width + 7) / 8) * 8;
                    final byte[] array = maskData;
                    final int n = index / 8;
                    array[n] |= (byte)(1 << 7 - index % 8);
                }
            }
        }
        imageData.maskPad = 1;
        imageData.maskData = maskData;
        return imageData;
    }
    
    public static BufferedImage convertSWTImage(final ImageData data) {
        ColorModel colorModel = null;
        final PaletteData palette = data.palette;
        if (palette.isDirect) {
            final BufferedImage bufferedImage = new BufferedImage(data.width, data.height, 2);
            final ImageData transparencyMask = data.getTransparencyMask();
            for (int x = data.width - 1; x >= 0; --x) {
                for (int y = data.height - 1; y >= 0; --y) {
                    RGB rgb = data.palette.getRGB(data.getPixel(x, y));
                    int pixel = rgb.red << 16 | rgb.green << 8 | rgb.blue;
                    rgb = transparencyMask.palette.getRGB(transparencyMask.getPixel(x, y));
                    final int mask = rgb.red << 16 | rgb.green << 8 | rgb.blue;
                    if (mask != 0) {
                        final int alpha = data.getAlpha(x, y);
                        if (alpha > 0) {
                            pixel = ((pixel & 0xFFFFFF) | alpha << 24);
                            bufferedImage.setRGB(x, y, pixel);
                        }
                    }
                }
            }
            return bufferedImage;
        }
        final RGB[] rgbs = palette.getRGBs();
        final byte[] red = new byte[rgbs.length];
        final byte[] green = new byte[rgbs.length];
        final byte[] blue = new byte[rgbs.length];
        for (int i = 0; i < rgbs.length; ++i) {
            final RGB rgb2 = rgbs[i];
            red[i] = (byte)rgb2.red;
            green[i] = (byte)rgb2.green;
            blue[i] = (byte)rgb2.blue;
        }
        if (data.transparentPixel != -1) {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue, data.transparentPixel);
        }
        else {
            colorModel = new IndexColorModel(data.depth, rgbs.length, red, green, blue);
        }
        final BufferedImage bufferedImage2 = new BufferedImage(colorModel, colorModel.createCompatibleWritableRaster(data.width, data.height), false, null);
        final WritableRaster raster = bufferedImage2.getRaster();
        final int[] pixelArray = { 0 };
        for (int y2 = 0; y2 < data.height; ++y2) {
            for (int x2 = 0; x2 < data.width; ++x2) {
                final int pixel2 = data.getPixel(x2, y2);
                pixelArray[0] = pixel2;
                raster.setPixel(x2, y2, pixelArray);
            }
        }
        return bufferedImage2;
    }
}
