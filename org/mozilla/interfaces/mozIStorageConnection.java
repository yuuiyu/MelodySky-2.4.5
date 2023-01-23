//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageConnection extends nsISupports
{
    public static final String MOZISTORAGECONNECTION_IID = "{77015f88-bfc2-4669-b1c3-cc19fb07cd4e}";
    public static final int TRANSACTION_DEFERRED = 0;
    public static final int TRANSACTION_IMMEDIATE = 1;
    public static final int TRANSACTION_EXCLUSIVE = 2;
    
    boolean getConnectionReady();
    
    nsIFile getDatabaseFile();
    
    long getLastInsertRowID();
    
    int getLastError();
    
    String getLastErrorString();
    
    mozIStorageStatement createStatement(final String p0);
    
    void executeSimpleSQL(final String p0);
    
    boolean tableExists(final String p0);
    
    boolean indexExists(final String p0);
    
    boolean getTransactionInProgress();
    
    void beginTransaction();
    
    void beginTransactionAs(final int p0);
    
    void commitTransaction();
    
    void rollbackTransaction();
    
    void createTable(final String p0, final String p1);
    
    void createFunction(final String p0, final int p1, final mozIStorageFunction p2);
    
    void preload();
}
