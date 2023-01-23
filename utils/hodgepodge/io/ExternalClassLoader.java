//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.io;

import java.io.*;
import java.net.*;

public final class ExternalClassLoader
{
    private static final CustomClassLoader Instance;
    
    public static void loadJar(final File jarIn) throws IOException {
        ExternalClassLoader.Instance.addURLFile(jarIn.toURI().toURL());
    }
    
    public static void loadJar(final URL jarURL) {
        ExternalClassLoader.Instance.addURLFile(jarURL);
    }
    
    public static Class<?> getClassFromName(final String name) throws ClassNotFoundException {
        return Class.forName(name, true, ExternalClassLoader.Instance);
    }
    
    static {
        Instance = new CustomClassLoader();
    }
    
    private static final class CustomClassLoader extends URLClassLoader
    {
        public CustomClassLoader() {
            super(new URL[0], findParentClassLoader());
        }
        
        public void addURLFile(final URL file) {
            this.addURL(file);
        }
        
        private static ClassLoader findParentClassLoader() {
            ClassLoader parent = CustomClassLoader.class.getClassLoader();
            if (parent == null) {
                parent = CustomClassLoader.class.getClassLoader();
            }
            if (parent == null) {
                parent = ClassLoader.getSystemClassLoader();
            }
            return parent;
        }
    }
}
