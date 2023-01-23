//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import java.lang.reflect.*;
import net.minecraft.client.*;

public class MethodReflectionHelper
{
    private Method method;
    
    public MethodReflectionHelper(final Class<?> clazz, final String methodName, final String methodname2, final Class<?>... parameter) {
        try {
            try {
                this.method = clazz.getDeclaredMethod(methodName, parameter);
            }
            catch (NoSuchMethodException e2) {
                this.method = clazz.getDeclaredMethod(methodname2, parameter);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public void invoke(final Object instance) {
        try {
            this.method.setAccessible(true);
            this.method.invoke(instance, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invoke(final Object instance, final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(instance, new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invokeStatic() {
        try {
            this.method.setAccessible(true);
            this.method.invoke(null, new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invokeStatic(final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(null, new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invokeMc() {
        try {
            this.method.setAccessible(true);
            this.method.invoke(Minecraft.getMinecraft(), new Object[0]);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void invokeMc(final int amt) {
        try {
            this.method.setAccessible(true);
            for (int i = 0; i < amt; ++i) {
                this.method.invoke(Minecraft.getMinecraft(), new Object[0]);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
