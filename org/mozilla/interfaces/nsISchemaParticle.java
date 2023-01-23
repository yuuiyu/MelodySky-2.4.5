//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsISchemaParticle extends nsISchemaComponent
{
    public static final String NS_ISCHEMAPARTICLE_IID = "{3c14a029-6f4e-11d5-9b46-000064657374}";
    public static final int PARTICLE_TYPE_ELEMENT = 1;
    public static final int PARTICLE_TYPE_MODEL_GROUP = 2;
    public static final int PARTICLE_TYPE_ANY = 3;
    public static final long OCCURRENCE_UNBOUNDED = 4294967295L;
    
    String getName();
    
    int getParticleType();
    
    long getMinOccurs();
    
    long getMaxOccurs();
}
