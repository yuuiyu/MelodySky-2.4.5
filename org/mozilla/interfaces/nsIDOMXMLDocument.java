//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDOMXMLDocument extends nsIDOMDocument
{
    public static final String NS_IDOMXMLDOCUMENT_IID = "{8816d003-e7c8-4065-8827-829b8d07b6e0}";
    
    boolean getAsync();
    
    void setAsync(final boolean p0);
    
    boolean load(final String p0);
    
    nsIDOMRange evaluateFIXptr(final String p0);
    
    nsIXPointerResult evaluateXPointer(final String p0);
}
