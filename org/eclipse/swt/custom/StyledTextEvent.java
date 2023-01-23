//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.widgets.*;
import org.eclipse.swt.graphics.*;

class StyledTextEvent extends Event
{
    int[] ranges;
    StyleRange[] styles;
    int alignment;
    int indent;
    int verticalIndent;
    int wrapIndent;
    boolean justify;
    Bullet bullet;
    int bulletIndex;
    int[] tabStops;
    Color lineBackground;
    int replaceCharCount;
    int newCharCount;
    int replaceLineCount;
    int newLineCount;
    int x;
    int y;
    int ascent;
    int descent;
    StyleRange style;
    
    StyledTextEvent(final StyledTextContent content) {
        this.data = content;
    }
}
