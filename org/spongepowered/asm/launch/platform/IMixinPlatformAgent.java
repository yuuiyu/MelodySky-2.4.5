//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import net.minecraft.launchwrapper.*;

public interface IMixinPlatformAgent
{
    String getPhaseProvider();
    
    void prepare();
    
    void initPrimaryContainer();
    
    void injectIntoClassLoader(final LaunchClassLoader p0);
    
    String getLaunchTarget();
}
