//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

import java.util.*;

public class HTMLEditorEvent extends EventObject
{
    private JHTMLEditor htmlEditor;
    
    public HTMLEditorEvent(final JHTMLEditor htmlEditor) {
        super(htmlEditor);
        this.htmlEditor = htmlEditor;
    }
    
    public JHTMLEditor getHTMLEditor() {
        return this.htmlEditor;
    }
}
