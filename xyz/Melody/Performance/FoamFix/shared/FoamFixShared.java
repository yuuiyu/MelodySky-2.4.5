//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.shared;

public class FoamFixShared
{
    public static final FoamFixConfig config;
    public static boolean coremodEnabled;
    public static int ramSaved;
    
    public static boolean hasOptifine() {
        try {
            return Class.forName("optifine.OptiFineTweaker") != null;
        }
        catch (ClassNotFoundException e) {
            return false;
        }
    }
    
    static {
        config = new FoamFixConfig();
        FoamFixShared.coremodEnabled = false;
        FoamFixShared.ramSaved = 0;
    }
}
