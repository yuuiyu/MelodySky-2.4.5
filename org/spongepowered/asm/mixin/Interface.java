//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin;

import java.lang.annotation.*;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.CLASS)
public @interface Interface {
    Class<?> iface();
    
    String prefix();
    
    boolean unique() default false;
    
    Remap remap() default Remap.ALL;
    
    public enum Remap
    {
        ALL, 
        FORCE(true), 
        ONLY_PREFIXED, 
        NONE;
        
        private final boolean forceRemap;
        
        private Remap() {
            this(false);
        }
        
        private Remap(final boolean forceRemap) {
            this.forceRemap = forceRemap;
        }
        
        public boolean forceRemap() {
            return this.forceRemap;
        }
    }
}
