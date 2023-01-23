//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection;

import java.lang.annotation.*;

@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ModifyConstant {
    String method();
    
    Constant constant() default @Constant;
    
    boolean remap() default true;
    
    int require() default -1;
    
    int expect() default 1;
    
    String constraints() default "";
}
