//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.core;

import org.eclipse.swt.browser.*;
import org.eclipse.swt.events.*;

final class lllIl extends SelectionAdapter
{
    final /* synthetic */ Browser val$browser;
    
    lllIl(final Browser val$browser) {
        this.val$browser = val$browser;
    }
    
    public void widgetSelected(final SelectionEvent e) {
        this.val$browser.refresh();
    }
}
