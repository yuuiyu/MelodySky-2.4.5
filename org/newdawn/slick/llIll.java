//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.util.*;

class llIll extends LinkedHashMap
{
    final /* synthetic */ UnicodeFont this$0;
    
    llIll(final UnicodeFont this$0, final int x0, final float x1, final boolean x2) {
        this.this$0 = this$0;
        super(x0, x1, x2);
    }
    
    @Override
    protected boolean removeEldestEntry(final Map.Entry eldest) {
        final UnicodeFont.DisplayList displayList = eldest.getValue();
        if (displayList != null) {
            this.this$0.eldestDisplayListID = displayList.id;
        }
        return this.size() > 200;
    }
}
