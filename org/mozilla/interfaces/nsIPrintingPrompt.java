//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintingPrompt extends nsISupports
{
    public static final String NS_IPRINTINGPROMPT_IID = "{44e314ca-75b1-4f3d-9553-9b3507912108}";
    
    void showPrintDialog(final nsIWebBrowserPrint p0, final nsIPrintSettings p1);
    
    void showProgress(final nsIWebBrowserPrint p0, final nsIPrintSettings p1, final nsIObserver p2, final boolean p3, final nsIWebProgressListener[] p4, final nsIPrintProgressParams[] p5, final boolean[] p6);
    
    void showPageSetup(final nsIPrintSettings p0, final nsIObserver p1);
    
    void showPrinterProperties(final String p0, final nsIPrintSettings p1);
}
