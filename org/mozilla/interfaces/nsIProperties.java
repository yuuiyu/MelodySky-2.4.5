//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProperties extends nsISupports
{
    public static final String NS_IPROPERTIES_IID = "{78650582-4e93-4b60-8e85-26ebd3eb14ca}";
    
    nsISupports get(final String p0, final String p1);
    
    void set(final String p0, final nsISupports p1);
    
    boolean has(final String p0);
    
    void undefine(final String p0);
    
    String[] getKeys(final long[] p0);
}
