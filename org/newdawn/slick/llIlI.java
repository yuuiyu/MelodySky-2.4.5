//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import org.lwjgl.opengl.*;
import java.awt.*;
import org.lwjgl.*;

class llIlI implements Runnable
{
    final /* synthetic */ CanvasGameContainer this$0;
    
    llIlI(final CanvasGameContainer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            Input.disableControllers();
            try {
                Display.setParent((Canvas)this.this$0);
            }
            catch (LWJGLException e) {
                throw new SlickException("Failed to setParent of canvas", (Throwable)e);
            }
            this.this$0.container.setup();
            CanvasGameContainer.access$000(this.this$0);
        }
        catch (SlickException e2) {
            e2.printStackTrace();
            System.exit(0);
        }
    }
}
