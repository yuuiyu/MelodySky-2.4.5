//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import com.sun.jna.platform.*;
import java.util.*;
import java.awt.*;

final class lIIIIl implements RasterRangesUtils.RangesOutput
{
    final /* synthetic */ List val$rlist;
    
    lIIIIl(final List val$rlist) {
        this.val$rlist = val$rlist;
    }
    
    public boolean outputRange(final int x, final int y, final int w, final int h) {
        this.val$rlist.add(new Rectangle(x, y, w, h));
        return true;
    }
}
