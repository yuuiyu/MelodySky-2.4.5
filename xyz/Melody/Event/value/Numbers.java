//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Event.value;

public class Numbers<T extends Number> extends Value<T>
{
    private String name;
    public T min;
    public T max;
    public T inc;
    private boolean integer;
    
    public Numbers(final String name, final T value, final T min, final T max, final T inc) {
        super(name, name);
        this.setValue(value);
        this.min = min;
        this.max = max;
        this.inc = inc;
        this.integer = false;
    }
    
    public T getMinimum() {
        return this.min;
    }
    
    public T getMaximum() {
        return this.max;
    }
    
    public void setIncrement(final T inc) {
        this.inc = inc;
    }
    
    public T getIncrement() {
        return this.inc;
    }
    
    public String getId() {
        return this.name;
    }
}
