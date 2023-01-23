//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.events.*;

final class llllI extends MenuAdapter
{
    final /* synthetic */ MenuItem val$backMenuItem;
    final /* synthetic */ Browser val$browser;
    final /* synthetic */ MenuItem val$forwardMenuItem;
    final /* synthetic */ MenuItem val$stopMenuItem;
    
    llllI(final MenuItem val$backMenuItem, final Browser val$browser, final MenuItem val$forwardMenuItem, final MenuItem val$stopMenuItem) {
        this.val$backMenuItem = val$backMenuItem;
        this.val$browser = val$browser;
        this.val$forwardMenuItem = val$forwardMenuItem;
        this.val$stopMenuItem = val$stopMenuItem;
    }
    
    public void menuShown(final MenuEvent e) {
        this.val$backMenuItem.setEnabled(this.val$browser.isBackEnabled());
        this.val$forwardMenuItem.setEnabled(this.val$browser.isForwardEnabled());
        this.val$stopMenuItem.setEnabled(Boolean.TRUE.equals(this.val$browser.getData("Browser.loading")));
    }
}
