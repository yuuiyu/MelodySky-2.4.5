//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraftforge.common.property.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import net.minecraft.block.state.*;

public class FoamyExtendedBlockStateContainer extends ExtendedBlockState
{
    public FoamyExtendedBlockStateContainer(final Block blockIn, final IProperty<?>[] properties, final IUnlistedProperty<?>[] unlistedProperties) {
        super(blockIn, (IProperty[])properties, (IUnlistedProperty[])unlistedProperties);
    }
    
    protected BlockState.StateImplementation createState(final Block block, final ImmutableMap<IProperty, Comparable> properties, final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties) {
        if (unlistedProperties == null || unlistedProperties.isEmpty()) {
            return (BlockState.StateImplementation)new FoamyBlockState(PropertyValueMapper.getOrCreate((BlockState)this), block, (ImmutableMap)properties);
        }
        return (BlockState.StateImplementation)new FoamyExtendedBlockState(PropertyValueMapper.getOrCreate((BlockState)this), block, (ImmutableMap)properties, (ImmutableMap)unlistedProperties);
    }
}
