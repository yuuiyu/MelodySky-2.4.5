//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIControllerCommandGroup extends nsISupports
{
    public static final String NS_ICONTROLLERCOMMANDGROUP_IID = "{9f82c404-1c7b-11d5-a73c-eca43ca836fc}";
    
    void addCommandToGroup(final String p0, final String p1);
    
    void removeCommandFromGroup(final String p0, final String p1);
    
    boolean isCommandInGroup(final String p0, final String p1);
    
    nsISimpleEnumerator getGroupsEnumerator();
    
    nsISimpleEnumerator getEnumeratorForGroup(final String p0);
}
