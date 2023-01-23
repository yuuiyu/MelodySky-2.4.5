//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing;

import java.util.*;

public class NSOption
{
    private Object key;
    
    public static Map<Object, Object> createOptionMap(final NSOption... options) {
        final Map<Object, Object> keyToValueMap = new HashMap<Object, Object>();
        if (options == null) {
            return keyToValueMap;
        }
        for (final NSOption option : options) {
            keyToValueMap.put(option.getOptionKey(), option.getOptionValue());
        }
        return keyToValueMap;
    }
    
    public NSOption(final Object key) {
        this.key = ((key == null) ? this.getClass().getName() : key);
    }
    
    public Object getOptionKey() {
        return this.key;
    }
    
    public Object getOptionValue() {
        return this;
    }
    
    @Override
    public String toString() {
        final Object key = this.getOptionKey();
        final String sKey = (key == this) ? key.getClass().getName() : key.toString();
        final Object value = this.getOptionValue();
        if (value != this) {
            return sKey + "=" + value;
        }
        if (this.getClass() == NSOption.class) {
            return sKey;
        }
        return sKey + "=" + value.getClass().getName();
    }
}
