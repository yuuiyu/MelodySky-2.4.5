//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageService extends nsISupports
{
    public static final String MOZISTORAGESERVICE_IID = "{a4a0cad9-e0da-4379-bee4-2feef3dddc7e}";
    
    mozIStorageConnection openSpecialDatabase(final String p0);
    
    mozIStorageConnection openDatabase(final nsIFile p0);
}
