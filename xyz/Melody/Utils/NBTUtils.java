//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils;

import net.minecraft.item.*;
import net.minecraft.nbt.*;

public class NBTUtils
{
    public static NBTTagCompound getExtraAttributes(final ItemStack item) {
        if (item == null || !item.hasTagCompound()) {
            return null;
        }
        return item.getSubCompound("ExtraAttributes", false);
    }
}
