//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.accessibility;

import org.eclipse.swt.internal.ole.win32.*;

class lIIlI extends COMObject
{
    final /* synthetic */ Accessible this$0;
    
    lIIlI(final Accessible this$0, final int[] argCounts) {
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
        return this.this$0.Next((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.Skip((int)args[0]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.Reset();
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.Clone(args[0]);
    }
}
