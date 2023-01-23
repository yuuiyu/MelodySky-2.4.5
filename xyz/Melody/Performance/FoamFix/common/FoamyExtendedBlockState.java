//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import net.minecraftforge.common.property.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import com.google.common.base.*;
import com.google.common.collect.*;
import java.util.*;

public class FoamyExtendedBlockState extends FoamyBlockState implements IExtendedBlockState
{
    private final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties;
    
    public FoamyExtendedBlockState(final PropertyValueMapper owner, final Block block, final ImmutableMap<IProperty, Comparable> properties, final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties) {
        super(owner, block, (ImmutableMap)properties);
        this.unlistedProperties = unlistedProperties;
    }
    
    public FoamyExtendedBlockState(final PropertyValueMapper owner, final Block block, final ImmutableMap<IProperty, Comparable> properties, final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties, final int value) {
        super(owner, block, (ImmutableMap)properties);
        this.unlistedProperties = unlistedProperties;
        this.value = value;
    }
    
    public <T extends Comparable<T>, V extends T> IBlockState withProperty(final IProperty<T> property, final V propertyValue) {
        if (!this.getProperties().containsKey((Object)property)) {
            throw new IllegalArgumentException("Cannot set property " + property + " as it does not exist in " + this.getBlock().getBlockState());
        }
        if (!property.getAllowedValues().contains(propertyValue)) {
            throw new IllegalArgumentException("Cannot set property " + property + " to " + this.value + " on block " + Block.blockRegistry.getNameForObject((Object)this.getBlock()) + ", it is not an allowed value");
        }
        if (this.getProperties().get((Object)property) == propertyValue) {
            return (IBlockState)this;
        }
        final int newValue = this.owner.withPropertyValue(this.value, property, propertyValue);
        if (newValue == -1) {
            throw new IllegalArgumentException("Cannot set property " + property + " because FoamFix could not find a mapping for it! Please reproduce without FoamFix first!");
        }
        final IBlockState state = this.owner.getPropertyByValue(newValue);
        if (Iterables.all((Iterable)this.unlistedProperties.values(), Predicates.equalTo((Object)Optional.absent()))) {
            return state;
        }
        return (IBlockState)new FoamyExtendedBlockState(this.owner, this.getBlock(), (ImmutableMap<IProperty, Comparable>)state.getProperties(), this.unlistedProperties, newValue);
    }
    
    public <V> IExtendedBlockState withProperty(final IUnlistedProperty<V> property, final V value) {
        if (!this.unlistedProperties.containsKey((Object)property)) {
            throw new IllegalArgumentException("Cannot set unlisted property " + property + " as it does not exist in " + this.getBlock().getBlockState());
        }
        if (!property.isValid((Object)value)) {
            throw new IllegalArgumentException("Cannot set unlisted property " + property + " to " + value + " on block " + Block.blockRegistry.getNameForObject((Object)this.getBlock()) + ", it is not an allowed value");
        }
        final Map<IUnlistedProperty<?>, Optional<?>> newMap = new HashMap<IUnlistedProperty<?>, Optional<?>>((Map<? extends IUnlistedProperty<?>, ? extends Optional<?>>)this.unlistedProperties);
        newMap.put(property, (Optional<?>)Optional.fromNullable((Object)value));
        if (Iterables.all((Iterable)newMap.values(), Predicates.equalTo((Object)Optional.absent()))) {
            return (IExtendedBlockState)this.owner.getPropertyByValue(this.value);
        }
        return (IExtendedBlockState)new FoamyExtendedBlockState(this.owner, this.getBlock(), (ImmutableMap<IProperty, Comparable>)this.getProperties(), (ImmutableMap<IUnlistedProperty<?>, Optional<?>>)ImmutableMap.copyOf((Map)newMap), this.value);
    }
    
    public Collection<IUnlistedProperty<?>> getUnlistedNames() {
        return Collections.unmodifiableCollection((Collection<? extends IUnlistedProperty<?>>)this.unlistedProperties.keySet());
    }
    
    public <V> V getValue(final IUnlistedProperty<V> property) {
        if (!this.unlistedProperties.containsKey((Object)property)) {
            throw new IllegalArgumentException("Cannot get unlisted property " + property + " as it does not exist in " + this.getBlock().getBlockState());
        }
        return property.getType().cast(((Optional)this.unlistedProperties.get((Object)property)).orNull());
    }
    
    public ImmutableMap<IUnlistedProperty<?>, Optional<?>> getUnlistedProperties() {
        return this.unlistedProperties;
    }
    
    public IBlockState getClean() {
        return this.owner.getPropertyByValue(this.value);
    }
}
