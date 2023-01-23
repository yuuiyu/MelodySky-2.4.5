//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32.core;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.ole.win32.*;

final class ll implements OleListener
{
    final /* synthetic */ OleFrame val$frame;
    
    ll(final OleFrame val$frame) {
        this.val$frame = val$frame;
    }
    
    public void handleEvent(final OleEvent e) {
        final Variant[] args = e.arguments;
        final String url = args[1].getString();
        for (int i = 0; i < args.length; ++i) {
            args[i].dispose();
        }
        new NativeWShellExplorer.CMJ_sendDocumentCompleteEvent(null).asyncExec((Control)this.val$frame, url);
    }
}
