//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.refmap;

import org.spongepowered.asm.mixin.extensibility.*;

public interface IReferenceMapperContext
{
    IMixinInfo getMixin();
    
    String getClassRef();
    
    ReferenceMapper getReferenceMapper();
}
