//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.util;

import net.minecraftforge.fml.relauncher.*;
import xyz.Melody.*;
import com.google.common.collect.*;
import net.minecraft.util.*;
import net.minecraftforge.client.model.*;
import java.util.*;
import java.lang.invoke.*;
import net.minecraft.client.renderer.block.statemap.*;
import net.minecraft.block.*;
import java.lang.reflect.*;

public final class FoamUtils
{
    public static final MethodHandle MLR_GET_TEXTURES;
    public static final MethodHandle ML_LOAD_BLOCK;
    
    private FoamUtils() {
    }
    
    public static void wipeModelLoaderRegistryCache() {
        final Field resourceCacheField = ReflectionHelper.findField((Class)ModelLoaderRegistry.class, new String[] { "cache" });
        try {
            final Map<ResourceLocation, IModel> oldResourceCache = (Map<ResourceLocation, IModel>)resourceCacheField.get(null);
            int itemsCleared = 0;
            Client.instance.logger.info("Clearing ModelLoaderRegistry cache (" + oldResourceCache.size() + " items)...");
            for (final ResourceLocation r : Sets.newHashSet((Iterable)oldResourceCache.keySet())) {
                oldResourceCache.remove(r);
                ++itemsCleared;
            }
            Client.instance.logger.info("Cleared " + itemsCleared + " objects.");
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
    
    static {
        MethodHandle MLR_GET_TEXTURES_TMP = null;
        try {
            final Class k = Class.forName("net.minecraftforge.client.model.ModelLoaderRegistry");
            final Method m = k.getDeclaredMethod("getTextures", (Class[])new Class[0]);
            m.setAccessible(true);
            MLR_GET_TEXTURES_TMP = MethodHandles.lookup().unreflect(m);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        MLR_GET_TEXTURES = MLR_GET_TEXTURES_TMP;
        MethodHandle ML_LOAD_BLOCK_TMP = null;
        try {
            final Class i = Class.forName("net.minecraft.client.renderer.block.model.ModelBakery");
            final Method j = i.getDeclaredMethod("loadBlock", BlockStateMapper.class, Block.class, ResourceLocation.class);
            j.setAccessible(true);
            ML_LOAD_BLOCK_TMP = MethodHandles.lookup().unreflect(j);
        }
        catch (Exception e2) {
            e2.printStackTrace();
        }
        ML_LOAD_BLOCK = ML_LOAD_BLOCK_TMP;
    }
}
