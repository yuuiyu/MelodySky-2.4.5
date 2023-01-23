//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.*;
import org.eclipse.swt.internal.*;
import org.eclipse.swt.internal.win32.*;

class lIlll extends COMObject
{
    final /* synthetic */ WebSite this$0;
    
    lIlll(final WebSite this$0, final int[] argCounts) {
        this.this$0 = this$0;
        super(argCounts);
    }
    
    @Override
    public long method0(final long[] args) {
        final GUID guid = new GUID();
        COM.MoveMemory(guid, args[0], GUID.sizeof);
        if (COM.IsEqualGUID(guid, COM.IIDIDispatch)) {
            OS.MoveMemory(args[1], new long[] { this.this$0.iDispatch.getAddress() }, C.PTR_SIZEOF);
            this.this$0.AddRef();
            return 0L;
        }
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
        return this.this$0.GetTypeInfoCount(args[0]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.GetTypeInfo((int)args[0], (int)args[1], args[2]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.GetIDsOfNames((int)args[0], args[1], (int)args[2], (int)args[3], args[4]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.Invoke((int)args[0], (int)args[1], (int)args[2], (int)args[3], args[4], args[5], args[6], args[7]);
    }
}
