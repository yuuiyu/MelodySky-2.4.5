//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import javax.sound.sampled.*;

final class lIl implements Runnable
{
    final /* synthetic */ Class val$clz;
    final /* synthetic */ String val$url;
    
    lIl(final Class val$clz, final String val$url) {
        this.val$clz = val$clz;
        this.val$url = val$url;
    }
    
    @Override
    public void run() {
        try {
            final Clip clip = AudioSystem.getClip();
            final AudioInputStream inputStream = AudioSystem.getAudioInputStream(this.val$clz.getResourceAsStream("/assets/minecraft/Melody/Sounds/" + this.val$url));
            clip.open(inputStream);
            final FloatControl gainControl = (FloatControl)clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10.0f);
            clip.start();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
