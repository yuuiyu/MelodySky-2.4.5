//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGZoomAndPan extends nsISupports
{
    public static final String NS_IDOMSVGZOOMANDPAN_IID = "{18967370-921a-4245-8158-a279b190abca}";
    public static final int SVG_ZOOMANDPAN_UNKNOWN = 0;
    public static final int SVG_ZOOMANDPAN_DISABLE = 1;
    public static final int SVG_ZOOMANDPAN_MAGNIFY = 2;
    
    int getZoomAndPan();
    
    void setZoomAndPan(final int p0);
}
