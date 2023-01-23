//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFContainer extends nsISupports
{
    public static final String NS_IRDFCONTAINER_IID = "{d4214e90-fb94-11d2-bdd8-00104bde6048}";
    
    nsIRDFDataSource getDataSource();
    
    nsIRDFResource getResource();
    
    void init(final nsIRDFDataSource p0, final nsIRDFResource p1);
    
    int getCount();
    
    nsISimpleEnumerator getElements();
    
    void appendElement(final nsIRDFNode p0);
    
    void removeElement(final nsIRDFNode p0, final boolean p1);
    
    void insertElementAt(final nsIRDFNode p0, final int p1, final boolean p2);
    
    nsIRDFNode removeElementAt(final int p0, final boolean p1);
    
    int indexOf(final nsIRDFNode p0);
}
