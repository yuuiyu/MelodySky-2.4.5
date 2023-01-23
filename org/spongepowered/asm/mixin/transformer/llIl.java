//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import com.google.common.base.*;
import org.spongepowered.asm.lib.*;

class llIl implements Function<Type, String>
{
    final /* synthetic */ MixinInfo this$0;
    
    llIl(final MixinInfo this$0) {
        this.this$0 = this$0;
    }
    
    public String apply(final Type input) {
        return input.getClassName();
    }
}
