//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIArray extends nsISupports
{
    public static final String NS_IARRAY_IID = "{114744d9-c369-456e-b55a-52fe52880d2d}";
    
    long getLength();
    
    nsISupports queryElementAt(final long p0, final String p1);
    
    long indexOf(final long p0, final nsISupports p1);
    
    nsISimpleEnumerator enumerate();
}
