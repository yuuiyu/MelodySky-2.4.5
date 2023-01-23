//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

final class lll implements TransformerFunction
{
    @Override
    public byte[] transform(final byte[] data, final String transformedName) {
        return FoamFixTransformer.spliceClasses(data, "xyz.Melody.FoamFix.common.FoamyBlockStateContainer", transformedName, new String[] { "createState", "createState" });
    }
}
