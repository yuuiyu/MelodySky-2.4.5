//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIWindowsRegKey extends nsISupports
{
    public static final String NS_IWINDOWSREGKEY_IID = "{2555b930-d64f-437e-9be7-0a2cb252c1f4}";
    public static final long ROOT_KEY_CLASSES_ROOT = 2147483648L;
    public static final long ROOT_KEY_CURRENT_USER = 2147483649L;
    public static final long ROOT_KEY_LOCAL_MACHINE = 2147483650L;
    public static final long ACCESS_BASIC = 131072L;
    public static final long ACCESS_QUERY_VALUE = 1L;
    public static final long ACCESS_SET_VALUE = 2L;
    public static final long ACCESS_CREATE_SUB_KEY = 4L;
    public static final long ACCESS_ENUMERATE_SUB_KEYS = 8L;
    public static final long ACCESS_NOTIFY = 16L;
    public static final long ACCESS_READ = 131097L;
    public static final long ACCESS_WRITE = 131078L;
    public static final long ACCESS_ALL = 131103L;
    public static final long TYPE_NONE = 0L;
    public static final long TYPE_STRING = 1L;
    public static final long TYPE_BINARY = 3L;
    public static final long TYPE_INT = 4L;
    public static final long TYPE_INT64 = 11L;
    
    void close();
    
    void open(final long p0, final String p1, final long p2);
    
    void create(final long p0, final String p1, final long p2);
    
    nsIWindowsRegKey openChild(final String p0, final long p1);
    
    nsIWindowsRegKey createChild(final String p0, final long p1);
    
    long getChildCount();
    
    String getChildName(final long p0);
    
    boolean hasChild(final String p0);
    
    long getValueCount();
    
    String getValueName(final long p0);
    
    boolean hasValue(final String p0);
    
    void removeChild(final String p0);
    
    void removeValue(final String p0);
    
    long getValueType(final String p0);
    
    String readStringValue(final String p0);
    
    long readIntValue(final String p0);
    
    double readInt64Value(final String p0);
    
    String readBinaryValue(final String p0);
    
    void writeStringValue(final String p0, final String p1);
    
    void writeIntValue(final String p0, final long p1);
    
    void writeInt64Value(final String p0, final double p1);
    
    void writeBinaryValue(final String p0, final String p1);
    
    void startWatching(final boolean p0);
    
    void stopWatching();
    
    boolean isWatching();
    
    boolean hasChanged();
}
