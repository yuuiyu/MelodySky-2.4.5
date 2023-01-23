//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIException extends nsISupports
{
    public static final String NS_IEXCEPTION_IID = "{f3a8d3b4-c424-4edc-8bf6-8974c983ba78}";
    
    String getMessage();
    
    long getResult();
    
    String getName();
    
    String getFilename();
    
    long getLineNumber();
    
    long getColumnNumber();
    
    nsIStackFrame getLocation();
    
    nsIException getInner();
    
    nsISupports getData();
    
    String toString();
}
