//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

import chrriis.dj.nativeswing.swtimpl.internal.*;

public class WMPControls
{
    private IOleNativeComponent nativeComponent;
    
    WMPControls(final JWMediaPlayer wMediaPlayer) {
        this.nativeComponent = (IOleNativeComponent)wMediaPlayer.getNativeComponent();
    }
    
    public boolean isPlayEnabled() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty(new String[] { "controls", "isAvailable" }, "Play"));
    }
    
    public void play() {
        this.nativeComponent.invokeOleFunction(new String[] { "controls", "Play" }, new Object[0]);
    }
    
    public boolean isStopEnabled() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty(new String[] { "controls", "isAvailable" }, "Stop"));
    }
    
    public void stop() {
        this.nativeComponent.invokeOleFunction(new String[] { "controls", "Stop" }, new Object[0]);
    }
    
    public boolean isPauseEnabled() {
        return Boolean.TRUE.equals(this.nativeComponent.getOleProperty(new String[] { "controls", "isAvailable" }, "Pause"));
    }
    
    public void pause() {
        this.nativeComponent.invokeOleFunction(new String[] { "controls", "Pause" }, new Object[0]);
    }
    
    public void setAbsolutePosition(final int time) {
        this.nativeComponent.setOleProperty(new String[] { "controls", "currentPosition" }, time / 1000.0);
    }
    
    public int getAbsolutePosition() {
        try {
            return (int)Math.round((double)this.nativeComponent.getOleProperty(new String[] { "controls", "currentPosition" }, new Object[0]) * 1000.0);
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return -1;
        }
    }
}
