//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class HTMLEditorSaveEvent extends HTMLEditorEvent
{
    private String text;
    
    public HTMLEditorSaveEvent(final JHTMLEditor htmlEditor, final String text) {
        super(htmlEditor);
        this.text = text;
    }
    
    public String getText() {
        return this.text;
    }
}
