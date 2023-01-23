//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMSVGLength extends nsISupports
{
    public static final String NS_IDOMSVGLENGTH_IID = "{10231b04-7482-4960-bada-3dced0d586fc}";
    public static final int SVG_LENGTHTYPE_UNKNOWN = 0;
    public static final int SVG_LENGTHTYPE_NUMBER = 1;
    public static final int SVG_LENGTHTYPE_PERCENTAGE = 2;
    public static final int SVG_LENGTHTYPE_EMS = 3;
    public static final int SVG_LENGTHTYPE_EXS = 4;
    public static final int SVG_LENGTHTYPE_PX = 5;
    public static final int SVG_LENGTHTYPE_CM = 6;
    public static final int SVG_LENGTHTYPE_MM = 7;
    public static final int SVG_LENGTHTYPE_IN = 8;
    public static final int SVG_LENGTHTYPE_PT = 9;
    public static final int SVG_LENGTHTYPE_PC = 10;
    
    int getUnitType();
    
    float getValue();
    
    void setValue(final float p0);
    
    float getValueInSpecifiedUnits();
    
    void setValueInSpecifiedUnits(final float p0);
    
    String getValueAsString();
    
    void setValueAsString(final String p0);
    
    void newValueSpecifiedUnits(final int p0, final float p1);
    
    void convertToSpecifiedUnits(final int p0);
    
    float getTransformedValue(final nsIDOMSVGMatrix p0);
}
