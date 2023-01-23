//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFPurgeableDataSource extends nsISupports
{
    public static final String NS_IRDFPURGEABLEDATASOURCE_IID = "{951700f0-fed0-11d2-bdd9-00104bde6048}";
    
    boolean mark(final nsIRDFResource p0, final nsIRDFResource p1, final nsIRDFNode p2, final boolean p3);
    
    void sweep();
}
