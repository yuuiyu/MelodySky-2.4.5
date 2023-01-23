//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPrintingPromptService extends nsISupports
{
    public static final String NS_IPRINTINGPROMPTSERVICE_IID = "{75d1553d-63bf-4b5d-a8f7-e4e4cac21ba4}";
    
    void showPrintDialog(final nsIDOMWindow p0, final nsIWebBrowserPrint p1, final nsIPrintSettings p2);
    
    void showProgress(final nsIDOMWindow p0, final nsIWebBrowserPrint p1, final nsIPrintSettings p2, final nsIObserver p3, final boolean p4, final nsIWebProgressListener[] p5, final nsIPrintProgressParams[] p6, final boolean[] p7);
    
    void showPageSetup(final nsIDOMWindow p0, final nsIPrintSettings p1, final nsIObserver p2);
    
    void showPrinterProperties(final nsIDOMWindow p0, final String p1, final nsIPrintSettings p2);
}
