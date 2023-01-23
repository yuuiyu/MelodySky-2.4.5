//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import chrriis.dj.nativeswing.*;
import java.net.*;
import java.lang.reflect.*;
import java.util.concurrent.*;
import java.util.*;
import java.io.*;

public class WebServer
{
    private int port;
    private volatile boolean isRunning;
    private volatile ServerSocket serverSocket;
    private volatile int instanceID;
    private List<ClassLoader> referenceClassLoaderList;
    private List<WebServerContentProvider> contentProviderList;
    private static WebServer webServer;
    private static Object LOCK;
    private static String hostAddress;
    
    public WebServer() {
        this(0);
    }
    
    public WebServer(final int port) {
        this.referenceClassLoaderList = new ArrayList<ClassLoader>(1);
        this.contentProviderList = new ArrayList<WebServerContentProvider>();
        this.port = port;
    }
    
    public void stop() {
        this.isRunning = false;
        if (this.serverSocket != null) {
            final ServerSocket serverSocket = this.serverSocket;
            this.serverSocket = null;
            try {
                serverSocket.close();
            }
            catch (IOException ex) {}
        }
    }
    
    public boolean isRunning() {
        return this.isRunning;
    }
    
    public void start() throws IOException {
        this.start(true);
    }
    
    public void start(final boolean isDaemon) throws IOException {
        class lIlIl extends Thread
        {
            final /* synthetic */ WebServer this$0;
            
            lIlIl(final WebServer this$0, final String x0) {
                this.this$0 = this$0;
                super(x0);
            }
            
            @Override
            public void run() {
                while (this.this$0.isRunning) {
                    try {
                        final Socket socket = this.this$0.serverSocket.accept();
                        socket.setSoTimeout(10000);
                        try {
                            WebServerConnectionThread.semaphore.acquire();
                        }
                        catch (InterruptedException ex) {}
                        final WebServerConnectionThread webServerConnectionThread = new WebServerConnectionThread(socket);
                        webServerConnectionThread.start();
                    }
                    catch (Exception e) {
                        if (this.this$0.serverSocket == null) {
                            continue;
                        }
                        e.printStackTrace();
                    }
                }
                this.this$0.serverSocket = null;
                ObjectRegistry.getInstance().remove(this.this$0.instanceID);
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: getfield        chrriis/dj/nativeswing/common/WebServer.isRunning:Z
        //     4: ifeq            8
        //     7: return         
        //     8: aload_0         /* this */
        //     9: iconst_1       
        //    10: putfield        chrriis/dj/nativeswing/common/WebServer.isRunning:Z
        //    13: aload_0         /* this */
        //    14: invokestatic    chrriis/dj/nativeswing/common/ObjectRegistry.getInstance:()Lchrriis/dj/nativeswing/common/ObjectRegistry;
        //    17: aload_0         /* this */
        //    18: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.add:(Ljava/lang/Object;)I
        //    21: putfield        chrriis/dj/nativeswing/common/WebServer.instanceID:I
        //    24: aload_0         /* this */
        //    25: new             Ljava/net/ServerSocket;
        //    28: dup            
        //    29: invokespecial   java/net/ServerSocket.<init>:()V
        //    32: putfield        chrriis/dj/nativeswing/common/WebServer.serverSocket:Ljava/net/ServerSocket;
        //    35: aload_0         /* this */
        //    36: getfield        chrriis/dj/nativeswing/common/WebServer.serverSocket:Ljava/net/ServerSocket;
        //    39: new             Ljava/net/InetSocketAddress;
        //    42: dup            
        //    43: invokestatic    chrriis/dj/nativeswing/common/WebServer.getHostAddress:()Ljava/lang/String;
        //    46: invokestatic    java/net/InetAddress.getByName:(Ljava/lang/String;)Ljava/net/InetAddress;
        //    49: aload_0         /* this */
        //    50: getfield        chrriis/dj/nativeswing/common/WebServer.port:I
        //    53: invokespecial   java/net/InetSocketAddress.<init>:(Ljava/net/InetAddress;I)V
        //    56: invokevirtual   java/net/ServerSocket.bind:(Ljava/net/SocketAddress;)V
        //    59: aload_0         /* this */
        //    60: aload_0         /* this */
        //    61: getfield        chrriis/dj/nativeswing/common/WebServer.serverSocket:Ljava/net/ServerSocket;
        //    64: invokevirtual   java/net/ServerSocket.getLocalPort:()I
        //    67: putfield        chrriis/dj/nativeswing/common/WebServer.port:I
        //    70: getstatic       chrriis/dj/nativeswing/NSSystemProperty.WEBSERVER_DEBUG_PRINTPORT:Lchrriis/dj/nativeswing/NSSystemProperty;
        //    73: invokevirtual   chrriis/dj/nativeswing/NSSystemProperty.get:()Ljava/lang/String;
        //    76: invokestatic    java/lang/Boolean.parseBoolean:(Ljava/lang/String;)Z
        //    79: ifeq            110
        //    82: getstatic       java/lang/System.err:Ljava/io/PrintStream;
        //    85: new             Ljava/lang/StringBuilder;
        //    88: dup            
        //    89: invokespecial   java/lang/StringBuilder.<init>:()V
        //    92: ldc             "Web Server port: "
        //    94: invokevirtual   java/lang/StringBuilder.append:(Ljava/lang/String;)Ljava/lang/StringBuilder;
        //    97: aload_0         /* this */
        //    98: getfield        chrriis/dj/nativeswing/common/WebServer.port:I
        //   101: invokevirtual   java/lang/StringBuilder.append:(I)Ljava/lang/StringBuilder;
        //   104: invokevirtual   java/lang/StringBuilder.toString:()Ljava/lang/String;
        //   107: invokevirtual   java/io/PrintStream.println:(Ljava/lang/String;)V
        //   110: new             Lchrriis/dj/nativeswing/common/lIlIl;
        //   113: dup            
        //   114: aload_0         /* this */
        //   115: ldc             "WebServer"
        //   117: invokespecial   chrriis/dj/nativeswing/common/lIlIl.<init>:(Lchrriis/dj/nativeswing/common/WebServer;Ljava/lang/String;)V
        //   120: astore_2        /* listenerThread */
        //   121: aload_2         /* listenerThread */
        //   122: iload_1         /* isDaemon */
        //   123: invokevirtual   java/lang/Thread.setDaemon:(Z)V
        //   126: aload_2         /* listenerThread */
        //   127: invokevirtual   java/lang/Thread.start:()V
        //   130: return         
        //    Exceptions:
        //  throws java.io.IOException
        //    StackMapTable: 00 02 08 FB 00 65
        // 
        // The error that occurred was:
        // 
        // java.lang.NullPointerException
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:344)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.convertType(AstBuilder.java:173)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformCall(AstMethodBodyBuilder.java:1119)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:1009)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformByteCode(AstMethodBodyBuilder.java:554)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformExpression(AstMethodBodyBuilder.java:540)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformNode(AstMethodBodyBuilder.java:392)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.transformBlock(AstMethodBodyBuilder.java:333)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:294)
        //     at com.strobel.decompiler.languages.java.ast.AstMethodBodyBuilder.createMethodBody(AstMethodBodyBuilder.java:99)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethodBody(AstBuilder.java:782)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createMethod(AstBuilder.java:675)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:552)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeCore(AstBuilder.java:519)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createTypeNoCache(AstBuilder.java:161)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createType(AstBuilder.java:150)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addType(AstBuilder.java:125)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.buildAst(JavaLanguage.java:71)
        //     at com.strobel.decompiler.languages.java.JavaLanguage.decompileType(JavaLanguage.java:59)
        //     at com.strobel.decompiler.Decompiler.decompile(Decompiler.java:70)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompile(Deobfuscator3000.java:538)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.decompileAndDeobfuscate(Deobfuscator3000.java:552)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.processMod(Deobfuscator3000.java:510)
        //     at org.ugp.mc.deobfuscator.Deobfuscator3000.lambda$21(Deobfuscator3000.java:329)
        //     at java.base/java.lang.Thread.run(Unknown Source)
        // 
        throw new IllegalStateException("An error occurred while decompiling this method.");
    }
    
    public int getPort() {
        return this.port;
    }
    
    public String getURLPrefix() {
        if (WebServer.hostAddress.indexOf(58) >= 0) {
            return "http://[" + WebServer.hostAddress + "]:" + this.port;
        }
        return "http://" + WebServer.hostAddress + ":" + this.port;
    }
    
    public String getDynamicContentURL(final String className, final String parameter) {
        return this.getURLPrefix() + "/class/" + this.instanceID + "/" + className + "/" + Utils.encodeURL(parameter);
    }
    
    public String getDynamicContentURL(final String className, final String codebase, final String parameter) {
        return this.getURLPrefix() + "/class/" + this.instanceID + "/" + className + "/" + codebase + "/" + Utils.encodeURL(parameter);
    }
    
    public String getClassPathResourceURL(final String className, String resourcePath) {
        if (!resourcePath.startsWith("/")) {
            String classPath = className.replace('.', '/');
            classPath = classPath.substring(0, classPath.lastIndexOf(47) + 1);
            resourcePath = "/" + classPath + resourcePath;
        }
        return this.getURLPrefix() + "/classpath/" + this.instanceID + Utils.simplifyPath(resourcePath);
    }
    
    public String getResourcePathURL(String codeBase, String resourcePath) {
        if (codeBase == null) {
            codeBase = new File(SystemProperty.USER_DIR.get()).getAbsolutePath();
        }
        if (Boolean.parseBoolean(NSSystemProperty.WEBSERVER_ACTIVATEOLDRESOURCEMETHOD.get())) {
            if (Utils.IS_WINDOWS) {
                codeBase = codeBase.replace('\\', '/');
                resourcePath = resourcePath.replace('\\', '/');
            }
            return this.getURLPrefix() + "/resource/" + Utils.encodeURL(codeBase) + "/" + Utils.encodeURL(resourcePath);
        }
        return this.getURLPrefix() + "/location/" + Utils.encodeBase64(codeBase, true) + "/" + Utils.encodeURL(resourcePath);
    }
    
    public WebServerContent getURLContent(final String resourceURL) {
        try {
            final HTTPRequest httpRequest = new HTTPRequest(new URL(resourceURL).getPath(), null);
            return getWebServerContent(httpRequest);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public void addReferenceClassLoader(final ClassLoader referenceClassLoader) {
        if (referenceClassLoader == null || referenceClassLoader == this.getClass().getClassLoader()) {
            return;
        }
        this.referenceClassLoaderList.add(0, referenceClassLoader);
    }
    
    public void removeReferenceClassLoader(final ClassLoader referenceClassLoader) {
        if (referenceClassLoader == null || referenceClassLoader == this.getClass().getClassLoader()) {
            return;
        }
        this.referenceClassLoaderList.remove(referenceClassLoader);
    }
    
    public void addContentProvider(final WebServerContentProvider webServerContentProvider) {
        this.contentProviderList.add(webServerContentProvider);
    }
    
    public void removeContentProvider(final WebServerContentProvider webServerContentProvider) {
        this.contentProviderList.remove(webServerContentProvider);
    }
    
    protected static WebServerContent getWebServerContent(HTTPRequest httpRequest) {
        String parameter = httpRequest.getResourcePath();
        if (parameter.startsWith("/")) {
            parameter = parameter.substring(1);
        }
        int index = parameter.indexOf(47);
        if (index != -1) {
            final String type = parameter.substring(0, index);
            parameter = parameter.substring(index + 1);
            if ("class".equals(type)) {
                index = parameter.indexOf(47);
                final WebServer webServer = (WebServer)ObjectRegistry.getInstance().get(Integer.parseInt(parameter.substring(0, index)));
                if (webServer == null) {
                    return null;
                }
                parameter = parameter.substring(index + 1);
                index = parameter.indexOf(47);
                final String className = parameter.substring(0, index);
                parameter = Utils.decodeURL(parameter.substring(index + 1));
                httpRequest = httpRequest.clone();
                try {
                    Class<?> clazz = null;
                    for (final ClassLoader referenceClassLoader : webServer.referenceClassLoaderList) {
                        try {
                            clazz = Class.forName(className, true, referenceClassLoader);
                        }
                        catch (Exception ex) {
                            continue;
                        }
                        break;
                    }
                    if (clazz == null) {
                        clazz = Class.forName(className);
                    }
                    final Method getWebServerContentMethod = clazz.getDeclaredMethod("getWebServerContent", HTTPRequest.class);
                    getWebServerContentMethod.setAccessible(true);
                    httpRequest.setResourcePath(parameter);
                    return (WebServerContent)getWebServerContentMethod.invoke(null, httpRequest);
                }
                catch (Exception e) {
                    e.printStackTrace();
                    return null;
                }
            }
            if ("classpath".equals(type)) {
                index = parameter.indexOf(47);
                final WebServer webServer = (WebServer)ObjectRegistry.getInstance().get(Integer.parseInt(parameter.substring(0, index)));
                if (webServer == null) {
                    return null;
                }
                parameter = parameter.substring(index + 1);
                final String resourcePath = Utils.decodeURL(removeHTMLAnchor(parameter));
                return (WebServerContent)new lIllI(resourcePath, webServer);
            }
            else {
                if ("location".equals(type)) {
                    index = parameter.indexOf(47);
                    final String codeBase = Utils.decodeBase64(parameter.substring(0, index));
                    parameter = Utils.decodeURL(removeHTMLAnchor(parameter.substring(index + 1)));
                    String resourceURL;
                    try {
                        final URL url = new URL(codeBase);
                        final int port = url.getPort();
                        resourceURL = url.getProtocol() + "://" + url.getHost() + ((port != -1) ? (":" + port) : "");
                        if (parameter.startsWith("/")) {
                            resourceURL += parameter;
                        }
                        else {
                            String path = url.getPath();
                            path = path.substring(0, path.lastIndexOf(47) + 1) + parameter;
                            resourceURL += (path.startsWith("/") ? path : ("/" + path));
                        }
                    }
                    catch (Exception e) {
                        final File file = Utils.getLocalFile(new File(codeBase, parameter).getAbsolutePath());
                        if (file != null) {
                            resourceURL = new File(codeBase, parameter).toURI().toString();
                        }
                        else {
                            resourceURL = codeBase + "/" + parameter;
                        }
                    }
                    final String resourceURL_ = resourceURL;
                    return (WebServerContent)new llIl(resourceURL_);
                }
                if ("resource".equals(type)) {
                    index = parameter.indexOf(47);
                    if (index > 0) {
                        final String subs = parameter.substring(index - 1);
                        if (subs.startsWith("://")) {
                            index = parameter.indexOf(47, index + 2);
                        }
                    }
                    final String codeBase = Utils.decodeURL(parameter.substring(0, index));
                    parameter = Utils.decodeURL(parameter.substring(index + 1));
                    String resourceURL;
                    try {
                        final URL url = new URL(codeBase);
                        final int port = url.getPort();
                        resourceURL = url.getProtocol() + "://" + url.getHost() + ((port != -1) ? (":" + port) : "");
                        if (parameter.startsWith("/")) {
                            resourceURL += removeHTMLAnchor(parameter);
                        }
                        else {
                            String path = url.getPath();
                            path = path.substring(0, path.lastIndexOf(47) + 1) + parameter;
                            resourceURL += (path.startsWith("/") ? path : ("/" + path));
                        }
                    }
                    catch (Exception e) {
                        final File file = Utils.getLocalFile(new File(codeBase, removeHTMLAnchor(parameter)).getAbsolutePath());
                        if (file != null) {
                            resourceURL = new File(codeBase, removeHTMLAnchor(parameter)).toURI().toString();
                        }
                        else {
                            resourceURL = codeBase + "/" + removeHTMLAnchor(parameter);
                        }
                    }
                    final String resourceURL_ = resourceURL;
                    return (WebServerContent)new llII(resourceURL_);
                }
            }
        }
        for (final WebServerContentProvider contentProvider : WebServer.webServer.contentProviderList) {
            final WebServerContent webServerContent = contentProvider.getWebServerContent(httpRequest);
            if (webServerContent != null) {
                return webServerContent;
            }
        }
        return null;
    }
    
    private static String removeHTMLAnchor(String location) {
        final int anchorIndex = location.indexOf(35);
        if (anchorIndex > 0) {
            location = location.substring(0, anchorIndex);
        }
        return location;
    }
    
    private static String getHostAddress() {
        return WebServer.hostAddress;
    }
    
    public static void stopDefaultWebServer() {
        synchronized (WebServer.LOCK) {
            if (WebServer.webServer != null) {
                WebServer.webServer.stop();
                WebServer.webServer = null;
            }
        }
    }
    
    public static WebServer getDefaultWebServer() {
        synchronized (WebServer.LOCK) {
            if (WebServer.webServer != null) {
                return WebServer.webServer;
            }
            WebServer.webServer = new WebServer();
            try {
                final boolean isApplet = "applet".equals(NSSystemProperty.DEPLOYMENT_TYPE.get());
                WebServer.webServer.start(!isApplet);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return WebServer.webServer;
        }
    }
    
    static {
        WebServer.LOCK = new Object();
        String hostAddress = Utils.getLocalHostAddress();
        if (hostAddress == null) {
            hostAddress = "127.0.0.1";
        }
        WebServer.hostAddress = hostAddress;
    }
    
    public static class HTTPRequest implements Cloneable
    {
        private Map<String, String> headerMap;
        private String endQuery;
        private String urlPath;
        private String resourcePath;
        private String anchor;
        private Map<String, String> queryParameterMap;
        private boolean isPostMethod;
        private HTTPData[] httpPostDataArray;
        
        HTTPRequest(final String urlPath, final Map<String, String> headerMap) {
            this.endQuery = "";
            this.queryParameterMap = new HashMap<String, String>();
            this.headerMap = ((headerMap == null) ? new HashMap<String, String>() : headerMap);
            this.setURLPath(urlPath);
        }
        
        public Map<String, String> getHeaderMap() {
            return this.headerMap;
        }
        
        void setURLPath(final String urlPath) {
            this.urlPath = urlPath;
            this.resourcePath = urlPath;
            int index = this.resourcePath.indexOf(63);
            if (index != -1) {
                final String queryString = this.resourcePath.substring(index + 1);
                this.endQuery = '?' + queryString;
                this.resourcePath = this.resourcePath.substring(0, index);
                for (final String content : queryString.split("&")) {
                    final int eqIndex = content.indexOf(61);
                    if (eqIndex > 0) {
                        final String key = content.substring(0, eqIndex);
                        final String value = Utils.decodeURL(content.substring(eqIndex + 1));
                        this.queryParameterMap.put(key, value);
                    }
                    else {
                        this.queryParameterMap.put(content, "");
                    }
                }
            }
            index = this.resourcePath.indexOf(35);
            if (index != -1) {
                this.anchor = this.resourcePath.substring(index + 1);
                this.endQuery = '#' + this.anchor + this.endQuery;
                this.resourcePath = this.resourcePath.substring(0, index);
            }
        }
        
        public String getURLPath() {
            return this.urlPath;
        }
        
        void setResourcePath(final String resourcePath) {
            this.resourcePath = resourcePath;
            this.urlPath = resourcePath + this.endQuery;
        }
        
        public String getResourcePath() {
            return this.resourcePath;
        }
        
        public String getAnchor() {
            return this.anchor;
        }
        
        public Map<String, String> getQueryParameterMap() {
            return this.queryParameterMap;
        }
        
        void setPostMethod(final boolean isPostMethod) {
            this.isPostMethod = isPostMethod;
        }
        
        public boolean isPostMethod() {
            return this.isPostMethod;
        }
        
        void setHTTPPostDataArray(final HTTPData[] httpPostDataArray) {
            this.httpPostDataArray = httpPostDataArray;
        }
        
        public HTTPData[] getHTTPPostDataArray() {
            return this.httpPostDataArray;
        }
        
        @Override
        protected HTTPRequest clone() {
            try {
                final HTTPRequest httpRequest = (HTTPRequest)super.clone();
                httpRequest.queryParameterMap = new HashMap<String, String>(this.queryParameterMap);
                return httpRequest;
            }
            catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
        }
    }
    
    public static class HTTPData
    {
        private Map<String, String> headerMap;
        private byte[] bytes;
        
        HTTPData() {
            this.headerMap = new HashMap<String, String>();
        }
        
        public Map<String, String> getHeaderMap() {
            return this.headerMap;
        }
        
        public byte[] getBytes() {
            return this.bytes;
        }
        
        void setBytes(final byte[] bytes) {
            this.bytes = bytes;
        }
    }
    
    public abstract static class WebServerContent
    {
        private static final String MIME_APPLICATION_OCTET_STREAM = "application/octet-stream";
        
        public static String getDefaultMimeType(final String extension) {
            final String mimeType = MimeTypes.getMimeType(extension);
            return (mimeType == null) ? "application/octet-stream" : mimeType;
        }
        
        public abstract InputStream getInputStream();
        
        public static InputStream getInputStream(final String content) {
            if (content == null) {
                return null;
            }
            try {
                return new ByteArrayInputStream(content.getBytes("UTF-8"));
            }
            catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
        
        public String getContentType() {
            return getDefaultMimeType(".html");
        }
        
        public long getContentLength() {
            return -1L;
        }
        
        public long getLastModified() {
            return System.currentTimeMillis();
        }
    }
    
    private static class WebServerConnectionThread extends Thread
    {
        private static int threadInitNumber;
        private static Semaphore semaphore;
        private Socket socket;
        private static final String LS;
        
        private static synchronized int nextThreadNumber() {
            return WebServerConnectionThread.threadInitNumber++;
        }
        
        public WebServerConnectionThread(final Socket socket) {
            super("WebServer Connection-" + nextThreadNumber());
            this.socket = socket;
            this.setDaemon(true);
        }
        
        static void writeHTTPHeaders(final BufferedOutputStream out, final int code, final String contentType, final long contentLength, final long lastModified) {
            final StringBuilder sb = new StringBuilder();
            sb.append("HTTP/1.0 " + code + " OK" + WebServerConnectionThread.LS);
            sb.append("Content-Type: " + contentType + WebServerConnectionThread.LS);
            sb.append("Server: WebServer/1.0" + WebServerConnectionThread.LS);
            sb.append("Date: " + new Date() + WebServerConnectionThread.LS);
            if (contentLength != -1L) {
                sb.append("Content-Length: " + contentLength + WebServerConnectionThread.LS);
            }
            sb.append(WebServerConnectionThread.LS);
            try {
                out.write(sb.toString().getBytes("UTF-8"));
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        
        static void writeHTTPError(final BufferedOutputStream out, final int code, final String message) {
            writeHTTPHeaders(out, code, "text/html", message.length(), System.currentTimeMillis());
            try {
                out.write(message.getBytes("UTF-8"));
                out.flush();
                out.close();
            }
            catch (IOException ex) {}
        }
        
        @Override
        public void run() {
            try {
                final HTTPInputStream in = new HTTPInputStream(new BufferedInputStream(this.socket.getInputStream()));
                final BufferedOutputStream out = new BufferedOutputStream(this.socket.getOutputStream());
                try {
                    final String request = in.readAsciiLine();
                    if (request == null || (!request.endsWith(" HTTP/1.0") && !request.endsWith("HTTP/1.1"))) {
                        writeHTTPError(out, 500, "Invalid Method.");
                        return;
                    }
                    boolean isPostMethod = false;
                    if (request.startsWith("POST ")) {
                        isPostMethod = true;
                    }
                    else if (!request.startsWith("GET ")) {
                        writeHTTPError(out, 500, "Invalid Method.");
                        return;
                    }
                    final String resourcePath = request.substring((isPostMethod ? "POST " : "GET ").length(), request.length() - " HTTP/1.0".length());
                    final Map<String, String> headerMap = new HashMap<String, String>();
                    String header;
                    while ((header = in.readAsciiLine()).length() > 0) {
                        final int index = header.indexOf(": ");
                        if (index > 0) {
                            headerMap.put(header.substring(0, index), header.substring(index + ": ".length()));
                        }
                    }
                    final HTTPRequest httpRequest = new HTTPRequest(resourcePath, headerMap);
                    httpRequest.setPostMethod(isPostMethod);
                    if (isPostMethod) {
                        final String contentType = headerMap.get("Content-Type");
                        final String contentLengthString = headerMap.get("Content-Length");
                        final int contentLength = (contentLengthString == null) ? -1 : Integer.parseInt(contentLengthString);
                        HTTPData[] httpDataArray;
                        if (contentType != null && contentType.startsWith("multipart/")) {
                            byte[] dataBytes;
                            if (contentLength > 0) {
                                dataBytes = new byte[contentLength];
                                in.read(dataBytes);
                            }
                            else {
                                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                                final byte[] bytes = new byte[1024];
                                int i;
                                while ((i = in.read(bytes)) != -1) {
                                    baos.write(bytes, 0, i);
                                }
                                dataBytes = baos.toByteArray();
                            }
                            final String boundary = "--" + contentType.substring(contentType.indexOf("boundary=") + "boundary=".length());
                            final byte[] boundaryBytes = boundary.getBytes("UTF-8");
                            final List<Integer> indexList = new ArrayList<Integer>();
                            for (int j = 0; j < dataBytes.length - boundaryBytes.length; ++j) {
                                boolean isFound = true;
                                for (int k = 0; k < boundaryBytes.length; ++k) {
                                    if (dataBytes[j + k] != boundaryBytes[k]) {
                                        isFound = false;
                                        break;
                                    }
                                }
                                if (isFound) {
                                    indexList.add(j);
                                    j += boundaryBytes.length;
                                }
                            }
                            httpDataArray = new HTTPData[indexList.size() - 1];
                            for (int j = 0; j < httpDataArray.length; ++j) {
                                final HTTPData httpData = new HTTPData();
                                httpDataArray[j] = httpData;
                                final int start = indexList.get(j);
                                final ByteArrayInputStream bais = new ByteArrayInputStream(dataBytes, start, indexList.get(j + 1) - start - in.getLineSeparator().length());
                                final HTTPInputStream din = new HTTPInputStream(bais);
                                din.readAsciiLine();
                                final Map<String, String> dataHeaderMap = httpData.getHeaderMap();
                                String header2;
                                while ((header2 = din.readAsciiLine()).length() > 0) {
                                    final String key = header2.substring(header2.indexOf(": "));
                                    final String value = header2.substring(key.length() + ": ".length());
                                    dataHeaderMap.put(key, value);
                                }
                                final ByteArrayOutputStream aos = new ByteArrayOutputStream();
                                int n;
                                while ((n = din.read()) != -1) {
                                    aos.write(n);
                                }
                                httpData.setBytes(aos.toByteArray());
                            }
                        }
                        else {
                            final InputStreamReader reader = new InputStreamReader(in, "UTF-8");
                            String dataContent;
                            if (contentLength > 0) {
                                final char[] chars = new char[contentLength];
                                int n2;
                                for (int offset = 0; chars.length > offset; offset = ((n2 == -1) ? chars.length : (offset + n2))) {
                                    n2 = reader.read(chars, offset, chars.length - offset);
                                }
                                dataContent = new String(chars);
                            }
                            else {
                                final StringBuilder sb = new StringBuilder();
                                final char[] chars2 = new char[1024];
                                int j;
                                while ((j = reader.read(chars2)) != -1) {
                                    sb.append(chars2, 0, j);
                                }
                                dataContent = sb.toString();
                            }
                            final HTTPData httpData2 = new HTTPData();
                            final Map<String, String> dataHeaderMap2 = httpData2.getHeaderMap();
                            for (final String content : dataContent.split("&")) {
                                final int eqIndex = content.indexOf(61);
                                if (eqIndex > 0) {
                                    final String key2 = content.substring(0, eqIndex);
                                    final String value2 = Utils.decodeURL(content.substring(eqIndex + 1));
                                    dataHeaderMap2.put(key2, value2);
                                }
                                else {
                                    dataHeaderMap2.put(content, "");
                                }
                            }
                            httpDataArray = new HTTPData[] { httpData2 };
                        }
                        httpRequest.setHTTPPostDataArray(httpDataArray);
                    }
                    final WebServerContent webServerContent = WebServer.getWebServerContent(httpRequest);
                    InputStream resourceStream_ = null;
                    if (webServerContent != null) {
                        try {
                            resourceStream_ = webServerContent.getInputStream();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    final boolean isPrintRequestsDebug = Boolean.parseBoolean(NSSystemProperty.WEBSERVER_DEBUG_PRINTREQUESTS.get());
                    final String printDataProperty = NSSystemProperty.WEBSERVER_DEBUG_PRINTDATA.get();
                    boolean isPrintDataDebug = false;
                    long printDataCount = -1L;
                    if (printDataProperty != null) {
                        try {
                            printDataCount = Long.parseLong(printDataProperty);
                            isPrintDataDebug = true;
                        }
                        catch (Exception e3) {
                            isPrintDataDebug = Boolean.parseBoolean(printDataProperty);
                            printDataCount = 2147483647L;
                        }
                    }
                    if (resourceStream_ == null) {
                        if (isPrintRequestsDebug) {
                            System.err.println("Web Server " + (isPostMethod ? "POST" : "GET") + ": " + resourcePath + " -> 404 (not found)");
                        }
                        writeHTTPError(out, 404, "File Not Found.");
                        return;
                    }
                    if (isPrintRequestsDebug || isPrintDataDebug) {
                        System.err.println("Web Server " + (isPostMethod ? "POST" : "GET") + ": " + resourcePath + " -> 200 (OK)");
                    }
                    final BufferedInputStream resourceStream = new BufferedInputStream(resourceStream_);
                    writeHTTPHeaders(out, 200, webServerContent.getContentType(), webServerContent.getContentLength(), webServerContent.getLastModified());
                    final byte[] bytes2 = new byte[4096];
                    int l;
                    while ((l = resourceStream.read(bytes2)) != -1) {
                        if (isPrintDataDebug && l > 0 && printDataCount > 0L) {
                            System.err.print(new String(bytes2, 0, (int)Math.min(l, printDataCount), "UTF-8"));
                            printDataCount -= l;
                        }
                        out.write(bytes2, 0, l);
                    }
                    if (isPrintDataDebug) {
                        System.err.println();
                    }
                    try {
                        resourceStream.close();
                    }
                    catch (Exception e2) {
                        e2.printStackTrace();
                    }
                }
                finally {
                    out.flush();
                    out.close();
                    in.close();
                    this.socket.close();
                }
            }
            catch (Exception ex) {}
            finally {
                WebServerConnectionThread.semaphore.release();
            }
        }
        
        static {
            WebServerConnectionThread.semaphore = new Semaphore(10);
            LS = Utils.LINE_SEPARATOR;
        }
        
        private static class HTTPInputStream extends InputStream
        {
            private InputStream inputStream;
            private LineSeparator lineSeparator;
            private int lastByte;
            
            public HTTPInputStream(final InputStream inputStream) {
                this.lastByte = -1;
                this.inputStream = inputStream;
            }
            
            public String getLineSeparator() {
                switch (lIlll.$SwitchMap$chrriis$dj$nativeswing$common$WebServer$WebServerConnectionThread$HTTPInputStream$LineSeparator[this.lineSeparator.ordinal()]) {
                    case 1: {
                        return "\r";
                    }
                    case 2: {
                        return "\n";
                    }
                    case 3: {
                        return "\r\n";
                    }
                    default: {
                        return null;
                    }
                }
            }
            
            public String readAsciiLine() throws IOException {
                if (this.lineSeparator != null) {
                    final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    if (this.lastByte != -1) {
                        baos.write(this.lastByte);
                        this.lastByte = -1;
                    }
                    switch (lIlll.$SwitchMap$chrriis$dj$nativeswing$common$WebServer$WebServerConnectionThread$HTTPInputStream$LineSeparator[this.lineSeparator.ordinal()]) {
                        case 1: {
                            int b;
                            while ((b = this.read()) != 13 && b != -1) {
                                baos.write(b);
                            }
                            break;
                        }
                        case 2: {
                            int b;
                            while ((b = this.read()) != 10 && b != -1) {
                                baos.write(b);
                            }
                            break;
                        }
                        case 3: {
                            int b;
                            while ((b = this.read()) != 13 && b != -1) {
                                baos.write(b);
                            }
                            this.read();
                            break;
                        }
                    }
                    return new String(baos.toByteArray(), "UTF-8");
                }
                final ByteArrayOutputStream baos = new ByteArrayOutputStream();
                while (true) {
                    final int b = this.read();
                    if (b == -1) {
                        return null;
                    }
                    if (b == 10) {
                        this.lineSeparator = LineSeparator.LF;
                        return new String(baos.toByteArray(), "UTF-8");
                    }
                    if (b == 13) {
                        final int b2 = this.read();
                        if (b2 == 10) {
                            this.lineSeparator = LineSeparator.CRLF;
                        }
                        else {
                            this.lineSeparator = LineSeparator.CR;
                            if (b2 != -1) {
                                this.lastByte = b2;
                            }
                        }
                        return new String(baos.toByteArray(), "UTF-8");
                    }
                    baos.write(b);
                }
            }
            
            @Override
            public void close() throws IOException {
                this.inputStream.close();
            }
            
            @Override
            public int read(final byte[] b) throws IOException {
                return this.inputStream.read(b);
            }
            
            @Override
            public int read(final byte[] b, final int off, final int len) throws IOException {
                return this.inputStream.read(b, off, len);
            }
            
            @Override
            public int read() throws IOException {
                final int n = this.inputStream.read();
                return n;
            }
            
            enum LineSeparator
            {
                CR, 
                LF, 
                CRLF;
            }
        }
    }
    
    public interface WebServerContentProvider
    {
        WebServerContent getWebServerContent(final HTTPRequest p0);
    }
}
