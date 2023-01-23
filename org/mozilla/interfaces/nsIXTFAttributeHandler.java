//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIXTFAttributeHandler extends nsISupports
{
    public static final String NS_IXTFATTRIBUTEHANDLER_IID = "{72152f7f-7e8d-43fd-8477-3f29ae8d240d}";
    
    boolean handlesAttribute(final nsIAtom p0);
    
    void setAttribute(final nsIAtom p0, final String p1);
    
    void removeAttribute(final nsIAtom p0);
    
    String getAttribute(final nsIAtom p0);
    
    boolean hasAttribute(final nsIAtom p0);
    
    long getAttributeCount();
    
    nsIAtom getAttributeNameAt(final long p0);
}
