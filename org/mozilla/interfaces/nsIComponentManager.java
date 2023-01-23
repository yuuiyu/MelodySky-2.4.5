//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIComponentManager extends nsISupports
{
    public static final String NS_ICOMPONENTMANAGER_IID = "{a88e5a60-205a-4bb1-94e1-2628daf51eae}";
    
    nsISupports getClassObject(final String p0, final String p1);
    
    nsISupports getClassObjectByContractID(final String p0, final String p1);
    
    nsISupports createInstance(final String p0, final nsISupports p1, final String p2);
    
    nsISupports createInstanceByContractID(final String p0, final nsISupports p1, final String p2);
}
