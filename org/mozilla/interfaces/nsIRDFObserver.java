//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFObserver extends nsISupports
{
    public static final String NS_IRDFOBSERVER_IID = "{3cc75360-484a-11d2-bc16-00805f912fe7}";
    
    void onAssert(final nsIRDFDataSource p0, final nsIRDFResource p1, final nsIRDFResource p2, final nsIRDFNode p3);
    
    void onUnassert(final nsIRDFDataSource p0, final nsIRDFResource p1, final nsIRDFResource p2, final nsIRDFNode p3);
    
    void onChange(final nsIRDFDataSource p0, final nsIRDFResource p1, final nsIRDFResource p2, final nsIRDFNode p3, final nsIRDFNode p4);
    
    void onMove(final nsIRDFDataSource p0, final nsIRDFResource p1, final nsIRDFResource p2, final nsIRDFResource p3, final nsIRDFNode p4);
    
    void onBeginUpdateBatch(final nsIRDFDataSource p0);
    
    void onEndUpdateBatch(final nsIRDFDataSource p0);
}
