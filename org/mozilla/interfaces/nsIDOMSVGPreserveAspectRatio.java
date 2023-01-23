//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGPreserveAspectRatio extends nsISupports
{
    public static final String NS_IDOMSVGPRESERVEASPECTRATIO_IID = "{7ae42f27-4799-4e7c-86c6-e1dae6ad5157}";
    public static final int SVG_PRESERVEASPECTRATIO_UNKNOWN = 0;
    public static final int SVG_PRESERVEASPECTRATIO_NONE = 1;
    public static final int SVG_PRESERVEASPECTRATIO_XMINYMIN = 2;
    public static final int SVG_PRESERVEASPECTRATIO_XMIDYMIN = 3;
    public static final int SVG_PRESERVEASPECTRATIO_XMAXYMIN = 4;
    public static final int SVG_PRESERVEASPECTRATIO_XMINYMID = 5;
    public static final int SVG_PRESERVEASPECTRATIO_XMIDYMID = 6;
    public static final int SVG_PRESERVEASPECTRATIO_XMAXYMID = 7;
    public static final int SVG_PRESERVEASPECTRATIO_XMINYMAX = 8;
    public static final int SVG_PRESERVEASPECTRATIO_XMIDYMAX = 9;
    public static final int SVG_PRESERVEASPECTRATIO_XMAXYMAX = 10;
    public static final int SVG_MEETORSLICE_UNKNOWN = 0;
    public static final int SVG_MEETORSLICE_MEET = 1;
    public static final int SVG_MEETORSLICE_SLICE = 2;
    
    int getAlign();
    
    void setAlign(final int p0);
    
    int getMeetOrSlice();
    
    void setMeetOrSlice(final int p0);
}
