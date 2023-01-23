//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\32302\Desktop\新建文件夹 (2)"!

//Decompiled by Procyon!

package org.eclipse.swt.browser;

import org.eclipse.swt.internal.ole.win32.*;

class llIII extends COMObject
{
    final /* synthetic */ WebSite this$0;
    
    llIII(final WebSite this$0, final int[] argCounts) {
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
        return this.this$0.ShowContextMenu((int)args[0], args[1], args[2], args[3]);
    }
    
    @Override
    public long method4(final long[] args) {
        return this.this$0.GetHostInfo(args[0]);
    }
    
    @Override
    public long method5(final long[] args) {
        return this.this$0.ShowUI((int)args[0], args[1], args[2], args[3], args[4]);
    }
    
    @Override
    public long method6(final long[] args) {
        return this.this$0.HideUI();
    }
    
    @Override
    public long method7(final long[] args) {
        return this.this$0.UpdateUI();
    }
    
    @Override
    public long method8(final long[] args) {
        return this.this$0.EnableModeless((int)args[0]);
    }
    
    @Override
    public long method9(final long[] args) {
        return this.this$0.OnDocWindowActivate((int)args[0]);
    }
    
    @Override
    public long method10(final long[] args) {
        return this.this$0.OnFrameWindowActivate((int)args[0]);
    }
    
    @Override
    public long method11(final long[] args) {
        return this.this$0.ResizeBorder(args[0], args[1], (int)args[2]);
    }
    
    @Override
    public long method12(final long[] args) {
        return this.this$0.TranslateAccelerator(args[0], args[1], (int)args[2]);
    }
    
    @Override
    public long method13(final long[] args) {
        return this.this$0.GetOptionKeyPath(args[0], (int)args[1]);
    }
    
    @Override
    public long method14(final long[] args) {
        return this.this$0.GetDropTarget(args[0], args[1]);
    }
    
    @Override
    public long method15(final long[] args) {
        return this.this$0.GetExternal(args[0]);
    }
    
    @Override
    public long method16(final long[] args) {
        return this.this$0.TranslateUrl((int)args[0], args[1], args[2]);
    }
    
    @Override
    public long method17(final long[] args) {
        return this.this$0.FilterDataObject(args[0], args[1]);
    }
}
