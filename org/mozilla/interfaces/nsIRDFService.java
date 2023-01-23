//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFService extends nsISupports
{
    public static final String NS_IRDFSERVICE_IID = "{bfd05261-834c-11d2-8eac-00805f29f370}";
    
    nsIRDFResource getResource(final String p0);
    
    nsIRDFResource getUnicodeResource(final String p0);
    
    nsIRDFResource getAnonymousResource();
    
    nsIRDFLiteral getLiteral(final String p0);
    
    nsIRDFDate getDateLiteral(final long p0);
    
    nsIRDFInt getIntLiteral(final int p0);
    
    boolean isAnonymousResource(final nsIRDFResource p0);
    
    void registerResource(final nsIRDFResource p0, final boolean p1);
    
    void unregisterResource(final nsIRDFResource p0);
    
    void registerDataSource(final nsIRDFDataSource p0, final boolean p1);
    
    void unregisterDataSource(final nsIRDFDataSource p0);
    
    nsIRDFDataSource getDataSource(final String p0);
    
    nsIRDFDataSource getDataSourceBlocking(final String p0);
}
