//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.io.*;

class lllIIll implements FileFilter
{
    final /* synthetic */ String val$type;
    final /* synthetic */ JHTMLEditorFCKeditor this$0;
    
    lllIIll(final JHTMLEditorFCKeditor this$0, final String val$type) {
        this.this$0 = this$0;
        this.val$type = val$type;
    }
    
    @Override
    public boolean accept(final File pathname) {
        if (!pathname.isFile()) {
            return false;
        }
        if ("Image".equals(this.val$type)) {
            final String name = pathname.getName().toLowerCase();
            return name.endsWith(".bmp") || name.endsWith(".jpg") || name.endsWith(".gif") || name.endsWith(".png");
        }
        if ("Flash".equals(this.val$type)) {
            final String name = pathname.getName().toLowerCase();
            return name.endsWith(".swf");
        }
        return true;
    }
}
