//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.security.*;
import java.util.*;

public enum SystemProperty
{
    COM_IBM_VM_BITMODE("com.ibm.vm.bitmode"), 
    FILE_ENCODING("file.encoding"), 
    FILE_ENCODING_PKG("file.encoding.pkg"), 
    FILE_SEPARATOR("file.separator"), 
    JAVA_AWT_GRAPHICSENV("java.awt.graphicsenv"), 
    JAVA_AWT_PRINTERJOB("java.awt.printerjob"), 
    JAVA_AWT_SMARTINVALIDATE("java.awt.smartInvalidate"), 
    JAVA_CLASS_PATH("java.class.path"), 
    JAVA_CLASS_VERSION("java.class.version"), 
    JAVA_ENDORSED_DIRS("java.endorsed.dirs"), 
    JAVA_EXT_DIRS("java.ext.dirs"), 
    JAVA_HOME("java.home"), 
    JAVA_IO_TMPDIR("JAVA_IO_TMPDIR", 12, "java.io.tmpdir", Type.READ_WRITE), 
    JAVA_LIBRARY_PATH("java.library.path"), 
    JAVA_RUNTIME_NAME("java.runtime.name"), 
    JAVA_RUNTIME_VERSION("java.runtime.version"), 
    JAVA_SPECIFICATION_NAME("java.specification.name"), 
    JAVA_SPECIFICATION_VENDOR("java.specification.vendor"), 
    JAVA_SPECIFICATION_VERSION("java.specification.version"), 
    JAVA_VERSION("java.version"), 
    JAVA_VENDOR("java.vendor"), 
    JAVA_VENDOR_URL("java.vendor.url"), 
    JAVA_VENDOR_URL_BUG("java.vendor.url.bug"), 
    JAVAWEBSTART_VERSION("javawebstart.version"), 
    JAVA_VM_INFO("java.vm.info"), 
    JAVA_VM_NAME("java.vm.name"), 
    JAVA_VM_SPECIFICATION_NAME("java.vm.specification.name"), 
    JAVA_VM_SPECIFICATION_VENDOR("java.vm.specification.vendor"), 
    JAVA_VM_SPECIFICATION_VERSION("java.vm.specification.version"), 
    JAVA_VM_VERSION("java.vm.version"), 
    JAVA_VM_VENDOR("java.vm.vendor"), 
    LINE_SEPARATOR("line.separator"), 
    OS_NAME("os.name"), 
    OS_ARCH("os.arch"), 
    OS_VERSION("os.version"), 
    PATH_SEPARATOR("path.separator"), 
    SUN_ARCH_DATA_MODEL("sun.arch.data.model"), 
    SUN_BOOT_CLASS_PATH("sun.boot.class.path"), 
    SUN_BOOT_LIBRARY_PATH("sun.boot.library.path"), 
    SUN_CPU_ENDIAN("sun.cpu.endian"), 
    SUN_CPU_ISALIST("sun.cpu.isalist"), 
    SUN_IO_UNICODE_ENCODING("sun.io.unicode.encoding"), 
    SUN_JAVA_LAUNCHER("sun.java.launcher"), 
    SUN_JNU_ENCODING("sun.jnu.encoding"), 
    SUN_MANAGEMENT_COMPILER("sun.management.compiler"), 
    SUN_OS_PATCH_LEVEL("sun.os.patch.level"), 
    USER_COUNTRY("user.country"), 
    USER_DIR("user.dir"), 
    USER_HOME("user.home"), 
    USER_LANGUAGE("user.language"), 
    USER_NAME("user.name"), 
    USER_TIMEZONE("user.timezone"), 
    SUN_AWT_DISABLEMIXING("sun.awt.disableMixing", Type.READ_WRITE), 
    SUN_AWT_NOERASEBACKGROUND("sun.awt.noerasebackground", Type.READ_WRITE), 
    SUN_AWT_XEMBEDSERVER("sun.awt.xembedserver", Type.READ_WRITE), 
    SUN_DESKTOP("sun.desktop"), 
    AWT_NATIVE_DOUBLE_BUFFERING("awt.nativeDoubleBuffering"), 
    AWT_TOOLKIT("awt.toolkit"), 
    FTP_NON_PROXY_HOSTS("ftp.nonProxyHosts"), 
    GOPHER_PROXY_SET("gopherProxySet"), 
    HTTP_NON_PROXY_HOSTS("http.nonProxyHosts"), 
    MRJ_VERSION("mrj.version"), 
    SOCKS_NON_PROXY_HOSTS("socksNonProxyHosts");
    
    private final String _name;
    private final boolean _readOnly;
    
    private SystemProperty(final String name) {
        this(name, Type.READ_ONLY);
    }
    
    private SystemProperty(String name, final Type type) {
        if (name == null) {
            throw new NullPointerException("name");
        }
        name = name.trim();
        if ("".equals(name)) {
            throw new IllegalArgumentException();
        }
        this._name = name;
        this._readOnly = (type == Type.READ_ONLY);
    }
    
    public String get() {
        return this.get(null);
    }
    
    public String get(final String defaultValue) {
        return AccessController.doPrivileged((PrivilegedAction<String>)new lIIIl(this, defaultValue));
    }
    
    public String set(final String value) {
        if (this.isReadOnly()) {
            throw new UnsupportedOperationException(this.getName() + " is a read-only property");
        }
        return AccessController.doPrivileged((PrivilegedAction<String>)new lIIlI(this, value));
    }
    
    public String getName() {
        return this._name;
    }
    
    public boolean isReadOnly() {
        return this._readOnly;
    }
    
    @Override
    public String toString() {
        return this.get();
    }
    
    public String toDebugString() {
        final StringBuilder buf = new StringBuilder();
        buf.append(this.name()).append(": ");
        buf.append(this.getName()).append("=");
        buf.append(this.get());
        if (this.isReadOnly()) {
            buf.append(" (read-only)");
        }
        return buf.toString();
    }
    
    public static void main(final String[] args) {
        final TreeMap<Object, Object> props = new TreeMap<Object, Object>();
        final TreeSet<SystemProperty> unknown = new TreeSet<SystemProperty>();
        props.putAll(System.getProperties());
        for (final SystemProperty p : values()) {
            System.out.println(p.toDebugString());
            if (!props.containsKey(p.getName())) {
                unknown.add(p);
            }
            else {
                props.remove(p.getName());
            }
            checkNaming(p);
        }
        if (unknown.size() > 0) {
            System.out.println("\n\n### UNKNOWN");
            for (final SystemProperty p2 : unknown) {
                System.out.println(p2.toDebugString());
            }
        }
        if (props.size() > 0) {
            System.out.println("\n\n### MISSING");
            for (final Map.Entry<Object, Object> e : props.entrySet()) {
                System.out.println(e);
            }
            System.out.println("\n\n### PLEASE POST THIS AT http://j.mp/props0 or http://j.mp/props1");
            for (final Map.Entry<Object, Object> e : props.entrySet()) {
                System.out.println(String.format("\t/**\n\t * %s only: known values: %s\n\t */\n\t%s(\"%s\"),", SystemProperty.OS_NAME, e.getValue(), toEnumName(e.getKey()), e.getKey()));
            }
        }
    }
    
    private static void checkNaming(final SystemProperty p) {
        final String expected = toEnumName(p.getName());
        if (!p.name().equals(expected)) {
            System.err.println("name missmatch: " + p.toDebugString() + " (expected " + expected + ")");
        }
    }
    
    private static String toEnumName(final String property) {
        final StringBuilder buf = new StringBuilder();
        for (final char c : property.toCharArray()) {
            if (Character.isUpperCase(c)) {
                buf.append('_').append(c);
            }
            else if (c == '.') {
                buf.append('_');
            }
            else {
                buf.append(Character.toUpperCase(c));
            }
        }
        return buf.toString();
    }
    
    private enum Type
    {
        READ_WRITE, 
        READ_ONLY;
    }
}
