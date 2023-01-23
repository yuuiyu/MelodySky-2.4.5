//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIValue extends jsdIEphemeral
{
    public static final String JSDIVALUE_IID = "{b7964304-1dd1-11b2-ba20-cf4205772e9d}";
    public static final long TYPE_BOOLEAN = 0L;
    public static final long TYPE_DOUBLE = 1L;
    public static final long TYPE_INT = 2L;
    public static final long TYPE_FUNCTION = 3L;
    public static final long TYPE_NULL = 4L;
    public static final long TYPE_OBJECT = 5L;
    public static final long TYPE_STRING = 6L;
    public static final long TYPE_VOID = 7L;
    
    boolean getIsNative();
    
    boolean getIsNumber();
    
    boolean getIsPrimitive();
    
    long getJsType();
    
    jsdIValue getJsPrototype();
    
    jsdIValue getJsParent();
    
    String getJsClassName();
    
    jsdIValue getJsConstructor();
    
    String getJsFunctionName();
    
    boolean getBooleanValue();
    
    double getDoubleValue();
    
    int getIntValue();
    
    jsdIObject getObjectValue();
    
    String getStringValue();
    
    int getPropertyCount();
    
    void getProperties(final jsdIProperty[][] p0, final long[] p1);
    
    jsdIProperty getProperty(final String p0);
    
    void refresh();
    
    void getWrappedValue();
}
