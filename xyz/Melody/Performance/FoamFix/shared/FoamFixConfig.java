//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.shared;

import net.minecraftforge.common.config.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.versioning.*;
import java.io.*;

public class FoamFixConfig
{
    public boolean lwWeakenResourceCache;
    public boolean clDeduplicate;
    public boolean clCleanRedundantModelRegistry;
    public boolean geBlacklistLibraryTransformers;
    public boolean geBlockPosPatch;
    public boolean geDynamicRegistrySizeScaling;
    public boolean geSmallPropertyStorage;
    public boolean geReplaceSimpleName;
    public int clDeduplicateRecursionLevel;
    private Configuration config;
    public boolean geSmallLightingOptimize;
    public boolean clDynamicItemModels;
    
    public FoamFixConfig() {
        this.geSmallLightingOptimize = false;
    }
    
    private boolean getBoolean(final String name, final String category, final boolean defaultValue, final String description) {
        return this.config.getBoolean(name, category, defaultValue, description);
    }
    
    private boolean getBoolean(final String name, final String category, final boolean defaultValue, final String description, final String forgeVersionRange) {
        final VersionRange range = VersionParser.parseRange(forgeVersionRange);
        final DefaultArtifactVersion requiredVersion = new DefaultArtifactVersion("Forge", range);
        return requiredVersion.containsVersion((ArtifactVersion)new DefaultArtifactVersion("Forge", ForgeVersion.getVersion())) && this.getBoolean(name, category, defaultValue, description);
    }
    
    public void init(final File file, final boolean isCoremod) {
        if (this.config == null) {
            this.config = new Configuration(file);
            this.lwWeakenResourceCache = this.getBoolean("weakenResourceCache", "launchwrapper", true, "Weaken LaunchWrapper's byte[] resource cache to make it cleanuppable by the GC. Safe.");
            this.clDeduplicate = this.getBoolean("deduplicateModels", "client", true, "Enable deduplication of redundant objects in memory.");
            this.clDeduplicateRecursionLevel = this.config.getInt("deduplicateModelsMaxRecursion", "client", 6, 1, Integer.MAX_VALUE, "The maximum amount of levels of recursion for the deduplication process. Smaller values will deduplicate less data, but make the process run faster.");
            this.clCleanRedundantModelRegistry = this.getBoolean("clearDuplicateModelRegistry", "client", true, "Clears the baked models generated in the first pass *before* entering the second pass, instead of *after*. While this doesn't reduce memory usage in-game, it does reduce it noticeably during loading.");
            this.geDynamicRegistrySizeScaling = this.getBoolean("dynamicRegistrySizeScaling", "general", true, "Makes large FML registries scale their availability BitSets dynamically, saving ~48MB of RAM.", "(,13.19.1.2190)");
            if (isCoremod) {
                this.geBlacklistLibraryTransformers = this.getBoolean("blacklistLibraryTransformers", "coremod", true, "Stops certain non-Minecraft-related libraries from being ASM transformed. You shouldn't be transforming those anyway.");
                this.geSmallPropertyStorage = this.getBoolean("smallPropertyStorage", "coremod", true, "Replaces the default BlockState/ExtendedBlockState implementations with a far more memory-efficient variant.");
                this.geBlockPosPatch = this.getBoolean("optimizedBlockPos", "coremod", true, "Optimizes BlockPos mutable/immutable getters to run on the same variables, letting them be inlined and thus theoretically increasing performance.");
                this.geReplaceSimpleName = this.getBoolean("replaceWorldSimpleName", "coremod", true, "Replaces Class.getSimpleName in World.updateEntities with getName. As Class.getName's output is cached, unlike getSimpleName, this should provide a small performance boost.");
                this.clDynamicItemModels = this.getBoolean("dynamicItemModels", "coremod", true, "Make 3D forms of items be rendered dynamically and cached when necessary.");
            }
            this.config.save();
        }
    }
}
