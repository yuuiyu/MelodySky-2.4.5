//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMNSFeatureFactory extends nsISupports
{
    public static final String NS_IDOMNSFEATUREFACTORY_IID = "{dc5ba787-b648-4b01-a8e7-b293ffb044ef}";
    
    boolean hasFeature(final nsISupports p0, final String p1, final String p2);
    
    nsISupports getFeature(final nsISupports p0, final String p1, final String p2);
}
