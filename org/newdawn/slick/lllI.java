//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.security.*;
import org.newdawn.slick.util.*;

class lllI implements PrivilegedAction
{
    final /* synthetic */ Graphics this$0;
    
    lllI(final Graphics this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public Object run() {
        try {
            Graphics.DEFAULT_FONT = (Font)new AngelCodeFont("org/newdawn/slick/data/defaultfont.fnt", "org/newdawn/slick/data/defaultfont.png");
        }
        catch (SlickException e) {
            Log.error(e);
        }
        return null;
    }
}
