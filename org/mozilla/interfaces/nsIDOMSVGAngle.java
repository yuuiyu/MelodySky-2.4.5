//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGAngle extends nsISupports
{
    public static final String NS_IDOMSVGANGLE_IID = "{58b6190e-37b3-412a-ba02-1d5ad6c6ea7c}";
    public static final int SVG_ANGLETYPE_UNKNOWN = 0;
    public static final int SVG_ANGLETYPE_UNSPECIFIED = 1;
    public static final int SVG_ANGLETYPE_DEG = 2;
    public static final int SVG_ANGLETYPE_RAD = 3;
    public static final int SVG_ANGLETYPE_GRAD = 4;
    
    int getUnitType();
    
    float getValue();
    
    void setValue(final float p0);
    
    float getValueInSpecifiedUnits();
    
    void setValueInSpecifiedUnits(final float p0);
    
    String getValueAsString();
    
    void setValueAsString(final String p0);
    
    void newValueSpecifiedUnits(final int p0, final float p1);
    
    void convertToSpecifiedUnits(final int p0);
}
