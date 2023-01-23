//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface inIFlasher extends nsISupports
{
    public static final String INIFLASHER_IID = "{7b4a099f-6f6e-4565-977b-fb622adbff49}";
    
    String getColor();
    
    void setColor(final String p0);
    
    boolean getInvert();
    
    void setInvert(final boolean p0);
    
    int getThickness();
    
    void setThickness(final int p0);
    
    void drawElementOutline(final nsIDOMElement p0);
    
    void repaintElement(final nsIDOMElement p0);
    
    void scrollElementIntoView(final nsIDOMElement p0);
}
