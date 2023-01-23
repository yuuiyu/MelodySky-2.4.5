//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import net.minecraftforge.fml.relauncher.*;
import java.util.*;
import xyz.Melody.Performance.FoamFix.shared.*;
import java.io.*;
import net.minecraft.launchwrapper.*;

@IFMLLoadingPlugin.Name("Do not report to Forge! Remove FoamFixAPI (or replace with FoamFixAPI-Lawful) and try again.")
@IFMLLoadingPlugin.SortingIndex(1001)
@IFMLLoadingPlugin.TransformerExclusions({ "xyz.Melody.FoamFix" })
public class FoamFixCore implements IFMLLoadingPlugin
{
    public String[] getASMTransformerClass() {
        return new String[] { "xyz.Melody.FoamFix.coremod.FoamFixTransformer" };
    }
    
    public String getModContainerClass() {
        return "xyz.Melody.FoamFix.coremod.FoamFixCoreContainer";
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> data) {
        FoamFixShared.coremodEnabled = true;
        FoamFixShared.config.init(new File(new File("config"), "foamfix.cfg"), true);
        if (FoamFixShared.config.geBlacklistLibraryTransformers) {
            final LaunchClassLoader classLoader = (LaunchClassLoader)this.getClass().getClassLoader();
            classLoader.addTransformerExclusion("com.ibm.icu.");
            classLoader.addTransformerExclusion("com.sun.");
            classLoader.addTransformerExclusion("gnu.trove.");
            classLoader.addTransformerExclusion("io.netty.");
            classLoader.addTransformerExclusion("it.unimi.dsi.fastutil.");
            classLoader.addTransformerExclusion("joptsimple.");
            classLoader.addTransformerExclusion("org.apache.");
            classLoader.addTransformerExclusion("oshi.");
            classLoader.addTransformerExclusion("scala.");
        }
        FoamFixTransformer.init();
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
}
