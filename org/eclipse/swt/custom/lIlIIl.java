//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;

class lIlIIl implements Runnable
{
    final /* synthetic */ CTabFolder this$0;
    
    lIlIIl(final CTabFolder this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void run() {
        if (this.this$0.isDisposed()) {
            return;
        }
        if (this.this$0.hovering) {
            final Display display = this.this$0.getDisplay();
            final Control c = display.getCursorControl();
            boolean reschedule = false;
            if (c != null) {
                for (final Control control : this.this$0.controls) {
                    Control temp = c;
                    do {
                        if (temp.equals(control)) {
                            reschedule = true;
                        }
                        else {
                            temp = temp.getParent();
                            if (temp == null) {
                                break;
                            }
                            if (temp.equals(this.this$0)) {
                                break;
                            }
                            continue;
                        }
                    } while (!reschedule);
                    if (reschedule) {
                        break;
                    }
                }
            }
            if (reschedule && this.this$0.hoverTimerRunning) {
                display.timerExec(2000, this);
            }
            else {
                this.this$0.hovering = false;
                this.this$0.updateItems();
            }
        }
    }
}
