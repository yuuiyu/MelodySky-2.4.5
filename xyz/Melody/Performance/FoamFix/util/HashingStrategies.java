//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.util;

import net.minecraft.client.renderer.block.model.*;
import gnu.trove.strategy.*;
import org.lwjgl.util.vector.*;
import com.google.common.collect.*;
import java.util.*;

public final class HashingStrategies
{
    public static final HashingStrategy<byte[]> BYTE_ARRAY;
    public static final HashingStrategy<float[]> FLOAT_ARRAY;
    public static final HashingStrategy<float[][]> FLOAT_ARRAY_ARRAY;
    public static final HashingStrategy<int[]> INT_ARRAY;
    public static final HashingStrategy GENERIC;
    public static final HashingStrategy IDENTITY;
    public static final HashingStrategy<ItemCameraTransforms> ITEM_CAMERA_TRANSFORMS;
    public static final HashingStrategy<ItemTransformVec3f> ITEM_TRANSFORM_VEC3F;
    
    static {
        BYTE_ARRAY = (HashingStrategy)new ByteArray(null);
        FLOAT_ARRAY = (HashingStrategy)new FloatArray(null);
        FLOAT_ARRAY_ARRAY = (HashingStrategy)new FloatArrayArray(null);
        INT_ARRAY = (HashingStrategy)new IntArray(null);
        GENERIC = (HashingStrategy)new ObjectStrategy(null);
        IDENTITY = (HashingStrategy)new IdentityHashingStrategy();
        ITEM_CAMERA_TRANSFORMS = (HashingStrategy)new ItemCameraTransformsStrategy(null);
        ITEM_TRANSFORM_VEC3F = (HashingStrategy)new ItemTransformVecStrategy(null);
    }
    
    private static final class ItemCameraTransformsStrategy implements HashingStrategy<ItemCameraTransforms>
    {
        public int computeHashCode(final ItemCameraTransforms object) {
            int hash = 1;
            for (final ItemTransformVec3f transform : ImmutableSet.of((Object)object.firstPerson, (Object)object.fixed, (Object)object.ground, (Object)object.gui, (Object)object.head, (Object)object.thirdPerson, (Object[])new ItemTransformVec3f[0])) {
                for (final Vector3f vector : ImmutableSet.of((Object)transform.rotation, (Object)transform.scale, (Object)transform.translation)) {
                    hash = ((hash * 31 + Float.floatToIntBits(vector.getX())) * 31 + Float.floatToIntBits(vector.getY())) * 31 + Float.floatToIntBits(vector.getZ());
                }
            }
            return hash;
        }
        
        public boolean equals(final ItemCameraTransforms o1, final ItemCameraTransforms o2) {
            if (o1 == null) {
                return o2 == null;
            }
            return Objects.equals(o1.firstPerson, o2.firstPerson) && Objects.equals(o1.fixed, o2.fixed) && Objects.equals(o1.ground, o2.ground) && Objects.equals(o1.gui, o2.gui) && Objects.equals(o1.head, o2.head) && Objects.equals(o1.thirdPerson, o2.thirdPerson);
        }
    }
    
    private static final class ItemTransformVecStrategy implements HashingStrategy<ItemTransformVec3f>
    {
        public int computeHashCode(final ItemTransformVec3f transform) {
            int hash = 1;
            for (final Vector3f vector : ImmutableSet.of((Object)transform.rotation, (Object)transform.scale, (Object)transform.translation)) {
                hash = ((hash * 31 + Float.floatToIntBits(vector.getX())) * 31 + Float.floatToIntBits(vector.getY())) * 31 + Float.floatToIntBits(vector.getZ());
            }
            return hash;
        }
        
        public boolean equals(final ItemTransformVec3f o1, final ItemTransformVec3f o2) {
            return Objects.equals(o1, o2);
        }
    }
    
    private static final class ObjectStrategy implements HashingStrategy
    {
        public int computeHashCode(final Object object) {
            return Objects.hashCode(object);
        }
        
        public boolean equals(final Object o1, final Object o2) {
            return Objects.equals(o1, o2);
        }
    }
    
    private static final class ByteArray implements HashingStrategy<byte[]>
    {
        public int computeHashCode(final byte[] object) {
            return Arrays.hashCode(object);
        }
        
        public boolean equals(final byte[] o1, final byte[] o2) {
            return Arrays.equals(o1, o2);
        }
    }
    
    private static final class IntArray implements HashingStrategy<int[]>
    {
        public int computeHashCode(final int[] object) {
            return Arrays.hashCode(object);
        }
        
        public boolean equals(final int[] o1, final int[] o2) {
            return Arrays.equals(o1, o2);
        }
    }
    
    private static final class FloatArray implements HashingStrategy<float[]>
    {
        public int computeHashCode(final float[] object) {
            return Arrays.hashCode(object);
        }
        
        public boolean equals(final float[] o1, final float[] o2) {
            return Arrays.equals(o1, o2);
        }
    }
    
    private static final class FloatArrayArray implements HashingStrategy<float[][]>
    {
        public int computeHashCode(final float[][] object) {
            int hash = 1;
            for (final float[] anObject : object) {
                hash = hash * 31 + Arrays.hashCode(anObject);
            }
            return hash;
        }
        
        public boolean equals(final float[][] o1, final float[][] o2) {
            if (o1 == null) {
                return o2 == null;
            }
            if (o1.length != o2.length) {
                return false;
            }
            for (int i = 0; i < o1.length; ++i) {
                if (!Arrays.equals(o1[i], o2[i])) {
                    return false;
                }
            }
            return true;
        }
    }
}
