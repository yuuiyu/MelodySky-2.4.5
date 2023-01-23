//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import java.util.*;

public class ObfuscationData<T> implements Iterable<ObfuscationType>
{
    private final Map<ObfuscationType, T> data;
    private final T defaultValue;
    
    public ObfuscationData() {
        this(null);
    }
    
    public ObfuscationData(final T defaultValue) {
        this.data = new HashMap<ObfuscationType, T>();
        this.defaultValue = defaultValue;
    }
    
    public void add(final ObfuscationType type, final T value) {
        this.data.put(type, value);
    }
    
    public boolean isEmpty() {
        return this.data.isEmpty();
    }
    
    public T get(final ObfuscationType type) {
        final T value = this.data.get(type);
        return (value != null) ? value : this.defaultValue;
    }
    
    @Override
    public Iterator<ObfuscationType> iterator() {
        return this.data.keySet().iterator();
    }
    
    @Override
    public String toString() {
        return String.format("ObfuscationData[%sDEFAULT=%s]", this.listValues(), this.defaultValue);
    }
    
    private String listValues() {
        final StringBuilder sb = new StringBuilder();
        for (final ObfuscationType type : this.data.keySet()) {
            sb.append(type.getKey()).append('=').append(this.data.get(type)).append(',');
        }
        return sb.toString();
    }
}
