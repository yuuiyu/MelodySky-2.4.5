//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStringBundle extends nsISupports
{
    public static final String NS_ISTRINGBUNDLE_IID = "{d85a17c2-aa7c-11d2-9b8c-00805f8a16d9}";
    
    String getStringFromID(final int p0);
    
    String getStringFromName(final String p0);
    
    String formatStringFromID(final int p0, final String[] p1, final long p2);
    
    String formatStringFromName(final String p0, final String[] p1, final long p2);
    
    nsISimpleEnumerator getSimpleEnumeration();
}
