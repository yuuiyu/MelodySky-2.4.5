//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRegistry extends nsISupports
{
    public static final String NS_IREGISTRY_IID = "{5d41a440-8e37-11d2-8059-00600811a9c3}";
    public static final int None = 0;
    public static final int Users = 1;
    public static final int Common = 2;
    public static final int CurrentUser = 3;
    public static final int ApplicationComponentRegistry = 1;
    public static final int ApplicationRegistry = 2;
    public static final int ApplicationCustomRegistry = -1;
    public static final long String = 1L;
    public static final long Int32 = 2L;
    public static final long Bytes = 3L;
    public static final long File = 4L;
    
    void open(final nsIFile p0);
    
    void openWellKnownRegistry(final int p0);
    
    void flush();
    
    boolean isOpen();
    
    long addKey(final long p0, final String p1);
    
    long getKey(final long p0, final String p1);
    
    void removeKey(final long p0, final String p1);
    
    String getString(final long p0, final String p1);
    
    void setString(final long p0, final String p1, final String p2);
    
    String getStringUTF8(final long p0, final String p1);
    
    void setStringUTF8(final long p0, final String p1, final String p2);
    
    short[] getBytesUTF8(final long p0, final String p1, final long[] p2);
    
    void setBytesUTF8(final long p0, final String p1, final long p2, final short[] p3);
    
    int getInt(final long p0, final String p1);
    
    void setInt(final long p0, final String p1, final int p2);
    
    long getLongLong(final long p0, final String p1);
    
    void setLongLong(final long p0, final String p1, final long[] p2);
    
    long addSubtree(final long p0, final String p1);
    
    void removeSubtree(final long p0, final String p1);
    
    long getSubtree(final long p0, final String p1);
    
    long addSubtreeRaw(final long p0, final String p1);
    
    void removeSubtreeRaw(final long p0, final String p1);
    
    long getSubtreeRaw(final long p0, final String p1);
    
    nsIEnumerator enumerateSubtrees(final long p0);
    
    nsIEnumerator enumerateAllSubtrees(final long p0);
    
    nsIEnumerator enumerateValues(final long p0);
    
    long getValueType(final long p0, final String p1);
    
    long getValueLength(final long p0, final String p1);
    
    void deleteValue(final long p0, final String p1);
    
    short[] escapeKey(final short[] p0, final long p1, final long[] p2);
    
    short[] unescapeKey(final short[] p0, final long p1, final long[] p2);
    
    String getCurrentUserName();
    
    void setCurrentUserName(final String p0);
    
    void pack();
}
