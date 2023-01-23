//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import com.google.common.collect.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.*;
import net.minecraft.block.state.*;
import java.util.*;

public class FoamyBlockState extends BlockState.StateImplementation
{
    protected final PropertyValueMapper owner;
    protected final ImmutableMap<IProperty, Comparable> properties;
    protected int value;
    
    public FoamyBlockState(final PropertyValueMapper owner, final Block blockIn, final ImmutableMap<IProperty, Comparable> propertiesIn) {
        super(blockIn, (ImmutableMap)propertiesIn);
        this.owner = owner;
        this.properties = propertiesIn;
    }
    
    public <T extends Comparable<T>, V extends T> IBlockState withProperty(final IProperty<T> property, final V value) {
        final Comparable<?> comparable = (Comparable<?>)this.properties.get((Object)property);
        if (comparable == null) {
            throw new IllegalArgumentException("Cannot set property " + property + " as it does not exist in " + this.getBlock().getBlockState());
        }
        if (comparable == value) {
            return (IBlockState)this;
        }
        final IBlockState state = this.owner.withProperty(this.value, property, value);
        if (state == null) {
            throw new IllegalArgumentException("Cannot set property " + property + " to " + value + " on block " + Block.blockRegistry.getNameForObject((Object)this.getBlock()) + ", it is not an allowed value");
        }
        return state;
    }
    
    public void buildPropertyValueTable(final Map<Map<IProperty, Comparable>, BlockState.StateImplementation> map) {
        this.value = this.owner.generateValue((IBlockState)this);
    }
}
