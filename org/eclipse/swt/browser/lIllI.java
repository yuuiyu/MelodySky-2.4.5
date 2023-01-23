//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.*;

class lIllI extends COMObject
{
    final /* synthetic */ WebSite this$0;
    
    lIllI(final WebSite this$0, final int[] argCounts) {
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
        return this.this$0.QueryStatus(args[0], (int)args[1], args[2], args[3]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.Exec(args[0], (int)args[1], (int)args[2], args[3], args[4]);
    }
}
