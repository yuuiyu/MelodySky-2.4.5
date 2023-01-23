//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import java.lang.reflect.*;

public final class ReflectUtils
{
    public static <T> Field getField(final T obj, final String fieldName) throws NoSuchFieldException {
        final Field field = obj.getClass().getDeclaredField(fieldName);
        return setFieldAccessible(field, true);
    }
    
    public static Field setFieldAccessible(final Field field, final boolean accessible) {
        field.setAccessible(accessible);
        return field;
    }
    
    public static <T, V> void setField(final T obj, final String fieldName, final V value) throws NoSuchFieldException, IllegalAccessException {
        getField(obj, fieldName).set(obj, value);
    }
    
    public static <T> Method getMethod(final T obj, final String methodName, final Class<?>... parameterTypes) throws NoSuchMethodException {
        final Method method = obj.getClass().getDeclaredMethod(methodName, parameterTypes);
        return setMethodAccessible(method, true);
    }
    
    public static <T> Method getMethodFromClass(final Class<T> cla, final String methodName, final Class<?>... parameterTypes) throws NoSuchMethodException {
        final Method method = cla.getDeclaredMethod(methodName, parameterTypes);
        return setMethodAccessible(method, true);
    }
    
    public static Class[] getParameterTypes(final Object[] objects) {
        final Class[] classes = new Class[objects.length];
        for (int i = 0; i < objects.length; ++i) {
            classes[i] = objects[i].getClass();
        }
        return classes;
    }
    
    public static Method setMethodAccessible(final Method method, final boolean accessible) {
        method.setAccessible(accessible);
        return method;
    }
    
    public static <T> Object invokeMethodFromName(final T obj, final Class<T> objClass, final String methodName, final Object[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        return invokeMethod(obj, getMethodFromClass(objClass, methodName, (Class<?>[])getParameterTypes(args)), args);
    }
    
    public static <T> Object invokeMethod(final T obj, final Method method, final Object[] args) throws InvocationTargetException, IllegalAccessException {
        return method.invoke(obj, args);
    }
}
