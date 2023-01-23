//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

import chrriis.dj.nativeswing.swtimpl.internal.*;

public class WMPSettings
{
    private IOleNativeComponent nativeComponent;
    
    WMPSettings(final JWMediaPlayer wMediaPlayer) {
        this.nativeComponent = (IOleNativeComponent)wMediaPlayer.getNativeComponent();
    }
    
    void setErrorDialogsEnabled(final boolean isErrorDialogEnabled) {
        this.nativeComponent.setOleProperty(new String[] { "settings", "enableErrorDialogs" }, isErrorDialogEnabled);
    }
    
    public void setVolume(final int volume) {
        if (volume < 0 || volume > 100) {
            throw new IllegalArgumentException("The volume must be between 0 and 100");
        }
        this.nativeComponent.setOleProperty(new String[] { "settings", "volume" }, volume);
    }
    
    public int getVolume() {
        try {
            return (int)this.nativeComponent.getOleProperty(new String[] { "settings", "volume" }, new Object[0]);
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return -1;
        }
    }
    
    public void setPlayCount(final int playCount) {
        if (playCount <= 0) {
            throw new IllegalArgumentException("The play count must be strictly greater than 0");
        }
        this.nativeComponent.setOleProperty(new String[] { "settings", "playCount" }, playCount);
    }
    
    public int getPlayCount() {
        try {
            return (int)this.nativeComponent.getOleProperty(new String[] { "settings", "playCount" }, new Object[0]);
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return -1;
        }
    }
    
    public void setPlaySpeedFactor(final float speedFactor) {
        if (speedFactor <= 0.0f) {
            throw new IllegalArgumentException("The rate must be strictly greater than 0!");
        }
        this.nativeComponent.setOleProperty(new String[] { "settings", "rate" }, speedFactor);
    }
    
    public float getPlaySpeedFactor() {
        try {
            return ((Double)this.nativeComponent.getOleProperty(new String[] { "settings", "rate" }, new Object[0])).floatValue();
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return Float.NaN;
        }
    }
    
    public void setStereoBalance(final int stereoBalance) {
        if (stereoBalance < 100 || stereoBalance > 100) {
            throw new IllegalArgumentException("The stereo balance must be between -100 and 100");
        }
        this.nativeComponent.setOleProperty(new String[] { "settings", "balance" }, stereoBalance);
    }
    
    public int getStereoBalance() {
        try {
            return (int)this.nativeComponent.getOleProperty(new String[] { "settings", "balance" }, new Object[0]);
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return -1;
        }
    }
    
    public void setAutoStart(final boolean isAutoStart) {
        this.nativeComponent.setOleProperty(new String[] { "settings", "autoStart" }, isAutoStart);
    }
    
    public boolean isAutoStart() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty(new String[] { "settings", "autoStart" }, new Object[0]));
    }
    
    public void setMute(final boolean isMute) {
        this.nativeComponent.setOleProperty(new String[] { "settings", "mute" }, isMute);
    }
    
    public boolean isMute() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty(new String[] { "settings", "mute" }, new Object[0]));
    }
}
