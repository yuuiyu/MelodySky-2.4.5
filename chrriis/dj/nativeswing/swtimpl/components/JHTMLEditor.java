//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import java.io.*;
import java.net.*;
import java.util.regex.*;
import chrriis.dj.nativeswing.*;
import javax.swing.event.*;
import java.util.*;

public class JHTMLEditor extends NSPanelComponent
{
    private JWebBrowser webBrowser;
    private int instanceID;
    private JHTMLEditorImplementation implementation;
    private boolean isDirty;
    
    JHTMLEditorImplementation getImplementation() {
        return this.implementation;
    }
    
    public JHTMLEditor(final HTMLEditorImplementation editorImplementation, final NSOption... options) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   chrriis/dj/nativeswing/swtimpl/NSPanelComponent.<init>:()V
        //     4: aload_1         /* editorImplementation */
        //     5: ifnonnull       18
        //     8: new             Ljava/lang/NullPointerException;
        //    11: dup            
        //    12: ldc             "The editor implementation cannot be null!"
        //    14: invokespecial   java/lang/NullPointerException.<init>:(Ljava/lang/String;)V
        //    17: athrow         
        //    18: aload_2         /* options */
        //    19: invokestatic    chrriis/dj/nativeswing/NSOption.createOptionMap:([Lchrriis/dj/nativeswing/NSOption;)Ljava/util/Map;
        //    22: astore_3        /* optionMap */
        //    23: aload_0         /* this */
        //    24: new             Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    27: dup            
        //    28: aload_2         /* options */
        //    29: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.<init>:([Lchrriis/dj/nativeswing/NSOption;)V
        //    32: putfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    35: aload_0         /* this */
        //    36: aload_0         /* this */
        //    37: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    40: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.getNativeComponent:()Lchrriis/dj/nativeswing/swtimpl/NativeComponent;
        //    43: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.initialize:(Lchrriis/dj/nativeswing/swtimpl/NativeComponent;)V
        //    46: getstatic       chrriis/dj/nativeswing/swtimpl/components/llIIIll.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$JHTMLEditor$HTMLEditorImplementation:[I
        //    49: aload_1         /* editorImplementation */
        //    50: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor$HTMLEditorImplementation.ordinal:()I
        //    53: iaload         
        //    54: tableswitch {
        //                2: 80
        //                3: 105
        //                4: 130
        //          default: 155
        //        }
        //    80: aload_0         /* this */
        //    81: new             Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditorFCKeditor;
        //    84: dup            
        //    85: aload_0         /* this */
        //    86: aload_3         /* optionMap */
        //    87: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditorFCKeditor.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;Ljava/util/Map;)V
        //    90: putfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.implementation:Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor$JHTMLEditorImplementation;
        //    93: goto            165
        //    96: astore          e
        //    98: aload_1         /* editorImplementation */
        //    99: ifnull          105
        //   102: aload           e
        //   104: athrow         
        //   105: aload_0         /* this */
        //   106: new             Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditorCKeditor;
        //   109: dup            
        //   110: aload_0         /* this */
        //   111: aload_3         /* optionMap */
        //   112: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditorCKeditor.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;Ljava/util/Map;)V
        //   115: putfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.implementation:Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor$JHTMLEditorImplementation;
        //   118: goto            165
        //   121: astore          e
        //   123: aload_1         /* editorImplementation */
        //   124: ifnull          130
        //   127: aload           e
        //   129: athrow         
        //   130: aload_0         /* this */
        //   131: new             Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditorTinyMCE;
        //   134: dup            
        //   135: aload_0         /* this */
        //   136: aload_3         /* optionMap */
        //   137: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditorTinyMCE.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;Ljava/util/Map;)V
        //   140: putfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.implementation:Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor$JHTMLEditorImplementation;
        //   143: goto            165
        //   146: astore          e
        //   148: aload_1         /* editorImplementation */
        //   149: ifnull          155
        //   152: aload           e
        //   154: athrow         
        //   155: new             Ljava/lang/IllegalStateException;
        //   158: dup            
        //   159: ldc             "A suitable HTML editor (FCKeditor, CKeditor, TinyMCE) distribution could not be found on the classpath!"
        //   161: invokespecial   java/lang/IllegalStateException.<init>:(Ljava/lang/String;)V
        //   164: athrow         
        //   165: aload_0         /* this */
        //   166: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //   169: new             Lchrriis/dj/nativeswing/swtimpl/components/llIllll;
        //   172: dup            
        //   173: aload_0         /* this */
        //   174: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIllll.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;)V
        //   177: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.addWebBrowserListener:(Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserListener;)V
        //   180: aload_0         /* this */
        //   181: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //   184: iconst_0       
        //   185: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.setBarsVisible:(Z)V
        //   188: aload_0         /* this */
        //   189: aload_0         /* this */
        //   190: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //   193: ldc             "Center"
        //   195: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //   198: aload_0         /* this */
        //   199: invokestatic    chrriis/dj/nativeswing/common/ObjectRegistry.getInstance:()Lchrriis/dj/nativeswing/common/ObjectRegistry;
        //   202: aload_0         /* this */
        //   203: invokevirtual   chrriis/dj/nativeswing/common/ObjectRegistry.add:(Ljava/lang/Object;)I
        //   206: putfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.instanceID:I
        //   209: new             Ljava/util/concurrent/atomic/AtomicBoolean;
        //   212: dup            
        //   213: invokespecial   java/util/concurrent/atomic/AtomicBoolean.<init>:()V
        //   216: astore          result
        //   218: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIlIIIl;
        //   221: dup            
        //   222: aload_0         /* this */
        //   223: aload           result
        //   225: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIlIIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;Ljava/util/concurrent/atomic/AtomicBoolean;)V
        //   228: astore          initializationListener
        //   230: aload_0         /* this */
        //   231: aload           initializationListener
        //   233: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.addInitializationListener:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor$InitializationListener;)V
        //   236: aload_0         /* this */
        //   237: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //   240: invokestatic    chrriis/dj/nativeswing/common/WebServer.getDefaultWebServer:()Lchrriis/dj/nativeswing/common/WebServer;
        //   243: ldc             Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;.class
        //   245: invokevirtual   java/lang/Class.getName:()Ljava/lang/String;
        //   248: aload_0         /* this */
        //   249: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.instanceID:I
        //   252: invokestatic    java/lang/String.valueOf:(I)Ljava/lang/String;
        //   255: ldc             "index.html"
        //   257: invokevirtual   chrriis/dj/nativeswing/common/WebServer.getDynamicContentURL:(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)Ljava/lang/String;
        //   260: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.navigate:(Ljava/lang/String;)Z
        //   263: pop            
        //   264: aload_0         /* this */
        //   265: getfield        chrriis/dj/nativeswing/swtimpl/components/JHTMLEditor.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //   268: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.getNativeComponent:()Lchrriis/dj/nativeswing/swtimpl/NativeComponent;
        //   271: new             Lchrriis/dj/nativeswing/swtimpl/components/lIIIlIIl;
        //   274: dup            
        //   275: aload_0         /* this */
        //   276: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIIIlIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JHTMLEditor;)V
        //   279: iconst_2       
        //   280: anewarray       Ljava/lang/Object;
        //   283: dup            
        //   284: iconst_0       
        //   285: aload           initializationListener
        //   287: aastore        
        //   288: dup            
        //   289: iconst_1       
        //   290: aload           result
        //   292: aastore        
        //   293: invokevirtual   chrriis/dj/nativeswing/swtimpl/NativeComponent.runSync:(Lchrriis/dj/nativeswing/swtimpl/CommandMessage;[Ljava/lang/Object;)Ljava/lang/Object;
        //   296: pop            
        //   297: return         
        //    StackMapTable: 00 09 FF 00 12 00 03 07 00 02 07 00 15 07 00 45 00 00 FC 00 3D 07 00 64 4F 07 00 39 08 4F 07 00 39 08 4F 07 00 39 08 09
        //    Exceptions:
        //  Try           Handler
        //  Start  End    Start  End    Type                        
        //  -----  -----  -----  -----  ----------------------------
        //  80     93     96     105    Ljava/lang/RuntimeException;
        //  105    118    121    130    Ljava/lang/RuntimeException;
        //  130    143    146    155    Ljava/lang/RuntimeException;
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
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.createConstructor(AstBuilder.java:713)
        //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:549)
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
    
    public JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
    
    protected static WebServer.WebServerContent getWebServerContent(final WebServer.HTTPRequest httpRequest) {
        final String resourcePath = httpRequest.getResourcePath();
        final int index = resourcePath.indexOf(47);
        final int instanceID = Integer.parseInt(resourcePath.substring(0, index));
        final JHTMLEditor htmlEditor = (JHTMLEditor)ObjectRegistry.getInstance().get(instanceID);
        if (htmlEditor == null) {
            return null;
        }
        String resourcePath_ = resourcePath.substring(index + 1);
        if (resourcePath_.startsWith("/")) {
            resourcePath_ = resourcePath_.substring(1);
        }
        return htmlEditor.getWebServerContent(httpRequest, resourcePath_, instanceID);
    }
    
    protected WebServer.WebServerContent getWebServerContent(final WebServer.HTTPRequest httpRequest, final String resourcePath, final int instanceID) {
        return this.implementation.getWebServerContent(httpRequest, resourcePath, instanceID);
    }
    
    public String getHTMLContent() {
        return convertLinksToLocal(this.implementation.getHTMLContent());
    }
    
    public void setHTMLContent(String html) {
        html = convertLinksFromLocal(html.replaceAll("[\r\n]", " "));
        this.implementation.setHTMLContent(html);
        this.setDirty(false);
    }
    
    public boolean isDirty() {
        return this.isDirty;
    }
    
    private void setDirty(final boolean isDirty) {
        if (this.isDirty == isDirty) {
            return;
        }
        this.isDirty = isDirty;
        final Object[] listeners = this.listenerList.getListenerList();
        HTMLEditorDirtyStateEvent ev = null;
        for (int i = listeners.length - 2; i >= 0; i -= 2) {
            if (listeners[i] == HTMLEditorListener.class) {
                if (ev == null) {
                    ev = new HTMLEditorDirtyStateEvent(this, isDirty);
                }
                ((HTMLEditorListener)listeners[i + 1]).notifyDirtyStateChanged(ev);
            }
        }
    }
    
    public void clearDirtyState() {
        this.implementation.clearDirtyIndicator();
        this.setDirty(false);
    }
    
    static String convertLinksToLocal(String html) {
        if (html == null) {
            return html;
        }
        Matcher m;
        String resource;
        for (Pattern p = Pattern.compile("=\\s*\"(" + WebServer.getDefaultWebServer().getURLPrefix() + "/resource/)([^/]+)/([^\"]+)\"\\s"); (m = p.matcher(html)).find(); html = html.substring(0, m.start(1)) + resource + html.substring(m.end(3))) {
            final String codeBase = html.substring(m.start(2), m.end(2));
            resource = html.substring(m.start(3), m.end(3));
            try {
                resource = new File(Utils.decodeURL(Utils.decodeURL(codeBase)), resource).toURI().toURL().toExternalForm();
            }
            catch (MalformedURLException ex) {}
        }
        for (Pattern p = Pattern.compile("=\\s*\"(" + WebServer.getDefaultWebServer().getURLPrefix() + "/location/)([^/]+)/([^\"]+)\"\\s"); (m = p.matcher(html)).find(); html = html.substring(0, m.start(1)) + resource + html.substring(m.end(3))) {
            final String codeBase = html.substring(m.start(2), m.end(2));
            resource = html.substring(m.start(3), m.end(3));
            try {
                resource = new File(Utils.decodeBase64(codeBase), Utils.decodeURL(resource)).toURI().toURL().toExternalForm();
            }
            catch (MalformedURLException ex2) {}
        }
        return html;
    }
    
    static String convertLinksFromLocal(String html) {
        if (html == null) {
            return html;
        }
        Matcher m;
        String resource;
        for (Pattern p = Pattern.compile("=\\s*\"(file:/{1,3})([^\"]+)\"\\s"); (m = p.matcher(html)).find(); html = html.substring(0, m.start(1)) + resource + html.substring(m.end(2))) {
            resource = html.substring(m.start(2), m.end(2));
            if (Boolean.parseBoolean(NSSystemProperty.WEBSERVER_ACTIVATEOLDRESOURCEMETHOD.get())) {
                final File resourceFile = new File(resource);
                resource = WebServer.getDefaultWebServer().getResourcePathURL(Utils.encodeURL(resourceFile.getParent()), resourceFile.getName());
            }
            else {
                final File resourceFile = new File(Utils.decodeURL(resource));
                resource = WebServer.getDefaultWebServer().getResourcePathURL(resourceFile.getParent(), resourceFile.getName());
            }
        }
        return html;
    }
    
    public void addHTMLEditorListener(final HTMLEditorListener listener) {
        this.listenerList.add(HTMLEditorListener.class, listener);
    }
    
    public void removeHTMLEditorListener(final HTMLEditorListener listener) {
        this.listenerList.remove(HTMLEditorListener.class, listener);
    }
    
    public HTMLEditorListener[] getHTMLEditorListeners() {
        return this.listenerList.getListeners(HTMLEditorListener.class);
    }
    
    private void addInitializationListener(final InitializationListener listener) {
        this.listenerList.add(InitializationListener.class, listener);
    }
    
    private void removeInitializationListener(final InitializationListener listener) {
        this.listenerList.remove(InitializationListener.class, listener);
    }
    
    public enum HTMLEditorImplementation
    {
        FCKEditor, 
        CKEditor, 
        TinyMCE;
    }
    
    public static class TinyMCEOptions
    {
        static final String SET_CUSTOM_HTML_HEADERS_OPTION_KEY = "TinyMCE Custom HTML Headers";
        static final String SET_OPTIONS_OPTION_KEY = "TinyMCE Options";
        
        private TinyMCEOptions() {
        }
        
        public static NSOption setCustomHTMLHeaders(final String customHTMLHeaders) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: ldc             "TinyMCE Custom HTML Headers"
            //     6: aload_0         /* customHTMLHeaders */
            //     7: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIlIIllI.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
            //    10: areturn        
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
        
        public static NSOption setOptions(final Map<String, String> optionMap) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_0         /* optionMap */
            //     5: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
            //     8: astore_1        /* optionMap_ */
            //     9: new             Lchrriis/dj/nativeswing/swtimpl/components/lllIllI;
            //    12: dup            
            //    13: ldc             "TinyMCE Options"
            //    15: aload_1         /* optionMap_ */
            //    16: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllIllI.<init>:(Ljava/lang/Object;Ljava/util/Map;)V
            //    19: areturn        
            //    Signature:
            //  (Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Lchrriis/dj/nativeswing/NSOption;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    }
    
    public static class FCKEditorOptions
    {
        static final String SET_CUSTOM_JAVASCRIPT_CONFIGURATION_OPTION_KEY = "FCKEditor Custom Configuration Script";
        
        private FCKEditorOptions() {
        }
        
        public static NSOption setCustomJavascriptConfiguration(final String javascriptConfiguration) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: ldc             "FCKEditor Custom Configuration Script"
            //     6: aload_0         /* javascriptConfiguration */
            //     7: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIllIlI.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
            //    10: areturn        
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    }
    
    public static class CKEditorOptions
    {
        static final String SET_OPTIONS_OPTION_KEY = "CKEditor Options";
        
        private CKEditorOptions() {
        }
        
        public static NSOption setOptions(final Map<String, String> optionMap) {
            // 
            // This method could not be decompiled.
            // 
            // Original Bytecode:
            // 
            //     3: dup            
            //     4: aload_0         /* optionMap */
            //     5: invokespecial   java/util/HashMap.<init>:(Ljava/util/Map;)V
            //     8: astore_1        /* optionMap_ */
            //     9: new             Lchrriis/dj/nativeswing/swtimpl/components/lIllIll;
            //    12: dup            
            //    13: ldc             "CKEditor Options"
            //    15: aload_1         /* optionMap_ */
            //    16: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lIllIll.<init>:(Ljava/lang/Object;Ljava/util/Map;)V
            //    19: areturn        
            //    Signature:
            //  (Ljava/util/Map<Ljava/lang/String;Ljava/lang/String;>;)Lchrriis/dj/nativeswing/NSOption;
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
            //     at com.strobel.decompiler.languages.java.ast.AstBuilder.addTypeMembers(AstBuilder.java:576)
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
    }
    
    private interface InitializationListener extends EventListener
    {
        void objectInitialized();
    }
    
    interface JHTMLEditorImplementation
    {
        WebServer.WebServerContent getWebServerContent(final WebServer.HTTPRequest p0, final String p1, final int p2);
        
        String getHTMLContent();
        
        void setHTMLContent(final String p0);
        
        void setDirtyTrackingActive(final boolean p0);
        
        void clearDirtyIndicator();
    }
}
