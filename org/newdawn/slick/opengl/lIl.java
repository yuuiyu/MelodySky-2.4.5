//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.opengl;

import java.security.*;
import org.newdawn.slick.util.*;

final class lIl implements PrivilegedAction
{
    @Override
    public Object run() {
        final String val = System.getProperty("org.newdawn.slick.pngloader");
        if ("false".equalsIgnoreCase(val)) {
            ImageDataFactory.access$002(false);
        }
        Log.info("Use Java PNG Loader = " + ImageDataFactory.access$000());
        return null;
    }
}
