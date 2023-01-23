//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.widgets.*;

final class lIlIII implements TitleListener
{
    final /* synthetic */ Browser val$browser;
    
    lIlIII(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void changed(final TitleEvent e) {
        new NativeWebBrowser.CMJ_updateTitle(null).asyncExec((Control)this.val$browser, e.title);
    }
}
