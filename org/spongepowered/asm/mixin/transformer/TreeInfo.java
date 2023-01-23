//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.*;
import java.net.*;
import org.apache.commons.io.*;
import java.io.*;
import org.spongepowered.asm.util.launchwrapper.*;
import net.minecraft.launchwrapper.*;
import java.util.*;
import org.apache.logging.log4j.*;

abstract class TreeInfo
{
    private static final Logger logger;
    private static MixinTransformer.ReEntranceState lock;
    
    static void setLock(final MixinTransformer.ReEntranceState lock) {
        TreeInfo.lock = lock;
    }
    
    static ClassNode getClassNode(final String className) throws ClassNotFoundException, IOException {
        return getClassNode(loadClass(className, true), 0);
    }
    
    protected static ClassNode getClassNode(final byte[] classBytes, final int flags) {
        final ClassNode classNode = new ClassNode();
        final ClassReader classReader = new ClassReader(classBytes);
        classReader.accept((ClassVisitor)classNode, flags);
        return classNode;
    }
    
    protected static byte[] loadClass(final String className, final boolean runTransformers) throws ClassNotFoundException, IOException {
        final String transformedName = className.replace('/', '.');
        final String name = MixinEnvironment.getCurrentEnvironment().unmap(transformedName);
        byte[] classBytes = getClassBytes(name, transformedName);
        if (runTransformers) {
            classBytes = applyTransformers(name, transformedName, classBytes);
        }
        if (classBytes == null) {
            throw new ClassNotFoundException(String.format("The specified class '%s' was not found", transformedName));
        }
        return classBytes;
    }
    
    private static byte[] getClassBytes(final String name, final String transformedName) throws IOException {
        final byte[] classBytes = Launch.classLoader.getClassBytes(name);
        if (classBytes != null) {
            return classBytes;
        }
        final URLClassLoader appClassLoader = (URLClassLoader)Launch.class.getClassLoader();
        InputStream classStream = null;
        try {
            final String resourcePath = transformedName.replace('.', '/').concat(".class");
            classStream = appClassLoader.getResourceAsStream(resourcePath);
            return IOUtils.toByteArray(classStream);
        }
        catch (Exception ex) {
            return null;
        }
        finally {
            IOUtils.closeQuietly(classStream);
        }
    }
    
    private static byte[] applyTransformers(final String name, final String transformedName, byte[] basicClass) {
        if (LaunchClassLoaderUtil.forClassLoader(Launch.classLoader).isClassExcluded(name, transformedName)) {
            return basicClass;
        }
        final MixinEnvironment environment = MixinEnvironment.getCurrentEnvironment();
        for (final IClassTransformer transformer : environment.getTransformers()) {
            if (TreeInfo.lock != null) {
                TreeInfo.lock.clear();
            }
            basicClass = transformer.transform(name, transformedName, basicClass);
            if (TreeInfo.lock != null && TreeInfo.lock.isSet()) {
                environment.addTransformerExclusion(transformer.getClass().getName());
                TreeInfo.lock.clear();
                TreeInfo.logger.info("A re-entrant transformer '{}' was detected and will no longer process meta class data", new Object[] { transformer.getClass().getName() });
            }
        }
        return basicClass;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}
