//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIProfileMigrator extends nsISupports
{
    public static final String NS_IPROFILEMIGRATOR_IID = "{24ce8b9d-b7ff-4279-aef4-26e158f03e34}";
    
    void _import();
    
    void migrate(final nsIProfileStartup p0);
}
