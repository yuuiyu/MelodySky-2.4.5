//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

import java.util.regex.*;

public abstract class JavaVersion
{
    private static double current;
    
    private JavaVersion() {
    }
    
    public static double current() {
        if (JavaVersion.current == 0.0) {
            JavaVersion.current = resolveCurrentVersion();
        }
        return JavaVersion.current;
    }
    
    private static double resolveCurrentVersion() {
        final String version = System.getProperty("java.version");
        final Matcher matcher = Pattern.compile("[0-9]+\\.[0-9]+").matcher(version);
        if (matcher.find()) {
            return Double.parseDouble(matcher.group());
        }
        return 1.6;
    }
    
    static {
        JavaVersion.current = 0.0;
    }
}
