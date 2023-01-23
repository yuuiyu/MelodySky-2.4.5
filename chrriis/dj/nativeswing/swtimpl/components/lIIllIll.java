//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import java.util.concurrent.atomic.*;

class lIIllIll implements EventDispatchUtils.Condition
{
    final /* synthetic */ AtomicReference val$result;
    final /* synthetic */ JWebBrowser this$0;
    
    lIIllIll(final JWebBrowser this$0, final AtomicReference val$result) {
        this.this$0 = this$0;
        this.val$result = val$result;
    }
    
    @Override
    public boolean getValue() {
        return this.val$result.get() != null;
    }
}
