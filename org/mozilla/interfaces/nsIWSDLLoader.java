//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWSDLLoader extends nsISupports
{
    public static final String NS_IWSDLLOADER_IID = "{0458dac5-65de-11d5-9b42-00104bdf5339}";
    
    nsIWSDLPort load(final String p0, final String p1);
    
    void loadAsync(final String p0, final String p1, final nsIWSDLLoadListener p2);
}
