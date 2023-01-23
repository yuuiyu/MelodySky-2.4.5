//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import utils.hodgepodge.function.*;

public final class ObjectUtils
{
    public static int hash(final Object o) {
        return o.hashCode();
    }
    
    public static boolean checkNull(final Object o) {
        return o == null;
    }
    
    public static boolean checkNull(final Object... o) {
        if (o == null || o.length == 0) {
            return true;
        }
        for (final Object o2 : o) {
            if (checkNull(o2)) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean notNull(final Object o) {
        return o != null;
    }
    
    public static boolean notNull(final Object... o) {
        if (o == null || o.length == 0) {
            return false;
        }
        for (final Object o2 : o) {
            if (checkNull(o2)) {
                return false;
            }
        }
        return true;
    }
    
    public static <T> T makeSureNotNull(final T o) {
        if (o == null) {
            throw new NullPointerException();
        }
        return o;
    }
    
    public static <T> T makeSureNotNull(final T o, final String message) {
        if (o == null) {
            throw new NullPointerException(message);
        }
        return o;
    }
    
    public static <T> T makeSureNotNull(final T o, final VoidFunction function) {
        if (o == null) {
            function.handle();
        }
        return o;
    }
    
    public static Object[] makeSureNotNull(final Object... objArray) {
        for (final Object obj : objArray) {
            makeSureNotNull(obj);
        }
        return objArray;
    }
    
    public static Object[] makeSureNotNull(final String message, final Object... objArray) {
        for (final Object obj : objArray) {
            makeSureNotNull(obj, message);
        }
        return objArray;
    }
    
    public static Object[] makeSureNotNull(final VoidFunction function, final Object... objArray) {
        for (final Object obj : objArray) {
            makeSureNotNull(obj, function);
        }
        return objArray;
    }
    
    public static void trySomeThing(final ThrowsVoidFunction function, final CatchHandler handler) {
        try {
            function.handle();
        }
        catch (Throwable throwable) {
            handler.onCatchException(throwable);
        }
    }
    
    public static <O, T> O forcedConversion(final T obj) {
        return (O)obj;
    }
    
    public static boolean reverseBoolean(final boolean b) {
        return !b;
    }
}
