//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.mozilla.xpcom;

import java.io.*;
import java.nio.charset.*;
import java.net.*;
import org.mozilla.interfaces.*;
import java.util.*;

public class Mozilla implements IMozilla, IGRE, IXPCOM, IJavaXPCOMUtils, IXPCOMError
{
    private static Mozilla mozillaInstance;
    private static final String JAVAXPCOM_JAR = "javaxpcom.jar";
    private IMozilla mozilla;
    private IGRE gre;
    private IXPCOM xpcom;
    private IJavaXPCOMUtils jxutils;
    
    public static Mozilla getInstance() {
        return Mozilla.mozillaInstance;
    }
    
    private Mozilla() {
        this.mozilla = null;
        this.gre = null;
        this.xpcom = null;
        this.jxutils = null;
    }
    
    public static File getGREPathWithProperties(final GREVersionRange[] array, Properties properties) throws FileNotFoundException {
        final String property = System.getProperty("GRE_HOME");
        if (property != null) {
            File canonicalFile;
            try {
                canonicalFile = new File(property).getCanonicalFile();
            }
            catch (IOException ex) {
                throw new FileNotFoundException("cannot access GRE_HOME");
            }
            if (!canonicalFile.exists()) {
                throw new FileNotFoundException("GRE_HOME doesn't exist");
            }
            return canonicalFile;
        }
        else {
            if (System.getProperty("USE_LOCAL_GRE") != null) {
                return null;
            }
            if (properties == null) {
                properties = new Properties();
            }
            properties.setProperty("javaxpcom", "1");
            final String lowerCase = System.getProperty("os.name").toLowerCase();
            File file;
            if (lowerCase.startsWith("mac os x")) {
                file = getGREPathMacOSX(array);
            }
            else if (lowerCase.startsWith("windows")) {
                file = getGREPathWindows(array, properties);
            }
            else {
                file = getGREPathUnix(array, properties);
            }
            if (file == null) {
                throw new FileNotFoundException("GRE not found");
            }
            return file;
        }
    }
    
    private static File getGREPathMacOSX(final GREVersionRange[] array) {
        final File greBundleFramework = findGREBundleFramework();
        if (greBundleFramework != null) {
            return greBundleFramework;
        }
        final String property = System.getProperty("user.home");
        if (property != null) {
            final File greFramework = findGREFramework(property, array);
            if (greFramework != null) {
                return greFramework;
            }
        }
        return findGREFramework("", array);
    }
    
    private static File findGREBundleFramework() {
        try {
            final Class<?> forName = Class.forName("com.apple.cocoa.foundation.NSBundle", true, new URLClassLoader(new URL[] { new File("/System/Library/Java/").toURL() }));
            final Object invoke = forName.getMethod("mainBundle", (Class<?>[])null).invoke(null, (Object[])null);
            if (invoke != null) {
                final String s = (String)forName.getMethod("privateFrameworksPath", (Class<?>[])null).invoke(invoke, (Object[])null);
                if (s.length() != 0) {
                    final File file = new File(s, "XUL.framework");
                    if (file.isDirectory()) {
                        final File file2 = new File(file, "libxpcom.dylib");
                        if (file2.canRead()) {
                            final File parentFile = file2.getCanonicalFile().getParentFile();
                            if (new File(parentFile, "javaxpcom.jar").canRead()) {
                                return parentFile;
                            }
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
        return null;
    }
    
    private static File findGREFramework(final String s, final GREVersionRange[] array) {
        final File file = new File(s + "/Library/Frameworks/XUL.framework/Versions");
        if (!file.exists()) {
            return null;
        }
        final File[] listFiles = file.listFiles();
        for (int i = 0; i < listFiles.length; ++i) {
            if (checkVersion(listFiles[i].getName(), array)) {
                final File file2 = new File(listFiles[i], "libxpcom.dylib");
                final File file3 = new File(listFiles[i], "javaxpcom.jar");
                if (file2.canRead() && file3.canRead()) {
                    return listFiles[i];
                }
            }
        }
        return null;
    }
    
    private static File getGREPathWindows(final GREVersionRange[] array, final Properties properties) {
        File file = getGREPathFromRegKey("HKEY_CURRENT_USER\\Software\\mozilla.org\\GRE", array, properties);
        if (file == null) {
            file = getGREPathFromRegKey("HKEY_LOCAL_MACHINE\\Software\\mozilla.org\\GRE", array, properties);
        }
        return file;
    }
    
    private static File getGREPathFromRegKey(final String s, final GREVersionRange[] array, final Properties properties) {
        File tempFile;
        try {
            tempFile = File.createTempFile("jx_registry", null);
        }
        catch (IOException ex) {
            return null;
        }
        try {
            Runtime.getRuntime().exec("regedit /e \"" + tempFile.getPath() + "\" \"" + s + "\"").waitFor();
        }
        catch (Exception ex2) {}
        File grePathFromRegistryFile = null;
        if (tempFile.length() != 0L) {
            grePathFromRegistryFile = getGREPathFromRegistryFile(tempFile.getPath(), s, array, properties);
        }
        tempFile.delete();
        return grePathFromRegistryFile;
    }
    
    private static File getGREPathFromRegistryFile(final String s, final String s2, final GREVersionRange[] array, final Properties properties) {
        INIParser iniParser;
        try {
            iniParser = new INIParser(s, Charset.forName("UTF-16"));
        }
        catch (Exception ex) {
            return null;
        }
        final Iterator sections = iniParser.getSections();
        while (sections.hasNext()) {
            final String s3 = sections.next();
            final int length = s2.length();
            if (s3.length() <= length) {
                continue;
            }
            if (s3.substring(length + 1).indexOf(92) != -1) {
                continue;
            }
            final String string = iniParser.getString(s3, "\"Version\"");
            if (string == null) {
                continue;
            }
            if (!checkVersion(string.substring(1, string.length() - 1), array)) {
                continue;
            }
            if (properties != null) {
                int n = 1;
                final Enumeration<?> propertyNames = properties.propertyNames();
                while (n != 0 && propertyNames.hasMoreElements()) {
                    final String s4 = (String)propertyNames.nextElement();
                    final String string2 = iniParser.getString(s3, "\"" + s4 + "\"");
                    if (string2 == null) {
                        n = 0;
                    }
                    else {
                        if (string2.equals("\"" + properties.getProperty(s4) + "\"")) {
                            continue;
                        }
                        n = 0;
                    }
                }
                if (n == 0) {
                    continue;
                }
            }
            final String string3 = iniParser.getString(s3, "\"GreHome\"");
            if (string3 == null) {
                continue;
            }
            final File file = new File(string3.substring(1, string3.length() - 1));
            if (file.exists() && new File(file, "xpcom.dll").canRead()) {
                return file;
            }
        }
        return null;
    }
    
    private static File getGREPathUnix(final GREVersionRange[] array, final Properties properties) {
        final String property = System.getProperty("MOZ_GRE_CONF");
        if (property != null) {
            final File pathFromConfigFile = getPathFromConfigFile(property, array, properties);
            if (pathFromConfigFile != null) {
                return pathFromConfigFile;
            }
        }
        final String property2 = System.getProperty("user.home");
        if (property2 != null) {
            final File pathFromConfigFile2 = getPathFromConfigFile(property2 + File.separator + ".gre.config", array, properties);
            if (pathFromConfigFile2 != null) {
                return pathFromConfigFile2;
            }
            final File pathFromConfigDir = getPathFromConfigDir(property2 + File.separator + ".gre.d", array, properties);
            if (pathFromConfigDir != null) {
                return pathFromConfigDir;
            }
        }
        final File pathFromConfigFile3 = getPathFromConfigFile("/etc/gre.conf", array, properties);
        if (pathFromConfigFile3 != null) {
            return pathFromConfigFile3;
        }
        return getPathFromConfigDir("/etc/gre.d", array, properties);
    }
    
    private static File getPathFromConfigFile(final String s, final GREVersionRange[] array, final Properties properties) {
        INIParser iniParser;
        try {
            iniParser = new INIParser(s);
        }
        catch (Exception ex) {
            return null;
        }
        final Iterator sections = iniParser.getSections();
        while (sections.hasNext()) {
            final String s2 = sections.next();
            if (!checkVersion(s2, array)) {
                continue;
            }
            if (properties != null) {
                int n = 1;
                final Enumeration<?> propertyNames = properties.propertyNames();
                while (n != 0 && propertyNames.hasMoreElements()) {
                    final String s3 = (String)propertyNames.nextElement();
                    final String string = iniParser.getString(s2, s3);
                    if (string == null) {
                        n = 0;
                    }
                    else {
                        if (string.equals(properties.getProperty(s3))) {
                            continue;
                        }
                        n = 0;
                    }
                }
                if (n == 0) {
                    continue;
                }
            }
            final String string2 = iniParser.getString(s2, "GRE_PATH");
            if (string2 == null) {
                continue;
            }
            final File file = new File(string2);
            if (file.exists() && new File(file, "libxpcom.so").canRead()) {
                return file;
            }
        }
        return null;
    }
    
    private static File getPathFromConfigDir(final String s, final GREVersionRange[] array, final Properties properties) {
        final File file = new File(s);
        if (!file.isDirectory()) {
            return null;
        }
        File pathFromConfigFile = null;
        final File[] listFiles = file.listFiles();
        for (int n = 0; n < listFiles.length && pathFromConfigFile == null; ++n) {
            if (listFiles[n].getName().endsWith(".conf")) {
                pathFromConfigFile = getPathFromConfigFile(listFiles[n].getPath(), array, properties);
            }
        }
        return pathFromConfigFile;
    }
    
    private static boolean checkVersion(final String s, final GREVersionRange[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (array[i].check(s)) {
                return true;
            }
        }
        return false;
    }
    
    public void initialize(final File file) throws XPCOMInitializationException {
        final File file2 = new File(file, "javaxpcom.jar");
        if (!file2.exists()) {
            throw new XPCOMInitializationException("Could not find javaxpcom.jar in " + file);
        }
        final URL[] array = { null };
        try {
            array[0] = file2.toURL();
        }
        catch (MalformedURLException ex) {
            throw new XPCOMInitializationException(ex);
        }
        final URLClassLoader urlClassLoader = new URLClassLoader(array, this.getClass().getClassLoader());
        try {
            this.mozilla = (IMozilla)Class.forName("org.mozilla.xpcom.internal.MozillaImpl", true, urlClassLoader).newInstance();
            this.gre = (IGRE)Class.forName("org.mozilla.xpcom.internal.GREImpl", true, urlClassLoader).newInstance();
            this.xpcom = (IXPCOM)Class.forName("org.mozilla.xpcom.internal.XPCOMImpl", true, urlClassLoader).newInstance();
            this.jxutils = (IJavaXPCOMUtils)Class.forName("org.mozilla.xpcom.internal.JavaXPCOMMethods", true, urlClassLoader).newInstance();
        }
        catch (Exception ex2) {
            throw new XPCOMInitializationException("Could not load org.mozilla.xpcom.internal.* classes", ex2);
        }
        this.mozilla.initialize(file);
    }
    
    public void initEmbedding(final File file, final File file2, final IAppFileLocProvider appFileLocProvider) throws XPCOMException {
        try {
            this.gre.initEmbedding(file, file2, appFileLocProvider);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public void termEmbedding() {
        try {
            this.gre.termEmbedding();
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
        finally {
            this.mozilla = null;
            this.gre = null;
            this.xpcom = null;
        }
    }
    
    public ProfileLock lockProfileDirectory(final File file) throws XPCOMException {
        try {
            return this.gre.lockProfileDirectory(file);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public void notifyProfile() {
        try {
            this.gre.notifyProfile();
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public nsIServiceManager initXPCOM(final File file, final IAppFileLocProvider appFileLocProvider) throws XPCOMException {
        try {
            return this.xpcom.initXPCOM(file, appFileLocProvider);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public void shutdownXPCOM(final nsIServiceManager nsIServiceManager) throws XPCOMException {
        try {
            this.xpcom.shutdownXPCOM(nsIServiceManager);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
        finally {
            this.mozilla = null;
            this.gre = null;
            this.xpcom = null;
        }
    }
    
    public nsIServiceManager getServiceManager() throws XPCOMException {
        try {
            return this.xpcom.getServiceManager();
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public nsIComponentManager getComponentManager() throws XPCOMException {
        try {
            return this.xpcom.getComponentManager();
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public nsIComponentRegistrar getComponentRegistrar() throws XPCOMException {
        try {
            return this.xpcom.getComponentRegistrar();
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public nsILocalFile newLocalFile(final String s, final boolean b) throws XPCOMException {
        try {
            return this.xpcom.newLocalFile(s, b);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public static nsISupports queryInterface(final nsISupports nsISupports, final String s) {
        final ArrayList list = new ArrayList();
        list.add(nsISupports.getClass());
        while (!list.isEmpty()) {
            final Class<? extends nsISupports> clazz = list.remove(0);
            final String name = clazz.getName();
            if (!name.startsWith("java.")) {
                if (name.startsWith("javax.")) {
                    continue;
                }
                if (clazz.isInterface() && name.startsWith("org.mozilla")) {
                    final String interfaceIID = getInterfaceIID(clazz);
                    if (interfaceIID != null && s.equals(interfaceIID)) {
                        return nsISupports;
                    }
                }
                final Class[] interfaces = clazz.getInterfaces();
                for (int i = 0; i < interfaces.length; ++i) {
                    list.add(interfaces[i]);
                }
                final Class<? super nsISupports> superclass = clazz.getSuperclass();
                if (superclass == null) {
                    continue;
                }
                list.add(superclass);
            }
        }
        return null;
    }
    
    public static String getInterfaceIID(final Class clazz) {
        final StringBuffer sb = new StringBuffer();
        final String name = clazz.getName();
        final int lastIndex = name.lastIndexOf(".");
        final String s = (lastIndex > 0) ? name.substring(lastIndex + 1) : name;
        if (s.startsWith("ns")) {
            sb.append("NS_");
            sb.append(s.substring(2).toUpperCase());
        }
        else {
            sb.append(s.toUpperCase());
        }
        sb.append("_IID");
        String s2;
        try {
            s2 = (String)clazz.getDeclaredField(sb.toString()).get(null);
        }
        catch (NoSuchFieldException ex) {
            s2 = null;
        }
        catch (IllegalAccessException ex2) {
            System.err.println("ERROR: Could not get field " + sb.toString());
            s2 = null;
        }
        return s2;
    }
    
    public long getNativeHandleFromAWT(final Object o) {
        try {
            return this.mozilla.getNativeHandleFromAWT(o);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public long wrapJavaObject(final Object o, final String s) {
        try {
            return this.jxutils.wrapJavaObject(o, s);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    public Object wrapXPCOMObject(final long n, final String s) {
        try {
            return this.jxutils.wrapXPCOMObject(n, s);
        }
        catch (NullPointerException ex) {
            throw new XPCOMInitializationException("Must call Mozilla.getInstance().initialize() before using this method", ex);
        }
    }
    
    static {
        Mozilla.mozillaInstance = new Mozilla();
    }
}
