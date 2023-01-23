//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.awt.event.*;

class lIIIIl extends FocusAdapter
{
    final /* synthetic */ NativeComponentProxy this$0;
    
    lIIIIl(final NativeComponentProxy this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public void focusGained(final FocusEvent e) {
        this.this$0.nativeComponentWrapper.getNativeComponent().requestFocus();
    }
}
