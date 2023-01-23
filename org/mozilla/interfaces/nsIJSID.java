//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIJSID extends nsISupports
{
    public static final String NS_IJSID_IID = "{c86ae131-d101-11d2-9841-006008962422}";
    
    String getName();
    
    String getNumber();
    
    boolean getValid();
    
    boolean _equals(final nsIJSID p0);
    
    void initialize(final String p0);
    
    String toString();
}
