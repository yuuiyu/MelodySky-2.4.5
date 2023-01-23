//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIStackFrame extends nsISupports
{
    public static final String NS_ISTACKFRAME_IID = "{91d82105-7c62-4f8b-9779-154277c0ee90}";
    
    long getLanguage();
    
    String getLanguageName();
    
    String getFilename();
    
    String getName();
    
    int getLineNumber();
    
    String getSourceLine();
    
    nsIStackFrame getCaller();
    
    String toString();
}
