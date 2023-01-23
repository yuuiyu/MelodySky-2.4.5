//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageStatementWrapper extends nsISupports
{
    public static final String MOZISTORAGESTATEMENTWRAPPER_IID = "{eee6f7c9-5586-4eaf-b35c-dca987c4ffd1}";
    
    void initialize(final mozIStorageStatement p0);
    
    mozIStorageStatement getStatement();
    
    void reset();
    
    boolean step();
    
    void execute();
    
    mozIStorageStatementRow getRow();
    
    mozIStorageStatementParams getParams();
}
