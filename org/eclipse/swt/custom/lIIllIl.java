//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;

class lIIllIl implements Runnable
{
    final /* synthetic */ Display val$display;
    final /* synthetic */ StyledText this$0;
    
    lIIllIl(final StyledText this$0, final Display val$display) {
        this.this$0 = this$0;
        this.val$display = val$display;
    }
    
    @Override
    public void run() {
        if (this.this$0.isDisposed()) {
            return;
        }
        if (this.this$0.autoScrollDirection == 16777219) {
            if (this.this$0.blockSelection) {
                final int x = this.this$0.blockXLocation - this.this$0.horizontalScrollOffset;
                final int pixels = Math.max(-this.this$0.autoScrollDistance, -this.this$0.horizontalScrollOffset);
                if (pixels != 0) {
                    this.this$0.setBlockSelectionLocation(x + pixels, this.this$0.blockYLocation - this.this$0.getVerticalScrollOffset(), true);
                    this.this$0.scrollHorizontal(pixels, true);
                }
            }
            else {
                this.this$0.doVisualPrevious();
                this.this$0.setMouseWordSelectionAnchor();
                this.this$0.doMouseSelection();
            }
            this.val$display.timerExec(10, this);
        }
    }
}
