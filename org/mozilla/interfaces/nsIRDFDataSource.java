//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFDataSource extends nsISupports
{
    public static final String NS_IRDFDATASOURCE_IID = "{0f78da58-8321-11d2-8eac-00805f29f370}";
    
    String getURI();
    
    nsIRDFResource getSource(final nsIRDFResource p0, final nsIRDFNode p1, final boolean p2);
    
    nsISimpleEnumerator getSources(final nsIRDFResource p0, final nsIRDFNode p1, final boolean p2);
    
    nsIRDFNode getTarget(final nsIRDFResource p0, final nsIRDFResource p1, final boolean p2);
    
    nsISimpleEnumerator getTargets(final nsIRDFResource p0, final nsIRDFResource p1, final boolean p2);
    
    void _assert(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFNode p2, final boolean p3);
    
    void unassert(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFNode p2);
    
    void change(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFNode p2, final nsIRDFNode p3);
    
    void move(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFResource p2, final nsIRDFNode p3);
    
    boolean hasAssertion(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFNode p2, final boolean p3);
    
    void addObserver(final nsIRDFObserver p0);
    
    void removeObserver(final nsIRDFObserver p0);
    
    nsISimpleEnumerator arcLabelsIn(final nsIRDFNode p0);
    
    nsISimpleEnumerator arcLabelsOut(final nsIRDFResource p0);
    
    nsISimpleEnumerator getAllResources();
    
    boolean isCommandEnabled(final nsISupportsArray p0, final nsIRDFResource p1, final nsISupportsArray p2);
    
    void doCommand(final nsISupportsArray p0, final nsIRDFResource p1, final nsISupportsArray p2);
    
    nsISimpleEnumerator getAllCmds(final nsIRDFResource p0);
    
    boolean hasArcIn(final nsIRDFNode p0, final nsIRDFResource p1);
    
    boolean hasArcOut(final nsIRDFResource p0, final nsIRDFResource p1);
    
    void beginUpdateBatch();
    
    void endUpdateBatch();
}
