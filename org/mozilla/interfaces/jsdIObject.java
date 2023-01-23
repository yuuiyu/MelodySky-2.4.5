//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIObject extends nsISupports
{
    public static final String JSDIOBJECT_IID = "{d500e8b8-1dd1-11b2-89a1-cdf55d91cbbd}";
    
    String getCreatorURL();
    
    long getCreatorLine();
    
    String getConstructorURL();
    
    long getConstructorLine();
    
    jsdIValue getValue();
}
