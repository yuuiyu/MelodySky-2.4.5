//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import org.objectweb.asm.commons.*;

final class lIIl extends Remapper
{
    final /* synthetic */ String val$className2;
    final /* synthetic */ String val$targetClassName2;
    
    lIIl(final String val$className2, final String val$targetClassName2) {
        this.val$className2 = val$className2;
        this.val$targetClassName2 = val$targetClassName2;
    }
    
    public String map(final String name) {
        return this.val$className2.equals(name) ? this.val$targetClassName2 : name;
    }
}
