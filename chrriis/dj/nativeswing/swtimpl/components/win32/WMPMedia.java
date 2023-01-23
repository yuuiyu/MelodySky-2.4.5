//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components.win32;

import chrriis.dj.nativeswing.swtimpl.internal.*;

public class WMPMedia
{
    private IOleNativeComponent nativeComponent;
    
    WMPMedia(final JWMediaPlayer wMediaPlayer) {
        this.nativeComponent = (IOleNativeComponent)wMediaPlayer.getNativeComponent();
    }
    
    public int getDuration() {
        try {
            return (int)Math.round((double)this.nativeComponent.getOleProperty(new String[] { "currentMedia", "duration" }, new Object[0]) * 1000.0);
        }
        catch (IllegalStateException e) {
            throw e;
        }
        catch (Exception e2) {
            return -1;
        }
    }
}
