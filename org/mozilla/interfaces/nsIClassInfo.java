//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIClassInfo extends nsISupports
{
    public static final String NS_ICLASSINFO_IID = "{986c11d0-f340-11d4-9075-0010a4e73d9a}";
    public static final long SINGLETON = 1L;
    public static final long THREADSAFE = 2L;
    public static final long MAIN_THREAD_ONLY = 4L;
    public static final long DOM_OBJECT = 8L;
    public static final long PLUGIN_OBJECT = 16L;
    public static final long EAGER_CLASSINFO = 32L;
    public static final long CONTENT_NODE = 64L;
    public static final long RESERVED = 2147483648L;
    
    String[] getInterfaces(final long[] p0);
    
    nsISupports getHelperForLanguage(final long p0);
    
    String getContractID();
    
    String getClassDescription();
    
    String getClassID();
    
    long getImplementationLanguage();
    
    long getFlags();
}
