//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch.platform;

import java.net.*;
import org.spongepowered.asm.launch.*;
import java.io.*;
import net.minecraft.launchwrapper.*;
import java.lang.reflect.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.mixin.*;
import org.apache.logging.log4j.*;
import java.util.*;

public class MixinPlatformAgentFML extends MixinPlatformAgentAbstract
{
    private static final String LOAD_CORE_MOD_METHOD = "loadCoreMod";
    private static final String GET_REPARSEABLE_COREMODS_METHOD = "getReparseableCoremods";
    private static final String CORE_MOD_MANAGER_CLASS = "net.minecraftforge.fml.relauncher.CoreModManager";
    private static final String GET_IGNORED_MODS_METHOD = "getIgnoredMods";
    private static final String FML_REMAPPER_ADAPTER_CLASS = "org.spongepowered.asm.bridge.RemapperAdapterFML";
    private static final String MFATT_FORCELOADASMOD = "ForceLoadAsMod";
    private static final String MFATT_FMLCOREPLUGIN = "FMLCorePlugin";
    private static final String MFATT_COREMODCONTAINSMOD = "FMLCorePluginContainsFMLMod";
    private final ITweaker coreModWrapper;
    private final String fileName;
    private Class<?> clCoreModManager;
    
    public MixinPlatformAgentFML(final MixinPlatformManager manager, final URI uri) {
        super(manager, uri);
        this.fileName = this.container.getName();
        this.coreModWrapper = this.initFMLCoreMod();
    }
    
    private ITweaker initFMLCoreMod() {
        try {
            this.clCoreModManager = getCoreModManagerClass();
            if ("true".equalsIgnoreCase(this.attributes.get("ForceLoadAsMod"))) {
                MixinPlatformAgentAbstract.logger.debug("ForceLoadAsMod was specified for {}, attempting force-load", new Object[] { this.fileName });
                this.loadAsMod();
            }
            return this.injectCorePlugin();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private void loadAsMod() {
        try {
            getIgnoredMods(this.clCoreModManager).remove(this.fileName);
        }
        catch (Exception ex) {
            MixinPlatformAgentAbstract.logger.catching((Throwable)ex);
        }
        if (this.attributes.get("FMLCorePluginContainsFMLMod") != null) {
            this.addReparseableJar();
        }
    }
    
    private void addReparseableJar() {
        try {
            final Method mdGetReparsedCoremods = this.clCoreModManager.getDeclaredMethod(Blackboard.getString("mixin.launch.fml.reparseablecoremodsmethod", "getReparseableCoremods"), (Class<?>[])new Class[0]);
            final List<String> reparsedCoremods = (List<String>)mdGetReparsedCoremods.invoke(null, new Object[0]);
            if (!reparsedCoremods.contains(this.fileName)) {
                MixinPlatformAgentAbstract.logger.debug("Adding {} to reparseable coremod collection", new Object[] { this.fileName });
                reparsedCoremods.add(this.fileName);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    private ITweaker injectCorePlugin() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        final String coreModName = this.attributes.get("FMLCorePlugin");
        if (coreModName == null) {
            return null;
        }
        MixinPlatformAgentAbstract.logger.debug("{} has core plugin {}. Injecting it into FML for co-initialisation:", new Object[] { this.fileName, coreModName });
        final Method mdLoadCoreMod = this.clCoreModManager.getDeclaredMethod(Blackboard.getString("mixin.launch.fml.loadcoremodmethod", "loadCoreMod"), LaunchClassLoader.class, String.class, File.class);
        mdLoadCoreMod.setAccessible(true);
        final ITweaker wrapper = (ITweaker)mdLoadCoreMod.invoke(null, Launch.classLoader, coreModName, this.container);
        if (wrapper == null) {
            MixinPlatformAgentAbstract.logger.debug("Core plugin {} could not be loaded.", new Object[] { coreModName });
            return null;
        }
        return wrapper;
    }
    
    public String getPhaseProvider() {
        return MixinPlatformAgentFML.class.getName() + "$PhaseProvider";
    }
    
    public void prepare() {
    }
    
    public void initPrimaryContainer() {
        if (this.clCoreModManager != null) {
            this.injectRemapper();
        }
    }
    
    private void injectRemapper() {
        try {
            MixinPlatformAgentAbstract.logger.debug("Creating FML remapper adapter: {}", new Object[] { "org.spongepowered.asm.bridge.RemapperAdapterFML" });
            final Class<?> clFmlRemapperAdapter = Class.forName("org.spongepowered.asm.bridge.RemapperAdapterFML", true, (ClassLoader)Launch.classLoader);
            final Method mdCreate = clFmlRemapperAdapter.getDeclaredMethod("create", (Class<?>[])new Class[0]);
            final IRemapper remapper = (IRemapper)mdCreate.invoke(null, new Object[0]);
            MixinEnvironment.getDefaultEnvironment().getRemappers().add(remapper);
        }
        catch (Exception ex) {
            MixinPlatformAgentAbstract.logger.debug("Failed instancing FML remapper adapter, things will probably go horribly for notch-obf'd mods!");
        }
    }
    
    public void injectIntoClassLoader(final LaunchClassLoader classLoader) {
        if (this.coreModWrapper != null) {
            if (!this.isFMLInjected()) {
                MixinPlatformAgentAbstract.logger.debug("FML agent is co-initialising coremod instance {} for {}", new Object[] { this.coreModWrapper, this.uri });
                this.coreModWrapper.injectIntoClassLoader(classLoader);
            }
            else {
                MixinPlatformAgentAbstract.logger.debug("FML agent is skipping co-init for {} because FML already started", new Object[] { this.coreModWrapper });
            }
        }
    }
    
    public String getLaunchTarget() {
        return null;
    }
    
    protected final boolean isFMLInjected() {
        for (final String tweaker : (List)Blackboard.get("TweakClasses")) {
            if (tweaker.endsWith("FMLDeobfTweaker")) {
                return true;
            }
        }
        return false;
    }
    
    private static Class<?> getCoreModManagerClass() throws ClassNotFoundException {
        try {
            return Class.forName(Blackboard.getString("mixin.launch.fml.coremodmanagerclass", "net.minecraftforge.fml.relauncher.CoreModManager"));
        }
        catch (ClassNotFoundException ex) {
            return Class.forName("cpw.mods.fml.relauncher.CoreModManager");
        }
    }
    
    private static List<String> getIgnoredMods(final Class<?> clCoreModManager) throws IllegalAccessException, InvocationTargetException {
        Method mdGetIgnoredMods = null;
        try {
            mdGetIgnoredMods = clCoreModManager.getDeclaredMethod(Blackboard.getString("mixin.launch.fml.ignoredmodsmethod", "getIgnoredMods"), (Class<?>[])new Class[0]);
        }
        catch (NoSuchMethodException ex3) {
            try {
                mdGetIgnoredMods = clCoreModManager.getDeclaredMethod("getLoadedCoremods", (Class<?>[])new Class[0]);
            }
            catch (NoSuchMethodException ex2) {
                MixinPlatformAgentAbstract.logger.catching(Level.DEBUG, (Throwable)ex2);
                return Collections.emptyList();
            }
        }
        return (List<String>)mdGetIgnoredMods.invoke(null, new Object[0]);
    }
}
