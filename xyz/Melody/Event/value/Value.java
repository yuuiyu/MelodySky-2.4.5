//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.value;

public abstract class Value<V>
{
    private String displayName;
    private String name;
    private V value;
    
    public Value(final String displayName, final String name) {
        this.displayName = displayName;
        this.name = name;
    }
    
    public String getDisplayName() {
        return this.displayName;
    }
    
    public String getName() {
        return this.name;
    }
    
    public V getValue() {
        return this.value;
    }
    
    public void setValue(final V value) {
        this.value = value;
    }
}
