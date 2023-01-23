//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.value;

public class Option<V> extends Value<V>
{
    public Option(final String name, final V enabled) {
        super(name, name);
        this.setValue(enabled);
    }
}
