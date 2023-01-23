//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import java.util.*;
import com.google.common.collect.*;
import java.util.function.*;

public class ArrayUtils
{
    public static Object getRandomItem(final List<?> list) {
        return list.get(new Random().nextInt(list.size()));
    }
    
    public static <T> T firstOrNull(final Iterable<T> iterable) {
        return (T)Iterables.getFirst((Iterable)iterable, (Object)null);
    }
    
    public static <T> T getFirstMatch(final List<T> list, final Predicate<? super T> predicate) {
        return list.stream().filter(predicate).findFirst().orElse(null);
    }
}
