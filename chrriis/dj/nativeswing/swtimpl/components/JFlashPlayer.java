//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import java.awt.*;
import chrriis.dj.nativeswing.common.*;
import java.util.*;
import java.io.*;
import chrriis.dj.nativeswing.*;

public class JFlashPlayer extends NSPanelComponent
{
    private static final String SET_CUSTOM_JAVASCRIPT_DEFINITIONS_OPTION_KEY = "Flash Player Custom Javascript definitions";
    private static FlashPlayerDecoratorFactory flashPlayerDecoratorFactory;
    private FlashPlayerDecorator flashPlayerDecorator;
    private JWebBrowser webBrowser;
    private WebBrowserObject webBrowserObject;
    private volatile String customJavascriptDefinitions;
    private volatile FlashPluginOptions options;
    private List<ClassLoader> referenceClassLoaderList;
    
    public static NSOption setCustomJavascriptDefinitions(final String javascript) {
        final class llIllIl extends NSOption
        {
            final /* synthetic */ String val$javascript;
            
            llIllIl(final Object key, final String val$javascript) {
                this.val$javascript = val$javascript;
                super(key);
            }
            
            public Object getOptionValue() {
                return this.val$javascript;
            }
        }
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     3: dup            
        //     4: ldc             "Flash Player Custom Javascript definitions"
        //     6: aload_0         /* javascript */
        //     7: invokespecial   chrriis/dj/nativeswing/swtimpl/components/llIllIl.<init>:(Ljava/lang/Object;Ljava/lang/String;)V
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
    
    public static void setFlashPlayerDecoratorFactory(final FlashPlayerDecoratorFactory flashPlayerDecoratorFactory) {
        JFlashPlayer.flashPlayerDecoratorFactory = flashPlayerDecoratorFactory;
    }
    
    FlashPlayerDecorator getFlashPlayerDecorator() {
        return this.flashPlayerDecorator;
    }
    
    protected FlashPlayerDecorator createFlashPlayerDecorator(final Component renderingComponent) {
        if (JFlashPlayer.flashPlayerDecoratorFactory != null) {
            final FlashPlayerDecorator flashPlayerDecorator = JFlashPlayer.flashPlayerDecoratorFactory.createFlashPlayerDecorator(this, renderingComponent);
            if (flashPlayerDecorator != null) {
                return flashPlayerDecorator;
            }
        }
        return (FlashPlayerDecorator)new DefaultFlashPlayerDecorator(this, renderingComponent);
    }
    
    public JFlashPlayer(final NSOption... options) {
        // 
        // This method could not be decompiled.
        // 
        // Original Bytecode:
        // 
        //     1: invokespecial   chrriis/dj/nativeswing/swtimpl/NSPanelComponent.<init>:()V
        //     4: aload_0         /* this */
        //     5: new             Ljava/util/ArrayList;
        //     8: dup            
        //     9: iconst_1       
        //    10: invokespecial   java/util/ArrayList.<init>:(I)V
        //    13: putfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.referenceClassLoaderList:Ljava/util/List;
        //    16: aload_1         /* options */
        //    17: invokestatic    chrriis/dj/nativeswing/NSOption.createOptionMap:([Lchrriis/dj/nativeswing/NSOption;)Ljava/util/Map;
        //    20: astore_2        /* optionMap */
        //    21: aload_0         /* this */
        //    22: aload_2         /* optionMap */
        //    23: ldc             "Flash Player Custom Javascript definitions"
        //    25: invokeinterface java/util/Map.get:(Ljava/lang/Object;)Ljava/lang/Object;
        //    30: checkcast       Ljava/lang/String;
        //    33: putfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.customJavascriptDefinitions:Ljava/lang/String;
        //    36: aload_0         /* this */
        //    37: new             Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    40: dup            
        //    41: aload_1         /* options */
        //    42: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.<init>:([Lchrriis/dj/nativeswing/NSOption;)V
        //    45: putfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    48: aload_0         /* this */
        //    49: aload_0         /* this */
        //    50: getfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    53: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.getNativeComponent:()Lchrriis/dj/nativeswing/swtimpl/NativeComponent;
        //    56: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.initialize:(Lchrriis/dj/nativeswing/swtimpl/NativeComponent;)V
        //    59: aload_0         /* this */
        //    60: new             Lchrriis/dj/nativeswing/swtimpl/components/JFlashPlayer$NWebBrowserObject;
        //    63: dup            
        //    64: aload_0         /* this */
        //    65: invokespecial   chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer$NWebBrowserObject.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JFlashPlayer;)V
        //    68: putfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.webBrowserObject:Lchrriis/dj/nativeswing/swtimpl/WebBrowserObject;
        //    71: aload_0         /* this */
        //    72: getfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    75: new             Lchrriis/dj/nativeswing/swtimpl/components/lllIIIl;
        //    78: dup            
        //    79: aload_0         /* this */
        //    80: invokespecial   chrriis/dj/nativeswing/swtimpl/components/lllIIIl.<init>:(Lchrriis/dj/nativeswing/swtimpl/components/JFlashPlayer;)V
        //    83: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JWebBrowser.addWebBrowserListener:(Lchrriis/dj/nativeswing/swtimpl/components/WebBrowserListener;)V
        //    86: aload_0         /* this */
        //    87: aload_0         /* this */
        //    88: aload_0         /* this */
        //    89: getfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.webBrowser:Lchrriis/dj/nativeswing/swtimpl/components/JWebBrowser;
        //    92: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.createFlashPlayerDecorator:(Ljava/awt/Component;)Lchrriis/dj/nativeswing/swtimpl/components/FlashPlayerDecorator;
        //    95: putfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.flashPlayerDecorator:Lchrriis/dj/nativeswing/swtimpl/components/FlashPlayerDecorator;
        //    98: aload_0         /* this */
        //    99: aload_0         /* this */
        //   100: getfield        chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.flashPlayerDecorator:Lchrriis/dj/nativeswing/swtimpl/components/FlashPlayerDecorator;
        //   103: ldc             "Center"
        //   105: invokevirtual   chrriis/dj/nativeswing/swtimpl/components/JFlashPlayer.add:(Ljava/awt/Component;Ljava/lang/Object;)V
        //   108: return         
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
    
    public void load(final Class<?> clazz, final String resourcePath) {
        this.load(clazz, resourcePath, null);
    }
    
    public void load(final Class<?> clazz, final String resourcePath, final FlashPluginOptions options) {
        this.addReferenceClassLoader(clazz.getClassLoader());
        this.load(WebServer.getDefaultWebServer().getClassPathResourceURL(clazz.getName(), resourcePath), options);
    }
    
    public void load(final String resourceLocation) {
        this.load(resourceLocation, null);
    }
    
    public void load(String resourceLocation, FlashPluginOptions options) {
        if ("".equals(resourceLocation)) {
            resourceLocation = null;
        }
        if (options == null) {
            options = new FlashPluginOptions();
        }
        this.options = options;
        this.webBrowserObject.load(resourceLocation);
    }
    
    public void play() {
        if (!this.webBrowserObject.hasContent()) {
            return;
        }
        this.webBrowserObject.invokeObjectFunction("Play", new Object[0]);
    }
    
    public void pause() {
        if (!this.webBrowserObject.hasContent()) {
            return;
        }
        this.webBrowserObject.invokeObjectFunction("StopPlay", new Object[0]);
    }
    
    public void stop() {
        if (!this.webBrowserObject.hasContent()) {
            return;
        }
        this.webBrowserObject.invokeObjectFunction("Rewind", new Object[0]);
    }
    
    public void setVariable(final String name, final String value) {
        if (!this.webBrowserObject.hasContent()) {
            return;
        }
        this.webBrowserObject.invokeObjectFunction("SetVariable", name, value);
    }
    
    public Object getVariable(final String name) {
        if (!this.webBrowserObject.hasContent()) {
            return null;
        }
        return this.webBrowserObject.invokeObjectFunctionWithResult("GetVariable", name);
    }
    
    public void invokeFlashFunction(final String functionName, final Object... args) {
        this.webBrowserObject.invokeObjectFunction(functionName, args);
    }
    
    public Object invokeFlashFunctionWithResult(final String functionName, final Object... args) {
        return this.webBrowserObject.invokeObjectFunctionWithResult(functionName, args);
    }
    
    public JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
    
    public boolean isControlBarVisible() {
        return this.flashPlayerDecorator.isControlBarVisible();
    }
    
    public void setControlBarVisible(final boolean isControlBarVisible) {
        this.flashPlayerDecorator.setControlBarVisible(isControlBarVisible);
    }
    
    public void addFlashPlayerListener(final FlashPlayerListener listener) {
        this.listenerList.add(FlashPlayerListener.class, listener);
    }
    
    public void removeFlashPlayerListener(final FlashPlayerListener listener) {
        this.listenerList.remove(FlashPlayerListener.class, listener);
    }
    
    public FlashPlayerListener[] getFlashPlayerListeners() {
        return this.listenerList.getListeners(FlashPlayerListener.class);
    }
    
    private void addReferenceClassLoader(final ClassLoader referenceClassLoader) {
        if (referenceClassLoader == null || referenceClassLoader == this.getClass().getClassLoader() || this.referenceClassLoaderList.contains(referenceClassLoader)) {
            return;
        }
        this.referenceClassLoaderList.add(referenceClassLoader);
        WebServer.getDefaultWebServer().addReferenceClassLoader(referenceClassLoader);
    }
    
    @Override
    protected void finalize() throws Throwable {
        for (final ClassLoader referenceClassLoader : this.referenceClassLoaderList) {
            WebServer.getDefaultWebServer().removeReferenceClassLoader(referenceClassLoader);
        }
        this.referenceClassLoaderList.clear();
        super.finalize();
    }
    
    @Override
    public void removeNotify() {
        super.removeNotify();
        this.cleanup();
    }
    
    @Override
    public void disposeNativePeer() {
        super.disposeNativePeer();
        this.cleanup();
    }
    
    private void cleanup() {
        if (this.isNativePeerDisposed()) {
            this.webBrowserObject.load(null);
        }
    }
    
    static {
        WebServer.getDefaultWebServer().addContentProvider((WebServer.WebServerContentProvider)new lIIIIIIl());
    }
    
    private static class NWebBrowserObject extends WebBrowserObject
    {
        private final JFlashPlayer flashPlayer;
        private final String LS;
        
        NWebBrowserObject(final JFlashPlayer flashPlayer) {
            super(flashPlayer.webBrowser);
            this.LS = Utils.LINE_SEPARATOR;
            this.flashPlayer = flashPlayer;
        }
        
        @Override
        protected ObjectHTMLConfiguration getObjectHtmlConfiguration() {
            final ObjectHTMLConfiguration objectHTMLConfiguration = new ObjectHTMLConfiguration();
            if (this.flashPlayer.options != null) {
                final Map<String, String> htmlParameters = (Map<String, String>)this.flashPlayer.options.getHTMLParameters();
                if (!htmlParameters.containsKey("base")) {
                    final String loadedResource = this.flashPlayer.webBrowserObject.getLoadedResource();
                    if (loadedResource != null) {
                        final int lastIndex = loadedResource.lastIndexOf(47);
                        htmlParameters.put("base", loadedResource.substring(0, lastIndex + 1));
                    }
                }
                objectHTMLConfiguration.setHTMLParameters(htmlParameters);
            }
            objectHTMLConfiguration.setWindowsClassID("D27CDB6E-AE6D-11cf-96B8-444553540000");
            objectHTMLConfiguration.setWindowsInstallationURL("http://fpdownload.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=9,0,0,0");
            objectHTMLConfiguration.setMimeType("application/x-shockwave-flash");
            objectHTMLConfiguration.setInstallationURL("http://www.adobe.com/go/getflashplayer");
            objectHTMLConfiguration.setWindowsParamName("movie");
            objectHTMLConfiguration.setParamName("src");
            return objectHTMLConfiguration;
        }
        
        @Override
        protected String getJavascriptDefinitions() {
            final String javascriptDefinitions = this.flashPlayer.customJavascriptDefinitions;
            return "      function " + WebBrowserObject.getEmbeddedObjectJavascriptName() + "_DoFSCommand(command, args) {" + this.LS + "        sendCommand(command, args);" + this.LS + "      }" + ((javascriptDefinitions == null) ? "" : (this.LS + javascriptDefinitions));
        }
        
        @Override
        protected String getAdditionalHeadDefinitions() {
            return "    <script language=\"VBScript\">" + this.LS + "    <!-- " + this.LS + "    Sub " + WebBrowserObject.getEmbeddedObjectJavascriptName() + "_FSCommand(ByVal command, ByVal args)" + this.LS + "      call " + WebBrowserObject.getEmbeddedObjectJavascriptName() + "_DoFSCommand(command, args)" + this.LS + "    end sub" + this.LS + "    //-->" + this.LS + "    </script>";
        }
        
        @Override
        public String getLocalFileURL(final File localFile) {
            if (Boolean.parseBoolean(NSSystemProperty.WEBSERVER_ACTIVATEOLDRESOURCEMETHOD.get())) {
                return WebServer.getDefaultWebServer().getResourcePathURL(this.encodeSpecialCharacters(localFile.getParent()), this.encodeSpecialCharacters(localFile.getName()));
            }
            return WebServer.getDefaultWebServer().getResourcePathURL(localFile.getParent(), localFile.getName());
        }
        
        private String encodeSpecialCharacters(final String s) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); ++i) {
                char c = s.charAt(i);
                boolean isToEncode = false;
                if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z') && (c < '0' || c > '9')) {
                    switch (c) {
                        case '%':
                        case '*':
                        case '+':
                        case '-':
                        case '.':
                        case '/':
                        case ':':
                        case '_': {
                            break;
                        }
                        case '\\': {
                            if (Utils.IS_WINDOWS) {
                                c = '/';
                                break;
                            }
                            break;
                        }
                        default: {
                            isToEncode = true;
                            break;
                        }
                    }
                }
                if (isToEncode) {
                    sb.append(Utils.encodeURL(String.valueOf(c)));
                }
                else {
                    sb.append(c);
                }
            }
            return sb.toString();
        }
    }
    
    public interface FlashPlayerDecoratorFactory
    {
        FlashPlayerDecorator createFlashPlayerDecorator(final JFlashPlayer p0, final Component p1);
    }
}
