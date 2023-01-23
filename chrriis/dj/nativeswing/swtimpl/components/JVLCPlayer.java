//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;
import java.awt.*;
import chrriis.dj.nativeswing.*;
import java.util.*;
import java.io.*;
import chrriis.dj.nativeswing.common.*;

public class JVLCPlayer extends NSPanelComponent
{
    private static VLCPlayerDecoratorFactory vlcPlayerDecoratorFactory;
    private VLCPlayerDecorator vlcPlayerDecorator;
    private JWebBrowser webBrowser;
    private WebBrowserObject webBrowserObject;
    private volatile VLCPluginOptions options;
    private VLCAudio vlcAudio;
    private VLCInput vlcInput;
    private VLCPlaylist vlcPlaylist;
    private VLCVideo vlcVideo;
    private List<ClassLoader> referenceClassLoaderList;
    
    public static void setVLCPlayerDecoratorFactory(final VLCPlayerDecoratorFactory vlcPlayerDecoratorFactory) {
        JVLCPlayer.vlcPlayerDecoratorFactory = vlcPlayerDecoratorFactory;
    }
    
    VLCPlayerDecorator getVLCPlayerDecorator() {
        return this.vlcPlayerDecorator;
    }
    
    protected VLCPlayerDecorator createVLCPlayerDecorator(final Component renderingComponent) {
        if (JVLCPlayer.vlcPlayerDecoratorFactory != null) {
            final VLCPlayerDecorator vlcPlayerDecorator = JVLCPlayer.vlcPlayerDecoratorFactory.createVLCPlayerDecorator(this, renderingComponent);
            if (vlcPlayerDecorator != null) {
                return vlcPlayerDecorator;
            }
        }
        return (VLCPlayerDecorator)new DefaultVLCPlayerDecorator(this, renderingComponent);
    }
    
    WebBrowserObject getWebBrowserObject() {
        return this.webBrowserObject;
    }
    
    @Override
    public void removeNotify() {
        super.removeNotify();
        this.cleanup();
    }
    
    public JVLCPlayer(final NSOption... options) {
        this.referenceClassLoaderList = new ArrayList<ClassLoader>(1);
        this.webBrowser = new JWebBrowser(options);
        this.initialize(this.webBrowser.getNativeComponent());
        this.webBrowserObject = new NWebBrowserObject(this);
        this.vlcAudio = new VLCAudio(this);
        this.vlcInput = new VLCInput(this);
        this.vlcPlaylist = new VLCPlaylist(this);
        this.vlcVideo = new VLCVideo(this);
        this.add(this.vlcPlayerDecorator = this.createVLCPlayerDecorator(this.webBrowser), "Center");
    }
    
    public JWebBrowser getWebBrowser() {
        return this.webBrowser;
    }
    
    public void load() {
        this.load((VLCPluginOptions)null);
    }
    
    public void load(final String resourceLocation) {
        this.load(resourceLocation, null);
    }
    
    public void load(final VLCPluginOptions options) {
        this.load_("", options);
    }
    
    public void load(final Class<?> clazz, final String resourcePath) {
        this.load(clazz, resourcePath, null);
    }
    
    public void load(final Class<?> clazz, final String resourcePath, final VLCPluginOptions options) {
        this.addReferenceClassLoader(clazz.getClassLoader());
        this.load(WebServer.getDefaultWebServer().getClassPathResourceURL(clazz.getName(), resourcePath), options);
    }
    
    public void load(String resourceLocation, final VLCPluginOptions options) {
        if ("".equals(resourceLocation)) {
            resourceLocation = null;
        }
        this.load_(resourceLocation, options);
    }
    
    private void load_(final String resourceLocation, VLCPluginOptions options) {
        if (options == null) {
            options = new VLCPluginOptions();
        }
        this.options = options;
        this.webBrowserObject.load(resourceLocation);
        final VLCPlaylist playlist = this.getVLCPlaylist();
        if (resourceLocation != null && !"".equals(resourceLocation)) {
            playlist.stop();
            playlist.clear();
            playlist.addItem(resourceLocation);
            playlist.play();
        }
    }
    
    public boolean isControlBarVisible() {
        return this.vlcPlayerDecorator.isControlBarVisible();
    }
    
    public void setControlBarVisible(final boolean isControlBarVisible) {
        this.vlcPlayerDecorator.setControlBarVisible(isControlBarVisible);
    }
    
    public VLCAudio getVLCAudio() {
        return this.vlcAudio;
    }
    
    public VLCInput getVLCInput() {
        return this.vlcInput;
    }
    
    public VLCPlaylist getVLCPlaylist() {
        return this.vlcPlaylist;
    }
    
    public VLCVideo getVLCVideo() {
        return this.vlcVideo;
    }
    
    void addReferenceClassLoader(final ClassLoader referenceClassLoader) {
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
    public void disposeNativePeer() {
        super.disposeNativePeer();
        this.cleanup();
    }
    
    private void cleanup() {
        if (this.isNativePeerDisposed()) {
            this.webBrowserObject.load(null);
        }
    }
    
    private static class NWebBrowserObject extends WebBrowserObject
    {
        private final JVLCPlayer vlcPlayer;
        
        public NWebBrowserObject(final JVLCPlayer vlcPlayer) {
            super(vlcPlayer.webBrowser);
            this.vlcPlayer = vlcPlayer;
        }
        
        @Override
        protected ObjectHTMLConfiguration getObjectHtmlConfiguration() {
            final ObjectHTMLConfiguration objectHTMLConfiguration = new ObjectHTMLConfiguration();
            if (this.vlcPlayer.options != null) {
                objectHTMLConfiguration.setHTMLParameters(this.vlcPlayer.options.getParameters());
            }
            objectHTMLConfiguration.setWindowsClassID("9BE31822-FDAD-461B-AD51-BE1D1C159921");
            objectHTMLConfiguration.setWindowsInstallationURL("http://downloads.videolan.org/pub/videolan/vlc/latest/win32/axvlc.cab");
            objectHTMLConfiguration.setMimeType("application/x-vlc-plugin");
            objectHTMLConfiguration.setInstallationURL("http://www.videolan.org");
            objectHTMLConfiguration.setVersion("VideoLAN.VLCPlugin.2");
            return objectHTMLConfiguration;
        }
        
        @Override
        public String getLocalFileURL(final File localFile) {
            final String absolutePath = localFile.getAbsolutePath();
            if (absolutePath.startsWith("\\\\")) {
                return absolutePath;
            }
            String s;
            try {
                s = "file://" + localFile.toURI().toURL().toString().substring("file:".length());
            }
            catch (Exception e) {
                s = "file:///" + absolutePath;
                if (Utils.IS_WINDOWS) {
                    s = s.replace('\\', '/');
                }
            }
            return this.encodeSpecialCharacters(s);
        }
        
        private String encodeSpecialCharacters(final String s) {
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < s.length(); ++i) {
                final char c = s.charAt(i);
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
    
    public interface VLCPlayerDecoratorFactory
    {
        VLCPlayerDecorator createVLCPlayerDecorator(final JVLCPlayer p0, final Component p1);
    }
}
