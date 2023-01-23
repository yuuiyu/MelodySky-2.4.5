//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import com.google.common.eventbus.*;
import net.minecraftforge.fml.common.*;
import java.util.*;
import com.google.common.collect.*;

public class FoamFixCoreContainer extends DummyModContainer
{
    private static final ModMetadata md;
    
    public FoamFixCoreContainer() {
        super(FoamFixCoreContainer.md);
    }
    
    public boolean registerBus(final EventBus bus, final LoadController controller) {
        return true;
    }
    
    public List<String> getOwnedPackages() {
        return (List<String>)ImmutableList.of((Object)"xyz.Melody.FoamFix.coremod");
    }
    
    static {
        md = new ModMetadata();
        FoamFixCoreContainer.md.modId = "foamfixcore";
        FoamFixCoreContainer.md.name = "FoamFixCore";
        FoamFixCoreContainer.md.description = "I'm actually just an optional part of FoamFix, available exclusively as part of the Anarchy version!";
        FoamFixCoreContainer.md.authorList = (List)ImmutableList.of((Object)"asie");
        FoamFixCoreContainer.md.version = "7.7.4";
    }
}
