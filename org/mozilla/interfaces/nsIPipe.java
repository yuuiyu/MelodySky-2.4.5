//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIPipe extends nsISupports
{
    public static final String NS_IPIPE_IID = "{f4211abc-61b3-11d4-9877-00c04fa0cf4a}";
    
    void init(final boolean p0, final boolean p1, final long p2, final long p3, final nsIMemory p4);
    
    nsIAsyncInputStream getInputStream();
    
    nsIAsyncOutputStream getOutputStream();
}
