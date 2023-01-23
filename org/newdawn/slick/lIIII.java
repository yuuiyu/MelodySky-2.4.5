//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.security.*;
import org.lwjgl.opengl.*;
import org.newdawn.slick.util.*;

final class lIIII implements PrivilegedAction
{
    @Override
    public Object run() {
        try {
            Display.getDisplayMode();
        }
        catch (Exception e) {
            Log.error(e);
        }
        return null;
    }
}
