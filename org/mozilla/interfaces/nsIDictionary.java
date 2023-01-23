//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDictionary extends nsISupports
{
    public static final String NS_IDICTIONARY_IID = "{1dd0cb45-aea3-4a52-8b29-01429a542863}";
    
    boolean hasKey(final String p0);
    
    String[] getKeys(final long[] p0);
    
    nsISupports getValue(final String p0);
    
    void setValue(final String p0, final nsISupports p1);
    
    nsISupports deleteValue(final String p0);
    
    void clear();
}
