//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;
import java.awt.*;

class lIlIll implements AWTEventListener
{
    final /* synthetic */ NativeComponentProxyPanel this$0;
    
    lIlIll(final NativeComponentProxyPanel this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void eventDispatched(final AWTEvent e) {
        boolean isAdjustingShape = false;
        switch (e.getID()) {
            case 100:
            case 101:
            case 300:
            case 301: {
                isAdjustingShape = true;
                break;
            }
            case 102:
            case 103: {
                if (e.getSource() instanceof Window) {
                    isAdjustingShape = true;
                    break;
                }
                break;
            }
        }
        if (isAdjustingShape && this.this$0.nativeComponentWrapper.getNativeComponentProxy() == this.this$0) {
            this.this$0.adjustEmbeddedPanelShape();
        }
    }
}
