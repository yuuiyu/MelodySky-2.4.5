//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import java.util.function.*;

class lIlIl extends LocationAdapter
{
    final /* synthetic */ Consumer val$c;
    final /* synthetic */ LocationListener this$0;
    
    lIlIl(final LocationListener this$0, final Consumer val$c) {
        this.this$0 = this$0;
        this.val$c = val$c;
    }
    
    @Override
    public void changed(final LocationEvent e) {
        this.val$c.accept(e);
    }
}
