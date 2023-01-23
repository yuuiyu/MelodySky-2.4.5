//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class lIIll extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    lIIll(final Accessible this$0, final int[] argCounts) {
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
        return this.this$0.get_nActions(args[0]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.doAction((int)args[0]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.get_description((int)args[0], args[1]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.get_keyBinding((int)args[0], (int)args[1], args[2], args[3]);
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.get_name((int)args[0], args[1]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.get_localizedName((int)args[0], args[1]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.get_anchor((int)args[0], args[1]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.get_anchorTarget((int)args[0], args[1]);
    }
    
    @Override
    public long method11(final long[] args) {
        return this.this$0.get_startIndex(args[0]);
    }
    
    @Override
    public long method12(final long[] args) {
        return this.this$0.get_endIndex(args[0]);
    }
    
    @Override
    public long method13(final long[] args) {
        return this.this$0.get_valid(args[0]);
    }
}
