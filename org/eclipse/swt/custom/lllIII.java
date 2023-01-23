//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;

class lllIII implements Runnable
{
    final /* synthetic */ Display val$display;
    final /* synthetic */ StyledText this$0;
    
    lllIII(final StyledText this$0, final Display val$display) {
        this.this$0 = this$0;
        this.val$display = val$display;
    }
    
    @Override
    public void run() {
        if (this.this$0.isDisposed()) {
            return;
        }
        if (this.this$0.autoScrollDirection == 16777220) {
            if (this.this$0.blockSelection) {
                final int x = this.this$0.blockXLocation - this.this$0.horizontalScrollOffset;
                final int max = this.this$0.renderer.getWidth() - this.this$0.horizontalScrollOffset - this.this$0.clientAreaWidth;
                final int pixels = Math.min(this.this$0.autoScrollDistance, Math.max(0, max));
                if (pixels != 0) {
                    this.this$0.setBlockSelectionLocation(x + pixels, this.this$0.blockYLocation - this.this$0.getVerticalScrollOffset(), true);
                    this.this$0.scrollHorizontal(pixels, true);
                }
            }
            else {
                this.this$0.doVisualNext();
                this.this$0.setMouseWordSelectionAnchor();
                this.this$0.doMouseSelection();
            }
            this.val$display.timerExec(10, this);
        }
    }
}
