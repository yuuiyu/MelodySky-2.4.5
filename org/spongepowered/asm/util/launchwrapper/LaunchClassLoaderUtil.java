//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.launchwrapper;

import net.minecraft.launchwrapper.*;
import java.lang.reflect.*;
import java.util.*;

public final class LaunchClassLoaderUtil
{
    private static final Map<LaunchClassLoader, LaunchClassLoaderUtil> utils;
    private final LaunchClassLoader classLoader;
    private Map<String, Class<?>> cachedClasses;
    private final Set<String> invalidClasses;
    private final Set<String> classLoaderExceptions;
    private final Set<String> transformerExceptions;
    
    private LaunchClassLoaderUtil(final LaunchClassLoader classLoader) {
        this.classLoader = classLoader;
        this.cachedClasses = getField(classLoader, "cachedClasses");
        this.invalidClasses = getField(classLoader, "invalidClasses");
        this.classLoaderExceptions = getField(classLoader, "classLoaderExceptions");
        this.transformerExceptions = getField(classLoader, "transformerExceptions");
    }
    
    public LaunchClassLoader getClassLoader() {
        return this.classLoader;
    }
    
    public Set<String> getLoadedClasses() {
        return this.getLoadedClasses(null);
    }
    
    public Set<String> getLoadedClasses(final String filter) {
        final Set<String> loadedClasses = new HashSet<String>();
        for (final String className : this.cachedClasses.keySet()) {
            if (filter == null || className.startsWith(filter)) {
                loadedClasses.add(className);
            }
        }
        return loadedClasses;
    }
    
    public boolean isClassLoaded(final String name) {
        return this.cachedClasses != null && this.cachedClasses.containsKey(name);
    }
    
    public boolean isClassExcluded(final String name, final String transformedName) {
        for (final String exception : this.getClassLoaderExceptions()) {
            if (transformedName.startsWith(exception) || name.startsWith(exception)) {
                return true;
            }
        }
        for (final String exception : this.getTransformerExceptions()) {
            if (transformedName.startsWith(exception) || name.startsWith(exception)) {
                return true;
            }
        }
        return false;
    }
    
    public void registerInvalidClass(final String name) {
        if (this.invalidClasses != null) {
            this.invalidClasses.add(name);
        }
    }
    
    public Set<String> getClassLoaderExceptions() {
        if (this.classLoaderExceptions != null) {
            return this.classLoaderExceptions;
        }
        return Collections.emptySet();
    }
    
    public Set<String> getTransformerExceptions() {
        if (this.transformerExceptions != null) {
            return this.transformerExceptions;
        }
        return Collections.emptySet();
    }
    
    private static <T> T getField(final LaunchClassLoader classLoader, final String fieldName) {
        try {
            final Field field = LaunchClassLoader.class.getDeclaredField(fieldName);
            field.setAccessible(true);
            return (T)field.get(classLoader);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static LaunchClassLoaderUtil forClassLoader(final LaunchClassLoader classLoader) {
        LaunchClassLoaderUtil util = LaunchClassLoaderUtil.utils.get(classLoader);
        if (util == null) {
            util = new LaunchClassLoaderUtil(classLoader);
            LaunchClassLoaderUtil.utils.put(classLoader, util);
        }
        return util;
    }
    
    static {
        utils = new HashMap<LaunchClassLoader, LaunchClassLoaderUtil>();
    }
}
