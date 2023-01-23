//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.*;

class lII extends COMObject
{
    final /* synthetic */ Clipboard this$0;
    
    lII(final Clipboard this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    @Override
    public long method0(final long[] args) {
        return Clipboard.access$000(this.this$0, args[0], args[1]);
    }
    
    @Override
    public long method1(final long[] args) {
        return Clipboard.access$100(this.this$0);
    }
    
    @Override
    public long method2(final long[] args) {
        return Clipboard.access$200(this.this$0);
    }
    
    @Override
    public long method3(final long[] args) {
        return Clipboard.access$300(this.this$0, args[0], args[1]);
    }
    
    @Override
    public long method5(final long[] args) {
        return Clipboard.access$400(this.this$0, args[0]);
    }
    
    @Override
    public long method8(final long[] args) {
        return Clipboard.access$500(this.this$0, (int)args[0], args[1]);
    }
}
