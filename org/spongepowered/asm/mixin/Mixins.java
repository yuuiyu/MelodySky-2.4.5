//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.launch.*;
import net.minecraft.launchwrapper.*;
import java.util.*;
import org.apache.logging.log4j.*;

public final class Mixins
{
    private static final Logger logger;
    private static final String CONFIGS_KEY = "mixin.configs.queue";
    private static final Set<String> errorHandlers;
    
    private Mixins() {
    }
    
    public static void addConfigurations(final String... configFiles) {
        final MixinEnvironment fallback = MixinEnvironment.getDefaultEnvironment();
        for (final String configFile : configFiles) {
            createConfiguration(configFile, fallback);
        }
    }
    
    public static void addConfiguration(final String configFile) {
        createConfiguration(configFile, MixinEnvironment.getDefaultEnvironment());
    }
    
    @Deprecated
    static void addConfiguration(final String configFile, final MixinEnvironment fallback) {
        createConfiguration(configFile, fallback);
    }
    
    private static void createConfiguration(final String configFile, final MixinEnvironment fallback) {
        Config config = null;
        try {
            config = Config.create(configFile, fallback);
        }
        catch (Exception ex) {
            Mixins.logger.error("Error encountered reading mixin config " + configFile + ": " + ex.getClass().getName() + " " + ex.getMessage(), (Throwable)ex);
        }
        registerConfiguration(config);
    }
    
    private static void registerConfiguration(final Config config) {
        if (config == null) {
            return;
        }
        final MixinEnvironment env = config.getEnvironment();
        if (env != null) {
            env.registerConfig(config.getName());
        }
        getConfigs().add(config);
    }
    
    public static Set<Config> getConfigs() {
        Set<Config> mixinConfigs = (Set<Config>)Blackboard.get("mixin.configs.queue");
        if (mixinConfigs == null) {
            mixinConfigs = new LinkedHashSet<Config>();
            Launch.blackboard.put("mixin.configs.queue", mixinConfigs);
        }
        return mixinConfigs;
    }
    
    public static void registerErrorHandlerClass(final String handlerName) {
        if (handlerName != null) {
            Mixins.errorHandlers.add(handlerName);
        }
    }
    
    public static Set<String> getErrorHandlerClasses() {
        return Collections.unmodifiableSet((Set<? extends String>)Mixins.errorHandlers);
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        errorHandlers = new LinkedHashSet<String>();
    }
}
