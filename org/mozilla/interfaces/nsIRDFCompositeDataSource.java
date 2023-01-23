//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFCompositeDataSource extends nsIRDFDataSource
{
    public static final String NS_IRDFCOMPOSITEDATASOURCE_IID = "{96343820-307c-11d2-bc15-00805f912fe7}";
    
    boolean getAllowNegativeAssertions();
    
    void setAllowNegativeAssertions(final boolean p0);
    
    boolean getCoalesceDuplicateArcs();
    
    void setCoalesceDuplicateArcs(final boolean p0);
    
    void addDataSource(final nsIRDFDataSource p0);
    
    void removeDataSource(final nsIRDFDataSource p0);
    
    nsISimpleEnumerator getDataSources();
}
