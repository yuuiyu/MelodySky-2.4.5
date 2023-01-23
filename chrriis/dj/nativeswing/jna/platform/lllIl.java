//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.jna.platform;

import com.sun.jna.platform.*;
import com.sun.jna.platform.win32.*;

class lllIl implements RasterRangesUtils.RangesOutput
{
    final /* synthetic */ WinDef.HRGN val$tempRgn;
    final /* synthetic */ WinDef.HRGN val$region;
    final /* synthetic */ WindowUtils.W32WindowUtils this$0;
    
    lllIl(final WindowUtils.W32WindowUtils this$0, final WinDef.HRGN val$tempRgn, final WinDef.HRGN val$region) {
        this.this$0 = this$0;
        this.val$tempRgn = val$tempRgn;
        this.val$region = val$region;
    }
    
    public boolean outputRange(final int x, final int y, final int w, final int h) {
        final GDI32 gdi = GDI32.INSTANCE;
        gdi.SetRectRgn(this.val$tempRgn, x, y, x + w, y + h);
        return gdi.CombineRgn(this.val$region, this.val$region, this.val$tempRgn, 2) != 0;
    }
}
