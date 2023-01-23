//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

enum lIIll
{
    lIIll(final String x0, final int x2) {
    }
    
    @Override
    protected boolean detect() {
        final String sideName = this.getSideName();
        return "CLIENT".equals(sideName);
    }
}
