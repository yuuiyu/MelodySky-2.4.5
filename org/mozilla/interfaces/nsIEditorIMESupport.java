//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIEditorIMESupport extends nsISupports
{
    public static final String NS_IEDITORIMESUPPORT_IID = "{205b3e49-aa58-499e-880b-aacab9dede01}";
    
    void endComposition();
    
    void forceCompositionEnd();
    
    void notifyIMEOnFocus();
    
    void notifyIMEOnBlur();
}
