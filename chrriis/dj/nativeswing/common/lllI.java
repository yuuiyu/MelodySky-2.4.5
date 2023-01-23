//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.util.*;

final class lllI implements Comparator<Thread>
{
    @Override
    public int compare(final Thread o1, final Thread o2) {
        return o1.getName().compareToIgnoreCase(o2.getName());
    }
}
