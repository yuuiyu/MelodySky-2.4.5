//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.common;

import java.io.*;

enum llll
{
    llll(final String x0, final int x2, final String name, final Type type) {
    }
    
    @Override
    public String get() {
        String value = super.get();
        if (value == null) {
            return null;
        }
        if (!value.endsWith(File.separator)) {
            value += File.separator;
        }
        try {
            new File(value).mkdirs();
        }
        catch (SecurityException ex) {}
        return value;
    }
}
