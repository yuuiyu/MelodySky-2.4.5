//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.events;

import java.util.function.*;

class lIIlll extends SelectionAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ SelectionListener this$0;
    
    lIIlll(final SelectionListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void widgetDefaultSelected(final SelectionEvent e) {
        this.val$c.accept(e);
    }
}
