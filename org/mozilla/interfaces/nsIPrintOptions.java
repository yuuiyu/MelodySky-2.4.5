//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintOptions extends nsISupports
{
    public static final String NS_IPRINTOPTIONS_IID = "{d9948a4d-f49c-4456-938a-acda2c8d7741}";
    public static final short kNativeDataPrintRecord = 0;
    
    void showPrintSetupDialog(final nsIPrintSettings p0);
    
    nsIPrintSettings createPrintSettings();
    
    nsISimpleEnumerator availablePrinters();
    
    int getPrinterPrefInt(final nsIPrintSettings p0, final String p1);
    
    void displayJobProperties(final String p0, final nsIPrintSettings p1, final boolean[] p2);
    
    void setFontNamePointSize(final String p0, final int p1);
}
