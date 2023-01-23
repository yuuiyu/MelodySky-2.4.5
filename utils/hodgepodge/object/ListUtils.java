//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package utils.hodgepodge.object;

import java.util.function.*;
import java.util.stream.*;
import java.util.*;

public final class ListUtils
{
    public static <E> List<E> emptyList() {
        return Collections.emptyList();
    }
    
    public static <E> List<E> search(final List<E> searchedList, final ListSearchBooleanFunction<E> function) {
        return searchedList.stream().filter(function::function).collect((Collector<? super Object, ?, List<E>>)Collectors.toList());
    }
    
    public static <E> E firstItem(final List<E> list) {
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
    
    public static <E> E lastItem(final List<E> list) {
        if (list.size() <= 0) {
            return null;
        }
        return list.get(list.size() - 1);
    }
    
    public static <E> boolean isFirstItem(final List<E> list, final E item) {
        return list.indexOf(item) == 0;
    }
    
    public static <E> boolean isLastItem(final List<E> list, final E item) {
        return list.indexOf(item) == list.size() - 1;
    }
    
    public static <E> E pollFirst(final List<E> list) {
        final E item = (E)firstItem((List<Object>)list);
        if (item != null) {
            list.remove(item);
            return item;
        }
        return null;
    }
    
    public static <E> E pollLast(final List<E> list) {
        final E item = (E)lastItem((List<Object>)list);
        if (item != null) {
            list.remove(item);
            return item;
        }
        return null;
    }
    
    public static <E> String asString(final List<E> list) {
        if (list == null || list.isEmpty()) {
            return "[]";
        }
        final StringBuilder builder = new StringBuilder("[");
        for (final E o : list) {
            builder.append(o);
            if (!isLastItem(list, o)) {
                builder.append(",");
            }
        }
        builder.append("]");
        return builder.toString();
    }
    
    public static <E> void addArrayToList(final List<E> list, final E... array) {
        Collections.addAll(list, array);
    }
    
    public interface ListSearchBooleanFunction<E>
    {
        boolean function(final E p0);
    }
}
