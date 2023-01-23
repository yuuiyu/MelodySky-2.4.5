//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.*;

class lll implements Function<String, String>
{
    final /* synthetic */ MixinInfo this$0;
    
    lll(final MixinInfo this$0) {
        this.this$0 = this$0;
    }
    
    public String apply(final String input) {
        return this.this$0.getParent().remapClassName(this.this$0.getClassRef(), input);
    }
}
