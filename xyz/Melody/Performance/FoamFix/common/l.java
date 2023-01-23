//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.common;

import java.util.*;
import net.minecraft.block.properties.*;

final class l implements Comparator<IProperty>
{
    @Override
    public int compare(final IProperty first, final IProperty second) {
        final int diff1 = PropertyValueMapper.getPropertyEntry(first).bitSize - first.getAllowedValues().size();
        final int diff2 = PropertyValueMapper.getPropertyEntry(second).bitSize - second.getAllowedValues().size();
        return diff1 - diff2;
    }
}
