//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;

class llIIII extends SelectionAdapter
{
    final /* synthetic */ CTabFolder this$0;
    
    llIIII(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void widgetSelected(final SelectionEvent e) {
        final MenuItem menuItem = (MenuItem)e.widget;
        final int index = this.this$0.indexOf((CTabItem)menuItem.getData("CTabFolder_showList_Index"));
        this.this$0.setSelection(index, true);
    }
}
