//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.io.*;

public class TextureLoader
{
    public static Texture getTexture(final String format, final InputStream in) throws IOException {
        return getTexture(format, in, false, 9729);
    }
    
    public static Texture getTexture(final String format, final InputStream in, final boolean flipped) throws IOException {
        return getTexture(format, in, flipped, 9729);
    }
    
    public static Texture getTexture(final String format, final InputStream in, final int filter) throws IOException {
        return getTexture(format, in, false, filter);
    }
    
    public static Texture getTexture(final String format, final InputStream in, final boolean flipped, final int filter) throws IOException {
        return InternalTextureLoader.get().getTexture(in, in.toString() + "." + format, flipped, filter);
    }
}
