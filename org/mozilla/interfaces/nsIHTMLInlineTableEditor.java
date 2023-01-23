//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIHTMLInlineTableEditor extends nsISupports
{
    public static final String NS_IHTMLINLINETABLEEDITOR_IID = "{eda2e65c-a758-451f-9b05-77cb8de74ed2}";
    
    boolean getInlineTableEditingEnabled();
    
    void setInlineTableEditingEnabled(final boolean p0);
    
    void showInlineTableEditingUI(final nsIDOMElement p0);
    
    void hideInlineTableEditingUI();
    
    void doInlineTableEditingAction(final nsIDOMElement p0);
    
    void refreshInlineTableEditingUI();
}
