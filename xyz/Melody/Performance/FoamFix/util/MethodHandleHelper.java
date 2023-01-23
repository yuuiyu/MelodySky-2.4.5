//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.util;

import java.lang.invoke.*;
import net.minecraftforge.fml.relauncher.*;

public final class MethodHandleHelper
{
    private MethodHandleHelper() {
    }
    
    public static MethodHandle findFieldGetter(final Class c, final String... names) {
        try {
            return MethodHandles.lookup().unreflectGetter(ReflectionHelper.findField(c, names));
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static MethodHandle findFieldSetter(final Class c, final String... names) {
        try {
            return MethodHandles.lookup().unreflectSetter(ReflectionHelper.findField(c, names));
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static MethodHandle findFieldGetter(final String s, final String... names) {
        try {
            return findFieldGetter(Class.forName(s), names);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static MethodHandle findFieldSetter(final String s, final String... names) {
        try {
            return findFieldSetter(Class.forName(s), names);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
