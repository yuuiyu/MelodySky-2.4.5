//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import chrriis.dj.nativeswing.common.*;
import java.awt.*;

class lIllll implements Filter<Component>
{
    final /* synthetic */ BackBufferManager this$0;
    
    lIllll(final BackBufferManager this$0) {
        this.this$0 = this$0;
    }
    
    public Filter.Acceptance accept(final Component c) {
        if (!c.isOpaque()) {
            return Filter.Acceptance.YES;
        }
        return Filter.Acceptance.NO;
    }
}
