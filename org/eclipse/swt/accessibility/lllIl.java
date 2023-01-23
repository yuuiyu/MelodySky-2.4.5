//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class lllIl extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    lllIl(final Accessible this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    @Override
    public long method0(final long[] args) {
        return this.this$0.QueryInterface(args[0], args[1]);
    }
    
    @Override
    public long method1(final long[] args) {
        return this.this$0.AddRef();
    }
    
    @Override
    public long method2(final long[] args) {
        return this.this$0.Release();
    }
    
    @Override
    public long method3(final long[] args) {
        return this.this$0.addSelection((int)args[0], (int)args[1]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.get_attributes((int)args[0], args[1], args[2], args[3]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.get_caretOffset(args[0]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.get_characterExtents((int)args[0], (int)args[1], args[2], args[3], args[4], args[5]);
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.get_nSelections(args[0]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.get_offsetAtPoint((int)args[0], (int)args[1], (int)args[2], args[3]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.get_selection((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.get_text((int)args[0], (int)args[1], args[2]);
    }
    
    @Override
    public long method11(final long[] args) {
        return this.this$0.get_textBeforeOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
    }
    
    @Override
    public long method12(final long[] args) {
        return this.this$0.get_textAfterOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
    }
    
    @Override
    public long method13(final long[] args) {
        return this.this$0.get_textAtOffset((int)args[0], (int)args[1], args[2], args[3], args[4]);
    }
    
    @Override
    public long method14(final long[] args) {
        return this.this$0.removeSelection((int)args[0]);
    }
    
    @Override
    public long method15(final long[] args) {
        return this.this$0.setCaretOffset((int)args[0]);
    }
    
    @Override
    public long method16(final long[] args) {
        return this.this$0.setSelection((int)args[0], (int)args[1], (int)args[2]);
    }
    
    @Override
    public long method17(final long[] args) {
        return this.this$0.get_nCharacters(args[0]);
    }
    
    @Override
    public long method18(final long[] args) {
        return this.this$0.scrollSubstringTo((int)args[0], (int)args[1], (int)args[2]);
    }
    
    @Override
    public long method19(final long[] args) {
        return this.this$0.scrollSubstringToPoint((int)args[0], (int)args[1], (int)args[2], (int)args[3], (int)args[4]);
    }
    
    @Override
    public long method20(final long[] args) {
        return this.this$0.get_newText(args[0]);
    }
    
    @Override
    public long method21(final long[] args) {
        return this.this$0.get_oldText(args[0]);
    }
    
    @Override
    public long method22(final long[] args) {
        return this.this$0.get_nHyperlinks(args[0]);
    }
    
    @Override
    public long method23(final long[] args) {
        return this.this$0.get_hyperlink((int)args[0], args[1]);
    }
    
    @Override
    public long method24(final long[] args) {
        return this.this$0.get_hyperlinkIndex((int)args[0], args[1]);
    }
}
