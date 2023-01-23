//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

public interface AccessibleTextExtendedListener extends AccessibleTextListener
{
    void addSelection(final AccessibleTextEvent p0);
    
    void getCharacterCount(final AccessibleTextEvent p0);
    
    void getHyperlinkCount(final AccessibleTextEvent p0);
    
    void getHyperlink(final AccessibleTextEvent p0);
    
    void getHyperlinkIndex(final AccessibleTextEvent p0);
    
    void getOffsetAtPoint(final AccessibleTextEvent p0);
    
    void getRanges(final AccessibleTextEvent p0);
    
    void getSelection(final AccessibleTextEvent p0);
    
    void getSelectionCount(final AccessibleTextEvent p0);
    
    void getText(final AccessibleTextEvent p0);
    
    void getTextBounds(final AccessibleTextEvent p0);
    
    void getVisibleRanges(final AccessibleTextEvent p0);
    
    void removeSelection(final AccessibleTextEvent p0);
    
    void scrollText(final AccessibleTextEvent p0);
    
    void setCaretOffset(final AccessibleTextEvent p0);
    
    void setSelection(final AccessibleTextEvent p0);
}
