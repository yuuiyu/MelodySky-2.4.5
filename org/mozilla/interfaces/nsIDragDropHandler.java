//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.interfaces;

public interface nsIDragDropHandler extends nsISupports
{
    public static final String NS_IDRAGDROPHANDLER_IID = "{4f418f58-f834-4736-a755-e0395bedca9d}";
    
    void hookupTo(final nsIDOMEventTarget p0, final nsIWebNavigation p1);
    
    void detach();
}
