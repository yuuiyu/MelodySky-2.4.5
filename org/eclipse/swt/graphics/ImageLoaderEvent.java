//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.graphics;

import java.util.*;

public class ImageLoaderEvent extends EventObject
{
    public ImageData imageData;
    public int incrementCount;
    public boolean endOfImage;
    static final long serialVersionUID = 3257284738325558065L;
    
    public ImageLoaderEvent(final ImageLoader source, final ImageData imageData, final int incrementCount, final boolean endOfImage) {
        super(source);
        this.imageData = imageData;
        this.incrementCount = incrementCount;
        this.endOfImage = endOfImage;
    }
    
    @Override
    public String toString() {
        return "ImageLoaderEvent {source=" + this.source + " imageData=" + this.imageData + " incrementCount=" + this.incrementCount + " endOfImage=" + this.endOfImage;
    }
}
