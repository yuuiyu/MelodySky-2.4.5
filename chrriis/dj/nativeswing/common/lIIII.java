//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.util.*;
import java.net.*;

final class lIIII implements Comparator<InetAddress>
{
    @Override
    public int compare(final InetAddress o1, final InetAddress o2) {
        if (o1.isLoopbackAddress() != o2.isLoopbackAddress() && o1.isSiteLocalAddress() != o2.isSiteLocalAddress()) {
            if (o1.isLoopbackAddress()) {
                return -1;
            }
            if (o2.isLoopbackAddress()) {
                return 1;
            }
            if (o1.isSiteLocalAddress()) {
                return -1;
            }
            if (o2.isSiteLocalAddress()) {
                return 1;
            }
        }
        return o1.getHostAddress().compareTo(o2.getHostAddress());
    }
}
