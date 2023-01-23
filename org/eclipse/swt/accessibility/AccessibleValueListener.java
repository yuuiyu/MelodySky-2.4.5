//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleValueListener extends SWTEventListener
{
    void getCurrentValue(final AccessibleValueEvent p0);
    
    void setCurrentValue(final AccessibleValueEvent p0);
    
    void getMaximumValue(final AccessibleValueEvent p0);
    
    void getMinimumValue(final AccessibleValueEvent p0);
}
