//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.common;

import java.io.*;
import java.net.*;
import java.lang.reflect.*;

public class NetworkURLClassLoader extends ClassLoader
{
    private final URL codebaseURL;
    
    public NetworkURLClassLoader(final String codebase) throws MalformedURLException {
        this.codebaseURL = new URL(codebase);
    }
    
    @Override
    protected URL findResource(final String name) {
        try {
            return new URL(this.codebaseURL, name);
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    @Override
    protected Class<?> findClass(final String name) throws ClassNotFoundException {
        Exception exception = null;
        for (int i = 0; i < 2; ++i) {
            final String path = name.replace('.', '/') + ".class";
            final URL resourceURL = this.getResource(path);
            InputStream in = null;
            Class<?> clazz = null;
            exception = null;
            try {
                final URLConnection connection = resourceURL.openConnection();
                connection.setReadTimeout(4000);
                in = connection.getInputStream();
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                byte[] bytes = new byte[1024];
                int n;
                while ((n = in.read(bytes)) != -1) {
                    baos.write(bytes, 0, n);
                }
                bytes = baos.toByteArray();
                clazz = this.defineClass(name, bytes, 0, bytes.length);
            }
            catch (Exception e) {
                exception = e;
            }
            try {
                if (in != null) {
                    in.close();
                }
            }
            catch (Exception ex) {}
            if (clazz != null) {
                return clazz;
            }
        }
        throw new ClassNotFoundException(name, exception);
    }
    
    public static void main(final String[] args) throws Exception {
        final String codeBase = args[0];
        final String mainClass = args[1];
        final String[] newArgs = new String[args.length - 2];
        System.arraycopy(args, 2, newArgs, 0, newArgs.length);
        Method method;
        try {
            final Class<?> clazz = new NetworkURLClassLoader(codeBase).loadClass(mainClass);
            method = clazz.getDeclaredMethod("main", String[].class);
            method.setAccessible(true);
        }
        catch (Exception e) {
            e.printStackTrace();
            System.exit(-1);
            return;
        }
        method.invoke(null, newArgs);
    }
}
