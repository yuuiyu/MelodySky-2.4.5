//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.events.*;

class llIll extends MenuAdapter
{
    final /* synthetic */ Menu val$menu;
    final /* synthetic */ NativeWebBrowser.CMN_setDefaultPopupMenuRegistered this$0;
    
    llIll(final NativeWebBrowser.CMN_setDefaultPopupMenuRegistered this$0, final Menu val$menu) {
        this.this$0 = this$0;
        this.val$menu = val$menu;
    }
    
    public void menuShown(final MenuEvent e) {
        this.val$menu.setVisible(false);
    }
}
