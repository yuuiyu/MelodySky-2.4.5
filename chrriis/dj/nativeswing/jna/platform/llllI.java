//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import com.sun.jna.platform.*;
import java.awt.geom.*;
import java.awt.*;

class llllI implements RasterRangesUtils.RangesOutput
{
    final /* synthetic */ Area val$area;
    final /* synthetic */ WindowUtils.NativeWindowUtils this$0;
    
    llllI(final WindowUtils.NativeWindowUtils this$0, final Area val$area) {
        this.this$0 = this$0;
        this.val$area = val$area;
    }
    
    public boolean outputRange(final int x, final int y, final int w, final int h) {
        this.val$area.add(new Area(new Rectangle(x, y, w, h)));
        return true;
    }
}
