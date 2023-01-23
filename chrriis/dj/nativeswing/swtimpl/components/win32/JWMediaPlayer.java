//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

import chrriis.dj.nativeswing.swtimpl.components.win32.internal.*;
import chrriis.dj.nativeswing.*;
import chrriis.dj.nativeswing.swtimpl.internal.*;
import chrriis.dj.nativeswing.swtimpl.*;
import chrriis.dj.nativeswing.common.*;
import java.util.*;

public class JWMediaPlayer extends NSPanelComponent
{
    private INativeWMediaPlayer nativeComponent;
    private WMPSettings wmpSettings;
    private WMPControls wmpControls;
    private WMPMedia wmpMedia;
    private List<ClassLoader> referenceClassLoaderList;
    
    public JWMediaPlayer(final NSOption... options) {
        this.referenceClassLoaderList = new ArrayList<ClassLoader>(1);
        this.nativeComponent = NativeCoreObjectFactory.create(INativeWMediaPlayer.class, "chrriis.dj.nativeswing.swtimpl.components.win32.core.NativeWMediaPlayer", new Class[0], new Object[0]);
        this.initialize((NativeComponent)this.nativeComponent);
        this.wmpSettings = new WMPSettings(this);
        this.wmpControls = new WMPControls(this);
        this.wmpMedia = new WMPMedia(this);
        this.add(this.nativeComponent.createEmbeddableComponent(NSOption.createOptionMap(options)), "Center");
        this.wmpSettings.setAutoStart(true);
        this.wmpSettings.setErrorDialogsEnabled(false);
        this.setControlBarVisible(true);
    }
    
    public WMPSettings getWMPSettings() {
        return this.wmpSettings;
    }
    
    public WMPControls getWMPControls() {
        return this.wmpControls;
    }
    
    public WMPMedia getWMPMedia() {
        return this.wmpMedia;
    }
    
    public void load(final String resourcePath) {
        this.nativeComponent.setOleProperty("url", new Object[] { (resourcePath == null) ? "" : resourcePath });
    }
    
    public void load(final Class<?> clazz, final String resourcePath) {
        this.addReferenceClassLoader(clazz.getClassLoader());
        this.load(WebServer.getDefaultWebServer().getClassPathResourceURL(clazz.getName(), resourcePath));
    }
    
    public void setControlBarVisible(final boolean isControlBarVisible) {
        this.nativeComponent.setOleProperty("uiMode", new Object[] { isControlBarVisible ? "full" : "none" });
    }
    
    public boolean isControlBarVisible() {
        return Boolean.TRUE.equals("full".equals(this.nativeComponent.getOleProperty("uiMode", new Object[0])));
    }
    
    public void setFullScreen(final boolean isFullScreen) {
        this.nativeComponent.setOleProperty("fullScreen", new Object[] { isFullScreen });
    }
    
    public boolean isFullScreen() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty("fullScreen", new Object[0]));
    }
    
    public void setStretchToFit(final boolean isStretchToFit) {
        this.nativeComponent.setOleProperty("stretchToFit", new Object[] { isStretchToFit });
    }
    
    public boolean isStretchToFit() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty("stretchToFit", new Object[0]));
    }
    
    public WMPMediaState getMediaState() {
        try {
            switch ((int)this.nativeComponent.getOleProperty("playState", new Object[0])) {
                case 1: {
                    return WMPMediaState.STOPPED;
                }
                case 2: {
                    return WMPMediaState.PAUSED;
                }
                case 3: {
                    return WMPMediaState.PLAYING;
                }
                case 4: {
                    return WMPMediaState.SCAN_FORWARD;
                }
                case 5: {
                    return WMPMediaState.SCAN_REVERSE;
                }
                case 6: {
                    return WMPMediaState.BUFFERING;
                }
                case 7: {
                    return WMPMediaState.WAITING;
                }
                case 8: {
                    return WMPMediaState.MEDIA_ENDED;
                }
                case 9: {
                    return WMPMediaState.TRANSITIONING;
                }
                case 10: {
                    return WMPMediaState.READY;
                }
                case 11: {
                    return WMPMediaState.RECONNECTING;
                }
            }
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception ex) {}
        return WMPMediaState.UNDEFINED;
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
    
    public enum WMPMediaState
    {
        UNDEFINED, 
        STOPPED, 
        PAUSED, 
        PLAYING, 
        SCAN_FORWARD, 
        SCAN_REVERSE, 
        BUFFERING, 
        WAITING, 
        MEDIA_ENDED, 
        TRANSITIONING, 
        READY, 
        RECONNECTING;
    }
}
