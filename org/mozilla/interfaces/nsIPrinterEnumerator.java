//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrinterEnumerator extends nsISupports
{
    public static final String NS_IPRINTERENUMERATOR_IID = "{a6cf9128-15b3-11d2-932e-00805f8add32}";
    
    String getDefaultPrinterName();
    
    void initPrintSettingsFromPrinter(final String p0, final nsIPrintSettings p1);
    
    String[] enumeratePrinters(final long[] p0);
    
    void displayPropertiesDlg(final String p0, final nsIPrintSettings p1);
}
