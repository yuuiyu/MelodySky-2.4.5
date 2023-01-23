//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import java.net.*;
import net.minecraft.launchwrapper.*;

public class MixinPlatformAgentDefault extends MixinPlatformAgentAbstract
{
    public MixinPlatformAgentDefault(final MixinPlatformManager manager, final URI uri) {
        super(manager, uri);
    }
    
    public void prepare() {
        final String compatibilityLevel = this.attributes.get("MixinCompatibilityLevel");
        if (compatibilityLevel != null) {
            this.manager.setCompatibilityLevel(compatibilityLevel);
        }
        final String mixinConfigs = this.attributes.get("MixinConfigs");
        if (mixinConfigs != null) {
            for (final String config : mixinConfigs.split(",")) {
                this.manager.addConfig(config.trim());
            }
        }
        final String tokenProviders = this.attributes.get("MixinTokenProviders");
        if (tokenProviders != null) {
            for (final String provider : tokenProviders.split(",")) {
                this.manager.addTokenProvider(provider.trim());
            }
        }
    }
    
    public void initPrimaryContainer() {
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
    }
    
    public String getLaunchTarget() {
        return this.attributes.get("Main-Class");
    }
}
