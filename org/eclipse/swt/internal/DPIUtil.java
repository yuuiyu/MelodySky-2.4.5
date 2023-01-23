//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import org.eclipse.swt.*;
import org.eclipse.swt.graphics.*;

public class DPIUtil
{
    private static final int DPI_ZOOM_100 = 96;
    private static int deviceZoom;
    private static int nativeDeviceZoom;
    private static AutoScaleMethod autoScaleMethodSetting;
    private static AutoScaleMethod autoScaleMethod;
    private static String autoScaleValue;
    private static boolean useCairoAutoScale;
    private static final String SWT_AUTOSCALE = "swt.autoScale";
    private static final String SWT_AUTOSCALE_METHOD = "swt.autoScale.method";
    
    public static ImageData autoScaleDown(final Device device, final ImageData imageData) {
        if (DPIUtil.deviceZoom == 100 || imageData == null || (device != null && !device.isAutoScalable())) {
            return imageData;
        }
        final float scaleFactor = 1.0f / getScalingFactor();
        return autoScaleImageData(device, imageData, scaleFactor);
    }
    
    public static int[] autoScaleDown(final int[] pointArray) {
        if (DPIUtil.deviceZoom == 100 || pointArray == null) {
            return pointArray;
        }
        final float scaleFactor = getScalingFactor();
        final int[] returnArray = new int[pointArray.length];
        for (int i = 0; i < pointArray.length; ++i) {
            returnArray[i] = Math.round(pointArray[i] / scaleFactor);
        }
        return returnArray;
    }
    
    public static int[] autoScaleDown(final Drawable drawable, final int[] pointArray) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return pointArray;
        }
        return autoScaleDown(pointArray);
    }
    
    public static float[] autoScaleDown(final float[] size) {
        if (DPIUtil.deviceZoom == 100 || size == null) {
            return size;
        }
        final float scaleFactor = getScalingFactor();
        final float[] scaledSize = new float[size.length];
        for (int i = 0; i < scaledSize.length; ++i) {
            scaledSize[i] = size[i] / scaleFactor;
        }
        return scaledSize;
    }
    
    public static float[] autoScaleDown(final Drawable drawable, final float[] size) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return size;
        }
        return autoScaleDown(size);
    }
    
    public static int autoScaleDown(final int size) {
        if (DPIUtil.deviceZoom == 100 || size == -1) {
            return size;
        }
        final float scaleFactor = getScalingFactor();
        return Math.round(size / scaleFactor);
    }
    
    public static int autoScaleDown(final Drawable drawable, final int size) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return size;
        }
        return autoScaleDown(size);
    }
    
    public static float autoScaleDown(final float size) {
        if (DPIUtil.deviceZoom == 100 || size == -1.0f) {
            return size;
        }
        final float scaleFactor = getScalingFactor();
        return size / scaleFactor;
    }
    
    public static float autoScaleDown(final Drawable drawable, final float size) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return size;
        }
        return autoScaleDown(size);
    }
    
    public static Point autoScaleDown(final Point point) {
        if (DPIUtil.deviceZoom == 100 || point == null) {
            return point;
        }
        final float scaleFactor = getScalingFactor();
        final Point scaledPoint = new Point(0, 0);
        scaledPoint.x = Math.round(point.x / scaleFactor);
        scaledPoint.y = Math.round(point.y / scaleFactor);
        return scaledPoint;
    }
    
    public static Point autoScaleDown(final Drawable drawable, final Point point) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return point;
        }
        return autoScaleDown(point);
    }
    
    public static Rectangle autoScaleDown(final Rectangle rect) {
        if (DPIUtil.deviceZoom == 100 || rect == null) {
            return rect;
        }
        final Rectangle scaledRect = new Rectangle(0, 0, 0, 0);
        final Point scaledTopLeft = autoScaleDown(new Point(rect.x, rect.y));
        final Point scaledBottomRight = autoScaleDown(new Point(rect.x + rect.width, rect.y + rect.height));
        scaledRect.x = scaledTopLeft.x;
        scaledRect.y = scaledTopLeft.y;
        scaledRect.width = scaledBottomRight.x - scaledTopLeft.x;
        scaledRect.height = scaledBottomRight.y - scaledTopLeft.y;
        return scaledRect;
    }
    
    public static Rectangle autoScaleDown(final Drawable drawable, final Rectangle rect) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return rect;
        }
        return autoScaleDown(rect);
    }
    
    public static ImageData autoScaleImageData(final Device device, final ImageData imageData, final int targetZoom, final int currentZoom) {
        if (imageData == null || targetZoom == currentZoom || (device != null && !device.isAutoScalable())) {
            return imageData;
        }
        final float scaleFactor = targetZoom / (float)currentZoom;
        return autoScaleImageData(device, imageData, scaleFactor);
    }
    
    private static ImageData autoScaleImageData(final Device device, final ImageData imageData, final float scaleFactor) {
        final int width = imageData.width;
        final int height = imageData.height;
        final int scaledWidth = Math.round(width * scaleFactor);
        final int scaledHeight = Math.round(height * scaleFactor);
        switch (l.$SwitchMap$org$eclipse$swt$internal$DPIUtil$AutoScaleMethod[DPIUtil.autoScaleMethod.ordinal()]) {
            case 1: {
                final Image original = new Image(device, imageData);
                final ImageData resultData = new ImageData(scaledWidth, scaledHeight, 24, new PaletteData(255, 65280, 16711680));
                resultData.alphaData = new byte[scaledWidth * scaledHeight];
                final Image resultImage = new Image(device, imageData);
                final GC gc = new GC((Drawable)resultImage);
                gc.setAntialias(1);
                gc.drawImage(original, 0, 0, autoScaleDown(width), autoScaleDown(height), 0, 0, Math.round(autoScaleDown(width * scaleFactor)), Math.round(autoScaleDown(height * scaleFactor)));
                gc.dispose();
                original.dispose();
                final ImageData result = resultImage.getImageData(getDeviceZoom());
                resultImage.dispose();
                return result;
            }
            default: {
                return imageData.scaledTo(scaledWidth, scaledHeight);
            }
        }
    }
    
    public static Rectangle autoScaleBounds(final Rectangle rect, final int targetZoom, final int currentZoom) {
        if (DPIUtil.deviceZoom == 100 || rect == null || targetZoom == currentZoom) {
            return rect;
        }
        final float scaleFactor = targetZoom / (float)currentZoom;
        final Rectangle returnRect = new Rectangle(0, 0, 0, 0);
        returnRect.x = Math.round(rect.x * scaleFactor);
        returnRect.y = Math.round(rect.y * scaleFactor);
        returnRect.width = Math.round(rect.width * scaleFactor);
        returnRect.height = Math.round(rect.height * scaleFactor);
        return returnRect;
    }
    
    public static ImageData autoScaleUp(final Device device, final ImageData imageData) {
        if (DPIUtil.deviceZoom == 100 || imageData == null || (device != null && !device.isAutoScalable())) {
            return imageData;
        }
        final float scaleFactor = DPIUtil.deviceZoom / 100.0f;
        return autoScaleImageData(device, imageData, scaleFactor);
    }
    
    public static int[] autoScaleUp(final int[] pointArray) {
        if (DPIUtil.deviceZoom == 100 || pointArray == null) {
            return pointArray;
        }
        final float scaleFactor = getScalingFactor();
        final int[] returnArray = new int[pointArray.length];
        for (int i = 0; i < pointArray.length; ++i) {
            returnArray[i] = Math.round(pointArray[i] * scaleFactor);
        }
        return returnArray;
    }
    
    public static int[] autoScaleUp(final Drawable drawable, final int[] pointArray) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return pointArray;
        }
        return autoScaleUp(pointArray);
    }
    
    public static int autoScaleUp(final int size) {
        if (DPIUtil.deviceZoom == 100 || size == -1) {
            return size;
        }
        final float scaleFactor = getScalingFactor();
        return Math.round(size * scaleFactor);
    }
    
    public static int autoScaleUpUsingNativeDPI(final int size) {
        if (DPIUtil.nativeDeviceZoom == 100 || size == -1) {
            return size;
        }
        final float nativeScaleFactor = DPIUtil.nativeDeviceZoom / 100.0f;
        return Math.round(size * nativeScaleFactor);
    }
    
    public static int autoScaleUp(final Drawable drawable, final int size) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return size;
        }
        return autoScaleUp(size);
    }
    
    public static float autoScaleUp(final float size) {
        if (DPIUtil.deviceZoom == 100 || size == -1.0f) {
            return size;
        }
        final float scaleFactor = getScalingFactor();
        return size * scaleFactor;
    }
    
    public static float autoScaleUp(final Drawable drawable, final float size) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return size;
        }
        return autoScaleUp(size);
    }
    
    public static Point autoScaleUp(final Point point) {
        if (DPIUtil.deviceZoom == 100 || point == null) {
            return point;
        }
        final float scaleFactor = getScalingFactor();
        final Point scaledPoint = new Point(0, 0);
        scaledPoint.x = Math.round(point.x * scaleFactor);
        scaledPoint.y = Math.round(point.y * scaleFactor);
        return scaledPoint;
    }
    
    public static Point autoScaleUp(final Drawable drawable, final Point point) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return point;
        }
        return autoScaleUp(point);
    }
    
    public static Rectangle autoScaleUp(final Rectangle rect) {
        if (DPIUtil.deviceZoom == 100 || rect == null) {
            return rect;
        }
        final Rectangle scaledRect = new Rectangle(0, 0, 0, 0);
        final Point scaledTopLeft = autoScaleUp(new Point(rect.x, rect.y));
        final Point scaledBottomRight = autoScaleUp(new Point(rect.x + rect.width, rect.y + rect.height));
        scaledRect.x = scaledTopLeft.x;
        scaledRect.y = scaledTopLeft.y;
        scaledRect.width = scaledBottomRight.x - scaledTopLeft.x;
        scaledRect.height = scaledBottomRight.y - scaledTopLeft.y;
        return scaledRect;
    }
    
    public static Rectangle autoScaleUp(final Drawable drawable, final Rectangle rect) {
        if (drawable != null && !drawable.isAutoScalable()) {
            return rect;
        }
        return autoScaleUp(rect);
    }
    
    private static float getScalingFactor() {
        if (DPIUtil.useCairoAutoScale) {
            return 1.0f;
        }
        return DPIUtil.deviceZoom / 100.0f;
    }
    
    public static int mapDPIToZoom(final int dpi) {
        final double zoom = dpi * 100.0 / 96.0;
        final int roundedZoom = (int)Math.round(zoom);
        return roundedZoom;
    }
    
    public static ImageData validateAndGetImageDataAtZoom(final ImageDataProvider provider, final int zoom, final boolean[] found) {
        if (provider == null) {
            SWT.error(4);
        }
        ImageData data = provider.getImageData(zoom);
        found[0] = (data != null);
        if (zoom != 100 && !found[0]) {
            data = provider.getImageData(100);
        }
        if (data == null) {
            SWT.error(5, null, ": ImageDataProvider [" + provider + "] returns null ImageData at 100% zoom.");
        }
        return data;
    }
    
    public static String validateAndGetImagePathAtZoom(final ImageFileNameProvider provider, final int zoom, final boolean[] found) {
        if (provider == null) {
            SWT.error(4);
        }
        String filename = provider.getImagePath(zoom);
        found[0] = (filename != null);
        if (zoom != 100 && !found[0]) {
            filename = provider.getImagePath(100);
        }
        if (filename == null) {
            SWT.error(5, null, ": ImageFileNameProvider [" + provider + "] returns null filename at 100% zoom.");
        }
        return filename;
    }
    
    public static int getDeviceZoom() {
        return DPIUtil.deviceZoom;
    }
    
    public static void setDeviceZoom(final int nativeDeviceZoom) {
        DPIUtil.nativeDeviceZoom = nativeDeviceZoom;
        final int deviceZoom = DPIUtil.deviceZoom = getZoomForAutoscaleProperty(nativeDeviceZoom);
        System.setProperty("org.eclipse.swt.internal.deviceZoom", Integer.toString(deviceZoom));
        if (deviceZoom != 100 && DPIUtil.autoScaleMethodSetting == AutoScaleMethod.AUTO) {
            if (deviceZoom / 100 * 100 == deviceZoom || !"gtk".equals(SWT.getPlatform())) {
                DPIUtil.autoScaleMethod = AutoScaleMethod.NEAREST;
            }
            else {
                DPIUtil.autoScaleMethod = AutoScaleMethod.SMOOTH;
            }
        }
    }
    
    public static void setUseCairoAutoScale(final boolean cairoAutoScale) {
        DPIUtil.useCairoAutoScale = cairoAutoScale;
    }
    
    public static boolean useCairoAutoScale() {
        return DPIUtil.useCairoAutoScale;
    }
    
    public static int getZoomForAutoscaleProperty(final int nativeDeviceZoom) {
        int zoom = 0;
        if (DPIUtil.autoScaleValue != null) {
            if ("false".equalsIgnoreCase(DPIUtil.autoScaleValue)) {
                zoom = 100;
            }
            else if ("quarter".equalsIgnoreCase(DPIUtil.autoScaleValue)) {
                zoom = Math.round(nativeDeviceZoom / 25.0f) * 25;
            }
            else if ("exact".equalsIgnoreCase(DPIUtil.autoScaleValue)) {
                zoom = nativeDeviceZoom;
            }
            else {
                try {
                    final int zoomValue = Integer.parseInt(DPIUtil.autoScaleValue);
                    zoom = Math.max(Math.min(zoomValue, 1600), 25);
                }
                catch (NumberFormatException ex) {}
            }
        }
        if (zoom == 0) {
            zoom = Math.max((nativeDeviceZoom + 25) / 100 * 100, 100);
        }
        return zoom;
    }
    
    static {
        DPIUtil.deviceZoom = 100;
        DPIUtil.nativeDeviceZoom = 100;
        DPIUtil.autoScaleMethodSetting = AutoScaleMethod.AUTO;
        DPIUtil.autoScaleMethod = AutoScaleMethod.NEAREST;
        DPIUtil.useCairoAutoScale = false;
        DPIUtil.autoScaleValue = System.getProperty("swt.autoScale");
        final String value = System.getProperty("swt.autoScale.method");
        if (value != null) {
            if (AutoScaleMethod.NEAREST.name().equalsIgnoreCase(value)) {
                DPIUtil.autoScaleMethod = (DPIUtil.autoScaleMethodSetting = AutoScaleMethod.NEAREST);
            }
            else if (AutoScaleMethod.SMOOTH.name().equalsIgnoreCase(value)) {
                DPIUtil.autoScaleMethod = (DPIUtil.autoScaleMethodSetting = AutoScaleMethod.SMOOTH);
            }
        }
    }
    
    private enum AutoScaleMethod
    {
        AUTO, 
        NEAREST, 
        SMOOTH;
    }
    
    public static final class AutoScaleImageDataProvider implements ImageDataProvider
    {
        Device device;
        ImageData imageData;
        int currentZoom;
        
        public AutoScaleImageDataProvider(final Device device, final ImageData data, final int zoom) {
            this.device = device;
            this.imageData = data;
            this.currentZoom = zoom;
        }
        
        public ImageData getImageData(final int zoom) {
            return DPIUtil.autoScaleImageData(this.device, this.imageData, zoom, this.currentZoom);
        }
    }
}
