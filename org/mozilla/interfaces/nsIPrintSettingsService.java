//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintSettingsService extends nsISupports
{
    public static final String NS_IPRINTSETTINGSSERVICE_IID = "{841387c8-72e6-484b-9296-bf6eea80d58a}";
    
    nsIPrintSettings getGlobalPrintSettings();
    
    nsIPrintSettings getNewPrintSettings();
    
    String getDefaultPrinterName();
    
    void initPrintSettingsFromPrinter(final String p0, final nsIPrintSettings p1);
    
    void initPrintSettingsFromPrefs(final nsIPrintSettings p0, final boolean p1, final long p2);
    
    void savePrintSettingsToPrefs(final nsIPrintSettings p0, final boolean p1, final long p2);
}
