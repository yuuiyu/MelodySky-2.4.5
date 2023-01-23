//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsPICommandUpdater extends nsISupports
{
    public static final String NS_PICOMMANDUPDATER_IID = "{b135f602-0bfe-11d5-a73c-f0e420e8293c}";
    
    void init(final nsIDOMWindow p0);
    
    void commandStatusChanged(final String p0);
}
