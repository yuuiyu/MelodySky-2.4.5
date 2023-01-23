//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleActionListener extends SWTEventListener
{
    void getActionCount(final AccessibleActionEvent p0);
    
    void doAction(final AccessibleActionEvent p0);
    
    void getDescription(final AccessibleActionEvent p0);
    
    void getKeyBinding(final AccessibleActionEvent p0);
    
    void getName(final AccessibleActionEvent p0);
}
