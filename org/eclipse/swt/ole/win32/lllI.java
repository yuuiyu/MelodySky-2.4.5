//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.widgets.*;

class lllI implements Listener
{
    private int nestedFocusEvents;
    final /* synthetic */ OleClientSite this$0;
    
    lllI(final OleClientSite this$0) {
        this.this$0 = this$0;
        this.nestedFocusEvents = 0;
    }
    
    @Override
    public void handleEvent(final Event e) {
        switch (e.type) {
            case 10:
            case 11: {
                this.this$0.onResize(e);
                break;
            }
            case 12: {
                this.this$0.onDispose(e);
                break;
            }
            case 15: {
                ++this.nestedFocusEvents;
                final boolean hasFocus = this.this$0.isFocusControl();
                this.this$0.onFocusIn(e);
                --this.nestedFocusEvents;
                if (this.nestedFocusEvents == 0 && hasFocus == this.this$0.isFocusControl()) {
                    this.this$0.frame.onFocusIn(e);
                    break;
                }
                break;
            }
            case 16: {
                ++this.nestedFocusEvents;
                this.this$0.onFocusOut(e);
                --this.nestedFocusEvents;
                if (this.nestedFocusEvents == 0) {
                    this.this$0.frame.onFocusOut(e);
                    break;
                }
                break;
            }
            case 9: {
                this.this$0.onPaint(e);
                break;
            }
            case 31: {
                this.this$0.onTraverse(e);
                break;
            }
            case 1: {
                break;
            }
            case 26: {
                this.this$0.isActivated = true;
                break;
            }
            case 27: {
                this.this$0.isActivated = false;
                break;
            }
            default: {
                OLE.error(20);
                break;
            }
        }
    }
}
