//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\�½��ļ��� (2)"!

//Decompiled by Procyon!

package org.newdawn.slick.util;

import java.security.*;

final class l implements PrivilegedAction
{
    @Override
    public Object run() {
        final String val = System.getProperty("org.newdawn.slick.forceVerboseLog");
        if (val != null && val.equalsIgnoreCase("true")) {
            Log.setForcedVerboseOn();
        }
        return null;
    }
}
