//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.events.*;

final class llIIl extends SelectionAdapter
{
    final /* synthetic */ Browser val$browser;
    
    llIIl(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void widgetSelected(final SelectionEvent e) {
        this.val$browser.stop();
    }
}
