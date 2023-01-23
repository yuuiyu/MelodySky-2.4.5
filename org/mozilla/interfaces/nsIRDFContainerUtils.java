//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFContainerUtils extends nsISupports
{
    public static final String NS_IRDFCONTAINERUTILS_IID = "{d4214e91-fb94-11d2-bdd8-00104bde6048}";
    
    boolean isOrdinalProperty(final nsIRDFResource p0);
    
    nsIRDFResource indexToOrdinalResource(final int p0);
    
    int ordinalResourceToIndex(final nsIRDFResource p0);
    
    boolean isContainer(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    boolean isEmpty(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    boolean isBag(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    boolean isSeq(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    boolean isAlt(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    nsIRDFContainer makeBag(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    nsIRDFContainer makeSeq(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    nsIRDFContainer makeAlt(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    int indexOf(final nsIRDFDataSource p0, final nsIRDFResource p1, final nsIRDFNode p2);
}
