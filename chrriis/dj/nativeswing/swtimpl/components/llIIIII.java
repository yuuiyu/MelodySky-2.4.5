//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.io.*;

class llIIIII implements FileFilter
{
    final /* synthetic */ JHTMLEditorCKeditor this$0;
    
    llIIIII(final JHTMLEditorCKeditor this$0) {
        this.this$0 = this$0;
    }
    
    @Override
    public boolean accept(final File pathname) {
        return !pathname.isFile() && !pathname.isHidden() && pathname.listFiles() != null;
    }
}
