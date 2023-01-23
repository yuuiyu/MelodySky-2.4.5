//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;

class lIIlIlI implements Runnable
{
    final /* synthetic */ Display val$display;
    final /* synthetic */ StyledText this$0;
    
    lIIlIlI(final StyledText this$0, final Display val$display) {
        this.this$0 = this$0;
        this.val$display = val$display;
    }
    
    @Override
    public void run() {
        if (this.this$0.isDisposed()) {
            return;
        }
        if (this.this$0.autoScrollDirection == 1024) {
            if (this.this$0.blockSelection) {
                final int verticalScrollOffset = this.this$0.getVerticalScrollOffset();
                final int y = this.this$0.blockYLocation - verticalScrollOffset;
                final int max = this.this$0.renderer.getHeight() - verticalScrollOffset - this.this$0.clientAreaHeight;
                final int pixels = Math.min(this.this$0.autoScrollDistance, Math.max(0, max));
                if (pixels != 0) {
                    this.this$0.setBlockSelectionLocation(this.this$0.blockXLocation - this.this$0.horizontalScrollOffset, y + pixels, true);
                    this.this$0.scrollVertical(pixels, true);
                }
            }
            else {
                this.this$0.doSelectionPageDown(this.this$0.autoScrollDistance);
            }
            this.val$display.timerExec(50, this);
        }
    }
}
