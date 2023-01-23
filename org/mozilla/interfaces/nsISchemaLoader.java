//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaLoader extends nsISupports
{
    public static final String NS_ISCHEMALOADER_IID = "{9b2f0b4a-8f00-4a78-961a-7e84ed49b0b6}";
    
    nsISchema load(final String p0);
    
    void loadAsync(final String p0, final nsISchemaLoadListener p1);
    
    nsISchema processSchemaElement(final nsIDOMElement p0, final nsIWebServiceErrorHandler p1);
}
