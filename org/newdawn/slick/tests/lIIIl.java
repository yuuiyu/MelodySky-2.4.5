//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.tests;

import org.newdawn.slick.*;

class lIIIl implements Runnable
{
    final /* synthetic */ GameContainer val$container;
    final /* synthetic */ CachedRenderTest this$0;
    
    lIIIl(final CachedRenderTest this$0, final GameContainer val$container) {
        this.this$0 = this$0;
        this.val$container = val$container;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 100; ++i) {
            final int c = i + 100;
            this.val$container.getGraphics().setColor(new Color(c, c, c, c));
            this.val$container.getGraphics().drawOval((float)(i * 5 + 50), (float)(i * 3 + 50), 100.0f, 100.0f);
        }
    }
}
