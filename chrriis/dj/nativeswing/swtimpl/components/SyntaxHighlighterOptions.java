//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package chrriis.dj.nativeswing.swtimpl.components;

public class SyntaxHighlighterOptions
{
    private boolean isGutterVisible;
    private boolean isControlAreaVisible;
    private boolean isBlockCollapsed;
    private int firstLineNumber;
    private boolean isShowingColumns;
    
    public SyntaxHighlighterOptions() {
        this.isGutterVisible = true;
        this.isControlAreaVisible = true;
        this.firstLineNumber = 1;
    }
    
    public void setGutterVisible(final boolean isGutterVisible) {
        this.isGutterVisible = isGutterVisible;
    }
    
    public boolean isGutterVisible() {
        return this.isGutterVisible;
    }
    
    public void setControlAreaVisible(final boolean isControlAreaVisible) {
        this.isControlAreaVisible = isControlAreaVisible;
    }
    
    public boolean isControlAreaVisible() {
        return this.isControlAreaVisible;
    }
    
    public void setFirstLineNumber(final int firstLineNumber) {
        this.firstLineNumber = firstLineNumber;
    }
    
    public int getFirstLineNumber() {
        return this.firstLineNumber;
    }
    
    String getOptionsString() {
        final StringBuilder sb = new StringBuilder();
        if (!this.isGutterVisible) {
            sb.append(":nogutter");
        }
        if (!this.isControlAreaVisible) {
            sb.append(":nocontrols");
        }
        if (this.isBlockCollapsed) {
            sb.append(":collapse");
        }
        if (this.firstLineNumber != 1) {
            sb.append(":firstline[" + this.firstLineNumber + "]");
        }
        if (this.isShowingColumns) {
            sb.append(":showcolumns");
        }
        return sb.toString();
    }
}
