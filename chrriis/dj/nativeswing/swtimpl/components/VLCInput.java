//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import chrriis.dj.nativeswing.swtimpl.*;

public class VLCInput
{
    private WebBrowserObject webBrowserObject;
    
    VLCInput(final JVLCPlayer vlcPlayer) {
        this.webBrowserObject = vlcPlayer.getWebBrowserObject();
    }
    
    public int getDuration() {
        final Object value = this.webBrowserObject.getObjectProperty("input.length");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public float getFrameRate() {
        final Object value = this.webBrowserObject.getObjectProperty("input.fps");
        return (value == null) ? Float.NaN : ((Number)value).floatValue();
    }
    
    public boolean isVideoDisplayed() {
        return Boolean.TRUE.equals(this.webBrowserObject.getObjectProperty("input.isVout"));
    }
    
    public void setRelativePosition(final float position) {
        if (position >= 0.0f && position <= 1.0f) {
            this.webBrowserObject.setObjectProperty("input.position", position);
            return;
        }
        throw new IllegalArgumentException("The position must be between 0 and 1");
    }
    
    public float getRelativePosition() {
        final Object value = this.webBrowserObject.getObjectProperty("input.position");
        return (value == null) ? Float.NaN : ((Number)value).floatValue();
    }
    
    public void setAbsolutePosition(final int time) {
        this.webBrowserObject.setObjectProperty("input.time", time);
    }
    
    public int getAbsolutePosition() {
        final Object value = this.webBrowserObject.getObjectProperty("input.time");
        return (value == null) ? -1 : ((Number)value).intValue();
    }
    
    public VLCMediaState getMediaState() {
        final Object value = this.webBrowserObject.getObjectProperty("input.state");
        if (value == null) {
            return null;
        }
        switch (((Number)value).intValue()) {
            case 0: {
                return VLCMediaState.IDLE_CLOSE;
            }
            case 1: {
                return VLCMediaState.OPENING;
            }
            case 2: {
                return VLCMediaState.BUFFERING;
            }
            case 3: {
                return VLCMediaState.PLAYING;
            }
            case 4: {
                return VLCMediaState.PAUSED;
            }
            case 5: {
                return VLCMediaState.STOPPING;
            }
            case 6: {
                return VLCMediaState.ERROR;
            }
            default: {
                return null;
            }
        }
    }
    
    public void setPlaySpeedFactor(final float speedFactor) {
        this.webBrowserObject.setObjectProperty("input.rate", speedFactor);
    }
    
    public float getPlaySpeedFactor() {
        final Object value = this.webBrowserObject.getObjectProperty("input.rate");
        return (value == null) ? Float.NaN : ((Number)value).floatValue();
    }
    
    public enum VLCMediaState
    {
        IDLE_CLOSE, 
        OPENING, 
        BUFFERING, 
        PLAYING, 
        PAUSED, 
        STOPPING, 
        ERROR;
    }
}
