//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.lwjgl.opengl.*;
import java.awt.*;

class lIlIl extends Thread
{
    final /* synthetic */ AppletGameContainer this$0;
    
    lIlIl(final AppletGameContainer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.canvas.start();
        }
        catch (Exception e) {
            e.printStackTrace();
            if (Display.isCreated()) {
                Display.destroy();
            }
            this.this$0.displayParent.setVisible(false);
            this.this$0.add((Component)new AppletGameContainer.ConsolePanel(this.this$0, e));
            this.this$0.validate();
        }
    }
}
