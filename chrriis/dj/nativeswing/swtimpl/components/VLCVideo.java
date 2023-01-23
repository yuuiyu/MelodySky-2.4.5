//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;

public class VLCVideo
{
    private WebBrowserObject webBrowserObject;
    
    VLCVideo(final JVLCPlayer vlcPlayer) {
        this.webBrowserObject = vlcPlayer.getWebBrowserObject();
    }
    
    public int getWidth() {
        final Object value = this.webBrowserObject.getObjectProperty("video.width");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public int getHeight() {
        final Object value = this.webBrowserObject.getObjectProperty("video.height");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public void setFullScreen(final boolean isFullScreen) {
        this.webBrowserObject.setObjectProperty("video.fullscreen", isFullScreen);
    }
    
    public boolean isFullScreen() {
        return Boolean.TRUE.equals(this.webBrowserObject.getObjectProperty("video.fullscreen"));
    }
    
    public void setAspectRatio(final VLCAspectRatio aspectRatio) {
        String value = null;
        switch (lIIlIIll.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$VLCVideo$VLCAspectRatio[aspectRatio.ordinal()]) {
            case 1: {
                value = "1:1";
                break;
            }
            case 2: {
                value = "4:3";
                break;
            }
            case 3: {
                value = "16:9";
                break;
            }
            case 4: {
                value = "16:10";
                break;
            }
            case 5: {
                value = "221:100";
                break;
            }
            case 6: {
                value = "5:4";
                break;
            }
            default: {
                throw new IllegalArgumentException("The aspect ratio value is invalid!");
            }
        }
        this.webBrowserObject.setObjectProperty("video.aspectRatio", value);
    }
    
    public VLCAspectRatio getAspectRatio() {
        final String value = (String)this.webBrowserObject.getObjectProperty("video.aspectRatio");
        if ("1:1".equals(value)) {
            return VLCAspectRatio._1x1;
        }
        if ("4:3".equals(value)) {
            return VLCAspectRatio._4x3;
        }
        if ("16:9".equals(value)) {
            return VLCAspectRatio._16x9;
        }
        if ("16:10".equals(value)) {
            return VLCAspectRatio._16x10;
        }
        if ("221:100".equals(value)) {
            return VLCAspectRatio._221x100;
        }
        if ("5:4".equals(value)) {
            return VLCAspectRatio._5x4;
        }
        return null;
    }
    
    public void setSubtitleTrack(final int subtitleTrack) {
        this.webBrowserObject.setObjectProperty("video.subtitle", subtitleTrack);
    }
    
    public int getSubtitleTrack() {
        final Object value = this.webBrowserObject.getObjectProperty("video.subtitle");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public void toggleFullScreen() {
        this.webBrowserObject.invokeObjectFunction("video.toggleFullscreen", new Object[0]);
    }
    
    public enum VLCAspectRatio
    {
        _1x1, 
        _4x3, 
        _16x9, 
        _16x10, 
        _221x100, 
        _5x4;
    }
}
