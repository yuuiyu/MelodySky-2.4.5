//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.io.*;

class ImageDataLoader
{
    public static ImageData[] load(final InputStream stream) {
        return new ImageLoader().load(stream);
    }
    
    public static ImageData[] load(final String filename) {
        return new ImageLoader().load(filename);
    }
}
