//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.graphics.*;
import org.eclipse.swt.events.*;

final class lIIlIll implements PaintListener
{
    final /* synthetic */ Image val$screenshot;
    
    lIIlIll(final Image val$screenshot) {
        this.val$screenshot = val$screenshot;
    }
    
    public void paintControl(final PaintEvent e) {
        e.gc.drawImage(this.val$screenshot, 0, 0);
    }
}
