//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

public class Music
{
    public static synchronized void playSound(final Class<?> clz, final String url) {
        new Thread((Runnable)new lIl((Class)clz, url)).start();
    }
}
