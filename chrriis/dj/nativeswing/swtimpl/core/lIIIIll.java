//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.awt.event.*;
import chrriis.dj.nativeswing.swtimpl.*;

class lIIIIll extends FocusAdapter
{
    final /* synthetic */ SWTNativeComponent this$0;
    
    lIIIIll(final SWTNativeComponent this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void focusGained(final FocusEvent e) {
        if (this.this$0.isNativePeerValid() && !this.this$0.isNativePeerDisposed()) {
            this.this$0.runSync((CommandMessage)new SWTNativeComponent.CMN_transferFocus(null), new Object[0]);
        }
    }
}
