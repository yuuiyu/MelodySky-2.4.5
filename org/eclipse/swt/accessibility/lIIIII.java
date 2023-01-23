//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class lIIIII extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    lIIIII(final Accessible this$0, final int[] argCounts) {
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
        return this.this$0.copyText((int)args[0], (int)args[1]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.deleteText((int)args[0], (int)args[1]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.insertText((int)args[0], args[1]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.cutText((int)args[0], (int)args[1]);
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.pasteText((int)args[0]);
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.replaceText((int)args[0], (int)args[1], args[2]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.setAttributes((int)args[0], (int)args[1], args[2]);
    }
}
