//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.module.modules.QOL.Dungeons;

import net.minecraft.item.*;

class lI
{
    static {
        $SwitchMap$net$minecraft$item$EnumDyeColor = new int[EnumDyeColor.values().length];
        try {
            lI.$SwitchMap$net$minecraft$item$EnumDyeColor[EnumDyeColor.PURPLE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            lI.$SwitchMap$net$minecraft$item$EnumDyeColor[EnumDyeColor.LIME.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        $SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType = new int[AutoTerminals.TerminalType.values().length];
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.MAZE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.NUMBERS.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.CORRECT_ALL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError5) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.LETTER.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError6) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.COLOR.ordinal()] = 5;
        }
        catch (NoSuchFieldError noSuchFieldError7) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.CHANGEATSC.ordinal()] = 6;
        }
        catch (NoSuchFieldError noSuchFieldError8) {}
        try {
            lI.$SwitchMap$xyz$Melody$module$modules$QOL$Dungeons$AutoTerminals$TerminalType[AutoTerminals.TerminalType.TIMING.ordinal()] = 7;
        }
        catch (NoSuchFieldError noSuchFieldError9) {}
    }
}
