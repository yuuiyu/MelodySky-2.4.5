//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMMimeType extends nsISupports
{
    public static final String NS_IDOMMIMETYPE_IID = "{f6134682-f28b-11d2-8360-c90899049c3c}";
    
    String getDescription();
    
    nsIDOMPlugin getEnabledPlugin();
    
    String getSuffixes();
    
    String getType();
}
