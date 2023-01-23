//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

class lIllll implements TreeListener
{
    final Runnable runnable;
    final /* synthetic */ TreeEditor this$0;
    
    lIllll(final TreeEditor this$0) {
        this.this$0 = this$0;
        this.runnable = (() -> {
            if (this.this$0.editor != null && !this.this$0.editor.isDisposed() && !this.this$0.tree.isDisposed()) {
                this.this$0.layout();
                this.this$0.editor.setVisible(true);
            }
        });
    }
    
    @Override
    public void treeCollapsed(final TreeEvent e) {
        if (this.this$0.editor == null || this.this$0.editor.isDisposed()) {
            return;
        }
        this.this$0.editor.setVisible(false);
        e.display.asyncExec(this.runnable);
    }
    
    @Override
    public void treeExpanded(final TreeEvent e) {
        if (this.this$0.editor == null || this.this$0.editor.isDisposed()) {
            return;
        }
        this.this$0.editor.setVisible(false);
        e.display.asyncExec(this.runnable);
    }
}
