//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;

class lIIlIII implements Runnable
{
    final /* synthetic */ StyledTextRenderer this$0;
    
    lIIlIII(final StyledTextRenderer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.styledText == null) {
            return;
        }
        final long start = System.currentTimeMillis();
        int i;
        for (i = 0; i < this.this$0.lineCount; ++i) {
            final StyledTextRenderer.LineSizeInfo line = this.this$0.getLineSize(i);
            if (line.needsRecalculateSize()) {
                this.this$0.calculate(i, 1);
                if (System.currentTimeMillis() - start > 50L) {
                    break;
                }
            }
        }
        if (i < this.this$0.lineCount) {
            final Display display = this.this$0.styledText.getDisplay();
            display.asyncExec(this);
        }
        else {
            this.this$0.idleRunning = false;
            this.this$0.styledText.setScrollBars(true);
            final ScrollBar bar = this.this$0.styledText.getVerticalBar();
            if (bar != null) {
                bar.setSelection(this.this$0.styledText.getVerticalScrollOffset());
            }
        }
    }
}
