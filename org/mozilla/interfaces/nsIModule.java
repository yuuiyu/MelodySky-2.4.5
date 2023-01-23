//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIModule extends nsISupports
{
    public static final String NS_IMODULE_IID = "{7392d032-5371-11d3-994e-00805fd26fee}";
    
    nsISupports getClassObject(final nsIComponentManager p0, final String p1, final String p2);
    
    void registerSelf(final nsIComponentManager p0, final nsIFile p1, final String p2, final String p3);
    
    void unregisterSelf(final nsIComponentManager p0, final nsIFile p1, final String p2);
    
    boolean canUnload(final nsIComponentManager p0);
}
