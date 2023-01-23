//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISecureBrowserUI extends nsISupports
{
    public static final String NS_ISECUREBROWSERUI_IID = "{081e31e0-a144-11d3-8c7c-00609792278c}";
    
    void init(final nsIDOMWindow p0);
    
    long getState();
    
    String getTooltipText();
}
