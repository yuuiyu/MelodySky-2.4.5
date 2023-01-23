//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.client.*;
import java.lang.reflect.*;

public class ReflectionUtils
{
    public static boolean invoke(final Class<?> _class, final String methodName) {
        try {
            final Method method = _class.getDeclaredMethod(methodName, (Class<?>[])new Class[0]);
            method.setAccessible(true);
            method.invoke(Minecraft.getMinecraft(), new Object[0]);
            return true;
        }
        catch (Exception exception) {
            return false;
        }
    }
    
    public static Object field(final Object object, final String name) {
        try {
            final Field field = object.getClass().getDeclaredField(name);
            field.setAccessible(true);
            return field.get(object);
        }
        catch (Exception exception) {
            return null;
        }
    }
}
