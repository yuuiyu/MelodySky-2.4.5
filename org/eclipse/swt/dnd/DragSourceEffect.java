//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

public class DragSourceEffect extends DragSourceAdapter
{
    Control control;
    
    public DragSourceEffect(final Control control) {
        this.control = null;
        if (control == null) {
            SWT.error(4);
        }
        this.control = control;
    }
    
    public Control getControl() {
        return this.control;
    }
}
