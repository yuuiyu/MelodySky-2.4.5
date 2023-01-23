//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.security.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.util.*;

class lIIll implements PrivilegedAction
{
    final /* synthetic */ AppGameContainer this$0;
    
    lIIll(final AppGameContainer this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Object run() {
        try {
            final PixelFormat format = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0, this.this$0.samples);
            AppGameContainer.access$000(this.this$0, format);
            this.this$0.supportsMultiSample = true;
        }
        catch (Exception e4) {
            Display.destroy();
            try {
                final PixelFormat format2 = new PixelFormat(8, 8, GameContainer.stencil ? 8 : 0);
                AppGameContainer.access$000(this.this$0, format2);
                this.this$0.alphaSupport = false;
            }
            catch (Exception e5) {
                Display.destroy();
                try {
                    AppGameContainer.access$000(this.this$0, new PixelFormat());
                }
                catch (Exception e3) {
                    Log.error(e3);
                }
            }
        }
        return null;
    }
}
