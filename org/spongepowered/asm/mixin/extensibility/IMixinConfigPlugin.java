//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.extensibility;

import java.util.*;
import org.spongepowered.asm.lib.tree.*;

public interface IMixinConfigPlugin
{
    void onLoad(final String p0);
    
    String getRefMapperConfig();
    
    boolean shouldApplyMixin(final String p0, final String p1);
    
    void acceptTargets(final Set<String> p0, final Set<String> p1);
    
    List<String> getMixins();
    
    void preApply(final String p0, final ClassNode p1, final String p2, final IMixinInfo p3);
    
    void postApply(final String p0, final ClassNode p1, final String p2, final IMixinInfo p3);
}
