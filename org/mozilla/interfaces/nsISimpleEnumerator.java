//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISimpleEnumerator extends nsISupports
{
    public static final String NS_ISIMPLEENUMERATOR_IID = "{d1899240-f9d2-11d2-bdd6-000064657374}";
    
    boolean hasMoreElements();
    
    nsISupports getNext();
}
