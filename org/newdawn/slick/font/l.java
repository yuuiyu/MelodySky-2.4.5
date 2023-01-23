//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.font;

import java.util.*;

class l implements Iterator
{
    final /* synthetic */ ListIterator val$iter;
    final /* synthetic */ GlyphPage this$0;
    
    l(final GlyphPage this$0, final ListIterator val$iter) {
        this.this$0 = this$0;
        this.val$iter = val$iter;
    }
    
    @Override
    public boolean hasNext() {
        return this.val$iter.hasPrevious();
    }
    
    @Override
    public Object next() {
        return this.val$iter.previous();
    }
    
    @Override
    public void remove() {
        this.val$iter.remove();
    }
}
