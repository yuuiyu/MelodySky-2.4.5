//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

class llI
{
    static {
        $SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum = new int[AccountEnum.values().length];
        try {
            llI.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.OFFLINE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            llI.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.MICROSOFT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            llI.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.ORIGINAL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            llI.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.XBLTOKEN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
    }
}
