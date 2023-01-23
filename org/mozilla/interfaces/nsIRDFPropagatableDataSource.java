//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIRDFPropagatableDataSource extends nsISupports
{
    public static final String NS_IRDFPROPAGATABLEDATASOURCE_IID = "{5a9b4770-9fcb-4307-a12e-4b6708e78b97}";
    
    boolean getPropagateChanges();
    
    void setPropagateChanges(final boolean p0);
}
