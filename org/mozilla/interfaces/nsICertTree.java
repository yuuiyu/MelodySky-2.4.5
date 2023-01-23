//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsICertTree extends nsITreeView
{
    public static final String NS_ICERTTREE_IID = "{4ea60761-31d6-491d-9e34-4b53a26c416c}";
    
    void loadCerts(final long p0);
    
    void loadCertsFromCache(final nsINSSCertCache p0, final long p1);
    
    nsIX509Cert getCert(final long p0);
    
    void removeCert(final long p0);
}
