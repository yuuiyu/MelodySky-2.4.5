//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.core;

import java.util.concurrent.atomic.*;
import org.eclipse.swt.widgets.*;

class lIlIIlI implements Runnable
{
    final /* synthetic */ AtomicBoolean val$result;
    final /* synthetic */ Control val$control;
    final /* synthetic */ SWTNativeComponent.CMN_hasFocus this$0;
    
    lIlIIlI(final SWTNativeComponent.CMN_hasFocus this$0, final AtomicBoolean val$result, final Control val$control) {
        this.this$0 = this$0;
        this.val$result = val$result;
        this.val$control = val$control;
    }
    
    @Override
    public void run() {
        this.val$result.set(!this.val$control.isDisposed() && this.val$control.isFocusControl());
    }
}
