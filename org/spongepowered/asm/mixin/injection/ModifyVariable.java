//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyVariable {
    String method();
    
    Slice slice() default @Slice;
    
    At at();
    
    boolean print() default false;
    
    int ordinal() default -1;
    
    int index() default -1;
    
    String[] name() default {};
    
    boolean argsOnly() default false;
    
    boolean remap() default true;
    
    int require() default -1;
    
    int expect() default 1;
    
    String constraints() default "";
}
