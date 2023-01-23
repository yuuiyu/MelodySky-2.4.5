//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.dnd;

import org.eclipse.swt.internal.ole.win32.*;

class ll extends COMObject
{
    final /* synthetic */ DropTarget this$0;
    
    ll(final DropTarget this$0, final int[] argCounts) {
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
        if (args.length == 5) {
            return this.this$0.DragEnter(args[0], (int)args[1], (int)args[2], (int)args[3], args[4]);
        }
        return this.this$0.DragEnter_64(args[0], (int)args[1], args[2], args[3]);
    }
    
    @Override
    public long method4(final long[] args) {
        if (args.length == 4) {
            return this.this$0.DragOver((int)args[0], (int)args[1], (int)args[2], args[3]);
        }
        return this.this$0.DragOver_64((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.DragLeave();
    }
    
    @Override
    public long method6(final long[] args) {
        if (args.length == 5) {
            return this.this$0.Drop(args[0], (int)args[1], (int)args[2], (int)args[3], args[4]);
        }
        return this.this$0.Drop_64(args[0], (int)args[1], args[2], args[3]);
    }
}
