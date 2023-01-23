//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Utils.other;

import net.minecraft.client.*;

public class StringUtil
{
    public static Minecraft mc;
    
    public static String removeFormatting(final String input) {
        return input.replaceAll("§[0-9a-fk-or]", "");
    }
    
    static {
        StringUtil.mc = Minecraft.getMinecraft();
    }
}
