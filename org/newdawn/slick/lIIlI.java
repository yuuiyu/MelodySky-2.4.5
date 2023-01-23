//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

class lIIlI implements Runnable
{
    final /* synthetic */ CanvasGameContainer this$0;
    
    lIIlI(final CanvasGameContainer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        try {
            this.this$0.container.gameLoop();
        }
        catch (SlickException e) {
            e.printStackTrace();
        }
        this.this$0.container.checkDimensions();
        CanvasGameContainer.access$000(this.this$0);
    }
}
