//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.internal;

import java.lang.reflect.*;

public class NativeCoreObjectFactory
{
    private static NativeCoreObjectFactory factory;
    private ClassLoader classLoader;
    
    public static void setDefaultFactory(final NativeCoreObjectFactory factory) {
        synchronized (NativeCoreObjectFactory.class) {
            NativeCoreObjectFactory.factory = factory;
        }
    }
    
    public static <T> T create(final Class<T> clazz, final String className, final Class<?>[] types, final Object[] args) {
        ClassLoader classLoader;
        synchronized (NativeCoreObjectFactory.class) {
            classLoader = ((NativeCoreObjectFactory.factory != null) ? NativeCoreObjectFactory.factory.classLoader : null);
        }
        if (classLoader == null) {
            classLoader = NativeCoreObjectFactory.class.getClassLoader();
        }
        try {
            final Class<T> wbClass = (Class<T>)classLoader.loadClass(className);
            final Constructor<T> wbConstructor = wbClass.getDeclaredConstructor(types);
            wbConstructor.setAccessible(true);
            return wbConstructor.newInstance(args);
        }
        catch (RuntimeException e) {
            e.printStackTrace();
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        return null;
    }
    
    public NativeCoreObjectFactory(final ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
