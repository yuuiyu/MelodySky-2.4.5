//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.client;

import gnu.trove.strategy.*;
import gnu.trove.set.hash.*;
import xyz.Melody.Performance.FoamFix.shared.*;
import net.minecraftforge.client.model.pipeline.*;
import net.minecraft.block.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import org.apache.logging.log4j.*;
import net.minecraft.util.*;
import java.lang.invoke.*;
import com.google.common.collect.*;
import java.lang.reflect.*;
import java.util.*;
import xyz.Melody.Performance.FoamFix.util.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.item.*;
import com.google.gson.*;
import net.minecraftforge.client.model.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.model.*;
import com.google.common.base.*;
import net.minecraft.client.renderer.*;
import com.google.common.cache.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.block.model.*;

public class Deduplicator
{
    private static final Set<Class> BLACKLIST_CLASS;
    private static final Set<Class> TRIM_ARRAYS_CLASSES;
    private static final Map<Class, Set<MethodHandle[]>> CLASS_FIELDS;
    private static final Map<Class, MethodHandle> COLLECTION_CONSTRUCTORS;
    private static final MethodHandle FIELD_UNPACKED_DATA_GETTER;
    private static final MethodHandle FIELD_UNPACKED_DATA_SETTER;
    private static final MethodHandle IPAM_MW_TRANSFORMS_GETTER;
    private static final MethodHandle IPAM_MW_TRANSFORMS_SETTER;
    private static final MethodHandle BIM_TRANSFORMS_GETTER;
    private static final MethodHandle BIM_TRANSFORMS_SETTER;
    public int successfuls;
    public int maxRecursion;
    private final IDeduplicatingStorage<float[]> FLOATA_STORAGE;
    private final IDeduplicatingStorage<float[][]> FLOATAA_STORAGE;
    private final IDeduplicatingStorage OBJECT_STORAGE;
    private final IDeduplicatingStorage<ItemCameraTransforms> ICT_STORAGE;
    private final Set<Object> deduplicatedObjects;
    
    public Deduplicator() {
        this.successfuls = 0;
        this.maxRecursion = 0;
        this.FLOATA_STORAGE = (IDeduplicatingStorage<float[]>)new DeduplicatingStorageTrove((HashingStrategy)HashingStrategies.FLOAT_ARRAY);
        this.FLOATAA_STORAGE = (IDeduplicatingStorage<float[][]>)new DeduplicatingStorageTrove((HashingStrategy)HashingStrategies.FLOAT_ARRAY_ARRAY);
        this.OBJECT_STORAGE = (IDeduplicatingStorage)new DeduplicatingStorageTrove(HashingStrategies.GENERIC);
        this.ICT_STORAGE = (IDeduplicatingStorage<ItemCameraTransforms>)new DeduplicatingStorageTrove((HashingStrategy)HashingStrategies.ITEM_CAMERA_TRANSFORMS);
        this.deduplicatedObjects = (Set<Object>)new TCustomHashSet(HashingStrategies.IDENTITY);
    }
    
    private boolean shouldCheckClass(final Class c) {
        if (Deduplicator.BLACKLIST_CLASS.contains(c)) {
            return false;
        }
        if (c.isPrimitive() || c.isEnum() || (c.isArray() && !this.shouldCheckClass(c.getComponentType()))) {
            Deduplicator.BLACKLIST_CLASS.add(c);
            return false;
        }
        return true;
    }
    
    public void addObject(final Object o) {
        this.OBJECT_STORAGE.deduplicate(o);
    }
    
    public void addObjects(final Collection coll) {
        for (final Object o : coll) {
            this.OBJECT_STORAGE.deduplicate(o);
        }
    }
    
    public Object deduplicate0(final Object o) {
        Object n = o;
        int size = 0;
        if (o instanceof float[]) {
            size = 24 + ((float[])o).length * 4;
            n = this.FLOATA_STORAGE.deduplicate((float[])o);
        }
        else if (o instanceof float[][]) {
            size = 16 + ((float[][])o).length * 4;
            final float[][] arr = this.FLOATAA_STORAGE.deduplicate((float[][])o);
            if (arr != o) {
                n = arr;
                this.successfuls += arr.length;
            }
            else {
                for (int i = 0; i < arr.length; ++i) {
                    arr[i] = (float[])this.deduplicate0(arr[i]);
                }
            }
        }
        else if (o instanceof float[][][]) {
            final float[][][] arr2 = (float[][][])o;
            for (int i = 0; i < arr2.length; ++i) {
                arr2[i] = (float[][])this.deduplicate0(arr2[i]);
            }
        }
        else if (o instanceof ImmutableList || o instanceof ImmutableSet || o instanceof ImmutableMap) {
            n = this.OBJECT_STORAGE.deduplicate(o);
        }
        else {
            final Class c = o.getClass();
            if (ResourceLocation.class == c || ModelResourceLocation.class == c) {
                size = 16;
                n = this.OBJECT_STORAGE.deduplicate(o);
            }
            else if (TRSRTransformation.class == c) {
                size = 257;
                n = this.OBJECT_STORAGE.deduplicate(o);
            }
            else {
                if (ItemCameraTransforms.class != c) {
                    return null;
                }
                size = 80;
                n = this.ICT_STORAGE.deduplicate((ItemCameraTransforms)o);
            }
        }
        if (n != o) {
            ++this.successfuls;
            FoamFixShared.ramSaved += size;
        }
        return n;
    }
    
    public Object deduplicateObject(final Object o, final int recursion) {
        if (o == null || recursion > this.maxRecursion) {
            return o;
        }
        final Class c = o.getClass();
        if (!this.shouldCheckClass(c)) {
            return o;
        }
        if (!this.deduplicatedObjects.add(o)) {
            return o;
        }
        if (o instanceof IBakedModel) {
            if (o instanceof IPerspectiveAwareModel.MapWrapper) {
                try {
                    final Object to = Deduplicator.IPAM_MW_TRANSFORMS_GETTER.invoke(o);
                    final Object toD = this.deduplicate0(to);
                    if (toD != null && to != toD) {
                        Deduplicator.IPAM_MW_TRANSFORMS_SETTER.invoke(o, toD);
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
            else if ("net.minecraftforge.client.model.ItemLayerModel$BakedItemModel".equals(c.getName())) {
                try {
                    final Object to = Deduplicator.BIM_TRANSFORMS_GETTER.invoke(o);
                    final Object toD = this.deduplicate0(to);
                    if (toD != null && to != toD) {
                        Deduplicator.BIM_TRANSFORMS_SETTER.invoke(o, toD);
                    }
                }
                catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
        if (c == UnpackedBakedQuad.class) {
            try {
                final float[][][] array = Deduplicator.FIELD_UNPACKED_DATA_GETTER.invokeExact((UnpackedBakedQuad)o);
                this.deduplicate0(array);
            }
            catch (Throwable t) {
                t.printStackTrace();
            }
        }
        else {
            if (o instanceof ResourceLocation || o instanceof TRSRTransformation) {
                return this.deduplicate0(o);
            }
            if (c == ItemCameraTransforms.class) {
                final Object d = this.deduplicate0(o);
                if (d != o) {
                    return d;
                }
                return o;
            }
            else if (o instanceof Item || o instanceof Block || o instanceof World || o instanceof Entity || o instanceof Logger || o instanceof IRegistry) {
                Deduplicator.BLACKLIST_CLASS.add(c);
            }
            else if (o instanceof Optional) {
                final Optional opt = (Optional)o;
                if (opt.isPresent()) {
                    final Object b = this.deduplicateObject(opt.get(), recursion + 1);
                    if (b != null && b != opt.get()) {
                        return Optional.of(b);
                    }
                }
            }
            else if (o instanceof Multimap) {
                if (!(o instanceof ImmutableMultimap)) {
                    for (final Object key : ((Multimap)o).keySet()) {
                        final List l = Lists.newArrayList((Iterable)((Multimap)o).values());
                        for (int i = 0; i < l.size(); ++i) {
                            l.set(i, this.deduplicateObject(l.get(i), recursion + 1));
                        }
                        ((Multimap)o).replaceValues(key, (Iterable)l);
                    }
                }
            }
            else if (o instanceof Map) {
                if (o instanceof ImmutableMap) {
                    final ImmutableMap im = (ImmutableMap)o;
                    final Map newMap = new HashMap();
                    boolean deduplicated = false;
                    for (final Object key2 : im.keySet()) {
                        final Object a = im.get(key2);
                        final Object b2 = this.deduplicateObject(a, recursion + 1);
                        newMap.put(key2, (b2 != null) ? b2 : a);
                        if (b2 != null && b2 != a) {
                            deduplicated = true;
                        }
                    }
                    if (deduplicated) {
                        return ImmutableMap.copyOf(newMap);
                    }
                }
                else {
                    for (final Object key : ((Map)o).keySet()) {
                        final Object value = ((Map)o).get(key);
                        final Object valueD = this.deduplicateObject(value, recursion + 1);
                        if (valueD != null && value != valueD) {
                            ((Map)o).put(key, valueD);
                        }
                    }
                }
            }
            else if (o instanceof List) {
                if (o instanceof ImmutableList) {
                    final ImmutableList il = (ImmutableList)o;
                    final List newList = new ArrayList();
                    boolean deduplicated = false;
                    for (int i = 0; i < il.size(); ++i) {
                        final Object a2 = il.get(i);
                        final Object b3 = this.deduplicateObject(a2, recursion + 1);
                        newList.add((b3 != null) ? b3 : a2);
                        if (b3 != null && b3 != a2) {
                            deduplicated = true;
                        }
                    }
                    if (deduplicated) {
                        return ImmutableList.copyOf((Collection)newList);
                    }
                }
                else {
                    final List j = (List)o;
                    for (int k = 0; k < j.size(); ++k) {
                        j.set(k, this.deduplicateObject(j.get(k), recursion + 1));
                    }
                }
            }
            else if (o instanceof Collection) {
                if (!Deduplicator.COLLECTION_CONSTRUCTORS.containsKey(c)) {
                    try {
                        Deduplicator.COLLECTION_CONSTRUCTORS.put(c, MethodHandles.publicLookup().findConstructor(c, MethodType.methodType(Void.TYPE)));
                    }
                    catch (Exception e2) {
                        Deduplicator.COLLECTION_CONSTRUCTORS.put(c, null);
                    }
                }
                final MethodHandle constructor = Deduplicator.COLLECTION_CONSTRUCTORS.get(c);
                if (constructor != null) {
                    try {
                        final Collection nc = constructor.invoke();
                        for (final Object o2 : (Collection)o) {
                            nc.add(this.deduplicateObject(o2, recursion + 1));
                        }
                        return nc;
                    }
                    catch (Throwable t3) {}
                }
                for (final Object o3 : (Collection)o) {
                    this.deduplicateObject(o3, recursion + 1);
                }
            }
            else if (c.isArray()) {
                for (int m = 0; m < Array.getLength(o); ++m) {
                    final Object entry = Array.get(o, m);
                    final Object entryD = this.deduplicateObject(entry, recursion + 1);
                    if (entryD != null && entry != entryD) {
                        Array.set(o, m, entryD);
                    }
                }
            }
            else {
                if (!Deduplicator.CLASS_FIELDS.containsKey(c)) {
                    final ImmutableSet.Builder<MethodHandle[]> fsBuilder = (ImmutableSet.Builder<MethodHandle[]>)ImmutableSet.builder();
                    Class cc = c;
                    do {
                        for (final Field f : cc.getDeclaredFields()) {
                            f.setAccessible(true);
                            if ((f.getModifiers() & 0x8) == 0x0) {
                                if (this.shouldCheckClass(f.getType())) {
                                    try {
                                        fsBuilder.add((Object)new MethodHandle[] { MethodHandles.lookup().unreflectGetter(f), MethodHandles.lookup().unreflectSetter(f) });
                                    }
                                    catch (IllegalAccessException e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                        }
                    } while ((cc = cc.getSuperclass()) != Object.class);
                    Deduplicator.CLASS_FIELDS.put(c, (Set<MethodHandle[]>)fsBuilder.build());
                }
                for (final MethodHandle[] mh : Deduplicator.CLASS_FIELDS.get(c)) {
                    try {
                        final Object value = mh[0].invoke(o);
                        final Object valueD = this.deduplicateObject(value, recursion + 1);
                        if (Deduplicator.TRIM_ARRAYS_CLASSES.contains(c) && valueD instanceof ArrayList) {
                            ((ArrayList)valueD).trimToSize();
                        }
                        if (valueD == null || value == valueD) {
                            continue;
                        }
                        mh[1].invoke(o, valueD);
                    }
                    catch (IllegalAccessException ex) {}
                    catch (Throwable t2) {
                        t2.printStackTrace();
                    }
                }
            }
        }
        return o;
    }
    
    static {
        BLACKLIST_CLASS = (Set)new TCustomHashSet(HashingStrategies.IDENTITY);
        TRIM_ARRAYS_CLASSES = (Set)new TCustomHashSet(HashingStrategies.IDENTITY);
        CLASS_FIELDS = new IdentityHashMap<Class, Set<MethodHandle[]>>();
        COLLECTION_CONSTRUCTORS = new IdentityHashMap<Class, MethodHandle>();
        FIELD_UNPACKED_DATA_GETTER = MethodHandleHelper.findFieldGetter(UnpackedBakedQuad.class, "unpackedData");
        FIELD_UNPACKED_DATA_SETTER = MethodHandleHelper.findFieldSetter(UnpackedBakedQuad.class, "unpackedData");
        IPAM_MW_TRANSFORMS_GETTER = MethodHandleHelper.findFieldGetter(IPerspectiveAwareModel.MapWrapper.class, "transforms");
        IPAM_MW_TRANSFORMS_SETTER = MethodHandleHelper.findFieldSetter(IPerspectiveAwareModel.MapWrapper.class, "transforms");
        BIM_TRANSFORMS_GETTER = MethodHandleHelper.findFieldGetter("net.minecraftforge.client.model.ItemLayerModel$BakedItemModel", "transforms");
        BIM_TRANSFORMS_SETTER = MethodHandleHelper.findFieldSetter("net.minecraftforge.client.model.ItemLayerModel$BakedItemModel", "transforms");
        Deduplicator.TRIM_ARRAYS_CLASSES.add(FoamyItemLayerModel.DynamicItemModel.class);
        Deduplicator.TRIM_ARRAYS_CLASSES.add(SimpleBakedModel.class);
        Deduplicator.BLACKLIST_CLASS.add(FoamyItemLayerModel.Dynamic3DItemModel.class);
        Deduplicator.BLACKLIST_CLASS.add(Object.class);
        Deduplicator.BLACKLIST_CLASS.add(Class.class);
        Deduplicator.BLACKLIST_CLASS.add(String.class);
        Deduplicator.BLACKLIST_CLASS.add(Integer.class);
        Deduplicator.BLACKLIST_CLASS.add(Long.class);
        Deduplicator.BLACKLIST_CLASS.add(Byte.class);
        Deduplicator.BLACKLIST_CLASS.add(Boolean.class);
        Deduplicator.BLACKLIST_CLASS.add(Float.class);
        Deduplicator.BLACKLIST_CLASS.add(Double.class);
        Deduplicator.BLACKLIST_CLASS.add(Short.class);
        Deduplicator.BLACKLIST_CLASS.add(TextureAtlasSprite.class);
        Deduplicator.BLACKLIST_CLASS.add(ItemStack.class);
        Deduplicator.BLACKLIST_CLASS.add(Gson.class);
        Deduplicator.BLACKLIST_CLASS.add(ModelLoader.class);
        Deduplicator.BLACKLIST_CLASS.add(Class.class);
        Deduplicator.BLACKLIST_CLASS.add(BlockPart.class);
        Deduplicator.BLACKLIST_CLASS.add(Minecraft.class);
        Deduplicator.BLACKLIST_CLASS.add(BlockModelShapes.class);
        Deduplicator.BLACKLIST_CLASS.add(ModelManager.class);
        Deduplicator.BLACKLIST_CLASS.add(Logger.class);
        Deduplicator.BLACKLIST_CLASS.add(Joiner.class);
        Deduplicator.BLACKLIST_CLASS.add(Tessellator.class);
        Deduplicator.BLACKLIST_CLASS.add(WorldRenderer.class);
        Deduplicator.BLACKLIST_CLASS.add(Cache.class);
        Deduplicator.BLACKLIST_CLASS.add(LoadingCache.class);
        Deduplicator.BLACKLIST_CLASS.add(VertexFormatElement.class);
        Deduplicator.BLACKLIST_CLASS.add(BakedQuad.class);
    }
}
