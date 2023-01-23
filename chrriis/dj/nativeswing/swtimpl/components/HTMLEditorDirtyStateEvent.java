//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class HTMLEditorDirtyStateEvent extends HTMLEditorEvent
{
    private boolean isDirty;
    
    public HTMLEditorDirtyStateEvent(final JHTMLEditor htmlEditor, final boolean isDirty) {
        super(htmlEditor);
        this.isDirty = isDirty;
    }
    
    public boolean isDirty() {
        return this.isDirty;
    }
}
