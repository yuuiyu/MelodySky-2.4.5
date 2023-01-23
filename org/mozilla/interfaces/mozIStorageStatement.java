//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageStatement extends mozIStorageValueArray
{
    public static final String MOZISTORAGESTATEMENT_IID = "{1f39bc95-090d-40a5-9dee-6d5a591e48bf}";
    public static final int MOZ_STORAGE_STATEMENT_INVALID = 0;
    public static final int MOZ_STORAGE_STATEMENT_READY = 1;
    public static final int MOZ_STORAGE_STATEMENT_EXECUTING = 2;
    
    void initialize(final mozIStorageConnection p0, final String p1);
    
    mozIStorageStatement _clone();
    
    long getParameterCount();
    
    String getParameterName(final long p0);
    
    long[] getParameterIndexes(final String p0, final long[] p1);
    
    long getColumnCount();
    
    String getColumnName(final long p0);
    
    void reset();
    
    void bindUTF8StringParameter(final long p0, final String p1);
    
    void bindStringParameter(final long p0, final String p1);
    
    void bindDoubleParameter(final long p0, final double p1);
    
    void bindInt32Parameter(final long p0, final int p1);
    
    void bindInt64Parameter(final long p0, final long p1);
    
    void bindNullParameter(final long p0);
    
    void bindBlobParameter(final long p0, final byte[] p1, final long p2);
    
    void execute();
    
    boolean executeStep();
    
    int getState();
}
