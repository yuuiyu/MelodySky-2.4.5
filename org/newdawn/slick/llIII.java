//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.awt.*;

class llIII extends Canvas
{
    final /* synthetic */ AppletGameContainer this$0;
    
    llIII(final AppletGameContainer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public final void addNotify() {
        super.addNotify();
        this.this$0.startLWJGL();
    }
    
    @Override
    public final void removeNotify() {
        AppletGameContainer.access$000(this.this$0);
        super.removeNotify();
    }
}
