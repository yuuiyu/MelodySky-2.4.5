//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.internal;

import java.nio.file.*;
import java.io.*;
import java.util.jar.*;
import java.net.*;

public class Library
{
    static int MAJOR_VERSION;
    static int MINOR_VERSION;
    static int REVISION;
    public static final int JAVA_VERSION;
    public static final int SWT_VERSION;
    public static final String USER_HOME;
    static final String SEPARATOR;
    static final String DELIMITER;
    static final String JAVA_LIB_PATH = "java.library.path";
    static final String SWT_LIB_PATH = "swt.library.path";
    static final String SUFFIX_64 = "-64";
    static final String SWT_LIB_DIR;
    
    static String arch() {
        final String osArch = System.getProperty("os.arch");
        if (osArch.equals("amd64")) {
            return "x86_64";
        }
        return osArch;
    }
    
    static String os() {
        final String osName = System.getProperty("os.name");
        if (osName.equals("Linux")) {
            return "linux";
        }
        if (osName.equals("Mac OS X")) {
            return "macosx";
        }
        if (osName.startsWith("Win")) {
            return "win32";
        }
        return osName;
    }
    
    static void chmod(final String permision, final String path) {
        if (os().equals("win32")) {
            return;
        }
        try {
            Runtime.getRuntime().exec(new String[] { "chmod", permision, path }).waitFor();
        }
        catch (Throwable e) {
            try {
                new File(path).setExecutable(true);
            }
            catch (Throwable t) {}
        }
    }
    
    static long longConst() {
        return 8589934591L;
    }
    
    static int parseVersion(final String version) {
        if (version == null) {
            return 0;
        }
        int major = 0;
        int minor = 0;
        int micro = 0;
        final int length = version.length();
        int index = 0;
        int start = 0;
        while (index < length && Character.isDigit(version.charAt(index))) {
            ++index;
        }
        try {
            if (start < length) {
                major = Integer.parseInt(version.substring(start, index));
            }
        }
        catch (NumberFormatException ex) {}
        for (start = ++index; index < length && Character.isDigit(version.charAt(index)); ++index) {}
        try {
            if (start < length) {
                minor = Integer.parseInt(version.substring(start, index));
            }
        }
        catch (NumberFormatException ex2) {}
        for (start = ++index; index < length && Character.isDigit(version.charAt(index)); ++index) {}
        try {
            if (start < length) {
                micro = Integer.parseInt(version.substring(start, index));
            }
        }
        catch (NumberFormatException ex3) {}
        return JAVA_VERSION(major, minor, micro);
    }
    
    public static int JAVA_VERSION(final int major, final int minor, final int micro) {
        return (major << 16) + (minor << 8) + micro;
    }
    
    public static int SWT_VERSION(final int major, final int minor) {
        return major * 1000 + minor;
    }
    
    private static boolean extractResource(final String resourceName, final File outFile) {
        try (final InputStream inputStream = Library.class.getResourceAsStream(resourceName)) {
            if (inputStream == null) {
                final boolean b = false;
                if (inputStream != null) {
                    inputStream.close();
                }
                return false;
            }
            Files.copy(inputStream, outFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
        }
        catch (Throwable e) {
            return false;
        }
        return true;
    }
    
    static boolean extract(final String extractToFilePath, final String mappedName) {
        final File file = new File(extractToFilePath);
        if (file.exists()) {
            return true;
        }
        File tempFile;
        try {
            tempFile = File.createTempFile(file.getName(), ".tmp", file.getParentFile());
        }
        catch (Throwable e) {
            return false;
        }
        final String resourceName = "/" + mappedName.replace('\\', '/');
        if (!extractResource(resourceName, tempFile)) {
            tempFile.delete();
            return false;
        }
        chmod("755", tempFile.getPath());
        try {
            Files.move(tempFile.toPath(), file.toPath(), new CopyOption[0]);
        }
        catch (Throwable e2) {
            tempFile.delete();
        }
        return true;
    }
    
    static boolean isLoadable() {
        final URL url = Platform.class.getClassLoader().getResource("org/eclipse/swt/internal/Library.class");
        if (!url.getProtocol().equals("jar")) {
            return true;
        }
        Attributes attributes = null;
        try {
            final URLConnection connection = url.openConnection();
            if (!(connection instanceof JarURLConnection)) {
                return false;
            }
            final JarURLConnection jc = (JarURLConnection)connection;
            attributes = jc.getMainAttributes();
        }
        catch (IOException e) {
            return false;
        }
        final String os = os();
        final String arch = arch();
        final String manifestOS = attributes.getValue("SWT-OS");
        final String manifestArch = attributes.getValue("SWT-Arch");
        return arch.equals(manifestArch) && os.equals(manifestOS);
    }
    
    static boolean load(final String libName, final StringBuilder message) {
        try {
            if (libName.contains(Library.SEPARATOR)) {
                System.load(libName);
            }
            else {
                System.loadLibrary(libName);
            }
            return true;
        }
        catch (UnsatisfiedLinkError e) {
            if (message.length() == 0) {
                message.append(Library.DELIMITER);
            }
            message.append('\t');
            message.append(e.getMessage());
            message.append(Library.DELIMITER);
            return false;
        }
    }
    
    public static void loadLibrary(final String name) {
        loadLibrary(name, true);
    }
    
    public static void loadLibrary(final String name, final boolean mapName) {
        String prop = System.getProperty("sun.arch.data.model");
        if (prop == null) {
            prop = System.getProperty("com.ibm.vm.bitmode");
        }
        if (prop != null && "32".equals(prop)) {
            throw new UnsatisfiedLinkError("Cannot load 64-bit SWT libraries on 32-bit JVM");
        }
        final int candidates = 3;
        final String[] libNames = new String[3];
        final String[] mappedNames = new String[3];
        if (mapName) {
            final String version = getVersionString();
            libNames[0] = name + "-win32-" + version;
            libNames[1] = name + "-win32";
            libNames[2] = name;
            for (int i = 0; i < 3; ++i) {
                mappedNames[i] = mapLibraryName(libNames[i]);
            }
        }
        else {
            for (int j = 0; j < 3; ++j) {
                libNames[j] = (mappedNames[j] = name);
            }
        }
        final StringBuilder message = new StringBuilder();
        String path = System.getProperty("swt.library.path");
        if (path != null) {
            path = new File(path).getAbsolutePath();
            for (int k = 0; k < 3; ++k) {
                if ((k == 0 || mapName) && load(path + Library.SEPARATOR + mappedNames[k], message)) {
                    return;
                }
            }
        }
        for (int k = 0; k < 3; ++k) {
            if ((k == 0 || mapName) && load(libNames[k], message)) {
                return;
            }
        }
        final String[] fileNames = new String[3];
        int l;
        for (l = 0, l = 0; l < 3; ++l) {
            fileNames[l] = mappedNames[l];
        }
        if (path == null) {
            path = Library.USER_HOME;
            final File dir = new File(path, Library.SWT_LIB_DIR);
            int m = 0;
            if ((dir.exists() && dir.isDirectory()) || dir.mkdirs()) {
                path = dir.getAbsolutePath();
            }
            else {
                for (m = 0; m < 3; ++m) {
                    fileNames[m] = mapLibraryName(libNames[m] + "-64");
                }
            }
            for (m = 0; m < 3; ++m) {
                if ((m == 0 || mapName) && load(path + Library.SEPARATOR + fileNames[m], message)) {
                    return;
                }
            }
        }
        if (path != null) {
            for (l = 0; l < 3; ++l) {
                if ((l == 0 || mapName) && extract(path + Library.SEPARATOR + fileNames[l], mappedNames[l]) && load(path + Library.SEPARATOR + fileNames[l], message)) {
                    return;
                }
            }
        }
        throw new UnsatisfiedLinkError("Could not load SWT library. Reasons: " + message.toString());
    }
    
    static String mapLibraryName(final String libName) {
        return mapLibraryName(libName, true);
    }
    
    static String mapLibraryName(String libName, final boolean replaceDylib) {
        libName = System.mapLibraryName(libName);
        final String ext = ".dylib";
        if (libName.endsWith(".dylib") && replaceDylib) {
            libName = libName.substring(0, libName.length() - ".dylib".length()) + ".jnilib";
        }
        return libName;
    }
    
    public static String getVersionString() {
        String version = System.getProperty("swt.version");
        if (version == null) {
            version = "" + Library.MAJOR_VERSION;
            if (Library.MINOR_VERSION < 10) {
                version += "00";
            }
            else if (Library.MINOR_VERSION < 100) {}
            version += Library.MINOR_VERSION;
            if (Library.REVISION > 0) {
                version = version + "r" + Library.REVISION;
            }
        }
        return version;
    }
    
    static {
        Library.MAJOR_VERSION = 4;
        Library.MINOR_VERSION = 952;
        Library.REVISION = 11;
        DELIMITER = System.lineSeparator();
        SEPARATOR = File.separator;
        USER_HOME = System.getProperty("user.home");
        SWT_LIB_DIR = ".swt" + Library.SEPARATOR + "lib" + Library.SEPARATOR + os() + Library.SEPARATOR + arch();
        JAVA_VERSION = parseVersion(System.getProperty("java.version"));
        SWT_VERSION = SWT_VERSION(Library.MAJOR_VERSION, Library.MINOR_VERSION);
    }
}
