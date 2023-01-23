//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFRemoteDataSource extends nsISupports
{
    public static final String NS_IRDFREMOTEDATASOURCE_IID = "{1d297320-27f7-11d3-be01-000064657374}";
    
    boolean getLoaded();
    
    void init(final String p0);
    
    void refresh(final boolean p0);
    
    void flush();
    
    void flushTo(final String p0);
}
