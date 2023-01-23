//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import org.apache.logging.log4j.core.helpers.*;

enum lIlIl
{
    lIlIl(final String x2, final int x3, final MixinEnvironment.Option x0, final String x1) {
    }
    
    @Override
    boolean getBooleanValue() {
        return Booleans.parseBoolean(System.getProperty(this.property), super.getBooleanValue());
    }
}
