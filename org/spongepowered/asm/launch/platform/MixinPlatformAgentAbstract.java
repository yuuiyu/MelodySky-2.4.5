//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import java.net.*;
import java.io.*;
import org.apache.logging.log4j.*;

public abstract class MixinPlatformAgentAbstract implements IMixinPlatformAgent
{
    protected static final Logger logger;
    protected final MixinPlatformManager manager;
    protected final URI uri;
    protected final File container;
    protected final MainAttributes attributes;
    
    public MixinPlatformAgentAbstract(final MixinPlatformManager manager, final URI uri) {
        this.manager = manager;
        this.uri = uri;
        this.container = ((this.uri != null) ? new File(this.uri) : null);
        this.attributes = MainAttributes.of(uri);
    }
    
    @Override
    public String toString() {
        return String.format("PlatformAgent[%s:%s]", this.getClass().getSimpleName(), this.uri);
    }
    
    public String getPhaseProvider() {
        return null;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
