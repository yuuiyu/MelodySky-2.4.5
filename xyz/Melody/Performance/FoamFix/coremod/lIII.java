//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package xyz.Melody.Performance.FoamFix.coremod;

import org.objectweb.asm.*;

final class lIII implements TransformerFunction
{
    @Override
    public byte[] transform(final byte[] data, final String transformedName) {
        final ClassReader reader = new ClassReader(data);
        final ClassWriter writer = new ClassWriter(0);
        reader.accept(new FoamFixReplaceClassSimpleName(new String[] { "updateEntities", "updateEntities" }).getClassVisitor(327680, (ClassVisitor)writer), 0);
        return writer.toByteArray();
    }
}
