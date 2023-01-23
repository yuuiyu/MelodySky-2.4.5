//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsITreeContentView extends nsISupports
{
    public static final String NS_ITREECONTENTVIEW_IID = "{5ef62896-0c0a-41f1-bb3c-44a60f5dfdab}";
    
    nsIDOMElement getItemAtIndex(final int p0);
    
    int getIndexOfItem(final nsIDOMElement p0);
}
