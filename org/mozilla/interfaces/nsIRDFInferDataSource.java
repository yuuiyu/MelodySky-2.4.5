//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFInferDataSource extends nsIRDFDataSource
{
    public static final String NS_IRDFINFERDATASOURCE_IID = "{2b04860f-4017-40f6-8a57-784a1e35077a}";
    
    nsIRDFDataSource getBaseDataSource();
    
    void setBaseDataSource(final nsIRDFDataSource p0);
}
