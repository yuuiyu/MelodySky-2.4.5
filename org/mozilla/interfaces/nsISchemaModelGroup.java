//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaModelGroup extends nsISchemaParticle
{
    public static final String NS_ISCHEMAMODELGROUP_IID = "{3c14a02a-6f4e-11d5-9b46-000064657374}";
    public static final int COMPOSITOR_ALL = 1;
    public static final int COMPOSITOR_SEQUENCE = 2;
    public static final int COMPOSITOR_CHOICE = 3;
    
    int getCompositor();
    
    long getParticleCount();
    
    nsISchemaParticle getParticle(final long p0);
    
    nsISchemaElement getElementByName(final String p0);
}
