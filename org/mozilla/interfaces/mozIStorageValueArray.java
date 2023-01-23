//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface mozIStorageValueArray extends nsISupports
{
    public static final String MOZISTORAGEVALUEARRAY_IID = "{07b5b93e-113c-4150-863c-d247b003a55d}";
    public static final int VALUE_TYPE_NULL = 0;
    public static final int VALUE_TYPE_INTEGER = 1;
    public static final int VALUE_TYPE_FLOAT = 2;
    public static final int VALUE_TYPE_TEXT = 3;
    public static final int VALUE_TYPE_BLOB = 4;
    
    long getNumEntries();
    
    int getTypeOfIndex(final long p0);
    
    int getInt32(final long p0);
    
    long getInt64(final long p0);
    
    double getDouble(final long p0);
    
    String getUTF8String(final long p0);
    
    String getString(final long p0);
    
    void getBlob(final long p0, final long[] p1, final byte[][] p2);
    
    boolean getIsNull(final long p0);
}
