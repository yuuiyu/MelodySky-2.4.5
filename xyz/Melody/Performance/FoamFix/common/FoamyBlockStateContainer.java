//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import net.minecraft.block.state.*;
import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import com.google.common.collect.*;
import net.minecraftforge.common.property.*;
import com.google.common.base.*;

public class FoamyBlockStateContainer extends BlockState
{
    public FoamyBlockStateContainer(final Block blockIn, final IProperty<?>... properties) {
        super(blockIn, (IProperty[])properties);
    }
    
    protected BlockState.StateImplementation createState(final Block block, final ImmutableMap<IProperty, Comparable> properties, final ImmutableMap<IUnlistedProperty<?>, Optional<?>> unlistedProperties) {
        return (BlockState.StateImplementation)new FoamyBlockState(PropertyValueMapper.getOrCreate(this), block, (ImmutableMap)properties);
    }
}
