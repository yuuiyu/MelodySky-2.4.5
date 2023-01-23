//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFormFillController extends nsISupports
{
    public static final String NS_IFORMFILLCONTROLLER_IID = "{872f07f3-ed11-47c6-b7cf-246db53379fb}";
    
    void attachToBrowser(final nsIDocShell p0, final nsIAutoCompletePopup p1);
    
    void detachFromBrowser(final nsIDocShell p0);
}
