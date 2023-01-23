//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleEditableTextListener extends SWTEventListener
{
    void copyText(final AccessibleEditableTextEvent p0);
    
    void cutText(final AccessibleEditableTextEvent p0);
    
    void pasteText(final AccessibleEditableTextEvent p0);
    
    void replaceText(final AccessibleEditableTextEvent p0);
    
    void setTextAttributes(final AccessibleTextAttributeEvent p0);
}
