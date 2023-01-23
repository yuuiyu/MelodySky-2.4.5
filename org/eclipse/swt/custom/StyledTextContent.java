//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

public interface StyledTextContent
{
    void addTextChangeListener(final TextChangeListener p0);
    
    int getCharCount();
    
    String getLine(final int p0);
    
    int getLineAtOffset(final int p0);
    
    int getLineCount();
    
    String getLineDelimiter();
    
    int getOffsetAtLine(final int p0);
    
    String getTextRange(final int p0, final int p1);
    
    void removeTextChangeListener(final TextChangeListener p0);
    
    void replaceTextRange(final int p0, final int p1, final String p2);
    
    void setText(final String p0);
}
