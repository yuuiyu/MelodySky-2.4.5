//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDocumentCharsetInfo extends nsISupports
{
    public static final String NS_IDOCUMENTCHARSETINFO_IID = "{2d40b291-01e1-11d4-9d0e-0050040007b2}";
    
    nsIAtom getForcedCharset();
    
    void setForcedCharset(final nsIAtom p0);
    
    boolean getForcedDetector();
    
    void setForcedDetector(final boolean p0);
    
    nsIAtom getParentCharset();
    
    void setParentCharset(final nsIAtom p0);
    
    int getParentCharsetSource();
    
    void setParentCharsetSource(final int p0);
}
