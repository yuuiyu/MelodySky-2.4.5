//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.value;

public class Mode<V extends Enum> extends Value<V>
{
    private V[] modes;
    
    public Mode(final String name, final V[] modes, final V value) {
        super(name, name);
        this.modes = modes;
        this.setValue(value);
    }
    
    public V[] getModes() {
        return this.modes;
    }
    
    public String getModeAsString() {
        return this.getValue().name();
    }
    
    public void setMode(final String mode) {
        for (final V e : this.modes) {
            if (e.name().equalsIgnoreCase(mode)) {
                this.setValue(e);
            }
        }
    }
    
    public boolean isValid(final String name) {
        for (final V e : this.modes) {
            if (e.name().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }
}
