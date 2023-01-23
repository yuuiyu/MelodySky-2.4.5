//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPersistentProperties extends nsIProperties
{
    public static final String NS_IPERSISTENTPROPERTIES_IID = "{1a180f60-93b2-11d2-9b8b-00805f8a16d9}";
    
    void load(final nsIInputStream p0);
    
    void save(final nsIOutputStream p0, final String p1);
    
    void subclass(final nsIPersistentProperties p0);
    
    nsISimpleEnumerator enumerate();
    
    String getStringProperty(final String p0);
    
    String setStringProperty(final String p0, final String p1);
}
