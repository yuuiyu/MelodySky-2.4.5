//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import org.eclipse.swt.widgets.*;
import java.util.concurrent.atomic.*;
import java.awt.*;
import org.eclipse.swt.graphics.*;

class lIlllIl implements Runnable
{
    final /* synthetic */ Control val$control;
    final /* synthetic */ AtomicReference val$result;
    final /* synthetic */ SWTNativeComponent.CMN_getPreferredSize this$0;
    
    lIlllIl(final SWTNativeComponent.CMN_getPreferredSize this$0, final Control val$control, final AtomicReference val$result) {
        this.this$0 = this$0;
        this.val$control = val$control;
        this.val$result = val$result;
    }
    
    @Override
    public void run() {
        if (this.val$control.isDisposed()) {
            return;
        }
        final Point cSize = this.val$control.computeSize(-1, -1);
        this.val$result.set(new Dimension(cSize.x, cSize.y));
    }
}
