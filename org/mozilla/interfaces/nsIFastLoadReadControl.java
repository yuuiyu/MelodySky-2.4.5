//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIFastLoadReadControl extends nsIFastLoadFileControl
{
    public static final String NS_IFASTLOADREADCONTROL_IID = "{652ecec6-d40b-45b6-afef-641d6c63a35b}";
    
    long computeChecksum();
    
    nsISimpleEnumerator getDependencies();
}
