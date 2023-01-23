//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import java.util.*;

class l extends ArrayList<Object>
{
    final /* synthetic */ MethodNode this$0;
    
    l(final MethodNode this$0, final int x0) {
        this.this$0 = this$0;
        super(x0);
    }
    
    @Override
    public boolean add(final Object o) {
        this.this$0.annotationDefault = o;
        return super.add(o);
    }
}
