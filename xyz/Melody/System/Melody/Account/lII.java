//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.System.Melody.Account;

class lII
{
    static {
        $SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum = new int[AccountEnum.values().length];
        try {
            lII.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.OFFLINE.ordinal()] = 1;
        }
        catch (NoSuchFieldError noSuchFieldError) {}
        try {
            lII.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.MICROSOFT.ordinal()] = 2;
        }
        catch (NoSuchFieldError noSuchFieldError2) {}
        try {
            lII.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.ORIGINAL.ordinal()] = 3;
        }
        catch (NoSuchFieldError noSuchFieldError3) {}
        try {
            lII.$SwitchMap$xyz$Melody$System$Melody$Account$AccountEnum[AccountEnum.XBLTOKEN.ordinal()] = 4;
        }
        catch (NoSuchFieldError noSuchFieldError4) {}
    }
}
