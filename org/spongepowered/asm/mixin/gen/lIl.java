//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import java.util.*;

enum lIl
{
    lIl(final String s, final int n, final Set x0) {
    }
    
    AccessorGenerator getGenerator(final AccessorInfo info) {
        return (AccessorGenerator)new AccessorGeneratorFieldSetter(info);
    }
}
