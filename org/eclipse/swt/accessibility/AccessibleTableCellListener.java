//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.*;

public interface AccessibleTableCellListener extends SWTEventListener
{
    void getColumnSpan(final AccessibleTableCellEvent p0);
    
    void getColumnHeaders(final AccessibleTableCellEvent p0);
    
    void getColumnIndex(final AccessibleTableCellEvent p0);
    
    void getRowSpan(final AccessibleTableCellEvent p0);
    
    void getRowHeaders(final AccessibleTableCellEvent p0);
    
    void getRowIndex(final AccessibleTableCellEvent p0);
    
    void getTable(final AccessibleTableCellEvent p0);
    
    void isSelected(final AccessibleTableCellEvent p0);
}
