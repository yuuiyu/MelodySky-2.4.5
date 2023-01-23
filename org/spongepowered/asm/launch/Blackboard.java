//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch;

import net.minecraft.launchwrapper.*;

public final class Blackboard
{
    private Blackboard() {
    }
    
    public static <T> T get(final String key) {
        return Launch.blackboard.get(key);
    }
    
    public static void put(final String key, final Object value) {
        Launch.blackboard.put(key, value);
    }
    
    public static <T> T get(final String key, final T defaultValue) {
        final Object value = Launch.blackboard.get(key);
        return (T)((value != null) ? value : defaultValue);
    }
    
    public static String getString(final String key, final String defaultValue) {
        final Object value = Launch.blackboard.get(key);
        return (value != null) ? value.toString() : defaultValue;
    }
    
    public static final class Keys
    {
        public static final String TWEAKCLASSES = "TweakClasses";
        public static final String TWEAKS = "Tweaks";
        public static final String INIT = "mixin.initialised";
        public static final String AGENTS = "mixin.agents";
        public static final String CONFIGS = "mixin.configs";
        public static final String TRANSFORMER = "mixin.transformer";
        public static final String FML_LOAD_CORE_MOD = "mixin.launch.fml.loadcoremodmethod";
        public static final String FML_GET_REPARSEABLE_COREMODS = "mixin.launch.fml.reparseablecoremodsmethod";
        public static final String FML_CORE_MOD_MANAGER = "mixin.launch.fml.coremodmanagerclass";
        public static final String FML_GET_IGNORED_MODS = "mixin.launch.fml.ignoredmodsmethod";
        
        private Keys() {
        }
    }
}
