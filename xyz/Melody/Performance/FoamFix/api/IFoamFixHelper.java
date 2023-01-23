//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.api;

import net.minecraft.block.*;
import net.minecraft.block.properties.*;
import net.minecraft.block.state.*;
import net.minecraftforge.common.property.*;

public interface IFoamFixHelper
{
    BlockState createBlockState(final Block p0, final IProperty<?>... p1);
    
    BlockState createExtendedBlockState(final Block p0, final IProperty<?>[] p1, final IUnlistedProperty<?>[] p2);
    
    public static class Default implements IFoamFixHelper
    {
        @Override
        public BlockState createBlockState(final Block block, final IProperty<?>... properties) {
            return new BlockState(block, (IProperty[])properties);
        }
        
        @Override
        public BlockState createExtendedBlockState(final Block block, final IProperty<?>[] properties, final IUnlistedProperty<?>[] unlistedProperties) {
            return (BlockState)new ExtendedBlockState(block, (IProperty[])properties, (IUnlistedProperty[])unlistedProperties);
        }
    }
}
