//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;

public class VLCAudio
{
    private WebBrowserObject webBrowserObject;
    
    VLCAudio(final JVLCPlayer vlcPlayer) {
        this.webBrowserObject = vlcPlayer.getWebBrowserObject();
    }
    
    public void setMute(final boolean isMute) {
        this.webBrowserObject.setObjectProperty("audio.mute", isMute);
    }
    
    public boolean isMute() {
        return Boolean.TRUE.equals(this.webBrowserObject.getObjectProperty("audio.mute"));
    }
    
    public void setVolume(final int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("The volume must be between 0 and 100");
        }
        this.webBrowserObject.setObjectProperty("audio.volume", Math.round(volume * 1.99 + 1.0));
    }
    
    public int getVolume() {
        final Object value = this.webBrowserObject.getObjectProperty("audio.volume");
        return (value == null) ? -1 : Math.max(0, (int)Math.round((((Number)value).intValue() - 1) / 1.99));
    }
    
    public void setTrack(final int track) {
        this.webBrowserObject.setObjectProperty("audio.track", track);
    }
    
    public int getTrack() {
        final Object value = this.webBrowserObject.getObjectProperty("audio.track");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public void setChannel(final VLCChannel channel) {
        int value = 0;
        switch (llIIllI.$SwitchMap$chrriis$dj$nativeswing$swtimpl$components$VLCAudio$VLCChannel[channel.ordinal()]) {
            case 1: {
                value = 1;
                break;
            }
            case 2: {
                value = 2;
                break;
            }
            case 3: {
                value = 3;
                break;
            }
            case 4: {
                value = 4;
                break;
            }
            case 5: {
                value = 5;
                break;
            }
            default: {
                throw new IllegalArgumentException("The channel value is invalid!");
            }
        }
        this.webBrowserObject.setObjectProperty("audio.channel", value);
    }
    
    public VLCChannel getChannel() {
        final Object value = this.webBrowserObject.getObjectProperty("audio.channel");
        if (value == null) {
            return null;
        }
        switch (((Number)value).intValue()) {
            case 1: {
                return VLCChannel.STEREO;
            }
            case 2: {
                return VLCChannel.REVERSE_STEREO;
            }
            case 3: {
                return VLCChannel.LEFT;
            }
            case 4: {
                return VLCChannel.RIGHT;
            }
            case 5: {
                return VLCChannel.DOLBY;
            }
            default: {
                return null;
            }
        }
    }
    
    public void toggleMute() {
        this.webBrowserObject.invokeObjectFunction("audio.toggleMute", new Object[0]);
    }
    
    public enum VLCChannel
    {
        STEREO, 
        REVERSE_STEREO, 
        LEFT, 
        RIGHT, 
        DOLBY;
    }
}
