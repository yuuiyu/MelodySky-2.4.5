//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleControlListener extends SWTEventListener
{
    void getChildAtPoint(final AccessibleControlEvent p0);
    
    void getLocation(final AccessibleControlEvent p0);
    
    void getChild(final AccessibleControlEvent p0);
    
    void getChildCount(final AccessibleControlEvent p0);
    
    void getDefaultAction(final AccessibleControlEvent p0);
    
    void getFocus(final AccessibleControlEvent p0);
    
    void getRole(final AccessibleControlEvent p0);
    
    void getSelection(final AccessibleControlEvent p0);
    
    void getState(final AccessibleControlEvent p0);
    
    void getValue(final AccessibleControlEvent p0);
    
    void getChildren(final AccessibleControlEvent p0);
}
