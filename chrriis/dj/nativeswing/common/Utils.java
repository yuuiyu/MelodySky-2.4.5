//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import chrriis.dj.nativeswing.*;
import java.util.*;
import java.net.*;
import java.io.*;

public class Utils
{
    public static final boolean IS_JAVA_6_OR_GREATER;
    public static final boolean IS_JAVA_7_OR_GREATER;
    public static final boolean IS_MAC;
    public static final boolean IS_WINDOWS;
    public static final boolean IS_32_BIT;
    public static final boolean IS_64_BIT;
    public static final boolean IS_WEBSTART;
    public static final boolean IS_WINDOWS_VISTA_OR_GREATER;
    public static final boolean IS_WINDOWS_7_OR_GREATER;
    public static final String LINE_SEPARATOR;
    private static String localHostAddress;
    
    private Utils() {
    }
    
    public static String decodeURL(final String s) {
        try {
            return URLDecoder.decode(s, "UTF-8");
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String encodeURL(final String s) {
        String encodedString;
        try {
            encodedString = URLEncoder.encode(s, "UTF-8");
        }
        catch (Exception e) {
            encodedString = URLEncoder.encode(s);
        }
        return encodedString.replaceAll("\\+", "%20");
    }
    
    public static String encodeBase64(final String s, final boolean isURLSafe) {
        return Base64.encode(s, isURLSafe);
    }
    
    public static String decodeBase64(final String s) {
        return Base64.decode(s);
    }
    
    public static String escapeXML(final String s) {
        if (s == null || s.length() == 0) {
            return s;
        }
        final StringBuilder sb = new StringBuilder((int)(s.length() * 1.1));
        for (int i = 0; i < s.length(); ++i) {
            final char c = s.charAt(i);
            switch (c) {
                case '<': {
                    sb.append("&lt;");
                    break;
                }
                case '>': {
                    sb.append("&gt;");
                    break;
                }
                case '&': {
                    sb.append("&amp;");
                    break;
                }
                case '\'': {
                    sb.append("&apos;");
                    break;
                }
                case '\"': {
                    sb.append("&quot;");
                    break;
                }
                default: {
                    sb.append(c);
                    break;
                }
            }
        }
        return sb.toString();
    }
    
    public static File getLocalFile(final String path) {
        if (path == null) {
            return null;
        }
        if (path.startsWith("file:")) {
            final File file = new File(decodeURL(path.substring("file:".length())));
            if (file.exists()) {
                return simplifyLocalFile(file);
            }
        }
        final File file = new File(path);
        if (file.exists()) {
            return simplifyLocalFile(file);
        }
        return null;
    }
    
    private static File simplifyLocalFile(final File localFile) {
        try {
            final File cFile = localFile.getCanonicalFile();
            if (cFile.exists()) {
                return cFile;
            }
        }
        catch (Exception ex) {}
        return localFile;
    }
    
    public static File getClassPathFile(final String resourcePath) {
        final File file = getJARFile(resourcePath);
        return (file != null) ? file : getDirectory(resourcePath);
    }
    
    public static File getClassPathFile(final Class<?> clazz) {
        final File file = getJARFile(clazz);
        return (file != null) ? file : getDirectory(clazz);
    }
    
    public static File getJARFile(String resourcePath) {
        if (!resourcePath.startsWith("/")) {
            resourcePath = '/' + resourcePath;
        }
        return getJARFile(Utils.class, resourcePath);
    }
    
    public static File getJARFile(final Class<?> clazz) {
        return getJARFile(clazz, "/" + clazz.getName().replace('.', '/') + ".class");
    }
    
    private static File getJARFile(final Class<?> clazz, final String resourcePath) {
        final URL resource = getResourceWithinJavaModules(clazz, resourcePath);
        if (resource == null) {
            return null;
        }
        String classResourceURL = resource.toExternalForm();
        if (classResourceURL != null && classResourceURL.startsWith("jar:file:")) {
            classResourceURL = classResourceURL.substring("jar:file:".length());
            if (classResourceURL.endsWith("!" + resourcePath)) {
                return new File(decodeURL(classResourceURL.substring(0, classResourceURL.length() - 1 - resourcePath.length()).replace("+", "%2B")));
            }
        }
        return null;
    }
    
    public static File getDirectory(String resourcePath) {
        if (!resourcePath.startsWith("/")) {
            resourcePath = '/' + resourcePath;
        }
        return getDirectory(Utils.class, resourcePath);
    }
    
    public static File getDirectory(final Class<?> clazz) {
        return getDirectory(clazz, "/" + clazz.getName().replace('.', '/') + ".class");
    }
    
    private static File getDirectory(final Class<?> clazz, final String resourcePath) {
        String resourceName = resourcePath;
        if (resourceName.startsWith("/")) {
            resourceName = resourceName.substring(1);
        }
        final URL resource = getResourceWithinJavaModules(clazz, resourcePath);
        if (resource == null) {
            return null;
        }
        final String classResourceURL = resource.toExternalForm();
        if (classResourceURL != null && classResourceURL.startsWith("file:")) {
            File dir = new File(decodeURL(classResourceURL.substring("file:".length()))).getParentFile();
            for (int i = 0; i < resourceName.length(); ++i) {
                if (resourceName.charAt(i) == '/') {
                    dir = dir.getParentFile();
                }
            }
            return dir;
        }
        return null;
    }
    
    public static URL getResourceWithinJavaModules(final Class<?> clazz, final String resourcePath) {
        URL resource = clazz.getResource(resourcePath);
        if (resource == null && resourcePath.startsWith("/")) {
            resource = clazz.getClassLoader().getResource(resourcePath.substring(1));
        }
        return resource;
    }
    
    public static InputStream getResourceAsStreamWithinJavaModules(final Class<?> clazz, final String resourcePath) {
        InputStream resource = clazz.getResourceAsStream(resourcePath);
        if (resource == null && resourcePath.startsWith("/")) {
            resource = clazz.getClassLoader().getResourceAsStream(resourcePath.substring(1));
        }
        return resource;
    }
    
    public static void deleteAll(final File fileOrDir) {
        if (!fileOrDir.delete() && fileOrDir.isDirectory()) {
            for (final File file : fileOrDir.listFiles()) {
                deleteAll(file);
            }
            fileOrDir.delete();
        }
    }
    
    public static boolean equals(final Object o1, final Object o2) {
        return (o1 == null) ? (o2 == null) : o1.equals(o2);
    }
    
    public static String arrayDeepToString(final Object array) {
        if (array == null) {
            return null;
        }
        final Class<?> clazz = array.getClass();
        if (!clazz.isArray()) {
            return null;
        }
        if (clazz == boolean[].class) {
            return Arrays.toString((boolean[])array);
        }
        if (clazz == byte[].class) {
            return Arrays.toString((byte[])array);
        }
        if (clazz == short[].class) {
            return Arrays.toString((short[])array);
        }
        if (clazz == char[].class) {
            return Arrays.toString((char[])array);
        }
        if (clazz == int[].class) {
            return Arrays.toString((int[])array);
        }
        if (clazz == long[].class) {
            return Arrays.toString((long[])array);
        }
        if (clazz == float[].class) {
            return Arrays.toString((float[])array);
        }
        if (clazz == double[].class) {
            return Arrays.toString((double[])array);
        }
        return Arrays.deepToString((Object[])array);
    }
    
    public static String simplifyPath(final String path) {
        if (path.indexOf("//") != -1) {
            throw new IllegalArgumentException("The path is invalid: " + path);
        }
        final String[] crumbs = path.split("/");
        final List<String> crumbList = new ArrayList<String>(crumbs.length);
        for (final String crumb : crumbs) {
            if (!"".equals(crumb)) {
                if (!".".equals(crumb)) {
                    if ("..".equals(crumb)) {
                        final int index = crumbList.size() - 1;
                        if (index == -1) {
                            throw new IllegalArgumentException("The path is invalid: " + path);
                        }
                        crumbList.remove(index);
                    }
                    else {
                        crumbList.add(crumb);
                    }
                }
            }
        }
        final StringBuilder sb = new StringBuilder(path.length());
        if (path.startsWith("/")) {
            sb.append('/');
        }
        for (int crumbCount = crumbList.size(), i = 0; i < crumbCount; ++i) {
            if (i > 0) {
                sb.append('/');
            }
            sb.append(crumbList.get(i));
        }
        if (path.length() > 1 && path.endsWith("/")) {
            sb.append('/');
        }
        return sb.toString();
    }
    
    public static void printStackTraces() {
        printStackTraces(System.err);
    }
    
    public static void printStackTraces(final PrintStream printStream) {
        printStream.print(getStackTracesAsString());
    }
    
    public static void printStackTraces(final PrintWriter printWriter) {
        printWriter.print(getStackTracesAsString());
    }
    
    private static String getStackTracesAsString() {
        final Map<Thread, StackTraceElement[]> allStackTraces = Thread.getAllStackTraces();
        final Thread[] threads = allStackTraces.keySet().toArray(new Thread[0]);
        Arrays.sort(threads, (Comparator<? super Thread>)new lllI());
        final StringBuilder sb = new StringBuilder();
        for (final Thread t : threads) {
            sb.append(t.isDaemon() ? "Daemon Thread [" : "Thread [").append(t.getName()).append("] (").append(t.getState()).append(")").append(Utils.LINE_SEPARATOR);
            final StackTraceElement[] array2;
            final StackTraceElement[] stackTraceElements = array2 = allStackTraces.get(t);
            for (final StackTraceElement stackTraceElement : array2) {
                sb.append("\tat ").append(stackTraceElement).append(Utils.LINE_SEPARATOR);
            }
        }
        return sb.toString();
    }
    
    public static String getLocalHostAddress() {
        synchronized (Utils.class) {
            if (Utils.localHostAddress != null) {
                return "".equals(Utils.localHostAddress) ? null : Utils.localHostAddress;
            }
            String localHostAddress = NSSystemProperty.LOCALHOSTADDRESS.get();
            if ("_localhost_".equals(localHostAddress)) {
                try {
                    localHostAddress = InetAddress.getLocalHost().getHostAddress();
                }
                catch (Exception e) {
                    localHostAddress = null;
                }
            }
            if (localHostAddress == null) {
                final boolean isDebugging = Boolean.parseBoolean(NSSystemProperty.LOCALHOSTADDRESS_DEBUG_PRINTDETECTION.get());
                localHostAddress = getLocalHostAddress(0, isDebugging);
            }
            if (Boolean.parseBoolean(NSSystemProperty.LOCALHOSTADDRESS_DEBUG_PRINT.get())) {
                System.err.println("Local host address: " + localHostAddress);
            }
            Utils.localHostAddress = ((localHostAddress == null) ? "" : localHostAddress);
            return localHostAddress;
        }
    }
    
    public static String getLocalHostAddress(final int port) {
        return getLocalHostAddress(port, false);
    }
    
    private static String getLocalHostAddress(final int port, final boolean isDebugging) {
        if (isDebugging) {
            System.err.println("Local host address detection using " + ((port == 0) ? "an automatic port" : ("port " + port)) + ":");
        }
        final String loopbackAddress = "127.0.0.1";
        if (isDebugging) {
            System.err.print("  Trying 127.0.0.1: ");
        }
        if (isLocalHostAddressReachable(loopbackAddress, port)) {
            if (isDebugging) {
                System.err.println("success.");
            }
            return loopbackAddress;
        }
        if (isDebugging) {
            System.err.println("failed.");
        }
        final List<InetAddress> inetAddressList = new ArrayList<InetAddress>();
        try {
            final Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces();
            while (en.hasMoreElements()) {
                final NetworkInterface networkInterface = en.nextElement();
                final Enumeration<InetAddress> en2 = networkInterface.getInetAddresses();
                while (en2.hasMoreElements()) {
                    final InetAddress inetAddress = en2.nextElement();
                    if (!loopbackAddress.equals(inetAddress.getHostAddress())) {
                        inetAddressList.add(inetAddress);
                    }
                }
            }
        }
        catch (SocketException ex) {}
        Collections.sort(inetAddressList, (Comparator<? super InetAddress>)new lIIII());
        if (isDebugging) {
            System.err.println("  Trying addresses: " + inetAddressList);
        }
        for (final InetAddress address : inetAddressList) {
            final String hostAddress = address.getHostAddress();
            if (isDebugging) {
                System.err.print("    " + hostAddress + ": ");
            }
            if (isLocalHostAddressReachable(hostAddress, port)) {
                if (isDebugging) {
                    System.err.println("success.");
                }
                return hostAddress;
            }
            if (!isDebugging) {
                continue;
            }
            System.err.println("failed.");
        }
        try {
            if (isDebugging) {
                System.err.print("  Trying LocalHost: ");
            }
            final String hostAddress2 = InetAddress.getLocalHost().getHostAddress();
            if (isDebugging) {
                System.err.print("success (" + hostAddress2 + ").");
            }
            return hostAddress2;
        }
        catch (Exception ex2) {
            if (isDebugging) {
                System.err.println("failed.");
                System.err.println("  Failed to find a suitable local host address!");
            }
            return null;
        }
    }
    
    private static boolean isLocalHostAddressReachable(final String hostAddress, int port) {
        boolean isReachable = false;
        try {
            final ServerSocket serverSocket = new ServerSocket();
            serverSocket.bind(new InetSocketAddress(InetAddress.getByName(hostAddress), port));
            port = serverSocket.getLocalPort();
            try {
                final Socket socket = new Socket();
                socket.connect(new InetSocketAddress(hostAddress, port), 500);
                isReachable = true;
                socket.close();
            }
            catch (Exception e) {
                try {
                    serverSocket.close();
                }
                catch (IOException ex) {}
            }
            serverSocket.close();
        }
        catch (Exception ex2) {}
        return isReachable;
    }
    
    static {
        IS_JAVA_6_OR_GREATER = (SystemProperty.JAVA_VERSION.get().compareTo("1.6") >= 0);
        IS_JAVA_7_OR_GREATER = (SystemProperty.JAVA_VERSION.get().compareTo("1.7") >= 0);
        IS_WEBSTART = (SystemProperty.JAVAWEBSTART_VERSION.get() != null);
        final String os = SystemProperty.OS_NAME.get();
        IS_MAC = (os.startsWith("Mac") || os.startsWith("Darwin"));
        IS_WINDOWS = os.startsWith("Windows");
        final String arch = SystemProperty.OS_ARCH.get();
        IS_64_BIT = ("x86_64".equals(arch) || "x64".equals(arch) || "amd64".equals(arch) || "ia64".equals(arch) || "ppc64".equals(arch) || "IA64N".equals(arch) || "64".equals(SystemProperty.SUN_ARCH_DATA_MODEL.get()) || "64".equals(SystemProperty.COM_IBM_VM_BITMODE.get()));
        IS_32_BIT = !Utils.IS_64_BIT;
        IS_WINDOWS_VISTA_OR_GREATER = (Utils.IS_WINDOWS && SystemProperty.OS_VERSION.get().compareTo("6.0") >= 0);
        IS_WINDOWS_7_OR_GREATER = (Utils.IS_WINDOWS && SystemProperty.OS_VERSION.get().compareTo("6.1") >= 0);
        LINE_SEPARATOR = SystemProperty.LINE_SEPARATOR.get();
    }
}
