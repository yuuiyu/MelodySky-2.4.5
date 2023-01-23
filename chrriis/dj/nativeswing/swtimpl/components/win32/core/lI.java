//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32.core;

import org.eclipse.swt.ole.win32.*;
import org.eclipse.swt.events.*;

final class lI implements DisposeListener
{
    final /* synthetic */ OleAutomation val$application;
    
    lI(final OleAutomation val$application) {
        this.val$application = val$application;
    }
    
    public void widgetDisposed(final DisposeEvent e) {
        this.val$application.dispose();
    }
}
