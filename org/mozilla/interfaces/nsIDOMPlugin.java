//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMPlugin extends nsISupports
{
    public static final String NS_IDOMPLUGIN_IID = "{f6134681-f28b-11d2-8360-c90899049c3c}";
    
    String getDescription();
    
    String getFilename();
    
    String getName();
    
    long getLength();
    
    nsIDOMMimeType item(final long p0);
    
    nsIDOMMimeType namedItem(final String p0);
}
