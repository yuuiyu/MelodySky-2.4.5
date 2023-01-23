//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.custom;

import org.eclipse.swt.accessibility.*;

class lIIIIIl implements AccessibleEditableTextListener
{
    final /* synthetic */ StyledText this$0;
    
    lIIIIIl(final StyledText this$0) {
        this.this$0 = this$0;
    }
    
    public void setTextAttributes(final AccessibleTextAttributeEvent e) {
        e.result = "OK";
    }
    
    public void replaceText(final AccessibleEditableTextEvent e) {
        final StyledText st = this.this$0;
        st.replaceTextRange(e.start, e.end - e.start, e.string);
        e.result = "OK";
    }
    
    public void pasteText(final AccessibleEditableTextEvent e) {
        final StyledText st = this.this$0;
        st.setSelection(e.start);
        st.paste();
        e.result = "OK";
    }
    
    public void cutText(final AccessibleEditableTextEvent e) {
        final StyledText st = this.this$0;
        st.setSelection(e.start, e.end);
        st.cut();
        e.result = "OK";
    }
    
    public void copyText(final AccessibleEditableTextEvent e) {
        final StyledText st = this.this$0;
        st.setSelection(e.start, e.end);
        st.copy();
        e.result = "OK";
    }
}
