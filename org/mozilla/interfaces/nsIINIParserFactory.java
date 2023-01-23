//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIINIParserFactory extends nsISupports
{
    public static final String NS_IINIPARSERFACTORY_IID = "{ccae7ea5-1218-4b51-aecb-c2d8ecd46af9}";
    
    nsIINIParser createINIParser(final nsILocalFile p0);
}
