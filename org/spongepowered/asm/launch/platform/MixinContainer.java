//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import java.net.*;
import org.apache.logging.log4j.*;
import java.lang.reflect.*;
import java.util.*;
import net.minecraft.launchwrapper.*;
import org.spongepowered.asm.launch.*;

public class MixinContainer
{
    public static final List<String> agentClasses;
    private final Logger logger;
    private final URI uri;
    private final List<IMixinPlatformAgent> agents;
    
    public MixinContainer(final MixinPlatformManager manager, final URI uri) {
        this.logger = LogManager.getLogger("mixin");
        this.agents = new ArrayList<IMixinPlatformAgent>();
        this.uri = uri;
        for (final String agentClass : MixinContainer.agentClasses) {
            try {
                final Class<IMixinPlatformAgent> clazz = (Class<IMixinPlatformAgent>)Class.forName(agentClass);
                final Constructor<IMixinPlatformAgent> ctor = clazz.getDeclaredConstructor(MixinPlatformManager.class, URI.class);
                this.logger.debug("Instancing new {} for {}", new Object[] { clazz.getSimpleName(), this.uri });
                final IMixinPlatformAgent agent = ctor.newInstance(manager, uri);
                this.agents.add(agent);
            }
            catch (Exception ex) {
                this.logger.catching((Throwable)ex);
            }
        }
    }
    
    public URI getURI() {
        return this.uri;
    }
    
    public Collection<String> getPhaseProviders() {
        final List<String> phaseProviders = new ArrayList<String>();
        for (final IMixinPlatformAgent agent : this.agents) {
            final String phaseProvider = agent.getPhaseProvider();
            if (phaseProvider != null) {
                phaseProviders.add(phaseProvider);
            }
        }
        return phaseProviders;
    }
    
    public void prepare() {
        for (final IMixinPlatformAgent agent : this.agents) {
            this.logger.debug("Processing prepare() for {}", new Object[] { agent });
            agent.prepare();
        }
    }
    
    public void initPrimaryContainer() {
        for (final IMixinPlatformAgent agent : this.agents) {
            this.logger.debug("Processing launch tasks for {}", new Object[] { agent });
            agent.initPrimaryContainer();
        }
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        for (final IMixinPlatformAgent agent : this.agents) {
            this.logger.debug("Processing injectIntoClassLoader() for {}", new Object[] { agent });
            agent.injectIntoClassLoader(classLoader);
        }
    }
    
    public String getLaunchTarget() {
        for (final IMixinPlatformAgent agent : this.agents) {
            final String launchTarget = agent.getLaunchTarget();
            if (launchTarget != null) {
                return launchTarget;
            }
        }
        return null;
    }
    
    static {
        Blackboard.put("mixin.agents", (Object)(agentClasses = new ArrayList<String>()));
        MixinContainer.agentClasses.add("org.spongepowered.asm.launch.platform.MixinPlatformAgentFML");
        MixinContainer.agentClasses.add("org.spongepowered.asm.launch.platform.MixinPlatformAgentDefault");
    }
}
