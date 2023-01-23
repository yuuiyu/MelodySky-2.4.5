//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch;

import org.spongepowered.asm.launch.platform.*;
import java.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.launchwrapper.*;
import org.apache.logging.log4j.*;

public abstract class MixinBootstrap
{
    public static final String VERSION = "0.6.5";
    private static final String LAUNCH_PACKAGE = "org.spongepowered.asm.launch.";
    private static final String MIXIN_PACKAGE = "org.spongepowered.asm.mixin.";
    private static final String MIXIN_UTIL_PACKAGE = "org.spongepowered.asm.util.";
    private static final String ASM_PACKAGE = "org.spongepowered.asm.lib.";
    private static final String TRANSFORMER_PROXY_CLASS = "org.spongepowered.asm.mixin.transformer.MixinTransformer$Proxy";
    private static final Logger logger;
    private static boolean initialised;
    private static boolean initState;
    private static MixinPlatformManager platform;
    
    private MixinBootstrap() {
    }
    
    public static void addProxy() {
        Launch.classLoader.registerTransformer("org.spongepowered.asm.mixin.transformer.MixinTransformer$Proxy");
    }
    
    public static MixinPlatformManager getPlatform() {
        if (MixinBootstrap.platform == null) {
            MixinBootstrap.platform = new MixinPlatformManager();
        }
        return MixinBootstrap.platform;
    }
    
    public static void init() {
        if (!start()) {
            return;
        }
        doInit(null);
    }
    
    static boolean start() {
        if (!isSubsystemRegistered()) {
            registerSubsystem("0.6.5");
            if (!MixinBootstrap.initialised) {
                MixinBootstrap.initialised = true;
                final String command = System.getProperty("sun.java.command");
                if (command != null && command.contains("GradleStart")) {
                    System.setProperty("mixin.env.disableRefMap", "true");
                }
                if (findInStackTrace(Launch.class.getName(), "launch") > 132) {
                    MixinBootstrap.logger.error("Initialising mixin subsystem after game pre-init phase! Some mixins may be skipped.");
                    MixinEnvironment.init(MixinEnvironment.Phase.DEFAULT);
                    getPlatform().prepare(null);
                    MixinBootstrap.initState = false;
                }
                else {
                    MixinEnvironment.init(MixinEnvironment.Phase.PREINIT);
                }
                addProxy();
            }
            getPlatform();
            return true;
        }
        if (!checkSubsystemVersion()) {
            throw new MixinInitialisationError("Mixin subsystem version " + getActiveSubsystemVersion() + " was already initialised. Cannot bootstrap version " + "0.6.5");
        }
        return false;
    }
    
    static void doInit(final List<String> args) {
        if (MixinBootstrap.initialised) {
            getPlatform().getPhaseProviderClasses();
            if (MixinBootstrap.initState) {
                getPlatform().prepare(args);
                if (findInStackTrace(Launch.class.getName(), "launch") < 4) {
                    MixinBootstrap.logger.warn("MixinBootstrap.doInit() called during a tweak constructor. Expect CoModificationException in 5.. 4..");
                }
                final List<String> tweakClasses = (List<String>)Blackboard.get("TweakClasses");
                if (tweakClasses != null) {
                    tweakClasses.add(MixinEnvironment.class.getName() + "$EnvironmentStateTweaker");
                }
            }
            return;
        }
        if (isSubsystemRegistered()) {
            MixinBootstrap.logger.warn("Multiple Mixin containers present, init suppressed for 0.6.5");
            return;
        }
        throw new IllegalStateException("MixinBootstrap.doInit() called before MixinBootstrap.start()");
    }
    
    static void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        getPlatform().injectIntoClassLoader(classLoader);
    }
    
    private static boolean isSubsystemRegistered() {
        return Blackboard.get("mixin.initialised") != null;
    }
    
    private static boolean checkSubsystemVersion() {
        return "0.6.5".equals(getActiveSubsystemVersion());
    }
    
    private static Object getActiveSubsystemVersion() {
        final Object version = Blackboard.get("mixin.initialised");
        return (version != null) ? version : "";
    }
    
    private static void registerSubsystem(final String version) {
        Blackboard.put("mixin.initialised", (Object)version);
    }
    
    private static int findInStackTrace(final String className, final String methodName) {
        final Thread currentThread = Thread.currentThread();
        if (!"main".equals(currentThread.getName())) {
            return 0;
        }
        final StackTraceElement[] arr$;
        final StackTraceElement[] stackTrace = arr$ = currentThread.getStackTrace();
        for (final StackTraceElement s : arr$) {
            if (className.equals(s.getClassName()) && methodName.equals(s.getMethodName())) {
                return s.getLineNumber();
            }
        }
        return 0;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
        MixinBootstrap.initialised = false;
        MixinBootstrap.initState = true;
        Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.lib.");
        Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.mixin.");
        Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.util.");
        Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.launch.");
    }
}
