//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.awt.*;
import java.awt.event.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lIllllI implements AWTEventListener
{
    final /* synthetic */ SWTNativeInterface this$0;
    
    lIllllI(final SWTNativeInterface this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void eventDispatched(final AWTEvent e) {
        final KeyEvent ke = (KeyEvent)e;
        if (ke.getID() == 401 && ke.getKeyCode() == 114 && ke.isControlDown() && ke.isAltDown() && ke.isShiftDown()) {
            NativeInterface.printStackTraces();
        }
    }
}
