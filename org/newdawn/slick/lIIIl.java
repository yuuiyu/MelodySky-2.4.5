//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.newdawn.slick;

import java.util.*;
import org.newdawn.slick.font.*;

final class lIIIl implements Comparator
{
    @Override
    public int compare(final Object o1, final Object o2) {
        return ((Glyph)o1).getHeight() - ((Glyph)o2).getHeight();
    }
}
