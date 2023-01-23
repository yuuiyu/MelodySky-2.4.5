//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import java.util.concurrent.atomic.*;

class lIlIIIIl implements EventDispatchUtils.Condition
{
    final /* synthetic */ AtomicBoolean val$result;
    final /* synthetic */ lIIIlIIl this$1;
    
    lIlIIIIl(final lIIIlIIl this$1, final AtomicBoolean val$result) {
        this.this$1 = this$1;
        this.val$result = val$result;
    }
    
    @Override
    public boolean getValue() {
        return this.val$result.get();
    }
}
