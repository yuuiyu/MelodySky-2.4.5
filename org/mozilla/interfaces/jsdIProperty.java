//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface jsdIProperty extends jsdIEphemeral
{
    public static final String JSDIPROPERTY_IID = "{b8816e56-1dd1-11b2-81dc-8ba99a833d9e}";
    public static final long FLAG_ENUMERATE = 1L;
    public static final long FLAG_READONLY = 2L;
    public static final long FLAG_PERMANENT = 4L;
    public static final long FLAG_ALIAS = 8L;
    public static final long FLAG_ARGUMENT = 16L;
    public static final long FLAG_VARIABLE = 32L;
    public static final long FLAG_EXCEPTION = 64L;
    public static final long FLAG_ERROR = 128L;
    public static final long FLAG_HINTED = 2048L;
    
    long getFlags();
    
    jsdIValue getAlias();
    
    jsdIValue getName();
    
    jsdIValue getValue();
    
    long getVarArgSlot();
}
