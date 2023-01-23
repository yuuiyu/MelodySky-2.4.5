//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.common.*;
import java.io.*;
import chrriis.dj.nativeswing.swtimpl.*;

public class VLCPlaylist
{
    private JVLCPlayer vlcPlayer;
    private WebBrowserObject webBrowserObject;
    private volatile Thread playlistFixThread;
    
    VLCPlaylist(final JVLCPlayer vlcPlayer) {
        this.vlcPlayer = vlcPlayer;
        this.webBrowserObject = vlcPlayer.getWebBrowserObject();
    }
    
    public int getItemCount() {
        final Object value = this.webBrowserObject.getObjectProperty("playlist.items.count");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public boolean isPlaying() {
        return Boolean.TRUE.equals(this.webBrowserObject.getObjectProperty("playlist.isPlaying"));
    }
    
    public void addItem(final Class<?> clazz, final String resourcePath) {
        this.addItem(clazz, resourcePath, null);
    }
    
    public void addItem(final Class<?> clazz, final String resourcePath, final String options) {
        this.vlcPlayer.addReferenceClassLoader(clazz.getClassLoader());
        this.addItem(WebServer.getDefaultWebServer().getClassPathResourceURL(clazz.getName(), resourcePath), options);
    }
    
    public void addItem(final String resourcePath) {
        this.addItem(resourcePath, null);
    }
    
    public void addItem(String resourcePath, final String options) {
        if (!this.webBrowserObject.hasContent()) {
            this.vlcPlayer.load();
            this.clear();
        }
        final File file = Utils.getLocalFile(resourcePath);
        if (file != null) {
            resourcePath = this.webBrowserObject.getLocalFileURL(file);
        }
        this.webBrowserObject.invokeObjectFunction("playlist.add", resourcePath, resourcePath, options);
    }
    
    public void play() {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.play", new Object[0]);
        this.setPlaylistFixActive(true);
    }
    
    public void playItem(final int itemID) {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.playItem", itemID);
        this.setPlaylistFixActive(true);
    }
    
    public void togglePause() {
        this.webBrowserObject.invokeObjectFunction("playlist.togglePause", new Object[0]);
    }
    
    public void stop() {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.stop", new Object[0]);
    }
    
    public void goNext() {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.next", new Object[0]);
        this.setPlaylistFixActive(true);
    }
    
    public void goPrevious() {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.prev", new Object[0]);
        this.setPlaylistFixActive(true);
    }
    
    public void clear() {
        this.setPlaylistFixActive(false);
        this.webBrowserObject.invokeObjectFunction("playlist.items.clear", new Object[0]);
    }
    
    public void removeItem(final int index) {
        this.webBrowserObject.invokeObjectFunction("playlist.items.removeItem", index);
    }
    
    private void setPlaylistFixActive(final boolean isActive) {
        if (this.playlistFixThread != null == isActive) {
            return;
        }
        if (isActive) {
            if (!Boolean.parseBoolean(NSSystemPropertySWT.VLCPLAYER_FIXPLAYLISTAUTOPLAYNEXT.get("true"))) {
                return;
            }
            (this.playlistFixThread = (Thread)new lIIlIlII(this, "NativeSwing - VLC Player playlist fix")).setDaemon(true);
            this.playlistFixThread.start();
        }
        else {
            this.playlistFixThread = null;
        }
    }
}
