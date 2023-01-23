//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.internal;

public interface IOleNativeComponent
{
    void invokeOleFunction(final String p0, final Object... p1);
    
    void invokeOleFunction(final String[] p0, final Object... p1);
    
    Object invokeOleFunctionWithResult(final String p0, final Object... p1);
    
    Object invokeOleFunctionWithResult(final String[] p0, final Object... p1);
    
    void setOleProperty(final String p0, final Object... p1);
    
    void setOleProperty(final String[] p0, final Object... p1);
    
    Object getOleProperty(final String p0, final Object... p1);
    
    Object getOleProperty(final String[] p0, final Object... p1);
    
    void dumpOleInterfaceDefinitions();
}
