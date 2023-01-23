//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.events.*;

public class TextChangingEvent extends TypedEvent
{
    public int start;
    public String newText;
    public int replaceCharCount;
    public int newCharCount;
    public int replaceLineCount;
    public int newLineCount;
    static final long serialVersionUID = 3257290210114352439L;
    
    public TextChangingEvent(final StyledTextContent source) {
        super(source);
    }
    
    TextChangingEvent(final StyledTextContent source, final StyledTextEvent e) {
        super(source);
        this.start = e.start;
        this.replaceCharCount = e.replaceCharCount;
        this.newCharCount = e.newCharCount;
        this.replaceLineCount = e.replaceLineCount;
        this.newLineCount = e.newLineCount;
        this.newText = e.text;
    }
}
