//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class llIIl extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    llIIl(final Accessible this$0, final int[] argCounts) {
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
        return this.this$0.get_cellAt((int)args[0], (int)args[1], args[2]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.get_caption(args[0]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.get_columnDescription((int)args[0], args[1]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.get_nColumns(args[0]);
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.get_nRows(args[0]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.get_nSelectedCells(args[0]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.get_nSelectedColumns(args[0]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.get_nSelectedRows(args[0]);
    }
    
    @Override
    public long method11(final long[] args) {
        return this.this$0.get_rowDescription((int)args[0], args[1]);
    }
    
    @Override
    public long method12(final long[] args) {
        return this.this$0.get_selectedCells(args[0], args[1]);
    }
    
    @Override
    public long method13(final long[] args) {
        return this.this$0.get_selectedColumns(args[0], args[1]);
    }
    
    @Override
    public long method14(final long[] args) {
        return this.this$0.get_selectedRows(args[0], args[1]);
    }
    
    @Override
    public long method15(final long[] args) {
        return this.this$0.get_summary(args[0]);
    }
    
    @Override
    public long method16(final long[] args) {
        return this.this$0.get_isColumnSelected((int)args[0], args[1]);
    }
    
    @Override
    public long method17(final long[] args) {
        return this.this$0.get_isRowSelected((int)args[0], args[1]);
    }
    
    @Override
    public long method18(final long[] args) {
        return this.this$0.selectRow((int)args[0]);
    }
    
    @Override
    public long method19(final long[] args) {
        return this.this$0.selectColumn((int)args[0]);
    }
    
    @Override
    public long method20(final long[] args) {
        return this.this$0.unselectRow((int)args[0]);
    }
    
    @Override
    public long method21(final long[] args) {
        return this.this$0.unselectColumn((int)args[0]);
    }
    
    @Override
    public long method22(final long[] args) {
        return this.this$0.get_modelChange(args[0]);
    }
}
