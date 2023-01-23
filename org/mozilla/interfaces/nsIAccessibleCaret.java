//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIAccessibleCaret extends nsISupports
{
    public static final String NS_IACCESSIBLECARET_IID = "{9124c666-6133-4be6-b3ed-dd0ec35f1e64}";
    
    void attachNewSelectionListener(final nsIDOMNode p0);
    
    void removeSelectionListener();
}
