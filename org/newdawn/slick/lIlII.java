//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.util.*;

class lIlII extends LinkedHashMap
{
    final /* synthetic */ AngelCodeFont this$0;
    
    lIlII(final AngelCodeFont this$0, final int x0, final float x1, final boolean x2) {
        this.this$0 = this$0;
        super(x0, x1, x2);
    }
    
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
        AngelCodeFont.access$002(this.this$0, (AngelCodeFont.DisplayList)eldest.getValue());
        AngelCodeFont.access$102(this.this$0, AngelCodeFont.access$000(this.this$0).id);
        return false;
    }
}
