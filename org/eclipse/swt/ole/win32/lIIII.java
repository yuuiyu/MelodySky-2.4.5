//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.ole.win32;

import org.eclipse.swt.internal.ole.win32.*;

class lIIII extends COMObject
{
    final /* synthetic */ OleClientSite this$0;
    
    lIIII(final OleClientSite this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    public long method0(final long[] args) {
        return this.this$0.QueryInterface(args[0], args[1]);
    }
    
    public long method1(final long[] args) {
        return this.this$0.AddRef();
    }
    
    public long method2(final long[] args) {
        return this.this$0.Release();
    }
    
    public long method3(final long[] args) {
        return this.this$0.ActivateMe(args[0]);
    }
}
